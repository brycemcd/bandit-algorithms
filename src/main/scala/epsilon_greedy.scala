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
    //println("random number: " + randomNumber )
    randomNumber
  }

  def main(args: Array[String]): Unit = {
    //val eg : EpsilonGreedy = new EpsilonGreedy(0.9, 5)
    //eg.returnArmForExplorationOrExperimentation

    sanityCheck(20, 5000)
  }

  // TODO: this should be in src/test/scala/epsilon_greedy but I don't
  // know how to do that
  import java.io._
  def sanityCheck(numRuns: Int, iterationsPerRun : Int) = {
    val file = new File("/home/brycemcd/Desktop/epsilonGreedySanityCheck.txt")
    // NOTE: file should take the shape:
    // test #, iteration #, epsilon value, nArms value, explore or exploit, index chosen, random number to compare to epsilon

    val bw = new BufferedWriter(new FileWriter(file))


    for(i <- (0 until numRuns)) {
      val randEps = Random.nextDouble
      val randArms = Random.nextInt(20)
      val eg : EpsilonGreedy = new EpsilonGreedy(randEps, randArms)

      for(g <- (0 until iterationsPerRun)) {
        val chosen = eg.returnArmForExplorationOrExperimentation
        bw.write(i + "," + g + "," + randEps + "," + randArms + "," + chosen._1  + "," + chosen._2 + "," + chosen._3 + "\n")
      }
      println(s"test $i complete")
    }

    bw.close()
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
    println("  ----vals")
    println(epsilon)
    println(nArms)
    println("  ----vals")
  }

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
    //println("random index: " + randomIndex )
    randomIndex
  }
}
