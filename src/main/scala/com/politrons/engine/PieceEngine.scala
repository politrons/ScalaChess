package com.politrons.engine

import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.{ChessDomain, Piece}
import com.politrons.model.ChessDomain.{ColumnFrom, ColumnTo, Movement, Player, Player1, Player2, RowFrom, RowTo}
import com.politrons.view.ChessBoard

import scala.util.{Success, Try}

/**
 * Contract of all Rule Engine of the game
 */
trait PieceEngine {

  def isValid(movement: ChessDomain.Movement): Try[Unit]

  def isCheck(movement: Movement): Try[Boolean]

  def inCheck(movement: Movement): Try[Unit] = {
    val maybePieceFrom = ChessBoard.board(movement.rowFrom.value)(movement.columnFrom.value)
    val maybePieceTo = ChessBoard.board(movement.rowTo.value)(movement.columnTo.value)
    ChessBoard.board(movement.rowFrom.value)(movement.columnFrom.value) = None
    ChessBoard.board(movement.rowTo.value)(movement.columnTo.value) = maybePieceFrom
    val triedUnit = Try {
      movement.player match {
        case Player1() =>
          checkOpponentPieceForCheck(movement, Player2(), maybePieceFrom)
        case Player2() =>
          checkOpponentPieceForCheck(movement, Player1(), maybePieceFrom)
      }
    }
    ChessBoard.board(movement.rowFrom.value)(movement.columnFrom.value) = maybePieceFrom
    ChessBoard.board(movement.rowTo.value)(movement.columnTo.value) = maybePieceTo
    triedUnit
  }

  def isValidNextMove(movement: Movement): Boolean = {
    (movement.columnTo.value >= 0 || movement.columnTo.value <= 7) ||
      (movement.rowTo.value >= 0 || movement.rowTo.value <= 7)
  }

  def diffMovements(movement: Movement): (Int, Int) = {
    val vertical = Math.abs(movement.rowFrom.value - movement.rowTo.value)
    val horizontal = Math.abs(movement.columnFrom.value - movement.columnTo.value)
    (vertical, horizontal)
  }

  private def checkOpponentPieceForCheck(movement: Movement,
                                         opponentPlayer:Player,
                                         maybePieceFrom: Option[Piece]): Unit = {
    for (row <- 0 to 7) {
      for (column <- 0 to 7) {
        ChessBoard.board(row)(column) match {
          case Some(piece) if piece.player == opponentPlayer =>
            val opponentMovement = Movement(opponentPlayer, 0, ColumnFrom(0), RowFrom(0), ColumnTo(column), RowTo(row))
            piece.isCheck(opponentMovement) match {
              case Success(isCheck) if isCheck => throw IllegalMovementException(s"Illegal move of ${maybePieceFrom.get.name}, since it let the King in Check by $movement")
              case _ => ()
            }
          case _ => ()
        }
      }
    }
  }
}
