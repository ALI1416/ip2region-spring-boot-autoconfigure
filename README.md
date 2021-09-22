# High performance snowflake ID generator springboot autoconfigure 高性能雪花ID生成器自动配置

## 项目地址
[Github源码](https://github.com/ALI1416/id-spring-boot-autoconfigure)
[Gitee源码](https://gitee.com/ALI1416/id-spring-boot-autoconfigure)
[![Build Status](https://travis-ci.com/ALI1416/id-spring-boot-autoconfigure.svg?branch=master)](https://travis-ci.com/ALI1416/id-spring-boot-autoconfigure)

[Github测试](https://github.com/ALI1416/id-spring-boot-autoconfigure-test)
[Gitee测试](https://gitee.com/ALI1416/id-spring-boot-autoconfigure-test)
[![Build Status](https://travis-ci.com/ALI1416/id-spring-boot-autoconfigure-test.svg?branch=master)](https://travis-ci.com/ALI1416/id-spring-boot-autoconfigure-test)

### 高性能雪花ID生成器项目地址
[Github源码](https://github.com/ALI1416/id)
[Gitee源码](https://gitee.com/ALI1416/id)
[![Build Status](https://travis-ci.com/ALI1416/id.svg?branch=master)](https://travis-ci.com/ALI1416/id)

[Github测试](https://github.com/ALI1416/id-test)
[Gitee测试](https://gitee.com/ALI1416/id-test)
[![Build Status](https://travis-ci.com/ALI1416/id-test.svg?branch=master)](https://travis-ci.com/ALI1416/id-test)

## 简介
本项目使用了SpringBoot的自动配置，只需要在配置文件中写入初始化参数即可，不用写死在代码中。

## 依赖导入
最新版本
[![Maven central](https://maven-badges.herokuapp.com/maven-central/cn.404z/id-spring-boot-autoconfigure/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cn.404z/id-spring-boot-autoconfigure)

maven
```xml
<!-- 必须依赖 -->
<dependency>
    <groupId>cn.404z</groupId>
    <artifactId>id-spring-boot-autoconfigure</artifactId>
    <version>2.3.0</version>
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
implementation 'cn.404z:id-spring-boot-autoconfigure:2.3.0'
// 额外依赖(运行未报错，不需要加)
implementation 'org.springframework.boot:spring-boot-starter:2.5.4'
```

## 使用方法
### 正常使用
配置文件
```yml
id:
  machine-id: 0
  machine-bits: 8
  sequence-bits: 14
```

代码
```java
System.out.println("ID为：" + Id.next());
```

结果
```txt
[main] INFO cn.z.id.autoconfigure.IdAutoConfiguration - 读取到配置文件，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为14
[main] INFO cn.z.id.Id - 预初始化...
[main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
[main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00.0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
[main] INFO cn.z.id.Id - 手动初始化...
[main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为14
[main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为16384，时钟最早回拨到2021-01-01 08:00:00.0，可使用时间大约为69年，失效日期为2090-09-07 23:47:35.551
[main] INFO com.demo.App - Started App in 0.798 seconds (JVM running for 1.632)
ID为：22502074365247488
```

### 不配置属性
不需要配置文件  
代码
```java
System.out.println("ID为：" + Id.next());
```

结果
```txt
[main] INFO com.demo.App - Started App in 0.735 seconds (JVM running for 1.401)
[main] INFO cn.z.id.Id - 预初始化...
[main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
[main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00.0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
ID为：5625838540161024
```

### 配置部分属性
配置文件
```yml
id:
  machine-id: 10
```

代码
```java
System.out.println("ID为：" + Id.next());
```

结果
```txt
[main] INFO cn.z.id.autoconfigure.IdAutoConfiguration - 读取到配置文件，MACHINE_ID为10
[main] INFO cn.z.id.Id - 预初始化...
[main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
[main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00.0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
[main] INFO cn.z.id.Id - 手动初始化...
[main] INFO cn.z.id.Id - 初始化，MACHINE_ID为10，MACHINE_BITS为8，SEQUENCE_BITS为12
[main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00.0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
[main] INFO com.demo.App - Started App in 0.825 seconds (JVM running for 1.509)
ID为：5626263803830272
```

### 配置属性后又手动初始化
配置文件
```yml
id:
  machine-id: 0
  machine-bits: 8
  sequence-bits: 6
```

代码
```java
Id.init(20, 6, 10);
System.out.println("ID为：" + Id.next());
```

结果
```txt
[main] INFO cn.z.id.autoconfigure.IdAutoConfiguration - 读取到配置文件，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为6
[main] INFO cn.z.id.Id - 预初始化...
[main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为12
[main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为4096，时钟最早回拨到2021-01-01 08:00:00.0，可使用时间大约为278年，失效日期为2299-09-27 23:10:22.207
[main] INFO cn.z.id.Id - 手动初始化...
[main] INFO cn.z.id.Id - 初始化，MACHINE_ID为0，MACHINE_BITS为8，SEQUENCE_BITS为6
[main] INFO cn.z.id.Id - 最大机器码MACHINE_ID为255，1ms内最多生成Id数量为64，时钟最早回拨到2021-01-01 08:00:00.0，可使用时间大约为17851年，失效日期为19860-03-05 19:03:41.311
[main] INFO com.demo.App - Started App in 0.754 seconds (JVM running for 1.378)
[main] WARN cn.z.id.Id - 已经初始化过了，不可重复初始化！
ID为：87913675669504
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
