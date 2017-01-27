CREATE KEYSPACE IF NOT EXISTS ks_twitter WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': 1 };

USE ks_twitter;

DROP TABLE IF EXISTS twitter_profile;
CREATE TABLE IF NOT EXISTS twitter_profile (
	screen_name text PRIMARY KEY,
	followers_count int
);

DROP TABLE IF EXISTS tweet;
CREATE TABLE IF NOT EXISTS tweet (
	language_code text,
	hashtag text,
	created_at timestamp,
	hour_of_day timestamp,
	twitter_profile text,
	PRIMARY KEY (language_code, hashtag, created_at, hour_of_day, twitter_profile)
);

TRUNCATE twitter_profile;
TRUNCATE tweet;