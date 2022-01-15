package drunkard

import scala.collection.parallel.CollectionConverters.ImmutableIterableIsParallelizable

object MainPar extends App {
  val shuffles = for (_ <- 1 to 1000000) yield Shuffle.shuffle
  shuffles.par.foreach { shuffle =>
    val game = Drunkard(shuffle).resolve(logging = false)
    val step = game._2.step
    if (step > 1800) {
      println(s"${step}: ${shuffle}")
    }
  }
}
