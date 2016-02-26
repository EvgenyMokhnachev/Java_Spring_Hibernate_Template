package com.lib.patcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatchReader{
    public static String rootPath;
    public static Map<String, List<String>> patches;

    public static String getString(String group, String qName) throws IOException{
        StringBuilder result = new StringBuilder();

        File file = null;
        if("ROOT".equals(group)){
            file = new File(rootPath + "/" + qName);
        }
        else{
            file = new File(rootPath + "/" + group + "/" + qName);
        }
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while(line != null){
            result.append(line + "\n");
            line = br.readLine();
        }
        return result.toString();
    }

    public static void init(){
        File folder = new File(rootPath);
        patches = new HashMap<String, List<String>>();
        patches.put("ROOT", new ArrayList<String>());
        readDir(folder, "ROOT");
    }

    protected static void readDir(File folder, String container){
        patches.put(container, new ArrayList<String>());
        for(final File fileEntry : folder.listFiles()){
            if(fileEntry.isDirectory()){
                readDir(fileEntry, fileEntry.getName());
            }
            else{
                patches.get(container).add(fileEntry.getName());
            }
        }
    }

}