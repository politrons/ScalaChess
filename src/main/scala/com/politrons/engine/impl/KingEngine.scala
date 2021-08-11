package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.engine.impl.PathRules.{diagonalPathRule, horizontalOrVerticalPathRule}
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * King Rule Engine Class responsible for all piece rules.
 */
case class KingEngine() extends PieceEngine {

  override def valid(movement: Movement): Try[Boolean] = {
    Try {
      isValidNextMove(movement) &&
        kingMovementRule(movement) &&
        kingPathRule(movement)
    }
  }

  /**
   * Rule:Valid that the movement is just one move in all possible directions
   */
  private def kingMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal <= 1 && vertical <= 1) &&
      ((horizontal == 1 || vertical == 1) ||
        (horizontal == 0 || vertical == 1) ||
        (horizontal == 1 || vertical == 0))
  }

  private def kingPathRule(movement: Movement): Boolean = {
    val (vertical: Int, horizontal) = diffMovements(movement)
    if (vertical == horizontal) {
      diagonalPathRule(movement)
    } else {
      horizontalOrVerticalPathRule(vertical, movement)
    }
  }

}
