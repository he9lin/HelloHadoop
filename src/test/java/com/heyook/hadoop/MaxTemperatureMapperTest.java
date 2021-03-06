package com.heyook.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.*;

public class MaxTemperatureMapperTest {

  @Test
  public void processesValidRecord() throws IOException, InterruptedException {
    Text value = new Text("0043011990999991950051518004+68750+023550FM-12+0382"
        + "99999V0203201N00261220001CN9999999N9-00111+99999999999");

    new MapDriver<LongWritable, Text, Text, IntWritable>()
        .withMapper(new MaxTemperatureMapper())
        .withInput(new LongWritable(1), value)
        .withOutput(new Text("1950"), new IntWritable(-11)).runTest();
  }

  @Test
  public void ignoresMissingTemperatureRecord() throws IOException,
      InterruptedException {
    Text value = new Text("0043011990999991950051518004+68750+023550FM-12+0382"
        + "99999V0203201N00261220001CN9999999N9+99991+99999999999");

    new MapDriver<LongWritable, Text, Text, IntWritable>()
        .withMapper(new MaxTemperatureMapper())
        .withInput(new LongWritable(1), value).runTest();
  }

  @Test
  public void processesZeroTemperatureRecord() throws IOException,
      InterruptedException {
    Text value = new Text("0067011990999991950051507004+68750+023550FM-12+0382"
        + "99999V0203301N00671220001CN9999999N9+00001+99999999999");

    new MapDriver<LongWritable, Text, Text, IntWritable>()
        .withMapper(new MaxTemperatureMapper())
        .withInput(new LongWritable(1), value)
        .withOutput(new Text("1950"), new IntWritable(0)).runTest();
  }

  @Test
  public void processesPositiveTemperatureRecord() throws IOException,
      InterruptedException {
    Text value = new Text("0043011990999991950051512004+68750+023550FM-12+0382"
        + "99999V0203201N00671220001CN9999999N9+00221+99999999999");

    new MapDriver<LongWritable, Text, Text, IntWritable>()
        .withMapper(new MaxTemperatureMapper())
        .withInput(new LongWritable(1), value)
        .withOutput(new Text("1950"), new IntWritable(22)).runTest();
  }

}
