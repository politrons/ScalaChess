package com.politrons.engine

import com.politrons.engine.impl.{BishopEngine, PawnEngine}
import com.politrons.model.ChessDomain._
import com.politrons.model.Piece
import com.politrons.utils.BoardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

class ChessEngineBishopSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterEach {


  override def beforeEach(): Unit = {
    ChessBoard.board = BoardMock.getBoardMock
  }

  /**
   * MOVEMENT RULES
   * ---------------
   */
  test("Bishop rule validation move horizontal 1 and vertical 1 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    When("I invoke isValidateMove for Bishop")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Bishop rule validation move horizontal 5 and vertical 5 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    When("I invoke isValidateMove for Bishop")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Bishop rule validation move negative horizontal 5 and vertical 5 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    When("I invoke isValidateMove for Bishop")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Bishop rule validation move horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    When("I invoke isValidateMove for Bishop")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  test("Bishop rule validation move negative horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    When("I invoke isValidateMove for Bishop")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  /**
   * PATH RULES
   * ----------
   */

  test("Bishop rule validation move diagonal to (3,3) but there's a piece in (2,2) ") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    ChessBoard.board(2)(2) = Some(Piece("pawn", Player2(), PawnEngine()))
    When("I invoke isValidateMove for Bishop")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(3)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  test("Bishop rule validation move diagonal to (2,4) but there's a piece in (2,3) ") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    ChessBoard.board(1)(3) = None
    ChessBoard.board(2)(3) = Some(Piece("pawn", Player2(), PawnEngine()))
    When("I invoke isValidateMove for Bishop")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(2), RowFrom(0), ColumnTo(4), RowTo(2)))
    Then("The movement is wrong")
    assert(result.isSuccess)
  }

  /**
   * CHECK RULES
   * ------------
   */
  test("Bishop check rule, rook is in front of king with other piece in between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("bishop",Player2(), BishopEngine())
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(6), RowTo(3))
    ChessBoard.board(3)(6) = Some(piece)
    When("I invoke isCheck for Bishop")
    val isCheck = piece.isCheck(movement)
    Then("The movement is not check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("Bishop check rule bishop row-- and column--") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("bishop",Player2(), BishopEngine())
    ChessBoard.board(1)(4) = None
    ChessBoard.board(3)(6) = Some(piece)
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(6), RowTo(3))
    When("I invoke isCheck for Bishop")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Bishop check rule bishop row++ and column-- with piece between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("Bishop",Player1(), BishopEngine())
    ChessBoard.board(5)(5) = Some(piece)
    val movement = Movement(Player1(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(5), RowTo(5))
    When("I invoke isCheck for Bishop")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("Bishop check rule bishop row++ and column--") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("Bishop",Player1(), BishopEngine())
    ChessBoard.board(6)(4) = None
    ChessBoard.board(5)(5) = Some(piece)
    val movement = Movement(Player1(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(5), RowTo(5))
    When("I invoke isCheck for Bishop")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Bishop check rule bishop row++ and column++ with piece between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("Bishop",Player1(), BishopEngine())
    ChessBoard.board(5)(1) = Some(piece)
    val movement = Movement(Player1(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(1), RowTo(5))
    When("I invoke isCheck for Bishop")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("Bishop check rule bishop row++ and column++") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("Bishop",Player1(), BishopEngine())
    ChessBoard.board(6)(2) = None
    ChessBoard.board(5)(1) = Some(piece)
    val movement = Movement(Player1(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(1), RowTo(5))
    When("I invoke isCheck for Bishop")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Bishop check rule bishop row-- and column++ with piece between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("bishop",Player2(), BishopEngine())
    ChessBoard.board(3)(0) = Some(piece)
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(0), RowTo(3))
    When("I invoke isCheck for Bishop")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("Bishop check rule bishop row-- and column++") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("bishop",Player2(), BishopEngine())
    ChessBoard.board(1)(2) = None
    ChessBoard.board(3)(0) = Some(piece)
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(0), RowTo(3))
    When("I invoke isCheck for Bishop")
    val isCheck = piece.isCheck(movement)
    Then("The movement is check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

}


