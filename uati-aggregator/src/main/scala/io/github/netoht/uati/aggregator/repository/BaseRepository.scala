package io.github.netoht.uati.aggregator.repository

import io.github.netoht.uati.aggregator.config.AppConfig
import org.apache.spark.sql.DataFrameReader
import org.json4s.DefaultFormats

/**
  * Created by neto on 25/01/17.
  */
class BaseRepository(val keyspace: String, val tableName: String) {

  implicit val formats = DefaultFormats

  val session = AppConfig.session()
  val jedis = AppConfig.jedis()

  def read(): DataFrameReader = {
    session.read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("keyspace" -> keyspace, "table" -> tableName))
  }
}
