package com.politrons.engine

import com.politrons.model.ChessDomain.{ColumnFrom, ColumnTo, Movement, Piece, Player1, RowFrom, RowTo}
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen}

class ChessEngineQueenSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll {

  feature("Chess engine queen") {

    scenario("Queen rule validation move horizontal succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen and horizontal movement")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(),1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(1)))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Queen rule validation move vertical succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen and vertical movement")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(),1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(5)))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Queen rule validation move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen and vertical and horizontal movement")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 1,ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
      Then("The movement is wrong")
      assert(result)
    }

    scenario("Queen rule validation no move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen and vertical and horizontal movement")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(1)))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("Queen rule validation move horizontal 1 and vertical 1 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(),1,  ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Queen rule validation move horizontal 5 and vertical 5 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(),1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Queen rule validation move negative horizontal 5 and vertical 5 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 1,ColumnFrom(5), RowFrom(5), ColumnTo(1), RowTo(1)))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Queen rule validation move horizontal 1 and vertical 2 wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(),1, ColumnFrom(1), RowFrom(1), ColumnTo(3), RowTo(4)))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("Queen rule validation move negative horizontal 1 and vertical 2 wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(),1, ColumnFrom(5), RowFrom(5), ColumnTo(3), RowTo(4)))
      Then("The movement is wrong")
      assert(!result)
    }


  }

}
