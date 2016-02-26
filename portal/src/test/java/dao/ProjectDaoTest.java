//package dao;
//
//import database.project.Project;
//import database.project.ProjectDao;
//import database.dto.User;
//import database.user.UserRole;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by yevhen on 4/9/15.
// */
//@Sql("/init.sql")
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:mvc-dispatcher-servlet-dao-test.xml")
//public class ProjectDaoTest {
//
//    private Project project = new Project();
//    private String name = "Test project";
//    private String description = "Project tests stuff";
//
//    private long projectId;
//    private User user = new User();
//
//    @Autowired
//    private ProjectDao projectDao;
//
//    @Before
//    public void initProject(){
//        project.setName(name);
//        project.setDescription(description);
//
//        projectId = projectDao.add(project);
//    }
//
//    @Test
//    public void getProject(){
//        Project dbProject = projectDao.get(projectId);
//
//        assertEquals(name, dbProject.getName());
//        assertEquals(description, dbProject.getDescription());
//    }
//
//    @Test
//    public void getProjectsAssignedWithUser(){
//        putSomeProjToDb();
//        user.setRole(UserRole.CUSTOMER);
//        List<Project> projects = projectDao.listAssignedByUserRole(user);
//        assertEquals(2, projects.size());
//
//        Set<Project> allProjects = projectDao.listAllAssignedProjects(user);
//        assertEquals(3, allProjects.size());
//
//    }
//
//    private void putSomeProjToDb(){
//        User user2 = new User();
//        User user3 = new User();
//        User user4 = new User();
//        User user5 = new User();
//
//        List<User>clients1 = new ArrayList<>();
//        clients1.add(user);
//        clients1.add(user3);
//        clients1.add(user5);
//
//        List<User>clients2 = new ArrayList<>();
//        clients2.add(user2);
//
//        List<User>clients3 = new ArrayList<>();
//        clients3.add(user);
//        clients3.add(user4);
//
//        List<User>executors = new ArrayList<>();
//        executors.add(user);
//        executors.add(user4);
//
//        Project project1 = new Project();
//        project1.setName("Project #1");
//        project1.setCustomers(clients1);
//
//        Project project2 = new Project();
//        project2.setName("Project #2");
//        project2.setCustomers(clients2);
//        project2.setExecutors(executors);
//
//        Project project3 = new Project();
//        project3.setName("Project #3");
//        project3.setCustomers(clients3);
//
//        projectDao.add(project1);
//        projectDao.add(project2);
//        projectDao.add(project3);
//
//    }
//}
