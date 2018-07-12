package MockFormula.lib.UI.Console;

import MockFormula.lib.Method.TempCreater;

import javax.swing.*;
import java.awt.*;

public class CheckItemPanel extends JPanel {
    public static Color configbackgroundcolor = new Color(222, 222, 222);
    public static Color configfontcolor = new Color(66, 66, 66);
    public JCheckBox checkBox = new JCheckBox();
    public JLabel textLabel = new JLabel("",JLabel.LEFT);

    public CheckItemPanel(boolean isSelected, String text){
        checkBox.setSelected(isSelected);
        textLabel.setText(text);
        this.setLayout(TempCreater.tempgridlayout(2,1,5,5));
        this.add(checkBox);
        this.add(textLabel);
    }

    public boolean IsSelected(){
        if(checkBox.isSelected()){return true;}
        else return false;
    }
}
