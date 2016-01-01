package com.github.brycemcd.bandit_algorithms

// NOTE: to check how well the algo does, it's also important to
// change the values in EpsilonGreedyResults.rewards to vary the
// variance in the values and make more valuable arms are chosen 
// more frequently than lower value arms

import java.io._

object SoftmaxSanityCheck {

  val varyingRewards : Array[(String, Array[Double])] = Array(
    ("high"   -> Array(1.0, 120.0, 8.0, 40.0)),
    ("medium" -> Array(10.0, 12.0, 4.0, 2.0)),
    ("low"    -> Array(3.0, 4.0, 4.0, 2.0))
  )

  def main(args: Array[String]): Unit = {
    val outputFileLocation = "/home/brycemcd/Desktop/softmaxSanityCheck.csv"
    val file = new File(outputFileLocation)
    val bw = new BufferedWriter(new FileWriter(file))

    // header
    bw.write("arm,temp,rewardVariance\n")

    for(i    <- (0 until 10000);
        temp <- (1 until 100 by 2);
        reward <- varyingRewards) {

      val sm = new Softmax(temp, reward._2)
      val arm = sm.selectArm

      bw.write(arm + "," + temp + "," + reward._1 + "\n")
      //println("arm: " + arm + " temp: " + temp, " reward: " + reward._1)
    }

    bw.close()
  }
}
