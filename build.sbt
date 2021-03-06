name := "rdb-parquet-parser"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.apache.parquet" % "parquet-column" % "1.8.1",
  "org.apache.parquet" % "parquet-hadoop" % "1.8.1",
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.apache.hadoop" % "hadoop-client" % "2.7.1",
  "org.apache.hadoop" % "hadoop-common" % "2.7.1",
  "com.google.code.gson" % "gson" % "2.3.1",
  "org.specs2" % "specs2-core_2.11" % "3.6.4-20150813062946-0dbb642")
