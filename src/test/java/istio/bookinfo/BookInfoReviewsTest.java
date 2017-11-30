package istio.bookinfo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasKey;

public class BookInfoReviewsTest {

    @Test
    public void any_none_log_user_should_use_reviews_v1_version() {

        // given
        final RequestSpecification requestConfiguration = new RequestSpecBuilder()
            .setBaseUri("http://productpage-istio-system.192.168.64.16.nip.io")
            .build();

        // Cookie userCookie = new Cookie.Builder("user", "alex").build();

        // when
        given()
            .spec(requestConfiguration)
            .when()
            .get("api/v1/products/{productId}/reviews", 0)
            .then()
            .assertThat()
            .statusCode(200)
            .body("$.reviews", not(hasKey("rating")));

    }

}
