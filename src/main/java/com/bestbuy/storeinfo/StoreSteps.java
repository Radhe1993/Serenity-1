package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;

public class StoreSteps {

    @Step("Cresting store : name :{0},type :{1},address :{2},address2 :{3},city :{4},state :{5},zip :{6},lat :{7},lng :{8}, hours :{9}")
    public ValidatableResponse creatStore1(String name, String type, String address, String address2, String city , String state, String zip, double lat, double lng,String hours) {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);


        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .when()
                .body(storePojo)
                .post()
                .then().log().body().statusCode(201);

    }

    @Step("Getting all store data")
    public void getAllStoreInfo() {


        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Step("verification if the store was added in appilication")
    public void findStroresWithServiceName(String serviceName) {

          int sId = given()
                .queryParam("service.name", serviceName)
                .when()
                .get()
                .then().statusCode(200)
                .extract()
                .path("total");

    }
    @Step("findStore")
        public void findStores(String state){

             int sId=given()
                    .queryParam("state",state)
                    .when()
                    .get()
                    .then().statusCode(200)
                    .extract()
                    .path("total");
            System.out.println("value of ID"+sId);

        }

        @Step("Update store")
        public ValidatableResponse updateID(String name, String type, String address, String address2, String city, String state, String zip, double lat, double lng ){
             int id = 8951;
            StorePojo storePojo=new StorePojo();
            storePojo.setName(name);
            storePojo.setType(type);
            storePojo.setAddress(address);
            storePojo.setAddress2(address2);
            storePojo.setCity(city);
            storePojo.setState(state);
            storePojo.setZip(zip);
            storePojo.setLat(lat);
            storePojo.setLng(lng);
            storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");

         return    SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParams("id", id)
                    .body(storePojo)
                    .when()
                    .patch(EndPoints.UPDATE_STORE_BY_id)
                    .then().log().all().statusCode(200);

        }

        @Step("Delete Store")
                public void deleteId(){
            int id = 8951;
                given()
                        .pathParam("id", id)
                        .when()
                        .delete(EndPoints.DELETE_STORE_BY_ID)
                        .then()
                        .statusCode(200);

                given()
                        .pathParam("id", id)
                        .when()
                        .get(EndPoints.DELETE_STORE_BY_ID)
                        .then().statusCode(404);
                }




    }


