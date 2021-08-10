package com.politrons.model

object ChessDomain {

  case class Piece(name: String)

  case class Movement(val player: Player,
                      val columFrom: Int,
                      val rowFrom: Int,
                      val columTo: Int,
                      val rowTo: Int)

  enum Player() {
    case Player1 extends Player
    case Player2 extends Player
  }

}
