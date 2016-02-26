package com.lib.patcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PatcherStart{
    public static void main(String[] args){
        try{
            Properties properties = new Properties();
            if(args != null && args.length > 0){
                properties.load(new FileInputStream(new File(args[0])));
            }
            else{
                properties.load(PatcherStart.class.getResourceAsStream("/patcher.properties"));
            }
            DataBaseConnector.name = (String) properties.get("Username");
            DataBaseConnector.pswd = (String) properties.get("Password");
            DataBaseConnector.db = (String) properties.get("DataBaseName");
            PatcherRunner.mode = (String) properties.get("PatcherMode");
            DataBaseConnector.ip = (String) properties.get("DataBaseIP");
            PatchReader.rootPath = PatcherStart.class.getResource("/data").getFile();

            System.out.println("START.");
            DataBaseConnector.connect();
            PatchReader.init();
            PatcherRunner.patches = PatchReader.patches;
            PatcherRunner.run();
            System.out.println("END.");

        }catch(IOException e){
            System.out.print("Add path patcher.properties in to arguments PatcherStart.main() or create patcher.properties file in resources");
        }
    }
}
