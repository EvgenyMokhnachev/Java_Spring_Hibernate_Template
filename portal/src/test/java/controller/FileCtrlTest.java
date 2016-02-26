package controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet-test.xml"})
public class FileCtrlTest {
    private static final String path = "src/test/resources/testData/sanitarium.mp3";

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;
    private MockHttpSession mockSession;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webAppContext)
                .alwaysDo(print())
                .alwaysExpect(status().isOk()).build();

        mockSession = new MockHttpSession();
    }

    @After
    public void resetMocks() {
//        Mockito.reset(projectServiceMock);
    }

    @Test
    public void uploadFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "sanitarium.mp3", null, new FileInputStream(path));
        mockMvc.perform(fileUpload("/file/upload")
                .file(file));
    }

}
