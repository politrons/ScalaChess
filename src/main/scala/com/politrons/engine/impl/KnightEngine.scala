package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain.{Movement, Player}
import com.politrons.rules.CheckRules.knightCheckRule
import com.politrons.rules.PathRules.{destinationMovementNoPieceOfMine, diagonalPathRule, horizontalOrVerticalPathRule}
import com.politrons.view.ChessBoard

import scala.util.{Failure, Success, Try}

/**
 * Knight Rule Engine Class responsible for all piece rules.
 */
case class KnightEngine() extends PieceEngine {

  override def isValid(movement: Movement): Try[Unit] = {
    for {
      _ <- if (isValidNextMove(movement)) Success() else Failure(IllegalMovementException(s"Error validating $movement"))
      _ <- if (isValidMovementRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating Knight $movement"))
      _ <- if (isValidPathRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating Knight path $movement"))
      _ <- inCheck(movement)
    } yield ()
  }

  /**
   * Rule:Valid that the horizontal and vertical always do an L,
   * checking if the length of movement is always 2-1 or 1-2
   */
  private def isValidMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal == 2 && vertical == 1) ||
      (horizontal == 1 && vertical == 2)
  }

  private def isValidPathRule(movement: Movement): Boolean = {
    destinationMovementNoPieceOfMine(movement)
  }

  /**
   * Check Rule: check if the King is in all possible 8 movements of the current Knight position.
   */
  override def isCheck(movement: Movement): Try[Boolean] = {
    knightCheckRule(movement)
  }

}
