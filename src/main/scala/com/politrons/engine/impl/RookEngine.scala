package com.politrons.engine.impl

import com.politrons.engine.PieceEngine
import com.politrons.engine.impl.PathRules.horizontalOrVerticalPathRule
import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain.Movement
import com.politrons.model.Piece
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

  /**
   * Function that check if in the horizontal or vertical path the first piece found is a King of the opponent.
   */
  override def isCheck(movement: Movement): Try[Boolean] = {
    Try {
      val definedVerticalRight = getKingInCleanPath(movement.rowTo.value + 1, 7, movement)
      val definedVerticalLeft = getKingInCleanPath(0, movement.rowTo.value - 1, movement)

      val definedHorizontalRight = getKingInCleanPath(movement.columnTo.value + 1, 7, movement)
      val definedHorizontalLeft = getKingInCleanPath(0, movement.columnTo.value - 1, movement)

      definedHorizontalRight.isDefined ||
        definedHorizontalLeft.isDefined ||
        definedVerticalRight.isDefined ||
        definedVerticalLeft.isDefined
    }
  }

  /**
   * Function to check if the first defined piece in the path is a King
   */
  def getKingInCleanPath(from: Int, to: Int, movement: Movement): Option[Piece] = {
    (from to to)
      .flatMap(row => ChessBoard.board(row)(movement.columnFrom.value))
      .take(1)
      .find(piece =>
        piece.player != movement.player &&
          piece.name.trim.toLowerCase() == "king")
  }

}
