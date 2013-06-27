## Sample projects from Hadoop book

Some commands

```
# Running local
hadoop com.heyook.hadoop.MaxTemperatureDriver \
  -conf config/hadoop-local.xml input/ncdc/micro output

# Running pseduo-cluster
hadoop jar hello-hadoop.jar com.heyook.hadoop.MaxTemperatureDriver \
  -conf config/hadoop-localhost.xml input/ncdc/all max-temp
```
