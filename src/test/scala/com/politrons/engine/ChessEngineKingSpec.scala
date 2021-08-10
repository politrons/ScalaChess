package com.politrons.engine

import com.politrons.model.ChessDomain.{Movement, Piece, Player1}
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen}

class ChessEngineKingSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll {

  feature("Chess engine King") {

    scenario("King rule validation move horizontal 1 and vertical 1 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for King")
      val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, 1, 2, 2))
      Then("The movement is ok")
      assert(result)
    }

    scenario("King rule validation move horizontal 1") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for King")
      val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, 1, 2, 1))
      Then("The movement is ok")
      assert(result)
    }

    scenario("King rule validation move vertical 1") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for King")
      val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, 1, 1, 2))
      Then("The movement is ok")
      assert(result)
    }

    scenario("King rule validation move  negative horizontal 1 and vertical 2 wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for King")
      val result = engine.isValidMove(Piece("King"), Movement(Player1(), 5, 5, 3, 4))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("King rule validation move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for King")
      val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, 1, 3, 6))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("King rule validation no move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for King")
      val result = engine.isValidMove(Piece("King"), Movement(Player1(), 1, 1, 3, 1))
      Then("The movement is wrong")
      assert(!result)
    }

  }

}
