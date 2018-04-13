class CreateUserDetails < ActiveRecord::Migration[5.0]
  def change
    create_table :user_details do |t|
      t.string :login
      t.string :salt
      t.string :crypted_password
      t.references :user, foreign_key: true
    end
  end
end
