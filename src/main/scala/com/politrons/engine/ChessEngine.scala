package com.politrons.engine

import com.politrons.model.ChessDomain.{Piece, Movement}

/**
 * Rule Engine Class responsible for all the Chess Pieces movement rules.
 */
case class ChessEngine() {


  def isValidMove(piece: Piece, movement: Movement): Boolean = {
    isValidNextMove(movement) && isValidPieceMove(piece, movement)
  }

  private def isValidPieceMove(piece: Piece, movement: Movement) = {
    piece.name.toLowerCase match {
      case "rook" => rookRule(movement)
      case "knight" => knightRule(movement)
      case "king" => kingRule(movement)
      case "bishop" => bishopRule(movement)
      case "queen" => queenRule(movement)
      case _ => false
    }
  }

  private def isValidNextMove(movement: Movement) = {
    (movement.columnTo >= 0 || movement.columnTo <= 7) || (movement.rowTo >= 0 || movement.rowTo <= 7)
  }

  /**
   * ------------
   * PIECE RULES
   * ------------
   */
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

    (horizontal == 2 && vertical == 1) ||
      (horizontal == 1 && vertical == 2)
  }

  private def kingRule(movement: Movement): Boolean = {
    val horizontal = Math.abs(movement.rowFrom - movement.rowTo)
    val vertical = Math.abs(movement.columnFrom - movement.columnTo)
    (horizontal <= 1 && vertical <= 1) &&
      ((horizontal == 1 || vertical == 1) ||
        (horizontal == 0 || vertical == 1) ||
        (horizontal == 1 || vertical == 0))
  }

  private def bishopRule(movement: Movement): Boolean = {
    val horizontal = Math.abs(movement.rowFrom - movement.rowTo)
    val vertical = Math.abs(movement.columnFrom - movement.columnTo)
    (horizontal > 0 && vertical > 0) && (horizontal == vertical)
  }

  private def queenRule(movement: Movement): Boolean = {
    bishopRule(movement) || rookRule(movement)
  }

}
