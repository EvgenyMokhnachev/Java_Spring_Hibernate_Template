package com.lib.patcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created for inetMarcet
 * User: Volk
 * Date: 23.10.13
 * Time: 1:30
 */
public class PatcherRunner{
    public static String mode;
    public static Map<String, List<String>> patches;

    public static void run(){

        if("init".equals(mode)){
            System.out.println("Mode init(newsbytetv.datacollection.dto DROPED)\n");
            for(String group : patches.keySet()){
                if(!group.equals("ROOT")){
                    try{
                        DataBaseConnector.dropSchema(group);
                        System.out.println("Group: " + group + " DROPED ");
                    }catch(SQLException e){
                        System.out.println("Group: " + group + " not exists now");
                    }
                }
            }
            runGroup("ROOT");
            for(String group : patches.keySet()){
                if(!group.equals("ROOT")){
                    runGroup(group);
                }
            }

        }
        if("normal".equals(mode)){
            System.out.println("Mode normal\n\n");
            for(String group : patches.keySet()){
                if(group != "ROOT"){
                    runGroup(group);
                }
            }
        }
    }

    protected static void runGroup(String group){
        try{
            Integer lastPatch = 0;
            if(!"ROOT".equals(group)){
                lastPatch = DataBaseConnector.getLastPatch(group);
            }
            System.out.println("Start Group: " + group + " last patch: " + lastPatch);
            Integer curPatchNum = 0;
            for(String patch : patches.get(group)){
                try{
                    if(!"ROOT".equals(group)){
                        curPatchNum = Integer.parseInt(patch.substring(0, patch.length() - 4));
                    }
                    if(curPatchNum > lastPatch || "ROOT".equals(group)){
                        DataBaseConnector.execute(PatchReader.getString(group, patch));
                        System.out.println("Patch : " + patch + " done ");
                    }
                }catch(IOException e){
                    System.out.println("Group: " + group + " bed patch read from file: " + patch);
                    System.out.println("Error: " + e.getMessage() + "\n\n");
                }catch(SQLException e){
                    System.out.println("Group: " + group + " bed patch execute: " + patch);
                    System.out.println("Error: " + e.getMessage() + "\n\n");
                }
            }
            if(!"ROOT".equals(group)){
                DataBaseConnector.setLastPatch(group, curPatchNum);
            }
            if(!"ROOT".equals(group)){
                System.out.println("Group: " + group + " done\nLast patch: " + curPatchNum);
            }
            else{
                System.out.println("Group: " + group + " done\n");
            }
        }catch(SQLException e){
            System.out.println("Bed Group: " + group);
            System.out.println("Error: " + e.getMessage() + "\n\n");
        }
    }

}
