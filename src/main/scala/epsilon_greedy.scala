package com.github.brycemcd.bandit_algorithms

import scala.util.Random

// this is meant to stub the "update" method from the book
object EpsilonGreedyResults {

  // TODO: This should query some sort of data store and calculate how many
  // times each arm has been explored and the total value of each arm. For
  // now, it's simply a stubbed method returning the expected return value
  def counts : Array[Int] = {
    Array(1, 4, 2, 20)
  }

  def rewards : Array[Double] = {
    Array(1.0, 12.0, 8.0, 2.0)
  }
}

object EpsilonGreedy {

  // a.k.a. indMax from the book
  def highestKnownRewardIndex : Int = {
    val highestReward = EpsilonGreedyResults.rewards.max
    EpsilonGreedyResults.rewards.indexOf(highestReward)
  }

  // prd = Pseudo Random Double
  private def prd : Double = {
    val randomNumber = Random.nextDouble()
    randomNumber
  }

  def main(args: Array[String]): Unit = {
    //val eg : EpsilonGreedy = new EpsilonGreedy(0.9, 5)
    //eg.returnArmForExplorationOrExperimentation

    EpsilonGreedySanityCheck.sanityCheck(10, 150000,
      "/home/brycemcd/Desktop/epsilonGreedySanityCheck.txt")
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
    val randomIndex = Random.nextInt( EpsilonGreedyResults.rewards.length + 1 )
    randomIndex
  }
}
