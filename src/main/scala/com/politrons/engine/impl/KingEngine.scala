package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * King Rule Engine Class responsible for all piece rules.
 */
case class KingEngine() extends PieceEngine{

  override def valid(movement: Movement): Try[Boolean] = {
    Try {
      isValidNextMove(movement) && kingMovementRule(movement)
    }
  }

  private def kingMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal <= 1 && vertical <= 1) &&
      ((horizontal == 1 || vertical == 1) ||
        (horizontal == 0 || vertical == 1) ||
        (horizontal == 1 || vertical == 0))
  }

}
