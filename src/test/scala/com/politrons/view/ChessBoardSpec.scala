package com.politrons.view

import com.politrons.engine.impl.PawnEngine
import com.politrons.model.ChessDomain.Player1
import com.politrons.model.Piece
import com.politrons.utils.BoardMock.boardMock
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}

class ChessBoardSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    ChessBoard.board = boardMock
  }

  test("Check that all pieces are on the board when we start a game") {
    Given("Chess board")
    val board = ChessBoard.board
    When("I get all first line pieces")
    val maybeRookPieceLeft = board(0)(0)
    val maybeRookPieceRight = board(0)(7)
    Then("All pieces are in place")
    assert(maybeRookPieceLeft.isDefined)
    assert(maybeRookPieceLeft.get.name.trim.toLowerCase() == "rook")
    assert(maybeRookPieceRight.isDefined)
    assert(maybeRookPieceRight.get.name.trim.toLowerCase() == "rook")
  }

  test("Check that all singleton board can be modify in each movement") {
    Given("Chess board")
    val board = ChessBoard.board
    When("I modify one of the pieces")
    board(0)(0) = Some(Piece("pawn",Player1(), PawnEngine()))
    Then("The piece in the position is a new one")
    val maybeRookPiece = board(0)(0)
    assert(maybeRookPiece.isDefined)
    assert(maybeRookPiece.get.name.trim.toLowerCase() == "pawn")
  }

}

