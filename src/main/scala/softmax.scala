package com.github.brycemcd.bandit_algorithms

import scala.util.Random

object Softmax {

  def main(args: Array[String]) = {
    val sm = new Softmax(2.0, EpsilonGreedyResults.rewards)
    println("arm: " + sm.selectArm)
  }

  def categoricalDraw(probs: Array[Double]) : Double = {
    val z = Random.nextDouble
    var i = 0
    probs.foldLeft(0.0) { (r,c) =>
      if(r > z) {
        return i
      } else {
        i += 1
        r + c
      }
    }

    return (probs.length).toDouble
  }
}

class Softmax (
  val temperature : Double,
  val rewards : Array[Double]
) {

  private def calcNormalizedRewards = {
    val z = rewards.map { v =>
      Math.exp(v / temperature)
    }.sum

    rewards.map { v =>
      Math.exp(v / temperature) / z
    }
  }

  def selectArm = {
    Softmax.categoricalDraw(calcNormalizedRewards)
  }
}
