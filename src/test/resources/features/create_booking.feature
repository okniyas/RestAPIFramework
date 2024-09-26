Feature: Validate booking post request

  Scenario: Verify user can create booking
    Given user wants to call "/booking" endpoint
    And set header "Content-Type" to "application/json"
    And set request body from the file "create_booking.json"
    When user performs post call
    Then verify status code is 200

    Scenario: update booking
      Given user wants to call "/booking" endpoint
      And set header "Content-Type" to "application/json"
      And set request body from the file "create_booking.json"
      When user performs post call
      Then verify status code is 200

      And verify booking is not empty
      And store created booking id into "booking.id"

      When user wants to call "/auth" endpoint
      And set header "Content-Type" to "application/json"
      And set request body from the file "create_token.json"
      When user performs post call
      Then verify status code is 200

      And store token value into "api.token"

      When user wants to call "/booking/@id" endpoint
      And set header "Content-Type" to "application/json"
      And set header "Cookie" to "token=@token"
      And set request body from the file "update_booking.json"
      And set request body from the file "create_booking.json" using pojo
      When user performs put call
      Then verify status code is 200

