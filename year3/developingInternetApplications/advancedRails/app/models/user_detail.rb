require 'digest/sha2'
class UserDetail < ApplicationRecord
  validates_presence_of :login
  validates_presence_of :password
  validates_uniqueness_of :login, within: 3..40

  validates_confirmation_of :password

  belongs_to :user


  # We delay encrypting of the password until the moment we
  # save. This lifecycle method is called at that moment.
  # This ensures that we keep the plain text 
  # password in the
  # object to allow validates_confirmation_of to compare
  # with implicitly created password_confirmation 
  # instance variable.
  before_save :prepare_password

  attr_reader :password

  def password=(pass)
    return if pass.blank?
    @password = pass
  end

  def self.authenticate(name, passwd)
    user_detail = self.find_by_login(name)
    if user_detail
      expected_password = 
        encrypt_password(passwd, user_detail.salt)
      if user_detail.crypted_password != expected_password
        user_detail = nil
      end
    end
    user_detail
  end

  private

  def prepare_password
    create_new_salt
    self.crypted_password =
        UserDetail.encrypt_password(@password, self.salt)
  end

  def create_new_salt
    self.salt = self.object_id.to_s + rand.to_s
  end

  def self.encrypt_password(password, salt)
    string_to_hash = password + "wibble" + salt
    Digest::SHA256.hexdigest(string_to_hash)
  end

end