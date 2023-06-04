package com.lfs.parser.generator

import org.scalatest.flatspec.AnyFlatSpec
import com.lfs.parser.config.Column
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class FixedWidthFileGeneratorTest extends AnyFlatSpec {

  "FixedWidthFileGenerator" should "generate 1 line correctly with headers" in {
    val columns = List(Column("a", 2), Column("b", 3), Column("c", 4))
    val linesGenerated = FixedWidthFileGenerator.generate(columns, 1)
    linesGenerated.size shouldBe 2
    linesGenerated(0) shouldBe "a b  c   "
    linesGenerated(1) shouldBe "X XX XXX "
  }

  it should "generate 2 lines correctly with headers" in {
    val columns = List(Column("a", 2), Column("b", 3), Column("c", 4))
    val linesGenerated = FixedWidthFileGenerator.generate(columns, 2)
    linesGenerated.size shouldBe 3
    linesGenerated(0) shouldBe "a b  c   "
    linesGenerated(1) shouldBe "X XX XXX "
    linesGenerated(2) shouldBe "X XX XXX "
  }

  it should "generate column names greater than fixed width" in {
    val columns = List(Column("aaaaa", 2), Column("b", 3), Column("c", 4))
    val linesGenerated = FixedWidthFileGenerator.generate(columns, 1)
    linesGenerated.size shouldBe 2
    linesGenerated(0) shouldBe "aaaaa b  c   "
    linesGenerated(1) shouldBe "X     XX XXX "
  }

  it should "generate column names same as column width" in {
    val columns = List(Column("aa", 2), Column("bb", 2), Column("ccc", 3))
    val linesGenerated = FixedWidthFileGenerator.generate(columns, 1)
    linesGenerated.size shouldBe 2
    linesGenerated(0) shouldBe "aa bb ccc "
    linesGenerated(1) shouldBe "X  X  XX  "
  }

}
