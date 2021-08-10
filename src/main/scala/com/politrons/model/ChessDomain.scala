package com.politrons.model

object ChessDomain {

  case class Piece(name: String)

  case class Movement(player: Player,
                      columnFrom: Int,
                      rowFrom: Int,
                      columnTo: Int,
                      rowTo: Int)

  trait Player

  case class Player1() extends Player

  case class Player2() extends Player

}
