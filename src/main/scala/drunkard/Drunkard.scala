package drunkard

case class Hands(
  hand1: List[Card],
  hand2: List[Card]
)

case class GameState(
  hands: Hands,
  contested: Set[Card] = Set.empty,
  seen: Set[Hands] = Set.empty,
  step: Int = 0
) {
  override def toString: String =
    s"${step}: ${hands.hand1.mkString(" ")} | ${hands.hand2.mkString(" ")} (${contested.mkString(" ")}) "
}

case class Drunkard(shuffle: List[Card]) {

  def deal: Hands = {
    val (hand1List, hand2List) = shuffle
      .zipWithIndex
      .partition(_._2 % 2 == 0)
    Hands(hand1List.map(_._1), hand2List.map(_._1))
  }

  def play(state: GameState, logging: Boolean): (String, GameState) = {
    if (state.hands.hand1.isEmpty) (s"Hand2 won at step ${ state.step }", state)
    else if (state.hands.hand2.isEmpty) (s"Hand1 won at step ${ state.step }", state)
    else if (state.seen.contains(state.hands)) (s"Hands repeated, game resolved as a tie", state)
    else {
      val seen = state.seen + state.hands
      val nextStep = state.step + 1
      val (hand1, hand2) = (state.hands.hand1, state.hands.hand2)
      val (topCard1, topCard2) = (hand1.head, hand2.head)
      if (logging) println(state)

      play(
        if (topCard1 < topCard2) {
          GameState(
            hands = Hands(
              hand1 = hand1.tail,
              hand2 = hand2.tail ++ List(topCard2, topCard1) ++ state.contested,
            ),
            contested = Set.empty,
            seen = seen,
            step = nextStep
          )
        } else if (topCard1 > topCard2) {
          GameState(
            hands = Hands(
              hand1 = hand1.tail ++ List(topCard1, topCard2) ++ state.contested,
              hand2 = hand2.tail
            ),
            contested = Set.empty,
            seen = seen,
            step = nextStep
          )
        } else {
          GameState(
            hands = Hands(
              hand1 = hand1.tail,
              hand2 = hand2.tail
            ),
            contested = (state.contested + topCard1) + topCard2,
            seen = seen,
            step = nextStep
          )
        }
      , logging)
    }
  }

  def resolve(logging: Boolean): (String, GameState) = play(logging = logging, state = GameState(deal))

}

