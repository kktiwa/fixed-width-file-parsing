package com.lfs.parser.csv

import com.lfs.parser.config.Column

object CsvParser {

  def parse(columns: List[Column], lines: List[String]): Seq[String] = {
    val columnWidths = columns.map(column => Math.max(column.width, column.name.length + 1))
    //E.g: List(List(0, 2), List(2, 5), List(5, 9))
    val offsetStartEndPairs = columnWidths.scanLeft(0)(_ + _).sliding(2).toList
    lines.map(line => parseLine(offsetStartEndPairs, line))
  }

  private def parseLine(offsets: List[List[Int]], line: String): String = {
    offsets.map {
      case List(start, end) => line.substring(start, end).trim
    }.mkString(",")
  }

}
