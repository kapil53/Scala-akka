import java.io.File

import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class SearchHelperTest extends FlatSpec {
  markup{
    """
      |# Search Helper
      |
      |## `readFileContents`
      |It reads contents of a given file and returns a list of lines
      |
      |## `prepareInMemoryMap`
      |It prepares an in memory `Map` representing file(s) and their contents
      |
      |## `getListOfFiles`
      |It gets the list the file(s) from a given directory
      |
      |## `rankAsPerWordsMatched`
      |It generates the rank based on the percentage of words matched in a given file contents
    """.stripMargin
  }

  behavior of "readFileContents"
  it should "read contents of a given file and return list of lines" in {
    val filePath = new File("testing/test1.txt")

    SearchHelper.readFileContents(filePath) should be(List("Hi This is me"))
  }

  behavior of "prepareInMemoryMap"
  it should "prepare an in memory `Map` for representing file(s) and their contents" in {
    val filePath = new File("testing/test1.txt")

    SearchHelper.prepareInMemoryMap(List(filePath)) should be(Map(filePath -> List("Hi This is me")))
  }

  behavior of "getListOfFiles"
  it should "return a list the file(s) from a given directory" in {
    SearchHelper.getListOfFiles("testing") should not be(Nil)
  }

  behavior of "rankAsPerWordsMatched"
  it should "generate the rank based on the percentage of words matched in a given file contents" in {
    SearchHelper.rankAsPerWordsMatched(List("word1", "word2"), List("This", "includes", "word1")) should be(50.0)
  }
}
