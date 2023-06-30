package api;

import api.responseDto.PlayerResponseDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

@Log4j
public abstract class BaseApi {

    @Step("Get")
    public static Response get(String endPoint) {
        return given()
                .when()
                .log().all()
                .get(endPoint)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Post")
    public static Response post(Object body, String endPoint) {
        return given()
                .body(body)
                .when()
                .log().all()
                .post(endPoint)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Put")
    public static Response put(Object body, String endPoint) {
        return given()
                .body(body)
                .when()
                .log().all()
                .put(endPoint)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Delete")
    public static Response delete(Object body, String endPoint) {
        return given()
                .body(body)
                .when()
                .log().all()
                .delete(endPoint)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Deserialization")
    protected static <T> T deserialization(Response response, Class<T> tClass) {
        log.info("deserialization class " + tClass.getName());
        return response.as(tClass);
    }

    @Step("Deserialization list")
    public static <T> List<T> deserializationList(Response response, String path, Class<T> classT) {
        log.info("deserialization list  " + classT.getName());
        return response.jsonPath().getList(path, classT);
    }

    @Step("Assert status code")
    public static void assertStatusCode(Response response, int defultStatus, int negativeStatus) {
        defultStatus = (negativeStatus == 0) ? defultStatus : negativeStatus;
        log.info("assert status code " + defultStatus);
        Assert.assertEquals(response.getStatusCode(), defultStatus);
    }

    @Step("Get negative status")
    public static int getNegativeStatus(int... status) {
        log.info("get negative status" + status);
        return Arrays.stream(status).findFirst().orElse(0);
    }
}
