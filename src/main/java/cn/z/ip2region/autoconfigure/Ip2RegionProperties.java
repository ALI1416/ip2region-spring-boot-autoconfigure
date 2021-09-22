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
     * 可以使用以下地址(请不要直接使用gitee链接,下载文件大于1MB需要登录才能下载;也不要使用github链接,经常不能访问)<br>
     * https://cdn.jsdelivr.net/gh/lionsoul2014/ip2region/data/ip2region.db<br>
     * 实际路径为<br>
     * https://gitee.com/lionsoul/ip2region/blob/master/data/ip2region.db
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
