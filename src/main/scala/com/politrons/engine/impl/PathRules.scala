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
    val result = (movement.rowFrom.value + 1 until movement.rowTo.value).count(row => {
      (movement.columnFrom.value + 1 until movement.columnTo.value).count(column => {
        val maybePiece = ChessBoard.board(row)(column)
        maybePiece.isDefined
      }) > 0
    }) == 0
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
