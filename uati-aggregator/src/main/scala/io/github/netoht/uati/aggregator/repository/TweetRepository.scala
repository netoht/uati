package io.github.netoht.uati.aggregator.repository

import io.github.netoht.uati.aggregator.domain.{Hashtag, Tweet}
import org.apache.spark.sql.functions.desc
import org.json4s.jackson.Serialization.write

/**
  * Created by neto on 25/01/17.
  */
class TweetRepository extends BaseRepository("ks_twitter", "tweet") {

  def saveHashtagsOnCache(hashtags: Array[Hashtag]) = {
    val cacheHashtagKey = "hashtag_pt"
    jedis.set(cacheHashtagKey, write(hashtags))
  }

  def saveTweetsOnCache(tweets: Array[Tweet]) = {
    val cacheTweetsKey = "tweet_by_hour"
    jedis.set(cacheTweetsKey, write(tweets))
  }

  def getTotalHashtagsFromLanguage(languageCode: String) = {
    this.read().load()
      .select("language_code", "hashtag")
      .filter(s"language_code = '${languageCode}'")
      .groupBy("hashtag")
      .count
      .orderBy(desc("count"))
      .collect()
      .map(row => new Hashtag(row.getString(0), row.getLong(1)))
  }

  def getTotalByHour() = {
    this.read().load()
      .select("hour_of_day")
      .groupBy("hour_of_day")
      .count
      .orderBy(desc("count"))
      .collect()
      .map(row => new Tweet(row.getTimestamp(0).getTime, row.getLong(1)))
  }
}
