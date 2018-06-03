package MockFormula.lib.Method;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Speaker {
    public static boolean speakWhenAnnouncement = true;
    public static boolean speakWhenScreenRefresh = true;
    public static boolean speakWhenOperateAnnounce = true;
    public static boolean speakWhenChangeOrder = true;
    public static boolean speakWhenChangeSpeaker = true;

    public static boolean speakControl = true;

    public static void speak(String text) {
        try {
            if (speakControl) {
                Runtime.getRuntime().exec("mshta vbscript:CreateObject(\"SAPI.SpVoice\").Speak(\"" + text + "\")(window.close)");
                EventOutput.outputEvent("Speaker : "+text);
            }
        } catch (Exception e) {
            EventOutput.outputException(e);
        }
    }

    public static void StopAllSpeeches(){
        try {
            Runtime.getRuntime().exec("taskkill -f -im mshta.exe");
        } catch (Exception e1) {
            EventOutput.outputException(e1);
        }
    }

    public static void main(String[] args){
        JFrame testFrame =new JFrame();
        testFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        testFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                StopAllSpeeches();
                System.exit(0);
            }
        });
        testFrame.setVisible(true);
        testFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                speak("mouse clicked");
            }
        });
    }
}
