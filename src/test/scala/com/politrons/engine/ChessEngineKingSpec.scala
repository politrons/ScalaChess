package com.politrons.engine

import com.politrons.model.ChessDomain.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}

class ChessEngineKingSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterAll {

  test("King rule validation move horizontal 1 and vertical 1 succeed") {
    Given("Chess engine instance")
    val engine = ChessEngine()
    When("I invoke isValidateMove for King")
    val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    assert(result)
  }

  test("King rule validation move horizontal 1") {
    Given("Chess engine instance")
    val engine = ChessEngine()
    When("I invoke isValidateMove for King")
    val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(1)))
    Then("The movement is ok")
    assert(result)
  }

  test("King rule validation move vertical 1") {
    Given("Chess engine instance")
    val engine = ChessEngine()
    When("I invoke isValidateMove for King")
    val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(2)))
    Then("The movement is ok")
    assert(result)
  }

  test("King rule validation move  negative horizontal 1 and vertical 2 wrong") {
    Given("Chess engine instance")
    val engine = ChessEngine()
    When("I invoke isValidateMove for King")
    val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
    Then("The movement is wrong")
    assert(!result)
  }

  test("King rule validation move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val engine = ChessEngine()
    When("I invoke isValidateMove for King")
    val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(6)))
    Then("The movement is wrong")
    assert(!result)
  }

  test("King rule validation no move vertical and horizontal wrong") {
    Given("Chess engine instance")
    val engine = ChessEngine()
    When("I invoke isValidateMove for King")
    val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(1)))
    Then("The movement is wrong")
    assert(!result)
  }

}

