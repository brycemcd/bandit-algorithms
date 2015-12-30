package com.github.brycemcd.bandit_algorithms

import java.io._
import scala.util.Random

object EpsilonGreedySanityCheck {

  def sanityCheck(numRuns: Int,
    iterationsPerRun : Int,
    outputFileLocation: String) = {

    val file = new File(outputFileLocation)
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
