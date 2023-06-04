package com.lfs.parser.csv

import com.lfs.parser.config.Column

object CsvParser {

  /**
   * Parse a given list of lines using the column constraints passed
   *
   * @param lines
   * @param columns
   * @return
   */
  def parse(lines: List[String], columns: List[Column]): Seq[String] = {
    val columnWidths = columns.map(column => Math.max(column.width, column.name.length + 1))
    //E.g: List(List(0, 2), List(2, 5), List(5, 9))
    val offsetStartEndPairs = columnWidths.scanLeft(0)(_ + _).sliding(2).toList
    lines.map(line => parseLine(line, offsetStartEndPairs))
  }

  private def parseLine(line: String, offsets: List[List[Int]]): String = {
    offsets.map {
      //for each line take the start, end substring
      case List(start, end) => line.substring(start, end).trim
    }.mkString(",")
  }

}
