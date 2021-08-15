Read file Design.docx


**kafka**,
**zookeeper**,
**postgres** - in the role of an external data source,

**football-match** - REST requests transformation into events,
input validation with the domain model,

**connect** - Kafka Connect with Debezium PostgreSQL connector,
writes events for insert or update operations on PLAYERS table to a single Kafka topic,

**football-player** is receiving notifications from connect service 
and creating only a single PlayerStartedCareer event using Processor API (see the code),