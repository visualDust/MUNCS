package MockFormula.lib.UI.Console;

import MockFormula.lib.DataStructure.Resource;
import MockFormula.lib.Method.EventOutput;
import MockFormula.lib.Method.Speaker;
import MockFormula.lib.Method.TempCreater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class SpeakerPanel extends JPanel{
    public Vector<String> sentenceVector = new Vector<>();
    public JList sentenceList = new JList();
    public JScrollPane sentenceListScrollPane = new JScrollPane(sentenceList);

    public JPanel buttonPanel = new JPanel();
    public JButton speakSelectedButton = new JButton("朗读选中项");
    public JButton speakSettingButton = new JButton("朗读选项");
    public JButton stopAllSpeechButton = new JButton("紧停所有朗读");

    public Resource respack = new Resource();

    public SpeakerPanel(){
        this.setLayout(TempCreater.tempgridlayout(1,2,10,10));
        buttonPanel.setLayout(TempCreater.tempgridlayout(4,1,5,5));
        buttonPanel.add(speakSelectedButton);
        buttonPanel.add(speakSettingButton);
        speakSettingButton.setEnabled(false);
        buttonPanel.add(TempCreater.templabel("",null,null,null));
        stopAllSpeechButton.setForeground(new Color(255,0,0));
        buttonPanel.add(stopAllSpeechButton);

        speakSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sentenceList.getSelectedIndex()!=-1){
                    Speaker.speak(sentenceVector.elementAt(sentenceList.getSelectedIndex()));
                }
            }
        });

        speakSettingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }); //TODO finish it

        stopAllSpeechButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Speaker.StopAllSpeeches();
            }
        });

        this.add(sentenceListScrollPane);
        this.add(buttonPanel);



        JPopupMenu listPopMenu = new JPopupMenu();
        JMenuItem addNewSentenceMenuItem = new JMenuItem("新建朗读");
        addNewSentenceMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sentence =MessageWindow.showMessageWindow(1,"新建朗读...");
                if(!sentence.equals("")){
                    sentenceVector.add(0,sentence);
                    sentenceList.setListData(sentenceVector);
                    sentenceList.setSelectedIndex(0);
                }
            }
        });
        //JMenuItem upMoveSelectedMenuItem = new JMenuItem("上移选中项");
        //JMenuItem downMoveSelectedMenuItem = new JMenuItem("下移选中项");
        JMenuItem editSelectedMenuItem = new JMenuItem("编辑选中项");
        editSelectedMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sentenceList.getSelectedIndex()!=-1){
                    String sentence =MessageWindow.showMessageWindow(1,"编辑...");
                    int index = sentenceList.getSelectedIndex();
                    if(!sentence.equals("")) {
                        sentenceVector.setElementAt(sentence,index);
                        sentenceList.setListData(sentenceVector);
                        sentenceList.setSelectedIndex(index);
                    }
                }
            }
        });
        JMenuItem deletSelectedMenuItem = new JMenuItem("删除选中项");
        deletSelectedMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sentenceList.getSelectedIndex()!=-1){
                    sentenceVector.removeElementAt(sentenceList.getSelectedIndex());
                    sentenceList.setListData(sentenceVector);
                }
            }
        });
        listPopMenu.add(addNewSentenceMenuItem);
        //listPopMenu.add(upMoveSelectedMenuItem);
        //listPopMenu.add(downMoveSelectedMenuItem);
        listPopMenu.add(editSelectedMenuItem);
        listPopMenu.add(deletSelectedMenuItem);
        listPopMenu.add("取消");

        sentenceList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.isPopupTrigger()){
                    listPopMenu.show(sentenceList,e.getX(),e.getY());
                    EventOutput.outputEvent("PopupTrigger at SpeakerList");
                }
            }
        });

        LoadDefault();
        ReColor();
    }

    public void ReColor(){
        this.setBackground(respack.getcolor("backgroundcolor"));
        buttonPanel.setBackground(respack.getcolor("backgroundcolor"));
        sentenceListScrollPane.setBackground(respack.getcolor("backgroundcolor"));
        sentenceList.setBackground(respack.getcolor("backgroundcolor"));
        sentenceList.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
    }

    public void LoadDefault(){
        sentenceVector.removeAllElements();
        sentenceVector.add("请支持此动议的代表团高举代表牌");
        sentenceVector.add("请想要加入此动议的代表团高举代表牌");
        sentenceVector.add("会议即将开始，请保持安静");
        sentenceList.setListData(sentenceVector);
    }
}
