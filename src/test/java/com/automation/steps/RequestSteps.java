package com.automation.steps;

import com.automation.pojo.CreateBookingPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RequestSteps {

    @Given("user wants to call {string} endpoint")
    public void user_wants_to_call_endpoint(String endPoint) {
        if(endPoint.contains("@id")){
            endPoint = endPoint.replace("@id", ConfigReader.getConfigValue("booking.id"));
        }
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        if(value.contains("@token")){
            value = value.replace("@token", ConfigReader.getConfigValue("api.token"));
        }
        RestAssuredUtils.setHeader(key, value);
    }

    @Given("set request body from the file {string}")
    public void set_request_body_from_the_file(String fileName) {
        RestAssuredUtils.setBody(fileName);
    }

    @When("user performs post call")
    public void user_performs_post_call() {
        RestAssuredUtils.post();
    }


    @When("user performs put call")
    public void userPerformsPutCall() {
        RestAssuredUtils.put();
    }

    @And("set request body from the file {string} using pojo")
    public void setRequestBodyFromTheFileUsingPojo(String fileName) throws JsonProcessingException {
        String jsonFolderPath = ConfigReader.getConfigValue("json.folder.path");
        String jsonBody = RestAssuredUtils.getDataFromFile(jsonFolderPath + fileName);

        ObjectMapper om = new ObjectMapper();
        CreateBookingPojo createBookingPojo = om.readValue(jsonBody, CreateBookingPojo.class);

        createBookingPojo.setTotalprice(999);
        createBookingPojo.setFirstname("New Testq");

        RestAssuredUtils.setBodyUsingPojo(createBookingPojo);


    }
}
