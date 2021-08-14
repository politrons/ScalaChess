package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain.Movement
import com.politrons.rules.CheckRules.diagonalCheck
import com.politrons.rules.PathRules.diagonalPathRule

import scala.util.{Failure, Success, Try}

/**
 * Bishop Rule Engine Class responsible for all piece rules.
 */
case class BishopEngine() extends PieceEngine {

  override def isValid(movement: Movement): Try[Unit] = {
    for {
      _ <- if (isValidNextMove(movement)) Success() else Failure(IllegalMovementException(s"Error validating $movement"))
      _ <- if (isValidMovementRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating Bishop $movement"))
      _ <- if (isValidPathRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating Bishop path $movement"))
      _ <- inCheck(movement)
    } yield ()
  }

  /**
   * Rule: Validate that the delta of the movement is higher than 0 for horizontal and vertical movement,
   * and the length of both movements are the same, in order to be consider a diagonal
   */
  private def isValidMovementRule(movement: Movement): Boolean = {
    val (horizontal: Int, vertical: Int) = diffMovements(movement)
    (horizontal > 0 && vertical > 0) && (horizontal == vertical)
  }

  /**
   * Rule: Check if all previous diagonals movements from start to end has any piece
   */
  private def isValidPathRule(movement: Movement): Boolean = {
    diagonalPathRule(movement)
  }

  /**
   * Rule Check: Go in all possible diagonals, and just obtain the first defined piece in the path.
   * If that piece is a King is consider a Check.
   */
  override def isCheck(movement: Movement): Try[Boolean] = {
    diagonalCheck(movement,7,0)
  }


}
