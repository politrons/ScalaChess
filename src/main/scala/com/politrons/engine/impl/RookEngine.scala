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


  override def isCheck(movement: Movement): Try[Boolean] = {
    Try {
      val (vertical: Int, _) = diffMovements(movement)
      val result = if (vertical > 0) {
        val definedRight = (movement.rowTo.value + 1 to 7).exists(row => {
          val maybePiece = ChessBoard.board(row)(movement.columnFrom.value)
          maybePiece.isDefined &&
            maybePiece.get.player != movement.player &&
            maybePiece.get.name.trim.toLowerCase() == "king"
        })

        val definedLeft = (0 until movement.rowTo.value).exists(row => {
          val maybePiece = ChessBoard.board(row)(movement.columnFrom.value)
          maybePiece.isDefined &&
            maybePiece.get.player != movement.player &&
            maybePiece.get.name.trim.toLowerCase() == "king"
        })
        definedRight || definedLeft
      } else {
        val definedRight = (movement.columnTo.value + 1 to 7).exists(row => {
          val maybePiece = ChessBoard.board(row)(movement.columnFrom.value)
          maybePiece.isDefined &&
            maybePiece.get.player != movement.player &&
            maybePiece.get.name.trim.toLowerCase() == "king"
        })

        val definedLeft = (0 until movement.columnTo.value).exists(row => {
          val maybePiece = ChessBoard.board(row)(movement.columnFrom.value)
          maybePiece.isDefined &&
            maybePiece.get.player != movement.player &&
            maybePiece.get.name.trim.toLowerCase() == "king"
        })
        definedRight || definedLeft
      }
      result
    }
  }

}
