//package services;
//
//import TestHelpers.DbHelper;
//import TestHelpers.TestGenerator;
//import junit.framework.TestCase;
//import database.audio.Audio;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AudioServiceTest extends TestCase{
//
//    @Override
//    protected void setUp() throws Exception{
//        super.setUp();
//        DbHelper.testDataInit();
//    }
//
//    public void testAdd(){
//        Audio audio = TestGenerator.audio("test name");
//        audio = AudioServiceImpl.add(audio);
//        assertNotNull(audio);
//    }
//
//    public void testUpdateAndGetById(){
//        Audio audio = TestGenerator.audio("test name");
//        audio = AudioServiceImpl.add(audio);
//        audio.name = "updated name";
//        AudioServiceImpl.update(audio);
//        audio = AudioServiceImpl.getById(audio.getId());
//        assertEquals(audio.name, "updated name");
//    }
//
//    public void testDelete(){
//        Audio audio = TestGenerator.audio("delete audio test");
//        audio = AudioServiceImpl.add(audio);
//        AudioServiceImpl.delete(audio.getId());
//        audio = AudioServiceImpl.getById(audio.getId());
//        assertNull(audio);
//    }
//
//    public void testGetSomeAudio(){
//        List<Audio> audios = new ArrayList<Audio>(){{
//            add(TestGenerator.audio("audio 1"));
//            add(TestGenerator.audio("audio 2"));
//            add(TestGenerator.audio("audio 3"));
//        }};
//        for(Audio audio : audios) audio = AudioServiceImpl.add(audio);
//
//        Criterion[] criterions = new Criterion[audios.size()];
//        for(byte i = 0; i < criterions.length; i++) criterions[i] = Restrictions.eq("id", audios.get(i).getId());
//        audios = AudioServiceImpl.get(0, 0,
//                new ArrayList<Order>() {{
//                    add(Order.asc("id"));
//                    add(Order.desc("name"));
//                }},
//                new ArrayList<Criterion>() {{
//                    add(Restrictions.or(criterions));
//                }}
//        );
//        assertNotNull(audios);
//        assertEquals(audios.size(), 3);
//    }
//}
