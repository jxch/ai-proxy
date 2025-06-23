package io.github.jxch.ai.proxy.pool;

import io.github.jxch.ai.proxy.pool.config.WebDriverRemoteProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
@RequiredArgsConstructor
public class WebDriverPooledObjectFactory extends BasePooledObjectFactory<WebDriver>  {
    private final WebDriverRemoteProperties webDriverRemoteProperties;

    @Override
    public WebDriver create() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(webDriverRemoteProperties.getOptions());
        return new RemoteWebDriver(new URL(webDriverRemoteProperties.getUrl()), options);
    }

    @Override
    public PooledObject<WebDriver> wrap(WebDriver holder) {
        return new DefaultPooledObject<>(holder);
    }

    @Override
    public void destroyObject(PooledObject<WebDriver> p) {
        p.getObject().quit();
    }

    @Override
    public boolean validateObject(PooledObject<WebDriver> p) {
        try {
            p.getObject().getTitle();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
