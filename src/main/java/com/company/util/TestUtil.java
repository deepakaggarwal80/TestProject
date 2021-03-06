package com.company.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.company.base.ActionLibrary;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestUtil extends ActionLibrary{
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=20;
	public static long EXPLICIT_WAIT=20;
	
	public static Logger log = LogManager.getLogger();
	
	static Workbook book;
	static Sheet sheet;
	

	public static Object[][] getTestData(String sheetName){
		FileInputStream file=null;
		try {
			file = new FileInputStream("somefile.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book=WorkbookFactory.create(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j]=sheet.getRow(i).getCell(j).toString(); 
			}

		}
		return data;
	}

	public static String takeScreenShot() throws IOException{
		String path;
//		String isFullPageScreenshot= System.getProperty("project.isFullPageScreenshot",prop.getProperty("isFullPageScreenshot"));
		String isFullPageScreenshot= prop.getProperty("isFullPageScreenshot");
		String currentDir =System.getProperty("user.dir");
		if (isFullPageScreenshot.equalsIgnoreCase("true")) {
			Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(getDriver());
			path=currentDir+"/test-output/screenshots/"+"FP_"+System.currentTimeMillis()+".png";
			ImageIO.write(fpScreenshot.getImage(),"PNG",new File(path));
		} else {
			File scrFile=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			path = currentDir+"/test-output/screenshots/"+System.currentTimeMillis()+".png";
			FileUtils.copyFile(scrFile, new File(path));
		}
		return path;
	}
	
	public String generateUniqueAlphaString(int length) {
		String uniqueString = RandomStringUtils.randomAlphabetic(length);
		return uniqueString;
	}

	public String generateRandomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}
	
	public int getRandomNumberAsInt(int length) {
		return Integer.valueOf(RandomStringUtils.randomNumeric(length));
	}

	
	public String generateRandomNumber(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt(max - min + 1) + min;
		return String.valueOf(randomNum);
	}
	
	public boolean checkIfFileExists(String fileName) {
		boolean found = false;

		try {
			File file = new File(fileName);
			if (file.exists() && file.isFile()) {
				found = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return found;
	}

	public boolean checkIfFolderExists(String folderName) {
		boolean found = false;

		try {
			File file = new File(folderName);
			if (file.exists() && file.isDirectory()) {
				found = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return found;
	}


}
