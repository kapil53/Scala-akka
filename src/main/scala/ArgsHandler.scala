import akka.actor.Actor

class ArgsHandler extends Actor {
  def receive = {
    case ReadArgs =>
      println("Enter words to be searched or quit to stop")
      val input = scala.io.StdIn.readLine()
      sender ! SearchIn(input)
    case StopMessage =>
      println("Search Engine Stopped")
      context.stop(self)
      context.system terminate
  }
}