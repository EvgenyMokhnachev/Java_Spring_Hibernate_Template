//package controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
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
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet-test.xml"})
//public class ProjectCtrlTest {
//
//    @Autowired
//    private WebApplicationContext webAppContext;
//
//    @Autowired
//    public ProjectService projectServiceMock;
//
//    @Autowired
//    public UserService userServiceMock;
//
//    private MockMvc mockMvc;
//    private MockHttpSession mockSession;
//
//    private String name = "Project #1";
//    private String description = "project number one";
//
//    @Before
//    public void setup() {
//        mockMvc = webAppContextSetup(webAppContext)
//                .alwaysDo(print())
//                .alwaysExpect(status().isOk()).build();
//
//        mockSession = new MockHttpSession();
//    }
//
//    @After
//    public void resetMocks() {
//        Mockito.reset(projectServiceMock);
//    }
//
//    @Test
//    public void createProject() throws Exception {
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", name);
//        map.put("description", description);
//
//        List<String> clientsEmails = new ArrayList<>();
//        clientsEmails.add("client1@email");
//        clientsEmails.add("client2@email");
//        map.put("clients", clientsEmails);
//
//        List<String> peopleEmails = new ArrayList<>();
//        peopleEmails.add("man1@email");
//        peopleEmails.add("man2@email");
//        map.put("people", peopleEmails);
//
//        ObjectMapper objectMapper = new  ObjectMapper();
//        String json = objectMapper.writeValueAsString(map);
//
//        User user = getUser();
//        mockSession.setAttribute("sessionUser", user);
//        mockMvc.perform(post("/project/create")
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(jsonPath("$.success").value(true));
//
////        verify(projectServiceMock, times(1)).createProject(user, );
////        verifyNoMoreInteractions(projectServiceMock);
//    }
//
//    @Test
//    public void createProjectWrongData() throws Exception {
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "Proj");
//        map.put("description", "<script>very bad javascript</script>");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(map);
//
//        mockSession.setAttribute("sessionUser", getUser());
//
//        mockMvc.perform(post("/project/create")
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(jsonPath("$.success").value(false))
//                .andExpect(jsonPath("$.errors.name").exists())
//                .andExpect(jsonPath("$.errors.description").exists());
//
//    }
//
//    @Test
//    public void createProjectSafeHtml() throws Exception {
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "Project #1");
//        map.put("description", "<del>description</del><br /><div>text</div>");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(map);
//
//        mockSession.setAttribute("sessionUser", getUser());
//
//        mockMvc.perform(post("/project/create")
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(jsonPath("$.success").value(true));
//
//    }
//
//    @Test
//    public void getAssignedProjects() throws Exception{
//        User user = getUser();
//        when(projectServiceMock.listAssignedByUserRole(user)).thenReturn(projectList());
//        mockSession.setAttribute("sessionUser", user);
//        mockMvc.perform(get("/project/getAssignedProjects")
//                .session(mockSession))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.errors").doesNotExist())
//                .andExpect(jsonPath("$.data", hasSize(3)));
//    }
//
//
//    @Test
//    public void createNote() throws Exception {
////        Map<String, Object> map = new HashMap<>();
////        map.put("name", name);
////        map.put("description", description);
////
////        List<String> clientsEmails = new ArrayList<>();
////        clientsEmails.add("client1@email");
////        clientsEmails.add("client2@email");
////        map.put("clients", clientsEmails);
////
////        List<String> peopleEmails = new ArrayList<>();
////        peopleEmails.add("man1@email");
////        peopleEmails.add("man2@email");
////        map.put("people", peopleEmails);
////
////        ObjectMapper objectMapper = new  ObjectMapper();
////        String json = objectMapper.writeValueAsString(map);
////
////        User user = getUser();
////        mockSession.setAttribute("sessionUser", user);
//        String content = "{\"subject\":\"Subject\",\"message\":\"Text\"," +
//                            "\"projectId\":\"1\",\"filesIds\":[1,2]}";
//
//        mockMvc.perform(post("/project/addNote")
//                .session(mockSession)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content));
//
////        verify(projectServiceMock, times(1)).createProject(user, );
////        verifyNoMoreInteractions(projectServiceMock);
//    }
//
//
//    private User getUser(){
//        User user = new User("user", "email", "password", "");
//        user.setRole(UserRole.EXECUTOR);
//        return user;
//    }
//
//    private List<Project> projectList() {
//        User user2 = new User();
//        user2.setId(2L);
//        User user3 = new User();
//        user3.setId(3L);
//        User user4 = new User();
//        user4.setId(4L);
//        User user5 = new User();
//        user5.setId(5L);
//
//        List<User> clients1 = new ArrayList<>();
//        clients1.add(user2);
//        clients1.add(user3);
//        clients1.add(user5);
//
//        List<User> clients2 = new ArrayList<>();
//        clients2.add(user2);
//
//        List<User> clients3 = new ArrayList<>();
//        clients3.add(user5);
//        clients3.add(user4);
//
//        Project project1 = new Project();
//        project1.setName("Project #1");
//        project1.setDescription("Description #1");
//        project1.setCustomers(clients1);
//
//        Project project2 = new Project();
//        project2.setName("Project #2");
//        project2.setDescription("Description #2");
//        project2.setCustomers(clients2);
//
//        Project project3 = new Project();
//        project3.setName("Project #3");
//        project3.setDescription("Description #3");
//        project3.setCustomers(clients3);
//
//        List <Project> result = new ArrayList<>();
//        result.add(project1);
//        result.add(project2);
//        result.add(project3);
//
//        return result;
//    }
//}
