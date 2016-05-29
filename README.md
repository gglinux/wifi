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
#### HBase创建和数据导入

#### 用户轨迹查询

#### 伴随情况分析

#### 碰撞分析

### Web方式
#### HBase创建和数据导入

#### 用户轨迹查询

#### 伴随情况分析

#### 碰撞分析
