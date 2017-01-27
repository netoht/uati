package io.github.netoht.uati.aggregator

import io.github.netoht.uati.aggregator.service.AggregatorService

object UatiAggregatorApplication {

  def main(args: Array[String]): Unit = {
    val aggregator = new AggregatorService()
    aggregator.topTwitterProfiles()
    aggregator.hashtagByLanguage()
    aggregator.tweetsByHour()
  }

}