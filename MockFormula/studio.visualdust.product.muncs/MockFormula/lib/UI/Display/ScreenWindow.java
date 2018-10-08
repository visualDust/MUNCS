package MockFormula.lib.UI.Display;

import MockFormula.lib.Method.EventOutput;
import MockFormula.lib.MFLauncher;
import MockFormula.lib.DataStructure.Resource;
import MockFormula.lib.Method.Speaker;
import MockFormula.lib.UI.Console.ConsoleWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ScreenWindow {
    public static JFrame screenwindow = new JFrame();
    public static JPanel border = new JPanel();
    public static Resource respack = new Resource();
    public static JLabel totaltimelabel = new JLabel("0000s");
    public static JLabel singletimelabel = new JLabel("0000s");
    public static Color tocolor = respack.getcolor("screenbuckcolor");
    public static int changeusedtimes = 100;//how many times does the color change
    public static int singlechangetime = 1;//how long change a piece
    public static colorchangethread colorchanger = new colorchangethread();
    public static JLabel infotextlabel = new JLabel("动议大致内容", JLabel.CENTER);
    public static JLabel annoucerinflabel = new JLabel("动议其他详情", JLabel.CENTER);
    public static JPanel colorpanel = new JPanel();
    public static int noticeappeartime = 2000;
    public static int displaydelaytime = 1;
    public static JLabel infoactionlabel = new JLabel("决议结果条", JLabel.CENTER);
    public static JLabel questionlabel = new JLabel("这里将显示权力和问题", JLabel.CENTER);
    public static JLabel twiterlabel = new JLabel("这里将会显示来自控制台的推送消息", JLabel.CENTER);
    public static JLabel meetinginfolabel = new JLabel("这里将会显示这次会议的出席信息", JLabel.LEFT);
    public static JLabel Announcerinfolabel = new JLabel("这里将会显示发言者详情", JLabel.CENTER);
    public static JTextArea listprinttextarea = new JTextArea();
    public static JScrollPane listprintscrollpane = new JScrollPane(listprinttextarea);
    private int labelfontsize = 35;
    public static int shotScreenTime = 50;

    /**
     * test code
     */
    public static class CaptureService extends Thread {
        private Robot r;

        public CaptureService() {
            try {
                r = new Robot();
            } catch (AWTException e) {
                EventOutput.outputException(e);
            }
        }

        @Override
        public void run() {
            for (; ; ) {
                try {
                    Rectangle rect = getWindowRectangle(screenwindow);
                    BufferedImage img = r.createScreenCapture(rect);

                    try {
                        ConsoleWindow.ThumbPanel target = MFLauncher.console.screennowpanel;
                        target.displayImage(img);
                    } catch (NullPointerException ignored) {
                        //ignored
                    }

                    Thread.sleep(shotScreenTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        private Rectangle getWindowRectangle(JFrame frame) {
            Rectangle rectangle = new Rectangle(frame.getLocation(), frame.getSize());
            return rectangle;
        }
    }

    public ScreenWindow() {
        screenwindow.add(border);
        screenwindow.setVisible(false);
        //screenwindow.setResizable(false);
        border.setLayout(null);
        border.setSize(screenwindow.getWidth(), screenwindow.getHeight());
        setFullScreen(false);
        screenwindow.addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                repaintWindow();
            }
        });
        screenwindow.setBackground(respack.getcolor("screenbuckcolor"));
        border.setBackground(new Color(0, 36, 78));

        colorpanel.setSize(screenwindow.getWidth(), 71);
        colorpanel.setLocation(0, -screenwindow.getWidth() - 1);
        infoactionlabel.setBackground(null);
        infoactionlabel.setFont(new Font("微软雅黑", 0, 45));
        infoactionlabel.setForeground(respack.getcolor("defaultfontcolor"));
        colorpanel.add(infoactionlabel);
        border.add(colorpanel);

        questionlabel.setBackground(null);
        questionlabel.setFont(new Font("微软雅黑", 0, labelfontsize));
        questionlabel.setForeground(respack.getcolor("defaultfontcolor"));
        questionlabel.setSize(screenwindow.getWidth() - 100, 50);
        questionlabel.setLocation(50, screenwindow.getHeight() - 120);
        border.add(questionlabel);
        twiterlabel.setSize(screenwindow.getWidth() - 100, 50);
        twiterlabel.setLocation(50, screenwindow.getHeight() - 190);
        twiterlabel.setFont(new Font("微软雅黑", 0, 30));
        twiterlabel.setBackground(null);
        twiterlabel.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        border.add(twiterlabel);

        totaltimelabel.setFont(new Font("微软雅黑", 1, 60));
        totaltimelabel.setForeground(respack.getcolor("screenfontcolor"));
        singletimelabel.setFont(new Font("微软雅黑", 1, 40));
        singletimelabel.setForeground(respack.getcolor("screenfontcolor"));
        infotextlabel.setForeground(respack.getcolor("defaultfontcolor"));
        infotextlabel.setFont(new Font("微软雅黑", 0, labelfontsize));
        infotextlabel.setBackground(null);
        infotextlabel.setLocation(320, 80);
        infotextlabel.setSize(screenwindow.getWidth() - 400, 60);
        annoucerinflabel.setBackground(null);
        annoucerinflabel.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        annoucerinflabel.setFont(new Font("微软雅黑", 0, 30));
        annoucerinflabel.setSize(screenwindow.getWidth() - 400, 60);
        annoucerinflabel.setLocation(320, 140);
        infotextlabel.setText("这里将会显示动议概览");
        annoucerinflabel.setText("这里将会显示动议详情");

        listprintscrollpane.setBackground(respack.getcolor("screenbuckcolor"));
        listprintscrollpane.getVerticalScrollBar().setVisible(false);
        listprinttextarea.setBackground(respack.getcolor("screenbuckcolor"));
        listprinttextarea.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        listprinttextarea.setEditable(false);
        listprinttextarea.append("这里将会显示发言列表\n发言者1\n发言者2\n发言者3\n发言者4\n发言者5");
        listprinttextarea.setFont(new Font("微软雅黑", 0, 30));
        JPopupMenu listprinttextareapopmenu = new JPopupMenu();
        JCheckBoxMenuItem listeditablemenuitem = new JCheckBoxMenuItem("防误触");
        listeditablemenuitem.setSelected(true);
        listeditablemenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeditablemenuitem.isSelected()) {
                    listprinttextarea.setEditable(false);
                } else {
                    listprinttextarea.setEditable(true);
                }
            }
        });
        listprinttextareapopmenu.add(listeditablemenuitem);
        listprinttextarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    listprinttextareapopmenu.show(listprinttextarea, e.getX(), e.getY());
                }
            }
        });
        listprintscrollpane.setLocation(50, 220);
        listprintscrollpane.setSize(400, screenwindow.getHeight() - 430);
        border.add(listprintscrollpane);

        Announcerinfolabel.setForeground(respack.getcolor("defaultfontcolor"));
        Announcerinfolabel.setLocation(450, 220);
        Announcerinfolabel.setFont(new Font("微软雅黑", 0, 35));
        Announcerinfolabel.setSize(screenwindow.getWidth() - 500, 50);
        border.add(Announcerinfolabel);

        border.add(annoucerinflabel);
        border.add(infotextlabel);
        totaltimelabel.setSize(200, 60);
        singletimelabel.setSize(200, 60);
        border.add(totaltimelabel);
        border.add(singletimelabel);
        border.setLocation(0, 0);
        totaltimelabel.setLocation(50, 80);
        singletimelabel.setLocation(50, 140);

        windowSizeChangeListener windowSizeChangeAdapter = new windowSizeChangeListener();
        windowSizeChangeAdapter.start();


        new CaptureService().start();
    }

    public static void refresh() {
        boolean somethingnew = false;
        System.out.println(MFLauncher.console.editQuestionWindow.questionfield.isEnabled());
        if (!MFLauncher.console.editQuestionWindow.questionfield.isEnabled()) {
            somethingnew = true;
            try {
                questionlabel.setText(MFLauncher.console.editQuestionWindow.questionfield.getText() + " 提出 " + MFLauncher.console.editQuestionWindow.questiontype.getItemAt(MFLauncher.console.editQuestionWindow.questiontype.getSelectedIndex()));
                MFLauncher.console.editQuestionWindow.questionfield.setEnabled(true);
                MFLauncher.console.editQuestionWindow.questiontype.setEnabled(true);
                MFLauncher.console.editQuestionWindow.questionyesbutton.setEnabled(true);
            } catch (Exception e) {
                EventOutput.outputException(e);
            }
        }
        if (!MFLauncher.console.editAnnouncementWindow.about.isEnabled()) {
            somethingnew = true;
            try {
                String speakString = "";
                infotextlabel.setText("关于 " + MFLauncher.console.editAnnouncementWindow.about.getText() + " 的 " + MFLauncher.console.editAnnouncementWindow.infotype.getItemAt(MFLauncher.console.editAnnouncementWindow.infotype.getSelectedIndex()));
                annoucerinflabel.setText("提出者：" + MFLauncher.console.editAnnouncementWindow.announcernametextfield.getText());
                speakString = speakString + MFLauncher.console.editAnnouncementWindow.announcernametextfield.getText() + "，提出了关于" + MFLauncher.console.editAnnouncementWindow.about.getText() + " 的 " + MFLauncher.console.editAnnouncementWindow.infotype.getItemAt(MFLauncher.console.editAnnouncementWindow.infotype.getSelectedIndex());
                if (Integer.valueOf(String.valueOf(MFLauncher.console.editAnnouncementWindow.totaltimefield.getValue())) != 0) {
                    annoucerinflabel.setText(annoucerinflabel.getText() + "，总时长：" + Integer.valueOf(String.valueOf(MFLauncher.console.editAnnouncementWindow.totaltimefield.getValue())));
                    speakString = speakString + "，总时长：" + Integer.valueOf(String.valueOf(MFLauncher.console.editAnnouncementWindow.totaltimefield.getValue()));
                }
                if (Integer.valueOf(String.valueOf(MFLauncher.console.editAnnouncementWindow.singletimefield.getValue())) != 0) {
                    annoucerinflabel.setText(annoucerinflabel.getText() + "，分时长：" + Integer.valueOf(String.valueOf(MFLauncher.console.editAnnouncementWindow.singletimefield.getValue())));
                    speakString = speakString + "，分时长：" + Integer.valueOf(String.valueOf(MFLauncher.console.editAnnouncementWindow.singletimefield.getValue()));
                    if (Integer.valueOf(String.valueOf(MFLauncher.console.editAnnouncementWindow.totaltimefield.getValue())) != 0 && Integer.valueOf(String.valueOf(MFLauncher.console.editAnnouncementWindow.singletimefield.getValue())) != 0)
                        speakString = speakString + "，共可容纳" + Integer.valueOf(Integer.valueOf(String.valueOf(MFLauncher.console.editAnnouncementWindow.totaltimefield.getValue()))) / Integer.valueOf(String.valueOf(MFLauncher.console.editAnnouncementWindow.singletimefield.getValue())) + "名代表发言。";
                }
                if (MFLauncher.console.editAnnouncementWindow.order.getSelectedIndex() != 0) {
                    speakString = speakString + MFLauncher.console.editAnnouncementWindow.order.getItemAt(MFLauncher.console.editAnnouncementWindow.order.getSelectedIndex()).toString();
                }
                Speaker.speak(speakString);
                speakString = "";
            } catch (Exception e) {
                EventOutput.outputException(e);
            }
        }
        if (somethingnew) {
            somethingnew = false;
            colorpanel.setBackground(new Color(123, 123, 123));
            MFLauncher.console.screennexttextarea.setText("");
            infoactionlabel.setText("屏幕有内容更新");
            inforactioncolormovingthread tempthread = new inforactioncolormovingthread();
            tempthread.start();
        }
    }

    public void setFullScreen(boolean flag) {
        this.screenwindow.setUndecorated(flag);
        this.screenwindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public static void infoaction(int action) {
        if (action == 0) {
            colorpanel.setBackground(new Color(0, 170, 46));
            infoactionlabel.setText("该动议获得通过");
        } else if (action == 1) {
            colorpanel.setBackground(new Color(170, 16, 0));
            infoactionlabel.setText("该动议未获得通过");
        } else if (action == 2) {
            infotextlabel.setText("");
            annoucerinflabel.setText("");
            colorpanel.setBackground(new Color(123, 123, 123));
            infoactionlabel.setText("动议被清除");
        }
        inforactioncolormovingthread tempthread = new inforactioncolormovingthread();
        tempthread.start();
    }

    public static void questionaction(int action) {
        if (action == 0) {
            colorpanel.setBackground(new Color(0, 170, 46));
            infoactionlabel.setText("该问题获得通过");
        } else if (action == 1) {
            colorpanel.setBackground(new Color(170, 16, 0));
            infoactionlabel.setText("该问题未获得通过");
        } else if (action == 2) {
            questionlabel.setText("");
            colorpanel.setBackground(new Color(123, 123, 123));
            infoactionlabel.setText("问题被清除");
        }
        questionactioncolormovingthread tempthread = new questionactioncolormovingthread();
        tempthread.start();
    }

    public static class colorchangethread extends Thread {
        @Override
        public void run() {
            Color oricolor = border.getBackground();
            int rchanger = (tocolor.getRed() - oricolor.getRed()) / changeusedtimes;
            int gchanger = (tocolor.getGreen() - oricolor.getGreen()) / changeusedtimes;
            int bchanger = (tocolor.getBlue() - oricolor.getBlue()) / changeusedtimes;
            for (int temp = 1; temp <= changeusedtimes; temp++) {
                try {
                    sleep(singlechangetime);
                    border.setBackground(new Color(oricolor.getRed() + rchanger * temp, oricolor.getGreen() + gchanger * temp, oricolor.getBlue() + bchanger * temp));
                    listprinttextarea.setBackground(new Color(oricolor.getRed() + rchanger * temp, oricolor.getGreen() + gchanger * temp, oricolor.getBlue() + bchanger * temp));
                    listprintscrollpane.setBackground(new Color(oricolor.getRed() + rchanger * temp, oricolor.getGreen() + gchanger * temp, oricolor.getBlue() + bchanger * temp));
                } catch (Exception e) {
                    EventOutput.outputException(e);
                }
            }
            border.setBackground(tocolor);
            listprinttextarea.setBackground(tocolor);
            listprintscrollpane.setBackground(tocolor);
        }
    }

    public static class questionactioncolormovingthread extends Thread {
        /*you need to set the Text of "infoactionlabel and the Color of colorpanel"*/
        @Override
        public void run() {
            tocolor = Color.lightGray;
            colorchangethread col1 = new colorchangethread();
            col1.start();
            for (int temp = border.getHeight(); temp >= border.getHeight() - 70; temp--) {
                try {
                    sleep(displaydelaytime);
                } catch (Exception e) {
                    EventOutput.outputException(e);
                }
                colorpanel.setLocation(0, temp + 1);
            }
            try {
                sleep(noticeappeartime);
            } catch (Exception e) {
                EventOutput.outputException(e);
            }
            tocolor = respack.getcolor("screenbuckcolor");
            colorchangethread col2 = new colorchangethread();
            col2.start();
            for (int temp = border.getHeight() - 70; temp <= border.getHeight(); temp++) {
                try {
                    sleep(displaydelaytime);
                } catch (Exception e) {
                    EventOutput.outputException(e);
                }
                colorpanel.setLocation(0, temp + 1);
            }
        }
    }

    public static class inforactioncolormovingthread extends Thread {
        /*you need to set the label of "infoactionlabel and the color of colorpanel"*/
        @Override
        public void run() {
            try {
            } catch (Exception e) {
                EventOutput.outputException(e);
            }
            tocolor = Color.lightGray;
            colorchangethread col1 = new colorchangethread();
            col1.start();
            for (int temp = 0; temp <= 70; temp++) {
                try {
                    sleep(displaydelaytime);
                } catch (Exception e) {
                    EventOutput.outputException(e);
                }
                colorpanel.setLocation(0, temp - 71);
            }
            try {
                sleep(noticeappeartime);
            } catch (Exception e) {
                EventOutput.outputException(e);
            }
            tocolor = respack.getcolor("screenbuckcolor");
            colorchangethread col2 = new colorchangethread();
            col2.start();
            for (int temp = 70; temp >= 0; temp--) {
                try {
                    sleep(displaydelaytime);
                } catch (Exception e) {
                    EventOutput.outputException(e);
                }
                colorpanel.setLocation(0, temp - 71);
            }
        }
    }

    public static void reloadcolors() {
        border.setBackground(respack.getcolor("screenbuckcolor"));
        if (screenwindow.isVisible()) {
            MFLauncher.console.screennowpanel.setBackground(respack.getcolor("screenbuckcolor"));
        }
        listprinttextarea.setBackground(respack.getcolor("screenbuckcolor"));
        listprintscrollpane.setBackground(respack.getcolor("screenbuckcolor"));
    }

    public static void repaintWindow() {
        border.setLocation(0, 0);
        border.setSize(screenwindow.getWidth(), screenwindow.getHeight());
        totaltimelabel.setLocation(50, 80);
        totaltimelabel.setSize(200, 60);
        singletimelabel.setLocation(50, 140);
        singletimelabel.setSize(200, 60);
        infotextlabel.setLocation(320, 80);
        infotextlabel.setSize(screenwindow.getWidth() - 400, 60);
        colorpanel.setSize(border.getWidth(), 71);
        annoucerinflabel.setLocation(320, 140);
        annoucerinflabel.setSize(screenwindow.getWidth() - 400, 60);
        questionlabel.setLocation(50, screenwindow.getHeight() - 120);
        questionlabel.setSize(screenwindow.getWidth() - 100, 50);
        twiterlabel.setLocation(50, screenwindow.getHeight() - 190);
        twiterlabel.setSize(screenwindow.getWidth() - 100, 50);
        Announcerinfolabel.setLocation(450, 220);
        Announcerinfolabel.setSize(screenwindow.getWidth() - 500, 50);
        listprintscrollpane.setLocation(50, 220);
        listprintscrollpane.setSize(400, screenwindow.getHeight() - 430);
    }

    public class windowSizeChangeListener extends Thread {
        @Override
        public void run() {
            for (; ; ) {
                double hight = screenwindow.getHeight();
                double width = screenwindow.getWidth();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    EventOutput.outputException(e);
                }
                if (screenwindow.getHeight() != hight || screenwindow.getWidth() != width) {
                    repaintWindow();
                }
            }
        }
    }
}

