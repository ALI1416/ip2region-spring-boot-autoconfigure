# IP Address To Region Springboot Autoconfigure IP地址转区域自动配置

## 项目地址
[Github源码](https://github.com/ALI1416/ip2region-spring-boot-autoconfigure)
[Gitee源码](https://gitee.com/ALI1416/ip2region-spring-boot-autoconfigure)
[![Build Status](https://travis-ci.com/ALI1416/ip2region-spring-boot-autoconfigure.svg?branch=master)](https://travis-ci.com/ALI1416/ip2region-spring-boot-autoconfigure)

[Github测试](https://github.com/ALI1416/ip2region-spring-boot-autoconfigure-test)
[Gitee测试](https://gitee.com/ALI1416/ip2region-spring-boot-autoconfigure-test)
[![Build Status](https://travis-ci.com/ALI1416/ip2region-spring-boot-autoconfigure-test.svg?branch=master)](https://travis-ci.com/ALI1416/ip2region-spring-boot-autoconfigure-test)

### IP地址转区域项目地址
[Github源码](https://github.com/ALI1416/ip2region)
[Gitee源码](https://gitee.com/ALI1416/ip2region)
[![Build Status](https://travis-ci.com/ALI1416/ip2region.svg?branch=master)](https://travis-ci.com/ALI1416/ip2region)

[Github测试](https://github.com/ALI1416/ip2region-test)
[Gitee测试](https://gitee.com/ALI1416/ip2region-test)
[![Build Status](https://travis-ci.com/ALI1416/ip2region-test.svg?branch=master)](https://travis-ci.com/ALI1416/ip2region-test)

## 简介
本项目使用了SpringBoot的自动配置，只需要在配置文件中写入初始化参数即可，不用写死在代码中。

## 依赖导入
最新版本
[![Maven central](https://maven-badges.herokuapp.com/maven-central/cn.404z/ip2region-spring-boot-autoconfigure/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cn.404z/ip2region-spring-boot-autoconfigure)

`org.lionsoul:ip2region`最新版本
[![Maven central](https://maven-badges.herokuapp.com/maven-central/org.lionsoul/ip2region/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.lionsoul/ip2region)

maven
```xml
<!-- 必须依赖 -->
<dependency>
    <groupId>cn.404z</groupId>
    <artifactId>ip2region-spring-boot-autoconfigure</artifactId>
    <version>1.0.0</version>
</dependency>
    <dependency>
      <groupId>org.lionsoul</groupId>
      <artifactId>ip2region</artifactId>
      <version>1.7.2</version>
    </dependency>
<!-- 额外依赖(运行未报错，不需要加) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <version>2.5.4</version>
</dependency>
```

gradle
```groovy
// 必须依赖
implementation 'cn.404z:ip2region-spring-boot-autoconfigure:1.0.0'
implementation 'org.lionsoul:ip2region:1.7.2'
// 额外依赖(运行未报错，不需要加)
implementation 'org.springframework.boot:spring-boot-starter:2.5.4'
```

## 使用方法
### 使用资源路径
配置文件
```yml
ip2region:
  resource-path: /file/ip2region/data.db
```

代码
```java
System.out.print(Ip2Region.parse("202.108.22.5"));
```

结果
```txt
INFO 15744 --- [           main] c.z.i.a.Ip2RegionAutoConfiguration       : 读取到配置文件，RESOURCE_PATH为/file/ip2region/data.db
INFO 15744 --- [           main] cn.z.ip2region.Ip2Region                 : 加载数据文件成功，总共8.93MB
INFO 15744 --- [           main] com.demo.App                             : Started App in 0.971 seconds (JVM running for 2.101)
Region{country='中国', province='北京', city='北京', area='', isp='联通'}
```

### 使用本地路径
配置文件
```yml
ip2region:
  local-path: /file/ip2region/data.db
```

代码
```java
System.out.print(Ip2Region.parse("202.108.22.5"));
```

结果
```txt
INFO 13984 --- [           main] c.z.i.a.Ip2RegionAutoConfiguration       : 读取到配置文件，LOCAL_PATH为/file/ip2region/data.db
INFO 13984 --- [           main] cn.z.ip2region.Ip2Region                 : 初始化，文件路径为/file/ip2region/data.db
INFO 13984 --- [           main] cn.z.ip2region.Ip2Region                 : 加载数据文件成功，总共8.93MB
INFO 13984 --- [           main] com.demo.App                             : Started App in 1.053 seconds (JVM running for 2.208)
Region{country='中国', province='北京', city='北京', area='', isp='联通'}
```

### 使用url路径
配置文件
```yml
ip2region:
  url-path: https://cdn.jsdelivr.net/gh/lionsoul2014/ip2region/data/ip2region.db
```

代码
```java
System.out.print(Ip2Region.parse("202.108.22.5"));
```

结果
```txt
INFO 13900 --- [           main] c.z.i.a.Ip2RegionAutoConfiguration       : 读取到配置文件，URL_PATH为https://cdn.jsdelivr.net/gh/lionsoul2014/ip2region/data/ip2region.db
INFO 13900 --- [           main] cn.z.ip2region.Ip2Region                 : 初始化，URL路径为https://cdn.jsdelivr.net/gh/lionsoul2014/ip2region/data/ip2region.db
INFO 13900 --- [           main] cn.z.ip2region.Ip2Region                 : 加载数据文件成功，总共8.93MB
INFO 13900 --- [           main] com.demo.App                             : Started App in 2.704 seconds (JVM running for 4.005)
Region{country='中国', province='北京', city='北京', area='', isp='联通'}
```

### 优先级
配置文件
```yml
ip2region:
  resource-path: /file/ip2region/data.db
  local-path: /file/ip2region/data.db
  url-path: https://cdn.jsdelivr.net/gh/lionsoul2014/ip2region/data/ip2region.db
```

代码
```java
System.out.print(Ip2Region.parse("202.108.22.5"));
```

结果
```txt
INFO 4364 --- [           main] c.z.i.a.Ip2RegionAutoConfiguration       : 读取到配置文件，RESOURCE_PATH为/file/ip2region/data.db
INFO 4364 --- [           main] cn.z.ip2region.Ip2Region                 : 加载数据文件成功，总共8.93MB
INFO 4364 --- [           main] com.demo.App                             : Started App in 1.024 seconds (JVM running for 2.208)
Region{country='中国', province='北京', city='北京', area='', isp='联通'}
```

### 没有配置
不需要配置文件  
代码
```java
System.out.print(Ip2Region.parse("202.108.22.5"));
```

结果
```txt
 INFO 13960 --- [           main] com.demo.App                             : Started App in 0.972 seconds (JVM running for 2.132)
ERROR 13960 --- [           main] cn.z.ip2region.Ip2Region                 : memorySearch查询异常
java.lang.NullPointerException: null
Region{country='', province='', city='', area='', isp=''}
```

### 配置错误
配置文件
```yml
ip2region:
  resource-path: /file/ip2region/data
```

代码
```java
System.out.print(Ip2Region.parse("202.108.22.5"));
```

结果
```txt
 INFO 15352 --- [           main] c.z.i.a.Ip2RegionAutoConfiguration       : 读取到配置文件，RESOURCE_PATH为/file/ip2region/data
ERROR 15352 --- [           main] c.z.i.a.Ip2RegionAutoConfiguration       : 数据文件读取异常
java.io.FileNotFoundException: class path resource [file/ip2region/data] cannot be opened because it does not exist
 INFO 15352 --- [           main] com.demo.App                             : Started App in 1.007 seconds (JVM running for 2.123)
ERROR 15352 --- [           main] cn.z.ip2region.Ip2Region                 : memorySearch查询异常
java.lang.NullPointerException: null
Region{country='', province='', city='', area='', isp=''}
```

### 配置属性后又手动初始化
配置文件
```yml
ip2region:
  resource-path: /file/ip2region/data.db
```

代码
```java
Ip2Region.initByFile("/file/ip2region/data.db");
System.out.print(Ip2Region.parse("202.108.22.5"));
```

结果
```txt
INFO 10676 --- [           main] c.z.i.a.Ip2RegionAutoConfiguration       : 读取到配置文件，RESOURCE_PATH为/file/ip2region/data.db
INFO 10676 --- [           main] cn.z.ip2region.Ip2Region                 : 加载数据文件成功，总共8.93MB
INFO 10676 --- [           main] com.demo.App                             : Started App in 0.969 seconds (JVM running for 2.106)
WARN 10676 --- [           main] cn.z.ip2region.Ip2Region                 : 已经初始化过了，不可重复初始化！
Region{country='中国', province='北京', city='北京', area='', isp='联通'}
```

## 许可证
[![License](https://img.shields.io/badge/license-BSD-brightgreen)](https://opensource.org/licenses/BSD-3-Clause)

## 交流
QQ：1416978277  
微信：1416978277  
支付宝：1416978277@qq.com  
![交流](https://cdn.jsdelivr.net/gh/ALI1416/web/image/contact.png)

## 赞助
![赞助](https://cdn.jsdelivr.net/gh/ALI1416/web/image/donate.png)
