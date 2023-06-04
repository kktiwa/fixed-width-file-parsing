package com.lfs.parser.config

import org.scalatest.EitherValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import java.nio.charset.Charset


class FileSpecConfigLoaderTest extends AnyFlatSpec with EitherValues {

  "FileSpecConfigLoader" should "parse a valid file spec correctly" in {
    val fileName = "test-spec.json"
    val filePath = "/Users/kunaltiwary/projects/fixed-width-file-parsing/src/test/resources"
    val result = FileSpecConfigLoader.load(path = filePath, fileName = fileName)
    val expected = FileSpecConfig(
      columns = List(
        Column(name = "a", width = 2),
        Column(name = "b", width = 3),
        Column(name = "c", width = 4)
      ),
      inputEncoding = Charset.forName("windows-1252"),
      outputEncoding = Charset.forName("utf-8"),
      includeHeader = true
    )
    result.value shouldBe expected
  }

  it should "fail parsing for an invalid file spec" in {
    val fileName = "invalid-spec.json"
    val filePath = "/Users/kunaltiwary/projects/fixed-width-file-parsing/src/test/resources"
    val result = FileSpecConfigLoader.load(path = filePath, fileName = fileName)
    result.left.value.toString shouldBe "DecodingFailure at .ColumnNames: Missing required field"
  }

}
