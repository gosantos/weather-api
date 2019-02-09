[![CircleCI](https://circleci.com/gh/gosantos/weather-api/tree/master.svg?style=svg)](https://circleci.com/gh/gosantos/weather-api/tree/master)

# Weather API

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* [Java 8](https://sdkman.io/usage)

### Installing

```
$ git clone https://github.com/gosantos/weather-api

$ cd weather-api

$ ./gradlew clean build

$ ./gradlew bootRun

$ curl http://localhost:8080/data/London
```

## Running the tests

Automated tests

```
$ ./gradlew test
```

### Docs

API Docs were built using Swagger. You can see it on the link below.

[API Documentation](https://mysterious-spire-64282.herokuapp.com/swagger-ui.html)

## Deployment

The application is deployed on Heroku.
 
Every commit on the master branch triggers a circle ci build,
after the build succeeds, the application is deployed.

## Final considerations

Due the lack of time I decided to use a Open Weather Library for Java. I had no time to create *real* integration tests, therefore I created some end to end tests using my free api key. No corner cases were tested. I wish to have more time to do that.   