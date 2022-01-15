package drunkard

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DrunkardSpec extends AnyFlatSpec with Matchers {

  "Card ranking" should "respect aces and sixes" in {
    val ace = Ace of Spades
    val six = Six of Hearts
    val seven = Seven of Clubs
    val king = King of Diamonds

    six > ace shouldBe(true)
    ace < six shouldBe(true)
    seven == seven shouldBe(true)
    ace > king shouldBe(true)
    king > seven shouldBe (true)
  }

  "Deal" should "split the shuffle" in {

    val shuffle = Shuffle.shuffle
    val hands = Drunkard(shuffle).deal
    hands.hand1.length shouldEqual hands.hand2.length
  }

  "Same shuffle" should "give the same result" in {

    for (_ <- 1 to 100) {
      val shuffle = Shuffle.shuffle
      val result = Drunkard(shuffle).resolve(logging = false)
      for (_ <- 1 to 100) {
        Drunkard(shuffle).resolve(logging = false) shouldEqual result
      }
    }
  }

  "Many shuffles" should "give some maxsteps value" in {
    var maxSteps = 0
    for (_ <- 1 to 10000) {
        val result = Drunkard(Shuffle.shuffle).resolve(logging = false)
        if (result._2.step > maxSteps) maxSteps = result._2.step
    }
    println(s"Max steps $maxSteps")
  }
}
