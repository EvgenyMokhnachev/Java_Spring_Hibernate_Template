//package dao;
//
//import database.task.ToDoTask;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//import database.task.ToDoTaskDao;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
///**
// * Created by yevhen on 4/9/15.
// */
//@Sql("/init.sql")
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:mvc-dispatcher-servlet-dao-test.xml")
//public class ToDoTaskDaoTest {
//
//    @Autowired
//    private ToDoTaskDao toDoTaskDao;
//
//    @Test
//    public void deleteUser(){
//        ToDoTask task = new ToDoTask();
//
//        task.setTitle("title");
//        task.setDescription("description");
//
//        toDoTaskDao.add(task);
//    }
//
//}
