package io.github.jxch.ai.proxy.deepseek.page;


import com.codeborne.selenide.SelenideElement;
import io.github.jxch.ai.proxy.pool.Iframe;
import io.github.jxch.ai.proxy.pool.Page;
import io.github.jxch.ai.proxy.pool.SelenidePage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.codeborne.selenide.Selenide.$;

@NoArgsConstructor
@Page(url = "https://chat.deepseek.com/sign_in")
public class DeepseekSignInPage implements SelenidePage {
    @Getter
    @Iframe(DeepseekSignInWechatQRIframePage.class)
    private final SelenideElement wechatLoginIframe = $("#wxLogin").$("iframe");
}