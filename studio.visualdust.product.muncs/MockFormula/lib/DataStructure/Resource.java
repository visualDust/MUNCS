package MockFormula.lib.DataStructure;

import MockFormula.lib.MFLauncher;
import com.alee.laf.WebLookAndFeel;

import javax.swing.*;
import java.awt.*;

public class Resource {

    public static Color defaultfontcolor = new Color(230, 230, 230);
    public static Color titlefontcolor = defaultfontcolor;
    public static Color backgroundcolor = new Color(60, 63, 65);
    public static Color screenbuckcolor = new Color(0, 180, 255);
    public static Color screenfontcolor = defaultfontcolor;
    public static Color warningbuckcolor = new Color(255, 202, 0);
    public static Color warningforecolor = defaultfontcolor;
    public static Color textfielddefaultbuckcolor = new Color(255, 255, 255);
    public static Color textfielddefaultforecolor = backgroundcolor;
    public static Font defaulttextfont = new Font("微软雅黑", 0, 20);
    public static Font recordtextareafont = new Font("微软雅黑", 0, 20);
    public static Font defaultlabelfont = new Font("微软雅黑", 0, 15);

    public static LookAndFeel defaultlookandfeel = new WebLookAndFeel();

    public Resource() {
        restdefaultfont();
    }

    public static void restdefaultfont() {
        Font f = new Font("微软雅黑", 0, 15);
        String names[] = {"Label", "CheckBox", "PopupMenu", "MenuItem", "CheckBoxMenuItem",
                "JRadioButtonMenuItem", "ComboBox", "Button", "Tree", "ScrollPane",
                "TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
                "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
                "ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
                "PasswordField", "TextField", "Table", "Label", "Viewport",
                "RadioButtonMenuItem", "RadioButton", "DesktopPane", "InternalFrame"
        };
        for (String item : names) {
            UIManager.put(item + ".font", f);
        }
    }

    public Color getcolor(String colorname) {
        switch (colorname) {
            case "titlefontcolor":
                return titlefontcolor;
            case "backgroundcolor":
                return backgroundcolor;
            case "screenbuckcolor":
                return screenbuckcolor;
            case "defaultfontcolor":
                return defaultfontcolor;
            case "screenfontcolor":
                return screenfontcolor;
            case "warningforecolor":
                return warningforecolor;
            case "warningbuckcolor":
                return warningbuckcolor;
            case "textfielddefaultbuckcolor":
                return textfielddefaultbuckcolor;
            case "textfielddefaultforecolor":
                return textfielddefaultforecolor;
            default:
                IllegalArgumentException e = new IllegalArgumentException(String.format("Color name %s not found", colorname));
                MFLauncher.console.noticeanderrortextarea.append(e.toString() + "\n");
                throw e;
        }
    }

    public LookAndFeel getDefaultlookandfeel() {
        return defaultlookandfeel;
    }

    public Font getFont(String fontname) {
        switch (fontname) {
            case "defaulttextfont":
                return defaulttextfont;
            case "recordtextareafont":
                return recordtextareafont;
            case "defaultlabelfont":
                return defaultlabelfont;
            default:
                return null;
        }
    }
}
