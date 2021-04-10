学习笔记
1. 启动zookeeper:`bin/zookeeper-server-start.sh config/zookeeper.properties`
2. 分别启动三个kafka:
  1. `nohup bin/kafka-server-start.sh config2/kafka9001.properties &`
  2. `nohup bin/kafka-server-start.sh config2/kafka9002.properties &`
  3. `nohup bin/kafka-server-start.sh config2/kafka9003.properties &`
3. 创建topic:`bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic test32 --partitions 3 --replication-factor 2`
4. 查看topci:`bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic test32`
5. 压测写入:`bin/kafka-producer-perf-test.sh --topic test32 --num-records 100000 --record-size 1000 --throughput 200000 --producer-props bootstrap.servers=localhost:9002`
6. 压测读取:`bin/kafka-consumer-perf-test.sh --bootstrap-server localhost:9002 --topic test32 --fetch-size 10485760 --messages 100000 --threads 2`