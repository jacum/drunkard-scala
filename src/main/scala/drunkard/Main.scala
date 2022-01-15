package drunkard

object Main extends App {
  val game = Drunkard(Shuffle.shuffle)
  println(game.resolve(logging = true)._1)
}
