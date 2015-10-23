package parser.MSSQL2MySQL

import java.io._
import java.util
import java.util.concurrent.{LinkedBlockingQueue, ThreadPoolExecutor, TimeUnit}

import json.{MSSQLConnInfoJsonUtil, MSSQLConnInfo, MySQLConnInfoJsonUtil, MySQLConnInfo}
import mssql.MSSQLUtil

/**
 * Created by root on 15-7-30.
 */
object CommodityFutureMSSQL2MySQLMultiThread{
  //  log class


  val MSSQLConfigFile: String = "./src/test/resources/MSSQLConnConfig.json"
  val MSSQLInfo: MSSQLConnInfo = MSSQLConnInfoJsonUtil.getInfo(MSSQLConfigFile)


  def main(args: Array[String]) {

    val linkedQueue: LinkedBlockingQueue[Runnable] = new LinkedBlockingQueue[Runnable]
    val executor: ThreadPoolExecutor = new ThreadPoolExecutor(30, 30, 7, TimeUnit.DAYS, linkedQueue)// All threads wait for 2 days max
    try {
      // Get MSSQL tables
      val tableList: util.ArrayList[String] = MSSQLUtil.getTableList(MSSQLInfo.getConnString)

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
