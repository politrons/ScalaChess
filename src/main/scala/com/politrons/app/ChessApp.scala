package com.politrons.app

import com.whitehatgaming.*

import scala.io.Source

object ChessApp {


  //  val horizontal = ('A' to 'H')
  //  val vertical = (1 to 8)


  //  case class Position(piece:Option[Piece])

  case class Piece(name: String)

  var myArray = Array.ofDim[Piece](8, 8)


  val board = Array.tabulate[Option[Piece]](8, 8) { (i, j) =>
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

      //First row
      case (0, 0) => Some(Piece("  Rook  "))
      case (0, 1) => Some(Piece(" Knight "))
      case (0, 2) => Some(Piece(" Bishop "))
      case (0, 3) => Some(Piece("  King  "))
      case (0, 4) => Some(Piece("  Queen "))
      case (0, 5) => Some(Piece(" Bishop "))
      case (0, 6) => Some(Piece(" Knight "))
      case (0, 7) => Some(Piece("  Rook  "))

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


  case class Movement(val columFrom: Int,
                      val rowFrom: Int,
                      val columTo: Int,
                      val rowTo: Int)

  def main(args: Array[String]): Unit = {
    val path = getClass.getResource("/sample-moves.txt").getPath
    val inputFile = new UserInputFile(path)

    def getAllMovements(movements: List[Movement]): List[Movement] = {
      val move: Array[Int] = inputFile.nextMove()
      if (move != null) {
        require(move.size == 4, "Error loading movement, a mnove must include 4 elements")
        getAllMovements(Movement(move(0), move(1), move(2), move(3)) +: movements)
      } else {
        movements
      }
    }

    val value = getAllMovements(List())
    println(value)

    printBoard()
  }


  /**
   * Prints current board state to the console in the ASCII mnemonic format
   */
  def printBoard(): Unit = {
    val filesRow = """     A       B        C        D        E        F        G        H       """
    val separator = """+-------+--------+--------+--------+--------+--------+--------+--------+  """
    println(filesRow)
    println(separator)

    board.foreach(colums => {
      colums.foreach(maybePos => {
        print(maybePos.getOrElse(Piece("        ")).name)
        print("|")
      })
      println("\n")
    })

    println(separator)
    println(filesRow)
  }

}




//0 : The column position to move from (0 indexed)
//1 : The row position to move from (0 indexed)
//2 : The column position to move to (0 indexed)
//3 : The row position to move to (0 indexed)