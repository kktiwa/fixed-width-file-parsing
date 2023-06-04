package com.lfs.parser.generator

import com.lfs.parser.config.Column

object FixedWidthFileGenerator {

  def generate(columns: List[Column], numberOfLines: Int = 10): List[String] =
    generateColumnHeading(columns) +:
      (1 to numberOfLines)
        .toList
        .map(_ => generateLine(columns))

  private def generateLine(columns: List[Column]): String =
    columns
      .map(
        column => generateColumnValue(column)
      ).mkString

  /**
   * @param columns
   * @return
   */
  private def generateColumnHeading(columns: List[Column]): String =
    columns
      .map(
        column => addPadding(column.name, column)
      ).mkString

  /**
   * Add padding to ensure keeping the column width fixed
   *
   * @param content
   * @param column
   * @return
   */
  private def addPadding(content: String, column: Column): String = {
    //Column names can be longer than their width but not their content values
    val width = Math.max(column.width, column.name.length + 1)
    //Return the given string concatenated `n` times.
    content + " " * (width - content.length)
  }

  private def generateColumnValue(column: Column): String = {
    //Return the given string concatenated `n` times.
    val content = "X" * (column.width - 1)
    addPadding(content, column)
  }

}
