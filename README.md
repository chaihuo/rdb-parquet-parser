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



#### <i class="icon-folder-open"></i>Module : /src/main/scala-2.11/json

Implements functions to read Json file:
/src/test/resources/configuration.json
The Json file contains user configurations on MySQL connection and parser work folder.

#### <i class="icon-folder-open"></i>Module : /src/main/scala-2.11/mysql

Implements functions to get data from a MySQL database.
You can manage connection infomation and SQL page size by modify (Json) configuration file.
Configuration file see **configuration module**.

#### <i class="icon-folder-open"></i>Module : /src/main/scala-2.11/parser
Contains main logic code to get data from MySQL and parse thems into parquet.

#### <i class="icon-folder-open"></i>Module : /src/test/resources/configuration.json
> **Configurations:**

> - "MYSQL_HOST": "your_mysql_host_ip",
> - "MYSQL_PORT": "mysql_port",
> - "MYSQL_DB_NAME": "mysql_db_name",
> - "MYSQL_USER": "mysql_logic_user",
> - "MYSQL_PWD": "mysql_login_password",
> - "MYSQL_TABLE": "table_to_parse",
> - "MYSQL_PRIMARY_COLUMN": "primary_key_name",
> - "DATA_SET_LIMIT": "how_many_rows_to_parse_each_time",
> - "SCHEMA_FILE_PATH": "where_is_your_schema_file",
> - "OUTPUT_PATH": "where_you_can_find_your_parquet_output_file"

Here is an example:

> **Example:** 
>   rdb-parquet-parser/src/test/resources/configuration.json

#### <i class="icon-folder-open"></i>Module : /src/test/scala-2.11
Use scala specs2 as unit test framework.
specs2: http://etorreborre.github.io/specs2/

#### <i class="icon-folder-open"></i>Folder: /src/test/resources/ImproperConfigurationFiles

This folder contains bad configuration files, which used by  TestParquetInfoJsonUtil for testing ParquetInfoJsonUtil.scala.

#### <i class="icon-folder-open"></i>File: /src/test/resources/ImproperTestFiles/output_exist.parquet

This file is an empty file. It is used by TestMySQLConvertUtil for testing  MySQLConvertUtil.scala.

How to run/test using sbt.
-------------
First of all, you should install scala & sbt.

scala: http://www.scala-lang.org/documentation/getting-started.html
sbt: http://www.scala-sbt.org/0.13/tutorial/index.html

We use Ubuntu 14.1 + scala 2.11.7 + sbt 0.13.9.

#### run project
Change directory to project, and use 'sbt run'.
sbt will automatically compile and find main function, just try it!
Here is an example:
> **Example:**
> root@virtual-machine:~# cd /root/IdeaProjects/rdb-parquet-parser
> root@virtual-machine:~/IdeaProjects/rdb-parquet-parser# sbt run
>  **Output:**
> [info] Loading project definition from /root/IdeaProjects/rdb-parquet-parser/project
> [info] Set current project to rdb-parquet-parser (in build file:/root/IdeaProjects/rdb-parquet-parser/)
> [info] Running parser.MysqlConvertUtils 
> [……]Lots of output info
> [success] Total time: 7 s, completed Aug 19, 2015 5:15:24 PM

#### test project
Same to run project.
Change directory to project, and use 'sbt test'.
Tips: This will run all tests in your project.

>  **Output:**
> [info] Compiling 1 Scala source to /root/IdeaProjects/rdb-parquet-parser/target/scala-2.11/classes...
> [info] TestMySQLUtil
> [info] This is mysql.MySQLUtil test.
> [info] Most of tests aims to make sure that function arguments are correct.
> …………
> [info] Total for specification TestMySQLUtil
> [info] Finished in 264 ms
> [info] 3 examples, 0 failure, 0 error



Try it!

