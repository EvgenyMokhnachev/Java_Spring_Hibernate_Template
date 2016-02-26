//package dao;
//
//import database.audio.Audio;
//import database.audio.AudioDao;
//import database.file.UploadedFile;
//import database.file.UploadedFileDao;
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
//import static org.junit.Assert.assertNull;
//
///**
// * Created by yevhen on 4/9/15.
// */
//@Sql("/init.sql")
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:mvc-dispatcher-servlet-dao-test.xml")
//public class AudioDaoTest {
//
//    @Autowired
//    private AudioDao audioDao;
//
//    @Autowired
//    private UploadedFileDao uploadedFileDao;
//
//    private String name = "test audio";
//    private Audio audio = new Audio();
//    private long audioId;
//    private long fileId;
//
//    @Before
//    public void insertAudio() {
//
//        UploadedFile file = new UploadedFile();
//        file.setName("file_name.mp3");
//        file.setPath("/dir/filename");
//
//        fileId = uploadedFileDao.add(file);
//
//        audio.setName(name);
//        audio.setOriginalFile(file);
//
//        audioId = audioDao.add(audio);
//    }
//
//
//    @Test
//    public void getAudio(){
//        Audio dbAudio = audioDao.get(audioId);
//        return;
//    }
//
//}
