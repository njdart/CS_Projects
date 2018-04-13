class User < ApplicationRecord

  has_many :posts, dependent: :destroy

  def full_name
    "#{firstname} #{lastname}"
  end

end
