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
    ChessApp.main(Array("src/test/resources/sample-moves.txt", "10"))
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
    ChessApp.main(Array("src/test/resources/sample-moves-invalid.txt", "10"))
    When("I check for several wrong movements in the board")
    val maybeKnightPiece = ChessBoard.board(5)(1)
    val maybeRookPiece = ChessBoard.board(4)(7)
    Then("The pieces are not there")
    assert(maybeKnightPiece.isEmpty)
    assert(maybeRookPiece.isEmpty)
  }

  test("Check mate with scholar player1") {
    Given("Chess App running")
    ChessApp.main(Array("src/test/resources/scholar-checkmate-player1.txt", "10"))
    When("I run the chess game")
    Then("The game end with a checkmate")
    assert(ChessBoard.player2Check == "In Check")
  }

  test("Check mate with scholar player2") {
    Given("Chess App running")
    ChessApp.main(Array("src/test/resources/scholar-checkmate-player2.txt", "10"))
    When("I run the chess game")
    Then("The game end with a checkmate")
    assert(ChessBoard.player1Check == "In Check")
  }

  test("Player1 eat pawn of player2") {
    Given("Chess App running")
    ChessApp.main(Array("src/test/resources/pawn-moves.txt", "10"))
    When("I run the chess game")
    Then("The player1 pawn is on player2 spot")
    val maybePlayer1Pawn = ChessBoard.board(4)(4)
    assert(maybePlayer1Pawn.isDefined)
    assert(maybePlayer1Pawn.get.name.trim == "Pawn")
  }

  test("Player1 make a wrong move that provoke a Chess of Bishop") {
    Given("Chess App running")
    ChessApp.main(Array("src/test/resources/invalid-check-move/in-check-invalid-bishop.txt", "10"))
    When("I run the chess game")
    Then("The player1 made a wrong move")
    assert(ChessBoard.board(2)(4).isEmpty)
  }

  test("Player1 make a wrong move that provoke a Chess of Rook") {
    Given("Chess App running")
    ChessApp.main(Array("src/test/resources/invalid-check-move/in-check-invalid-rook.txt", "10"))
    When("I run the chess game")
    Then("The player1 made a wrong move")
    val maybePlayer1Pawn = ChessBoard.board(4)(4)
    assert(maybePlayer1Pawn.isDefined)
    assert(maybePlayer1Pawn.get.name.trim == "pawn")
  }

  test("Player1 make a wrong move that provoke a Chess of Queen") {
    Given("Chess App running")
    ChessApp.main(Array("src/test/resources/invalid-check-move/in-check-invalid-queen.txt", "10"))
    When("I run the chess game")
    Then("The player1 made a wrong move")
    val maybePlayer1Pawn = ChessBoard.board(1)(4)
    assert(maybePlayer1Pawn.isDefined)
    assert(maybePlayer1Pawn.get.name.trim == "Pawn")
  }

  test("Player1 make a wrong move that provoke a Chess of Knight") {
    Given("Chess App running")
    ChessApp.main(Array("src/test/resources/invalid-check-move/in-check-invalid-knight.txt", "10"))
    When("I run the chess game")
    Then("The player1 made a wrong move")
    val maybePlayer1Pawn = ChessBoard.board(3)(3)
    assert(maybePlayer1Pawn.isEmpty)
  }

  test("Player1 make a wrong move with a piece that it does not belong to him") {
    Given("Chess App running")
    ChessApp.main(Array("src/test/resources/invalid-player-piece.txt", "10"))
    When("I run the chess game")
    Then("The player1 made a wrong move")
    val maybePlayer1Pawn = ChessBoard.board(5)(3)
    assert(maybePlayer1Pawn.isEmpty)
  }

}

