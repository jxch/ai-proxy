package io.github.jxch.ai.proxy.pool;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

public interface WebDriverPoolRegister {

    default String key() {
        return getClass().getSimpleName();
    }

    default void register() {
        if (!WebDriverPooled.isRegistered(key())) {
            WebDriverPooled.register(key());
        }
    }

    default void unregister() {
        WebDriverPooled.unregister(key());
    }

    default WebDriver getWebDriver() {
        return Optional.ofNullable(WebDriverPooled.get(key()))
                .orElseThrow(() -> new RuntimeException("找不到对应的WebDriver: " + getClass().getName()));
    }

    default void resetWebDriverContext() {
        WebDriverRunner.setWebDriver(getWebDriver());
    }

    default void clearWebDriverContext() {
        WebDriverRunner.clearBrowserCache();
    }

}
