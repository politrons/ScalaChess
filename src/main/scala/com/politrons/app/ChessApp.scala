package com.politrons.app

import com.politrons.engine.ChessEngine
import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain.{ColumnFrom, ColumnTo, Movement, Player, Player1, Player2, RowFrom, RowTo}
import com.politrons.view.ChessBoard
import com.whitehatgaming.UserInputFile

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object ChessApp extends App {

  val chessView = ChessBoard()
  val chessEngine = ChessEngine(chessView)

  val path = getClass.getResource("/sample-moves.txt").getPath
  val inputFile = new UserInputFile(path)

  runPlayerMovement(Player1(), 1)

  /**
   * Recursive function which in each iteration we extract one of the movements.
   * In each iteration we pivot from one Player to another.
   * Once we create the [Movement] we pass to the [movePieceInBoard] function
   * to move phisically the piece in the board.
   */
  @tailrec
  def runPlayerMovement(player: Player, moveNumber: Int): Unit = {
    val move: Array[Int] = inputFile.nextMove()
    if (move != null) {
      require(move.length == 4, "Error loading movement, a move must include 4 elements")
      val movement = Movement(player, moveNumber, ColumnFrom(move(0)), RowFrom(move(1)), ColumnTo(move(2)), RowTo(move(3)))
      movePieceInBoard(movement) match {
        case Success(_) =>
          player match {
            case Player1() => runPlayerMovement(Player2(), moveNumber + 1)
            case Player2() => runPlayerMovement(Player1(), moveNumber + 1)
          }
        case Failure(t) =>
          println(t.getMessage)
          runPlayerMovement(player, moveNumber + 1)
      }
    }
  }

  /**
   * Once we have a movement, we check that the movement can be done, and then we apply on
   * the board and we print in console.
   */
  private def movePieceInBoard(movement: Movement): Try[Unit] = {
    chessView.board(movement.rowFrom.value)(movement.columnFrom.value) match {
      case maybePiece@Some(piece) =>
        chessEngine.isValidMovement(piece, movement) match {
          case Success(result) if result =>
            chessView.board(movement.rowTo.value)(movement.columnTo.value) = maybePiece
            chessView.board(movement.rowFrom.value)(movement.columnFrom.value) = None
          case Success(result) if !result =>
            val errorMessage = s"Invalid move for movement $movement"
            Failure(IllegalMovementException(errorMessage))
        }
        chessView.printBoard()
        Thread.sleep(4000)
        Success()
      case None =>
        val errorMessage = s"Invalid movement. Piece does not exist in position ${movement.rowFrom.value}-${movement.columnFrom.value}"
        Failure(IllegalMovementException(errorMessage))
    }
  }


}
