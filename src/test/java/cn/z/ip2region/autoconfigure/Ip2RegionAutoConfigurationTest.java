package cn.z.ip2region.autoconfigure;

import cn.z.ip2region.Ip2Region;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <h1>IP地址转区域SpringBoot自动配置测试</h1>
 *
 * <p>
 * createDate 2023/03/30 15:43:49
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootApplication
@SpringBootTest
@Slf4j
class Ip2RegionAutoConfigurationTest {

    final String zdbPath = "E:/ip2region.zdb";
    final String ip = "123.132.0.0";

    /**
     * 使用资源路径
     */
    // @Test
    void test00Resource() {
        // ip2region:
        //   resource-path: /file/ip2region/ip2region.zdb
        log.info(String.valueOf(Ip2Region.parse(ip)));
        log.info("是否已经初始化：{}", Ip2Region.initialized());
        // INFO cn.z.ip2region.Ip2Region                 : 数据加载成功：版本号VERSION 20221207 ，校验码CRC32 68EDD841
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Region{country='中国', province='山东省', city='济宁市', isp='联通'}
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : 是否已经初始化：true
    }

    /**
     * 使用本地路径
     */
    // @Test
    void test01Local() {
        // ip2region:
        //   local-path: E:/ip2region.zdb
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // INFO c.z.i.a.Ip2RegionAutoConfiguration       : IP地址转区域配置：本地路径LOCAL_PATH E:/ip2region.zdb
        // INFO cn.z.ip2region.Ip2Region                 : IP地址转区域初始化：文件路径LOCAL_PATH E:/ip2region.zdb
        // INFO cn.z.ip2region.Ip2Region                 : 数据加载成功：版本号VERSION 20221207 ，校验码CRC32 68EDD841
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Region{country='中国', province='山东省', city='济宁市', isp='联通'}
    }

    /**
     * 使用url路径
     */
    @Test
    void test02Url() {
        // ip2region:
        //   url-path: https://www.404z.cn/files/ip2region/v3.0.0/data/ip2region.zdb
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // INFO c.z.i.a.Ip2RegionAutoConfiguration       : IP地址转区域配置：URL路径URL_PATH https://www.404z.cn/files/ip2region/v3.0.0/data/ip2region.zdb
        // INFO cn.z.ip2region.Ip2Region                 : IP地址转区域初始化：URL路径URL_PATH https://www.404z.cn/files/ip2region/v3.0.0/data/ip2region.zdb
        // INFO cn.z.ip2region.Ip2Region                 : 数据加载成功：版本号VERSION 20221207 ，校验码CRC32 68EDD841
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Region{country='中国', province='山东省', city='济宁市', isp='联通'}
    }

    /**
     * 优先级
     */
    // @Test
    void test03Priority() {
        // ip2region:
        //   resource-path: /file/ip2region/ip2region.zdb
        //   local-path: E:/ip2region.zdb
        //   url-path: https://www.404z.cn/files/ip2region/v3.0.0/data/ip2region.zdb
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // INFO cn.z.ip2region.Ip2Region                 : 数据加载成功：版本号VERSION 20221207 ，校验码CRC32 68EDD841
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Region{country='中国', province='山东省', city='济宁市', isp='联通'}
    }

    /**
     * 没有配置
     */
    // @Test
    void test04None() {
        // # 不需要配置
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
        // cn.z.ip2region.Ip2RegionException: 未初始化！
    }

    /**
     * 配置错误
     */
    // @Test
    void test05Error() {
        // ip2region:
        //   resource-path: /file/ip2region/ip2region
        log.info(String.valueOf(Ip2Region.parse(ip)));
        //  INFO c.z.i.a.Ip2RegionAutoConfiguration       : IP地址转区域配置：资源路径RESOURCE_PATH /file/ip2region/ip2region
        // ERROR c.z.i.a.Ip2RegionAutoConfiguration       : 资源文件异常！
        // java.io.FileNotFoundException: class path resource [file/ip2region/ip2region] cannot be opened because it does not exist
    }

    /**
     * 配置属性后又手动初始化
     */
    // @Test
    void test06Init() {
        // ip2region:
        //   resource-path: /file/ip2region/ip2region.zdb
        Ip2Region.initByFile(zdbPath);
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // INFO cn.z.ip2region.Ip2Region                 : 数据加载成功：版本号VERSION 20221207 ，校验码CRC32 68EDD841
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Started Ip2RegionAutoConfigurationTest in 0.955 seconds (JVM running for 1.859)
        // WARN cn.z.ip2region.Ip2Region                 : 已经初始化过了，不可重复初始化！
        // INFO c.z.i.a.Ip2RegionAutoConfigurationTest   : Region{country='中国', province='山东省', city='济宁市', isp='联通'}
    }

}
