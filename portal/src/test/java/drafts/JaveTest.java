//package drafts;
//
////import it.sauronsoftware.jave.*;
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import utils.AudioFileConverter;
//
//import java.io.File;
//
///**
// * Created by yevhen on 4/8/15.
// */
//public class JaveTest {
//    private static final Logger logger = Logger.getLogger(JaveTest.class);
//
//    public void testMain() throws EncoderException, InterruptedException {
//
//        String source = "/home/yevhen/IdeaProjects/AudioTaskManager/portal/src/test/resources/testData/sanitarium.mp3";
//        String target = "/home/yevhen/IdeaProjects/AudioTaskManager/portal/src/test/resources/testData/sanitarium64.mp3";
//
//        AudioFileConverter.toMp3(source, target/*, 64000*/);
//
//        String watermark = "/home/yevhen/IdeaProjects/AudioTaskManager/portal/src/test/resources/testData/smallWavAudioFile.mp3";
//
//        AudioFileConverter.addWatermarks(source, watermark, target, 10);
//        int duration = AudioFileConverter.getTrackDuration(source);
//
//        String result = "/home/yevhen/target/result.mp3";
//        String tmpTarget = "/home/yevhen/target/tmpTarget.mp3";
//
//        int watermarkPeriod = 10;
//
//        int filesNumber = (int) duration / watermarkPeriod;
//
//    }
//
//    public void estWavMp3() throws Exception {
//        File source = new File("src/test/resources/testData/smallWavAudioFile.wav");
//        File target = new File("src/test/resources/testData/smallWavAudioFile.mp3");
//
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("libmp3lame");
//        audio.setBitRate(128000);
//
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("mp3");
//        attrs.setAudioAttributes(audio);
//
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//    public void testMp3Wav() throws Exception {
//        File source = new File("src/test/resources/testData/wav.mp3");
//        File target = new File("src/test/resources/testData/mp3.wav");
//
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("libmp3lame");
//
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("wav");
//        attrs.setAudioAttributes(audio);
//
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//    public void testWavAiff() throws Exception {
//        File source = new File("/home/yevhen/sanitarium.wav");
//        File target = new File("/home/yevhen/sanitarium.aiff");
//
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("pcm_s16le");
//
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("aiff");
//        attrs.setAudioAttributes(audio);
//
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//    public void testAiffWav() throws Exception {
//        File source = new File("src/test/resources/testData/wav.aiff");
//        File target = new File("src/test/resources/testData/aiff.wav");
//
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("pcm_alaw");
//
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("wav");
//        attrs.setAudioAttributes(audio);
//
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//    public void testAiffMp3() throws Exception {
//        File source = new File("src/test/resources/testData/wav.aiff");
//        File target = new File("src/test/resources/testData/aiff.mp3");
//
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("libmp3lame");
//        audio.setBitRate(128000);
//        audio.setChannels(2);
//        audio.setSamplingRate(44100);
//
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("mp3");
//        attrs.setAudioAttributes(audio);
//
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//
//    private String[] strings = {
//            "ac3",
//            "adpcm_adx",
//            "adpcm_ima_wav",
//            "adpcm_ms",
//            "adpcm_swf",
//            "adpcm_yamaha",
//            "flac",
//            "g726",
//            "libamr_nb",
//            "libamr_wb",
//            "libfaac",
//            "libgsm",
//            "libgsm_ms",
//            "libmp3lame",
//            "libvorbis",
//            "mp2",
//            "pcm_alaw",
//            "pcm_mulaw",
//            "pcm_s16be",
//            "pcm_s16le",
//            "pcm_s24be",
//            "pcm_s24daud",
//            "pcm_s24le",
//            "pcm_s32be",
//            "pcm_s32le",
//            "pcm_s8",
//            "pcm_u16be",
//            "pcm_u16le",
//            "pcm_u24be",
//            "pcm_u24le",
//            "pcm_u32be",
//            "pcm_u32le",
//            "pcm_u8",
//            "pcm_zork",
//            "roq_dpcm",
//            "sonic",
//            "sonicls",
//            "vorbis",
//            "wmav1",
//            "wmav2"};
//
//    public void estAiffMp3() throws Exception {
//        File source = new File("src/test/resources/testData/smallWavAudioFile.wav");
//        File target = new File("src/test/resources/testData/target.aif");
//
//        for (String codec : strings) {
//            AudioAttributes audio = new AudioAttributes();
//            audio.setCodec(codec);
//            audio.setBitRate(128000);
//            audio.setChannels(2);
//            audio.setSamplingRate(44100);
//            EncodingAttributes attrs = new EncodingAttributes();
//            attrs.setFormat("aiff");
//            attrs.setAudioAttributes(audio);
//
//            Encoder encoder = new Encoder();
//            try {
//                encoder.encode(source, target, attrs);
//            } catch (Exception e) {
//                int a = 0;
//                continue;
//            }
//            int a = 0;
//        }
//
//        return;
//    }
//}
//
