package io.github.netoht.uati.collector.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import io.github.netoht.uati.collector.domain.Tweet;
import io.github.netoht.uati.collector.domain.TwitterProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by neto on 23/01/17.
 */
@Configuration
public class CassandraConfig {

    @Value("${cassandra.contact-points}")
    private String[] contactPoints;

    @Value("${cassandra.port}")
    private int port;

    @Bean
    public Session session() {
        return Cluster.builder()
                .addContactPoints(contactPoints).withPort(port)
                .build().connect();
    }

    @Bean
    public MappingManager mappingManager() {
        return new MappingManager(session());
    }

    @Bean
    public Mapper<TwitterProfile> mapperTwitterProfile() {
        return mappingManager().mapper(TwitterProfile.class);
    }

    @Bean
    public Mapper<Tweet> mapperTweet() {
        return mappingManager().mapper(Tweet.class);
    }
}
