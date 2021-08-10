package com.politrons.engine

import com.politrons.model.ChessDomain.{Movement, Piece, Player1}
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen}

class ChessEngineKnightSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll {

  feature("Chess engine Knight") {

    scenario("Knight rule validation move horizontal 2 and vertical 1 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Knight")
      val result = engine.isValidateMove(Piece("Knight"), Movement(Player1(), 1, 1, 3, 2))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Knight rule validation move horizontal 1 and vertical 2 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Knight")
      val result = engine.isValidateMove(Piece("Knight"), Movement(Player1(), 1, 1, 2, 3))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Knight rule validation move  negative horizontal 1 and vertical 2 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Knight")
      val result = engine.isValidateMove(Piece("Knight"), Movement(Player1(), 5, 5, 3, 4))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Knight rule validation move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Knight")
      val result = engine.isValidateMove(Piece("Knight"), Movement(Player1(), 1, 1, 3, 6))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("Knight rule validation no move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Knight")
      val result = engine.isValidateMove(Piece("Knight"), Movement(Player1(), 1, 1, 3, 1))
      Then("The movement is wrong")
      assert(!result)
    }

  }

}
