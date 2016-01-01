package com.github.brycemcd.bandit_algorithms

object Experiments {
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

