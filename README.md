# locoData

<i>(Start: mvn jetty:run.)</i><br><br>
<b>Current project is a part of analytic system.</b><br><br>
<b><i>Project includes:</i></b><br>
<ul>
<li>parser (/com/parser), that converts specefic binary file containing data about values of locomotive sensors to JSON file.<br></li>
<li>data access layer to store parsed data to database (/com/db);<br></li>
<li>rest service to retrieve necessary data (from database) in JSON format and display on client side (for example in charts);<br></li>
</ul>
It's necessary to store large amount of data so that it's used NoSQL database Cassandra;<br>
<b>Used technologies:</b> Java 8, CassandraDB, Jersey(REST), Maven, Jetty embbeded server.
