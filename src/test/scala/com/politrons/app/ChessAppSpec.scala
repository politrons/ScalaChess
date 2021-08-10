package com.politrons.app

import com.politrons.model.ChessDomain.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}

class ChessAppSpec extends AnyFunSuite with GivenWhenThen with BeforeAndAfterAll {


  test("Check that all pieces are on the board when we start a game") {
    Given("Chess board")
    val board = ChessApp.board
    When("I check for ")
    //      val result = engine.isValidMove(Piece("Bishop"), Movement(Player1(),1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
    Then("The movement is ok")
    //      assert(result)
  }
}

