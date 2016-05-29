## wifi
基于wifi抓取信息的大数据查询系统，使用到了HBase、Hive、ZooKeeper，Hadoop等技术。

## 使用说明

### 命令行方式
1. 分别导入HBase和Hive项目
2. 运行Hive服务端 "/mnt/hgfs/yyx/apache-hive-1.0.1-bin/hiveserver2"（此目录实际情况会有变化）
3. 运行HBase服务端 "/mnt/hgfs/yyx/hbase-0.98.18-hadoop1/bin/start-hbase.sh"(此目录实际会有变化)
4. 根据实际测试情况，调整参数，运行项目即可

### Web方式
1. Myeclipse导入webApp项目
2. 运行Hive服务端 "/mnt/hgfs/yyx/apache-hive-1.0.1-bin/hiveserver2"（此目录实际情况会有变化）
3. 运行HBase服务端 "/mnt/hgfs/yyx/hbase-0.98.18-hadoop1/bin/start-hbase.sh"(此目录实际会有变化)
4. 访问index.jsp，改变参数，可查询到相关结果

##测试结果展示

### 命令行方式

#### 参数如下
String startTime = "'2016-04-13 10:43:36'";

String endTime = "'2016-04-14 11:43:36'";

String userMac = "'ff:ff:ff:ff:ff:ff'";

String deviceMac = "'b8:27:eb:1c:c0:09'";
		
String time1 = "'2016-04-13 10:43:36'";

String time2 = "'2016-04-14 11:43:36'";

#### HBase创建和数据导入
![Hbase_insert](https://github.com/gglinux/wifi/blob/master/test/cli/hbase_insert.png)
#### 用户轨迹查询
![track](https://github.com/gglinux/wifi/blob/master/test/cli/user_track.png)
#### 伴随情况分析
![accompany](https://github.com/gglinux/wifi/blob/master/test/cli/accompany.png)
#### 碰撞分析
![crash](https://github.com/gglinux/wifi/blob/master/test/cli/crash.png)

### Web方式
#### HBase创建和数据导入
![Hbase_insert](https://github.com/gglinux/wifi/blob/master/test/web/hbase_insert.png)
#### 用户轨迹查询
![track](https://github.com/gglinux/wifi/blob/master/test/web/user_track.png)
#### 伴随情况分析
![accompany](https://github.com/gglinux/wifi/blob/master/test/web/accompany.png)
#### 碰撞分析
![crash](https://github.com/gglinux/wifi/blob/master/test/web/crash.png)
