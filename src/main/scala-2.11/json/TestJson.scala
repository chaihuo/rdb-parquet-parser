package json


/**
 * Created by root on 15-8-4.
 */
object TestJson {
  def main(args: Array[String]) {
    val pi: ParserInfo = JsonUtil.getParserInfo()
    System.out.println("Host: " + pi.getMysqlHost)
  }
}
