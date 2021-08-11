package com.politrons.engine

import com.politrons.engine.impl.KnightEngine
import com.politrons.model.ChessDomain._
import com.politrons.model.Piece
import com.politrons.utils.BoardMock.boardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}

class ChessEngineKnightSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    ChessBoard.board = boardMock
  }

  test("Knight rule validation move horizontal 2 and vertical 1 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Knight",Player1(), KnightEngine())
    When("I invoke isValidateMove for Knight")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Knight rule validation move horizontal 1 and vertical 2 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Knight",Player1(), KnightEngine())
    When("I invoke isValidateMove for Knight")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(3)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Knight rule validation move  negative horizontal 1 and vertical 2 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Knight",Player1(), KnightEngine())
    When("I invoke isValidateMove for Knight")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Knight rule validation move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Knight",Player1(), KnightEngine())
    When("I invoke isValidateMove for Knight")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(6)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  test("Knight rule validation no move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Knight",Player1(), KnightEngine())
    When("I invoke isValidateMove for Knight")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

}
