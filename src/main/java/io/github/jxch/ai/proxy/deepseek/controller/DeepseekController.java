package io.github.jxch.ai.proxy.deepseek.controller;

import io.github.jxch.ai.proxy.deepseek.service.DeepseekService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai/proxy/deepseek")
public class DeepseekController {
    private final DeepseekService deepseekService;

    @RequestMapping("getDeepseekWechatLoginQR")
    public ResponseEntity<byte[]> getDeepseekWechatLoginQR() {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(deepseekService.getDeepseekWechatLoginQRImageBytes());
    }

}
