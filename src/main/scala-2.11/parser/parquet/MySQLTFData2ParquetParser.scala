package parser.parquet

import java.io._
import java.sql.{ResultSet, SQLException}

import model.TFDataAsList
import mysql.MySQLUtil
import org.apache.hadoop.fs.Path
import org.apache.parquet.schema.{MessageType, MessageTypeParser}
import parser.CloseUtils
import writesupport.MysqlParquetWriter

/**
 * Created by root on 15-9-25.
 */
class MySQLTFData2ParquetParser(OutputPath: String, SchemaFilePath: String, MySQLConnString: String, user: String, pwd: String, tableName: String, MysqlPrimaryColumn: String, limit: Int) extends  Runnable{

  @throws(classOf[IOException])
  private def readSchemaFile(path: String): String = {
    val file: File = new File(path)
    if(file.isDirectory) {
      throw new FileNotFoundException("Is a directory")
    }
    if(!file.exists()) {
      throw new FileNotFoundException("Cannot find schema file")
    }
    val reader: BufferedReader = new BufferedReader(new FileReader(path))
    val stringBuilder: StringBuilder = new StringBuilder
    try {
      var line: String = null
      val ls: String = System.getProperty("line.separator")
      while ((({
        line = reader.readLine; line
      })) != null) {
        stringBuilder.append(line)
        stringBuilder.append(ls)
      }
    } finally {
      CloseUtils.closeQuietly(reader)
    }
    return stringBuilder.toString
  }

  @throws(classOf[IOException])
  def getSchema(schemaFile: File): String = {
    return readSchemaFile(schemaFile.getAbsolutePath)
  }



  @throws(classOf[IOException])
  def convertSQLToParquet(schemaFile: File, outputParquetFile: File, enableDictionary: Boolean) {
    //    LOG.info("Converting " + schemaFile.getName + " to " + outputParquetFile.getName)

    val rawSchema: String = getSchema(schemaFile)
    if (outputParquetFile.exists) {
      throw new IOException("Output file " + outputParquetFile.getAbsolutePath + " already exists")
    }
    val path: Path = new Path(outputParquetFile.toURI)
    val schema: MessageType = MessageTypeParser.parseMessageType(rawSchema)
    val writer: MysqlParquetWriter = new MysqlParquetWriter(path, schema, enableDictionary)
    // Repeat get data set from MySQL, data set size defined in json file
    val RowCount = MySQLUtil.getRowCount(MySQLConnString, user, pwd, tableName);
    if(RowCount <= 0) {
      throw new IOException("Table " + tableName + " is empty.")
    }
    if(RowCount > limit) {
      var parsedCount = 0
      while (parsedCount <= RowCount) {
        // Get data set limited by LIMIT
        // Then write into parquet file
        val rs: ResultSet = MySQLUtil.getTable(
          MySQLConnString,
          user,
          pwd,
          tableName,
          MysqlPrimaryColumn,
          parsedCount,
          limit)
        //    var lineNumber: Int = 0
        try {
          while ((rs.next)) {
            writer.write(TFDataAsList.tableList(rs))
            //        lineNumber += 1
          }
          parsedCount += limit
        }
        catch {
          case e: SQLException => {
            e.printStackTrace
          }
        } finally {
          //      LOG.info("Number of lines: " + lineNumber)
        }
      }
      writer.close
    } else {

      val rs: ResultSet = MySQLUtil.getTable(
        MySQLConnString,
        user,
        pwd,
        tableName,
        MysqlPrimaryColumn)
      val line: String = null
      //    var lineNumber: Int = 0
      try {
        while ((rs.next)) {
          writer.write(TFDataAsList.tableList(rs))
          //        lineNumber += 1
        }
        writer.close
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


  @throws(classOf[IOException])
  override def run(): Unit = {
    val schemaFile: File = new File(SchemaFilePath)
    val outputParquetFile: File = new File(OutputPath + tableName + ".parquet")
    convertSQLToParquet(schemaFile, outputParquetFile, false)
  }


}
