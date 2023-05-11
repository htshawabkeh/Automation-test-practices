
Feature: Validate The subscription Packages 
Scenario: Validate The subscription Packages Type & Price and Currency for all Countries (SA, Kuwait and Baharin)
    
    Given Navigate to Subscribe URL
    When Change and check Type price and currency
    Then The data should display correctly and close browser

    #Examples: 
      #| country | 
      #| SA |    
      #| Kuwait |
      #| Baharin |