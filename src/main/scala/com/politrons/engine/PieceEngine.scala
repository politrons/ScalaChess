package com.politrons.engine

import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain
import com.politrons.model.ChessDomain.{ColumnFrom, ColumnTo, Movement, Player1, Player2, RowFrom, RowTo}
import com.politrons.view.ChessBoard

import scala.util.{Success, Try}

/**
 * Contract of all Rule Engine of the game
 */
trait PieceEngine {

  def isValid(movement: ChessDomain.Movement): Try[Unit]

  def isCheck(movement: Movement): Try[Boolean]

  def inCheck(movement: Movement): Try[Unit] = {
    val maybePiece = ChessBoard.board(movement.rowFrom.value)(movement.columnFrom.value)
    ChessBoard.board(movement.rowFrom.value)(movement.columnFrom.value) = None
    val triedUnit = Try {
      movement.player match {
        case Player1() =>
          for (row <- 0 to 7) {
            for (column <- 0 to 7) {
              ChessBoard.board(row)(column) match {
                case Some(piece) if piece.player == Player2() =>
                  val opponentMovement = Movement(Player2(), 0, ColumnFrom(0), RowFrom(0), ColumnTo(column), RowTo(row))
                  piece.isCheck(opponentMovement) match {
                    case Success(isCheck) if isCheck => throw IllegalMovementException(s"Illegal move, since it let the King in Check by $movement")
                    case _ => ()
                  }
                case _ => ()
              }
            }
          }
        case Player2() =>
          for (row <- 0 to 7) {
            for (column <- 0 to 7) {
              ChessBoard.board(row)(column) match {
                case Some(piece) if piece.player == Player1() =>
                  val opponentMovement = Movement(Player1(), 0, ColumnFrom(0), RowFrom(0), ColumnTo(column), RowTo(row))
                  piece.isCheck(opponentMovement) match {
                    case Success(isCheck) if isCheck => throw IllegalMovementException(s"Illegal move, since it let the King in Check by $movement")
                    case _ => ()
                  }
                case _ => ()
              }
            }
          }
      }
    }
    ChessBoard.board(movement.rowFrom.value)(movement.columnFrom.value) = maybePiece
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
}
