package parser.MSSQL2MySQL

import java.io.IOException
import java.sql.{ResultSet, SQLException}

import json.{MSSQLConnInfoJsonUtil, MSSQLConnInfo, MySQLConnInfoJsonUtil, MySQLConnInfo}
import model.CommodityFuture
import mssql.MSSQLUtil
import mysql.MySQLUtil

/**
 * Created by root on 15-9-21.
 */
class CommodityFutureParser(tableName: String, limit: Int) extends Runnable {
  val MySQLConfigFile: String = "./src/test/resources/MySQLConnConfig.json"
  val MySQLInfo: MySQLConnInfo = MySQLConnInfoJsonUtil.getInfo(MySQLConfigFile)
  val MSSQLConfigFile: String = "./src/test/resources/MSSQLConnConfig.json"
  val MSSQLInfo: MSSQLConnInfo = MSSQLConnInfoJsonUtil.getInfo(MSSQLConfigFile)

  val tn: String = tableName
  val l: Int = limit



  override def run(): Unit = {
    val RowCount: Int = MSSQLUtil.getRowCount(MSSQLInfo.getConnString, tn)
    if(RowCount <= 0) {
      throw new IOException("Table " + tn + " is empty.")
    }
    val targetContractID: String = MSSQLUtil.getContractID(MSSQLInfo.getConnString, tn)
    val targetTableName: String = CommodityFuture.whichTable(targetContractID)
    if(targetTableName != null) {
      if(RowCount > l) {
        var parsedCount = 0
        while (parsedCount <= RowCount) {

          val rs: ResultSet = MSSQLUtil.getTable(
            MSSQLInfo.getConnString,
            tn,
            "TDATETIME",
            parsedCount,
            l)
          try {
            var data: String = ""
            if(rs.next()) {
              data += CommodityFuture.getData(rs)
            }
            while ((rs.next)) {
              // Collect data
              data += ","
              data += CommodityFuture.getData(rs)
            }
            MySQLUtil.insertCommodityFuture(MySQLInfo.getConnStringWithoutAccount, MySQLInfo.getMysqlUser, MySQLInfo.getMysqlPwd, targetTableName, data)
            parsedCount += l
            print("From table:" + tn + ",page:" + parsedCount + "\r\n")
          }
          catch {
            case e: SQLException => {
              e.printStackTrace
            }
          } finally {
            //      LOG.info("Number of lines: " + lineNumber)
          }
        }
      } else {

        val rs: ResultSet = MSSQLUtil.getTable(
          MSSQLInfo.getConnString,
          tn,
          "TDATETIME",
          0,
          l)
        try {
          var data: String = ""
          if(rs.next()) {
            data += CommodityFuture.getData(rs)
          }
          while ((rs.next)) {
            // Collect data
            data += ","
            data += CommodityFuture.getData(rs)
          }
          MySQLUtil.insertCommodityFuture(MySQLInfo.getConnStringWithoutAccount, MySQLInfo.getMysqlUser, MySQLInfo.getMysqlPwd, targetTableName, data)
          print("From table:" + tn + ".\r\n")
        }
        catch {
          case e: SQLException => {
            e.printStackTrace
          }
        } finally {
          //      LOG.info("Number of lines: " + lineNumber)
        }
      }
    }
  }


}