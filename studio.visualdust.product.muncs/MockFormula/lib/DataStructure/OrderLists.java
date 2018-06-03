package MockFormula.lib.DataStructure;

import MockFormula.lib.Method.EventOutput;
import MockFormula.lib.MFLauncher;
import MockFormula.lib.Method.TempCreater;
import MockFormula.lib.UI.Console.MessageWindow;
import MockFormula.lib.UI.Display.ScreenWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.Vector;

public class OrderLists {
    public Resource respack = new Resource();
    JDialog ordereditdialog = new JDialog();

    public class list {

        public String name = "";
        public int lastselectedindex = -1;

        public class Order {
            public String name = "";
            public Vector<String> announcements = new Vector();
            public Vector<String> questions = new Vector();
        }

        public Vector<Order> orderVector = new Vector<>();

        public void setName(String newname) {
            name = newname;
        }

        public String getName() {
            return name;
        }

        public void setLastselectedindex(int lastindex) {
            lastselectedindex = lastindex;
        }

        public int getLastselectedindex() {
            return lastselectedindex;
        }

        public void addorder(String _name) {
            Order temporder = new Order();
            temporder.name = _name;
            orderVector.add(temporder);
        }

        public void deleteorderatindex(int orderindex) {
            orderVector.remove(orderindex);
        }

        public void setnameatindex(int orderindex, String newname) {
            orderVector.elementAt(orderindex).name = newname;
        }

        public String getnameatindex(int orderindex) {
            return orderVector.elementAt(orderindex).name;
        }

        public void addannouncementatindex(int orderindex, String announcement) {
            orderVector.elementAt(orderindex).announcements.add(announcement);
        }

        public String getannouncementatindex(int orderindex, int announcementnum) {
            return orderVector.elementAt(orderindex).announcements.elementAt(announcementnum);
        }

        public void addquestionatindex(int orderindex, String question) {
            orderVector.elementAt(orderindex).questions.add(question);
        }

        public String getquestionatindex(int orderindex, int questionnum) {
            return orderVector.elementAt(orderindex).questions.elementAt(questionnum);
        }

        public int getannouncementamountatindex(int orderindex) {
            return orderVector.elementAt(orderindex).announcements.size();
        }

        public int getquestionamountatindex(int orderindex) {
            return orderVector.elementAt(orderindex).questions.size();
        }
    }

    public Vector<list> listVector = new Vector<>();

    public void addlist(String name) {
        list templist = new list();
        templist.setName(name);
        listVector.add(templist);
    }

    public void deletelistatindex(int listindex) {
        listVector.remove(listindex);
    }

    public void setlistnameatindex(int listindex, String name) {
        listVector.elementAt(listindex).name = name;
    }

    public String getlistnameatindex(int listindex) {
        return listVector.elementAt(listindex).name;
    }

    public JList listlist = new JList();
    public JList orderlist = new JList();
    public JScrollPane listlistscrollpane = new JScrollPane(listlist);
    public JScrollPane orderlistscrollpane = new JScrollPane(orderlist);

    public OrderLists() {
        list defaultlist = new list();
        defaultlist.setName("所有国家和地区");
        listVector.add(defaultlist);
        refreshorderlist(3);// number "3" is default (refresh all)
        JPopupMenu listpopmenu = new JPopupMenu();
        JMenuItem addlistpopmenuitem = new JMenuItem("新建发言列表");
        addlistpopmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempstr = MessageWindow.showMessageWindow(1, "请输入一个名称");
                if (!tempstr.equals("")) {
                    addlist(tempstr);
                    refreshorderlist(1);
                }
            }
        });
        JMenuItem renamelistpopmenuitem = new JMenuItem("重命名选中列表");
        renamelistpopmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listlist.getSelectedIndex() != -1) {
                    String tempstr = MessageWindow.showMessageWindow(1, "输入一个新的名字");
                    if (!tempstr.equals("") && !tempstr.equals(null)) {
                        setlistnameatindex(listlist.getSelectedIndex(), tempstr);
                        refreshorderlist(1);
                    }
                }
            }
        });
        JMenuItem printlistmenuitem = new JMenuItem("将选中列表显示到主屏");
        printlistmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listlist.getSelectedIndex() != -1 && MessageWindow.showMessageWindow(2, "确定将列表\"" + listVector.elementAt(listlist.getSelectedIndex()).getName() + "\"显示在主屏窗口？").equals("0")) {
                    ScreenWindow.listprinttextarea.setText("");
                    MFLauncher.console.recordtextarea.append(LocalTime.now().toString() + "当前发言列表：" + listVector.elementAt(listlist.getSelectedIndex()).getName() + "，共有" + String.valueOf(listVector.elementAt(listlist.getSelectedIndex()).orderVector.size() - 1) + "名发言者\r\r\n\n");
                    for (int tempint = 0; tempint < listVector.elementAt(listlist.getSelectedIndex()).orderVector.size(); tempint++) {
                        ScreenWindow.listprinttextarea.append(listVector.elementAt(listlist.getSelectedIndex()).getnameatindex(tempint) + "\n");
                    }
                }
                ScreenWindow.listprintscrollpane.getVerticalScrollBar().setValue(ScreenWindow.listprintscrollpane.getVerticalScrollBar().getMinimum());
            }
        });
        JMenuItem deletelistpopmenuitem = new JMenuItem("删除选中列表");
        deletelistpopmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listlist.getSelectedIndex() != -1) {
                    if (MessageWindow.showMessageWindow(2, "确认要删除？").equals("0")) {
                        deletelistatindex(listlist.getSelectedIndex());
                        refreshorderlist(1);
                    }
                }
            }
        });
        listpopmenu.add(addlistpopmenuitem);
        listpopmenu.add(renamelistpopmenuitem);
        listpopmenu.add(printlistmenuitem);
        listpopmenu.add(deletelistpopmenuitem);
        listpopmenu.add(new JMenuItem("取消"));
        listlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3) {
                    EventOutput.outputEvent("PopupTrigger at Listlist");
                    listpopmenu.show(listlist, e.getX(), e.getY());
                }
            }
        });
        listlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Vector<String> tempstringVector_2 = new Vector<>();
                if (listlist.getSelectedIndex() != -1) {
                    for (int tempint = 0; tempint < listVector.elementAt(listlist.getSelectedIndex()).orderVector.size(); tempint++) {
                        tempstringVector_2.add(listVector.elementAt(listlist.getSelectedIndex()).getnameatindex(tempint));
                    }
                    orderlist.setListData(tempstringVector_2);
                    orderlist.setSelectedIndex(listVector.elementAt(listlist.getSelectedIndex()).getLastselectedindex());
                } else {
                    orderlist.setListData(tempstringVector_2);
                }
            }
        });

        ordereditdialog.setLayout(TempCreater.tempgridlayout(1, 1, 0, 0));
        JTextField editnametextfield = new JTextField();
        ordereditdialog.setSize(400, 200);
        ordereditdialog.setResizable(false);
        ordereditdialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ordereditdialog.setVisible(false);
                listlist.setEnabled(true);
                orderlist.setEnabled(true);
            }
        });
        ordereditdialog.setBackground(respack.getcolor("backgroundcolor"));
        ordereditdialog.setVisible(false);
        JButton edityesbutton = new JButton("确认");
        edityesbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("List_Edit_Yes_Button clicked");
                try {
                    listVector.elementAt(listlist.getSelectedIndex()).setnameatindex(orderlist.getSelectedIndex(), editnametextfield.getText());
                } catch (Exception e1) {
                    EventOutput.outputException(e1);
                }
                ordereditdialog.setVisible(false);
                listlist.setEnabled(true);
                int tempindex = orderlist.getSelectedIndex();
                refreshorderlist(2);
                orderlist.setSelectedIndex(tempindex);
                orderlist.setEnabled(true);
            }
        });
        JButton editcancelbutton = new JButton("取消");
        editcancelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventOutput.outputEvent("List_Edit_Cancel_Button clicked");
                ordereditdialog.setVisible(false);
                listlist.setEnabled(true);
                orderlist.setEnabled(true);
            }
        });
        JPanel editborder = new JPanel();
        editborder.setBorder(BorderFactory.createLineBorder(respack.getcolor("backgroundcolor"), 10));
        editborder.setBackground(respack.getcolor("backgroundcolor"));
        editborder.setLayout(TempCreater.tempgridlayout(3, 2, 5, 5));
        editborder.add(TempCreater.templabel("名称：", new Font("微软雅黑", 0, 20), null, Color.white));
        editborder.add(editnametextfield);
        editborder.add(edityesbutton);
        editborder.add(editcancelbutton);
        ordereditdialog.add(editborder);

        //nothing here...
        JPopupMenu orderpopmenu = new JPopupMenu();
        JMenuItem addorderpopmenuitem = new JMenuItem("新建发言人");
        addorderpopmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listlist.getSelectedIndex() != -1) {
                    try {
                        String tempstr = MessageWindow.showMessageWindow(1, "请输入一个代表团名称");
                        if (!tempstr.equals("")) {
                            listVector.elementAt(listlist.getSelectedIndex()).addorder(tempstr);
                            refreshorderlist(2);
                        }
                    } catch (Exception e1) {
                        EventOutput.outputException(e1);
                    }
                }
            }
        });
        JMenuItem printspeakermenuitem = new JMenuItem("将选中项显示为当前发言人");
        printspeakermenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listlist.getSelectedIndex() != -1) {
                    MFLauncher.console.recordtextarea.append("当前发言：" + listVector.elementAt(listlist.getSelectedIndex()).getnameatindex(orderlist.getSelectedIndex()) + "代表团\r\r\n\n");
                    ScreenWindow.Announcerinfolabel.setText("当前发言：" + listVector.elementAt(listlist.getSelectedIndex()).getnameatindex(orderlist.getSelectedIndex()) + "代表团");
                }
            }
        });
        JMenu addtolistmenu = new JMenu("添加到发言列表...");
        JMenuItem editmenuitem = new JMenuItem("编辑选中发言人");
        editmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listlist.getSelectedIndex() != -1 && orderlist.getSelectedIndex() != -1) {
                    listlist.setEnabled(false);
                    orderlist.setEnabled(false);
                    ordereditdialog.setLocation(MFLauncher.console.consolewind.getX() + MFLauncher.console.consolewind.getWidth() / 2, MFLauncher.console.consolewind.getY() + MFLauncher.console.consolewind.getHeight() / 2);
                    editborder.setBackground(respack.getcolor("backgroundcolor"));
                    editborder.setBorder(BorderFactory.createLineBorder(respack.getcolor("backgroundcolor"), 20));
                    ordereditdialog.setBackground(respack.getcolor("backgroundcolor"));
                    ordereditdialog.setTitle("编辑发言人" + listVector.elementAt(listlist.getSelectedIndex()).getnameatindex(orderlist.getSelectedIndex()));
                    editnametextfield.setText(listVector.elementAt(listlist.getSelectedIndex()).getnameatindex(orderlist.getSelectedIndex()));
                    ordereditdialog.setVisible(true);
                }

            }
        });
        JMenuItem deleteorderpopmenuitem = new JMenuItem("删除选中发言人");
        deleteorderpopmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderlist.getSelectedIndex() != -1) {
                    listVector.elementAt(listlist.getSelectedIndex()).deleteorderatindex(orderlist.getSelectedIndex());
                    refreshorderlist(2);
                }
            }
        });
        JMenu moveordermenu = new JMenu("移动...");
        JMenuItem upmoveordermenuitem = new JMenuItem("上移选中项");
        upmoveordermenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderlist.getSelectedIndex() != -1) {
                    list.Order temporder = listVector.elementAt(listlist.getSelectedIndex()).orderVector.elementAt(orderlist.getSelectedIndex());
                    listVector.elementAt(listlist.getSelectedIndex()).orderVector.remove(orderlist.getSelectedIndex());
                    int tempindex = orderlist.getSelectedIndex();
                    listVector.elementAt(listlist.getSelectedIndex()).orderVector.insertElementAt(temporder, orderlist.getSelectedIndex() - 1);
                    refreshorderlist(2);
                    orderlist.setSelectedIndex(tempindex - 1);
                }
            }
        });
        JMenuItem downmoveordermenuitem = new JMenuItem("下移选中项");
        downmoveordermenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderlist.getSelectedIndex() != -1) {
                    list.Order temporder = listVector.elementAt(listlist.getSelectedIndex()).orderVector.elementAt(orderlist.getSelectedIndex());
                    listVector.elementAt(listlist.getSelectedIndex()).orderVector.remove(orderlist.getSelectedIndex());
                    int tempindex = orderlist.getSelectedIndex();
                    listVector.elementAt(listlist.getSelectedIndex()).orderVector.insertElementAt(temporder, orderlist.getSelectedIndex() + 1);
                    refreshorderlist(2);
                    orderlist.setSelectedIndex(tempindex + 1);
                }
            }
        });
        moveordermenu.add(upmoveordermenuitem);
        moveordermenu.add(downmoveordermenuitem);
        orderpopmenu.add(addorderpopmenuitem);
        orderpopmenu.add(printspeakermenuitem);
        orderpopmenu.add(addtolistmenu);
        orderpopmenu.add(editmenuitem);
        orderpopmenu.add(moveordermenu);
        orderpopmenu.add(deleteorderpopmenuitem);
        orderpopmenu.add(new JMenuItem("取消"));
        orderlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listVector.elementAt(listlist.getSelectedIndex()).setLastselectedindex(orderlist.getSelectedIndex());
            }
        });
        orderlist.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()=='u'||e.getKeyChar()=='U'){
                    if (orderlist.getSelectedIndex() != -1) {
                        list.Order temporder = listVector.elementAt(listlist.getSelectedIndex()).orderVector.elementAt(orderlist.getSelectedIndex());
                        listVector.elementAt(listlist.getSelectedIndex()).orderVector.remove(orderlist.getSelectedIndex());
                        int tempindex = orderlist.getSelectedIndex();
                        listVector.elementAt(listlist.getSelectedIndex()).orderVector.insertElementAt(temporder, orderlist.getSelectedIndex() - 1);
                        refreshorderlist(2);
                        orderlist.setSelectedIndex(tempindex - 1);
                    }
                }else if(e.getKeyChar()=='d'||e.getKeyChar()=='D'){
                    if (orderlist.getSelectedIndex() != -1) {
                        list.Order temporder = listVector.elementAt(listlist.getSelectedIndex()).orderVector.elementAt(orderlist.getSelectedIndex());
                        listVector.elementAt(listlist.getSelectedIndex()).orderVector.remove(orderlist.getSelectedIndex());
                        int tempindex = orderlist.getSelectedIndex();
                        listVector.elementAt(listlist.getSelectedIndex()).orderVector.insertElementAt(temporder, orderlist.getSelectedIndex() + 1);
                        refreshorderlist(2);
                        orderlist.setSelectedIndex(tempindex + 1);
                    }
                }
            }
        });
        orderlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3) {
                    EventOutput.outputEvent("PopupTrigger at Orderlist");
                    addtolistmenu.removeAll();
                    upmoveordermenuitem.setEnabled(true);
                    downmoveordermenuitem.setEnabled(true);
                    if (orderlist.getSelectedIndex() != -1) {
                        for (int tempint = 0; tempint <= listVector.size() - 1; tempint++) {
                            JMenuItem tempmenuitem = new JMenuItem(listVector.elementAt(tempint).getName());
                            if (tempint == listlist.getSelectedIndex()) {
                                tempmenuitem.setEnabled(false);
                            }
                            final int tempindex = Integer.valueOf(tempint);
                            tempmenuitem.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    listVector.elementAt(tempindex).orderVector.add(listVector.elementAt(listlist.getSelectedIndex()).orderVector.elementAt(orderlist.getSelectedIndex()));
                                }
                            });
                            addtolistmenu.add(tempmenuitem);
                        }
                        if (orderlist.getSelectedIndex() == 0) {
                            upmoveordermenuitem.setEnabled(false);
                        }
                        if (orderlist.getSelectedIndex() == listVector.elementAt(listlist.getSelectedIndex()).orderVector.size() - 1) {
                            downmoveordermenuitem.setEnabled(false);
                        }
                    } else {
                        JMenuItem tempmenuitem = new JMenuItem("选中一名发言人以添加到...");
                        tempmenuitem.setEnabled(false);
                        addtolistmenu.add(tempmenuitem);
                    }
                    orderpopmenu.show(orderlist, e.getX(), e.getY());
                }
            }
        });
        listlist.setBackground(respack.getcolor("backgroundcolor"));
        orderlist.setBackground(respack.getcolor("backgroundcolor"));
        listlist.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
        orderlist.setForeground(respack.getcolor("textfielddefaultbuckcolor"));
    }

    public void AddToConsole(){
        MFLauncher.console.listpanel.add(listlistscrollpane);
        MFLauncher.console.listpanel.add(orderlistscrollpane);
    }

    public void refreshorderlist(int action) {
        Vector<String> tempstringVector_1 = new Vector<>();
        Vector<String> tempstringVector_2 = new Vector<>();
        switch (action) {
            case 1:
                for (int tempint = 0; tempint < listVector.size(); tempint++) {
                    tempstringVector_1.add(listVector.elementAt(tempint).getName());
                }
                listlist.setListData(tempstringVector_1);
                break;
            case 2:
                if (listlist.getSelectedIndex() != -1) {
                    for (int tempint = 0; tempint < listVector.elementAt(listlist.getSelectedIndex()).orderVector.size(); tempint++) {
                        tempstringVector_2.add(listVector.elementAt(listlist.getSelectedIndex()).getnameatindex(tempint));
                    }
                    orderlist.setListData(tempstringVector_2);
                } else {
                    orderlist.setListData(tempstringVector_2);
                }
                break;
            default:
                for (int tempint = 0; tempint < listVector.size(); tempint++) {
                    tempstringVector_1.add(listVector.elementAt(tempint).getName());
                }
                listlist.setListData(tempstringVector_1);
                if (listlist.getSelectedIndex() != -1) {
                    for (int tempint = 0; tempint < listVector.elementAt(listlist.getSelectedIndex()).orderVector.size(); tempint++) {
                        tempstringVector_2.add(listVector.elementAt(listlist.getSelectedIndex()).getnameatindex(tempint));
                    }
                    orderlist.setListData(tempstringVector_2);
                } else {
                    orderlist.setListData(tempstringVector_2);
                }
                break;
        }

    }
}
