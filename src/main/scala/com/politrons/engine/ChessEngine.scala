package com.politrons.engine

import com.politrons.model.ChessDomain.{Piece,Movement}

case class ChessEngine() {


  def isValidateMove(piece: Piece, movement: Movement): Boolean = {
    piece.name.toLowerCase match {
      case "pawn" => ???
      case _ => false
    }
  }
}
