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

