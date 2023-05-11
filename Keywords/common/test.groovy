package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.util.internal.PathUtil as PathUtil
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import org.junit.After as After
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import org.apache.poi.ss.usermodel.Row as Row
import org.apache.poi.ss.usermodel.Cell as Cell
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.apache.poi.ss.usermodel.BorderStyle as BorderStyle
import org.apache.poi.ss.usermodel.FillPatternType as FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors as IndexedColors
import org.apache.poi.ss.usermodel.Sheet as Sheet
import org.apache.poi.xssf.usermodel.XSSFCellStyle as XSSFCellStyle

public class test {

	@Keyword
	def navigateTo(String url) {
		WebUI.openBrowser('')
		WebUI.deleteAllCookies()
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(url)
	}

	@Keyword
	def planType() {

		String path = RunConfiguration.getProjectDir() + '/Data Files/SubscriptionPackages.xlsx'

		InputStream excelFile = new FileInputStream(path)

		XSSFWorkbook wb = new XSSFWorkbook(excelFile)

		int lastRow = wb.getSheet('SA').getLastRowNum()

		XSSFSheet sheet = wb.getSheet('SA')

		int i = 1

		for (i; i < 3; i++) {
			String dynamicId = i

			String xpath = ('(//strong[@class="plan-title"])[' + dynamicId) + ']'

			TestObject to = new TestObject('objectName')

			to.addProperty('xpath', ConditionType.EQUALS, xpath)

			String text = WebUI.getText(to)

			def string = wb.getSheet('SA').getRow(i).getCell(1).getStringCellValue()

			System.err.println(string)

			if (WebUI.verifyElementText(to, string)) {
				sheet.getRow(i).createCell(3).setCellValue('Passed')

				Row row = sheet.getRow(i)

				XSSFCellStyle style = wb.createCellStyle()

				style.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex())

				style.setFillPattern(FillPatternType.SOLID_FOREGROUND)

				row.getCell(3).setCellStyle(style)

				FailureHandling.OPTIONAL

				KeywordUtil.markPassed('Passed')
			} else {
				sheet.getRow(i).createCell(3).setCellValue('Failed')

				Row row = sheet.getRow(i)

				XSSFCellStyle style = wb.createCellStyle()

				style.setFillForegroundColor(IndexedColors.ROSE.getIndex())

				style.setFillPattern(FillPatternType.SOLID_FOREGROUND)

				row.getCell(3).setCellStyle(style)

				KeywordUtil.markFailed('Failed')
			}

			FileOutputStream save = new FileOutputStream(path)

			wb.write(save)
		}
	}

	@Keyword
	def planPrice(String country){

		String path = RunConfiguration.getProjectDir() + '/Data Files/SubscriptionPackages.xlsx'

		InputStream excelFile = new FileInputStream(path)

		XSSFWorkbook wb = new XSSFWorkbook(excelFile)

		int lastRow = wb.getSheet(country).getLastRowNum()

		XSSFSheet sheet = wb.getSheet(country)

		int i = 4

		for (i; i < 7; i++) {
			String dynamicId = i - 3

			String xpath = ('(//div[contains(@id,"currency-")]/b)['+ dynamicId )+']'
			
			TestObject to = new TestObject('objectName')

			to.addProperty('xpath', ConditionType.EQUALS, xpath)

			String text = WebUI.getText(to)

			def string = wb.getSheet(country).getRow(i).getCell(1).getStringCellValue()
			
			sheet.getRow(i).createCell(4).setCellValue(text)

			if (WebUI.verifyElementText(to, string)) {
				sheet.getRow(i).createCell(3).setCellValue('Passed')

				Row row = sheet.getRow(i)

				XSSFCellStyle style = wb.createCellStyle()

				style.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex())

				style.setFillPattern(FillPatternType.SOLID_FOREGROUND)

				row.getCell(3).setCellStyle(style)

				FailureHandling.OPTIONAL

				KeywordUtil.markPassed('Passed')
			} else {
				sheet.getRow(i).createCell(3).setCellValue('Failed')

				Row row = sheet.getRow(i)

				XSSFCellStyle style = wb.createCellStyle()

				style.setFillForegroundColor(IndexedColors.ROSE.getIndex())

				style.setFillPattern(FillPatternType.SOLID_FOREGROUND)

				row.getCell(3).setCellStyle(style)

				KeywordUtil.markFailed('Failed')
			}

			FileOutputStream save = new FileOutputStream(path)

			wb.write(save)
		}
	}
}
