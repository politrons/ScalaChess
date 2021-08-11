package com.politrons.engine

import com.politrons.engine.impl.{PawnEngine, RookEngine}
import com.politrons.model.ChessDomain._
import com.politrons.model.Piece
import com.politrons.utils.BoardMock.boardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}

class ChessEngineRookSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    ChessBoard.board = boardMock
  }

  /**
   * MOVEMENT RULES
   * ---------------
   */
  test("Rook rule validation move horizontal succeed") {
    Given("Chess engine instance")
    val piece = Piece("Rook", RookEngine())
    When("I invoke isValidateMove for Rook and horizontal movement")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(3), RowFrom(3), ColumnTo(5), RowTo(3)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)

  }

  test("Rook rule validation move vertical succeed") {
    Given("Chess engine instance")
    val piece = Piece("Rook", RookEngine())
    When("I invoke isValidateMove for Rook and vertical movement")
    val result = piece.valid( Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(5)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Rook rule validation move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Rook", RookEngine())
    When("I invoke isValidateMove for Rook and vertical and horizontal movement")
    val result = piece.valid( Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  test("Rook rule validation no move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Rook", RookEngine())
    When("I invoke isValidateMove for Rook and vertical and horizontal movement")
    val result = piece.valid( Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  /**
   * PATH RULES
   * ----------
   */
  test("Rook path rule other piece at the end of path") {
    Given("Chess engine instance")
    val piece = Piece("Rook", RookEngine())
    ChessBoard.board(2)(1) = Some(Piece("pawn", PawnEngine()))
    When("I invoke isValidateMove for Rook")
    val result = piece.valid( Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Rook path rule other piece during the path") {
    Given("Chess engine instance")
    val piece = Piece("Rook", RookEngine())
    ChessBoard.board(2)(1) = Some(Piece("pawn", PawnEngine()))
    When("I invoke isValidateMove for Rook")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(3)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

}

