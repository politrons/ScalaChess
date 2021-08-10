package com.politrons.engine

import com.politrons.model.ChessDomain.{ColumnFrom, ColumnTo, Movement, Piece, Player1, RowFrom, RowTo}
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen}

class ChessEngineRookSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll {

  feature("Chess engine rook") {

    scenario("Rook rule validation move horizontal succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Rook and horizontal movement")
      val result = engine.isValidMove(Piece("Rook"), Movement(Player1(),1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(1)))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Rook rule validation move vertical succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Rook and vertical movement")
      val result = engine.isValidMove(Piece("Rook"), Movement(Player1(),1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(5)))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Rook rule validation move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Rook and vertical and horizontal movement")
      val result = engine.isValidMove(Piece("Rook"), Movement(Player1(),1, ColumnFrom(1), RowFrom(1), ColumnTo(5), RowTo(5)))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("Rook rule validation no move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Rook and vertical and horizontal movement")
      val result = engine.isValidMove(Piece("Rook"), Movement(Player1(),1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(1)))
      Then("The movement is wrong")
      assert(!result)
    }

  }

}
