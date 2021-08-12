package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.engine.impl.PathRules.{diagonalPathRule, horizontalOrVerticalPathRule}
import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.{Failure, Success, Try}

/**
 * Queen Rule Engine Class responsible for all piece rules.
 */
case class QueenEngine() extends PieceEngine {

  override def isValid(movement: Movement): Try[Unit] = {
    for {
      _ <- if (isValidNextMove(movement)) Success() else Failure(IllegalMovementException(s"Error validating $movement"))
      _ <- if (isValidMovementRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating Queen $movement"))
      _ <- if (isValidPathRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating Queen path $movement"))
    } yield ()
  }

  /**
   * Rule:Valid that the movement is in each possible direction.
   */
  private def isValidMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    ((horizontal > 0 && vertical > 0) && (horizontal == vertical)) ||
      ((vertical > 0 && horizontal == 0) || (vertical == 0 && horizontal > 0))
  }

  private def isValidPathRule(movement: Movement): Boolean = {
    val (vertical: Int, horizontal) = diffMovements(movement)
    if (vertical == horizontal) {
      diagonalPathRule(movement)
    } else {
      horizontalOrVerticalPathRule(vertical, movement)
    }
  }

  override def isCheck(movement: Movement): Try[Boolean] = Success(false)
}
