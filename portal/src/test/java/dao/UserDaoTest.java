//package dao;
//
//import database.dto.User;
//import database.dao.UserDao;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//
///**
// * Created by yevhen on 4/9/15.
// */
//@Sql("/init.sql")
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:mvc-dispatcher-servlet-dao-test.xml")
//public class UserDaoTest {
//
//    @Autowired
//    private UserDao userDao;
//
//    private String username = "username";
//    private String email = "user@mail.com";
//    private String password = "password";
//    private UserRole role = UserRole.CUSTOMER;
//    private UserType type = UserType.BASIC;
//    private User user = new User(username, email, password, "");
//    private long userId;
//
//    @Before
//    public void insertUser() {
//        userId = userDao.add(user);
//    }
//
//    @Test
//    public void deleteUser(){
//        User dbUser = userDao.get(userId);
//        userDao.delete(dbUser);
//        assertNull(userDao.get(userId));
//    }
//
//    @Test
//    public void getUser(){
//        User dbUser = userDao.get(userId);
//
//        assertEquals(email, dbUser.getEmail());
//        assertEquals(password, dbUser.getPassword());
//        assertEquals(role, dbUser.getRole());
//        assertEquals(type, dbUser.getType());
//    }
//
//    @Test
//    public void updateUser(){
//        User dbUser = userDao.get(userId);
//
//        String newUsername = "newUser";
//        UserType newUserType = UserType.PRO;
//
//        dbUser.setUsername(newUsername);
//        dbUser.setType(newUserType);
//
//        userDao.update(dbUser);
//
//        User updatedUser = userDao.get(userId);
//
//        assertEquals(newUsername, updatedUser.getUsername());
//        assertEquals(newUserType, updatedUser.getType());
//    }
//
//    @Test
//    public void getUserByEmail(){
//        User dbUser = userDao.getByEmail(email);
//        assertEquals(password, dbUser.getPassword());
//    }
//
//    @Test
//    public void getUserByNullUsername(){
//        User emptyUser = new User();
//        userDao.add(emptyUser);
//
//        User dbUser = userDao.getByEmail(null);
//        assertNull(dbUser);
//    }
//
//    @Test
//    public void getUsers(){
//        User secondUser = new User("second username", "second email", "second password", "");
//        userDao.add(secondUser);
//
//        List<User> users = userDao.get(0, 0, null, null);
//        assertEquals(2, users.size());
//    }
//
//}
