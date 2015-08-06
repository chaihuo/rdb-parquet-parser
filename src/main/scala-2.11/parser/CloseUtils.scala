package parser

import java.io._

/**
 * Created by root on 15-7-30.
 */
object CloseUtils {
//  private val LOG: Log = Log.getLog(classOf[Utils])

  def closeQuietly(res: Closeable) {
    try {
      if (res != null) {
        res.close
      }
    }
    catch {
      case ioe: IOException => {
//        LOG.warn("Exception closing reader " + res + ": " + ioe.getMessage)
      }
    }
  }
}
