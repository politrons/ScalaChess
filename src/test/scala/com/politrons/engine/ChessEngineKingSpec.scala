package com.politrons.engine

import com.politrons.engine.impl.{BishopEngine, KingEngine}
import com.politrons.model.ChessDomain._
import com.politrons.model.Piece
import com.politrons.utils.BoardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}

class ChessEngineKingSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    ChessBoard.board = BoardMock.getBoardMock
  }

  test("King rule validation move horizontal 1 and vertical 1 succeed") {
    Given("Chess engine instance")
    val piece = Piece("King",Player1(), KingEngine())
    When("I invoke isValidateMove for King")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("King rule validation move horizontal 1") {
    Given("Chess engine instance")
    val piece = Piece("King",Player1(), KingEngine())
    When("I invoke isValidateMove for King")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(2), RowFrom(2), ColumnTo(3), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("King rule validation move vertical 1") {
    Given("Chess engine instance")
    val piece = Piece("King",Player1(), KingEngine())
    When("I invoke isValidateMove for King")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("King rule validation move  negative horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val piece = Piece("King",Player1(), KingEngine())
    When("I invoke isValidateMove for King")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  test("King rule validation move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("King",Player1(), KingEngine())
    When("I invoke isValidateMove for King")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(6)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  test("King rule validation no move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("King",Player1(), KingEngine())
    When("I invoke isValidateMove for King")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  /**
   * PATH RULES
   * ----------
   */
  test("King rule validation path one piece in the path") {
    Given("Chess engine instance")
    val piece = Piece("King",Player1(), KingEngine())
    When("I invoke isValidateMove for King")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(1)))
    Then("The movement is ok")
    assert(result.isFailure)
  }

  /**
   * CHECK RULES
   * ------------
   */
  test("King check rule, rook is in front of king with other piece in between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("king",Player2(), KingEngine())
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(5), RowTo(2))
    ChessBoard.board(2)(5) = Some(piece)
    When("I invoke isCheck for King")
    val isCheck = piece.isCheck(movement)
    Then("The movement is not check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("King check rule king diagonal") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("king",Player2(), KingEngine())
    ChessBoard.board(1)(4) = Some(piece)
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(4), RowTo(1))
    When("I invoke isCheck for King")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("King check rule king vertical") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("king",Player2(), KingEngine())
    ChessBoard.board(1)(3) = Some(piece)
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(3), RowTo(1))
    When("I invoke isCheck for King")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }
}

