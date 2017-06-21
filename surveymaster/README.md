# SurveyMaster

## Functionalities

The implementation focused on the REST web service part, realizing the 
business requirement 1: *Providing information on available Market Surveys*.

The API has three main functionalities.

* **Browse available market surveys**: the client can explore available 
survey providers, subjects and surveys. The `/surveys` endpoint offers 
search engine to filter surveys based on all its properties.

* **Subscribe a particular search**: the client can subscribe a particular 
search, so that he can receive the results with a defined periodicity via 
the preferred channel.

* **Place an order**: the client can place an order to purchase a particular 
survey, choosing the channel for delivery.

## Assumptions

### Authentication

The implementations assumes that there is a proper authentication and 
authorization service. Notably, the endpoints `/subscription` an `orders` 
are clearly requester-specific, as they show and manipulate requester-related 
data.

Thus, in these endpoints' implementation a user id has been hardcoded.

## Possible improvements

The application is very simple and there are many improvement areas left, 
due to tight time constraint (implementation time has been about two days).

### Logging

The application has almost no logging, excluding the one emitted by the 
framework. Of course a real application should have a proper logging, but its 
lack in a simple proof-of-concept is acceptable.

### Error handling

There is no proper error handling. The endpoint implementations throw 
exceptions, as there should be a centralized error handler taking care of 
them. The framework used allows that.

### Direct use of *Repository classes

Ideally, the use of a service which eventually wraps the persistence layer 
should be preferred. In that case, the operation are essentially a simple 
CRUD, so useless (and time consuming) architecture complexity has been avoided.

## Testing

The application does not ship unit tests. As there is almost no business logic, 
the unit testing is quite superfluous. The assumptions, here, is that the 
framework works well...

To live test the application, a [Postman](https://www.getpostman.com/) 
collection is provided. It ships all REST call available, already configured 
to be used. Each one has a brief documentation too.

The application can be built and executed using [Maven](https://maven.apache.org/), 
easily installable on any major Linux distribution. To build and run the code 
it is enough to run the following command in the project's root folder:

    $ mvn tomcat7:run [-Dserver.port=<port>] [-Dserver.path=<path>]

The command will setup a Tomcat 7 server listening on port 8080 if not 
otherwise specified. Consider that the Postman collection hits 
`http://localhost:8080/surveymaster`, though.

## Conclusions

I hope you like it! :)


