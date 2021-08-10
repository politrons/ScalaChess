package com.politrons.engine

import com.politrons.model.ChessDomain.{Movement, Piece}
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * Rule Engine Class responsible for all the Chess Pieces movement rules.
 */
case class ChessEngine(var chessBoard: ChessBoard) {

  def isValidMovement(piece: Piece,
                      movement: Movement): Try[Boolean] = {
    Try {
      isValidNextMove(movement) &&
        isValidPieceMove(piece, movement)
    }
  }

  private def isValidPieceMove(piece: Piece,
                               movement: Movement) = {
    piece.name.toLowerCase.trim match {
      case "rook" => rookMovementRule(movement)
      case "knight" => knightMovementRule(movement)
      case "king" => kingMovementRule(movement)
      case "bishop" => bishopMovementRule(movement)
      case "queen" => queenMovementRule(movement)
      case "pawn" => pawnMovementRule(movement) && pawnPathRule(movement)
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
  private def rookMovementRule(movement: Movement): Boolean = {
    (movement.rowFrom.value > movement.rowTo.value &&
      movement.columnFrom.value == movement.columnTo.value) ||
      (movement.rowFrom.value < movement.rowTo.value &&
        movement.columnFrom.value == movement.columnTo.value) ||
      (movement.rowFrom.value == movement.rowTo.value &&
        movement.columnFrom.value > movement.columnTo.value) ||
      (movement.rowFrom.value == movement.rowTo.value &&
        movement.columnFrom.value < movement.columnTo.value)
  }

  private def knightMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)

    (horizontal == 2 && vertical == 1) ||
      (horizontal == 1 && vertical == 2)
  }

  private def kingMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal <= 1 && vertical <= 1) &&
      ((horizontal == 1 || vertical == 1) ||
        (horizontal == 0 || vertical == 1) ||
        (horizontal == 1 || vertical == 0))
  }

  private def bishopMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal > 0 && vertical > 0) && (horizontal == vertical)
  }

  private def queenMovementRule(movement: Movement): Boolean = {
    bishopMovementRule(movement) || rookMovementRule(movement)
  }

  private def pawnMovementRule(movement: Movement): Boolean = {
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
