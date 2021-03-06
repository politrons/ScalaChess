package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain.Movement
import com.politrons.rules.CheckRules.{diagonalCheck, horizontalVerticalCheck}
import com.politrons.rules.PathRules.{diagonalPathRule, horizontalOrVerticalPathRule}

import scala.util.{Failure, Success, Try}

/**
 * King Rule Engine Class responsible for all piece rules.
 */
case class KingEngine() extends PieceEngine {

  override def isValid(movement: Movement): Try[Unit] = {
    for {
      _ <- if (isValidNextMove(movement)) Success() else Failure(IllegalMovementException(s"Error validating $movement"))
      _ <- if (isValidMovementRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating King $movement"))
      _ <- if (isValidPathRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating King path $movement"))
      _ <- inCheckRule(movement)
    } yield ()
  }

  /**
   * Rule:Valid that the movement is just one move in all possible directions
   */
  private def isValidMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal <= 1 && vertical <= 1) &&
      ((horizontal == 1 || vertical == 1) ||
        (horizontal == 0 || vertical == 1) ||
        (horizontal == 1 || vertical == 0))
  }

  private def isValidPathRule(movement: Movement): Boolean = {
    val (vertical: Int, horizontal) = diffMovements(movement)
    if (vertical == horizontal) {
      diagonalPathRule(movement)
    } else {
      horizontalOrVerticalPathRule(vertical, movement)
    }
  }

  override def isCheckRule(movement: Movement): Try[Boolean] = {
    val tryDiagonalCheck = diagonalCheck(movement, movement.rowTo.value + 1, movement.rowTo.value - 1)
    val tryHorizontalVerticalCheck = horizontalVerticalCheck(movement, movement.rowTo.value + 1, movement.rowTo.value - 1, movement.columnTo.value + 1, movement.columnTo.value - 1)
    Try(tryDiagonalCheck.get || tryHorizontalVerticalCheck.get)
  }
}
