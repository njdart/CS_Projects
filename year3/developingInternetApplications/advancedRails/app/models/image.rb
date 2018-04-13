class Image < ApplicationRecord
  belongs_to :user
  has_attached_file :photo, styles: { large: '124x124#',
                                      medium: '80x80#',
                                      small: '50x50#',
                                      tiny: '30x30#' }
  validates_attachment :photo,
                       content_type: { content_type: ["image/jpg", "image/jpeg", "image/png", "image/gif"] }

end