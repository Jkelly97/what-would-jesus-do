## Summary

This application will feature the KJV Bible, along with many others, and the ability to find passages by the key word(s) provided.  
This may include words that may not be in the text itself but merely associated with specified verses.
Users will be able to fit reading the bible into their normal lives while working around time restraints.

* The ability to pull the KJV Bible to the application to read.
* The ability to navigate through the Bible at will.
* The to search to passages based on key words.
* The ability to save specific passages as "favorites."

## Intended users

* Christians who may be struggling with their faith.

> As a Christian myself I know sometimes life sucks. With this application, for any problem, there is an easy to attempt finding a solution. The 
key word search bar will find an answer for a majority of you problems. This way you don't have to spend hours scowering page after page to find 
what you are looking for.

* People of other or no faith. 

> As someone who may be interested in Christianity you might just not know where to start.  With this application it will be made 
  easier on you, in giving you an outlet into the Bible. This will be done by taking away excuses such as "I don't have time to read the Bible" and 
  "I don't know how to find a church". With this you aree able to remove excuses not to try you hand in the faith.


## Functionality

* There will be a drop down search menu with top searches along with a "favorites" / search history.
* This drop down menu will also take give data to the cloud and ranking top searches to give suggestions to other users.
* This app will compile and feature common and most popular searches to other users.
* They will be able to save their favorite passages to make reading them a sinch.
* Add notifications for a daily bible verse or reminding users to read a passage each day.
* Add a function to search for nearby churches to attend.


## Persistent data

* The app will store information about user searches to provide feedback to the app.
* Popular key word searches such as anxiety, depression, money, and others will be saved.
* The app will also allow for users to save specific passages allowing users to stop and come back to searches.
    
## Device/external services

* Using a API Bible web service of sorts I will be able to connect users to their preferred version of the Bible.

> This webservice will provide the passages and different versions of the Bible. This service provides 2500+ versions of the Bible in 1600+ languages.
> Without the service the application will not be able to access any version of the bible and therefor be unable to run.
>  https://docs.api.bible/

* Using a form of user account service to store favorite passages and recent searches

> This api will be used to provide users with accounts. By creating an account they will be provided with the ability to save searches, passages and searches of churches.
> Yes, the application will be usable without the help of this api.  Without this service users can still access the versions of the bible.
>https://developers.google.com/admin-sdk/directory/v1/guides/manage-users

* Using google maps to search for nearby churches.

> This service will be used to pull up a map with churches in the surrounding area. This will allow users to find suitable churches after moving or even just while traveling. 
> Yes the application should be able to perform without the service.
> https://developers.google.com/places/web-service/search

## Stretch goals/possible enhancements 

* The future goal with this application would be to add many versions of the Bible using the API Bible service.
* Add in videos of sermons for people to watch. Using the related search bar users will be able to find these videos.
* There will also be multiple languages to allow users who do not speak English the ability to use the application.  This will also be possible using the API BIble service.

## [Wireframe](wireframe.md)


## [Entity Relationship Diagram](erd.md)