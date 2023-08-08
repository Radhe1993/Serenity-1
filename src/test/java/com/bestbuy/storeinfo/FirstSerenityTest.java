package com.bestbuy.storeinfo;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FirstSerenityTest {
    @Test
    public void getAllStoreInfo() {

        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
