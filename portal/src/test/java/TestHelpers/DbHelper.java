//package TestHelpers;
//
//import com.lib.patcher.PatcherStart;
//
//import java.io.File;
//
//public class DbHelper{
//    public static boolean isInit = false;
//
//    public static void testDataInit(){
//        if(isInit) return;
//        File file = new File("src/test/resources/patcher.properties");
//        String[] args = new String[]{file.getAbsolutePath()};
//        PatcherStart.main(args);
//        isInit = true;
//    }
//}