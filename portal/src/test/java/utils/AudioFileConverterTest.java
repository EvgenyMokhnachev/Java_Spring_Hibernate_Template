//package utils;
//
//import database.file.UploadedFile;
//import database.watermark.Watermark;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import services.AudioService;
//import services.AudioServiceImpl;
//import services.WatermarkService;
//
//import java.io.File;
//import java.io.IOException;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:mvc-dispatcher-servlet-dao-test.xml")
//public class AudioFileConverterTest {
//    private static final String PATH = "";
//    private static String resultDir = PATH + "results/";
//
//    private static final String WATERMARK_MP_3 = "smallWavAudioFile.mp3";
//    private static final String WATERMARK_WAV = "smallWavAudioFile.wav";
//    private static final String WATERMARK_AIFF = "smallWavAudioFile.aiff";
//
//    @Autowired
//    private AudioFileConverter audioConverter;
//
//    @Autowired
//    private AudioService audioService;
//
//    @Autowired
//    private WatermarkService watermarkService;
//
////    @BeforeClass
//    public static void setUp() throws Exception {
//        File dir = new File(resultDir);
//        AudioFileConverter.deleteDir(dir);
//        dir.mkdir();
//    }
//
//
//    @Test
//    public void testProtect() throws Exception {
//        UploadedFile file = new UploadedFile();
//        file.setPath("/home/yevhen/IdeaProjects/AudioTaskManager/portal/src/test/resources/testData/sanitarium.mp3");
//
//        UploadedFile watermarkFile = new UploadedFile();
//        watermarkFile.setPath("/home/yevhen/IdeaProjects/AudioTaskManager/portal/src/test/resources/testData/watermark.mp3");
//
//        Watermark watermark = new Watermark();
//        watermark.setFile(watermarkFile);
//        watermark.setIntervalSeconds(25);
//
//        audioService.getProtectedAudio(file, false, true, null, 256);
//
//        return;
//    }
//
//
//    @Test
//    public void getExtension() {
//        String mp3 = audioConverter.getExtension("file.mp3");
//        assertEquals(".mp3", mp3);
//
//        String empty = audioConverter.getExtension("file");
//        assertEquals("", empty);
//    }
//
////    @Test
////    public void setFades(){
////        try {
////            audioConverter.setFades("D:\\Tests\\sanitarium.mp3", "D:\\Tests\\result.mp3", 3);
////        } catch (IOException | InterruptedException e) {
////            e.printStackTrace();
////        }
////    }
//
//
//
////    @Test
////    public void getDuration() {
////        String source = "C:\\GoogleDrive\\Projects\\EVGENY_MOKHNACHEV_JOB\\AudioTaskManager\\AudioTaskManager\\portal\\src\\test\\resources\\testData\\sanitarium.mp3";
////        System.out.println(AudioFileConverter.getTrackDuration(source));
////    }
//
////    @Test
////    public void changeMp3BitRate() throws EncoderException {
////        String source = PATH + "smallWavAudioFile.mp3";
////        String target64 = resultDir + "mp3_64k.mp3";
////        int bitRate64 = 64000;
////
////        AudioFileConverter.toMp3(source, target64/*, bitRate64*/);
////
////        MultimediaInfo target64Info = encoder.getInfo(new File(target64));
////        AudioInfo target64InfoAudio = target64Info.getAudio();
////
////        assertEquals("mp3", target64Info.getFormat());
////        assertEquals(bitRate64, target64InfoAudio.getBitRate() * 1000);
////
////        String target256 = resultDir + "mp3_256k.mp3";
////        int bitRate256 = 256000;
////
////        AudioFileConverter.toMp3(source, target256/*, bitRate256*/);
////
////        AudioInfo target256InfoAudio = encoder.getInfo(new File(target256)).getAudio();
////        assertEquals(bitRate256, target256InfoAudio.getBitRate() * 1000);
////    }
//
////    @Test
////    public void wavToMp3() throws EncoderException {
////        String source = PATH + "smallWavAudioFile.wav";
////        String target64 = resultDir + "wav64k.mp3";
////        int bitRate64 = 64000;
////
////        AudioFileConverter.toMp3(source, target64, bitRate64);
////
////        MultimediaInfo target64Info = encoder.getInfo(new File(target64));
////        AudioInfo target64InfoAudio = target64Info.getAudio();
////
////        assertEquals("mp3", target64Info.getFormat());
////        assertEquals(bitRate64, target64InfoAudio.getBitRate() * 1000);
////
////        String target256 = resultDir + "wav256k.mp3";
////        int bitRate256 = 256000;
////
////        AudioFileConverter.toMp3(source, target256, bitRate256);
////
////        AudioInfo target256InfoAudio = encoder.getInfo(new File(target256)).getAudio();
////        assertEquals(bitRate256, target256InfoAudio.getBitRate() * 1000);
////    }
//
////    @Test
////    public void aiffToMp3() throws EncoderException {
////        String source = PATH + "smallWavAudioFile.aiff";
////        String target = resultDir + "aiff.mp3";
////
////        AudioFileConverter.toMp3(source, target, null);
////
////        MultimediaInfo targetInfo = encoder.getInfo(new File(target));
////        assertEquals("mp3", targetInfo.getFormat());
////    }
//
////    @Test
////    public void mp3ToWav() throws EncoderException {
////        String source = PATH + "smallWavAudioFile.mp3";
////        String target = resultDir + "mp3.wav";
////
////        AudioFileConverter.mp3ToWav(source, target);
////
////        MultimediaInfo targetInfo = encoder.getInfo(new File(target));
////        assertEquals("wav", targetInfo.getFormat());
////    }
////
////    @Test
////    public void aiffToWav() throws EncoderException {
////        String source = PATH + "smallWavAudioFile.aiff";
////        String target = resultDir + "aiff.wav";
////
////        AudioFileConverter.aiffToWav(source, target);
////
////        MultimediaInfo targetInfo = encoder.getInfo(new File(target));
////        assertEquals("wav", targetInfo.getFormat());
////    }
//
////    @Test
////    public void watermark() throws EncoderException, InterruptedException {
////        AudioFileConverter.addWatermarks("C:\\testFolder\\file.mp3", "C:\\testFolder\\water.mp3", "C:\\testFolder\\target.mp3", 10);
//////        generateWatermarkedFiles("C:\\testFolder\\file.mp3",  "C:\\testFolder\\target.mp3",   "",   "");
//////        generateWatermarkedFiles("sanitarium.wav", "_wav_mp3.wav", "_wav_wav.wav", "_wav_aiff.wav");
//////        generateWatermarkedFiles("sanitarium.aiff", "_aiff_mp3.aiff", "_aiff_wav.aiff", "_aiff_aiff.aiff");
////    }
//
//
////    @Test
////    public void setFades(){
////        String input = new File("C:\\testFolder\\sanitarium.mp3").getAbsolutePath();
////        String output = new File("C:\\testFolder\\result.mp3").getAbsolutePath();
////        try {
////            AudioFileConverter.setFades(input, output, 3);
////        } catch (EncoderException | IOException | InterruptedException e) {
////            e.printStackTrace();
////        }
////    }
//
////    private String getAbsolutePath(String path) {
////        return new File(path).getAbsolutePath();
////    }
////
////    private void generateWatermarkedFiles(String source, String targetMp3, String targetWav, String targetAiff) throws EncoderException, InterruptedException {
////        generateWatermarked(source, WATERMARK_MP_3, targetMp3);
////    }
////
////    private void generateWatermarked(String source, String watermark, String target) throws EncoderException, InterruptedException {
////        AudioFileConverter.addWatermarks(getAbsolutePath(PATH + source),
////                getAbsolutePath(PATH + watermark),
////                getAbsolutePath(resultDir + "watermarked" + target), 10);
////    }
//
//}
