package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * Rule Engine Class responsible for all the Chess Pieces movement rules.
 */
case class RookEngine() extends PieceEngine {

  override def valid(movement: Movement): Try[Boolean] = {
    Try {
      isValidNextMove(movement) &&
        rookMovementRule(movement) &&
        rookPathRule(movement)
    }
  }

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

  /**
   * Rule: There's no piece during the horizontal or vertical path of the rook
   * from the beginning to the end
   */
  private def rookPathRule(movement: Movement): Boolean = {
    val (vertical: Int, _) = diffMovements(movement)
    if (vertical > 0) {
      (movement.rowFrom.value + 1 until movement.rowTo.value).count(row => {
        ChessBoard.board(row)(movement.columnFrom.value).isDefined
      }) == 0
    } else {
      (movement.columnFrom.value + 1 until movement.columnTo.value).count(column => {
        ChessBoard.board(movement.rowFrom.value)(column).isDefined
      }) == 0
    }
  }

}
