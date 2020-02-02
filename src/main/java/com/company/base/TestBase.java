package com.company.base;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.company.pages.HomePage;
import com.company.util.TestUtil;
import com.company.util.WebEventListener;

public class TestBase {

	private static WebDriver driver;
	private static ThreadLocal<WebDriver> ltDriver= new ThreadLocal<>();
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static ThreadLocal<ExtentTest> ETLog;
	public static Logger log = LogManager.getLogger();
//	public static TreeMap<String, String> objMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	public static TreeMap<Object, String> objMap = new TreeMap<Object, String>();
	
	public static String locValue;
	public static Field[] fields;
	public static SoftAssert softAssert = new SoftAssert();
	public static Properties prop;
	
	public static WebDriver getDriver() {
		return ltDriver.get();
	}

	public static void initialization() throws MalformedURLException {
		try {
			prop=new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\company\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String browserName= prop.getProperty("browser");
		//		String browserName= System.getProperty("browser",prop.getProperty("browser"));
		//		String browserName= System.getProperty("browser");
		//		
		String gridEnabled=prop.getProperty("isGridEnabled");

		if (browserName.equalsIgnoreCase("chrome")){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.setHeadless(false);
			//			WebDriverManager.chromedriver().version("74.0.3729.6").setup();
			if (gridEnabled.equalsIgnoreCase("false")) {
				System.setProperty("webdriver.chrome.driver", "\\DProject\\Downloads\\chromedriver\\chromedriver.exe");
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
//		e_driver =new EventFiringWebDriver((WebDriver) driver);
//		//		now create object of eventlistnerHandler to register it with EventFiringWebDriver
//		eventListener= new WebEventListener();
//		e_driver.register(eventListener);
//		driver=e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		ltDriver.set(driver);
//		cacheObjRepository();
//		setDriver(driver);
		driver.get(prop.getProperty("url"));
		//		driver.get(System.getProperty("url"));

	}
	
	public static ThreadLocal<ExtentTest> getETLog() {
		return ETLog;
	}
	
	public static void setETLog(ThreadLocal<ExtentTest> eTLogger) {
		ETLog = eTLogger;
	}


	
	public static void testLog(boolean status, String message) {

		if (status) {
			log.info(message);
			ETLog.get().pass(message);
		}
		else {
			log.error(message);
			try {
				ETLog.get().fail("<a class=\"image-link\" href=" + TestUtil.takeScreenShot() +">Screenshot</a><br>" + message);
				assertTrue(status,message);
//				ETLog.get().addScreenCaptureFromPath(TestUtil.takeScreenShot());
			} catch (IOException e) {
				e.printStackTrace();
			}
//			assertTrue(status);
		}
	}
	
	public static void testLog(String message) {

		log.info(message);
		ETLog.get().info(message);

	}
		
	
	public static void testLogAndContinue(boolean status, String message) {

		if (status) {
			log.info(message);
			ETLog.get().pass(message);
		}
		else {
			log.error(message);
			try {
				ETLog.get().fail("<a class=\"image-link\" href=" + TestUtil.takeScreenShot() +">Screenshot</a><br>" + message);
//				ETLog.get().addScreenCaptureFromPath(TestUtil.takeScreenShot());
				softAssert.assertTrue(status);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}
	
	public void closeBrowser() {
		try {
			if (getDriver() != null) {
				getDriver().quit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getLocValue() {
		return locValue;
	}
	
	public static void setLocValue(String locValue) {
		TestBase.locValue = locValue;
	}
	
	public String getLocatorValue(WebElement webElement) {
		String locValue=webElement.toString().split("->")[1]; 
		setLocValue(locValue);
		return locValue;
	}
	
	public String getObjName(WebElement webElement) {
		return (String) objMap.get(webElement.toString());
	}
	
	public static void cacheObjects(Object[] obj) throws IllegalArgumentException, IllegalAccessException {
		for (int j=0;j<obj.length;j++) {
			fields=obj[j].getClass().getFields();
			for (int i=0;i< fields.length;i++) {
				System.out.println("Value of Field "+ fields[i].getName());
				System.out.println("Value of Field "+ fields[i].get(obj[j]).toString());
				
				
				objMap.put(fields[i].get(obj[j]), fields[i].getName());
			}
		}
		
	}

	public static void cacheObjRepository() {
		String key = "";
		String value = "";
		Field[] fields;
		
		try {
			fields = HomePage.class.getDeclaredFields();
			Constructor<?> constructor = HomePage.class.getConstructor();
			Object object = constructor.newInstance();

			for (int j = 0; j < fields.length; ++j) {
				value = fields[j].getName();
				if (fields[j].get(object) == null) {
					break;
				}

				key = fields[j].get(object).toString();
				objMap.put(key, value);
			}
			System.out.println(objMap);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
	}
	
//	@AfterMethod
//	public void afterMethod() {
//		softAssert.assertAll();
//	}

	@BeforeClass
	public void setUp() {
		try {
			initialization();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}


}
