package io.github.netoht.uati.aggregator.config

import org.apache.spark.sql.SparkSession
import redis.clients.jedis.Jedis

/**
  * Created by neto on 25/01/17.
  */
object AppConfig {

  def session(): SparkSession = {
    SparkSession.builder
      .config("spark.app.name", "uati-aggregator")
      .config("spark.master", "local[4]")
      .config("spark.cassandra.connection.host", "127.0.0.1")
      .getOrCreate()
  }

  def jedis(): Jedis = {
    new Jedis("localhost")
  }
}
