package MockFormula.lib.UI.Console;

import MockFormula.Database.DataResource;
import MockFormula.lib.DataStructure.Resource;
import MockFormula.lib.MFLauncher;
import MockFormula.lib.Method.EventOutput;
import MockFormula.lib.Method.TempCreater;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class EditQuestionWindow extends JFrame {
    public JPanel border = new JPanel();
    public JPanel questionpanel = new JPanel();
    public JTextField questionfield = new JTextField();
    public JButton questionyesbutton = new JButton("确认");
    public JComboBox questiontype = new JComboBox();

    Resource respack = new Resource();

    public EditQuestionWindow() {
        this.setLayout(TempCreater.tempgridlayout(1,1,0,0));
        questionpanel.setBackground(respack.getcolor("backgroundcolor"));
        questionpanel.setLayout(TempCreater.tempgridlayout(2, 3, 5, 5));
        questionpanel.add(questionfield);
        questionpanel.add(questiontype);
        JButton questionclearbutton = new JButton("重置以上内容");
        questionpanel.add(questionclearbutton);
        questionpanel.add(questionyesbutton);
        border.setLayout(TempCreater.tempgridlayout(1,1,0,0));
        border.add(questionpanel);
        this.add(border);
        this.setResizable(false);
        this.setSize(500,160);
        questionyesbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("question_yes_Button clicked");
                if (!questionfield.getText().isEmpty()) {
                    questionfield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
                    MFLauncher.console.recordtextarea.append(LocalTime.now().toString() + "：" + questionfield.getText() + " 提出 " + questiontype.getItemAt(questiontype.getSelectedIndex()) + "\r\r\n\n");
                    questionfield.setEnabled(false);
                    questionyesbutton.setEnabled(false);
                    questiontype.setEnabled(false);
                    MFLauncher.console.screennexttextarea.append("待刷新" + questiontype.getItemAt(questiontype.getSelectedIndex()).toString() + "\n");
                    TempCreater.tempnoticePopmenu("已提交，刷新屏幕将内容更新到主屏", 10000).show(border, questionyesbutton.getX() + 50, questionyesbutton.getY() + questionyesbutton.getHeight());
                    setVisible(false);
                } else {
                    questionfield.setBackground(respack.getcolor("warningbuckcolor"));
                }
            }
        });
        questionclearbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("question_clear_Button clicked");
                if (MessageWindow.showMessageWindow(2, "确认要清除内容吗？").equals("0")) {
                    if (!questionfield.getText().isEmpty() && !questionfield.isEnabled()) {
                        MFLauncher.console.recordtextarea.append(LocalTime.now().toString() + "：上一条问题在更新到屏幕之前被清除\r\r\n\n");
                    }
                    questionfield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
                    questionfield.setText("");
                    questionfield.setEnabled(true);
                    questionyesbutton.setEnabled(true);
                    questiontype.setEnabled(true);
                }
            }
        });
        RefreshFillerColor();
        resetquestiontype();
        this.setTitle("新建问题...");
        try {
            this.setIconImage(new ImageIcon(DataResource.class.getResource("ICONIMG.png").toURI().toURL()).getImage());
        } catch (Exception e) {
            EventOutput.outputException(e);
        }
        ReColor();
        border.add(questionpanel);
    }

    public void ReColor() {
        this.setBackground(respack.getcolor("backgroundcolor"));
        questionpanel.setBackground(respack.getcolor("backgroundcolor"));
        border.setBackground(respack.getcolor("backgroundcolor"));
        border.setBorder(BorderFactory.createLineBorder(respack.getcolor("backgroundcolor"), 20));
    }

    private void RefreshFillerColor() {
        questionfield.setFont(respack.getFont("defaulttextfont;"));
        questionfield.setBackground(respack.getcolor("textfielddefaultbuckcolor"));
        questionfield.setForeground(respack.getcolor("textfielddefaultforecolor"));
    }

    public void clearandresetall() {
        MFLauncher.console.screennexttextarea.setText("");
        questiontype.setEnabled(true);
        questionyesbutton.setEnabled(true);
        questionfield.setEnabled(true);
        questiontype.setSelectedIndex(0);
        questionfield.setText("");
    }

    private void resetquestiontype() {
        questiontype.addItem("程序性问题");
        questiontype.addItem("资讯性问题");
        questiontype.addItem("个人特权问题");
        questiontype.addItem("使用答辩权");
        questiontype.addItem("使用质询权");
    }

    public void ShowUp() {
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        ReColor();
    }
}