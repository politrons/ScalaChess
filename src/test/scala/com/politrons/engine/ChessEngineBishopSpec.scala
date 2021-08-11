package com.politrons.engine

import com.politrons.engine.impl.{BishopEngine, PawnEngine}
import com.politrons.model.ChessDomain._
import com.politrons.model.Piece
import com.politrons.utils.BoardMock.boardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}

class ChessEngineBishopSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    ChessBoard.board = boardMock
  }

  /**
   * MOVEMENT RULES
   * ---------------
   */
  test("Bishop rule validation move horizontal 1 and vertical 1 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    When("I invoke isValidateMove for Bishop")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Bishop rule validation move horizontal 5 and vertical 5 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    When("I invoke isValidateMove for Bishop")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Bishop rule validation move negative horizontal 5 and vertical 5 succeed") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    When("I invoke isValidateMove for Bishop")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Bishop rule validation move horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    When("I invoke isValidateMove for Bishop")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  test("Bishop rule validation move negative horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    When("I invoke isValidateMove for Bishop")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
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
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(3)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  test("Bishop rule validation move diagonal to (2,4) but there's a piece in (2,3) ") {
    Given("Chess engine instance")
    val piece = Piece("Bishop", Player1(), BishopEngine())
    ChessBoard.board(1)(3) = None
    ChessBoard.board(2)(3) = Some(Piece("pawn", Player2(), PawnEngine()))
    When("I invoke isValidateMove for Bishop")
    val result = piece.valid(Movement(Player1(), 1, ColumnFrom(2), RowFrom(0), ColumnTo(4), RowTo(2)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(result.get)
  }

}


