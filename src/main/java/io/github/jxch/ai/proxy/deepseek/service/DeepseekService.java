package io.github.jxch.ai.proxy.deepseek.service;

import cn.hutool.core.img.ImgUtil;
import lombok.SneakyThrows;

import java.awt.image.BufferedImage;

public interface DeepseekService {

    BufferedImage getDeepseekWechatLoginQR();

    @SneakyThrows
    default byte[] getDeepseekWechatLoginQRImageBytes() {
        return ImgUtil.toBytes(getDeepseekWechatLoginQR(), ImgUtil.IMAGE_TYPE_PNG);
    }

}
