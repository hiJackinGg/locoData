CREATE TABLE data (
  locomotive_id int,
  parameter_id int,
  section_a boolean,
  time timestamp,
  value int,
PRIMARY KEY ((locomotive_id, parameter_id, section_a), time)
) WITH CLUSTERING ORDER BY (time DESC);


SELECT * FROM DATA WHERE parameter_id = 11 AND locomotive_id =494 AND section_a =False AND time > 0;