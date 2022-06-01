package ashotApi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CompareImage {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "C://BrowserDriver//chrome//chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		BufferedImage expectedImage=ImageIO.read(new File("C://BrowserDriver/Image/orangehrmlogo.png"));
		
		WebElement logoImageElement=driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/img"));
        Screenshot logoImageScreenshot=new AShot().takeScreenshot(driver,logoImageElement);
        BufferedImage actualImage= logoImageScreenshot.getImage();
      
      ImageDiffer imgDiff=new ImageDiffer();
      ImageDiff diff=imgDiff.makeDiff(expectedImage, actualImage);
      if(diff.hasDiff()==true) {//compare two images
    	  System.out.println("images are not same");
      }else {
    	  System.out.println("images are same");
      }
      driver.quit();
	}
	

}
