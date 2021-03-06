package com.politrons.app

import com.politrons.exceptions.IllegalMovementException
import com.politrons.model.ChessDomain._
import com.politrons.view.ChessBoard
import com.whitehatgaming.UserInputFile

import java.nio.file.Paths
import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object ChessApp {

  var path: String = _
  var chessClock: Int = _
  var inputFile: UserInputFile = _

  def main(args: Array[String]): Unit = {
    val fileName = args(0)
    println(s"Path of Chess game movements: $fileName")
    chessClock = args(1).toInt
    println(s"Chess clock for the game: $chessClock ms")
    ChessBoard.printBoard(false)
    path = Paths.get(fileName).toString
    inputFile = new UserInputFile(path)
    runPlayerMovement(Player1(), 1)
  }

  /**
   * Recursive function which in each iteration we extract one of the movements.
   * In each iteration we pivot from one Player to another.
   * Once we create the [Movement] we pass to the [movePieceInBoard] function
   * to move physically the piece in the board.
   * In case the movement was invalid we keep with the player that made the wrong move.
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
          println(s"[Movement Error]: $player ${t.getMessage}")
          runPlayerMovement(player, moveNumber + 1)
      }
    }
  }

  /**
   * Once we have a movement, we check that the movement can be done, and then we apply on
   * the board and we print in console.
   */
  private def movePieceInBoard(movement: Movement): Try[Unit] = {
    ChessBoard.board(movement.rowFrom.value)(movement.columnFrom.value) match {
      case maybePiece@Some(piece) if piece.player == movement.player =>
        piece.isValid(movement) match {
          case Success(_) =>
            ChessBoard.board(movement.rowTo.value)(movement.columnTo.value) = maybePiece
            ChessBoard.board(movement.rowFrom.value)(movement.columnFrom.value) = None

            val isCheck = piece.isCheck(movement) match {
              case Success(result) if result => true
              case Success(result) if !result => false
              case Failure(t) =>
                println(s"Error checking if King is in check. Caused by $t")
                false
            }

            ChessBoard.printGameInfo(movement.player, isCheck)
            Thread.sleep(chessClock)
            Success()
          case Failure(t) => Failure(t)
        }
      case Some(piece) if piece.player != movement.player =>
        val errorMessage = s"Invalid movement. ${piece.name} does not belong to player ${movement.player}"
        Failure(IllegalMovementException(errorMessage))
      case None =>
        val errorMessage = s"Invalid movement. Piece does not exist in position ${movement.rowFrom.value}-${movement.columnFrom.value}"
        Failure(IllegalMovementException(errorMessage))
    }
  }


}
