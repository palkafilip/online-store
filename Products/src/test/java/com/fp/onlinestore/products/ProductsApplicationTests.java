package com.fp.onlinestore.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
class ProductsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetProducts() throws Exception {
        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"Słuchawki bezprzewodowe\",\"category\":\"Elektornika\",\"description\":\"\",\"quantity\":60,\"pricePerItem\":249.99},{\"id\":2,\"name\":\"Czytnik ebook\",\"category\":\"Elektornika\",\"description\":\"Kolor czarny\",\"quantity\":10,\"pricePerItem\":435.0},{\"id\":3,\"name\":\"Monitor LG\",\"category\":\"Elektornika\",\"description\":\"22 cale\",\"quantity\":5,\"pricePerItem\":1200.0},{\"id\":4,\"name\":\"Buty zimowe\",\"category\":\"Obuwie\",\"description\":\"Bardzo ciepłe\",\"quantity\":12,\"pricePerItem\":140.0}]"));
    }

    @Test
    void testGetProductWithId2() throws Exception {
        mockMvc.perform(get("/products/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":2,\"name\":\"Czytnik ebook\",\"category\":\"Elektornika\",\"description\":\"Kolor czarny\",\"quantity\":10,\"pricePerItem\":435.0}"));
    }

    @Test
    void testGetProductWithId999() throws Exception {
        mockMvc.perform(get("/products/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"message\":\"Product with id = [999] was not found\",\"status\":404}"));
    }

}
