# rdb-parquet-parser
rdb-parquet-parser
MySQL data into parquet!
===================


This is a useful tool to parse rdbms(now focus on mysql) data into parquet。

----------


Documents
-------------



> **Note:**

> - The is a sbt project, pls check versions through /build.sbt.
> - Main function are implemented by scala.
> - Use specs2 for unit test.


#### <i class="icon-file"></i> MySQL functions

Get data from MySQL database.

#### <i class="icon-folder-open"></i> /src/main/scala-2.11/json

Implements functions to read Json file:
/src/test/resources/configuration.json
The Json file contains user configurations on MySQL connection and parser work folder.
