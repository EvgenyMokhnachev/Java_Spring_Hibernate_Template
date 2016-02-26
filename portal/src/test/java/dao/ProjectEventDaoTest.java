//package dao;
//
//import database.logger.ProjectAction;
//import database.logger.ProjectActionEntity;
//import database.logger.ProjectEvent;
//import database.logger.ProjectEventDao;
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
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by yevhen on 4/9/15.
// */
//@Sql("/init.sql")
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:mvc-dispatcher-servlet-dao-test.xml")
//public class ProjectEventDaoTest {
//
//    private Project project = new Project();
//    private String name = "Test project";
//    private String description = "Project tests stuff";
//
//    private long projectId;
//    private long eventId;
//    private User user = new User();
//
//    @Autowired
//    private ProjectDao projectDao;
//
//    @Autowired
//    private ProjectEventDao projectEventDao;
//
//    @Before
//    public void initProject(){
//        project.setName(name);
//        project.setDescription(description);
//        project.setAuthor(user);
//        projectId = projectDao.add(project);
//
//        ProjectEvent event = new ProjectEvent(user, project, ProjectAction.CREATE, ProjectActionEntity.COMMENT);
//        eventId = projectEventDao.add(event);
//    }
//
//    @Test
//    public void test(){
//        ProjectEvent savedEvent = projectEventDao.get(eventId);
//        return;
//    }
//
//}
