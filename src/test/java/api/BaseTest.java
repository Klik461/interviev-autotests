package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    @BeforeSuite
    public void setUpSpecification() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://3.68.165.45")
                .setContentType(ContentType.JSON)
                .build();
    }
}
