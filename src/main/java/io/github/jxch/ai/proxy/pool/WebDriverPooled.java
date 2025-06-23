package io.github.jxch.ai.proxy.pool;

import cn.hutool.extra.spring.SpringUtil;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebDriverPooled extends GenericObjectPool<WebDriver> implements DisposableBean {
    private final static Map<String, WebDriver> REGISTRATION = new ConcurrentHashMap<>();

    @Autowired
    public WebDriverPooled(PooledObjectFactory<WebDriver> factory, GenericObjectPoolConfig<WebDriver> config) {
        super(factory, config);
    }

    public void returnObjectIgnoreError(WebDriver webDriver) {
        try {
            returnObject(webDriver);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        REGISTRATION.values().forEach(this::returnObjectIgnoreError);
        close();
    }

    @EventListener
    public void appFailedEvent(ApplicationFailedEvent event) {
        destroy();
    }

    public static void register(String key) {
        try {
            WebDriver webDriver = SpringUtil.getBean(WebDriverPooled.class).borrowObject();
            REGISTRATION.put(key, webDriver);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    public static void unregister(String key) {
        SpringUtil.getBean(WebDriverPooled.class).returnObject(REGISTRATION.remove(key));
    }

    public static boolean isRegistered(String key) {
        return  REGISTRATION.containsKey(key) && Objects.nonNull(REGISTRATION.get(key));
    }

    public static WebDriver get(String key) {
        return REGISTRATION.get(key);
    }

    public static void injectContext(String key) {
        WebDriverRunner.setWebDriver(get(key));
    }

    public static void clearContext() {
//        WebDriverRunner.closeWindow();
    }

}
