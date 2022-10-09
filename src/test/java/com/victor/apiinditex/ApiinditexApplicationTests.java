package com.victor.apiinditex;

import com.victor.apiinditex.price.infrastructure.generate.model.PriceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.victor.apiinditex.datatest.PriceDataTest.buildURL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ApiinditexApplicationTests {

    private final Integer PRODUCT_ID = 35455;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int randomServerPort;

    @Test
    void testOneShouldReturnPriorityEqualOne() {
        final ResponseEntity<PriceResponse> response = this.invoke("2020-06-14-10.00.00");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(this.PRODUCT_ID, response.getBody().getProductId());
    }

    @Test
    void testTwoShouldReturnPriorityEqualTwo() {
        final ResponseEntity<PriceResponse> response = this.invoke("2020-06-14-16.00.00");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getPriceList());
    }

    @Test
    void testThreeShouldReturnPriorityEqualOne() {
        final ResponseEntity<PriceResponse> response = this.invoke("2020-06-14-21.00.00");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getPriceList());
    }

    @Test
    void testForShouldReturnPriorityEqualThree() {
        final ResponseEntity<PriceResponse> response = this.invoke("2020-06-15-10.00.00");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().getPriceList());
    }

    @Test
    void testFiveShouldReturnPriorityEqualFor() {
        final ResponseEntity<PriceResponse> response = this.invoke("2020-06-16-21.00.00");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(4, response.getBody().getPriceList());
    }

    @Test
    void testNotDataFound() {
        final ResponseEntity<PriceResponse> response = this.invoke("2022-06-16-21.00.00");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private ResponseEntity<PriceResponse> invoke(final String applyDate) {
        final Integer brandId = 1;
        return this.restTemplate
                .getForEntity(buildURL(this.randomServerPort, this.PRODUCT_ID, brandId, applyDate), PriceResponse.class);

    }

}

