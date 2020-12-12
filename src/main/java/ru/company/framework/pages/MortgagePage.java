package ru.company.framework.pages;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static ru.company.framework.utils.AllureListener.addScreenshot;


public class MortgagePage extends BasePage{

    @FindBy(xpath = "//body[@class='ym-hide-content']//div[@data-test-id='main-results-block']//span[text()='Ежемесячный платеж']/following-sibling::span/span")
    WebElement before;

    @FindBy(xpath = "//div[@data-label='Стоимость недвижимости']/input")
    WebElement currentHome;

    @FindBy(xpath = "//div[@data-label='Первоначальный взнос']/input")
    WebElement firstPay;

    @FindBy(xpath = "//div[@data-label='Срок кредита']/input")
    WebElement term;

    @FindBy(xpath = "//div[@class='styles_item__1SP5M']")
    List<WebElement> checkboxList;

    @FindBy(xpath = "//div[@data-test-id='main-results-block']//li[@class='styles_listItem__2oHcd']")
    List<WebElement> outputFields;

    /**
     * Метод заполнения полей
     *
     * @param nameField - имя веб элемента, поля ввода
     * @param value     - значение вводимое в поле
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    @Step("Заполняем поле '{nameField}' значение '{value}'")
    public MortgagePage inputField(String nameField, String value) {
        WebElement element = null;
        switch (nameField){
            case "Стоимость недвижимости":
                fillInputField(currentHome, value);
                elementToCompleted(currentHome, before);
                element = currentHome;
                break;
            case "Первоначальный взнос":
                fillInputField(firstPay, value);
                elementToCompleted(firstPay, before);
                element = firstPay;
                break;
            case "Срок кредита":
                fillInputField(term, value);
                elementToCompleted(term, before);
                element = term;
                break;

            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Ипотека на готовое жилье'");
        }
        Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
                value, element.getAttribute("value"));
        return this;
    }

    /**
     * Метод проверки чекбоксов
     *
     * @param checkboxName - имя веб элемента, чекбокса
     * @param booleanValue     - значение чекбокса
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    @Step("Проверяем чекбокс '{checkboxName}'  на значение '{booleanValue}'")
    public MortgagePage selectCheckboxes(String checkboxName, String booleanValue){
        for (WebElement checkbox : checkboxList){
            WebElement element = checkbox.findElement(By.xpath("./div/div/span"));
            if (element.getText().equalsIgnoreCase(checkboxName)){
                WebElement element1 = checkbox.findElement(By.xpath(".//input"));
                if (element1.getAttribute("ariaChecked").equalsIgnoreCase(booleanValue)){
                    return this;
                }
                if (!element1.getAttribute("ariaChecked").equalsIgnoreCase(booleanValue)){
                    element1.click();
                    elementToCompleted(element1, before);
                    return this;
                }
            }
        }
        Assert.fail("Доп. услуга '" + checkboxName + "' не было найдено на странице!");
        return this;
    }

    /**
     * Метод проверки поля веб-элемента
     *
     * @param fieldName - имя веб элемента, поля
     * @param value     - значение поля
     * @return MortgagePage - т.е. остаемся на этой странице
     */
    @Step("Проверяем поле '{fieldName}'  на значение '{value}'")
    public MortgagePage checkingField(String fieldName, String value) {
        for (WebElement element: outputFields){
            WebElement title = element.findElement(By.xpath("./span[contains(@class, 'label')]"));
            if (title.getText().equalsIgnoreCase(fieldName)){
                WebElement outValue = element.findElement(By.xpath("./span/span"));
                Assert.assertEquals("Полученное значение отличается от ожидаемой", value, outValue.getText());
                return this;
            }
        }
        Assert.fail("Элемент" + fieldName + "не найден");
        return this;
    }
}
