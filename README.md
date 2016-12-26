# locoData

Start: mvn jetty:run.
<b>Current project is a part of analytic system.</b>
Project includes:<br>
-parser (/com/parser), that converts specefic binary file containing data about values of locomotive sensors to JSON file.<br>
-data access layer to store parsed data to database (/com/db)<br>
-rest service to retrieve necessary data (from database) in JSON format and display on client side (for example in charts).<br>
It's necessary to store large amount of data so that it's used NoSQL database Cassandra.<br>
<b>Used technologies:</b> Java 8, CassandraDB, Jersey(REST), Maven, Jetty embbeded server.
