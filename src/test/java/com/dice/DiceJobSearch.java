package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	public static void main(String[] args) {
		
		//Setup chrome driver path 
		WebDriverManager.chromedriver().setup();
		//invoke selenium webdriver (Open browser)
		WebDriver driver = new ChromeDriver();
		
		//fullscreen
		driver.manage().window().maximize();
		// set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		driver.get("https://dice.com");
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Job search for Technology Proffesionals | Dice.com";
		
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice hompage succesfully loaded");
		} else {
			System.out.println("Step FAIL. Dice Homepage did not load successfully");
		}
		
		String keyword = "java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "03101";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		
		//ensure count is more than 0
		
		int countResult = Integer.parseInt(count.replace(",",""));
		
		if(countResult > 0) {
			System.out.println("Step PASS: Keyword : " + keyword + " search return "+ countResult
					+ " result in " + location);
			
		}else{
			System.out.println("Step FAIL: Keyword : " + keyword + " search return"+ countResult
					+ " result in " + location);
		}	
		driver.close();
		
		

	}
}
