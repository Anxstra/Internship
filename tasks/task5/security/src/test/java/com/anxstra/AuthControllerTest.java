package com.anxstra;

import com.anxstra.dto.RoleDto;
import com.anxstra.dto.UserCacheDto;
import com.anxstra.dto.UserLoginDto;
import com.anxstra.dto.UserRegisterDto;
import com.anxstra.entities.Token;
import com.anxstra.enums.StatusType;
import com.anxstra.repositories.TokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static com.anxstra.constants.CommonConstants.BEARER_PREFIX;
import static com.anxstra.constants.MessageConstants.REFRESH_TOKEN_INVALID_MSG;
import static com.anxstra.constants.MessageConstants.ROLE_NOT_FOUND_MSG;
import static com.anxstra.constants.MessageConstants.USER_EXISTS_MSG;
import static com.anxstra.constants.MessageConstants.USER_NOT_FOUND_MSG;
import static com.anxstra.constants.PathConstants.LOGIN_PATH;
import static com.anxstra.constants.PathConstants.REFRESH_PATH;
import static com.anxstra.constants.PathConstants.REGISTER_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends ContainerSetup {

    private static final String BODY_ACCESS_TOKEN = "$.body.accessToken";

    private static final String BODY_REFRESH_TOKEN = "$.body.refreshToken";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    @DisplayName("user login for user with correct username and password")
    void correctCredentials_Login_ShouldBeAuthenticated() throws Exception {
        var loginDto = new UserLoginDto("kozichev2020@gmail.com", "14526900");
        var loginRequest = MockMvcRequestBuilders.post(LOGIN_PATH)
                                                 .contentType(MediaType.APPLICATION_JSON)
                                                 .content(mapper.writeValueAsString(loginDto));

        mockMvc.perform(loginRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value()),
                       jsonPath("$.body.accessToken").value(Matchers.notNullValue()),
                       jsonPath("$.body.refreshToken").value(Matchers.notNullValue())
               );
    }

    @Test()
    @DisplayName("user login for user with wrong username")
    void incorrectCredentialsUserNotFound_Login_ShouldNotBeAuthenticated() throws Exception {
        var loginDto = new UserLoginDto("bullshit", "bullshit");
        var loginRequest = MockMvcRequestBuilders.post(LOGIN_PATH)
                                                 .contentType(MediaType.APPLICATION_JSON)
                                                 .content(mapper.writeValueAsString(loginDto));

        mockMvc.perform(loginRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(USER_NOT_FOUND_MSG)
               );
    }

    @Test()
    @DisplayName("user login for user with wrong password")
    void incorrectCredentialsWrongPassword_Login_ShouldNotBeAuthenticated() throws Exception {
        var loginDto = new UserLoginDto("kozichev2020@gmail.com", "bullshit");
        var loginRequest = MockMvcRequestBuilders.post(LOGIN_PATH)
                                                 .contentType(MediaType.APPLICATION_JSON)
                                                 .content(mapper.writeValueAsString(loginDto));

        mockMvc.perform(loginRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value())
               );
    }

    @Test
    @DisplayName("user register for user with correct username and password")
    void correctCredentials_Register_ShouldBeRegistered() throws Exception {
        var registerDto = new UserRegisterDto("bullshit", "14526900",
                Set.of(new RoleDto("USER")), StatusType.ACTIVE);
        var registerRequest = MockMvcRequestBuilders.post(REGISTER_PATH)
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(mapper.writeValueAsString(registerDto));

        mockMvc.perform(registerRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value())
               );
    }

    @Test()
    @DisplayName("user register for already registered user")
    void incorrectCredentialsUserExists_Register_ShouldNotBeRegistered() throws Exception {
        var registerDto = new UserRegisterDto("kozichev2020@gmail.com", "14526900",
                Set.of(new RoleDto("USER")), StatusType.ACTIVE);
        var registerRequest = MockMvcRequestBuilders.post(REGISTER_PATH)
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(mapper.writeValueAsString(registerDto));

        mockMvc.perform(registerRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(USER_EXISTS_MSG)
               );
    }

    @Test()
    @DisplayName("user register for user with not existing role")
    void incorrectCredentialsRoleNotFound_Register_ShouldNotBeRegistered() throws Exception {
        var registerDto = new UserRegisterDto("user", "password",
                Set.of(new RoleDto("BULLSHIT")), StatusType.ACTIVE);
        var registerRequest = MockMvcRequestBuilders.post(REGISTER_PATH)
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(mapper.writeValueAsString(registerDto));

        mockMvc.perform(registerRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(ROLE_NOT_FOUND_MSG)
               );
    }

    @Test()
    @DisplayName("user register for user with missing/null property")
    void incorrectCredentialsMissingProperty_Register_ShouldNotBeRegistered() throws Exception {
        var registerDto = new UserRegisterDto("user", "password",
                Set.of(new RoleDto("ADMIN")), null);
        var registerRequest = MockMvcRequestBuilders.post(REGISTER_PATH)
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(mapper.writeValueAsString(registerDto));

        mockMvc.perform(registerRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value())
               );
    }

    @Test()
    @DisplayName("use correct refresh token to get new")
    void correctToken_Refresh_ShouldGetNew() throws Exception {
        var token = getToken(BODY_REFRESH_TOKEN);

        var refreshRequest = MockMvcRequestBuilders.get(REFRESH_PATH)
                                                   .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + token);

        mockMvc.perform(refreshRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.OK.value()),
                       jsonPath("$.body.accessToken").value(Matchers.notNullValue()),
                       jsonPath("$.body.refreshToken").value(Matchers.notNullValue())
               );
    }

    private String getToken(String jsonPath) throws Exception {
        var loginDto = new UserLoginDto("kozichev2020@gmail.com", "14526900");
        var loginRequest = MockMvcRequestBuilders.post(LOGIN_PATH)
                                                 .contentType(MediaType.APPLICATION_JSON)
                                                 .content(mapper.writeValueAsString(loginDto));

        var result = mockMvc.perform(loginRequest)
                            .andDo(print())
                            .andReturn();

        return JsonPath.read(result.getResponse().getContentAsString(), jsonPath);
    }

    @Test()
    @DisplayName("use incorrect refresh token to get new")
    void incorrectToken_Refresh_ShouldNotGetNew() throws Exception {
        var token = getToken(BODY_REFRESH_TOKEN);

        Token tokenEntity = tokenRepository.findByRefreshToken(token)
                                           .orElseThrow(RuntimeException::new);
        tokenEntity.setIsRevoked(true);
        tokenRepository.saveAndFlush(tokenEntity);

        var refreshRequest = MockMvcRequestBuilders.get(REFRESH_PATH)
                                                   .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + token);

        mockMvc.perform(refreshRequest)
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       content().contentType(MediaType.APPLICATION_JSON),
                       jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                       jsonPath("$.message").value(REFRESH_TOKEN_INVALID_MSG)
               );
    }

    @Test()
    @DisplayName("access resource with changed ip")
    void ipChanged_Access_ShouldLogout() throws Exception {
        var token = getToken(BODY_ACCESS_TOKEN);

        var accessRequest = MockMvcRequestBuilders.get(REFRESH_PATH)
                                                  .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + token)
                                                  .with(request -> {
                                                      request.setRemoteAddr("bullshit");
                                                      return request;
                                                  });

        mockMvc.perform(accessRequest)
               .andDo(print())
               .andExpectAll(
                       status().isFound(),
                       redirectedUrl("http://localhost/oauth2/authorization/google")
               );
    }

    @Test()
    @DisplayName("user cache when successful logged")
    void correctCredentials_Login_ShouldCache() throws Exception {
        var loginDto = new UserLoginDto("kozichev2020@gmail.com", "14526900");
        var loginRequest = MockMvcRequestBuilders.post(LOGIN_PATH)
                                                 .contentType(MediaType.APPLICATION_JSON)
                                                 .content(mapper.writeValueAsString(loginDto));

        mockMvc.perform(loginRequest)
               .andDo(print())
               .andReturn();

        String cachedUser = redisTemplate.opsForValue().get("kozichev2020@gmail.com");
        assertNotNull(cachedUser);

        UserCacheDto userCacheDto = mapper.readValue(cachedUser, UserCacheDto.class);
        assertEquals(101, userCacheDto.id());
        assertEquals(Set.of(new RoleDto("ADMIN")), userCacheDto.roles());
        assertEquals(StatusType.ACTIVE, userCacheDto.status());
    }

}
