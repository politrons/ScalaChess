package com.politrons.utils

import com.politrons.engine.impl.{BishopEngine, KingEngine, KnightEngine, PawnEngine, QueenEngine, RookEngine}
import com.politrons.model.Piece

object BoardMock {

  val boardMock: Array[Array[Option[Piece]]] = {
    Array.tabulate[Option[Piece]](8, 8) {
      (i, j) =>
        (i, j) match {
          //First row
          case (0, 0) => Some(Piece("  Rook  ", RookEngine()))
          case (0, 1) => Some(Piece(" Knight ", KnightEngine()))
          case (0, 2) => Some(Piece(" Bishop ", BishopEngine()))
          case (0, 3) => Some(Piece("  King  ", KingEngine()))
          case (0, 4) => Some(Piece("  Queen ", QueenEngine()))
          case (0, 5) => Some(Piece(" Bishop ", BishopEngine()))
          case (0, 6) => Some(Piece(" Knight ", KnightEngine()))
          case (0, 7) => Some(Piece("  Rook  ", RookEngine()))

          //Second row
          case (1, 0) => Some(Piece("  Pawn  ", PawnEngine()))
          case (1, 1) => Some(Piece("  Pawn  ", PawnEngine()))
          case (1, 2) => Some(Piece("  Pawn  ", PawnEngine()))
          case (1, 3) => Some(Piece("  Pawn  ", PawnEngine()))
          case (1, 4) => Some(Piece("  Pawn  ", PawnEngine()))
          case (1, 5) => Some(Piece("  Pawn  ", PawnEngine()))
          case (1, 6) => Some(Piece("  Pawn  ", PawnEngine()))
          case (1, 7) => Some(Piece("  Pawn  ", PawnEngine()))

          //Seventh row
          case (6, 0) => Some(Piece("  pawn  ", PawnEngine()))
          case (6, 1) => Some(Piece("  pawn  ", PawnEngine()))
          case (6, 2) => Some(Piece("  pawn  ", PawnEngine()))
          case (6, 3) => Some(Piece("  pawn  ", PawnEngine()))
          case (6, 4) => Some(Piece("  pawn  ", PawnEngine()))
          case (6, 5) => Some(Piece("  pawn  ", PawnEngine()))
          case (6, 6) => Some(Piece("  pawn  ", PawnEngine()))
          case (6, 7) => Some(Piece("  pawn  ", PawnEngine()))

          //Eighth row
          case (7, 0) => Some(Piece("  rook  ", RookEngine()))
          case (7, 1) => Some(Piece(" knight ", KnightEngine()))
          case (7, 2) => Some(Piece(" bishop ", BishopEngine()))
          case (7, 3) => Some(Piece("  king  ", KingEngine()))
          case (7, 4) => Some(Piece("  queen ", QueenEngine()))
          case (7, 5) => Some(Piece(" bishop ", BishopEngine()))
          case (7, 6) => Some(Piece(" knight ", KnightEngine()))
          case (7, 7) => Some(Piece("  rook  ", RookEngine()))

          case (_, _) => None
        }
    }
  }
}
