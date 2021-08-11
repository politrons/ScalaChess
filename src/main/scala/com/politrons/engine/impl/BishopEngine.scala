package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * Bishop Rule Engine Class responsible for all piece rules.
 */
case class BishopEngine() extends PieceEngine{

  override def valid(movement: Movement): Try[Boolean] = {
    Try {
      isValidNextMove(movement) && bishopMovementRule(movement)
    }
  }

  private def bishopMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal > 0 && vertical > 0) && (horizontal == vertical)
  }

}
