//package controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import database.invite.Invite;
//import database.project.Project;
//import database.dto.User;
//import database.user.UserRole;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//import services.ProjectService;
//import services.UserService;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
///**
// * Created by yevhen on 4/9/15.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet-test.xml"})
//public class UserCtrlTest {
//
//    @Autowired
//    private WebApplicationContext webAppContext;
//
//    @Autowired
//    public UserService userServiceMock;
//
//    @Autowired
//    public ProjectService projectServiceMock;
//
//    private long id = 1;
//    private String username = "username";
//    private String email = "user@mail.com";
//    private String password = "password";
//    private UUID key = UUID.randomUUID();
//    private User user = new User(username, email, password, "");
//    private String json;
//    private Map<String, String> attrMap = new HashMap<>();
//
//    private MockHttpSession mockSession;
//    private MockMvc mockMvc;
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Before
//    public void setup() throws JsonProcessingException {
//        mockMvc = webAppContextSetup(webAppContext)
//                    .alwaysDo(print())
//                    .build();
//
//        mockSession = new MockHttpSession();
//
//        attrMap.put("email", email);
//        attrMap.put("username", username);
//        attrMap.put("password", password);
//
//        json = objectMapper.writeValueAsString(attrMap);
//    }
//
//    @After
//    public void resetMocks() {
//        Mockito.reset(userServiceMock);
//    }
//
//    @Test
//    public void registerUser() throws Exception {
//        //TODO Check test
////        when(userServiceMock.checkIfEmailAvailable(email)).thenReturn(true);
////        when(userServiceMock.checkIfUsernameAvailable(username)).thenReturn(true);
////        when(userServiceMock.createUser(username, email, password, null)).thenReturn(user);
//
//        mockMvc.perform(post("/user/register")
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.errors").doesNotExist());
//
//        assertEquals(user, mockSession.getAttribute("sessionUser"));
//
//        //TODO Check test
////        verify(userServiceMock, times(1)).checkIfEmailAvailable(email);
////        verify(userServiceMock, times(1)).checkIfUsernameAvailable(username);
////        verify(userServiceMock, times(1)).createUser(username, email, password, null);
//
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void registerUsernameAndEmailAlreadyExists() throws Exception {
//        when(userServiceMock.checkIfEmailAvailable(email)).thenReturn(false);
//        when(userServiceMock.checkIfUsernameAvailable(username)).thenReturn(false);
//
//        mockMvc.perform(post("/user/register")
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(false))
//                .andExpect(jsonPath("$.errors.email").exists())
//                .andExpect(jsonPath("$.errors.username").exists())
//                .andExpect(jsonPath("$.errors.password").doesNotExist());
//
//        assertNull(mockSession.getAttribute("sessionUser"));
//
//        //TODO Check test
////        verify(userServiceMock, times(1)).checkIfEmailAvailable(email);
////        verify(userServiceMock, times(1)).checkIfUsernameAvailable(username);
////        verify(userServiceMock, never()).createUser(any(), any(), any(), any());
////        verify(userServiceMock, never()).sendWelcomeEmail(any());
//
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void registerUserWrongFormatData() throws Exception {
//
//        Map<String, String> attrMap = new HashMap<>();
//        attrMap.put("email", "wrong format email");
//        attrMap.put("password", "pass");
//
//        String wrongData = objectMapper.writeValueAsString(attrMap);
//
//        mockMvc.perform(post("/user/register")
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(wrongData))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.errors.email").exists())
//                .andExpect(jsonPath("$.errors.password").exists());
//
//        assertNull(mockSession.getAttribute("sessionUser"));
//
//        attrMap.put("email", "");
//        attrMap.put("password", "");
//        wrongData = objectMapper.writeValueAsString(attrMap);
//
//        mockMvc.perform(post("/user/register")
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(wrongData))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.errors.email").exists())
//                .andExpect(jsonPath("$.errors.password").exists());
//
//        assertNull(mockSession.getAttribute("sessionUser"));
//        verifyZeroInteractions(userServiceMock);
//    }
//
//    @Test
//    public void loginUser() throws Exception {
//        when(userServiceMock.getByEmailAndPassword(email, password)).thenReturn(user);
//
//        mockMvc.perform(post("/user/login")
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(true));
//
//        verify(userServiceMock).getByEmailAndPassword(email, password);
//        assertEquals(user, mockSession.getAttribute("sessionUser"));
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void loginUserWrong() throws Exception {
//        when(userServiceMock.getByEmailAndPassword(email, password)).thenReturn(null);
//
//        mockMvc.perform(post("/user/login")
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(false))
//                .andExpect(jsonPath("$.errors.email").value("bad credentials"));
//
//        verify(userServiceMock).getByEmailAndPassword(email, password);
//        assertNull(mockSession.getAttribute("sessionUser"));
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void logoutUser() throws Exception {
//        mockMvc.perform(get("/user/logout")
//                .session(mockSession)
//                .sessionAttr("sessionUser", user))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//
//        assertNull(mockSession.getAttribute("sessionUser"));
//        verifyZeroInteractions(userServiceMock);
//    }
//
//    @Test
//    public void sendPasswordRecoveryMail() throws Exception {
//        attrMap = new HashMap<>();
//        attrMap.put("email", email);
//        String json = objectMapper.writeValueAsString(attrMap);
//
//        when(userServiceMock.getByEmail(email)).thenReturn(user);
//
//        mockMvc.perform(post("/user/sendPassRecoveryMail")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(true));
//
//        verify(userServiceMock, times(1)).getByEmail(email);
//        verify(userServiceMock, times(1)).sendPasswordRecoveryEmail(user);
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void sendPasswordRecoveryMailWrongEmail() throws Exception {
//        attrMap = new HashMap<>();
//        attrMap.put("email", email);
//        json = objectMapper.writeValueAsString(attrMap);
//
//        when(userServiceMock.getByEmail(email)).thenReturn(null);
//
//        mockMvc.perform(post("/user/sendPassRecoveryMail")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(false))
//                .andExpect(jsonPath("$.errors.email").value("wrong email"));
//
//        verify(userServiceMock, times(1)).getByEmail(email);
//        verify(userServiceMock, never()).sendPasswordRecoveryEmail(any());
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void sendPasswordRecoveryMailWrongFormatEmail() throws Exception {
//        attrMap = new HashMap<>();
//        attrMap.put("email", "email");
//        json = objectMapper.writeValueAsString(attrMap);
//
//        mockMvc.perform(post("/user/sendPassRecoveryMail")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(false))
//                .andExpect(jsonPath("$.errors.email").exists());
//
//        verifyZeroInteractions(userServiceMock);
//    }
//
////    @Test
////    public void getEnterNewPasswordPage() throws Exception {
////        when(userServiceMock.getByIdAndKey(id, key)).thenReturn(user);
////
////        String requestParameters = String.format("?id=%s&key=%s", Long.toString(id), key.toString());
////        mockMvc.perform(get("/user/recoverPassword" + requestParameters)
////                .session(mockSession))
////                .andExpect(status().isOk())
////                .andExpect(view().name("enterNewPasswordPage"))
////                .andExpect(model().attribute("requestParameters", requestParameters));
////
////        verify(userServiceMock, times(1)).getByIdAndKey(id, key);
////        verifyNoMoreInteractions(userServiceMock);
////    }
////
////    @Test
////    public void getEnterNewPasswordPageWrong() throws Exception {
////        when(userServiceMock.getByIdAndKey(id, key)).thenReturn(null);
////
////        mockMvc.perform(get("/user/recoverPassword?id={id}&key={key}", Long.toString(id), key.toString())
////                .session(mockSession))
////                .andExpect(status().isOk())
////                .andExpect(view().name("errorPage"));
////
////        verify(userServiceMock, times(1)).getByIdAndKey(id, key);
////        verifyNoMoreInteractions(userServiceMock);
////    }
//
//    @Test
//    public void setNewPassword() throws Exception {
//        attrMap = new HashMap<>();
//        attrMap.put("password", password);
//        json = objectMapper.writeValueAsString(attrMap);
//
//        when(userServiceMock.getByIdAndKey(id, key)).thenReturn(user);
//
//        mockMvc.perform(post("/user/setNewPassword?id={id}&key={key}", Long.toString(id), key.toString())
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(true));
//
//        verify(userServiceMock, times(1)).getByIdAndKey(id, key);
//        verify(userServiceMock, times(1)).changePassword(user, password);
//        assertEquals(user, mockSession.getAttribute("sessionUser"));
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void setNewPasswordUserNotFound() throws Exception {
//        attrMap = new HashMap<>();
//        attrMap.put("password", password);
//        json = objectMapper.writeValueAsString(attrMap);
//
//        when(userServiceMock.getByIdAndKey(id, key)).thenReturn(null);
//
//        mockMvc.perform(post("/user/setNewPassword?id={id}&key={key}", Long.toString(id), key.toString())
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(false));
//
//        assertNull(mockSession.getAttribute("sessionUser"));
//        verify(userServiceMock, times(1)).getByIdAndKey(id, key);
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void setNewPasswordWrongFormat() throws Exception {
//        attrMap = new HashMap<>();
//        attrMap.put("password", "pass");
//        json = objectMapper.writeValueAsString(attrMap);
//
//        mockMvc.perform(post("/user/setNewPassword?id={id}&key={key}", Long.toString(id), key.toString())
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(false))
//                .andExpect(jsonPath("$.errors.password").exists());
//
//        assertNull(mockSession.getAttribute("sessionUser"));
//        verifyZeroInteractions(userServiceMock);
//    }
//
//    @Test
//    public void checkIfUsernameAvailable() throws Exception{
//        attrMap = new HashMap<>();
//        attrMap.put("username", username);
//        json = objectMapper.writeValueAsString(attrMap);
//
//        when(userServiceMock.checkIfUsernameAvailable(username)).thenReturn(true);
//        mockMvc.perform(post("/user/checkUsernameAvailable")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(true));
//
//        verify(userServiceMock, times(1)).checkIfUsernameAvailable(username);
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void checkIfUsernameAvailableFalse() throws Exception {
//        attrMap = new HashMap<>();
//        attrMap.put("username", username);
//        json = objectMapper.writeValueAsString(attrMap);
//
//        when(userServiceMock.checkIfUsernameAvailable(username)).thenReturn(false);
//        mockMvc.perform(post("/user/checkUsernameAvailable")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(false));
//
//        verify(userServiceMock, times(1)).checkIfUsernameAvailable(username);
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void checkIfUsernameAvailableValidationFails() throws Exception{
//        attrMap = new HashMap<>();
//        attrMap.put("username", "<script>alert('I\'m a sinister script')</script>");
//        json = objectMapper.writeValueAsString(attrMap);
//
//        mockMvc.perform(post("/user/checkUsernameAvailable")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(false))
//                .andExpect(jsonPath("$.errors.username").exists());
//
//        verifyZeroInteractions(userServiceMock);
//
//    }
//
//    @Test
//    public void checkIfEmailAvailable() throws Exception{
//        attrMap = new HashMap<>();
//        attrMap.put("email", email);
//        json = objectMapper.writeValueAsString(attrMap);
//
//        when(userServiceMock.checkIfEmailAvailable(email)).thenReturn(true);
//        mockMvc.perform(post("/user/checkEmailAvailable")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(true));
//
//        verify(userServiceMock, times(1)).checkIfEmailAvailable(email);
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void checkIfEmailAvailableFalse() throws Exception {
//        attrMap = new HashMap<>();
//        attrMap.put("email", email);
//        json = objectMapper.writeValueAsString(attrMap);
//
//        when(userServiceMock.checkIfEmailAvailable(email)).thenReturn(false);
//        mockMvc.perform(post("/user/checkEmailAvailable")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(false));
//
//        verify(userServiceMock, times(1)).checkIfEmailAvailable(email);
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void checkIfEmailAvailableValidationFails() throws Exception{
//        attrMap = new HashMap<>();
//        attrMap.put("email", "wrong format email");
//        json = objectMapper.writeValueAsString(attrMap);
//
//        mockMvc.perform(post("/user/checkEmailAvailable")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(false))
//                .andExpect(jsonPath("$.errors.email").exists());
//
//        verifyZeroInteractions(userServiceMock);
//
//    }
//
////    @Test
////    public void getRegisterPageWithInvite() throws Exception {
////        Invite invite = new Invite();
////        invite.setEmail(email);
////
////        String requestParameters = "?key=" + key.toString();
////        when(projectServiceMock.getInviteByKey(key)).thenReturn(invite);
////        mockMvc.perform(get("/user/invite" + requestParameters)
////                .session(mockSession))
////                .andExpect(status().isOk())
////                .andExpect(view().name("registerPage"))
////                .andExpect(model().attribute("email", email))
////                .andExpect(model().attribute("requestParameters", requestParameters));
////
////    }
//
//    @Test
//    public void registerWithKey() throws Exception {
//        Invite invite = new Invite();
//        Project project = new Project();
//        invite.setProject(project);
//        invite.setRole(UserRole.CUSTOMER);
//        when(projectServiceMock.getInviteByKey(key)).thenReturn(invite);
//
//        //TODO Check test
////        user.setRole(null);
////        when(userServiceMock.checkIfEmailAvailable(email)).thenReturn(true);
////        when(userServiceMock.checkIfUsernameAvailable(username)).thenReturn(true);
////        when(userServiceMock.createUser(username, email, password, key)).thenReturn(user);
//
//        mockMvc.perform(post("/user/register?key=" + key.toString())
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.errors").doesNotExist());
//
//        assertEquals(user, mockSession.getAttribute("sessionUser"));
//
//        //TODO Check test
////        verify(userServiceMock, times(1)).checkIfEmailAvailable(email);
////        verify(userServiceMock, times(1)).checkIfUsernameAvailable(username);
////        verify(userServiceMock, times(1)).createUser(username, email, password, key);
//
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void getUserHtml() throws Exception{
//        user.setId(1L);
//        when(userServiceMock.getByEmail(email)).thenReturn(user);
//        mockMvc.perform(post("/user/get")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.data").exists());
//
//        verify(userServiceMock, times(1)).getByEmail(user.getEmail());
//        verifyNoMoreInteractions(userServiceMock);
//    }
//
//    @Test
//    public void getUserHtmlUserNull() throws Exception{
//        when(userServiceMock.getByEmail(email)).thenReturn(null);
//        mockMvc.perform(post("/user/get")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.data").exists());
//
//        verify(userServiceMock, times(1)).getByEmail(user.getEmail());
//        verifyNoMoreInteractions(userServiceMock);
//
//    }
//
//}
