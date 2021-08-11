package com.politrons.model

import com.politrons.engine.PieceEngine
import com.politrons.model.ChessDomain.Player

import scala.util.Try

case class Piece(name: String,
                 player: Player,
                 engine: PieceEngine) {
  def valid(movement: ChessDomain.Movement): Try[Unit] = engine.isValid(movement)
}
