package com.politrons.engine

import com.politrons.model.ChessDomain._
import com.politrons.view.ChessBoard
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}

class ChessEngineBishopSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterAll {

  test("Bishop rule validation move horizontal 1 and vertical 1 succeed") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Bishop")
    val result = engine.isValidMovement(Piece("Bishop"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Bishop rule validation move horizontal 5 and vertical 5 succeed") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Bishop")
    val result = engine.isValidMovement(Piece("Bishop"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Bishop rule validation move negative horizontal 5 and vertical 5 succeed") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Bishop")
    val result = engine.isValidMovement(Piece("Bishop"), Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(1), RowTo(1)))
    Then("The movement is ok")
    assert(result.isSuccess)
    assert(result.get)
  }

  test("Bishop rule validation move horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Bishop")
    val result = engine.isValidMovement(Piece("Bishop"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

  test("Bishop rule validation move negative horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val engine = ChessEngine(ChessBoard())
    When("I invoke isValidateMove for Bishop")
    val result = engine.isValidMovement(Piece("Bishop"), Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(result.isSuccess)
    assert(!result.get)
  }

}


