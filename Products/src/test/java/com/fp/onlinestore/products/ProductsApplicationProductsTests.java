package com.fp.onlinestore.products;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductsApplicationProductsTests extends BaseProductsTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void testGetProducts() throws Exception {
        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"Słuchawki bezprzewodowe\",\"category\":\"Elektornika\",\"description\":\"\",\"quantity\":60,\"pricePerItem\":249.99},{\"id\":2,\"name\":\"Czytnik ebook\",\"category\":\"Elektornika\",\"description\":\"Kolor czarny\",\"quantity\":10,\"pricePerItem\":435.0},{\"id\":3,\"name\":\"Monitor LG\",\"category\":\"Elektornika\",\"description\":\"22 cale\",\"quantity\":5,\"pricePerItem\":1200.0},{\"id\":4,\"name\":\"Buty zimowe\",\"category\":\"Obuwie\",\"description\":\"Bardzo ciepłe\",\"quantity\":12,\"pricePerItem\":140.0}]"));
    }

    @Test
    @Order(2)
    void testGetProductWithId2() throws Exception {
        mockMvc.perform(get("/products/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":2,\"name\":\"Czytnik ebook\",\"category\":\"Elektornika\",\"description\":\"Kolor czarny\",\"quantity\":10,\"pricePerItem\":435.0}"));
    }

    @Test
    @Order(3)
    void testGetProductWithId999() throws Exception {
        mockMvc.perform(get("/products/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"message\":\"Product with id = [999] was not found\",\"status\":404}"));
    }

    @Test
    @Order(4)
    void testPurchaseProductWithId1() throws Exception {
        mockMvc.perform(post("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"customerId\": 1, \"quantity\": 4 }"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/products/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Słuchawki bezprzewodowe\",\"category\":\"Elektornika\",\"description\":\"\",\"quantity\":56,\"pricePerItem\":249.99}"));
    }

    @Test
    @Order(5)
    void testPurchaseProductWithTooBigQuantity() throws Exception {
        mockMvc.perform(post("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"customerId\": 1, \"quantity\": 999 }"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("{\"message\":\"Required quantity = [999] is bigger than available quantity = [60] for product with id = [1]\",\"status\":500}"));
    }

}
