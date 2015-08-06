package parser

import java.io.{File, IOException}

/**
 * Created by root on 15-7-31.
 */
object TestParser {
  def main(args: Array[String]) {
    val schema: File = new File("/root/IdeaProjects/rdb-parquet-parser/src/test/resources/parquet.schema")
    val output: File = new File("/root/IdeaProjects/rdb-parquet-parser/src/test/resources/output.parquet")
    try {
      MysqlConvertUtils.convertSQLToParquet(schema, output)
    }
    catch {
      case e: IOException => {
        e.printStackTrace
      }
    }
  }
}
