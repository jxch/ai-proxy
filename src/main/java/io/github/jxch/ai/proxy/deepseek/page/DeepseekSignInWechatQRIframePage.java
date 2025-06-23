package io.github.jxch.ai.proxy.deepseek.page;

import com.codeborne.selenide.SelenideElement;
import io.github.jxch.ai.proxy.pool.Iframe;
import io.github.jxch.ai.proxy.pool.Page;
import io.github.jxch.ai.proxy.pool.SelenidePage;
import lombok.NoArgsConstructor;

import static com.codeborne.selenide.Selenide.$;

@NoArgsConstructor
@Iframe(DeepseekSignInPage.class)
@Page(father = DeepseekSignInPage.class)
public class DeepseekSignInWechatQRIframePage implements SelenidePage {
    private final SelenideElement wechatQR = $("#tpl_iframe > div.web_qrcode_panel > div.web_qrcode_initial_context.js_status.js_wx_default_tip > div > img");

    public String getWechatQRSrc() {
        return wechatQR.getAttribute("src");
    }
}