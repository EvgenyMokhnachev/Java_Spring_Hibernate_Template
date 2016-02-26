//package services;
//
//import database.dto.User;
//import database.dao.UserDao;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import services.impl.UserServiceImpl;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
///**
// * Created by yevhen on 4/15/15.
// */
//public class UserServiceTest {
//
//    @Mock
//    private UserDao userDaoMock;
//
//    @Mock
//    private EmailSender emailSenderMock;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    private User user = new User();
//
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void update() {
//        userService.updateUser(user);
//        verify(userDaoMock).update(user);
//    }
//
//    @Test
//    public void sendWelcomeEmail() {
//        userService.sendWelcomeEmail(user);
//        verify(emailSenderMock).sendWelcomeEmail(user);
//    }
//
//    @Test
//    public void getByEmailAndPasswordNullUser() {
//        String email = "email";
//        String password = "password";
//
//        //this checks method doesn't throw NPE when user == null
//        when(userDaoMock.getByEmail(email)).thenReturn(null);
////        userService.getByEmailAndPassword(email, password);
//
//        when(userDaoMock.getByEmail(email)).thenReturn(user);
////        userService.getByEmailAndPassword(email, null);
//    }
//
//}
