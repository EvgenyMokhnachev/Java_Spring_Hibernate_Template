package utils;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarUtils{


    public static void extractDirectoryFromClasspath(String sourceDirectory, String writeDirectory) throws IOException{
        // make sure write directory exists
        new File(writeDirectory).mkdirs();
        System.out.println("Write Directory  is " + writeDirectory);
        extract(sourceDirectory, writeDirectory);
    }
    public static boolean isJar(){
       return JarUtils.class.getProtectionDomain().getCodeSource().getLocation().toString().contains(".jar");
    }
    public static String getCurrentDirectory(){
        return new File(JarUtils.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6)).getParent();
    }

    public static void extract(String sourceDirectory, String writeDirectory) throws IOException{
        final URL dirURL = JarUtils.class.getClass().getResource(sourceDirectory);
        final String path = sourceDirectory.substring(1);

        if((dirURL != null) && dirURL.getProtocol().equals("jar")){
            final JarURLConnection jarConnection = (JarURLConnection) dirURL.openConnection();
            System.out.println("jarConnection is " + jarConnection);

            final ZipFile jar = jarConnection.getJarFile();

            final Enumeration<? extends ZipEntry> entries = jar.entries(); // gives ALL entries in jar

            while(entries.hasMoreElements()){
                final ZipEntry entry = entries.nextElement();
                final String name = entry.getName();
                // System.out.println( name );
                if(!name.startsWith(path)){
                    // entry in wrong subdir -- don't copy
                    continue;
                }
                final String entryTail = name.substring(path.length());

                final File f = new File(writeDirectory + File.separator + entryTail);
                if(entry.isDirectory()){
                    // if its a directory, create it
                    final boolean bMade = f.mkdir();
                    System.out.println((bMade ? "  creating " : "  unable to create ") + name);
                }
                else{
                    System.out.println("  writing  " + name);
                    final InputStream is = jar.getInputStream(entry);
                    final OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
                    final byte buffer[] = new byte[4096];
                    int readCount;
                    // write contents of 'is' to 'os'
                    while((readCount = is.read(buffer)) > 0){
                        os.write(buffer, 0, readCount);
                    }
                    os.close();
                    is.close();
                }
            }

        }
        else if(dirURL == null){
            throw new IllegalStateException("can't find " + sourceDirectory + " on the classpath");
        }
        else{
            // not a "jar" protocol URL
            throw new IllegalStateException("don't know how to handle extracting from " + dirURL);
        }
    }


}
