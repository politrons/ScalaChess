package com.politrons.engine

import com.politrons.model.ChessDomain.{Piece, Movement}

/**
 * Rule Engine Class responsible for all the Chess Pieces movement rules.
 */
case class ChessEngine() {


  def isValidateMove(piece: Piece, movement: Movement): Boolean = {
    piece.name.toLowerCase match {
      case "rook" => rookRule(movement)
      case "knight" => knightRule(movement)
      case _ => false
    }
  }

  private def rookRule(movement: Movement): Boolean = {
    (movement.rowFrom > movement.rowTo &&
      movement.columnFrom == movement.columnTo) ||
      (movement.rowFrom < movement.rowTo &&
        movement.columnFrom == movement.columnTo) ||
      (movement.rowFrom == movement.rowTo &&
        movement.columnFrom > movement.columnTo) ||
      (movement.rowFrom == movement.rowTo &&
        movement.columnFrom < movement.columnTo)
  }

  private def knightRule(movement: Movement): Boolean = {
    val horizontal = Math.abs(movement.rowFrom - movement.rowTo)
    val vertical = Math.abs(movement.columnFrom - movement.columnTo)

    horizontal == 2 && vertical == 1 ||
      horizontal == 1 && vertical == 2
  }

}
