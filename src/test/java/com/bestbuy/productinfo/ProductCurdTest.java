package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.EndPoints1;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase1;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;

import static io.restassured.RestAssured.given;

//@RunWith(SerenityRunner.class)
public class ProductCurdTest extends TestBase1 {

  static String  name= "Pen";
  static String  type= "Hard Good";
  static String  upc= "12345676";
  static  double price =  98.99;
  static String description = "This is a cucumber.api.java.lu.a request for creating a new product.";
  static String model = "MN908760";
   static int id = 9999681;

    @Test
    public void getAllProductInfo() {

        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void createProduct()
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
    @Test
    public void getProductName()
    {

         SerenityRest.given().queryParams("$select[]","name").queryParams("$select[]","description")
               .basePath("/products")
               .header("Accept", "application/json")
               .when()
               .get()
               .then().log().body().statusCode(200);

    }

    @Test
    public void getHeightPrice()
    {
        SerenityRest.given().queryParam("$sort[price]","-1")
                .basePath("/products")
                .header("Accept", "application/json")
                .when()
                .get()
                .then().log().body().statusCode(200);

    }

    @Test
    public void UpdateProduct()
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

    @Test
    public void DeletProduct()
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
