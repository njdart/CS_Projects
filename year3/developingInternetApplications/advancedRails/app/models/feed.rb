class Feed < ApplicationRecord
  has_and_belongs_to_many :broadcasts
end
