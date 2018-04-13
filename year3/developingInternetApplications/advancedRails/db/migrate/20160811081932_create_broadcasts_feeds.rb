class CreateBroadcastsFeeds < ActiveRecord::Migration[5.0]
  def change
    # No primary key id since a join table
    create_table :broadcasts_feeds, id: false, force: true do |t|
      t.references :broadcast, foreign_key: true
      t.references :feed, foreign_key: true
    end
  end
end
