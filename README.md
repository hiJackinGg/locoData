# locoData

<i>(Start: mvn jetty:run.)</i><br><br>
<b>Current project is a part of analytic system for diploma work.</b><br><br>
<b><i>Project includes modules:</i></b><br>
<ul>
<li>parser (/com/parse), that converts specific binary file (special file) containing data about values of locomotive sensors to JSON file. It parses file using multithreading (one file size is large (over 100 mb)) To load original file and convert run /com/parse/LocalFileUploader class<br></li>
<li>data access layer to store parsed data to database (/com/db);<br></li>
<li>rest service to retrieve necessary data (from database) in JSON format and display on client side (for example in charts). Also can be loaded file to store it in database (URI:localhost/fileService/upload/zip)<br></li>
</ul>
It's necessary to store large amount of data so that it's used NoSQL database Cassandra;<br>
<b>Used technologies:</b> Java 8, CassandraDB, Jersey(REST), Maven, Jetty embbeded server.
