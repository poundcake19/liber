# Liber #

Liber is a web content management system designed for flexible metadata and reduced coupling with runtime systems.

### Installation Instructions ###

- Create a UTF-8 encoded MySQL database
- Run the `liber-sql/structure.sql` file to create the database schema
- Create a JDBC datasource on the app server with JNDI name of `jdbc/liber`
- Run maven install on `liber-web`
- Install `liber-web.war` on app server
