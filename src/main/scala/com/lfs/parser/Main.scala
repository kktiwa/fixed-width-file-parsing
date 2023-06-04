package com.lfs.parser

import com.lfs.parser.config.FileSpecConfigLoader
import com.lfs.parser.csv.CsvParser
import com.lfs.parser.generator.FixedWidthFileGenerator
import com.lfs.parser.utils.Utils.{readFileAsList, writeFile}

object Main extends App {

  private val targetFilePath = "/Users/kunaltiwary/projects/fixed-width-file-parsing/src/main/resources"
  private val specFileName = "spec.json"
  private val fixedWidthFileName = "fixed-width.txt"
  private val csvOutputFileName = "output.csv"

  //Read the `spec.json` file and parse as config
  FileSpecConfigLoader.load(targetFilePath, specFileName) match {
    //TODO: Handle errors and return detailed message about the failure
    case Left(error) => error
    case Right(specConfig) =>
      //Generate fixed-width file
      val generatedLines = FixedWidthFileGenerator.generate(specConfig.columns)
      //Write content to a fixed-width target file
      writeFile(targetFilePath, fixedWidthFileName, generatedLines, specConfig.inputEncoding)
      //Read back the fixed-width file
      val fixedWidthContents = readFileAsList(targetFilePath, fixedWidthFileName, specConfig.inputEncoding)
      //Parse `fixedWidthContents` using the spec config
      val csvContents = CsvParser.parse(specConfig.columns, fixedWidthContents)
      //Write to an output CSV file
      writeFile(targetFilePath, csvOutputFileName, csvContents, specConfig.outputEncoding)
  }

}