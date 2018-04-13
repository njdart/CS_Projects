class UsersController < ApplicationController
  before_action :set_current_page, except: [:index]
  before_action :set_user, only: [:show, :edit, :update, :destroy]
  
  before_action :admin_required, only: [:index, :search, :destroy]

  rescue_from ActiveRecord::RecordNotFound, with: :show_record_not_found

  def search
    # Use will_paginate's :conditions and :joins to search across both the
    # users and user_details tables. search_fields private method will add a field
    # for each checkbox field checked by the user, or returns nil
    # if none were checked. The search_conditions method is defined
    # in lib/searchable.rb and either searches across all columns identified in
    # User.searchable_by or uses the search_fields to constrain the search

    respond_to do |format|
      format.html {
        @users = User.where(User.search_conditions(params[:q], search_fields(User)))
                     .joins(:user_detail)
                     .paginate(page: params[:page],
                               per_page: params[:per_page])
                     .order('surname, firstname')

        render 'index'
      }
      # Deal with incoming Ajax request for JSON data for autocomplete search field
      format.json {
        @users = User.where(User.search_conditions(params[:q], search_fields(User)))
                      .joins(:user_detail)
                      .order('surname, firstname')
        puts "Users are #{@users.join(' ')}"
      }
    end
  end
  
  # GET /users
  # GET /users.json
  # Can be called either by an admin to show any user account or else by
  # a specific user to show their own account, but no one else's
  def index
      @users = User.paginate(page: params[:page],
                             per_page: params[:per_page])
                   .order('surname, firstname')
  end

  # GET /users/1
  # GET /users/1.json
  def show
    if current_user.id == @user.id || is_admin?
      respond_to do |format|
        # If an Ajax request then send back a partial to include just
        # the user's record without layout headers and footers
        format.js { render partial: 'show_local',
                           locals: {user: @user, current_page: @current_page},
                           layout: false }
        format.html # show.html.erb
        format.json # show.json.builder
      end
    else
      indicate_illegal_request I18n.t('users.not-your-account')
    end
  end

  # GET /users/new
  def new
    @user = User.new
    @user.user_detail = UserDetail.new
  end

  # GET /users/1/edit
  # Can be called either by an admin to edit any user account or else by
  # a specific user to edit their own account, but no one else's
  def edit
    if !(current_user.id == @user.id || is_admin?)
      indicate_illegal_request I18n.t('users.not-your-account')
    end
  end

  # POST /users
  # POST /users.json
  # At the moment we are only allowing the admin user to create new
  # accounts.
  def create
    @user = User.new(user_params)
    
    # Only create a new image if the :image_file parameter
    # was specified
    @image = Image.new(photo: params[:image_file]) if params[:image_file]
    
    # The ImageService model wraps up application logic to
    # handle saving images correctly
    @service = ImageService.new(@user, @image)

    respond_to do |format|
      if @service.save # Will attempt to save user and image
        format.html { redirect_to(user_url(@user, page: @current_page),
                                  notice: I18n.t('users.account-created')) }
        format.json { render :show, status: :created, location: @user }
      else
        format.html { render :new }
        format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /users/1
  # PATCH/PUT /users/1.json
  # Can be called either by an admin to update any user account or else by
  # a specific user to update their own account, but no one else's
  def update
    if current_user.id == @user.id || is_admin?
      @image = @user.image
      @service = ImageService.new(@user, @image)

      respond_to do |format|
        if @service.update_attributes(user_params, params[:image_file])
          format.html { redirect_to(user_url(@user, page: @current_page),
                                    notice: I18n.t('users.account-created')) }
          format.json { render :show, status: :ok, location: @user }
        else
          format.html { render :edit }
          format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
    else
      indicate_illegal_request I18n.t('users.not-your-account')
    end
  end

  # DELETE /users/1
  # DELETE /users/1.json
  def destroy
    @user.destroy
    respond_to do |format|
      format.html { redirect_to users_url(page: @current_page) }
      format.json { head :no_content }
    end
  end

  private
  # Use callbacks to share common setup or constraints between actions.
  def set_user
    @user = User.find(params[:id])
  end

  def set_current_page
    @current_page = params[:page] || 1
  end
  
  def search_fields(table)
    fields = []
    table.search_columns.each do |column|
      # The parameters have had the table name stripped off so we
      # have to to the same to each search_columns column
      fields << column if params[column.sub(/^.*\./, "")]
    end
    fields = nil unless fields.length > 0
    fields
  end
  
  def indicate_illegal_request(message)
    respond_to do |format|
      format.html {
        flash[:error] = message
        redirect_back_or_default(home_url)
      }
      format.json {
        render json: "{#{message}}",
               status: :unprocessable_entity
      }
    end
  end

  def show_record_not_found(exception)
    respond_to do |format|
      format.html {
        redirect_to(users_url(page: @current_page),
                    notice: I18n.t('users.account-no-exists'))
      }
      format.json {
        render json: "{#{I18n.t('users.account-no-exists')}}",
               status: :unprocessable_entity
      }
    end
  end

  # Never trust parameters from the scary internet, only allow the white list through.
  def user_params
    params.require(:user).permit(:surname, :firstname, :phone, :grad_year, :jobs, :email, user_detail_attributes: [:id, :password, :password_confirmation, :login])
  end
end
