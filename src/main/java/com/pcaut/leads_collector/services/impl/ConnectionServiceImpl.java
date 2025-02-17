package com.pcaut.leads_collector.services.impl;

import com.pcaut.leads_collector.services.ConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {
    @Override
    public List<String> connect() throws InterruptedException, IOException {
        WebDriver chrome = new ChromeDriver();
        chrome.get("https://app.herospark.com/login");

        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(20));

        Thread.sleep(15000);
        WebElement loginButton2 = chrome.findElement(By.xpath("//*[@id=\"app\"]/section/div/div/form/button"));
        loginButton2.click();


        Thread.sleep(2000);
        WebElement hamburguer = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/header/div/div/button")));
        hamburguer.click();

        Thread.sleep(2000);
        WebElement funnels = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/main/aside/div/div[2]/ul/li[4]/a/div/i")));
        funnels.click();

        WebElement funilEscolhido = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"funnel-list-section\"]/div/div/div/div/div[1]/div[1]/div[2]/a")));
        funilEscolhido.click();

        return this.collectList(wait);
    }

    public List<String> collectList(WebDriverWait wait){
        int j = 9;
        List<String> finalList = new ArrayList<>();
        try {
            for (int i = 0; i < 1300; i++){
                List<String> list = this.loop(j, finalList, wait);
                WebElement buttonNext = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/main/div/div/section[2]/div/div[4]/div[2]/ul/li[7]/button/i")));
                buttonNext.click();
                finalList.addAll(list);
                System.out.println(i);
                Thread.sleep(1000);
            }

        }catch (TimeoutException e){
            log.error(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.saveToFile(finalList, "C:\\Users\\pctheone\\Documents\\Repositories\\leads-collector\\leads.txt");
        }
        return finalList;
    }

    public List<String> loop(int j, List<String> list, WebDriverWait wait){
        List<String> newList = new ArrayList<>();

        for (int i = 1; i< j ; i++){
            WebElement lead = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/main/div/div/section[2]/div/div[3]/div[3]/div["+i+"]/div[2]/ul/li[1]/p")));
            String email = lead.getText();
            newList.add(email);
        }
        return newList;
    }

    public void saveToFile(List<String> emails, String filePath) {
        String content = String.join(System.lineSeparator(), emails);

        try {
            Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE);
            System.out.println("E-mails salvos em: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
