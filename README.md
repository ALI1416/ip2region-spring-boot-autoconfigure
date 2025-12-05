# IP Address To Region Springboot Autoconfigure IP地址转区域SpringBoot自动配置

[![License](https://img.shields.io/github/license/ALI1416/ip2region-spring-boot-autoconfigure?label=License)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Java Support](https://img.shields.io/badge/Java-8+-green)](https://openjdk.org/)
[![Maven Central](https://img.shields.io/maven-central/v/cn.404z/ip2region-spring-boot-autoconfigure?label=Maven%20Central)](https://mvnrepository.com/artifact/cn.404z/ip2region-spring-boot-autoconfigure)
[![Tag](https://img.shields.io/github/v/tag/ALI1416/ip2region-spring-boot-autoconfigure?label=Tag)](https://github.com/ALI1416/ip2region-spring-boot-autoconfigure/tags)
[![Repo Size](https://img.shields.io/github/repo-size/ALI1416/ip2region-spring-boot-autoconfigure?label=Repo%20Size&color=success)](https://github.com/ALI1416/ip2region-spring-boot-autoconfigure/archive/refs/heads/master.zdb)

[![Java CI](https://github.com/ALI1416/ip2region-spring-boot-autoconfigure/actions/workflows/ci.yml/badge.svg)](https://github.com/ALI1416/ip2region-spring-boot-autoconfigure/actions/workflows/ci.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_ip2region-spring-boot-autoconfigure&metric=coverage)
![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_ip2region-spring-boot-autoconfigure&metric=reliability_rating)
![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_ip2region-spring-boot-autoconfigure&metric=sqale_rating)
![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_ip2region-spring-boot-autoconfigure&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=ALI1416_ip2region-spring-boot-autoconfigure)

## 简介

本项目是[IP地址转区域](https://github.com/ALI1416/ip2region)的SpringBoot自动配置

## 数据文件

- 数据文件目录：[点击查看](https://github.com/ALI1416/ip2region/tree/master/data)

## 依赖导入

```xml
<dependency>
  <groupId>cn.404z</groupId>
  <artifactId>ip2region-spring-boot-autoconfigure</artifactId>
  <version>3.4.0</version>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter</artifactId>
  <version>2.7.18</version>
</dependency>
```

## 使用方法

### 定义常量

```java
final String zdbPath = "E:/ip2region.zdb";
final String ip = "123.132.0.0";
```

### 使用资源路径

```java
// ip2region:
//   resource-path: /file/ip2region/ip2region.zdb
log.info(String.valueOf(Ip2Region.parse(ip)));
log.info("是否已经初始化：{}", Ip2Region.initialized());
// INFO cn.z.ip2region.Ip2Region                 : 数据加载成功：版本号VERSION 20221207 ，校验码CRC32 68EDD841
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Region{country='中国', province='山东省', city='济宁市', isp='联通'}
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : 是否已经初始化：true
```

### 使用本地路径

```java
// ip2region:
//   local-path: E:/ip2region.zdb
log.info(String.valueOf(Ip2Region.parse(ip)));
// INFO c.z.i.a.Ip2RegionAutoConfiguration       : IP地址转区域配置：本地路径LOCAL_PATH E:/ip2region.zdb
// INFO cn.z.ip2region.Ip2Region                 : IP地址转区域初始化：文件路径LOCAL_PATH E:/ip2region.zdb
// INFO cn.z.ip2region.Ip2Region                 : 数据加载成功：版本号VERSION 20221207 ，校验码CRC32 68EDD841
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Region{country='中国', province='山东省', city='济宁市', isp='联通'}
```

### 使用url路径

```java
// ip2region:
//   url-path: https://www.404z.cn/files/ip2region/v3.0.0/data/ip2region.zdb
log.info(String.valueOf(Ip2Region.parse(ip)));
// INFO c.z.i.a.Ip2RegionAutoConfiguration       : IP地址转区域配置：URL路径URL_PATH https://www.404z.cn/files/ip2region/v3.0.0/data/ip2region.zdb
// INFO cn.z.ip2region.Ip2Region                 : IP地址转区域初始化：URL路径URL_PATH https://www.404z.cn/files/ip2region/v3.0.0/data/ip2region.zdb
// INFO cn.z.ip2region.Ip2Region                 : 数据加载成功：版本号VERSION 20221207 ，校验码CRC32 68EDD841
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Region{country='中国', province='山东省', city='济宁市', isp='联通'}
```

### 优先级

```java
// ip2region:
//   resource-path: /file/ip2region/ip2region.zdb
//   local-path: E:/ip2region.zdb
//   url-path: https://www.404z.cn/files/ip2region/v3.0.0/data/ip2region.zdb
log.info(String.valueOf(Ip2Region.parse(ip)));
// INFO cn.z.ip2region.Ip2Region                 : 数据加载成功：版本号VERSION 20221207 ，校验码CRC32 68EDD841
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Region{country='中国', province='山东省', city='济宁市', isp='联通'}
```

### 没有配置

```java
// # 不需要配置
log.info(String.valueOf(Ip2Region.parse(ip)));
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
// cn.z.ip2region.Ip2RegionException: 未初始化！
```

### 配置错误

```java
// ip2region:
//   resource-path: /file/ip2region/ip2region
log.info(String.valueOf(Ip2Region.parse(ip)));
//  INFO c.z.i.a.Ip2RegionAutoConfiguration       : IP地址转区域配置：资源路径RESOURCE_PATH /file/ip2region/ip2region
// ERROR c.z.i.a.Ip2RegionAutoConfiguration       : 资源文件异常！
// java.io.FileNotFoundException: class path resource [file/ip2region/ip2region] cannot be opened because it does not exist
```

### 配置属性后又手动初始化

```java
// ip2region:
//   resource-path: /file/ip2region/ip2region.zdb
Ip2Region.initByFile(zdbPath);
log.info(String.valueOf(Ip2Region.parse(ip)));
// INFO cn.z.ip2region.Ip2Region                 : 数据加载成功：版本号VERSION 20221207 ，校验码CRC32 68EDD841
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
// WARN cn.z.ip2region.Ip2Region                 : 已经初始化过了，不可重复初始化！
// INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Region{country='中国', province='山东省', city='济宁市', isp='联通'}
```

更多请见[测试](./src/test)

## 更新日志

[点击查看](./CHANGELOG.md)

## 关于

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://www.404z.cn/images/about.dark.svg">
  <img alt="About" src="https://www.404z.cn/images/about.light.svg">
</picture>
