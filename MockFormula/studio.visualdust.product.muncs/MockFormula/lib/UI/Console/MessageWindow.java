package MockFormula.lib.UI.Console;

import MockFormula.lib.Method.EventOutput;
import MockFormula.lib.MFLauncher;
import MockFormula.lib.DataStructure.Resource;

import javax.swing.*;

public class MessageWindow {
    private static Resource respack = new Resource();

    public MessageWindow() {

    }

    public static String showMessageWindow(int style, String messages) {
        try {
            UIManager.setLookAndFeel(respack.getDefaultlookandfeel());
        } catch (Exception e) {
            EventOutput.outputException(e);
        }//Change look and feel to system look and feel

        if (style == 0)/*Just show a message*/ JOptionPane.showMessageDialog(MFLauncher.console.consolewind, messages);
        if (style == 1)/*Input a String with message showing*/
            return JOptionPane.showInputDialog(MFLauncher.console.consolewind, messages);
        if (style == 2) { /*switch a button with a message*/
            switch (JOptionPane.showConfirmDialog(MFLauncher.console.consolewind, messages)) {
                case 0:
                    return "0"; //"yes" button
                case 1:
                    return "1"; //"no" button
                case 2:
                    return "2"; //"cancel" button
            }
        }
        return "9"; //if nothing to be return...
    }
}
