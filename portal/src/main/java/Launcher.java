import org.apache.log4j.Logger;
import utils.JarUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import utils.WebAppUtils;

import java.io.File;

public class Launcher {

    public static void main(String[] args) throws Exception{
        Logger logger = Logger.getLogger(Launcher.class);
        String path = "webapp";
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        Server server = new Server(port);
        server.addConnector(new SelectChannelConnector());
        String  home = JarUtils.getCurrentDirectory();
        if(JarUtils.isJar() && ! new File(home + "/" + path).exists()){
            JarUtils.extractDirectoryFromClasspath("/" + path, "/" + home + "/" + path);
        }
        //for launch from IDE
        if(!JarUtils.isJar()) path = "portal/src/main/webapp";
        WebAppContext webapp = new WebAppContext(path, "");
        WebAppUtils.webAppFolder = path;
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
            webapp.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
        }
        server.setHandler(webapp);
        server.start();
        logger.info(String.format("SERVER STARTED :%d", port));
    }
}