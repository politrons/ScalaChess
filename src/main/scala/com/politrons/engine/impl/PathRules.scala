package com.politrons.engine.impl

import com.politrons.model.ChessDomain.Movement
import com.politrons.view.ChessBoard

/**
 * Generic path rules used for multiple [PieceEngines]
 */
object PathRules {

  /**
   * Rule:Generic Path rule to check that there's no pieces during the diagonal path
   */
  def diagonalPathRule(movement: Movement): Boolean = {
    var column = movement.columnFrom.value

    val result = if (movement.rowFrom.value > movement.rowTo.value) {
      (movement.rowFrom.value - 1 until movement.rowTo.value by -1).count(row => {
        if (movement.columnFrom.value > movement.columnTo.value) {
          column -= 1
        } else {
          column += 1
        }
        val maybePiece = if (column >= 0 && column <= 7) {
          ChessBoard.board(row)(column)
        } else {
          None
        }
        maybePiece.isDefined
      }) == 0
    } else {
      (movement.rowFrom.value + 1 until movement.rowTo.value by 1).count(row => {
        if (movement.columnFrom.value > movement.columnTo.value) {
          column -= 1
        } else {
          column += 1
        }
        val maybePiece = if (column >= 0 && column <= 7) {
          ChessBoard.board(row)(column)
        } else {
          None
        }
        maybePiece.isDefined
      }) == 0
    }
    result && destinationMovementNoPieceOfMine(movement)
  }

  /**
   * Rule:Generic Path rule to check that there's no pieces during the horizontal or vertical path
   */
  def horizontalOrVerticalPathRule(vertical: Int, movement: Movement): Boolean = {
    val result = if (vertical > 0) {
      (movement.rowFrom.value + 1 until movement.rowTo.value).count(row => {
        val maybePiece = ChessBoard.board(row)(movement.columnFrom.value)
        maybePiece.isDefined
      }) == 0
    } else {
      (movement.columnFrom.value + 1 until movement.columnTo.value).count(column => {
        val maybePiece = ChessBoard.board(movement.rowFrom.value)(column)
        maybePiece.isDefined
      }) == 0
    }
    result && destinationMovementNoPieceOfMine(movement)
  }

  /**
   * Rule:Check that the destination spot is empty or there is not a piece of mine.
   */
  private def destinationMovementNoPieceOfMine(movement: Movement): Boolean = {
    ChessBoard.board(movement.rowTo.value)(movement.columnTo.value) match {
      case Some(piece) => piece.player != movement.player
      case None => true
    }
  }

}
