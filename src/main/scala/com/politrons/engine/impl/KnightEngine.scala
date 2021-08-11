package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * Knight Rule Engine Class responsible for all piece rules.
 */
case class KnightEngine() extends PieceEngine {

  override def valid(movement: Movement): Try[Boolean] = {
    Try {
      isValidNextMove(movement) && knightMovementRule(movement)
    }
  }

  /**
   * Rule:Valid that the horizontal and vertical always do an L,
   * checking if the length of movement is always 2-1 or 1-2
   */
  private def knightMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal == 2 && vertical == 1) ||
      (horizontal == 1 && vertical == 2)
  }

}
