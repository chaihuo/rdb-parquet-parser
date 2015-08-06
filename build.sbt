name := "rdb-parquet-parser"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.apache.parquet" % "parquet-column" % "1.8.1",
  "org.apache.parquet" % "parquet-hadoop" % "1.8.1",
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.apache.hadoop" % "hadoop-client" % "2.7.1",
  "org.apache.hadoop" % "hadoop-common" % "2.7.1",
//  "net.sf.json-lib" % "json-lib" % "2.4"
  "com.google.code.gson" % "gson" % "2.3.1"
)




    