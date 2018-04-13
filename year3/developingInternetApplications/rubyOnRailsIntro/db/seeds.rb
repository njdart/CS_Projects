# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

body = "The path of the righteous man is beset on all sides by the iniquities of the selfish and the tyranny of evil "\
       "men. Blessed is he who, in the name of charity and good will, shepherds the weak through the valley of darkness"\
       ", for he is truly his brother's keeper and the finder of lost children. And I will strike down upon thee with "\
       "great vengeance and furious anger those who would attempt to poison and destroy My brothers. And you will know"\
       "My name is the Lord when I lay My vengeance upon thee."

(0..30).each do | i |
  user = User.create(firstname: "firstname #{i}", lastname: "lastname #{i}", email: "firstname#{i}.lastname#{i}@domain.com")

  (0..2).each do | j |
    Post.create(title: "Post #{j} for User #{i}", body: body, user: user)
  end
end