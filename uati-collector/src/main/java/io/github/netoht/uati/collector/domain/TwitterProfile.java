package io.github.netoht.uati.collector.domain;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by neto on 23/01/17.
 */
@Table(keyspace = "ks_twitter", name = "twitter_profile")
public class TwitterProfile {

    @PartitionKey
    @Column(name = "screen_name")
    private String screenName;

    @Column(name  = "followers_count")
    private Integer followersCount;

    public String getScreenName() {
        return screenName;
    }

    public TwitterProfile setScreenName(String screenName) {
        this.screenName = screenName;
        return this;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public TwitterProfile setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TwitterProfile that = (TwitterProfile) o;

        return new EqualsBuilder()
                .append(screenName, that.screenName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(screenName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "TwitterProfile{" +
                "screenName='" + screenName + '\'' +
                ", followersCount=" + followersCount +
                '}';
    }
}
