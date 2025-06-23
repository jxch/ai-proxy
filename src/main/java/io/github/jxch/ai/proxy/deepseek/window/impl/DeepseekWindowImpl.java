package io.github.jxch.ai.proxy.deepseek.window.impl;

import io.github.jxch.ai.proxy.deepseek.page.DeepseekSignInPage;
import io.github.jxch.ai.proxy.deepseek.page.DeepseekSignInWechatQRIframePage;
import io.github.jxch.ai.proxy.deepseek.window.DeepseekWindow;
import io.github.jxch.ai.proxy.pool.SelenidePages;
import org.springframework.stereotype.Component;

@Component
public class DeepseekWindowImpl implements DeepseekWindow {

    @Override
    public String getWechatQRUrl() {
        resetWebDriverContext();
        return SelenidePages.open(DeepseekSignInPage.class)
                .switchIframe(DeepseekSignInWechatQRIframePage.class)
                .getWechatQRSrc();
    }

}
