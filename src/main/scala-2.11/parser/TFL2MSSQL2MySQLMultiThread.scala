package parser

import java.io._
import java.util
import java.util.concurrent.{LinkedBlockingQueue, ArrayBlockingQueue, ThreadPoolExecutor, TimeUnit}

import mssql.MSSQLUtil
import parser.TFL2Parser

/**
 * Created by root on 15-7-30.
 */
object TFL2MSSQL2MySQLMultiThread{
  //  log class

//  val MSSQLConnString : String = "jdbc:sqlserver://172.19.17.186:1433;databaseName=GTA_TFL2_TAQ_201407;user=sa;password=sa;"

  val MySQLConnString: String = "jdbc:mysql://qkdata1.cltv2qruve9e.rds.cn-north-1.amazonaws.com.cn:3306/HF_Future?useUnicode=true&characterEncoding=utf8"
  val MySQLuser: String = "hf_app"
  val MySQLkey: String = "hf_app%2015"
  val limit: Int = 1000
//  val targetNationalDebtTableName: String = ""

  def main(args: Array[String]) {

    val linkedQueue: LinkedBlockingQueue[Runnable] = new LinkedBlockingQueue[Runnable]
    val executor: ThreadPoolExecutor = new ThreadPoolExecutor(60, 60, 7, TimeUnit.DAYS, linkedQueue)// All threads wait for 2 days max

    // TODO: these conn string should move to json configuration file
    val databasesConnString: util.ArrayList[String] = new util.ArrayList[String]
    for(i <- 7 to 9) {
      databasesConnString.add("jdbc:sqlserver://172.19.17.186:1433;databaseName=GTA_TFL2_TAQ_20140" + i + ";user=sa;password=sa;")
    }
    for(i <- 10 to 12) {
      databasesConnString.add("jdbc:sqlserver://172.19.17.186:1433;databaseName=GTA_TFL2_TAQ_2014" + i + ";user=sa;password=sa;")
    }
    for(i <- 1 to 7) {
      databasesConnString.add("jdbc:sqlserver://172.19.17.186:1433;databaseName=GTA_TFL2_TAQ_20150" + i + ";user=sa;password=sa;")
    }

    for(i <-0 to  databasesConnString.size()-1 ) {
      TFL2MSSQLParser.databaseParser(executor, databasesConnString.get(i), MySQLConnString, MySQLuser, MySQLkey, limit)
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
