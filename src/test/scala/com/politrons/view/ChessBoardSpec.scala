package com.politrons.view

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}

class ChessBoardSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterAll {

  test("Check that all pieces are on the board when we start a game") {
    Given("Chess board")
    val chessBoard = ChessBoard()
    When("I get all first line pieces")
    val maybeRookPiece = chessBoard.board(0)(0)
    Then("All pieces are in place")
    assert(maybeRookPiece.isDefined)
    assert(maybeRookPiece.get.name.trim.toLowerCase() == "rook")
  }
}

