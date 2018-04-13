json.array!(@users) do |user|
   json.html "#{remote_image_for(user, @current_page, :small)}
              <span class='drop-list-surname'>#{user.surname}</span>"
end
