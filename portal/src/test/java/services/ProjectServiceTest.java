//package services;
//
//import database.project.Project;
//import database.project.ProjectDao;
//import database.project.ProjectView;
//import database.dto.User;
//import database.dao.UserDao;
//import database.user.UserRole;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//
///**
// * Created by yevhen on 4/15/15.
// */
//public class ProjectServiceTest {
//
//    @Mock
//    private UserDao userDaoMock;
//
//    @Mock
//    private ProjectDao projectDaoMock;
//
//    @Mock
//    private EmailSender emailSenderMock;
//
//    @InjectMocks
//    private ProjectServiceImpl projectService;
//
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void createProject() {
//        User user = new User("userName", "user@mail.com");
//
//        String projectName = "Project #1";
//        String projectDecsription = "description";
//
//        List<String> clientsEmails = new ArrayList<>();
//        String client1 = "client1@email";
//        clientsEmails.add(client1);
//        String client2 = "client2@email";
//        clientsEmails.add(client2);
//
//        List<String> peopleEmails = new ArrayList<>();
//        String man1 = "man1@email";
//        peopleEmails.add(man1);
//        String man2 = "man2@email";
//        peopleEmails.add(man2);
//
//        ProjectView projectView = new ProjectView();
//        projectView.setName(projectName);
//        projectView.setDescription(projectDecsription);
//        projectView.setClients(clientsEmails);
//        projectView.setPeople(peopleEmails);
//
//
//        when(userDaoMock.getByEmail(client1)).thenReturn(user);
//        when(userDaoMock.getByEmail(client2)).thenReturn(null);
//        when(userDaoMock.getByEmail(man1)).thenReturn(null);
//        when(userDaoMock.getByEmail(man2)).thenReturn(user);
//
//        Project anotherProject = projectService.createProject(user, projectView);
//
//        //TODO Check test
////        verify(emailSenderMock, never()).sendInvite(any(), eq(client1), any());
////        verify(emailSenderMock, times(1)).sendInvite(any(), eq(client2), eq(UserRole.CUSTOMER));
////        verify(emailSenderMock, times(1)).sendInvite(any(), eq(man1), eq(UserRole.EXECUTOR));
////        verify(emailSenderMock, never()).sendInvite(any(), eq(man2), any());
//
//        Assert.assertEquals(1, anotherProject.getCustomers().size());
//        Assert.assertEquals(1, anotherProject.getExecutors().size());
//    }
//
//
//}
