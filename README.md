# Projet 2 : Gamification API

### How to run the Gamification API

The gamification API consist of 2 entities : the database and the API itself. In order to run the gamification API, you need to first run the database using the following command (withinthe Projet_2 directory) : 
```
docker-compose pull
docker-compose up database
```
Then, you either run the API locally (from the Projet_2/gamification-impl directory) :
```
mvn spring-boot:run
```
Or you can run it in a container using docker-compose (from the Projet_2/gamification-impl directory): 
```
docker-compose up api
```
Note : If you try to directly launch both the database and the API using docker-compose, you may end up with the api crashing.

### Endpoint

The gamification API provides 6 major Endpoints : 
Application : to register an application
Badges to register badges
ScoreScale to register ScoreScales
Rules to register the Rules
Event for the API to send the Events to be processed
User to retrieve gamified informations about the Users.

The application does not provide more information (such as advanced statistics).

No Delete operation is available on the Gamification API (so be careful) for the following reason : 
Deleting objects such as badge would force us to go through multiples tables to delete said badge and end up with incoherences (rules doing nothing for example).
There are some objects that could be deleted without problem (rules for example) but this has not been done.

No Endpoint uses pagination so far, for one main reason is that except for the User Endpoint, no endpoint returns a (potentially) long list of objects.



### Cucumber Tests

We built some test to check our endpoints and their CRUD methods

We have Scenarios for Application registration, badges, users, scorescales, events.

In each we test multiple things, like

* Badges:
  * creation and POST of a badge
  * if we can correctly GETa badge with its name
  * if we can't register twice the same badge for a same application
* Users:
  * creation of a User
  * check if we can GET it from an event where he is linked with the /users/{inGamifiedAppId} endpoint
  * try to GET an inexistant user
  * GET the list of all users,...

#### How to run the tests:

* Launch the database: `docker-compose up database`
* Lauch the API: (in `gamification-impl`) do `mvn spring-boot:run`
* Launch the tests: (in `gamification-specs`) do  `mvn clean test`)

### Things missing

* Some advanced request, for exemple to get a ranking of all user by their points, or the percentage of users having a certain badge
* Some other CRUD methods for our endpoints (and test with it), like for modify an entry, or to delete.




