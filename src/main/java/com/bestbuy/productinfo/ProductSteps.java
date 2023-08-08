package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.EndPoints1;
import com.bestbuy.model.DataPojo;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;

public class ProductSteps {

    @Step()
    public void getAllProductInfo() {

        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Step("Creating Product :name:{0},type :{1},upc :{2},price :{3},description :{4},model :{5}")
    public void createProduct(String name,String type,String upc,double price,String description,String model)
    {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName( name);
        productPojo.setType(type);
        productPojo.setUpc( upc);
        productPojo.setPrice( price);
        productPojo.setDescription( description);
        productPojo.setModel( model);

        SerenityRest. given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .post()
                .then().log().body().statusCode(201);


    }
    @Step("GetProductName")
    public void getProductName(String name,String description)
    {
        DataPojo dataPojo=new DataPojo();
        dataPojo.setName(name);
        dataPojo.setDescription(description);
        Response response = given()
                .basePath("/products")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Step("GetHightestPrice")
    public void getHeightPrice()
    {
        SerenityRest.given().queryParam("$sort[price]","-1")
                .basePath("/products")
                .header("Accept", "application/json")
                .when()
                .get()
                .then().log().body().statusCode(200);

    }
    @Step("UpdateProduct")
    public void UpdateProduct(String name,String type,String upc,double price,String description,String model,int id)
    {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName( name);
        productPojo.setType(type);
        productPojo.setUpc( upc);
        productPojo.setPrice( price);
        productPojo.setDescription( description);
        productPojo.setModel( model);

        SerenityRest. given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .pathParams("id", id)
                .when()
                .body(productPojo)
                .patch(EndPoints1.UPDATE_Products_BY_id)
                .then().log().body().statusCode(200);


    }
    @Step("DeleteProduct")

    public void deletProduct(int id)
    {
        given()
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then()
                .statusCode(200);

        given()
                .pathParam("id", id)
                .when()
                .get(EndPoints1.DELETE_Products_BY_ID)
                .then().statusCode(404);
    }

}
