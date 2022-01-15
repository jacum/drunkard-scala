package object drunkard {


  sealed abstract case class Suite(value: Char)
  object Clubs extends Suite('♣')
  object Diamonds extends Suite('♦')
  object Hearts extends Suite('♥')
  object Spades extends Suite('♠')

  sealed abstract case class Rank(value: Char, sortingValue: Int) extends Ordered[Rank] {
    override def compare(that: Rank): Int = {
      def defaultComparison: Int = sortingValue.compare(that.sortingValue)
      this match {
        case Ace => that match {
          case Six => -1
          case _ => defaultComparison
        }
        case Six => that match {
          case Ace => 1
          case _ => defaultComparison
        }
        case _ => defaultComparison
      }
    }

    def of(suite: Suite): Card = Card(this, suite)
  }

  object Six extends Rank('6', 6)
  object Seven extends Rank('7', 7)
  object Eight extends Rank('8', 8)
  object Nine extends Rank('9', 9)
  object Ten extends Rank('T', 10)
  object Jack extends Rank('J', 11)
  object Queen extends Rank('Q', 12)
  object King extends Rank('K', 13)
  object Ace extends Rank('A', 14)

  final case class Card(rank: Rank, suite: Suite) extends Ordered[Card] {

    def compare(that: Card): Int = this.rank.compare(that.rank)

    override def toString: String = s"${rank.value.toString}${suite.value.toString}"
  }

}
