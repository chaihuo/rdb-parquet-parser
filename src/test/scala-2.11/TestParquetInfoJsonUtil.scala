import java.io.{IOException, FileNotFoundException}

import json.ParquetInfoJsonUtil

/**
 * Created by root on 15-8-14.
 */
object TestParquetInfoJsonUtil extends org.specs2.mutable.Specification {
  "This is json.ParquetInfoJsonUtil test." +
    "\r\n" +
    "And this test covers its father class: json.JsonConfigUtil." >> {
    "where configuration (Json)file configuration.json must contain following objects" >> {
      val jsonString = ParquetInfoJsonUtil.ReadJsonFile("./src/test/resources/configuration.json")
      jsonString must contain("MYSQL_HOST")
      jsonString must contain("MYSQL_PORT")
      jsonString must contain("MYSQL_DB_NAME")
      jsonString must contain("MYSQL_USER")
      jsonString must contain("MYSQL_PWD")
      jsonString must contain("MYSQL_TABLE")
      jsonString must contain("MYSQL_PRIMARY_COLUMN")
      jsonString must contain("DATA_SET_LIMIT")
    }
    "when configuration file is ivalid, functions should throw the file invalid exception" >> {
      ParquetInfoJsonUtil.ReadJsonFile("") must throwA(new FileNotFoundException("Configuration file does not exist"))
      ParquetInfoJsonUtil.getInfo("") must throwA(new FileNotFoundException("Configuration file does not exist"))
    }
    "when configuration contains error, functions should throw objects invalid exception" >> {
      ParquetInfoJsonUtil.getInfo("/root/IdeaProjects/rdb-parquet-parser/src/test/resources/ImproperConfigurationFiles/empty_configuration_file.json") must throwA(new IOException("Configuration file maybe empty or contains error"))
      ParquetInfoJsonUtil.getInfo("/root/IdeaProjects/rdb-parquet-parser/src/test/resources/ImproperConfigurationFiles/configuration_missing_objects.json") must throwA(new IllegalArgumentException("Configuration file has missing some objects"))
      ParquetInfoJsonUtil.getInfo("/root/IdeaProjects/rdb-parquet-parser/src/test/resources/ImproperConfigurationFiles/configuration_large_LIMIT.json") must throwA(new IllegalArgumentException("DATA_SET_LIMIT is too large, this may cause performance issue"))
      ParquetInfoJsonUtil.getInfo("/root/IdeaProjects/rdb-parquet-parser/src/test/resources/ImproperConfigurationFiles/configuration_invalid_LIMIT.json") must throwA(new IllegalArgumentException("DATA_SET_LIMIT is invalid"))
    }
    "where example 2 must be true" >> {
      2 must_== 2
    }
  }



}
