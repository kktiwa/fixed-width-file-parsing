package com.lfs.parser.utils

import java.nio.charset.Charset
import java.nio.file.{Files, Paths}
import scala.collection.JavaConverters._
import scala.io.Source

object Utils {

  def readFile(filePath: String,
               fileName: String,
               encoding: Charset = Charset.forName("utf-8")
              ): String = {
    val source = Source.fromFile(s"$filePath/$fileName", encoding.name())
    val fileContent = try source.mkString finally source.close()
    fileContent
  }

  def readFileAsList(filePath: String,
                     fileName: String,
                     encoding: Charset = Charset.forName("utf-8")
                    ): List[String] = {
    val source = Source.fromFile(s"$filePath/$fileName", encoding.name())
    val fileContent = try source.getLines().toList finally source.close()
    fileContent
  }

  def writeFile(filePath: String,
                fileName: String,
                lines: Seq[String],
                encoding: Charset
               ): Unit =
    Files.write(
      Paths.get(s"$filePath/$fileName"), lines.asJava, encoding
    )

}
