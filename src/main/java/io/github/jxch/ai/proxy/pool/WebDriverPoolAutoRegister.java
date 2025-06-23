package io.github.jxch.ai.proxy.pool;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public interface WebDriverPoolAutoRegister extends WebDriverPoolRegister, InitializingBean, DisposableBean {

    @Override
    default void afterPropertiesSet() {
        register();
    }

    @Override
    default void destroy() throws Exception {
        unregister();
    }

}
