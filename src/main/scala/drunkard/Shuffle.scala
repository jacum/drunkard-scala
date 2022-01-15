package drunkard

import scala.util.Random

object Shuffle {

  private val allCards: List[Card] =
    for (
      rank <- List(Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace);
      suite <- List(Spades, Hearts, Diamonds, Clubs)
    ) yield rank of suite

  def shuffle: List[Card] = Random.shuffle(allCards)

}
