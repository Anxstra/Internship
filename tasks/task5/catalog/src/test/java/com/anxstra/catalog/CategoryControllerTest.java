package com.anxstra.catalog;

import com.anxstra.catalog.dto.CategoryDto;
import com.anxstra.catalog.dto.CategoryTreeDto;
import com.anxstra.jooq.db.enums.CategoryType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.anxstra.catalog.constants.PathConstants.CATEGORIES_PATH;
import static com.anxstra.catalog.constants.PathConstants.CATEGORY_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest extends ContainerSetup {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CacheManager cacheManager;

    @Test
    @DisplayName("get all with cache check")
    void correct_GetAll_ShouldReturnCategoriesTreeAndCacheIt() throws Exception {
        var getByIdRequest = MockMvcRequestBuilders.get(CATEGORIES_PATH);

        mockMvc.perform(getByIdRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.body[0].type").value(CategoryType.GROUP.getLiteral()),
                       jsonPath("$.body[0].children").value(Matchers.notNullValue())
               );

        Cache cache = cacheManager.getCache("categories");
        List<CategoryTreeDto> categories = (List<CategoryTreeDto>) cache.get("categories").get();
        assertNotNull(categories);
        CategoryTreeDto categoryTreeDto = categories.get(0);
        assertEquals(CategoryType.GROUP.getLiteral(), categoryTreeDto.type());
        assertEquals(2, categoryTreeDto.children().size());
    }

    @Test
    @DisplayName("add new category with correct authorities and data")
    @WithMockUser(authorities = {"ADMIN"})
    void correctAuthoritiesAndCategory_AddNewCategory_ShouldSuccess() throws Exception {
        var categoryDto = new CategoryDto("test", CategoryType.GROUP, null, null);
        var createDescriptionRequest = MockMvcRequestBuilders.post(CATEGORIES_PATH)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(categoryDto));

        mockMvc.perform(createDescriptionRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value())
               );
    }

    @Test
    @DisplayName("add new category with wrong authorities and correct data")
    @WithMockUser(authorities = {"USER"})
    void incorrectAuthorities_AddNewCategory_ShouldFail() throws Exception {
        var categoryDto = new CategoryDto("test", CategoryType.GROUP, null, null);
        var createDescriptionRequest = MockMvcRequestBuilders.post(CATEGORIES_PATH)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(categoryDto));

        mockMvc.perform(createDescriptionRequest)
               .andDo(print())
               .andExpectAll(
                       status().isForbidden()
               );
    }

    @Test
    @DisplayName("add new category with correct authorities and wrong data")
    @WithMockUser(authorities = {"ADMIN"})
    void incorrectCategory_AddNewCategory_ShouldFail() throws Exception {
        var categoryDto = new CategoryDto("", null, null, null);
        var createDescriptionRequest = MockMvcRequestBuilders.post(CATEGORIES_PATH)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(categoryDto));

        mockMvc.perform(createDescriptionRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(Matchers.notNullValue())
               );
    }

    @Test
    @DisplayName("update category with correct authorities and data")
    @WithMockUser(authorities = {"ADMIN"})
    void correctAuthoritiesAndDescription_UpdateDescription_ShouldSuccess() throws Exception {
        createCategory();
        var categoryDto = new CategoryDto("test", CategoryType.GROUP, null, 0L);
        var updateDescriptionRequest = MockMvcRequestBuilders.put(CATEGORY_PATH, 110)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(categoryDto));

        mockMvc.perform(updateDescriptionRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value())
               );
    }

    private void createCategory() throws Exception {
        var categoryDto = new CategoryDto("test", CategoryType.GROUP, null, null);
        mockMvc.perform(MockMvcRequestBuilders.post(CATEGORIES_PATH)
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(mapper.writeValueAsString(categoryDto)));
    }

    @Test
    @DisplayName("update category with wrong authorities and correct data")
    @WithMockUser(authorities = {"USER"})
    void incorrectAuthorities_UpdateDescription_ShouldFail() throws Exception {
        createCategory();
        var categoryDto = new CategoryDto("test", CategoryType.GROUP, null, 0L);
        var updateDescriptionRequest = MockMvcRequestBuilders.put(CATEGORY_PATH, 110)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(categoryDto));

        mockMvc.perform(updateDescriptionRequest)
               .andDo(print())
               .andExpectAll(
                       status().isForbidden()
               );
    }

    @Test
    @DisplayName("update category with correct authorities and wrong data")
    @WithMockUser(authorities = {"ADMIN"})
    void incorrectDescription_UpdateDescription_ShouldFail() throws Exception {
        createCategory();
        var categoryDto = new CategoryDto("", null, null, 0L);
        var updateDescriptionRequest = MockMvcRequestBuilders.put(CATEGORY_PATH, 110)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(categoryDto));

        mockMvc.perform(updateDescriptionRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(Matchers.notNullValue())
               );
    }

    @Test
    @DisplayName("delete category with correct id and authorities")
    @WithMockUser(authorities = {"ADMIN"})
    void correctAuthoritiesAndId_DeleteCategory_ShouldSuccess() throws Exception {
        createCategory();
        var deleteRequest = MockMvcRequestBuilders.delete(CATEGORY_PATH, 110);

        mockMvc.perform(deleteRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value())
               );
    }

    @Test
    @DisplayName("delete category with correct id and wrong authorities")
    @WithMockUser(authorities = {"USER"})
    void incorrectAuthorities_DeleteDescription_ShouldFail() throws Exception {
        createCategory();
        var deleteRequest = MockMvcRequestBuilders.delete(CATEGORY_PATH, 110);

        mockMvc.perform(deleteRequest)
               .andDo(print())
               .andExpectAll(
                       status().isForbidden()
               );
    }
}
