package com.anxstra.catalog;

import com.anxstra.catalog.dto.ProductDto;
import com.anxstra.catalog.dto.ProductListDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static com.anxstra.catalog.constants.MessageConstants.PRODUCT_NOT_FOUND;
import static com.anxstra.catalog.constants.PathConstants.PRODUCTS_PATH;
import static com.anxstra.catalog.constants.PathConstants.PRODUCT_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends ContainerSetup {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("get all without paging")
    void withoutPaging_GetAll_ShouldReturnAll() throws Exception {
        var getAllRequest = MockMvcRequestBuilders.get(PRODUCTS_PATH)
                                                  .param("category", "106");

        var response = mockMvc.perform(getAllRequest)
                              .andDo(print())
                              .andExpectAll(
                                      status().isOk(),
                                      content().contentType(MediaType.APPLICATION_JSON),
                                      jsonPath("$.code").value(HttpStatus.OK.value())
                              )
                              .andReturn();

        JsonNode body = mapper.readTree(response.getResponse().getContentAsString()).get("body");
        List<ProductListDto> result = mapper.readValue(body.toString(),
                mapper.getTypeFactory().constructCollectionType(List.class, ProductListDto.class));

        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("get all with paging")
    void withPaging_GetAll_ShouldReturnPart() throws Exception {
        var getAllRequest = MockMvcRequestBuilders.get(PRODUCTS_PATH)
                                                  .param("category", "106")
                                                  .param("pageNumber", "1")
                                                  .param("pageSize", "2");

        var response = mockMvc.perform(getAllRequest)
                              .andDo(print())
                              .andExpectAll(
                                      status().isOk(),
                                      content().contentType(MediaType.APPLICATION_JSON),
                                      jsonPath("$.code").value(HttpStatus.OK.value())
                              )
                              .andReturn();

        JsonNode body = mapper.readTree(response.getResponse().getContentAsString()).get("body");
        List<ProductListDto> result = mapper.readValue(body.toString(),
                mapper.getTypeFactory().constructCollectionType(List.class, ProductListDto.class));

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("get all with wrong paging properties")
    void withIncorrectPaging_GetAll_ShouldFail() throws Exception {
        var getAllRequest = MockMvcRequestBuilders.get(PRODUCTS_PATH)
                                                  .param("category", "106")
                                                  .param("pageNumber", "-1")
                                                  .param("pageSize", "0");

        mockMvc.perform(getAllRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(Matchers.notNullValue())
               );
    }

    @Test
    @DisplayName("get by Id")
    void withCorrectId_GetById_ShouldReturnProduct() throws Exception {
        var getByIdRequest = MockMvcRequestBuilders.get(PRODUCT_PATH, 101);

        mockMvc.perform(getByIdRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value()),
                       jsonPath("$.body.id").value(101)
               );
    }

    @Test
    @DisplayName("get by wrong Id")
    void withWrongId_GetById_ShouldFail() throws Exception {
        var getByIdRequest = MockMvcRequestBuilders.get(PRODUCT_PATH, 1);

        mockMvc.perform(getByIdRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(PRODUCT_NOT_FOUND)
               );
    }

    @Test
    @DisplayName("create product with correct data and authorities")
    @WithMockUser(authorities = {"ADMIN"})
    void withCorrectDataAndAuthorities_CreateNewProduct_ShouldSuccess() throws Exception {
        var productDto = new ProductDto("test", new BigDecimal(100), 50, 7,
                "url", 104L, 101L, null);

        var createProductRequest = MockMvcRequestBuilders.post(PRODUCTS_PATH)
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(productDto));

        mockMvc.perform(createProductRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value())
               );
    }

    @Test
    @DisplayName("create product with correct data and wrong authorities")
    @WithMockUser(authorities = {"USER"})
    void withCorrectDataAndWrongAuthorities_CreateNewProduct_ShouldFail() throws Exception {
        var productDto = new ProductDto("test", new BigDecimal(100), 50, 7,
                "url", 104L, 101L, null);

        var createProductRequest = MockMvcRequestBuilders.post(PRODUCTS_PATH)
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(productDto));

        mockMvc.perform(createProductRequest)
               .andDo(print())
               .andExpectAll(
                       status().isForbidden()
               );
    }

    @Test
    @DisplayName("create product with wrong data and correct authorities")
    @WithMockUser(authorities = {"ADMIN"})
    void withWrongDataAndCorrectAuthorities_CreateNewProduct_ShouldFail() throws Exception {
        var productDto = new ProductDto("", new BigDecimal(-12), -1, -11,
                "", null, null, null);

        var createProductRequest = MockMvcRequestBuilders.post(PRODUCTS_PATH)
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(productDto));

        mockMvc.perform(createProductRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(Matchers.notNullValue())
               );
    }

    @Test
    @DisplayName("update product with correct data and authorities")
    @WithMockUser(authorities = {"ADMIN"})
    void withCorrectDataAndAuthorities_UpdateProduct_ShouldSuccess() throws Exception {
        var productDto = new ProductDto("test", new BigDecimal(100), 50, 7,
                "url", 104L, 101L, 0L);

        var updateProductRequest = MockMvcRequestBuilders.put(PRODUCT_PATH, 101)
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(productDto));

        mockMvc.perform(updateProductRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value())
               );
    }

    @Test
    @DisplayName("update product with correct data and wrong authorities")
    @WithMockUser(authorities = {"USER"})
    void withCorrectDataAndWrongAuthorities_UpdateProduct_ShouldFail() throws Exception {
        var productDto = new ProductDto("test", new BigDecimal(100), 50, 7,
                "url", 104L, 101L, 0L);

        var updateProductRequest = MockMvcRequestBuilders.put(PRODUCT_PATH, 101)
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(productDto));

        mockMvc.perform(updateProductRequest)
               .andDo(print())
               .andExpectAll(
                       status().isForbidden()
               );
    }

    @Test
    @DisplayName("update product with wrong data and correct authorities")
    @WithMockUser(authorities = {"ADMIN"})
    void withWrongDataAndCorrectAuthorities_UpdateProduct_ShouldFail() throws Exception {
        var productDto = new ProductDto("", new BigDecimal(-12), -5, -15,
                "", null, null, 0L);

        var updateProductRequest = MockMvcRequestBuilders.put(PRODUCT_PATH, 101)
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(productDto));

        mockMvc.perform(updateProductRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(Matchers.notNullValue())
               );
    }

    @Test
    @DisplayName("delete product with correct authorities")
    @WithMockUser(authorities = {"ADMIN"})
    void withCorrectAuthorities_deleteProduct_ShouldSuccess() throws Exception {
        var deleteProductRequest = MockMvcRequestBuilders.delete(PRODUCT_PATH, 101);

        mockMvc.perform(deleteProductRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value())
               );
    }

    @Test
    @DisplayName("delete product with correct authorities")
    @WithMockUser(authorities = {"USER"})
    void withWrongAuthorities_deleteProduct_ShouldFail() throws Exception {
        var deleteProductRequest = MockMvcRequestBuilders.delete(PRODUCT_PATH, 101);

        mockMvc.perform(deleteProductRequest)
               .andDo(print())
               .andExpectAll(
                       status().isForbidden()
               );
    }
}
