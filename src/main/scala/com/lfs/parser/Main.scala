package com.lfs.parser

import com.lfs.parser.config.FileSpecConfigLoader
import com.lfs.parser.csv.CsvParser
import com.lfs.parser.generator.FixedWidthFileGenerator
import com.lfs.parser.utils.Utils.{readFile, readFileAsList, writeFile}

object Main extends App {

  private val specFileName = "spec.json"
  private val specFilePath = "/Users/kunaltiwary/projects/fixed-width-file-parsing/src/main/resources"

  FileSpecConfigLoader.load(specFilePath, specFileName) match {
    case Left(error) => error
    case Right(specConfig) =>
      val generatedLines = FixedWidthFileGenerator.generate(specConfig.columns)
      writeFile(specFilePath, "fixed-width.txt", generatedLines, specConfig.inputEncoding)
      val fwfLines = readFileAsList(specFilePath, "fixed-width.txt", specConfig.inputEncoding)
      val csvLines = CsvParser.parse(specConfig.columns, fwfLines)
      writeFile(specFilePath, "output.csv", csvLines, specConfig.outputEncoding)
  }

}