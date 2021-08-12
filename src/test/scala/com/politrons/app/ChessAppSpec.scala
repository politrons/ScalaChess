package com.politrons.app

import com.politrons.utils.BoardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

class ChessAppSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    ChessBoard.board = BoardMock.getBoardMock
  }

  test("Check that application start a game, and finish successfully") {
    Given("Chess App running")
    ChessApp.main(Array("/sample-moves.txt", "10"))
    When("I check for several movements in the board")
    val maybeKingPiece = ChessBoard.board(1)(4)
    val maybeRookPiece = ChessBoard.board(2)(3)
    val maybeBishopPiece = ChessBoard.board(2)(7)
    Then("The pieces were moved")
    assert(maybeRookPiece.isDefined)
    assert(maybeRookPiece.get.name.trim == "rook")
    assert(maybeBishopPiece.isDefined)
    assert(maybeBishopPiece.get.name.trim == "bishop")
    assert(maybeKingPiece.isDefined)
    assert(maybeKingPiece.get.name.trim == "King")
  }

  test("Check that application reject wrong movements") {
    Given("Chess App running")
    ChessApp.main(Array("/sample-moves-invalid.txt", "10"))
    When("I check for several wrong movements in the board")
    val maybeKnightPiece = ChessBoard.board(5)(1)
    val maybeRookPiece = ChessBoard.board(4)(7)
    Then("The pieces are not there")
    assert(maybeKnightPiece.isEmpty)
    assert(maybeRookPiece.isEmpty)
  }

  test("Check that application make a checkmate") {
    Given("Chess App running")
    ChessApp.main(Array("/checkmate.txt", "10"))
    When("I check for several wrong movements in the board")

    Then("")

  }

}

