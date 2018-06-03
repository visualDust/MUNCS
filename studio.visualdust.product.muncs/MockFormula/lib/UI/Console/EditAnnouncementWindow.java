package MockFormula.lib.UI.Console;

import MockFormula.Database.DataResource;
import MockFormula.lib.DataStructure.Resource;
import MockFormula.lib.MFLauncher;
import MockFormula.lib.Method.EventOutput;
import MockFormula.lib.Method.TempCreater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class EditAnnouncementWindow extends JFrame {
    Resource respack = new Resource();
    public JPanel border = new JPanel();
    public JPanel dividerpanel = new JPanel();
    public JPanel infoconsolepanel = new JPanel();
    public JPanel infofillerpanel = new JPanel();
    public JButton infofilleryesbutton = new JButton("确认");
    public JTextField announcernametextfield = new JTextField();
    public JTextField about = new JTextField();
    public JComboBox order = new JComboBox();
    public JComboBox infotype = new JComboBox();
    Font labelfont = respack.getFont("defaultlabelfont");
    public JSpinner totaltimefield = new JSpinner();//number only
    public JSpinner singletimefield = new JSpinner();//number only

    public EditAnnouncementWindow() {
        border.setLayout(TempCreater.tempgridlayout(3, 1, 10, 10));
        this.setLayout(TempCreater.tempgridlayout(1, 1, 0, 0));
        this.setSize(600, 400);
        this.add(border);
        JButton infofillerclearbutton = new JButton("重置以上内容");

        infoconsolepanel.setLayout(TempCreater.tempgridlayout(2, 3, 10, 10));
        infoconsolepanel.setBackground(respack.getcolor("backgroundcolor"));
        infoconsolepanel.add(order);
        infoconsolepanel.add(infofillerclearbutton);
        infoconsolepanel.add(infofilleryesbutton);

        infoconsolepanel.add(TempCreater.templabel("", labelfont, respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        infoconsolepanel.add(TempCreater.templabel("", labelfont, respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        infofillerpanel.setBackground(respack.getcolor("backgroundcolor"));
        infofillerpanel.setLayout(TempCreater.tempgridlayout(3, 2, 5, 5));

        infofillerpanel.add(TempCreater.templabel(" 提出方名称", labelfont, respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        infofillerpanel.add(announcernametextfield);
        infofillerpanel.add(TempCreater.templabel(" 总时长", labelfont, respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        infofillerpanel.add(totaltimefield);
        infofillerpanel.add(TempCreater.templabel(" 分时长", labelfont, respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        infofillerpanel.add(singletimefield);
        border.add(dividerpanel);
        border.add(infofillerpanel);
        dividerpanel.setBackground(respack.getcolor("backgroundcolor"));
        dividerpanel.setLayout(TempCreater.tempgridlayout(2, 2, 5, 5));
        JLabel discribe_1 = new JLabel(" 进行新的动议，内容为：");
        discribe_1.setFont(new Font("微软雅黑", 0, 20));
        discribe_1.setForeground(respack.getcolor("titlefontcolor"));
        dividerpanel.add(discribe_1);
        dividerpanel.add(TempCreater.templabel("", respack.getFont("defaulttextfont"), respack.getcolor("backgroundcolor"), respack.getcolor("titlefontcolor")));
        dividerpanel.add(about);
        dividerpanel.add(infotype);

        border.add(infoconsolepanel);
        infofillerclearbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("Info_filler_clear_Button clicked");
                if (MessageWindow.showMessageWindow(2, "确定要清除内容吗？").equals("0")) {
                    if (!about.getText().isEmpty() && !about.isEnabled()) {
                        MFLauncher.console.recordtextarea.append(LocalTime.now().toString() + "：上一条动议在被更新到屏幕之前被清除\r\r\n\n");
                    }
                    infotype.setSelectedIndex(0);
                    about.setText("");
                    announcernametextfield.setText("");
                    totaltimefield.setValue(0);
                    singletimefield.setValue(0);
                    infofilleryesbutton.setEnabled(true);
                    about.setEnabled(true);
                    totaltimefield.setEnabled(true);
                    announcernametextfield.setEnabled(true);
                    singletimefield.setEnabled(true);
                    infotype.setEnabled(true);
                    order.setEnabled(true);
                    RefreshColor();
                }
            }
        });
        infofilleryesbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EventOutput.outputEvent("Info_filler_yes_Button clicked");
                    if (!about.getText().isEmpty() && !announcernametextfield.getText().isEmpty() && Integer.valueOf(String.valueOf(totaltimefield.getValue())) >= Integer.valueOf(String.valueOf(singletimefield.getValue())) && Integer.valueOf(String.valueOf(totaltimefield.getValue())) <= 9999) {
                        topicprinter();
                        infofilleryesbutton.setEnabled(false);
                        about.setEnabled(false);
                        announcernametextfield.setEnabled(false);
                        totaltimefield.setEnabled(false);
                        singletimefield.setEnabled(false);
                        infotype.setEnabled(false);
                        order.setEnabled(false);
                        RefreshColor();
                        MFLauncher.console.screennexttextarea.append("待刷新" + infotype.getItemAt(infotype.getSelectedIndex()).toString() + "\n");
                        TempCreater.tempnoticePopmenu("已提交，刷新屏幕将内容更新到主屏", 10000).show(MFLauncher.console.subframe_left, infofillerpanel.getX() + 50, infofillerpanel.getY() + infofillerpanel.getHeight());
                        setVisible(false);
                    } else {
                        if (about.getText().isEmpty()) {
                            about.setBackground(respack.getcolor("warningbuckcolor"));
                            about.setForeground(respack.getcolor("warningforecolor"));
                        }
                        if (announcernametextfield.getText().isEmpty()) {
                            announcernametextfield.setBackground(respack.getcolor("warningbuckcolor"));
                            announcernametextfield.setForeground(respack.getcolor("warningforecolor"));
                        }
                        if (Integer.valueOf(String.valueOf(totaltimefield.getValue())) < Integer.valueOf(String.valueOf(singletimefield.getValue()))) {
                            totaltimefield.setBackground(respack.getcolor("warningbuckcolor"));
                            totaltimefield.setForeground(respack.getcolor("warningforecolor"));
                            singletimefield.setBackground(respack.getcolor("warningbuckcolor"));
                            singletimefield.setForeground(respack.getcolor("warningforecolor"));
                            TempCreater.tempnoticePopmenu("分时长大于总时长", 5000).show(border, infofillerpanel.getX() + infofillerpanel.getWidth() - 50, infofillerpanel.getY() + infofillerpanel.getHeight());
                        }
                        if (Integer.valueOf(String.valueOf(totaltimefield.getValue())) > 9999) {
                            totaltimefield.setBackground(respack.getcolor("warningbuckcolor"));
                            TempCreater.tempnoticePopmenu("时间太长了!", 5000).show(border, infofillerpanel.getX() + infofillerpanel.getWidth() - 50, infofillerpanel.getY() + infofillerpanel.getHeight());
                        }
                    }
                } catch (Exception e2) {
                    EventOutput.outputException(e2);
                }
            }
        });
        this.setResizable(false);
        this.setTitle("添加动议...");
        try {
            this.setIconImage(new ImageIcon(DataResource.class.getResource("ICONIMG.png").toURI().toURL()).getImage());
        } catch (Exception e) {
            EventOutput.outputException(e);
        }
        ReColor();
    }

    public void reset() {
        totaltimefield.setEnabled(true);
        singletimefield.setEnabled(true);
        totaltimefield.setValue(0);
        singletimefield.setValue(0);
        infofilleryesbutton.setEnabled(true);
        about.setEnabled(true);
        announcernametextfield.setEnabled(true);
        infotype.setEnabled(true);
        order.setEnabled(true);
        about.setText("");
        announcernametextfield.setText("");
    }

    public void resetinfotype() {
        infotype.addItem("有主持核心磋商");
        infotype.addItem("自由磋商");
        infotype.addItem("发言名单操作");
        infotype.addItem("有主题辩论");
        infotype.addItem("自由辩论");
        infotype.addItem("休会");
        order.addItem("选择申请方发言顺序");
        order.addItem("提出方申请首位发言");
        order.addItem("提出方申请末位发言");
    }

    public void RefreshColor() {
        totaltimefield.setForeground(respack.getcolor("textfielddefaultforecolor"));
        singletimefield.setForeground(respack.getcolor("textfielddefaultforecolor"));
        totaltimefield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
        singletimefield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
        totaltimefield.setFont(respack.getFont("defaulttextfont;"));
        singletimefield.setFont(respack.getFont("defaulttextfont;"));
        about.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
        announcernametextfield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
        about.setFont(respack.getFont("defaulttextfont;"));
        announcernametextfield.setFont(respack.getFont("defaulttextfont;"));
        about.setForeground(respack.getcolor("textfielddefaultforecolor"));
        announcernametextfield.setForeground(respack.getcolor("textfielddefaultforecolor"));
    }

    private void topicprinter() {
        try {
            MFLauncher.console.recordtextarea.append(LocalTime.now().toString() + "：" + announcernametextfield.getText() + " 提出了关于 " + about.getText() + " 的" + infotype.getItemAt(infotype.getSelectedIndex()) + "，");
            if (Integer.valueOf(String.valueOf(totaltimefield.getValue())) != 0)
                MFLauncher.console.recordtextarea.append("以上议题总时长：" + Integer.valueOf(String.valueOf(totaltimefield.getValue())));
            if (Integer.valueOf(String.valueOf(totaltimefield.getValue())) != 0 && Integer.valueOf(String.valueOf(singletimefield.getValue())) != 0)
                MFLauncher.console.recordtextarea.append("   分时长：" + Integer.valueOf(String.valueOf(singletimefield.getValue())) + "，共可容纳" + Integer.valueOf(Integer.valueOf(String.valueOf(totaltimefield.getValue()))) / Integer.valueOf(String.valueOf(singletimefield.getValue())) + "名代表发言");
            if (order.getSelectedIndex() != 0)
                MFLauncher.console.recordtextarea.append(" ， " + order.getItemAt(order.getSelectedIndex()));
            MFLauncher.console.recordtextarea.append("\r\r\n\n");
        } catch (Exception e) {
            EventOutput.outputException(e);
        }
    }

    public void ReColor() {
        this.setBackground(respack.getcolor("backgroundcolor"));
        border.setBackground(respack.getcolor("backgroundcolor"));
        border.setBorder(BorderFactory.createLineBorder(respack.getcolor("backgroundcolor"), 20));
        infoconsolepanel.setBackground(respack.getcolor("backgroundcolor"));
        dividerpanel.setBackground(respack.getcolor("backgroundcolor"));
        infofillerpanel.setBackground(respack.getcolor("backgroundcolor"));
    }

    public void showup() {
        ReColor();
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }
}
