package io.github.netoht.uati.collector.service;

import com.datastax.driver.mapping.Mapper;
import io.github.netoht.uati.collector.domain.Tweet;
import io.github.netoht.uati.collector.domain.TwitterProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

/**
 * Created by neto on 23/01/17.
 */
@Service
public class CollectService {

    private final Logger LOGGER = LoggerFactory.getLogger(CollectService.class);

    @Autowired
    private Twitter twitter;

    @Autowired
    private Mapper<TwitterProfile> mapperProfile;

    @Autowired
    private Mapper<Tweet> mapperTweet;

    private static final int TWEETS_LIMIT = 100;

    public void collectTweetsFrom(final String hashtag) {

        SearchResults list = twitter.searchOperations().search(hashtag, TWEETS_LIMIT);

        list.getTweets().forEach(tweet -> {
            TwitterProfile profile = new TwitterProfile()
                    .setScreenName(tweet.getUser().getScreenName())
                    .setFollowersCount(tweet.getUser().getFollowersCount());
            mapperProfile.save(profile);

            Tweet ntweet = new Tweet()
                    .setLanguageCode(tweet.getLanguageCode())
                    .setHashtag(hashtag)
                    .setCreatedAt(tweet.getCreatedAt())
                    .setTwitterProfile(tweet.getFromUser());
            mapperTweet.save(ntweet);

            LOGGER.debug("{}, {}", profile, ntweet);
        });

        LOGGER.info("HASHTAG: {}, TOTAL: {}", hashtag, list.getTweets().size());
    }
}
