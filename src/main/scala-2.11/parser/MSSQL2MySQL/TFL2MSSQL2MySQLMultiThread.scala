package parser.MSSQL2MySQL

import java.util
import java.util.concurrent.{LinkedBlockingQueue, ThreadPoolExecutor, TimeUnit}

import json.{MSSQLConnInfoJsonUtil, MSSQLConnInfo, MySQLConnInfoJsonUtil, MySQLConnInfo}

/**
 * Created by root on 15-7-30.
 */
object TFL2MSSQL2MySQLMultiThread{
  //  log class

  val MySQLConfigFile: String = "./src/test/resources/MySQLConnConfig.json"
  val MySQLInfo: MySQLConnInfo = MySQLConnInfoJsonUtil.getInfo(MySQLConfigFile)
  val MSSQLConfigFile: String = "./src/test/resources/MSSQLConnConfig.json"
  val MSSQLInfo: MSSQLConnInfo = MSSQLConnInfoJsonUtil.getInfo(MSSQLConfigFile)


  def main(args: Array[String]) {

    val linkedQueue: LinkedBlockingQueue[Runnable] = new LinkedBlockingQueue[Runnable]
    val executor: ThreadPoolExecutor = new ThreadPoolExecutor(60, 60, 7, TimeUnit.DAYS, linkedQueue)// All threads wait for 2 days max

    val databasesConnString: util.ArrayList[String] = new util.ArrayList[String]
//    for(i <- 7 to 9) {
//      databasesConnString.add("jdbc:sqlserver://172.19.17.186:1433;databaseName=GTA_TFL2_TAQ_20140" + i + ";user=sa;password=sa;")
//    }
//    for(i <- 10 to 12) {
//      databasesConnString.add("jdbc:sqlserver://172.19.17.186:1433;databaseName=GTA_TFL2_TAQ_2014" + i + ";user=sa;password=sa;")
//    }
//    for(i <- 1 to 7) {
//      databasesConnString.add("jdbc:sqlserver://172.19.17.186:1433;databaseName=GTA_TFL2_TAQ_20150" + i + ";user=sa;password=sa;")
//    }
    databasesConnString.add(MSSQLInfo.getConnString)

    for(i <-0 to  databasesConnString.size()-1 ) {
      TFL2MSSQLParser.databaseParser(executor, databasesConnString.get(i), MySQLInfo.getConnStringWithoutAccount, MySQLInfo.getMysqlUser, MySQLInfo.getMysqlUser, MySQLInfo.getDataSetLimit)
    }
    executor.shutdown()
    executor.awaitTermination(7, TimeUnit.DAYS)
    print("Finish!\r\n")
  }


//  def main(args: Array[String]): Unit = {
//    val tfL2Parser = new TFL2Parser("TFL2_TAQ_T1509_201503", 2)
//    tfL2Parser.run()
//  }

}
