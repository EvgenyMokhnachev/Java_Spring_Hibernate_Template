//package parsers;
//
//import junit.framework.TestCase;
//import parsers.wav.WavFile;
//import parsers.wav.WavFileException;
//
//import java.io.File;
//import java.io.IOException;
//
//public class TestWavFile extends TestCase{
//    public void testWavFile(){
//        WavFile wavFile = null;
//        try {
//            wavFile = WavFile.openWavFile(new File("C:\\Dropbox\\JohnMohnachevNB\\AudioTaskManager\\portal\\src\\test\\resources\\testData\\smallWavAudioFile.wav"));
//        } catch (IOException | WavFileException e) {
//            e.printStackTrace();
//        }
//        assertNotNull(wavFile);
//
//        double[][] samples = wavFile.getSamples();
//        assertNotNull(samples);
//    }
//}
