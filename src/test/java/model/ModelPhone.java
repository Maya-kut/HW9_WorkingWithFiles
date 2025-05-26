package model;

public class ModelPhone {
    private String model;
    private String userAgent;
    private Screen screen;
    private Viewport viewport;
    private int deviceScaleFactor;
    private boolean isMobile;
    private boolean hasTouch;
    private String defaultBrowserType;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getUserAgent() {
        return userAgent;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public Screen getScreen() {
        return screen;
    }
    public void setScreen(Screen screen) {
        this.screen = screen;
    }
    public Viewport getViewport() {
        return viewport;
    }
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }
    public int getDeviceScaleFactor() {
        return deviceScaleFactor;
    }
    public void setDeviceScaleFactor(int deviceScaleFactor) {
        this.deviceScaleFactor = deviceScaleFactor;
    }
    public boolean isMobile() {
        return isMobile;
    }
    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }
    public boolean HasTouch() {
        return hasTouch;
    }
    public void setHasTouch(boolean hasTouch) {
        this.hasTouch = hasTouch;
    }
    public String getDefaultBrowserType() {
        return defaultBrowserType;
    }
    public void setDefaultBrowserType(String defaultBrowserType) {
        this.defaultBrowserType = defaultBrowserType;
    }




}
