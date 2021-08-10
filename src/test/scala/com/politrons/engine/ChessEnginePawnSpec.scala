package com.politrons.engine

import com.politrons.model.ChessDomain._
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen}

class ChessEnginePawnSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll {

  feature("Chess engine Pawn") {

    scenario("Pawn rule validation move horizontal 0 and vertical 1 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Pawn")
      val result = engine.isValidMove(Piece("Pawn"), Movement(Player1(), 1, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(2)))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Pawn rule validation move horizontal 0 and vertical 2 with first move succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Pawn")
      val result = engine.isValidMove(Piece("Pawn"), Movement(Player2(), 2, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(3)))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Pawn rule validation move vertical 2 and horizontal 0 with not first move wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Pawn")
      val result = engine.isValidMove(Piece("Pawn"), Movement(Player1(),10, ColumnFrom(1), RowFrom(1), ColumnTo(1), RowTo(3)))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("Pawn rule validation move vertical 1 and horizontal 1 wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Pawn")
      val result = engine.isValidMove(Piece("Pawn"), Movement(Player1(),1, ColumnFrom(1), RowFrom(1), ColumnTo(2), RowTo(2)))
      Then("The movement is wrong")
      assert(!result)
    }

  }

}
