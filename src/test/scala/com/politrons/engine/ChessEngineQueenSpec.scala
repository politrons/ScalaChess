package com.politrons.engine

import com.politrons.model.ChessDomain.{Movement, Piece, Player1}
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen}

class ChessEngineQueenSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll {

  feature("Chess engine queen") {

    scenario("Queen rule validation move horizontal succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen and horizontal movement")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 1, 1, 5, 1))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Queen rule validation move vertical succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen and vertical movement")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 1, 1, 1, 5))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Queen rule validation move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen and vertical and horizontal movement")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 1, 1, 5, 5))
      Then("The movement is wrong")
      assert(result)
    }

    scenario("Queen rule validation no move vertical and horizontal wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen and vertical and horizontal movement")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 1, 1, 1, 1))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("Queen rule validation move horizontal 1 and vertical 1 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 1, 1, 2, 2))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Queen rule validation move horizontal 5 and vertical 5 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 1, 1, 5, 5))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Queen rule validation move negative horizontal 5 and vertical 5 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 5, 5, 1, 1))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Queen rule validation move horizontal 1 and vertical 2 wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 1, 1, 3, 4))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("Queen rule validation move negative horizontal 1 and vertical 2 wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Queen")
      val result = engine.isValidMove(Piece("Queen"), Movement(Player1(), 5, 5, 3, 4))
      Then("The movement is wrong")
      assert(!result)
    }


  }

}
