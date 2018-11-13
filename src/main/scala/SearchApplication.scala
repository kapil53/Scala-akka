
import akka.actor._

object SearchApplication extends App {
  val system = ActorSystem("SearchApplication")
  val argsHandler = system.actorOf(Props[ArgsHandler], name = "argsHandler")
  val searchEngine = system.actorOf(Props(new SearchEngine(argsHandler)), name = "searchEngine")
  //start the search engine
  searchEngine ! StartMessage(args(0))
}