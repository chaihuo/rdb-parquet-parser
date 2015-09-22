package parser

import java.io.IOException
import java.sql.{SQLException, ResultSet}

import model.CommodityFuture
import mssql.MSSQLUtil
import mysql.MySQLUtil

/**
 * Created by root on 15-9-21.
 */
class CommodityFutureParser(tableName: String, limit: Int) extends Runnable {
  val MSSQLConnString : String = "jdbc:sqlserver://172.19.17.222:1433;databaseName=GTA_MFL1_TAQ_201505;user=sa;password=sa;"

  val MySQLConnString: String = "jdbc:mysql://qkdata1.cltv2qruve9e.rds.cn-north-1.amazonaws.com.cn:3306/HF_Future?useUnicode=true&characterEncoding=utf8"
  val MySQLuser: String = "hf_app"
  val MySQLkey: String = "hf_app%2015"
  val tn: String = tableName
  val l: Int = 1000

//  // Constructor
//  def this(tableName : String, limit : String) = {
//    this(tn, l);
//  }

  override def run(): Unit = {
    val RowCount: Int = MSSQLUtil.getRowCount(MSSQLConnString, tn)
    if(RowCount <= 0) {
      throw new IOException("Table " + tn + " is empty.")
    }
    val targetContractID: String = MSSQLUtil.getContractID(MSSQLConnString, tn)
    val targetTableName: String = CommodityFuture.whichTable(targetContractID)
    if(targetTableName != null) {
      if(RowCount > l) {
        var parsedCount = 0
        while (parsedCount <= RowCount) {

          val rs: ResultSet = MSSQLUtil.getTable(
            MSSQLConnString,
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
            MySQLUtil.insertCommodityFuture(MySQLConnString, MySQLuser, MySQLkey, targetTableName, data)
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
          MSSQLConnString,
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
          MySQLUtil.insertCommodityFuture(MySQLConnString, MySQLuser, MySQLkey, targetTableName, data)
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