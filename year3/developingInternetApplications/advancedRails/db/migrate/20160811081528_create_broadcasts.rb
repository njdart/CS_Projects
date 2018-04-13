class CreateBroadcasts < ActiveRecord::Migration[5.0]
  def change
    create_table :broadcasts do |t|
      t.text :content, null: :no # Must have some text, empty broadcasts not allowed
      t.references :user, foreign_key: true
      
      t.timestamps
    end
  end
end
