package com.politrons.engine

import com.politrons.engine.impl.{QueenEngine, RookEngine}
import com.politrons.model.ChessDomain._
import com.politrons.model.Piece
import com.politrons.utils.BoardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

class ChessEngineQueenSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    ChessBoard.board = BoardMock.getBoardMock
  }

  test("Queen rule validation move horizontal succeed") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen and horizontal movement")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Queen rule validation move vertical succeed") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen and vertical movement")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(5)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Queen rule validation move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen and vertical and horizontal movement")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
    Then("The movement is wrong")
    assert(result.isSuccess)
  }

  test("Queen rule validation no move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen and vertical and horizontal movement")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  test("Queen rule validation move horizontal 1 and vertical 1 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Queen rule validation move horizontal 5 and vertical 5 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Queen rule validation move negative horizontal 5 and vertical 5 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(4), RowTo(4)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Queen rule validation move horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  test("Queen rule validation move negative horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), QueenEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  /**
   * PATH RULES
   * ----------
   */
  test("Queen path rule other piece in the path") {
    Given("Chess engine instance")
    val piece = Piece("Queen",Player1(), RookEngine())
    When("I invoke isValidateMove for Queen")
    val result = piece.isValid( Movement(Player1(), 1, ColumnFrom(3), RowFrom(0), ColumnTo(4), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  /**
   * CHECK RULES
   * ------------
   */
  test("Queen check rule, queen is in front of king with other piece in between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("queen",Player2(), QueenEngine())
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(6), RowTo(3))
    ChessBoard.board(3)(6) = Some(piece)
    When("I invoke isCheck for Queen")
    val isCheck = piece.isCheck(movement)
    Then("The movement is not check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("Queen check rule queen row-- and column--") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("queen",Player2(), QueenEngine())
    ChessBoard.board(1)(4) = None
    ChessBoard.board(3)(6) = Some(piece)
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(6), RowTo(3))
    When("I invoke isCheck for Queen")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Queen check rule queen row++ and column-- with piece between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("Queen",Player1(), QueenEngine())
    ChessBoard.board(5)(5) = Some(piece)
    val movement = Movement(Player1(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(5), RowTo(5))
    When("I invoke isCheck for Queen")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("Queen check rule queen row++ and column--") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("Queen",Player1(), QueenEngine())
    ChessBoard.board(6)(4) = None
    ChessBoard.board(5)(5) = Some(piece)
    val movement = Movement(Player1(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(5), RowTo(5))
    When("I invoke isCheck for Queen")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Queen check rule queen row++ and column++ with piece between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("Queen",Player1(), QueenEngine())
    ChessBoard.board(5)(1) = Some(piece)
    val movement = Movement(Player1(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(1), RowTo(5))
    When("I invoke isCheck for Queen")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("Queen check rule queen row++ and column++") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("Queen",Player1(), QueenEngine())
    ChessBoard.board(6)(2) = None
    ChessBoard.board(5)(1) = Some(piece)
    val movement = Movement(Player1(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(1), RowTo(5))
    When("I invoke isCheck for Queen")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Queen check rule queen row-- and column++ with piece between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("queen",Player2(), QueenEngine())
    ChessBoard.board(3)(0) = Some(piece)
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(0), RowTo(3))
    When("I invoke isCheck for Queen")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("Queen check rule queen row-- and column++") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("queen",Player2(), QueenEngine())
    ChessBoard.board(1)(2) = None
    ChessBoard.board(3)(0) = Some(piece)
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(0), RowTo(3))
    When("I invoke isCheck for Queen")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Queen check rule, queen is in front of king with another piece in between vertical/horizontal") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("queen",Player2(), QueenEngine())
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(3), RowTo(4))
    ChessBoard.board(4)(3) = Some(piece)
    When("I invoke isCheck for Rook")
    val isCheck = piece.isCheck(movement)
    Then("The movement is not check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("Queen check rule queen is in front of king without any other piece in between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("queen",Player2(), QueenEngine())
    ChessBoard.board(1)(3) = None
    ChessBoard.board(4)(3) = Some(piece)
    val movement = Movement(Player2(), 1, ColumnFrom(3), RowFrom(4), ColumnTo(3), RowTo(4))
    When("I invoke isCheck for Rook")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

}
