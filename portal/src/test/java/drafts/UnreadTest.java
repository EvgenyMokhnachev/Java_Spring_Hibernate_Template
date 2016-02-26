package drafts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mvc-dispatcher-servlet.xml")
public class UnreadTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception {

    }


}
