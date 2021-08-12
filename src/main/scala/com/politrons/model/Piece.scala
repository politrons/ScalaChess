package com.politrons.model

import com.politrons.engine.PieceEngine
import com.politrons.model.ChessDomain.{Movement, Player}

import scala.util.Try

case class Piece(name: String,
                 player: Player,
                 engine: PieceEngine) {
  def isValid(movement: Movement): Try[Unit] = engine.isValid(movement)

  def isCheck(movement: Movement): Try[Boolean] = engine.isCheck(movement)

}
