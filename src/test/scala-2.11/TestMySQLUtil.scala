import java.sql.ResultSet

import json.{ParquetInfoJsonUtil, ParserInfo}
import mysql.MySQLUtil

/**
 * Created by root on 15-8-14.
 */
object TestMySQLUtil extends org.specs2.mutable.Specification {
  "This is mysql.MySQLUtil test." +
    "\r\n" +
    "Most of tests aims to make sure that function arguments are correct." >> {
    "Test getTable(connString: String, tableName: String, columnName: String)" >> {
      val pi: ParserInfo = ParquetInfoJsonUtil.getInfo()
      MySQLUtil.getTable(null, pi.getMysqlTable, pi.getMysqlPrimaryColumn) must throwA(new IllegalArgumentException("MySQL connection string is null or empty"))
      MySQLUtil.getTable(pi.getConnString, null, pi.getMysqlPrimaryColumn) must throwA(new IllegalArgumentException("SQL argument table name is null or empty"))
      MySQLUtil.getTable(pi.getConnString, pi.getMysqlTable, null) must throwA(new IllegalArgumentException("SQL argument column name is null or empty"))
    }
    "Test getTable(connString: String, tableName: String, columnName: String, startNum: Int, limitNum: Int)" >> {
      val pi: ParserInfo = ParquetInfoJsonUtil.getInfo()
      MySQLUtil.getTable("", pi.getMysqlTable, pi.getMysqlPrimaryColumn, 10, 1000) must throwA(new IllegalArgumentException("MySQL connection string is null or empty"))
      MySQLUtil.getTable(pi.getConnString, null, pi.getMysqlPrimaryColumn, 10, 1000) must throwA(new IllegalArgumentException("SQL argument table name is null or empty"))
      MySQLUtil.getTable(pi.getConnString, pi.getMysqlTable, null, 10, 1000) must throwA(new IllegalArgumentException("SQL argument column name is null or empty"))
      MySQLUtil.getTable(pi.getConnString, pi.getMysqlTable, pi.getMysqlPrimaryColumn, -10, 1000) must throwA(new IllegalArgumentException("SQL argument start number or limit number is illegal"))
      MySQLUtil.getTable(pi.getConnString, pi.getMysqlTable, pi.getMysqlPrimaryColumn, 10, 1000000) must throwA(new IllegalArgumentException("DATA_SET_LIMIT is too large, this may cause performance issue"))
    }
    "Test getRowCount(connString: String, tableName: String)" >> {
      val pi: ParserInfo = ParquetInfoJsonUtil.getInfo()
      MySQLUtil.getRowCount("", pi.getMysqlTable) must throwA(new IllegalArgumentException("MySQL connection string is null or empty"))
      MySQLUtil.getRowCount(pi.getConnString, "") must throwA(new IllegalArgumentException("SQL argument table name is null or empty"))
    }
  }



}
