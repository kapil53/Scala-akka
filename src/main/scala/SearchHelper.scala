import java.io.File

import scala.io.Source.fromFile

trait SearchHelper {
  var mapOfFileVsLines = scala.collection.mutable.Map[File, List[String]]()

  def readFileContents(fileName: File): List[String] = {
    fromFile(fileName).getLines.toList
  }

  def prepareInMemoryMap(files: List[File]) = {
    files.foreach(file => mapOfFileVsLines += (file -> readFileContents(file)))
    mapOfFileVsLines //Returning mapOfFileVsLines , just for unit testing purpose and could be removed
  }

  def getListOfFiles(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      Nil
    }
  }

  def rankAsPerWordsMatched(words: List[String], contentsAsWordsList: List[String]): Double = {
    val matchedWordsCount = words.count(contentsAsWordsList.contains)
    (matchedWordsCount * 100) /words.size
  }
}

object SearchHelper extends SearchHelper
