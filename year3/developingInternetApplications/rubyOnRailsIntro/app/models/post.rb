class Post < ApplicationRecord
  belongs_to :user

  def get_body_short
    body[0..80] + '...'
  end
end
