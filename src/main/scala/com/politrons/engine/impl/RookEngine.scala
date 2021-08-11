package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.engine.impl.PathRules.horizontalOrVerticalPathRule
import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.{Failure, Success, Try}

/**
 * Rook Rule Engine Class responsible for all piece rules.
 */
case class RookEngine() extends PieceEngine {

  override def isValid(movement: Movement): Try[Unit] = {
    for {
      _ <- if (isValidNextMove(movement)) Success() else Failure(IllegalMovementException(s"Error validating $movement"))
      _ <- if (isValidMovementRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating Rook $movement"))
      _ <- if (isValidPathRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating Rook path $movement"))
    } yield ()
  }

  /**
   * Rule: Valid that the piece only can move horizontal or vertical.
   */
  private def isValidMovementRule(movement: Movement): Boolean = {
    val (vertical: Int, horizontal) = diffMovements(movement)
    (vertical > 0 && horizontal == 0) ||
      (vertical == 0 && horizontal > 0)
  }

  /**
   * Rule: There's no piece during the horizontal or vertical path of the rook
   * from the beginning to the end
   */
  private def isValidPathRule(movement: Movement): Boolean = {
    val (vertical: Int, _) = diffMovements(movement)
    horizontalOrVerticalPathRule(vertical, movement)
  }

}
