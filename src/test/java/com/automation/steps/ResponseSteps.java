package com.automation.steps;

import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ResponseSteps {
    @Then("verify status code is {int}")
    public void verify_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getStatusCode());
    }

    @And("verify booking is not empty")
    public void verifyBookingIsNotEmpty() {
        String bookingId = RestAssuredUtils.getResponseValue("bookingid");
        Assert.assertTrue(!bookingId.isEmpty());
    }

    @And("store created booking id into {string}")
    public void storeCreatedBookingIdInto(String key) {
        ConfigReader.setConfigValue(key, RestAssuredUtils.getResponseValue("bookingid"));
    }

    @And("store token value into {string}")
    public void storeTokenValueInto(String key) {
        ConfigReader.setConfigValue(key, RestAssuredUtils.getResponseValue("token"));

    }
}
