package MockFormula.lib.Method;

import javax.swing.*;
import java.awt.*;

public class TempCreater {
    public TempCreater() {

    }

    public static GridLayout tempgridlayout(int rows, int cols, int hgap, int vgap) {
        GridLayout templayout = new GridLayout(rows, cols);
        templayout.setVgap(vgap);
        templayout.setHgap(hgap);
        return templayout;
    }

    public static JLabel templabel(String text, Font font, Color buckgroundcolor, Color foregroundcolor) {
        JLabel templabel = new JLabel(text);
        templabel.setBackground(buckgroundcolor);
        templabel.setForeground(foregroundcolor);
        templabel.setFont(font);
        return templabel;
    }

    public static JPopupMenu tempnoticePopmenu(String showStr,int maxkeepTime){
        JPopupMenu temppopmenu = new JPopupMenu();
        JMenuItem tempmenuitem = new JMenuItem(showStr);
        tempmenuitem.setEnabled(false);
        temppopmenu.add(tempmenuitem);
        temppopmenu.add("我知道了");
        class tempwaitthr extends Thread{
            @Override
            public void run() {
                try{
                    sleep(maxkeepTime);
                }catch (Exception e){
                    EventOutput.outputException(e);
                }
                temppopmenu.setVisible(false);
            }
        }
        tempwaitthr tempthr = new tempwaitthr();
        tempthr.start();
        return temppopmenu;
    }
}
