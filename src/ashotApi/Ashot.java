package ashotApi;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class Ashot {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "C://BrowserDriver//chrome//chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		WebElement logoImageElement=driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/img"));
		
		Screenshot logoImageScreenshot=new AShot().takeScreenshot(driver,logoImageElement);
		
		ImageIO.write(logoImageScreenshot.getImage(),"png",new File("C://BrowserDriver/Image/orangehrmlogo.png"));
		File f=new File("C://BrowserDriver/Image/orangehrmlogo.png");
		if(f.exists()) {
			System.out.println("file exist");
			
		}else {
			System.out.println("file doesnot exist");
		}
		
	}

	

}
