package com.politrons.engine

import com.politrons.model.ChessDomain._
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}

class ChessEngineKnightSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterAll {

  test("Knight rule validation move horizontal 2 and vertical 1 succeed") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Knight")
    val result = engine.isValidMovement(Piece("Knight"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Knight rule validation move horizontal 1 and vertical 2 succeed") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Knight")
    val result = engine.isValidMovement(Piece("Knight"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(3)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Knight rule validation move  negative horizontal 1 and vertical 2 succeed") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Knight")
    val result = engine.isValidMovement(Piece("Knight"), Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Knight rule validation move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Knight")
    val result = engine.isValidMovement(Piece("Knight"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(6)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  test("Knight rule validation no move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Knight")
    val result = engine.isValidMovement(Piece("Knight"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(1)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

}
