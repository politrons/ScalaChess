package com.politrons.engine

import com.politrons.model.ChessDomain.{Movement, Piece, Player1}
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen}

class ChessEngineBishopSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll {

  feature("Chess engine Bishop") {

    scenario("Bishop rule validation move horizontal 1 and vertical 1 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Bishop")
      val result = engine.isValidMove(Piece("Bishop"), Movement(Player1(), 1, 1, 2, 2))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Bishop rule validation move horizontal 5 and vertical 5 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Bishop")
      val result = engine.isValidMove(Piece("Bishop"), Movement(Player1(), 1, 1, 5, 5))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Bishop rule validation move negative horizontal 5 and vertical 5 succeed") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Bishop")
      val result = engine.isValidMove(Piece("Bishop"), Movement(Player1(), 5, 5, 1, 1))
      Then("The movement is ok")
      assert(result)
    }

    scenario("Bishop rule validation move horizontal 1 and vertical 2 wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Bishop")
      val result = engine.isValidMove(Piece("Bishop"), Movement(Player1(), 1, 1, 3, 4))
      Then("The movement is wrong")
      assert(!result)
    }

    scenario("Bishop rule validation move negative horizontal 1 and vertical 2 wrong") {
      Given("Chess engine instance")
      val engine = ChessEngine()
      When("I invoke isValidateMove for Bishop")
      val result = engine.isValidMove(Piece("Bishop"), Movement(Player1(), 5, 5, 3, 4))
      Then("The movement is wrong")
      assert(!result)
    }

  }

}
