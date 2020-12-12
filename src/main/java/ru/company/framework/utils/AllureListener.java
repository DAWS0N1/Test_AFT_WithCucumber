package ru.company.framework.utils;

import io.cucumber.plugin.event.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.company.framework.managers.DriverManager;

import java.io.ByteArrayInputStream;


public class AllureListener extends AllureCucumber5Jvm {

    @Override
    public void setEventPublisher(final EventPublisher publisher) {

        publisher.registerHandlerFor(TestStepFinished.class, testStepFinished -> {
            if (testStepFinished.getResult().getStatus().equals(Status.FAILED)){
                Allure.attachment("Screenshot", new ByteArrayInputStream(addScreenshot()));
            }
        });
        super.setEventPublisher(publisher);
    }


    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] addScreenshot(){
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
