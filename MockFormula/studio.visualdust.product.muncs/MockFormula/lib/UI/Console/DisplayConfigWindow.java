package MockFormula.lib.UI.Console;

import MockFormula.Database.DataResource;
import MockFormula.lib.DataStructure.Resource;
import MockFormula.lib.DataStructure.VersionView;
import MockFormula.lib.MFLauncher;
import MockFormula.lib.Method.*;
import MockFormula.lib.UI.Display.ScreenWindow;
import com.alee.laf.WebLookAndFeel;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalTime;

public class DisplayConfigWindow {
    public static JFrame displayConfigFrame = new JFrame();
    public static Resource respack = new Resource();
    public static JPanel border = new JPanel();
    private JColorChooser colorchoosewind = new JColorChooser();
    public static JDialog colorChooseDialog;
    public static Color configbackgroundcolor = new Color(222, 222, 222);
    public static Color configfontcolor = new Color(66, 66, 66);
    public static VersionView versionView = new VersionView();

    public DisplayConfigWindow() {
        displayConfigFrame.setMinimumSize(new Dimension(600, 400));
        displayConfigFrame.setBackground(configbackgroundcolor);
        displayConfigFrame.setResizable(false);
        displayConfigFrame.setLayout(TempCreater.tempgridlayout(1, 1, 10, 10));
        displayConfigFrame.setTitle("显示设置 ( Display Config )");

        try {
            displayConfigFrame.setIconImage(new ImageIcon(DataResource.class.getResource("ICONIMG.png").toURI().toURL()).getImage());
        } catch (MalformedURLException | URISyntaxException e) {
            MFLauncher.console.noticeanderrortextarea.append(LocalTime.now().toString() + "：" + e.toString() + "\n");
            e.printStackTrace();
        }
        displayConfigFrame.setLocationRelativeTo(null);

        displayConfigFrame.add(border);
        border.setLayout(TempCreater.tempgridlayout(6, 1, 10, 10));
        border.setBorder(BorderFactory.createLineBorder(configbackgroundcolor, 20));

        JPanel lookandfeelconfigpanel = new JPanel();
        lookandfeelconfigpanel.setLayout(TempCreater.tempgridlayout(1, 3, 5, 5));
        lookandfeelconfigpanel.setBackground(configbackgroundcolor);
        JComboBox lookandfeelcombobox = new JComboBox();
        lookandfeelcombobox.addItem("Web laf风格");
        lookandfeelcombobox.addItem("Nimbus laf风格");
        lookandfeelcombobox.addItem("Windows laf风格");
        lookandfeelcombobox.addItem("Metal laf风格");
        lookandfeelcombobox.setEnabled(false);
        lookandfeelconfigpanel.add(TempCreater.templabel("界面风格:", respack.getFont("defaultlabelfont"), configbackgroundcolor, configfontcolor));
        lookandfeelconfigpanel.add(lookandfeelcombobox);
        lookandfeelconfigpanel.add(TempCreater.templabel("目前该功能还在调试", respack.getFont("defaultlabelfont"), configbackgroundcolor, configfontcolor));
        border.add(lookandfeelconfigpanel);

        JPanel windowcolorconfigpanel = new JPanel();
        windowcolorconfigpanel.setBackground(configbackgroundcolor);
        border.setBackground(configbackgroundcolor);
        windowcolorconfigpanel.setLayout(TempCreater.tempgridlayout(1, 3, 5, 5));
        windowcolorconfigpanel.add(TempCreater.templabel("窗口颜色:", respack.getFont("defaultlabelfont"), configbackgroundcolor, configfontcolor));
        JButton changeconsolebackcolorbutton = new JButton("更改控制台背景颜色");
        JButton changedisplaybackcolorbutton = new JButton("更改主屏窗口背景颜色");
        windowcolorconfigpanel.add(changeconsolebackcolorbutton);
        windowcolorconfigpanel.add(changedisplaybackcolorbutton);
        border.add(windowcolorconfigpanel);
        changeconsolebackcolorbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorChooseDialog = JColorChooser.createDialog(border, "选择一个颜色", false, colorchoosewind, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Resource.backgroundcolor = colorchoosewind.getColor();
                        MFLauncher.console.reloadcolors();
                    }
                }, null);
                colorChooseDialog.setVisible(true);
            }
        });
        changedisplaybackcolorbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorChooseDialog = JColorChooser.createDialog(border, "选择一个颜色", false, colorchoosewind, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Resource.screenbuckcolor = colorchoosewind.getColor();
                        ScreenWindow.reloadcolors();
                    }
                }, null);
                colorChooseDialog.setVisible(true);
            }
        });

        JPanel previewScreenPanel = new JPanel();
        previewScreenPanel.setLayout(TempCreater.tempgridlayout(1, 3, 5, 5));
        previewScreenPanel.setBackground(configbackgroundcolor);
        previewScreenPanel.add(TempCreater.templabel("主屏预览刷新间隔时间：", respack.getFont("defaultlabelfont"), configbackgroundcolor, configfontcolor));
        previewScreenPanel.add(TempCreater.templabel("单位毫秒，过小会导致卡顿", respack.getFont("defaultlabelfont"), configbackgroundcolor, configfontcolor));
        JSpinner changeViewSpeedSpinner = new JSpinner();
        changeViewSpeedSpinner.setValue(ScreenWindow.shotScreenTime);
        previewScreenPanel.add(changeViewSpeedSpinner);
        border.add(previewScreenPanel);

        JSpinner displayspeedspinner = new JSpinner();
        displayspeedspinner.setValue(ScreenWindow.displaydelaytime);
        JSpinner displaystaytimespinner = new JSpinner();
        displaystaytimespinner.setValue(ScreenWindow.noticeappeartime);
        JPanel displayspeedpanel = new JPanel();
        displayspeedpanel.setBackground(configbackgroundcolor);
        displayspeedpanel.setLayout(TempCreater.tempgridlayout(1, 4, 5, 5));
        displayspeedpanel.add(TempCreater.templabel("动画速度(越高越慢):", new Font("微软雅黑", 0, 15), Color.white, configfontcolor));
        displayspeedpanel.add(displayspeedspinner);
        displayspeedpanel.add(TempCreater.templabel("停留时间(越高越长):", new Font("微软雅黑", 0, 15), Color.white, configfontcolor));
        displayspeedpanel.add(displaystaytimespinner);
        border.add(displayspeedpanel);

        JPanel aboutanderrorpanel = new JPanel();
        aboutanderrorpanel.setLayout(TempCreater.tempgridlayout(1, 4, 5, 5));
        JButton specialbutton = new JButton("版本特性及后续版本");
        JDialog versiondialog = new JDialog();
        try {
            versiondialog.setIconImage(new ImageIcon(DataResource.class.getResource("ICONIMG.png").toURI().toURL()).getImage());
        } catch (MalformedURLException | URISyntaxException e) {
            MFLauncher.console.noticeanderrortextarea.append(LocalTime.now().toString() + "：" + e.toString() + "\n");
            e.printStackTrace();
        }
        versiondialog.setMinimumSize(new Dimension(800, 500));
        versiondialog.setTitle("本软件的版本(们)");
        versiondialog.setBackground(configbackgroundcolor);
        versiondialog.setLocationRelativeTo(null);
        versiondialog.setLayout(TempCreater.tempgridlayout(1, 1, 0, 0));
        JTextArea versiontextarea = new JTextArea();
        versiontextarea.setBackground(configbackgroundcolor);
        versiontextarea.setForeground(configfontcolor);
        versiontextarea.setLineWrap(true);
        versiontextarea.setFont(new Font("微软雅黑", 0, 15));
        versiontextarea.append(versionView.getVersions());
        versiontextarea.setEditable(false);
        JScrollPane versionscrollpane = new JScrollPane(versiontextarea);
        versionscrollpane.setBackground(configbackgroundcolor);
        versiondialog.add(versionscrollpane);
        specialbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                versiondialog.setVisible(true);
            }
        });
        JButton aboutbutton = new JButton("关于...");
        aboutbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageWindow.showMessageWindow(0, versionView.getAbout());
            }
        });
        JButton errorbutton = new JButton("错误和问题");
        errorbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageWindow.showMessageWindow(0, versionView.getAbouterror());
            }
        });
        aboutanderrorpanel.add(specialbutton);
        aboutanderrorpanel.add(aboutbutton);
        aboutanderrorpanel.add(errorbutton);
        aboutanderrorpanel.setBackground(configbackgroundcolor);
        border.add(aboutanderrorpanel);

        JButton closebutton = new JButton("返回");
        border.add(closebutton);
        closebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ScreenWindow.shotScreenTime = (int) (changeViewSpeedSpinner.getValue());
                    ScreenWindow.displaydelaytime = Integer.valueOf(String.valueOf(displayspeedspinner.getValue()));
                    ScreenWindow.noticeappeartime = Integer.valueOf(String.valueOf(displaystaytimespinner.getValue()));
                } catch (Exception e1) {
                    EventOutput.outputException(e1);
                }
                if (!(lookandfeelcombobox.getSelectedIndex() == 0 && UIManager.getLookAndFeel().getName().equals("WebLookAndFeel"))) {
                    switch (lookandfeelcombobox.getSelectedIndex()) {
                        case 0:
                            Resource.defaultlookandfeel = new WebLookAndFeel();
                            break;
                        case 1:
                            Resource.defaultlookandfeel = new NimbusLookAndFeel();
                            break;
                        case 2:
                            //Resource.defaultlookandfeel = new WindowsLookAndFeel();
                            break;
                        case 3:
                            Resource.defaultlookandfeel = new MetalLookAndFeel();
                            break;
                    }
                    Resource.restdefaultfont();
                    try {
                        UIManager.setLookAndFeel(respack.getDefaultlookandfeel());
                        SwingUtilities.updateComponentTreeUI(MFLauncher.console.consolewind.getContentPane());
                        SwingUtilities.updateComponentTreeUI(displayConfigFrame.getContentPane());
                    } catch (Exception e1) {
                        EventOutput.outputException(e1);
                    }
                }
                setvis(false);
            }
        });
        displayConfigFrame.setVisible(false);
    }

    public void setvis(boolean vis) {
        if (vis) displayConfigFrame.setVisible(true);
        else {
            displayConfigFrame.setVisible(false);
        }
    }
}
