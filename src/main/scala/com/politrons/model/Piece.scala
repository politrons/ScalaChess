package com.politrons.model

import com.politrons.engine.PieceEngine

import scala.util.Try

case class Piece(name: String,
                 engine: PieceEngine) {
  def valid(movement: ChessDomain.Movement): Try[Boolean] = engine.valid(movement)
}
