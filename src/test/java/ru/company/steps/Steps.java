package ru.company.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.company.framework.managers.PageManager;

public class Steps {
    /**
     * Менеджер страничек
     * @see PageManager#getPageManager()
     */
    private PageManager app = PageManager.getPageManager();

    @Когда("^Загружена стартовая страница$")
    public void getInitialPage(){
        app.getStartPage();
    }

    @Когда("^Переход в главное меню '(.*)'$")
    public void selectNameBaseMenu(String nameBaseMenu){
        app.getStartPage().selectBaseMenu(nameBaseMenu);
    }

    @Когда("^Выбираем подменю '(.*)'$")
    public void selectNameSubMenu(String nameSubMenu){
        app.getStartPage().selectSubMenu(nameSubMenu);
    }

    @Тогда("^Нажимаем кнопку 'Подать заявку'$")
    public void clickNextBtn(){
        app.getIpotekaPage().clickNextBtn();
    }

    @Когда("^Заполняем форму поле/значение$")
    public void fillFields(DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    app.getMortgagePage().inputField(raw.get(0), raw.get(1));
                }
        );
    }

    @Тогда("^Проверяем чекбокс по форме поле/значение$")
    public void selectCheckboxes(DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    app.getMortgagePage().selectCheckboxes(raw.get(0), raw.get(1));
                }
        );
    }

    @Тогда("^Проверяем что на странице значения совпадают по форме поле/значение$")
    public void checkingField(DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    app.getMortgagePage().checkingField(raw.get(0), raw.get(1));
                }
        );
    }
}
