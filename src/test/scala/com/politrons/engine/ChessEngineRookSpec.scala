package com.politrons.engine

import com.politrons.engine.impl.{KingEngine, PawnEngine, RookEngine}
import com.politrons.model.ChessDomain._
import com.politrons.model.Piece
import com.politrons.utils.BoardMock
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}

class ChessEngineRookSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    ChessBoard.board = BoardMock.getBoardMock
  }

  /**
   * MOVEMENT RULES
   * ---------------
   */
  test("Rook rule validation move horizontal succeed") {
    Given("Chess engine instance")
    val piece = Piece("Rook",Player1(), RookEngine())
    When("I invoke isValid for Rook and horizontal movement")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(3), RowFrom(3), ColumnTo(5), RowTo(3)))
    Then("The movement is ok")
    assert(result.isSuccess)

  }

  test("Rook rule validation move vertical succeed") {
    Given("Chess engine instance")
    val piece = Piece("Rook",Player1(), RookEngine())
    When("I invoke isValid for Rook and vertical movement")
    val result = piece.isValid( Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(5)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Rook rule validation move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Rook",Player1(), RookEngine())
    When("I invoke isValid for Rook and vertical and horizontal movement")
    val result = piece.isValid( Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  test("Rook rule validation no move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val piece = Piece("Rook",Player1(), RookEngine())
    When("I invoke isValid for Rook and vertical and horizontal movement")
    val result = piece.isValid( Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  /**
   * PATH RULES
   * ----------
   */
  test("Rook path rule other piece at the end of path not mine") {
    Given("Chess engine instance")
    val piece = Piece("Rook",Player1(), RookEngine())
    ChessBoard.board(2)(1) = Some(Piece("pawn", Player2(), PawnEngine()))
    When("I invoke isValid for Rook")
    val result = piece.isValid( Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
  }

  test("Rook path rule  mine piece at the end of path") {
    Given("Chess engine instance")
    val piece = Piece("Rook",Player2(), RookEngine())
    When("I invoke isValid for Rook")
    val result = piece.isValid(Movement(Player2(), 1, ColumnFrom(7), RowFrom(7), ColumnTo(7), RowTo(6)))
    Then("The movement is wrong")
    assert(result.isFailure)

  }

  test("Rook path rule other piece during the path") {
    Given("Chess engine instance")
    val piece = Piece("Rook",Player1(), RookEngine())
    ChessBoard.board(2)(1) = Some(Piece("pawn",Player2(), PawnEngine()))
    When("I invoke isValid for Rook")
    val result = piece.isValid(Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(3)))
    Then("The movement is wrong")
    assert(result.isFailure)
  }

  /**
   * CHECK RULES
   * ------------
   */
  test("Rook check rule, rook is in front of king with other piece in between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("rook",Player2(), RookEngine())
    val movement = Movement(Player2(), 1, ColumnFrom(4), RowFrom(4), ColumnTo(3), RowTo(4))
    ChessBoard.board(4)(3) = Some(piece)
    When("I invoke isCheck for Rook")
    val isCheck = piece.isCheck(movement)
    Then("The movement is not check")
    assert(isCheck.isSuccess)
    assert(!isCheck.get)
  }

  test("Rook check rule rook is in front of king without any other piece in between") {
    Given("Chess engine instance in piece and clear path to king")
    val piece = Piece("rook",Player2(), RookEngine())
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

