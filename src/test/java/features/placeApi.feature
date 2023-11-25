Feature: Validating Place API

  @AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>"  "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created using addPlaceApi contains matching "<name>" using "getPlaceAPI"

    Examples:
      | name   | language | address            |
      | Nitish | English  | World cross center |
    #  | Abc     | German   | Trade center       |

  @UpdatePlace @Regression
  Scenario: Verify if UpdatePlaceApi is working
    Given UpdatePlace Payload
    When user calls "updatePlaceAPI" with "PUT" http request
    Then the API call got success with status code 200
    And "msg" in response body is "Address successfully updated"

  @DeletePlace @Regression
  Scenario: Verify if Place added in AddPlaceApi can be deleted usingDeletePlaceApi
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"

