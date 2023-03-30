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
        log.info(String.valueOf(Ip2Region.parse(ip)));
        log.info("是否已经初始化：{}", Ip2Region.initialized());
        // [main] INFO c.z.i.a.Ip2RegionAutoConfiguration     :
        // 读取到配置，RESOURCE_PATH为：/file/ip2region/ip2region.zdb
        // [main] INFO cn.z.ip2region.Ip2Region               :
        // 数据加载成功，版本号为：20221207
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Started Ip2RegionAutoConfigurationTest in 0.442 seconds (JVM running for 0.931)
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Region{country='中国', province='山东省', city='济宁市', isp='联通'}
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // 是否已经初始化：true
    }

    /**
     * 使用本地路径
     */
    // @Test
    void test01Local() {
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // [main] INFO c.z.i.a.Ip2RegionAutoConfiguration     :
        // 读取到配置，LOCAL_PATH为：E:/ip2region.zdb
        // [main] INFO cn.z.ip2region.Ip2Region               :
        // 初始化，文件路径为：E:/ip2region.zdb
        // [main] INFO cn.z.ip2region.Ip2Region               :
        // 数据加载成功，版本号为：20221207
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Started Ip2RegionAutoConfigurationTest in 0.442 seconds (JVM running for 0.931)
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Region{country='中国', province='山东省', city='济宁市', isp='联通'}
    }

    /**
     * 使用url路径
     */
    @Test
    void test02Url() {
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // [main] INFO c.z.i.a.Ip2RegionAutoConfiguration     :
        // 读取到配置，URL_PATH为：https://cdn.jsdelivr.net/gh/ali1416/ip2region@master/data/ip2region.zdb
        // [main] INFO cn.z.ip2region.Ip2Region               :
        // 初始化，URL路径为：https://cdn.jsdelivr.net/gh/ali1416/ip2region@master/data/ip2region.zdb
        // [main] INFO cn.z.ip2region.Ip2Region               :
        // 数据加载成功，版本号为：20221207
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Started Ip2RegionAutoConfigurationTest in 0.442 seconds (JVM running for 0.931)
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Region{country='中国', province='山东省', city='济宁市', isp='联通'}
    }

    /**
     * 优先级
     */
    // @Test
    void test03Priority() {
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // [main] INFO c.z.i.a.Ip2RegionAutoConfiguration     :
        // 读取到配置，RESOURCE_PATH为：/file/ip2region/ip2region.zdb
        // [main] INFO cn.z.ip2region.Ip2Region               :
        // 数据加载成功，版本号为：20221207
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Started Ip2RegionAutoConfigurationTest in 0.442 seconds (JVM running for 0.931)
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Region{country='中国', province='山东省', city='济宁市', isp='联通'}
    }

    /**
     * 没有配置
     */
    // @Test
    void test04None() {
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // [main]  INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Started Ip2RegionAutoConfigurationTest in 0.442 seconds (JVM running for 0.931)
        // [main] ERROR cn.z.ip2region.Ip2Region               :
        // 未初始化！
        // [main]  INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // null
    }

    /**
     * 配置错误
     */
    // @Test
    void test05Error() {
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // [main]  INFO c.z.i.a.Ip2RegionAutoConfiguration     :
        // 读取到配置，RESOURCE_PATH为：/file/ip2region/ip2region
        // [main] ERROR cn.z.ip2region.Ip2Region               :
        // 资源文件异常！
        // java.io.FileNotFoundException: class path resource [file/ip2region/ip2region]
        // cannot be opened because it does not exist
        // [main]  INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Started Ip2RegionAutoConfigurationTest in 0.442 seconds (JVM running for 0.931)
        // [main] ERROR cn.z.ip2region.Ip2Region               :
        // 未初始化！
        // [main]  INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // null
    }

    /**
     * 配置属性后又手动初始化
     */
    // @Test
    void test06Init() {
        Ip2Region.initByFile(zdbPath);
        log.info(String.valueOf(Ip2Region.parse(ip)));
        // [main] INFO c.z.i.a.Ip2RegionAutoConfiguration     :
        // 读取到配置，RESOURCE_PATH为：/file/ip2region/ip2region.zdb
        // [main] INFO cn.z.ip2region.Ip2Region               :
        // 数据加载成功，版本号为：20221207
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Started Ip2RegionAutoConfigurationTest in 0.442 seconds (JVM running for 0.931)
        // [main] WARN cn.z.ip2region.Ip2Region               :
        // 已经初始化过了，不可重复初始化！
        // [main] INFO c.z.i.a.Ip2RegionAutoConfigurationTest :
        // Region{country='中国', province='山东省', city='济宁市', isp='联通'}
    }

}
