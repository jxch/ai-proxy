package io.github.jxch.ai.proxy.pool.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "spring.web-driver.remote")
public class WebDriverRemoteProperties {
    private String url;
    private List<String> options;
}
