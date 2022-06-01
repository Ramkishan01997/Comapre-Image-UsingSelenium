package barcodeAutomation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class ReadTextFromBarCode {

	public static void main(String[] args) throws IOException, NotFoundException {
		System.setProperty("webdriver.edge.driver", "C://BrowserDriver//chrome//msedgedriver.exe");
		WebDriver driver=new EdgeDriver();// edge driver
		driver.get("https://testautomationpractice.blogspot.com/");
		
		String qrCodeURL=driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/footer/div/div[2]/div[2]/table/tbody/tr/td[2]/div/div[2]/div[1]/img")).getAttribute("src");
		
		URL url=new URL(qrCodeURL);
		
		BufferedImage bufferedImage=ImageIO.read(url);
		
		LuminanceSource luminanceSource=new BufferedImageLuminanceSource(bufferedImage);
		
		BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(luminanceSource));
		
		Result result=new MultiFormatReader().decode(binaryBitmap);
		
		System.out.println(result.getText());
		driver.close();		
	}

}
	