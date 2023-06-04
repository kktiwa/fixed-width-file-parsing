package com.lfs.parser.csv

import com.lfs.parser.config.Column
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class CsvParserTest extends AnyFlatSpec {

  "CsvParser" should "parse valid lines correctly" in {
    val columns = List(Column("a", 2), Column("b", 3), Column("c", 4))
    val lines = List(
      "a b  c   ",
      "X XX XXX ",
      "X XX XXX "
    )
    val csvLines = CsvParser.parse(lines, columns)
    csvLines(0) shouldBe "a,b,c"
    csvLines(1) shouldBe "X,XX,XXX"
    csvLines(2) shouldBe "X,XX,XXX"
  }

}
