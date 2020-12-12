package ru.company.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.company.framework.managers.PageManager;

import static ru.company.framework.managers.DriverManager.getDriver;

public class BasePage {


    /**
     * Инициализация менеджера страничек
     *
     * @see PageManager
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Объект для имитации реального поведения мыши или клавиатуры
     *
     * @see Actions
     */
    protected Actions action = new Actions(getDriver());

    /**
     * Объект для выполнения любого js кода
     *
     * @see JavascriptExecutor
     */
    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    /**
     * Объект явного ожидания
     * При применении будет ожидать задонного состояния 15 секунд с интервалом в 1 секунду
     *
     * @see WebDriverWait
     */
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 15, 1000);


    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    /**
     * Функция позволяющая скролить до любого элемента с помощью js
     *
     * @param element - веб-элемент странички
     * @see JavascriptExecutor
     */
    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    /**
     * Явное ожидание состояния кликабельности элемента
     *
     * @param element - веб-элемент который требует проверки на кликабельность
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    protected WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание состояния видимости элемента
     *
     * @param element - веб-элемент который требует проверки на видимость
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    protected WebElement elementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void elementToCompleted(WebElement element, WebElement before) {
        wait.until(complectedOf(element, before));
    }


    /**
     * Общий метод по заполнения полей ввода
     *
     * @param field - веб-елемент поле ввода
     * @param value - значение вводимое в поле
     */
    public void fillInputField(WebElement field, String value) {
        scrollToElementJs(field);
        elementToBeClickable(field).click();
        js.executeScript("arguments[0].value = '';", field);
        field.sendKeys(value);
        field.sendKeys(Keys.ENTER);
        Assert.assertEquals("Поле было заполнено некорректно", value, field.getAttribute("value"));
    }

    /**
     * Общий метод по заполнению полей с датой
     *
     * @param field - веб-елемент поле с датой
     * @param value - значение вводимое в поле с датой
     */
    public void fillDateField(WebElement field, String value) {
        scrollToElementJs(field);
        field.sendKeys(value);
    }



    private static ExpectedCondition<WebElement> complectedOf(final WebElement element, final WebElement expected) {
        return new ExpectedCondition<WebElement>() {
            WebElement body = getDriver().findElement(By.xpath("//body[@class='ym-hide-content']//div[@data-test-id='main-results-block']//span[text()='Ежемесячный платеж']/following-sibling::span/span"));
            public WebElement apply(WebDriver driver) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!body.getText().equals(expected.getText())) {
                    return element;
                }
                return null;
            }
        };
    }
}
