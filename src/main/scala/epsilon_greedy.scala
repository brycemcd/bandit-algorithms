package com.github.brycemcd.bandit_algorithms

import scala.util.Random

object EpsilonGreedy {

  // a.k.a. indMax from the book
  def highestKnownRewardIndex : Int = {
    val highestReward = Experiments.rewards.max
    Experiments.rewards.indexOf(highestReward)
  }

  // prd = Pseudo Random Double
  private def prd : Double = {
    val randomNumber = Random.nextDouble()
    randomNumber
  }
}

// params:
// epsilon = a double to indicate the frequency of exploration
// nArms   = the number of different experiments to attempt

class EpsilonGreedy (
  val epsilon : Double,
  val nArms   : Int
) {


  def returnArmForExplorationOrExperimentation() : Tuple3[String, Int, Double] = {
    val numChosen = EpsilonGreedy.prd
    if (numChosen > epsilon) {
      ("exploit", EpsilonGreedy.highestKnownRewardIndex, numChosen)
    } else {
      ("explore", randomArm, numChosen)
    }
  }

  def randomArm() : Int = {
    val randomIndex = Random.nextInt( Experiments.rewards.length + 1 )
    randomIndex
  }
}
