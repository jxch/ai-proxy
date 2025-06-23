package io.github.jxch.ai.proxy.pool;

import cn.hutool.core.util.StrUtil;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class SelenidePages {

    public static <T extends SelenidePage> boolean isRootPage(Class<T> pageClass) {
        Page page = AnnotationUtils.getAnnotation(pageClass, Page.class);
        return Objects.nonNull(page) && StrUtil.isNotBlank(page.url());
    }

    public static <T extends SelenidePage> boolean isRootPage(T page) {
        return isRootPage(page.getClass());
    }

    public static <T extends SelenidePage> String getPageUrl(Class<T> pageClass) {
        Page page = AnnotationUtils.getAnnotation(pageClass, Page.class);
        if (Objects.nonNull(page)) {
            if (StrUtil.isNotBlank(page.url())) {
                return page.url();
            } else if (Objects.nonNull(page.father())
                    && !Objects.equals(page.father(), pageClass)
                    && !Objects.equals(page.father(), SelenidePage.class)) {
                return getPageUrl(page.father());
            }
        }

        throw new UnsupportedOperationException("找不到 PageUrl");
    }

    public static <T extends SelenidePage> String getPageUrl(T page) {
        return  getPageUrl(page.getClass());
    }

    public static <T extends SelenidePage> Class<? extends SelenidePage> getFather(Class<T> pageClass) {
        Page page = AnnotationUtils.getAnnotation(pageClass, Page.class);

        if (Objects.nonNull(page)) {
            if (isRootPage(pageClass)) {
                return pageClass;
            }

            if (Objects.nonNull(page.father())
                    && !Objects.equals(page.father(), pageClass)
                    && !Objects.equals(page.father(), SelenidePage.class)) {
                return page.father();
            }
        }

        throw new UnsupportedOperationException("找不到父页面");
    }

    public static <T extends SelenidePage> Class<? extends SelenidePage> getFather(T page) {
        return getFather(page.getClass());
    }

    public static <T extends SelenidePage> boolean hasIframe(Class<T> pageClass) {
        return Arrays.stream(pageClass.getDeclaredFields()).anyMatch(field -> field.isAnnotationPresent(Iframe.class));
    }

    public static <T extends SelenidePage> boolean hasIframe(T page) {
        return hasIframe(page.getClass());
    }

    public static <T extends SelenidePage> boolean isIframe(Class<T> pageClass) {
        return pageClass.isAnnotationPresent(Iframe.class);
    }

    public static <T extends SelenidePage> boolean isIframe(T page) {
        return isIframe(page.getClass());
    }

    @Nullable
    public static <T extends SelenidePage> Field getIframeField(T page, Class<? extends SelenidePage> iframe) {
        if (hasIframe(page.getClass())) {
            return Arrays.stream(page.getClass().getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Iframe.class))
                    .filter(field ->
                            Optional.ofNullable(AnnotationUtils.getAnnotation(field, Iframe.class))
                                    .map(anno -> Objects.equals(anno.value(), iframe))
                                    .orElse(false)
                    ).findFirst().orElse(null);
        }

        return null;
    }

    @NonNull
    @SneakyThrows
    public static <T extends SelenidePage> SelenideElement getIframeElement(T page, Class<? extends SelenidePage> iframe) {
        if (hasIframe(page.getClass())) {
            Field iframeField = getIframeField(page, iframe);
            if (Objects.nonNull(iframeField)) {
                PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(page.getClass(), iframeField.getName());
                if (Objects.nonNull(pd)) {
                    return (SelenideElement) pd.getReadMethod().invoke(page);
                }
            }
        }

        throw new IllegalArgumentException("该页面对象没有标记这个iframe元素: iframe[" + iframe.getName() + "] - " + page.getClass().getName());
    }

    public static <T extends SelenidePage, R extends SelenidePage> R switchIframe(T page, R iframe) {
        SelenideElement iframeElement = getIframeElement(page, iframe.getClass());
        Selenide.switchTo().frame(iframeElement);
        return iframe;
    }

    @SneakyThrows
    public static <T extends SelenidePage, R extends SelenidePage> R switchIframe(T page, Class<R> iframe) {
        return switchIframe(page, iframe.getConstructor().newInstance());
    }

    public static <T extends SelenidePage> T open(T page) {
        if (isRootPage(page.getClass())) {
            Selenide.open(getPageUrl(page.getClass()));
            return page;
        }

        throw new UnsupportedOperationException("暂不支持直接打开子页面");
    }

    @SneakyThrows
    public static <T extends SelenidePage> T open(Class<T> pageClass) {
        return open(pageClass.getConstructor().newInstance());
    }

    public static <T extends SelenidePage> T switchToMe(T page) {
        if (isRootPage(page.getClass())) {
            Selenide.switchTo().defaultContent();
            return page;
        }

        throw new UnsupportedOperationException("暂不支持直接聚焦到子页面");
    }

    @SneakyThrows
    public static <T extends SelenidePage> T switchToMe(Class<T> pageClass) {
        return switchToMe(pageClass.getConstructor().newInstance());
    }

    public static void switchToRoot() {
        Selenide.switchTo().defaultContent();
    }

}
