import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.*;

/**
 * Created by Wade on 3/17/16.
 */
public class Networking {

    public static void main(String[] args) {
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("starcluster put mycluster /opt/comp529/data/data.txt.part2 /root/data");
            Networking nt = new Networking();
            //String ot = nt.executeCommand("scp -i ~/.ssh/weikey.pem /opt/comp529/data/data.txt.part2 root@ec2-52-37-202-108.us-west-2.compute.amazonaws.com:/root/data");

//
//            JSch jsch = new JSch();
//            jsch.addIdentity("~/Desktop/weikey.pem");
//            jsch.setConfig("StrictHostKeyChecking", "no");

//enter your own EC2 instance IP here
//            Session session=jsch.getSession("ec2-user", "52.37.202.108", 22);
//            session.connect();
//            ChannelSftp channel = null;
//            channel = (ChannelSftp)session.openChannel("sftp");
//            channel.connect();
//            File localFile = new File("/opt/comp529/data/data.txt.part2");
//            //If you want you can change the directory using the following line.
//            channel.cd("/root/data");
//            channel.put(new FileInputStream(localFile),localFile.getName());
//            channel.disconnect();
//            session.disconnect();

            String ot = nt.useProcessBuilder();





            System.out.println("Uploading process finished: " + ot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            System.out.println("process created");
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                System.out.println("line is :" + line);
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return output.toString();

    }



    public String useProcessBuilder() {

        StringBuffer output = new StringBuffer();

        ProcessBuilder pb = new ProcessBuilder("scp", "-i ~/.ssh/weikey.pem",
                "/opt/comp529/data/data.txt.part2", "root@ec2-52-37-202-108.us-west-2.compute.amazonaws.com:/root/data");
        pb.redirectErrorStream(true);
        try {
            Process p = pb.start();
            System.out.println("process created");
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                System.out.println("line is :" + line);
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return output.toString();

    }



}
