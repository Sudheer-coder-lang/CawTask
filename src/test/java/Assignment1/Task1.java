package Assignment1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Task1 {
	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		driver=new ChromeDriver();
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//summary[text()='Table Data']")).click();
		
		String jsonData = "[{\"name\":\"Bob\",\"age\":20,\"gender\":\"male\"}," +
                "{\"name\":\"George\",\"age\":42,\"gender\":\"male\"}," +
                "{\"name\":\"Sara\",\"age\":42,\"gender\":\"female\"}," +
                "{\"name\":\"Conor\",\"age\":40,\"gender\":\"male\"}," +
                "{\"name\":\"Jennifer\",\"age\":42,\"gender\":\"female\"}]";
        WebElement inputBox = driver.findElement(By.id("jsondata"));
        
        inputBox.clear();
        inputBox.sendKeys(jsonData);
        driver.findElement(By.xpath("//button[text()='Refresh Table']")).click();
        String[][] expectedData = {
                {"Bob", "20", "male"},
                {"George", "42", "male"},
                {"Sara", "42", "female"},
                {"Conor", "40", "male"},
                {"Jennifer", "42", "female"}
        };

        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='dynamictable']//descendant::tr"));
        for (int i = 1; i < expectedData.length; i++) {
            List<WebElement> cells = rows.get(i).findElements(By.xpath("//table[@id='dynamictable']//descendant::td"));
            Assert.assertEquals(cells.get(0).getText(), expectedData[i][0]);
            Assert.assertEquals(cells.get(1).getText(), expectedData[i][1]);
            Assert.assertEquals(cells.get(2).getText(), expectedData[i][2]);
        }

        driver.quit();
        

	}

}
