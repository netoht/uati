package io.github.netoht.uati.aggregator.service

import io.github.netoht.uati.aggregator.repository.{TweetRepository, TwitterProfileRepository}

/**
  * Created by neto on 25/01/17.
  */
class AggregatorService {

  val tweetRepo = new TweetRepository()
  val profileRepo = new TwitterProfileRepository()

  def topTwitterProfiles() = {
    val profiles = profileRepo.getProfilesTop(5)
    profileRepo.saveTopProfilesOnCache(profiles)
  }

  def hashtagByLanguage() = {
    val hashtags = tweetRepo.getTotalHashtagsFromLanguage("pt")
    tweetRepo.saveHashtagsOnCache(hashtags)
  }

  def tweetsByHour() = {
    val tweets = tweetRepo.getTotalByHour()
    tweetRepo.saveTweetsOnCache(tweets)
  }
}
