import java.io.{IOException, FileNotFoundException, File}
import java.text.SimpleDateFormat
import java.util.Date

import json.{ParquetInfoJsonUtil, ParserInfo}
import mysql.MySQLUtil
import parser.MysqlConvertUtils

/**
  * Created by root on 15-8-14.
  */
object TestMySQLConvertUtil extends org.specs2.mutable.Specification {
   "This is parser.MysqlConvertUtils test." +
     "\r\n" +
     "Input arguments test are covered by TestMySQLUtil and TestParquetInfoJsonUtil." >> {
     "Test normal running when output file exists" >> {
       val pi: ParserInfo = ParquetInfoJsonUtil.getInfo()
       val schemaFile: File = new File(pi.getSchemaFilePath)
       val outputParquetFile: File = new File("/root/IdeaProjects/rdb-parquet-parser/src/test/resources/ImproperTestFiles/output_exist.parquet")
       MysqlConvertUtils.convertSQLToParquet(schemaFile, outputParquetFile, false) must throwA(new IOException("Output file " + outputParquetFile.getAbsolutePath + " already exists"))
     }
     "Test normal running when schema file does not exist" >> {
       val pi: ParserInfo = ParquetInfoJsonUtil.getInfo()
       val schemaFileNotExist: File = new File("aaa")
       val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
       val outputParquetFile: File = new File(pi.getOutputPath + df.format(new Date))
       MysqlConvertUtils.convertSQLToParquet(schemaFileNotExist, outputParquetFile, false) must throwA(new FileNotFoundException("Cannot find schema file"))
       val schemaFile: File = new File("")
       MysqlConvertUtils.convertSQLToParquet(schemaFile, outputParquetFile, false) must throwA(new FileNotFoundException("Is a directory"))
     }
   }



 }
