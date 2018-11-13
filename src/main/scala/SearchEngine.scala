import akka.actor.{Actor, ActorRef}

class SearchEngine(argsHandler: ActorRef) extends Actor with SearchHelper {
  def receive = {
    case StartMessage(directoryName) =>
      val filesList = getListOfFiles(directoryName)
      prepareInMemoryMap(filesList)
      println(s"${filesList.size} files read in directory $directoryName")
      argsHandler ! ReadArgs

    case SearchIn(inputLine) =>
      if (inputLine == "quit") {
        sender ! StopMessage
        context.stop(self)
      } else {
        val listOfFileAndRanks = mapOfFileVsLines.map {
          fileLines => {
            val rank = rankAsPerWordsMatched(inputLine.split(" ").toList, fileLines._2.flatMap(_.split(" ")))
            (fileLines._1, rank)
          }
        }.toList

        listOfFileAndRanks.sortWith(_._2 > _._2).map {
          (fileRank) => println(s"${fileRank._1} - ${fileRank._2}%")
        }

        sender ! ReadArgs
      }
  }
}
