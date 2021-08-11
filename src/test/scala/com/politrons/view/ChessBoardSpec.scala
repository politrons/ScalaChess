package com.politrons.view

import com.politrons.engine.impl.PawnEngine
import com.politrons.model.ChessDomain.{Player1, Player2}
import com.politrons.model.Piece
import com.politrons.utils.BoardMock
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

class ChessBoardSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    ChessBoard.board = BoardMock.getBoardMock
  }

  test("Check that all pieces are on the board when we start a game") {
    Given("Chess board")
    val board = ChessBoard.board
    When("I get all first line pieces")
    val maybeRookPieceLeft = board(0)(0)
    val maybeRookPieceRight = board(0)(7)
    val maybeRookPiecePlayer2Left = board(7)(0)
    val maybeRookPiecePlayer2Right = board(7)(7)
    Then("All pieces are in place")
    assert(maybeRookPieceLeft.isDefined)
    assert(maybeRookPieceLeft.get.name.trim.toLowerCase() == "rook")
    assert(maybeRookPieceLeft.get.player == Player1())
    assert(maybeRookPieceRight.isDefined)
    assert(maybeRookPieceRight.get.name.trim.toLowerCase() == "rook")
    assert(maybeRookPieceRight.get.player == Player1())

    assert(maybeRookPiecePlayer2Left.isDefined)
    assert(maybeRookPiecePlayer2Left.get.name.trim.toLowerCase() == "rook")
    assert(maybeRookPiecePlayer2Left.get.player == Player2())
    assert(maybeRookPiecePlayer2Right.isDefined)
    assert(maybeRookPiecePlayer2Right.get.name.trim.toLowerCase() == "rook")
    assert(maybeRookPiecePlayer2Right.get.player == Player2())

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

