package parser

import java.io._
import java.sql.{ResultSet, SQLException}
import java.util
import java.util.concurrent.{LinkedBlockingQueue, TimeUnit, ThreadPoolExecutor, ArrayBlockingQueue}

import model.{CommodityFuture, NationalDebt}
import mssql.MSSQLUtil
import mysql.MySQLUtil

/**
 * Created by root on 15-7-30.
 */
object CommodityFutureMSSQL2MySQLMultiThread{
  //  log class

  val MSSQLConnString : String = "jdbc:sqlserver://172.19.17.222:1433;databaseName=GTA_MFL1_TAQ_201507;user=sa;password=sa;"

  val MySQLConnString: String = "jdbc:mysql://qkdata1.cltv2qruve9e.rds.cn-north-1.amazonaws.com.cn:3306/HF_Future?useUnicode=true&characterEncoding=utf8"
  val MySQLuser: String = "hf_app"
  val MySQLkey: String = "hf_app%2015"
  val targetNationalDebtTableName: String = ""

  def main(args: Array[String]) {

    val linkedQueue: LinkedBlockingQueue[Runnable] = new LinkedBlockingQueue[Runnable]
    val executor: ThreadPoolExecutor = new ThreadPoolExecutor(30, 30, 7, TimeUnit.DAYS, linkedQueue)// All threads wait for 2 days max
    try {
      // Get MSSQL tables
      val tableList: util.ArrayList[String] = MSSQLUtil.getTableList(MSSQLConnString)

      // Convert tables in table listh

      val tableNum: Int = tableList.size()
      var currentTableNum: Int = 0
      val limit: Int = 1000
      while (currentTableNum < tableNum) {
        val tableName: String = tableList.get(currentTableNum)
        print(tableName)
        // Copy into MySQL table

        if(tableName != null) {
          val commodityFutureParser = new CommodityFutureParser(tableName, limit)
          executor.execute(commodityFutureParser)
          print(
            " Pool size：" + executor.getPoolSize()
              + "，queue size：" + executor.getQueue().size()
              + "，Completed task count：" + executor.getCompletedTaskCount()
              + ".\r\n"
          )
        }
        currentTableNum += 1
      }
      executor.shutdown()
    }
    catch {
      case e: IOException => {
        e.printStackTrace
      }
    }
    executor.awaitTermination(7, TimeUnit.DAYS)
    print("Finish!\r\n")
  }

  //  @throws(classOf[IOException])
  //  def convertMSSQL2MySQL(connString: String, tableName: String, columnName: String, startNum: Int, limitNum: Int) {
  //  }
  //  def main(args: Array[String]) {
  //    try {
  //      MysqlConvertUtils.convertSQLToParquet()
  //    }
  //    catch {
  //      case e: IOException => {
  //        e.printStackTrace
  //      }
  //    }
  //  }

}
