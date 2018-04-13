Rails.application.routes.draw do
  resources :users do
    get 'search', on: :collection
  end
  
  resources :broadcasts, except: [:edit, :update]
  
  # A singleton resource and so no paths requiring ids are generated
  # Also, don't want to support editing of the session
  resource :session, only: [:new, :create, :destroy]
  
  get 'home', to: 'home#index', as: :home
  
  root 'home#index'
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
