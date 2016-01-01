package com.github.brycemcd.bandit_algorithms

object Main {
  def main(args: Array[String]): Unit = {
    //EpsilonGreedySanityCheck.sanityCheck(10, 150000,
      //"/home/brycemcd/Desktop/epsilonGreedySanityCheck.txt")
    val sm = new Softmax(2.0, Experiments.rewards)
    println("arm: " + sm.selectArm)
  }
}
