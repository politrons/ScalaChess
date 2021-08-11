package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * Knight Rule Engine Class responsible for all piece rules.
 */
case class KnightEngine() extends PieceEngine{

  override def valid(movement: Movement): Try[Boolean] = {
    Try {
      isValidNextMove(movement) && knightMovementRule(movement)
    }
  }

  private def knightMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)

    (horizontal == 2 && vertical == 1) ||
      (horizontal == 1 && vertical == 2)
  }

}
