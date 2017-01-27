package io.github.netoht.uati.collector.domain;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by neto on 23/01/17.
 */
@Table(keyspace = "ks_twitter", name = "tweet")
public class Tweet {

    @PartitionKey
    @Column(name  = "language_code")
    private String languageCode;

    @ClusteringColumn(0)
    @Column(name = "hashtag")
    private String hashtag;

    @ClusteringColumn(1)
    @Column(name = "created_at")
    private Date createdAt;

    @ClusteringColumn(2)
    @Column(name = "hour_of_day")
    private Date hourOfDay;

    @ClusteringColumn(3)
    @Column(name = "twitter_profile")
    private String twitterProfile;

    public Tweet() {
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public Tweet setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        return this;
    }

    public String getHashtag() {
        return hashtag;
    }

    public Tweet setHashtag(String hashtag) {
        this.hashtag = StringUtils.removeFirst(hashtag, "#");
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Tweet setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        this.hourOfDay = DateUtils.truncate(createdAt, Calendar.HOUR);
        return this;
    }

    public Date getHourOfDay() {
        return hourOfDay;
    }

    public String getTwitterProfile() {
        return twitterProfile;
    }

    public Tweet setTwitterProfile(String twitterProfile) {
        this.twitterProfile = twitterProfile;
        return this;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "languageCode='" + languageCode + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", createdAt=" + createdAt +
                ", hourOfDay=" + hourOfDay +
                ", twitterProfile='" + twitterProfile + '\'' +
                '}';
    }
}
