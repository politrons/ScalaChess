package com.politrons.engine

import com.politrons.engine.impl.{BishopEngine, PawnEngine}
import com.politrons.model.ChessDomain._
import com.politrons.model.Piece
import com.politrons.utils.BoardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

class ChessEnginePawnSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    ChessBoard.board = BoardMock.getBoardMock
  }

  /**
   * MOVEMENT RULES
   * ---------------
   */
  test("Pawn rule validation move horizontal 0 and vertical 1 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Pawn",Player1(), PawnEngine())
    When("I invoke isValidateMove for Pawn")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Pawn rule validation move horizontal 0 and vertical 2 with first move succeed") {
    Given("Chess engine instance")
    val piece = Piece("Pawn",Player1(), PawnEngine())
    When("I invoke isValidateMove for Pawn")
    val result = piece.isValid(Movement(Player2(), 2, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(3)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Pawn rule validation move vertical 2 and horizontal 0 with not first move wrong") {
    Given("Chess engine instance")
    val piece = Piece("Pawn", Player1(),PawnEngine())
    When("I invoke isValidateMove for Pawn")
    val result = piece.isValid(Movement(Player1(), 10, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(3)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  test("Pawn rule validation move vertical 1 and horizontal 1 wrong") {
    Given("Chess engine instance")
    val piece = Piece("Pawn",Player1(), PawnEngine())
    When("I invoke isValidateMove for Pawn")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  /**
   * PATH RULES
   * ----------
   */
  test("Pawn path rule other piece during the path") {
    Given("Chess engine instance")
    val piece = Piece("Pawn",Player1(), PawnEngine())
    ChessBoard.board(2)(1) = Some(Piece("pawn",Player2(), PawnEngine()))
    When("I invoke isValidateMove for Pawn")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(2)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  /**
   * CHECK RULES
   * ------------
   */
  test("Pawn check rule, pawn 1,4") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("pawn",Player2(), PawnEngine())
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(4), RowTo(1))
    ChessBoard.board(1)(4) = Some(piece)
    When("I invoke isCheck for Pawn")
    val isCheck = piece.isCheck(movement)
    Then("The movement is not check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Pawn check rule, pawn 1,2") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("pawn",Player2(), PawnEngine())
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(2), RowTo(1))
    ChessBoard.board(1)(2) = Some(piece)
    When("I invoke isCheck for Pawn")
    val isCheck = piece.isCheck(movement)
    Then("The movement is not check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Pawn check rule, pawn 6,4") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("Pawn",Player1(), PawnEngine())
    val movement = Movement(Player1(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(4), RowTo(6))
    ChessBoard.board(6)(4) = Some(piece)
    When("I invoke isCheck for Pawn")
    val isCheck = piece.isCheck(movement)
    Then("The movement is not check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }

  test("Pawn check rule, pawn 6,2") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("Pawn",Player1(), PawnEngine())
    val movement = Movement(Player1(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(2), RowTo(6))
    ChessBoard.board(6)(2) = Some(piece)
    When("I invoke isCheck for Pawn")
    val isCheck = piece.isCheck(movement)
    Then("The movement is not check")
    assert(isCheck.isSuccess)
    assert(isCheck.get)
  }


}

