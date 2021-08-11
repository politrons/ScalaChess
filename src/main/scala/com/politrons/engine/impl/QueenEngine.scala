package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * Queen Rule Engine Class responsible for all piece rules.
 */
case class QueenEngine() extends PieceEngine {

  override def valid(movement: Movement): Try[Boolean] = {
    Try {
      isValidNextMove(movement) &&
        queenMovementRule(movement)
    }
  }

  /**
   * Rule:Valid that the movement is in each possible direction.
   */
  private def queenMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    ((horizontal > 0 && vertical > 0) && (horizontal == vertical)) ||
      ((vertical > 0 && horizontal == 0) || (vertical == 0 && horizontal > 0))
  }

}
