# Liber #

Liber is a web content management system built in Java. It is designed with a few goals in mind:

- Flexible Metadata
- Independent of Presentation Environment
- Support Custom Workflow
- Support Information Lifecycle: Creation, Usage, Preservation, Destruction

## Installation Instructions ##

- Create a UTF-8 encoded MySQL database
- Run the `liber-sql/structure.sql` file to create the database schema
- Run the `liber-sql/data.sql` file to insert necessary base data
- Create a JDBC datasource on the app server with JNDI name of `jdbc/liber`
- Run maven install on `liber-web`
- Install `liber-web.war` on app server

## Bug Tracking ##
Bugs will be tracked on the [project's issue tracker](https://github.com/marshmellow1328/liber/issues).

## Contributing ##
Submit a pull request to contribute code. To contribute in other ways, create a question on the [issue tracker](https://github.com/marshmellow1328/liber/issues).
