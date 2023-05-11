import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class TestScript {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */

	@Given("Navigate to Subscribe URL")
	def NavigateToL() {
		CustomKeywords.'common.test.navigateTo'(GlobalVariable.SubscribeURL)
	}

	@When("Change and check Type price and currency")
	def ChangeCountry() {
		
		CustomKeywords.'common.test.planType'()
		
		CustomKeywords.'common.test.planPrice'('SA')
		
		WebUI.verifyElementVisible(findTestObject('country btn'))
		
		WebUI.click(findTestObject('country btn'))
		
		WebUI.verifyElementVisible(findTestObject('kw btn'))
		
		WebUI.click(findTestObject('kw btn'))
		
		CustomKeywords.'common.test.planType'()
		
		CustomKeywords.'common.test.planPrice'('Kuwait')
		
		WebUI.click(findTestObject('country btn'))
		
		WebUI.verifyElementVisible(findTestObject('bh btn'))
		
		WebUI.click(findTestObject('bh btn'))
		
		CustomKeywords.'common.test.planType'()
		
		CustomKeywords.'common.test.planPrice'('Baharin')
		
	}

	@Then("The data should display correctly and close browser")
	def ValidateData() {
		WebUI.closeBrowser()
	}
}