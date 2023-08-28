import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.functions._

val spark = SparkSession.builder
  .appName("ImpClickJoinApp")
  .master("local[*]")
  .getOrCreate()

import spark.implicits._

val clickStream = spark.readStream
  .format("kafka")
  .option("kafka.bootstrap.servers", "localhost:9092")
  .option("subscribe", "clickTopic")
  .load()

val impStream = spark.readStream
  .format("kafka")
  .option("kafka.bootstrap.servers", "localhost:9092")
  .option("subscribe", "impTopic")
  .load()

val validWindow = 60 * 1000 // 1분

val joinedStream = clickStream.as("click")
  .join(impStream.as("imp"), expr("""
    click.timestamp - imp.timestamp <= validWindow
  """), "leftOuter")

joinedStream.writeStream
  .outputMode("append")
  .format("parquet")
  .option("path", "/path/to/output/directory")
  .option("checkpointLocation", "/path/to/checkpoint/directory")
  .trigger(Trigger.ProcessingTime("1 minute"))
  .start()

joinedStream.selectExpr("CAST(key AS STRING)", "to_json(struct(*)) AS value")
  .writeStream
  .format("kafka")
  .option("kafka.bootstrap.servers", "localhost:9092")
  .option("topic", "joinedOutputTopic")
  .option("checkpointLocation", "/path/to/checkpoint/directory")
  .start()


spark.streams.awaitAnyTermination()
