package MockFormula.lib.UI.Display;

import javax.swing.*;
import java.awt.*;

public class ScreenCover {
    public JPanel coverPanel = new JPanel();
    public JLabel textLabel = new JLabel("", JLabel.CENTER);

    public ScreenCover(String coverText, Font coverFont) {
        textLabel.setText(coverText);
        textLabel.setFont(coverFont);
        coverPanel.add(textLabel);
        coverPanel.setLayout(null);
        Resize(new Dimension(10, 10));
    }

    public void Resize(Dimension dimension) {
        coverPanel.setSize(dimension);
        textLabel.setLocation(0, (int) (coverPanel.getHeight() * 0.4));
        textLabel.setSize(coverPanel.getWidth(), 100);
    }

    public JPanel getCoverPanel(Dimension dimension) {
        Resize(dimension);
        return coverPanel;
    }

    public void setText(String text) {
        textLabel.setText(text);
    }

    public void setFont(Font font) {
        textLabel.setFont(font);
    }
}
