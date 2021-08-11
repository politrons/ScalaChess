package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * Bishop Rule Engine Class responsible for all piece rules.
 */
case class BishopEngine() extends PieceEngine {

  override def valid(movement: Movement): Try[Boolean] = {
    Try {
      isValidNextMove(movement) &&
        bishopMovementRule(movement) &&
        bishopPathRule(movement)
    }
  }

  /**
   * Rule: Validate that the delta of the movement is higher than 0 for horizontal and vertical movement,
   * and the length of both movements are the same, in order to be consider a diagonal
   */
  private def bishopMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal > 0 && vertical > 0) && (horizontal == vertical)
  }


  /**
   * Rule: Check if all previous diagonals movements from start to end has any piece
   */
  private def bishopPathRule(movement: Movement): Boolean = {
    (movement.rowFrom.value + 1 until movement.rowTo.value).count(i => {
        ChessBoard.board(i)(i).isDefined
    }) == 0
  }

}
