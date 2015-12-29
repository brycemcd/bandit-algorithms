package com.github.brycemcd.bandit_algorithms

import scala.util.Random

object EpsilonGreedy {

  // a.k.a. indMax from the book
  def highestKnownRewardIndex : Int = {
    val highestReward = EpsilonGreedyResults.rewards.max
    EpsilonGreedyResults.rewards.indexOf(highestReward)
  }

  // prd = Pseudo Random Double
  private def prd : Double = {
    val randomNumber = Random.nextDouble()
    println("random number: " + randomNumber )
    randomNumber
  }

  def main(args: Array[String]): Unit = {
    val eg : EpsilonGreedy = new EpsilonGreedy(0.9, 5)
    eg.printAttrs
    eg.returnArmForExplorationOrExperimentation
  }
}

// this is meant to stub the "update" method from the book
object EpsilonGreedyResults {

  // TODO: This should query some sort of data store and calculate how many
  // times each arm has been explored and the total value of each arm. For
  // now, it's simply a stubbed method returning the expected return value
  def counts : Array[Int] = {
    Array(1, 4, 2, 20)
  }

  def rewards : Array[Double] = {
    Array(1.0, 12.0, 19.0, 2.0)
  }
}

// params:
// epsilon = a double to indicate the frequency of exploration
// nArms   = the number of different experiments to attempt

class EpsilonGreedy (
  val epsilon : Double,
  val nArms   : Int
) {


  def printAttrs = {
    println("----vals")
    println(epsilon)
    println(nArms)
    println("----vals")
  }

  def returnArmForExplorationOrExperimentation : Int = {
    if (EpsilonGreedy.prd > epsilon) {
      println("exploiting best known choice: " + EpsilonGreedy.highestKnownRewardIndex)
      EpsilonGreedy.highestKnownRewardIndex
    } else {
      println("exploring options")
      randomArm
    }
  }

  def randomArm() : Int = {
    val randomIndex = Random.nextInt( EpsilonGreedyResults.rewards.length + 1 )
    println("random index: " + randomIndex )
    randomIndex
  }
}
