package com.politrons.engine

import com.politrons.model.ChessDomain
import com.politrons.model.ChessDomain.Movement

import scala.util.Try

/**
 * Contract of all Rule Engine of the game
 */
trait PieceEngine {

  def isValid(movement: ChessDomain.Movement): Try[Unit]

  def isValidNextMove(movement: Movement): Boolean = {
    (movement.columnTo.value >= 0 || movement.columnTo.value <= 7) ||
      (movement.rowTo.value >= 0 || movement.rowTo.value <= 7)
  }

  def diffMovements(movement: Movement): (Int, Int) = {
    val vertical = Math.abs(movement.rowFrom.value - movement.rowTo.value)
    val horizontal = Math.abs(movement.columnFrom.value - movement.columnTo.value)
    (vertical, horizontal)
  }
}
