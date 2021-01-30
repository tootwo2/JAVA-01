## 指令:
1. 串行: `java -XX:+UseSerialGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`

2. 并行: `java -XX:+UseParallelGC -Xms1g -Xmx1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.parallel.log GCLogAnalysis`

3. CMS: `java -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.cms.log GCLogAnalysis`

4. G1: `java -XX:+UseG1GC -Xms1g -Xmx1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.g1.log GCLogAnalysis`

## 参数说明
1. -XX:+UseSerialGC: serial & serial old
2. -XX:+UseParNewGC: ParNew & serial old
3. -XX:+UseParallelGC: parallel scavenge & parallel old
4. -XX:+UseConcMarkSweepGC: ParNew & CMS(serial old为替补)
5. -XX:+UseG1GC: G1
6. -XX:+PrintGCDetails输出GC明细,-XX:+PrintGC输出GC概要

## 备注:
调整了GCLogAnalysis工作时间为300s


## 日志分析工具:
1. GCviewer
2. GCeasy

## 结论
1. CMS在full GC上的耗时明显低于ParallelGC
2. 从GC日志中明细能看出CMS的6个阶段
3. G1和CMS比较
  - 1G堆: G1吞吐量为66%,CMS为63%两者差不多,最长停顿时间G1为0.15秒
  - 8G堆: G1的吞吐量高达92%,CMS为71%,内存较大的情况下G1的吞吐量明显优于CMS


# 笔记
关注两个数据:GC暂停时间,以及GC之后的内存使用量/使用率

1. Yong GC：关注暂停时间，以及GC后的内存使用率是否正常，但不用特别关注GC前的使用量，而且只要业务在运行，年轻代的对象分配就少不了，回收量就不会少。
2. Full GC：更关注老年代的使用量有没有下降，以及下降了多少，如果FullGC后内存不怎么下降，使用率很高，就说明系统有问题