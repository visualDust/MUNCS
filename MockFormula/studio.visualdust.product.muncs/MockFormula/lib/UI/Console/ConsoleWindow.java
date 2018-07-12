package MockFormula.lib.UI.Console;

import MockFormula.Database.DataResource;
import MockFormula.lib.DataStructure.Resource;
import MockFormula.lib.DataStructure.VersionView;
import MockFormula.lib.MFLauncher;
import MockFormula.lib.Method.*;
import MockFormula.lib.UI.Display.ScreenWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class ConsoleWindow {
    public JFrame consolewind = new JFrame();
    private Resource respack = new Resource();
    private Clockcontrol clocker = new Clockcontrol();
    private ScreenWindow screen = new ScreenWindow();//Create the screen window
    private DisplayConfigWindow configwind = new DisplayConfigWindow();

    private JPanel border = new JPanel();
    private JPanel subframe_left = new JPanel();
    private JPanel leftdownPanel = new JPanel();
    private JPanel subframe_right = new JPanel();
    public JPanel listpanel = new JPanel();
    private JPanel screenactionpanel = new JPanel();
    private JPanel screentwiterpanel = new JPanel();
    private JPanel screenviewpanel = new JPanel();
    private JPanel editWindShowAndTwitButtonPanel = new JPanel();
    private JPanel editButtonPanel = new JPanel();
    public ThumbPanel screennowpanel = new ThumbPanel();

    private JPanel clockcontrolpanel = new JPanel();
    private JPanel clockpanel = new JPanel();
    public JTextArea screennexttextarea = new JTextArea();
    private JScrollPane screennexttextscrollpane = new JScrollPane(screennexttextarea);

    private JTextField twitertextfield = new JTextField();

    private JPanel clockfieldpanel = new JPanel();
    public JLabel clocktotaltextfield = new JLabel("0", JLabel.CENTER);
    public JLabel clocksingletextfield = new JLabel("0", JLabel.CENTER);

    public JTextArea recordtextarea = new JTextArea("");


    public List listcontrol = new List();
    private VersionView versionView = new VersionView();
    public JTextArea noticeanderrortextarea = new JTextArea();

    public  JButton startclockbutton = new JButton("开始计时");
    public  JButton pauseclockbutton = new JButton("暂停计时");
    private JButton nexttimebutton = new JButton("结束此次");
    private JButton clearclockbutton = new JButton("清空计时器");
    private JButton twitbutton = new JButton("推送到主屏");

    public EditAnnouncementWindow editAnnouncementWindow;
    public EditQuestionWindow editQuestionWindow;
    private SpeakerPanel speakerPanel = new SpeakerPanel();

    private int totaltime;
    public int singletime;

    JFileChooser recordsavesfilechooser = new JFileChooser();

    public class EmptyImageObserver implements ImageObserver {

        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            return false;
        }
    }

    public class ThumbPanel extends JPanel {
        private Image image;

        public void displayImage(Image img) {
            this.image = Objects.requireNonNull(img);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.scale((double) this.getWidth() / image.getWidth(this), (double) this.getHeight() / image.getHeight(this));
                g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
            }
        }
    }

    public ConsoleWindow() {
        consolewind.setLayout(new GridLayout(1, 2));
        consolewind.setTitle("MockFormula 2018.6 By Mr.GZT");
        consolewind.setSize(111, 111);
        consolewind.setLocationRelativeTo(null);
        consolewind.setExtendedState(JFrame.MAXIMIZED_BOTH);

        screen.screenwindow.setMinimumSize(new Dimension(800, 600));
        screen.border.setBackground(new Color(0, 36, 78));

        consolewind.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        consolewind.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventOutput.outputEvent("WINDOW_CLOSE_CLICKED");
                if (MessageWindow.showMessageWindow(2, "帅气的主席团，你真的能狠下心退出" + VersionView.getNowVersion() + "吗？").equals("0")) {
                    ExitSystem();
                }
            }
        });
        ScreenWindow.screenwindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ScreenWindow.screenwindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventOutput.outputEvent("WINDOW_CLOSE_CLICKED");
                if (MessageWindow.showMessageWindow(2, "帅气的主席团，你真的能狠下心退出" + VersionView.getNowVersion() + "吗？").equals("0")) {
                    ExitSystem();
                }
            }
        });

        JPopupMenu errinfopopmenu = new JPopupMenu();
        JCheckBoxMenuItem erreditablemenuitem = new JCheckBoxMenuItem("防误触");
        erreditablemenuitem.setSelected(true);
        JMenuItem abouttheerrormenuitem = new JMenuItem("关于错误...");
        errinfopopmenu.add(erreditablemenuitem);
        errinfopopmenu.add(abouttheerrormenuitem);
        errinfopopmenu.add(new JMenuItem("取消"));
        noticeanderrortextarea.add(errinfopopmenu);
        noticeanderrortextarea.setEditable(false);
        noticeanderrortextarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    EventOutput.outputEvent("PopupTrigger at notice_and_error_textarea");
                    errinfopopmenu.show(noticeanderrortextarea, e.getX(), e.getY());
                }
            }
        });
        erreditablemenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (erreditablemenuitem.isSelected()) {
                    noticeanderrortextarea.setEditable(false);
                } else {
                    noticeanderrortextarea.setEditable(true);
                }
            }
        });
        abouttheerrormenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageWindow.showMessageWindow(0, versionView.getAbouterror());
            }
        });

        try {
            screen.screenwindow.setIconImage(new ImageIcon(DataResource.class.getResource("ICONIMG.png").toURI().toURL()).getImage());
        } catch (MalformedURLException | URISyntaxException e) {
            EventOutput.outputException(e);
        }

        recordsavesfilechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        recordsavesfilechooser.setDialogTitle("文件后缀名请自行添加");

        recordtextarea.setForeground(respack.getcolor("defaultfontcolor"));
        recordtextarea.setFont(respack.getFont("recordtextareafont"));
        recordtextarea.setLineWrap(true);
        recordtextarea.setEditable(false);

        JScrollPane recordtextScroll = new JScrollPane(recordtextarea);

        consolewind.setLayout(TempCreater.tempgridlayout(1, 1, 0, 10));
        subframe_left.setLayout(TempCreater.tempgridlayout(3, 1, 10, 10));
        subframe_right.setLayout(TempCreater.tempgridlayout(2, 1, 10, 10));
        border.setBorder(BorderFactory.createLineBorder(respack.getcolor("backgroundcolor"), 20));
        border.setLayout(TempCreater.tempgridlayout(1, 2, 15, 15));
        border.add(subframe_left);
        listpanel.setLayout(TempCreater.tempgridlayout(1, 2, 0, 0));

        border.add(subframe_right);
        subframe_right.add(recordtextScroll);
        subframe_right.add(listpanel);
        consolewind.add(border);

        screentwiterpanel.setLayout(TempCreater.tempgridlayout(2, 1, 5, 5));
        twitertextfield.setBackground(respack.getcolor("backgroundcolor"));
        twitertextfield.setFont(respack.getFont("defaultlabelfont"));
        twitertextfield.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        twitertextfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.getKeyText(e.getKeyCode()).equals("Enter")) {
                    EventOutput.outputEvent("twiter_text_field Keyboard pressed ENTER");
                    if (!twitertextfield.getText().equals("")) {
                        twitclick();
                    }
                }
            }
        });
        JScrollPane twiterscrollpane = new JScrollPane(twitertextfield);
        screentwiterpanel.add(twiterscrollpane);
        screentwiterpanel.add(twitbutton);
        twitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("twit_button clicked");
                if (!twitertextfield.getText().equals("")) {
                    twitclick();
                }
            }
        });

        screenactionpanel.setLayout(TempCreater.tempgridlayout(1, 2, 5, 5));
        screenviewpanel.setLayout(TempCreater.tempgridlayout(1, 2, 5, 5));
        JPopupMenu screennextpopmenu = new JPopupMenu();
        JMenuItem clearnextmenuitem = new JMenuItem("取消这些更新");
        screennextpopmenu.add(clearnextmenuitem);
        clearnextmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("clear_next_menu_item");
                if (MessageWindow.showMessageWindow(2, "确定要取消这些更新?").equals("0")) {
                    screennexttextarea.setText("");
                    editQuestionWindow.clearandresetall();
                    editAnnouncementWindow.reset();
                }
            }
        });
        screennextpopmenu.add(clearnextmenuitem);
        screennextpopmenu.add(new JMenuItem("取消"));
        screennexttextarea.setLineWrap(true);
        screennexttextarea.setEditable(false);
        screennexttextarea.setFont(new Font("微软雅黑", 0, 12));
        screennexttextarea.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        screennexttextarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    EventOutput.outputEvent("PopupTrigger at screen_next_text_area");
                    screennextpopmenu.show(screennexttextarea, e.getX(), e.getY());
                }
            }
        });

        screennowpanel.setBackground(new Color(111, 111, 111));
        JPopupMenu screencontrolpopmenu = new JPopupMenu();
        JMenuItem openscreenmenuitem = new JMenuItem("打开屏幕");
        JMenuItem refreshscreenmenuitem = new JMenuItem("刷新屏幕");

        JMenu infoactionmenu = new JMenu("动议操作...");
        JMenuItem infoacceptmenuitem = new JMenuItem("通过屏幕上的动议");
        JMenuItem infocancelmenuitem = new JMenuItem("否决屏幕上的动议");
        JMenuItem infoclearmenuitem = new JMenuItem("清空屏幕上的动议");
        infoactionmenu.add(infoacceptmenuitem);
        infoactionmenu.add(infocancelmenuitem);
        infoactionmenu.add(infoclearmenuitem);
        infoacceptmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.infoaction(0);
                recordtextarea.append(LocalTime.now().toString() + "：屏幕上的动议获得通过\r\r\n\n");
            }
        });
        infocancelmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.infoaction(1);
                recordtextarea.append(LocalTime.now().toString() + "：屏幕上的动议未获得通过\r\r\n\n");
            }
        });
        infoclearmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.infoaction(2);
            }
        });

        JMenu questionactionmenu = new JMenu("问题操作...");
        JMenuItem questionacceptmenuitem = new JMenuItem("通过屏幕上的问题");
        JMenuItem questioncancelmenuitem = new JMenuItem("否决屏幕上的问题");
        JMenuItem questionclearmenuitem = new JMenuItem("清空屏幕上的问题");
        questionactionmenu.add(questionacceptmenuitem);
        questionactionmenu.add(questioncancelmenuitem);
        questionactionmenu.add(questionclearmenuitem);
        questionacceptmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.questionaction(0);
            }
        });
        questioncancelmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.questionaction(1);
            }
        });
        questionclearmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenWindow.questionaction(2);
            }
        });

        JMenuItem closescreenmenuitem = new JMenuItem("关闭屏幕");
        JMenuItem displaysettingmenuitem = new JMenuItem("显示设置");
        displaysettingmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configwind.displayConfigFrame.setLocation(consolewind.getX() + consolewind.getWidth() / 4, consolewind.getY() + consolewind.getHeight() / 4);
                configwind.setvis(true);
                EventOutput.outputEvent("Config_Wind show");
            }
        });

        screencontrolpopmenu.add(openscreenmenuitem);
        screencontrolpopmenu.add(refreshscreenmenuitem);
        screencontrolpopmenu.add(infoactionmenu);
        screencontrolpopmenu.add(questionactionmenu);
        screencontrolpopmenu.add(closescreenmenuitem);
        screencontrolpopmenu.add(displaysettingmenuitem);
        screencontrolpopmenu.add(new JMenuItem("取消"));
        closescreenmenuitem.setEnabled(false);
        screennowpanel.add(screencontrolpopmenu);
        screennowpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    EventOutput.outputEvent("PopupTrigger at Screen_now_panel");
                    screencontrolpopmenu.show(screennowpanel, e.getX(), e.getY());
                }
            }
        });
        screenviewpanel.add(screennowpanel);
        screenviewpanel.add(screennexttextscrollpane);
        screenactionpanel.add(screenviewpanel);
        subframe_left.add(screenactionpanel);

        JButton shouEditAnnWindButton = new JButton("动议...");
        shouEditAnnWindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAnnouncementWindow.showup();
            }
        });
        JButton showEditQuesWindButton = new JButton("问题/权限...");
        showEditQuesWindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editQuestionWindow.ShowUp();
            }
        });
        editWindShowAndTwitButtonPanel.setLayout(TempCreater.tempgridlayout(1, 2, 5, 5));
        editWindShowAndTwitButtonPanel.add(screentwiterpanel);
        editButtonPanel.setLayout(TempCreater.tempgridlayout(2, 1, 5, 5));
        editButtonPanel.add(shouEditAnnWindButton);
        editButtonPanel.add(showEditQuesWindButton);
        editWindShowAndTwitButtonPanel.add(editButtonPanel);

        JPopupMenu recordtextareapopmenu = new JPopupMenu();
        JMenuItem recordsaverecordmenuitem = new JMenuItem("保存");
        JMenuItem recordresaverecordmenuitem = new JMenuItem("另存为");
//        JMenuItem printrecordmenuitem = new JMenuItem("将记录输出至屏幕"); //TODO have it done .
        JMenuItem recordclearmenuitem = new JMenuItem("清除记录");
        JCheckBoxMenuItem recordeditablemenuitem = new JCheckBoxMenuItem("防误触");
        JMenuItem editableintromenuitem = new JMenuItem("什么是防误触？");
        recordeditablemenuitem.setSelected(true);
        JMenu recordfileoperator = new JMenu("文件...");
        JMenu recordeditablemenu = new JMenu("防误触...");
        recordeditablemenu.add(recordeditablemenuitem);
        recordeditablemenu.add(editableintromenuitem);
        recordfileoperator.add(recordsaverecordmenuitem);
        recordfileoperator.add(recordresaverecordmenuitem);
        recordtextareapopmenu.add(recordeditablemenu);
        recordtextareapopmenu.add(recordfileoperator);
        recordtextareapopmenu.add(recordclearmenuitem);
        recordtextareapopmenu.add(new JMenuItem("取消"));
        recordtextarea.add(recordtextareapopmenu);
        recordtextarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    EventOutput.outputEvent("PopupTrigger at Record_text_area");
                    recordtextareapopmenu.show(recordtextarea, e.getX(), e.getY());
                }
            }
        });
        recordclearmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MessageWindow.showMessageWindow(2, "是否要清除控制窗口中的临时记录？").equals("0")) {
                    if (MessageWindow.showMessageWindow(2, "请再次确认").equals("0"))
                        recordtextarea.setText("");
                }
            }
        });
        recordsaverecordmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (recordsavesfilechooser.getSelectedFile() == null) {
                        recordsavesfilechooser.setLocation(DisplayConfigWindow.displayConfigFrame.getX(), DisplayConfigWindow.displayConfigFrame.getY());
                        recordsavesfilechooser.showSaveDialog(consolewind);
                        EventOutput.outputEvent("Save_Dialog show");
                    }
                    File saves = recordsavesfilechooser.getSelectedFile();
                    OutputStream recordoutputstream = new FileOutputStream(saves, false);
                    recordoutputstream.write((LocalDateTime.now() + "保存的记录\r\n").getBytes());
                    recordoutputstream.write((recordtextarea.getText() + "\r\n").getBytes());
                    TempCreater.tempnoticePopmenu("文件已写入" + recordsavesfilechooser.getSelectedFile().toString(), 10000).show(subframe_right, recordtextarea.getX() + 50, recordtextarea.getY() + recordtextarea.getHeight());
                } catch (Exception e1) {
                    EventOutput.outputException(e1);
                    TempCreater.tempnoticePopmenu("文件没有保存,原因是：" + e1.toString() + "，试试看另存为", 5000).show(subframe_right, recordtextarea.getX() + 50, recordtextarea.getY() + recordtextarea.getHeight());
                }
            }
        });
        recordresaverecordmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    recordsavesfilechooser.showSaveDialog(consolewind);
                    EventOutput.outputEvent("Save_Dialog show");
                    File saves = recordsavesfilechooser.getSelectedFile();
                    OutputStream recordoutputstream = new FileOutputStream(saves, true);
                    recordoutputstream.write((LocalDateTime.now() + "保存的记录\r\n").getBytes());
                    recordoutputstream.write((recordtextarea.getText() + "\r\n").getBytes());
                    TempCreater.tempnoticePopmenu("文件已写入" + recordsavesfilechooser.getSelectedFile().toString(), 10000).show(subframe_right, recordtextarea.getX() + 50, recordtextarea.getY() + recordtextarea.getHeight());
                } catch (Exception e1) {
                    EventOutput.outputException(e1);
                    TempCreater.tempnoticePopmenu("文件没有保存,原因是：" + e1.toString() + "，试试看另存为", 5000).show(subframe_right, recordtextarea.getX() + 50, recordtextarea.getY() + recordtextarea.getHeight());
                }
            }
        });
        recordeditablemenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (recordeditablemenuitem.isSelected()) {
                    recordtextarea.setEditable(false);
                } else {
                    recordtextarea.setEditable(true);
                }
            }
        });
        editableintromenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageWindow.showMessageWindow(0, versionView.getAbouteditable());
            }
        });

        noticeanderrortextarea.setForeground(respack.getcolor("warningbuckcolor"));
        noticeanderrortextarea.setFont(new Font("微软雅黑", 0, 10));
        noticeanderrortextarea.setLineWrap(true);
        JScrollPane noticeanderrorpanel = new JScrollPane(noticeanderrortextarea);
        //subframe_left.add(noticeanderrorpanel); //TODO DELET IF USELES

        clockfieldpanel.setLayout(TempCreater.tempgridlayout(1, 4, 3, 3));
        /*
        clocktotaltextfield.setEditable(false);
        clocksingletextfield.setEditable(false);
        * */
        clocktotaltextfield.setBorder(null);
        clocktotaltextfield.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        clocktotaltextfield.setFont(respack.getFont("defaulttextfont"));
        clocksingletextfield.setBorder(null);
        clocksingletextfield.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        clocksingletextfield.setFont(respack.getFont("defaulttextfont"));
        clockfieldpanel.add(TempCreater.templabel("总时长", respack.getFont("defaulttextfont"), respack.getcolor("backgroundcolor"), respack.getcolor("textfielddefaultbuckcolor")));
        clockfieldpanel.add(clocktotaltextfield);
        clockfieldpanel.add(TempCreater.templabel("分时长", respack.getFont("defaulttextfont"), respack.getcolor("backgroundcolor"), respack.getcolor("textfielddefaultbuckcolor")));
        clockfieldpanel.add(clocksingletextfield);

        clockcontrolpanel.setLayout(TempCreater.tempgridlayout(1, 4, 5, 5));
        pauseclockbutton.setEnabled(false);
        clockcontrolpanel.add(startclockbutton);
        clockcontrolpanel.add(pauseclockbutton);
        clockcontrolpanel.add(nexttimebutton);
        clockcontrolpanel.add(clearclockbutton);
        clockpanel.setLayout(TempCreater.tempgridlayout(2, 1, 5, 5));
        clockpanel.add(clockfieldpanel);
        clockpanel.add(clockcontrolpanel);
        leftdownPanel.setLayout(TempCreater.tempgridlayout(2, 1, 5, 5));
        leftdownPanel.add(editWindShowAndTwitButtonPanel);
        leftdownPanel.add(clockpanel);
        subframe_left.add(leftdownPanel);
        subframe_left.add(speakerPanel);
        startclockbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("Start_clock_Button clicked");
                clocker.running = true;
                pauseclockbutton.setEnabled(true);
                startclockbutton.setEnabled(false);
            }
        });
        pauseclockbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("Pause_clock_Button clicked");
                clocker.running = false;
                pauseclockbutton.setEnabled(false);
                startclockbutton.setEnabled(true);
            }
        });
        nexttimebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("Next_time_Button clicked");
                clocker.running = false;
                startclockbutton.setEnabled(true);
                pauseclockbutton.setEnabled(false);
                if (Integer.valueOf(clocktotaltextfield.getText()) >= Integer.valueOf(clocksingletextfield.getText())) {
                    clocktotaltextfield.setText(String.valueOf(Integer.valueOf(clocktotaltextfield.getText()) - Integer.valueOf(clocksingletextfield.getText())));
                    ScreenWindow.totaltimelabel.setText(clocktotaltextfield.getText() + " s");
                    clocksingletextfield.setText("0");
                    if (Integer.valueOf(String.valueOf(clocktotaltextfield.getText())) >= singletime) {
                        clocksingletextfield.setText(String.valueOf(singletime));
                    }
                    ScreenWindow.singletimelabel.setText(String.valueOf(singletime) + " s");
                    ScreenWindow.totaltimelabel.setText(String.valueOf(totaltime) + " s");
                } else {
                    clocksingletextfield.setText("0");
                    ScreenWindow.singletimelabel.setText("0s");
                }
                ScreenWindow.colorpanel.setBackground(new Color(255, 150, 0));
                ScreenWindow.infoactionlabel.setText("本次计时被手动结束");
                ScreenWindow.infoaction(3);
                MessageWindow.showMessageWindow(0, "本次计时被手动结束");
            }
        });
        clearclockbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("Clear_clock_Button clicked");
                if (MessageWindow.showMessageWindow(2, "确认要清空计时器吗?").equals("0")) {
                    clocktotaltextfield.setText("0");
                    clocksingletextfield.setText("0");
                    ScreenWindow.totaltimelabel.setText("0000s");
                    ScreenWindow.singletimelabel.setText("0000s");
                    totaltime = 0;
                    singletime = 0;
                }
            }
        });

        openscreenmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordtextarea.append(LocalTime.now().toString() + "：控制台打开了大屏幕\r\r\n\n");
                screennowpanel.setBackground(respack.getcolor("screenbuckcolor"));
                openscreenmenuitem.setEnabled(false);
                closescreenmenuitem.setEnabled(true);
                screencontrol(0);
            }
        });
        refreshscreenmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MessageWindow.showMessageWindow(2, "确认将新的内容刷新至主屏幕？\n" +
                        "提示：计时器会随主屏幕的更新而更新").equals("0")) {
                    if (!editAnnouncementWindow.about.isEnabled() || !editQuestionWindow.questionfield.isEnabled()) {
                        screencontrol(1);
                        try {
                            totaltime = Integer.valueOf(String.valueOf(editAnnouncementWindow.totaltimefield.getValue()));
                            clocktotaltextfield.setText(String.valueOf(totaltime));
                            ScreenWindow.totaltimelabel.setText(String.valueOf(totaltime) + " s");
                        } catch (Exception e1) {
                            totaltime = 0;
                            editAnnouncementWindow.totaltimefield.setBackground(respack.getcolor("warningbuckcolor"));
                            EventOutput.outputException(e1);
                        }
                        try {
                            singletime = Integer.valueOf(String.valueOf(editAnnouncementWindow.singletimefield.getValue()));
                            clocksingletextfield.setText(String.valueOf(singletime));
                            ScreenWindow.singletimelabel.setText(String.valueOf(singletime) + " s");
                        } catch (Exception e1) {
                            singletime = 0;
                            editAnnouncementWindow.singletimefield.setBackground(respack.getcolor("warningbuckcolor"));
                            EventOutput.outputException(e1);
                        }
                        editQuestionWindow.clearandresetall();
                        editAnnouncementWindow.reset();
                        recordtextarea.append(LocalTime.now().toString() + "：内容已更新至主屏幕");
                    }
                }
            }
        });
        closescreenmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordtextarea.append(LocalTime.now().toString() + "：控制台关闭了大屏幕\r\r\n\n");
                screennowpanel.setBackground(new Color(111, 111, 111));
                openscreenmenuitem.setEnabled(true);
                closescreenmenuitem.setEnabled(false);
                screencontrol(2);
            }
        });

        editAnnouncementWindow = new EditAnnouncementWindow();
        editAnnouncementWindow.RefreshColor();
        editAnnouncementWindow.resetinfotype();

        editQuestionWindow = new EditQuestionWindow();

        reloadcolors();

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            EventOutput.outputException(e);
        }
    }

    public void twitclick() {
        ScreenWindow.twiterlabel.setText(twitertextfield.getText());
        recordtextarea.append(LocalTime.now() + "：控制台推送了消息[" + twitertextfield.getText() + "]\r\r\n\n");
        twitertextfield.setText("");
        ScreenWindow.infoactionlabel.setText("有新的推送消息");
        ScreenWindow.colorpanel.setBackground(new Color(123, 123, 123));
        ScreenWindow.questionaction(3);
        class waitthr extends Thread {
            @Override
            public void run() {
                twitbutton.setEnabled(false);
                twitertextfield.setEnabled(false);
                try {
                    for (int tempint = 3; tempint >= 0; tempint--) {
                        twitertextfield.setText("防刷屏 " + String.valueOf(tempint) + " 秒");
                        Thread.sleep(1000);
                    }
                } catch (Exception e1) {
                    EventOutput.outputException(e1);
                }
                twitertextfield.setText("");
                twitertextfield.setEnabled(true);
                twitbutton.setEnabled(true);
            }
        }
        waitthr thrs = new waitthr();
        thrs.start();
    }

    private void screencontrol(int action) {
        if (action == 0) /*open screen*/ {
            screen.border.setBackground(new Color(0, 36, 78));
            try {
                screen.tocolor = respack.getcolor("screenbuckcolor");
                screen.screenwindow.setVisible(true);
                screen.changeusedtimes = 50;
                screen.singlechangetime = 5;
                screen.colorchanger.start();
            } catch (Exception e) {
                EventOutput.outputException(e);
                screen.border.setBackground(respack.getcolor("screenbuckcolor"));
            }
        }
        if (action == 1)/*refresh the display*/ {
            ScreenWindow.refresh();
        }
        if (action == 2)/*close screen*/ {
            screen.screenwindow.setVisible(false);
        }
        /*
        if (action == 3) {
            //reset the screen
        }
        */
    }

    public void reloadcolors() {
        consolewind.setBackground(respack.getcolor("backgroundcolor"));
        consolewind.setBackground(respack.getcolor("backgroundcolor"));
        recordtextarea.setBackground(respack.getcolor("backgroundcolor"));
        subframe_left.setBackground(respack.getcolor("backgroundcolor"));
        subframe_right.setBackground(respack.getcolor("backgroundcolor"));
        border.setBackground(respack.getcolor("backgroundcolor"));
        border.setBorder(BorderFactory.createLineBorder(respack.getcolor("backgroundcolor"), 20));
        MFLauncher.orderLists.listlist.setBackground(respack.getcolor("backgroundcolor"));
        MFLauncher.orderLists.orderlist.setBackground(respack.getcolor("backgroundcolor"));
        screentwiterpanel.setBackground(respack.getcolor("backgroundcolor"));
        screenactionpanel.setBackground(respack.getcolor("backgroundcolor"));
        twitertextfield.setBackground(respack.getcolor("backgroundcolor"));
        screenviewpanel.setBackground(respack.getcolor("backgroundcolor"));
        screennexttextarea.setBackground(respack.getcolor("backgroundcolor"));
        screennexttextscrollpane.setBackground(respack.getcolor("backgroundcolor"));
        noticeanderrortextarea.setBackground(respack.getcolor("backgroundcolor"));
        clockcontrolpanel.setBackground(respack.getcolor("backgroundcolor"));
        clocksingletextfield.setBackground(respack.getcolor("backgroundcolor"));
        clocktotaltextfield.setBackground(respack.getcolor("backgroundcolor"));
        clockfieldpanel.setBackground(respack.getcolor("backgroundcolor"));
        clockpanel.setBackground(respack.getcolor("backgroundcolor"));
        listpanel.setBackground(respack.getcolor("backgroundcolor"));
        editWindShowAndTwitButtonPanel.setBackground(respack.getcolor("backgroundcolor"));
        leftdownPanel.setBackground(respack.getcolor("backgroundcolor"));
        editButtonPanel.setBackground(respack.getcolor("backgroundcolor"));
        speakerPanel.ReColor();
    }

    public void setFullScreen(boolean flag) {
        EventOutput.outputEvent("Set_full_screen on Console_Wind");
        this.consolewind.setUndecorated(flag);
        this.consolewind.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public static void ExitSystem() {
        try {
            Runtime.getRuntime().exec("taskkill -f -im mshta.exe");
        } catch (Exception e) {
            EventOutput.outputException(e);
        }
        EventOutput.outputEvent(VersionView.getNowVersion() + "_stop");
        System.exit(0);
    }
}
