package com.politrons.rules

import com.politrons.model.ChessDomain.{Movement, Player}
import com.politrons.model.Piece
import com.politrons.view.ChessBoard

import scala.util.Try

/**
 * Generic check rules used for multiple [PieceEngines]
 */
object CheckRules {

  /**
   * Rule Check: Go in all possible diagonals, and just obtain the first defined piece in the path.
   * If that piece is a King is consider a Check.
   */
  def diagonalCheck(movement: Movement): Try[Boolean] = {
    Try {
      val currentRow = movement.rowTo.value
      var currentColumn = movement.columnTo.value
      //Row ++  Column --
      val definedRowPlusColumnLess = searchKingInDiagonal(movement, currentRow + 1, 7, 1, column => column - 1)
      //Row -- Column --
      val definedRowLessColumnLess = searchKingInDiagonal(movement, currentRow - 1, 0, -1, column => column - 1)
      //Row ++ Column ++
      val definedRowPlusColumnPlus = searchKingInDiagonal(movement, currentRow + 1, 7, 1, column => column + 1)
      //Row -- Column ++
      currentColumn = movement.columnTo.value
      val definedRowLessColumnPlus = searchKingInDiagonal(movement, currentRow - 1, 0, -1, column => column + 1)

      definedRowPlusColumnLess.isDefined ||
        definedRowLessColumnLess.isDefined ||
        definedRowPlusColumnPlus.isDefined ||
        definedRowLessColumnPlus.isDefined
    }
  }

  /**
   * Rule Check: Go in horizontal and vertical directions to try to find the first piece in the path as King.
   */
  def horizontalVerticalCheck(movement: Movement): Try[Boolean] = {
    Try {
      val definedVerticalRight = getKingInCleanPath(movement.rowTo.value + 1, 7, 1, movement)
      val definedVerticalLeft = getKingInCleanPath(movement.rowTo.value - 1, 0, -1, movement)

      val definedHorizontalRight = getKingInCleanPath(movement.columnTo.value + 1, 7, 1, movement)
      val definedHorizontalLeft = getKingInCleanPath(movement.columnTo.value - 1, 0, -1, movement)

      definedHorizontalRight.isDefined ||
        definedHorizontalLeft.isDefined ||
        definedVerticalRight.isDefined ||
        definedVerticalLeft.isDefined
    }
  }

  /**
   * Check Rule:  to check if the first defined piece in the path is a King
   */
  private def getKingInCleanPath(from: Int, to: Int, IncDec: Int, movement: Movement): Option[Piece] = {
    (from to to by IncDec)
      .flatMap(row => ChessBoard.board(row)(movement.columnFrom.value))
      .take(1)
      .find(piece =>
        piece.player != movement.player &&
          piece.name.trim.toLowerCase() == "king")
  }

  /**
   * Check Rule: check if the King is in all possible 8 movements of the current Knight position.
   */
  def knightCheckRule(movement: Movement): Try[Boolean] = {
    Try {
      val row = movement.rowTo.value
      val column = movement.columnTo.value
      findKingInKnightMovement(row - 1, column - 2, movement.player) ||
        findKingInKnightMovement(row - 1, column + 2, movement.player) ||
        findKingInKnightMovement(row + 1, column - 2, movement.player) ||
        findKingInKnightMovement(row + 1, column + 2, movement.player) ||
        findKingInKnightMovement(row + 2, column - 1, movement.player) ||
        findKingInKnightMovement(row + 2, column + 1, movement.player) ||
        findKingInKnightMovement(row - 2, column - 1, movement.player) ||
        findKingInKnightMovement(row - 2, column + 1, movement.player)
    }
  }

  def findKingInKnightMovement(row: Int,
                               column: Int,
                               player: Player): Boolean = {
    if ((row >= 0) && column >= 0) {
      val maybePiece = ChessBoard.board(row)(column)
      maybePiece.isDefined &&
        maybePiece.get.player != player &&
        (maybePiece.get.name.trim.toLowerCase() == "king")
    } else {
      false
    }
  }

  private def searchKingInDiagonal(movement: Movement,
                                   from: Int,
                                   to: Int,
                                   incDec: Int,
                                   incDecColumnFunc: Int => Int): Option[Piece] = {
    var currentColumn = movement.columnTo.value
    (from to to by incDec)
      .flatMap(row => {
        currentColumn = incDecColumnFunc(currentColumn)
        if (currentColumn >= 0 && currentColumn <= 7) {
          ChessBoard.board(row)(currentColumn)
        } else {
          None
        }
      })
      .take(1)
      .find(piece =>
        piece.player != movement.player &&
          piece.name.trim.toLowerCase() == "king")
  }

}
