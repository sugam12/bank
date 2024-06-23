package com.simple.bank.unit;

//@WebMvcTest(ProductController.class)
public class ProductControllerTest {


   /* @Test
    void shouldGetProductDetails() throws Exception {
//        api: GET /api/products/id/1 ==> 200 : ProductResponseDTO
        ProductResponseDTO expected = getProdutDetailsResponseDTO(1, "Iphone12", "Electronics", 60000);

        when(productService.getProduct(1L)).thenReturn(expected);
//        mockMvc.perform(get("/api/products/id/1")).andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Iphone12"))
//                .andExpect(jsonPath("$.category").value("Electronics"))
//                .andExpect(jsonPath("$.price").value(60000));
        MvcResult mvcResult = mockMvc.perform(get("/api/products/id/1")).andDo(print()).andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ProductResponseDTO productResponseDTO = new ObjectMapper().readValue(contentAsString, ProductResponseDTO.class);
        assertThat(productResponseDTO).isEqualTo(expected);*/
}
