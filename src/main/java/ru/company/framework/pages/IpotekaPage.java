package ru.company.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IpotekaPage extends BasePage{

    @FindBy(xpath = "//b[text()='Подать заявку']")
    WebElement btn;

    /**
     * Функция клика на кнопку подать заявку
     *
     * @return MortgagePage - т.е. переходим на страницу {@link ru.company.framework.pages.MortgagePage}
     */
    @Step("Нажимаем кнопку 'Подать заявку'")
    public MortgagePage clickNextBtn(){
        scrollToElementJs(btn);
        action.moveToElement(btn).click().build().perform();
        return app.getMortgagePage();
    }
}
