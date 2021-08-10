package com.politrons.engine

import com.politrons.model.ChessDomain._
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}

class ChessEngineRookSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterAll {

  test("Rook rule validation move horizontal succeed") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Rook and horizontal movement")
    val result = engine.isValidMovement(Piece("Rook"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(1)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)

  }

  test("Rook rule validation move vertical succeed") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Rook and vertical movement")
    val result = engine.isValidMovement(Piece("Rook"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(5)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Rook rule validation move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Rook and vertical and horizontal movement")
    val result = engine.isValidMovement(Piece("Rook"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  test("Rook rule validation no move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Rook and vertical and horizontal movement")
    val result = engine.isValidMovement(Piece("Rook"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

}

