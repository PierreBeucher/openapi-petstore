package org.openapitools;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

class SimpleIT {

    @Test
    void simpleTest() {
        /*
        String response =
                given()
                    .parameters("username", "user", "password", "user",
                            "grant_type", "password", "scope", "write:pets read:pets",
                            "client_id", "sample-client-id", "client_secret", "secret")
                    .auth()
                    .preemptive()
                    .basic("sample-client-id","secret")
                    .when()
                    .post("/api/oauth/dialog")
                    .asString();

        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");
        System.out.println("token:" + accessToken);
        */

        given().
            filter((requestSpec, responseSpec, ctx) -> {
                requestSpec.header("api_key", "special-key");
                requestSpec.header("Accept", "application/json");
                return ctx.next(requestSpec, responseSpec);
            }).
        when().
            get("http://localhost:8080/v3/pet/1").
        then().
                contentType(JSON).
                body("name", equalTo("Cat 1"));
    }
}