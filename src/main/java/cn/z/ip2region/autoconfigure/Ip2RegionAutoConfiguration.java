package cn.z.ip2region.autoconfigure;

import cn.z.ip2region.Ip2Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * <h1>IP地址转区域自动配置</h1>
 *
 * <p>
 * createDate 2021/09/22 15:43:49
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Configuration
@EnableConfigurationProperties(Ip2RegionProperties.class)
public class Ip2RegionAutoConfiguration {

    /**
     * 日志实例
     */
    private final static Logger log = LoggerFactory.getLogger(Ip2RegionAutoConfiguration.class);
    /**
     * Ip2RegionProperties
     */
    private final Ip2RegionProperties ip2RegionProperties;

    /**
     * 构造函数(自动注入)
     *
     * @param ip2RegionProperties Ip2RegionProperties
     */
    public Ip2RegionAutoConfiguration(Ip2RegionProperties ip2RegionProperties) {
        this.ip2RegionProperties = ip2RegionProperties;
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        if (ip2RegionProperties.getResourcePath() != null) {
            log.info("读取到配置文件，RESOURCE_PATH为" + ip2RegionProperties.getResourcePath());
            try {
                Ip2Region.init(Ip2Region.inputStream2bytes(new ClassPathResource(ip2RegionProperties.getResourcePath()).getInputStream()));
            } catch (IOException e) {
                log.error("数据文件读取异常", e);
            }
        } else if (ip2RegionProperties.getLocalPath() != null) {
            log.info("读取到配置文件，LOCAL_PATH为" + ip2RegionProperties.getLocalPath());
            Ip2Region.initByFile(ip2RegionProperties.getLocalPath());
        } else if (ip2RegionProperties.getUrlPath() != null) {
            log.info("读取到配置文件，URL_PATH为" + ip2RegionProperties.getUrlPath());
            Ip2Region.initByUrl(ip2RegionProperties.getUrlPath());
        }
    }

}