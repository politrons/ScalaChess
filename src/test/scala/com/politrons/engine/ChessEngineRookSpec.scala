package com.politrons.engine

import com.politrons.model.ChessDomain.{Movement, Piece, Player1}
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen}

class ChessEngineRookSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll {

  feature("Chess engine rook") {

    scenario("Rook rule validation move horizontal succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Rook and horizontal movement")
      val result = engine.isValidateMove(Piece("Rook"), Movement(Player1(), 1, 1, 5, 1))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Rook rule validation move vertical succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Rook and vertical movement")
      val result = engine.isValidateMove(Piece("Rook"), Movement(Player1(), 1, 1, 1, 5))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Rook rule validation move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Rook and vertical and horizontal movement")
      val result = engine.isValidateMove(Piece("Rook"), Movement(Player1(), 1, 1, 5, 5))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("Rook rule validation no move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Rook and vertical and horizontal movement")
      val result = engine.isValidateMove(Piece("Rook"), Movement(Player1(), 1, 1, 1, 1))
      Then("The movement is wrong")
      assert(!result)
    }

  }

}
