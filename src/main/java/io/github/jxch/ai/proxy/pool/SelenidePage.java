package io.github.jxch.ai.proxy.pool;

public interface SelenidePage {

    @SuppressWarnings("unchecked")
    default <T extends SelenidePage> T open() {
        return (T) SelenidePages.open(this);
    }

    @SuppressWarnings("unchecked")
    default <T extends SelenidePage> T switchToMe() {
        return (T) SelenidePages.switchToMe(this);
    }

    default String getPageUrl() {
        return SelenidePages.getPageUrl(this);
    }

    default boolean isRootPage() {
        return SelenidePages.isRootPage(this);
    }

    default Class<? extends SelenidePage> getFather() {
        return SelenidePages.getFather(this);
    }

    default boolean hasIframe() {
        return SelenidePages.hasIframe(this);
    }

    default boolean isIframe() {
        return SelenidePages.isIframe(this);
    }

    default <R extends SelenidePage> R switchIframe(R iframe) {
        return SelenidePages.switchIframe(this, iframe);
    }

    default <R extends SelenidePage> R switchIframe(Class<R> iframe) {
        return SelenidePages.switchIframe(this, iframe);
    }

    default void switchToRoot() {
        SelenidePages.switchToRoot();
    }

}
