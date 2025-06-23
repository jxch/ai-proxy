package io.github.jxch.ai.proxy.deepseek.service.impl;

import io.github.jxch.ai.proxy.deepseek.service.DeepseekService;
import io.github.jxch.ai.proxy.deepseek.window.DeepseekWindow;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class DeepseekServiceImpl implements DeepseekService {
    private final DeepseekWindow deepseekWindow;

    @Override
    @SneakyThrows
    public BufferedImage getDeepseekWechatLoginQR() {
        return ImageIO.read(new URL(deepseekWindow.getWechatQRUrl()));
    }

}
