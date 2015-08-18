import java.io.FileNotFoundException

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
      ParquetInfoJsonUtil.ReadJsonFile("") must throwA(new FileNotFoundException)
      ParquetInfoJsonUtil.getInfo("") must throwA(new FileNotFoundException)
    }
    "where example 2 must be true" >> {
      2 must_== 2
    }
    "where example 2 must be true" >> {
      2 must_== 2
    }
  }



}
