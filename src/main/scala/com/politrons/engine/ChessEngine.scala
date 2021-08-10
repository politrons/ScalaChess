package com.politrons.engine

import com.politrons.model.ChessDomain.{Movement, Piece}
import com.politrons.view.ChessBoard

/**
 * Rule Engine Class responsible for all the Chess Pieces movement rules.
 */
case class ChessEngine(var chessBoard: ChessBoard) {

  def isValidMove(piece: Piece,
                  movement: Movement): Boolean = {
    isValidNextMove(movement) &&
      isValidPieceMove(piece, movement)
  }

  private def isValidPieceMove(piece: Piece,
                               movement: Movement) = {
    piece.name.toLowerCase.trim match {
      case "rook" => rookRule(movement)
      case "knight" => knightRule(movement)
      case "king" => kingRule(movement)
      case "bishop" => bishopRule(movement)
      case "queen" => queenRule(movement)
      case "pawn" => pawnRule(movement) && pawnPathRule(movement)
      case _ => false
    }
  }

  private def isValidNextMove(movement: Movement) = {
    (movement.columnTo.value >= 0 || movement.columnTo.value <= 7) ||
      (movement.rowTo.value >= 0 || movement.rowTo.value <= 7)
  }

  /**
   * ---------------------
   * PIECE MOVEMENT RULES
   * ---------------------
   */
  private def rookRule(movement: Movement): Boolean = {
    (movement.rowFrom.value > movement.rowTo.value &&
      movement.columnFrom.value == movement.columnTo.value) ||
      (movement.rowFrom.value < movement.rowTo.value &&
        movement.columnFrom.value == movement.columnTo.value) ||
      (movement.rowFrom.value == movement.rowTo.value &&
        movement.columnFrom.value > movement.columnTo.value) ||
      (movement.rowFrom.value == movement.rowTo.value &&
        movement.columnFrom.value < movement.columnTo.value)
  }

  private def knightRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)

    (horizontal == 2 && vertical == 1) ||
      (horizontal == 1 && vertical == 2)
  }

  private def kingRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal <= 1 && vertical <= 1) &&
      ((horizontal == 1 || vertical == 1) ||
        (horizontal == 0 || vertical == 1) ||
        (horizontal == 1 || vertical == 0))
  }

  private def bishopRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal > 0 && vertical > 0) && (horizontal == vertical)
  }

  private def queenRule(movement: Movement): Boolean = {
    bishopRule(movement) || rookRule(movement)
  }

  private def pawnRule(movement: Movement): Boolean = {
    val (vertical: Int, horizontal: Int) = diffMovements(movement)
    (horizontal == 0 && vertical == 1) ||
      (horizontal == 0 && vertical == 2) && (movement.number <= 2)
  }

  private def diffMovements(movement: Movement): (Int, Int) = {
    val vertical = Math.abs(movement.rowFrom.value - movement.rowTo.value)
    val horizontal = Math.abs(movement.columnFrom.value - movement.columnTo.value)
    (vertical, horizontal)
  }

  /**
   * ----------------
   * PIECE PATH RULES
   * ----------------
   */

  /**
   * Rule: There's no pieces during the path of the pawn until reach the spot.
   */
  private def pawnPathRule(movement: Movement): Boolean = {
    (movement.rowFrom.value + 1 to movement.rowTo.value).count(row => {
      chessBoard.board(row)(movement.columnTo.value).isDefined
    }) == 0
  }


}
