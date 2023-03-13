package cn.z.ip2region.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <h1>IP地址转区域配置属性</h1>
 *
 * <p>
 * createDate 2021/09/22 15:43:41
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = Ip2RegionProperties.IP2REGION_PREFIX)
public class Ip2RegionProperties {

    /**
     * 前缀{@value}
     */
    public static final String IP2REGION_PREFIX = "ip2region";
    /**
     * 资源路径(优先级0)<br>
     * 读取项目文件夹resources下的路径
     */
    private String resourcePath;
    /**
     * 本地路径(优先级1)<br>
     * 读取本地物理路径
     */
    private String localPath;
    /**
     * URL路径(优先级2)<br>
     * 读取URL路径<br>
     * 可以用：https://cdn.jsdelivr.net/gh/ali1416/ip2region-test/data/ip2region.zxdb
     */
    private String urlPath;

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
