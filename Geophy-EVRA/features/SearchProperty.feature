@SearchFeature
Feature: Search Property for Valuation
   In order to test Search Page of application
   As a Registered user
   I want to specify the features of Search page

  Background: Login flow
    Given I navigate to EVRA login page
    When I enter the Email "qaskillschallenge@geophy.com"
    And I enter the password "qaskillschallenge@geophy.com"
    And I click on LOG IN button
    Then the following text must be visible "Welcome back, QA Geophy."

  @SearchFeature01
  Scenario: SearchProperty_001 User should able to run valuation for the Property
    When I enter address as "555 N College Avenue, Tempe, AZ, 85281"
    And I enter Net operating income as 2000000
    And I enter Number of units as 200
    And I enter Year of Construction as 2000
    And I enter Occupancy as 80
    And Run Valuation button must be enabled
    And I click on the button RUN VALUATION
    Then Run Valuation search in progress
    And Property "555 N COLLEGE AVE, TEMPE, AZ 85281" must be displayed on report page
    And I click on "Logout" button

  @SearchFeature02
  Scenario: SearchProperty_002 Run Valuation button is disabled if all mandatory information is not provided
    When I enter address as "555 N College Avenue, Tempe, AZ, 85281"
    And I enter Net operating income as 2000000
    And I enter Year of Construction as 2000
    And I enter Occupancy as 80
    Then Run Valuation button must be disabled

  @SearchFeature03
  Scenario Outline: SearchProperty_003 Verify Property details on report page
    When I enter address as "<address>"
    And I enter Net operating income as <NOI>
    And I enter Number of units as <NumOfUnits>
    And I enter Year of Construction as <YearOfConstruction>
    And I enter Occupancy as <occupancy>
    And Run Valuation button must be enabled
    And I click on the button RUN VALUATION
    Then Run Valuation search in progress
    And Property "<Address_verification>" must be displayed on report page
    And Verify all details on entered on reports page
      | NOI         | NumOfUnits | YearOfConstruction | occupancy |
      | $ 2,000,000 |         10 |               2000 |        80 % |

    Examples: 
      | address                                | NOI     | NumOfUnits | YearOfConstruction | occupancy | Address_verification               |
      | 555 N College Avenue, Tempe, AZ, 85281 | 2000000 |         10 |               2000 |        80 | 555 N COLLEGE AVE, TEMPE, AZ 85281 |
