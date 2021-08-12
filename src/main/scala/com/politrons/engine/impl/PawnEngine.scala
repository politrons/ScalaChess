package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

import scala.util.{Failure, Success, Try}

/**
 * Pawn Rule Engine Class responsible for all piece rules.
 */
case class PawnEngine() extends PieceEngine {

  override def isValid(movement: Movement): Try[Unit] = {
    for {
      _ <- if (isValidNextMove(movement)) Success() else Failure(IllegalMovementException(s"Error validating $movement"))
      _ <- if (isValidMovementRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating Pawn $movement"))
      _ <- if (isValidPathRule(movement)) Success() else Failure(IllegalMovementException(s"Error validating Pawn path $movement"))
    } yield ()
  }

  private def isValidMovementRule(movement: Movement): Boolean = {
    val (vertical: Int, horizontal: Int) = diffMovements(movement)
    (horizontal == 0 && vertical == 1) ||
      (horizontal == 0 && vertical == 2) && (movement.number <= 2)
  }

  /**
   * Rule: There's no pieces during the path of the pawn until reach the spot.
   */
  private def isValidPathRule(movement: Movement): Boolean = {
    (movement.rowFrom.value + 1 to movement.rowTo.value).count(row => {
      ChessBoard.board(row)(movement.columnTo.value).isDefined
    }) == 0
  }

  override def isCheck(movement: Movement): Try[Boolean] = Success(false)
}
