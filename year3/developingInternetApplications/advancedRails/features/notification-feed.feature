Feature: Notification feed
   As a logged in administrator
   I want to be able to broadcast to the notification feed
   So that CSA website users are pushed notifications to the CSA website
   on their browsers.  
    
   Scenario: Broadcast to the notification feed
     Given that user "admin" with password "taliesin" has logged in 
     When the admin user broadcasts "hello world" to the notification feed
     Then the current page should contain the text: "Broadcast was successfully saved and broadcast to all feeds"
     And the current page should show the text "hello world" in the notification feed