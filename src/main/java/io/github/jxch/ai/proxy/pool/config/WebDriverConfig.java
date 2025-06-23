package io.github.jxch.ai.proxy.pool.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
public class WebDriverConfig {

    @Bean
    @ConfigurationProperties("spring.web-driver.pool")
    public GenericObjectPoolConfig<WebDriver> webDriverObjectPoolConfig() {
        return new GenericObjectPoolConfig<>();
    }

}
