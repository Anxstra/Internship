package com.anxstra.catalog;

import com.anxstra.catalog.dto.DescriptionDto;
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

import static com.anxstra.catalog.constants.MessageConstants.DESCRIPTION_NOT_FOUND;
import static com.anxstra.catalog.constants.PathConstants.DESCRIPTIONS_PATH;
import static com.anxstra.catalog.constants.PathConstants.DESCRIPTION_PATH;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DescriptionControllerTest extends ContainerSetup {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("get by id request with correct id")
    void correctId_GetById_ShouldReturnDescription() throws Exception {
        var getByIdRequest = MockMvcRequestBuilders.get(DESCRIPTION_PATH, 101);

        mockMvc.perform(getByIdRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value()),
                       jsonPath("$.body.text").value(Matchers.notNullValue())
               );
    }

    @Test
    @DisplayName("get by id request with wrong id")
    void wrongId_GetById_ShouldNotReturnDescription() throws Exception {
        var getByIdRequest = MockMvcRequestBuilders.get(DESCRIPTION_PATH, 1);

        mockMvc.perform(getByIdRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(DESCRIPTION_NOT_FOUND)
               );
    }

    @Test
    @DisplayName("add new description with correct authorities and data")
    @WithMockUser(authorities = {"ADMIN"})
    void correctAuthoritiesAndDescription_AddNewDescription_ShouldSuccess() throws Exception {
        var descriptionDto = new DescriptionDto("test", 1000, 100, 100, 100, null);
        var createDescriptionRequest = MockMvcRequestBuilders.post(DESCRIPTIONS_PATH)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(descriptionDto));

        mockMvc.perform(createDescriptionRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value())
               );
    }

    @Test
    @DisplayName("add new description with correct data and wrong authorities")
    @WithMockUser(authorities = {"USER"})
    void incorrectAuthorities_AddNewDescription_ShouldFail() throws Exception {
        var descriptionDto = new DescriptionDto("test", 1000, 100, 100, 100, null);
        var createDescriptionRequest = MockMvcRequestBuilders.post(DESCRIPTIONS_PATH)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(descriptionDto));

        mockMvc.perform(createDescriptionRequest)
               .andDo(print())
               .andExpectAll(
                       status().isForbidden()
               );
    }

    @Test
    @DisplayName("add new description with wrong data and correct authorities")
    @WithMockUser(authorities = {"ADMIN"})
    void incorrectDescription_AddNewDescription_ShouldFail() throws Exception {
        var descriptionDto = new DescriptionDto("", null, null, -500, -500, null);
        var createDescriptionRequest = MockMvcRequestBuilders.post(DESCRIPTIONS_PATH)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(descriptionDto));

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
    @DisplayName("update description with correct authorities and data")
    @WithMockUser(authorities = {"ADMIN"})
    void correctAuthoritiesAndDescription_UpdateDescription_ShouldSuccess() throws Exception {
        createDescription();
        var descriptionDto = new DescriptionDto("test", 1000, 100, 100, 100, 0L);
        var updateDescriptionRequest = MockMvcRequestBuilders.put(DESCRIPTION_PATH, 110)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(descriptionDto));

        mockMvc.perform(updateDescriptionRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value())
               );
    }

    private void createDescription() throws Exception {
        var descriptionDto = new DescriptionDto("test", 1000, 100, 100, 100, null);
        mockMvc.perform(MockMvcRequestBuilders.post(DESCRIPTIONS_PATH)
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(mapper.writeValueAsString(descriptionDto)));
    }

    @Test
    @DisplayName("update description with correct data and wrong authorities")
    @WithMockUser(authorities = {"USER"})
    void incorrectAuthorities_UpdateDescription_ShouldFail() throws Exception {
        createDescription();
        var descriptionDto = new DescriptionDto("test", 1000, 100, 100, 100, null);
        var updateDescriptionRequest = MockMvcRequestBuilders.put(DESCRIPTION_PATH, 110)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(descriptionDto));

        mockMvc.perform(updateDescriptionRequest)
               .andDo(print())
               .andExpectAll(
                       status().isForbidden()
               );
    }

    @Test
    @DisplayName("update description with wrong data and correct authorities")
    @WithMockUser(authorities = {"ADMIN"})
    void incorrectDescription_UpdateDescription_ShouldFail() throws Exception {
        createDescription();
        var descriptionDto = new DescriptionDto("", null, null, -500, -500, null);
        var updateDescriptionRequest = MockMvcRequestBuilders.put(DESCRIPTION_PATH, 110)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(mapper.writeValueAsString(descriptionDto));

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
    @DisplayName("delete description with correct id and authorities")
    @WithMockUser(authorities = {"ADMIN"})
    void correctAuthoritiesAndId_DeleteDescription_ShouldSuccess() throws Exception {
        createDescription();
        var deleteRequest = MockMvcRequestBuilders.delete(DESCRIPTION_PATH, 110);

        mockMvc.perform(deleteRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value())
               );
    }

    @Test
    @DisplayName("delete description with correct id and wrong authorities")
    @WithMockUser(authorities = {"USER"})
    void incorrectAuthorities_DeleteDescription_ShouldFail() throws Exception {
        createDescription();
        var deleteRequest = MockMvcRequestBuilders.delete(DESCRIPTION_PATH, 110);

        mockMvc.perform(deleteRequest)
               .andDo(print())
               .andExpectAll(
                       status().isForbidden()
               );
    }
}
