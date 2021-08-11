package com.politrons.engine

import com.politrons.engine.impl.{PawnEngine, QueenEngine, RookEngine}
import com.politrons.model.ChessDomain._
import com.politrons.model.Piece
import com.politrons.utils.BoardMock.boardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}

class ChessEngineQueenSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    ChessBoard.board = boardMock
  }

  test("Queen rule validation move horizontal succeed") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen and horizontal movement")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Queen rule validation move vertical succeed") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen and vertical movement")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(5)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Queen rule validation move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen and vertical and horizontal movement")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Queen rule validation no move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen and vertical and horizontal movement")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  test("Queen rule validation move horizontal 1 and vertical 1 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Queen rule validation move horizontal 5 and vertical 5 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Queen rule validation move negative horizontal 5 and vertical 5 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(4), RowTo(4)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Queen rule validation move horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  test("Queen rule validation move negative horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  /**
   * PATH RULES
   * ----------
   */
  test("Queen path rule other piece in the path") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), RookEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.valid( Movement(Player1(), 1, ColumnFrom(3), RowFrom(0), ColumnTo(4), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

}
