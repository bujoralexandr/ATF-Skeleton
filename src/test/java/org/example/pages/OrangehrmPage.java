package org.example.pages;

import com.microsoft.playwright.Page;
import org.example.config.ConfigManager;

public class OrangehrmPage extends BasePage {
    public static final String ORANGE_HRM_PAGE_URL = "/web/index.php/auth/login";

    public OrangehrmPage(Page page) {
        super(page);
    }

    public OrangehrmPage open() {
        navigateTo(ConfigManager.getInstance().baseUiUrl() + ORANGE_HRM_PAGE_URL);
        return this;
    }
}
