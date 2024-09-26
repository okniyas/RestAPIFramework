package com.automation.utils;

import com.automation.pojo.CreateBookingPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {
    static RequestSpecification requestSpecification = RestAssured.given();
    static String endPoint;
    static Response response;

    public static void setEndPoint(String endPoint){
        RestAssuredUtils.endPoint = endPoint;
    }

    public static void setHeader(String key, String value){
        requestSpecification = requestSpecification.header(key, value);
    }

    public static void setBody(String fileName){
        String jsonFolderPath = ConfigReader.getConfigValue("json.folder.path");

        requestSpecification = requestSpecification.body(getDataFromFile(jsonFolderPath + fileName));
    }

    public static int getStatusCode(){
        return response.getStatusCode();
    }

    public static Response get(){

        requestSpecification.log().all();
        response = requestSpecification.get(endPoint);
        response.then().log().all();
        return response;
    }

    public static Response post(){

        requestSpecification.log().all();
        response = requestSpecification.post(endPoint);
        response.then().log().all();
        return response;
    }

    public static Response put(){

        requestSpecification.log().all();
        response = requestSpecification.put(endPoint);
        response.then().log().all();
        return response;
    }

    public static String getDataFromFile(String filePath) {
        String content = null;
        try {
            content = new Scanner(new FileInputStream(filePath)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return content;

    }

    public static String getResponseValue(String jsonPath){
        return response.jsonPath().getString(jsonPath);
    }

    public static void setBodyUsingPojo(Object object) {
        requestSpecification = requestSpecification.body(object);
    }
}
