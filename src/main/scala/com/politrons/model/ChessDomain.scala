package com.politrons.model

object ChessDomain {

  case class Piece(name: String)


  case class ColumnFrom(value: Int)

  case class ColumnTo(value: Int)

  case class RowFrom(value: Int)

  case class RowTo(value: Int)

  case class Movement(player: Player,
                      number: Int,
                      columnFrom: ColumnFrom,
                      rowFrom: RowFrom,
                      columnTo: ColumnTo,
                      rowTo: RowTo)

  trait Player 
  case class Player1() extends Player

  case class Player2() extends Player

}
