package com.politrons.engine

import com.politrons.engine.impl.{BishopEngine, KnightEngine}
import com.politrons.model.ChessDomain._
import com.politrons.model.Piece
import com.politrons.utils.BoardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

class ChessEngineKnightSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    ChessBoard.board = BoardMock.getBoardMock
  }

  test("Knight rule validation move horizontal 2 and vertical 1 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Knight",Player1(), KnightEngine())
    When("I invoke isValidateMove for Knight")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Knight rule validation move horizontal 1 and vertical 2 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Knight",Player1(), KnightEngine())
    When("I invoke isValidateMove for Knight")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(3)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Knight rule validation move  negative horizontal 1 and vertical 2 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Knight",Player1(), KnightEngine())
    When("I invoke isValidateMove for Knight")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Knight rule validation move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Knight",Player1(), KnightEngine())
    When("I invoke isValidateMove for Knight")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(6)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  test("Knight rule validation no move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Knight",Player1(), KnightEngine())
    When("I invoke isValidateMove for Knight")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  /**
   * CHECK RULES
   * ------------
   */
  test("Knight check rule, knight is in 4,2") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("knight",Player2(), KnightEngine())
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(4), RowTo(2))
    ChessBoard.board(2)(4) = Some(piece)
    When("I invoke isCheck for Knight")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Knight check rule, knight is in 2,2") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("knight",Player2(), KnightEngine())
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(2), RowTo(2))
    ChessBoard.board(2)(2) = Some(piece)
    When("I invoke isCheck for Knight")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Knight check rule, knight is in 1,5") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("knight",Player2(), KnightEngine())
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(5), RowTo(1))
    ChessBoard.board(1)(5) = Some(piece)
    When("I invoke isCheck for Knight")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

}
