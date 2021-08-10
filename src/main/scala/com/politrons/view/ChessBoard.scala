package com.politrons.view

import com.politrons.model.ChessDomain.Piece

case class ChessBoard(board: Array[Array[Option[Piece]]] = ChessBoard.createInitBoard()) {

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


object ChessBoard {

  def createInitBoard(): Array[Array[Option[Piece]]] = {
    Array.tabulate[Option[Piece]](8, 8) {
      (i, j) =>
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
}
