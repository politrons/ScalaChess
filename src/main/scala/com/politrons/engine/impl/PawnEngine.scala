package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain.Movement
import com.politrons.model.Piece
import com.politrons.rules.PathRules.oneForwardAndDiagonalPath
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
      _ <- inCheckRule(movement)
    } yield ()
  }

  private def isValidMovementRule(movement: Movement): Boolean = {
    val (vertical: Int, horizontal: Int) = diffMovements(movement)
    val destinationPiece = ChessBoard.board(movement.rowTo.value)(movement.columnTo.value)
    diagonalMoveWithOpponentPieceInDestination(movement, horizontal, vertical, destinationPiece) ||
      (horizontal == 0 && vertical == 1) ||
      ((horizontal == 0 && vertical == 2) && (movement.number <= 2))
  }

  /**
   * Rule: There's no pieces during the path of the pawn until reach the spot.
   */
  private def isValidPathRule(movement: Movement): Boolean = {
    val (_: Int, horizontal: Int) = diffMovements(movement)
    oneForwardAndDiagonalPath(horizontal, movement)
  }

  override def isCheckRule(movement: Movement): Try[Boolean] = Success(false)

  private def diagonalMoveWithOpponentPieceInDestination(movement: Movement,
                                                         horizontal: Int,
                                                         vertical: Int,
                                                         destinationPiece: Option[Piece]): Boolean = {
    horizontal == 1 && vertical == 1 && destinationPiece.isDefined && destinationPiece.get.player != movement.player
  }

}
