package com.politrons.view

import com.politrons.model.ChessDomain.Piece


case class ChessView() {


  /**
   * Prints current board state to the console in the ASCII mnemonic format
   */
  def printBoard(board: Array[Array[Option[Piece]]] ): Unit = {
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
