package parser

import java.io._

/**
 * Created by root on 15-7-30.
 */
object CloseUtils {
//  log class

  def closeQuietly(res: Closeable) {
    try {
      if (res != null) {
        res.close
      }
    }
    catch {
      case ioe: IOException => {
//        LOG
        throw new IOException("Close MySQL connection error")
      }
    }
  }
}
