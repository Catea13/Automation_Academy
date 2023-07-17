package Testss;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class RestAssuredAPITest {

        @Test
        public void GetBody() {
            RestAssured.baseURI = "http://saucedemo.com/";
            RequestSpecification httpRequest = given();
            Response response = httpRequest.request(Method.GET, "");
            System.out.println("Response=>" + response.prettyPrint());


        }
        @Test
        public void getStatus(){
            RestAssured.baseURI="http://saucedemo.com/";
            RequestSpecification httpRequest=given();
            Response response=httpRequest.get();
            int status=response.getStatusCode();
            Assert.assertEquals(status,200);


        }
        @Test
        public void login(){
            RestAssured.baseURI="http://saucedemo.com/";
            RequestSpecification request = RestAssured.given().auth().basic("standard_user", "secret_sauce");
            Response response = request.get();
            System.out.println(response.asString());}
        }
