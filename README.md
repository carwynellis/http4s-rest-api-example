# http4s-rest-api-example
A simple http4s rest api acting as a frontend to another http service

## Overivew

Implements the following
* retrieval of asteroids for a given date range
* sorting of retrieved asteroids by name
* retrieval of an asteroid by id

The `bin` directory contains some scripts that run requests against
the running service which can be started with 
```
sbt run
```

## TODOs
* only name and id are taken from neo api response - this should be
extended to include a wider range of data
* client and server logging
* error handling - only the happy path is covered properly
* configuration - properties are hardcoded as defaults for now
