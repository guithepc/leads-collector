package com.pcaut.leads_collector.services.impl;

import com.pcaut.leads_collector.services.ConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {
    @Value("${EMAIL}")
    String email;
    @Override
    public List<String> connect() throws InterruptedException, IOException {
        WebDriver chrome = new ChromeDriver();
        chrome.get("https://app.herospark.com/login");


        Thread.sleep(15000);
        WebElement loginButton = chrome.findElement(By.xpath("//*[@id=\"app\"]/section/div/div/form/button"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(20));

        Thread.sleep(2000);
        WebElement hamb = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/header/div/div/button")));
        hamb.click();

        Thread.sleep(2000);
        WebElement funnels = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/main/aside/div/div[2]/ul/li[5]")));
        funnels.click();


        return this.collectList(wait);



    }

    public List<String> collectList(WebDriverWait wait){
        int j = 9;
        List<String> newList = new ArrayList<>();
        for (int i = 1; i< j ; i++){
            WebElement lead = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/main/div/div/section[2]/div/div[3]/div[3]/div["+i+"]/div[2]/ul/li[1]/p")));
            String a = lead.getText();
            log.debug(lead.toString());
            newList.add(a);
        }

        log.debug(newList.toString());
        return newList;

    }
}
