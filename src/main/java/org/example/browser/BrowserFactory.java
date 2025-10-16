package org.example.browser;

import com.microsoft.playwright.*;
import org.example.config.ConfigManager;

/**
 * BrowserFactory provides a minimal example of how to centralize browser
 * creation for UI tests using Microsoft Playwright. In a production framework
 * this class would manage configurations (browser type, headless mode, timeouts)
 * and lifecycle concerns.
 * <p>
 * This project is intended for educational purposes, so the implementation is
 * intentionally simple and focuses on demonstrating structure.
 *
 * @see <a href="https://playwright.dev/java/docs/intro">Playwright Documentation</a>
 */
public final class BrowserFactory {

    private final Playwright playwright;
    private final Browser browser;
    private final ConfigManager config = ConfigManager.getInstance();

    private BrowserFactory() {
        this.playwright = Playwright.create();

        BrowserKind kind = BrowserKind.from(config.browser());
        boolean headless = config.headless();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(headless);

        switch (kind) {
            case CHROMIUM:
                this.browser = playwright.chromium().launch(launchOptions);
                break;
            case FIREFOX:
                this.browser = playwright.firefox().launch(launchOptions);
                break;
            case WEBKIT:
                this.browser = playwright.webkit().launch(launchOptions);
                break;
            default:
                throw new IllegalStateException("Unexpected BrowserKind: " + kind);
        }
    }

    private static final class Holder {
        static final BrowserFactory INSTANCE = new BrowserFactory();
    }

    public static BrowserFactory getInstance() {
        return Holder.INSTANCE;
    }

    public BrowserContext getNewContext() {
        BrowserContext context = browser.newContext();

        try {
            String pageTimeoutMs = config.pageTimeoutMs();
            if (pageTimeoutMs != null && !pageTimeoutMs.isBlank()) {
                double t = Double.parseDouble(pageTimeoutMs);
                context.setDefaultTimeout(t);
                context.setDefaultNavigationTimeout(t);
            }
        } catch (RuntimeException ignored) {
        }
        return context;
    }

    public void close() {
        try {
            browser.close();
        } catch (PlaywrightException e) {
            System.err.println("WARN: browser.close() failed: " + e.getMessage());
        }

        try {
            playwright.close();
        } catch (PlaywrightException e) {
            System.err.println("WARN: playwright.close() failed: " + e.getMessage());
        }
    }

}
