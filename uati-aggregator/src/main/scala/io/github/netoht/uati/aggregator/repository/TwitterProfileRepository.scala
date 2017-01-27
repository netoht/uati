package io.github.netoht.uati.aggregator.repository

import io.github.netoht.uati.aggregator.domain.TwitterProfile
import org.apache.spark.sql.functions._
import org.json4s.jackson.Serialization.write

/**
  * Created by neto on 25/01/17.
  */
class TwitterProfileRepository extends BaseRepository("ks_twitter", "twitter_profile") {

  def saveTopProfilesOnCache(profiles: Array[TwitterProfile]) = {
    val cacheKey = "top_twitter_profile"
    jedis.set(cacheKey, write(profiles))
  }

  def getProfilesTop(total: Int): Array[TwitterProfile] = {
    this.read().load()
      .select("screen_name", "followers_count")
      .sort(desc("followers_count")).limit(total)
      .collect()
      .map(row => new TwitterProfile(row.getString(0), row.getInt(1)))
  }
}
