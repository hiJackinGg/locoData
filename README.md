# locoData

Start: mvn jetty:run.

This project includes parser, that converts specefic binary file containing data about values of locomotive sensors to JSON file.
Includes data access layer to store parsed data to database and rest service to retrieve necessary data in JSON format and display on client side (for example in charts).
It's necessary to store large amount of data so that it's used NoSQL database Cassandra.

Used technologies: Java 8, CassandraDB, Jersey(REST), Maven, Jetty embbeded server.
