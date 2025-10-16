package org.example.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public abstract class BasePage {
    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    public void navigateTo(String url) {
        page.navigate(url, new Page.NavigateOptions()
                .setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
    }

    public String getTitle() {
        return page.title();
    }
}
