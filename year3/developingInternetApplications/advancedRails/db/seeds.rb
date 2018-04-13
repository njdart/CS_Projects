User.transaction do
  1..40.times do |i|
    user = User.create(surname: "Surname#{i}",
                       firstname: "Firstname#{i}",
                       email: "cwl#{i}@aber.ac.uk",
                       phone: '01970 622422',
                       grad_year: 1985)
    UserDetail.create!(login: "cwl#{i}",
                       password: 'secret',
                       user: user)
  end
  # Create one special admin user
  user = User.create!(surname: 'Loftus',
                      firstname: 'Chris',
                      email: 'cwl@aber.ac.uk',
                      phone: '01970 622422',
                      grad_year: 1985)
  UserDetail.create!(login: 'admin',
                     password: 'taliesin',
                     user: user)
                     
  # Create some dummy feeds
  Feed.create!(name: 'twitter')
  Feed.create!(name: 'facebook')
  Feed.create!(name: 'email')
  Feed.create!(name: 'RSS')
  Feed.create!(name: 'atom')
end