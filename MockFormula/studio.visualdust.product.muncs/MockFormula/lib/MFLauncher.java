package MockFormula.lib;

import MockFormula.Database.DataResource;
import MockFormula.lib.DataStructure.OrderLists;
import MockFormula.lib.DataStructure.Resource;
import MockFormula.lib.DataStructure.VersionView;
import MockFormula.lib.Method.EventOutput;
import MockFormula.lib.Method.Filereader;
import MockFormula.lib.Method.RAMCleaner;
import MockFormula.lib.Method.Speaker;
import MockFormula.lib.UI.Console.ConsoleWindow;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.swing.*;

public class MFLauncher extends JPanel {
    public static JProgressBar startupprocess = new JProgressBar();
    public static MFLauncher imagepanel = new MFLauncher();
    private static Resource respack = new Resource();
    public static ConsoleWindow console;
    public static JFrame startupframe = new JFrame();
    public static OrderLists orderLists;

    private Image startupimage;

    public MFLauncher() {
        super();
        setOpaque(true);
        try {
            startupimage = Toolkit.getDefaultToolkit().getImage(DataResource.class.getResource("startupwindowlemon.png").toURI().toURL());
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        if (startupimage != null) {
            int height = startupimage.getHeight(this);
            int width = startupimage.getWidth(this);

            if (height != -1 && height > getHeight())
                height = getHeight();

            if (width != -1 && width > getWidth())
                width = getWidth();

            int x = (int) (((double) (getWidth() - width)) / 2.0);
            int y = (int) (((double) (getHeight() - height)) / 2.0);
            g.drawImage(startupimage, x, y, width, height, this);
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            startupframe.setIconImage(new ImageIcon(DataResource.class.getResource("ICONIMG.png").toURI().toURL()).getImage());
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        startupframe.setSize(680, 424);
        startupframe.setResizable(false);
        startupframe.setLocationRelativeTo(null);

        imagepanel.setLayout(null);
        startupprocess.setMinimum(0);
        startupprocess.setMaximum(255);
        startupprocess.setValue(0);
        startupprocess.setBorderPainted(false);
        startupprocess.setString(" ");
        startupprocess.setBackground(null);
        startupprocess.setSize(new Dimension(540, 7));
        startupprocess.setLocation(140, 310);
        /* test code
        JLabel versionLabel = new JLabel(VersionView.getNowVersion()+" ",JLabel.RIGHT);
        imagepanel.add(versionLabel);
        versionLabel.setFont(new Font(null ,0,5));
        versionLabel.setForeground(new Color(222,222,222));
        versionLabel.setSize(680,9);
        versionLabel.setLocation(0,130);
        */

        startupframe.add(imagepanel);
        startupframe.setUndecorated(true);
        startupframe.setVisible(true);

        try {
            UIManager.setLookAndFeel(respack.getDefaultlookandfeel());
        } catch (Exception e) {
            EventOutput.outputException(e);
        }

        startupframe.setAlwaysOnTop(true);


        orderLists = new OrderLists();
        console = new ConsoleWindow();//Create the console window
        console.consolewind.setTitle(VersionView.getNowVersion()+" By VisualDust");
        console.consolewind.setMinimumSize(new Dimension(1024,760));
        orderLists.AddToConsole();
        try {
            console.consolewind.setIconImage(new ImageIcon(DataResource.class.getResource("ICONIMG.png").toURI().toURL()).getImage());
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            Filereader fileReader = new Filereader();
        } catch (Exception e) {
            EventOutput.outputException(e);
        }
        RAMCleaner clser = new RAMCleaner();
        EventOutput.outputEvent(VersionView.getNowVersion()+"_startup");
        Speaker.speak("Waiting for Mock Formula");
        ProcessThread processThread = new ProcessThread();
        processThread.run();
        console.consolewind.setVisible(true);
        startupframe.setVisible(false);
    }

    public static class ProcessThread extends Thread {
        @Override
        public void run() {
            try {
                sleep(0);
            } catch (Exception e) {
                EventOutput.outputException(e);
            }
            imagepanel.add(startupprocess);
            for (int temp = 1; temp <= 255; temp++) {
                try {
                    sleep(3L + (long) (Math.random() * 10));
                } catch (Exception e) {
                    EventOutput.outputException(e);
                }
                startupprocess.setForeground(new Color(255 - temp, temp, temp));
                startupprocess.setValue(temp);
            }
            try {
                sleep(500);
            } catch (Exception e) {
                EventOutput.outputException(e);
            }
        }
    }
}