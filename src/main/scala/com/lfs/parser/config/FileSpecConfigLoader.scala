package com.lfs.parser.config

import com.lfs.parser.utils.Utils.readFile
import io.circe
import java.nio.charset.Charset


/**
 * Represents the final data structure that holds the file specification config
 *
 * @param columns
 * @param inputEncoding
 * @param outputEncoding
 * @param includeHeader
 */
case class FileSpecConfig(columns: List[Column],
                          //FixedWidthEncoding
                          inputEncoding: Charset,
                          //DelimitedEncoding
                          outputEncoding: Charset,
                          includeHeader: Boolean
                         )

/**
 * Represents a column name and it's fixed offset
 *
 * @param name
 * @param width
 */
case class Column(name: String,
                  width: Int
                 )

/**
 * Represents the data structure after parsing the JSON file `spec.json`
 *
 * @param ColumnNames
 * @param Offsets
 * @param FixedWidthEncoding
 * @param IncludeHeader
 * @param DelimitedEncoding
 */
case class FileSpecJSON(
                         ColumnNames: List[String],
                         Offsets: List[String],
                         FixedWidthEncoding: String,
                         IncludeHeader: String,
                         DelimitedEncoding: String
                       )

object FileSpecConfigLoader {

  import io.circe.Decoder
  import io.circe.generic.semiauto.deriveDecoder
  import io.circe.parser._

  implicit val fileSpecJSON: Decoder[FileSpecJSON] = deriveDecoder[FileSpecJSON]

  def load(path: String, fileName: String): Either[circe.Error, FileSpecConfig] = {
    val jsonAsString = readFile(path, fileName)
    for {
      json <- parse(jsonAsString)
      fileSpecJSON <- json.as[FileSpecJSON]
    } yield buildFileSpecConfig(fileSpecJSON)
  }

  private def buildFileSpecConfig(fileSpecJSON: FileSpecJSON): FileSpecConfig = {
    val columns = fileSpecJSON.ColumnNames.zip(fileSpecJSON.Offsets).map {
      case (name, width) => Column(name, width.toInt)
    }
    FileSpecConfig(
      columns = columns,
      inputEncoding = Charset.forName(fileSpecJSON.FixedWidthEncoding),
      outputEncoding = Charset.forName(fileSpecJSON.DelimitedEncoding),
      includeHeader = fileSpecJSON.IncludeHeader.toBoolean
    )
  }
}