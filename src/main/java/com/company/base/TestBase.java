package com.company.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.company.util.TestUtil;
import com.company.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public static Properties prop;
	
	public static ExtentReports extentReports=new ExtentReports();
	public static ExtentTest extentTestLogger;
	public TestBase() { 
		try {
			prop=new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\company\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void initialization() throws MalformedURLException {
//		String browserName= prop.getProperty("browser");
//		String browserName= System.getProperty("browser",prop.getProperty("browser"));
		String browserName= System.getProperty("browser");
		
		String gridEnabled=System.getProperty("isGridEnabled");
		if (browserName.equalsIgnoreCase("chrome")){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.setHeadless(false);
//			WebDriverManager.chromedriver().version("74.0.3729.6").setup();
			if (gridEnabled.equalsIgnoreCase("false")) {
			System.setProperty("webdriver.chrome.driver", "\\DProject\\Downlaods\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver(options);
			}
			else if (gridEnabled.equalsIgnoreCase("true")) {
//				Add desired capabilities
				DesiredCapabilities cap =new DesiredCapabilities();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.WIN10);
				cap.acceptInsecureCerts();
				options.merge(cap);
				
				String hubURL="http://192.168.1.105:4444/wd/hub";
//				String hubURL="http://192.168.99.100:4444/wd/hub";
				driver = new RemoteWebDriver(new URL(hubURL), options);
			
			}
		}
		else if (browserName.equalsIgnoreCase("firefox")){
			if (gridEnabled.equalsIgnoreCase("false")) {
			System.setProperty("webdriver.gecko.driver", "C:\\DProject\\Downlaods\\geckodriver\\geckodriver.exe");
			FirefoxOptions options=new FirefoxOptions();
			options.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver();
			}
			else if (gridEnabled.equalsIgnoreCase("true")) {
				
			}
		}
		else if (browserName.equalsIgnoreCase("ie")){
			if (gridEnabled.equalsIgnoreCase("false")) {
			System.setProperty("webdriver.edge.driver", "C:\\DProject\\Downlaods\\edgedriver\\msedgedriver.exe");
//			DesiredCapabilities capabilities= new DesiredCapabilities().edge();
			driver = new EdgeDriver();
			}
			else if (gridEnabled.equalsIgnoreCase("false")) {
				
			}
		}
		e_driver =new EventFiringWebDriver(driver);
//		now create object of eventlistnerHandler to register it with EventFiringWebDriver
		eventListener= new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
//		driver.get(prop.getProperty("url"));
		driver.get(System.getProperty("url"));
		
	}
}
