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



