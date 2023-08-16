package cn.z.ip2region.autoconfigure;

import cn.z.ip2region.Ip2Region;
import cn.z.ip2region.Ip2RegionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.InputStream;

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
    private static final Logger log = LoggerFactory.getLogger(Ip2RegionAutoConfiguration.class);
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
            log.info("IP地址转区域配置：资源路径RESOURCE_PATH {}", ip2RegionProperties.getResourcePath());
            InputStream inputStream;
            try {
                inputStream = new ClassPathResource(ip2RegionProperties.getResourcePath()).getInputStream();
            } catch (Exception e) {
                log.error("资源文件异常！", e);
                throw new Ip2RegionException("资源文件异常！");
            }
            Ip2Region.init(inputStream);
        } else if (ip2RegionProperties.getLocalPath() != null) {
            log.info("IP地址转区域配置：本地路径LOCAL_PATH {}", ip2RegionProperties.getLocalPath());
            Ip2Region.initByFile(ip2RegionProperties.getLocalPath());
        } else if (ip2RegionProperties.getUrlPath() != null) {
            log.info("IP地址转区域配置：URL路径URL_PATH {}", ip2RegionProperties.getUrlPath());
            Ip2Region.initByUrl(ip2RegionProperties.getUrlPath());
        }
    }

}
