package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * Rule Engine Class responsible for all the Chess Pieces movement rules.
 */
case class PawnEngine() extends PieceEngine {

  override def valid(movement: Movement): Try[Boolean] = {
    Try {
      isValidNextMove(movement) &&
        pawnMovementRule(movement) &&
        pawnPathRule(movement)
    }
  }

  private def pawnMovementRule(movement: Movement): Boolean = {
    val (vertical: Int, horizontal: Int) = diffMovements(movement)
    (horizontal == 0 && vertical == 1) ||
      (horizontal == 0 && vertical == 2) && (movement.number <= 2)
  }

  /**
   * Rule: There's no pieces during the path of the pawn until reach the spot.
   */
  private def pawnPathRule(movement: Movement): Boolean = {
    (movement.rowFrom.value + 1 to movement.rowTo.value).count(row => {
      ChessBoard.board(row)(movement.columnTo.value).isDefined
    }) == 0
  }

}
