package com.politrons.app

import com.politrons.engine.ChessEngine
import com.politrons.model.ChessDomain._
import com.politrons.view.ChessView
import com.whitehatgaming.UserInputFile

import scala.annotation.tailrec

object ChessApp {

  def main(args: Array[String]): Unit = {

    val chessView = ChessView()
    val chessEngine = ChessEngine()

    val path = getClass.getResource("/sample-moves.txt").getPath
    val inputFile = new UserInputFile(path)

    @tailrec
    def runAllMovements(player: Player1 | Player2, moveNumber: Int): Unit = {
      val move: Array[Int] = inputFile.nextMove()
      if (move != null) {
        require(move.length == 4, "Error loading movement, a move must include 4 elements")
        val movement = Movement(player, moveNumber, ColumnFrom(move(0)), RowFrom(move(1)), ColumnTo(move(2)), RowTo(move(3)))
        board(movement.rowFrom.value)(movement.columnFrom.value) match {
          case maybePiece@Some(piece) =>
            if (chessEngine.isValidMove(piece, movement)) {
              board(movement.rowTo.value)(movement.columnTo.value) = maybePiece
              board(movement.rowFrom.value)(movement.columnFrom.value) = None
            }
            chessView.printBoard(board)
            Thread.sleep(4000)
          case None => ???
        }
        player match {
          case Player1() => runAllMovements(Player2(), moveNumber + 1)
          case Player2() => runAllMovements(Player1(), moveNumber + 1)
        }
      }
    }

    runAllMovements(Player1(), 1)
  }


  val board: Array[Array[Option[Piece]]] = Array.tabulate[Option[Piece]](8, 8) { (i, j) =>
    (i, j) match {
      //First row
      case (0, 0) => Some(Piece("  Rook  "))
      case (0, 1) => Some(Piece(" Knight "))
      case (0, 2) => Some(Piece(" Bishop "))
      case (0, 3) => Some(Piece("  King  "))
      case (0, 4) => Some(Piece("  Queen "))
      case (0, 5) => Some(Piece(" Bishop "))
      case (0, 6) => Some(Piece(" Knight "))
      case (0, 7) => Some(Piece("  Rook  "))

      //Second row
      case (1, 0) => Some(Piece("  Pawn  "))
      case (1, 1) => Some(Piece("  Pawn  "))
      case (1, 2) => Some(Piece("  Pawn  "))
      case (1, 3) => Some(Piece("  Pawn  "))
      case (1, 4) => Some(Piece("  Pawn  "))
      case (1, 5) => Some(Piece("  Pawn  "))
      case (1, 6) => Some(Piece("  Pawn  "))
      case (1, 7) => Some(Piece("  Pawn  "))

      //Seventh row
      case (6, 0) => Some(Piece("  pawn  "))
      case (6, 1) => Some(Piece("  pawn  "))
      case (6, 2) => Some(Piece("  pawn  "))
      case (6, 3) => Some(Piece("  pawn  "))
      case (6, 4) => Some(Piece("  pawn  "))
      case (6, 5) => Some(Piece("  pawn  "))
      case (6, 6) => Some(Piece("  pawn  "))
      case (6, 7) => Some(Piece("  pawn  "))

      //Eighth row
      case (7, 0) => Some(Piece("  rook  "))
      case (7, 1) => Some(Piece(" knight "))
      case (7, 2) => Some(Piece(" bishop "))
      case (7, 3) => Some(Piece("  king  "))
      case (7, 4) => Some(Piece("  queen "))
      case (7, 5) => Some(Piece(" bishop "))
      case (7, 6) => Some(Piece(" knight "))
      case (7, 7) => Some(Piece("  rook  "))

      case (_, _) => None
    }

  }

}
