package ru.company.framework.managers;

import ru.company.framework.pages.IpotekaPage;
import ru.company.framework.pages.MortgagePage;
import ru.company.framework.pages.StartPage;

public class PageManager {
    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    StartPage startPage;

    /**
     * Стартовая страничка ипотеки
     */
    IpotekaPage ipotekaPage;

    /**
     * Страничка ипотеки
     */
    MortgagePage mortgagePage;

    /**
     * Конструктор специально запривейтили (синглтон)
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManage
     *
     * @return PageManage
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link ru.company.framework.pages.StartPage}
     *
     * @return StartPage
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация {@link ru.company.framework.pages.IpotekaPage}
     *
     * @return IpotekaPage
     */
    public IpotekaPage getIpotekaPage() {
        if (ipotekaPage == null) {
            ipotekaPage = new IpotekaPage();
        }
        return ipotekaPage;
    }

    /**
     * Ленивая инициализация {@link ru.company.framework.pages.MortgagePage}
     *
     * @return MortgagePage
     */
    public MortgagePage getMortgagePage() {
        if (mortgagePage == null) {
            mortgagePage = new MortgagePage();
        }
        return mortgagePage;
    }
}
