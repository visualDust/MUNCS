package MockFormula.lib.Method;

import MockFormula.lib.MFLauncher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventOutput {
    public static File logoutfile = new File(LocalDate.now().toString()+"MF.log");
    public static OutputStream logstream;

    public static void outputException(Exception e) {
        try {
            logstream = new FileOutputStream(logoutfile, true);
            logstream.write(("  "+LocalDateTime.now().toString() + " Exception : " + e.toString() + "\r\n").getBytes());
        } catch (Exception e1) {
            System.out.println(e1.toString());
            MFLauncher.console.noticeanderrortextarea.append(e1.toString() + "\r\n");
            e1.printStackTrace();
        }
        System.out.println(e.toString());
        MFLauncher.console.noticeanderrortextarea.append(e.toString() + "\r\n");
        e.printStackTrace();
    }
    public static void outputEvent(String eventstr){
        try {
            logstream = new FileOutputStream(logoutfile, true);
            logstream.write((LocalDateTime.now().toString() + " Event : " + eventstr + "\r\n").getBytes());
        } catch (Exception e1) {
            System.out.println(e1.toString());
            MFLauncher.console.noticeanderrortextarea.append(e1.toString() + "\r\n");
            e1.printStackTrace();
        }
        System.out.println(eventstr);
    }
}
