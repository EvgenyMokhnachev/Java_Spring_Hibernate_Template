//package controller;
//
//import database.dto.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//import services.AudioService;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet-test.xml"})
//public class AudioCommentCtrlTest {
//    @Autowired
//    private WebApplicationContext webAppContext;
//
//    @Autowired
//    private AudioService audioService;
//
//    private MockMvc mockMvc;
//    private MockHttpSession mockSession;
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
//    @Test
//    public void deleteUnread() throws Exception {
//        User user = new User();
//        long messageId = 100500;
//        mockSession.setAttribute("sessionUser", user);
//        mockMvc.perform(post("/audioComment/deleteUnread/" + messageId)
//                .session(mockSession));
//
//        verify(audioService, times(1)).deleteUnread(user, messageId);
//
//    }
//
//}
