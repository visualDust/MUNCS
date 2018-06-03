package MockFormula.lib.Method;

import MockFormula.lib.MFLauncher;
import MockFormula.lib.UI.Console.MessageWindow;
import MockFormula.lib.UI.Display.ScreenWindow;

import java.awt.*;
import java.time.LocalTime;

public class Clockcontrol {
    public static boolean running = false;
    public String earlier = "";

    public Clockcontrol() {
        clockerthread tempthread = new clockerthread();
        tempthread.start();
    }

    public class clockerthread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (!earlier.equals(String.valueOf(LocalTime.now().getSecond()))) {
                    if (running && !MFLauncher.console.clocktotaltextfield.getText().equals("0")) {
                        if (MFLauncher.console.singletime == 0) {
                            if (!MFLauncher.console.clocktotaltextfield.getText().equals("0")) {
                                MFLauncher.console.clocktotaltextfield.setText(String.valueOf(Integer.valueOf(MFLauncher.console.clocktotaltextfield.getText()) - 1));
                                ScreenWindow.totaltimelabel.setText(String.valueOf(Integer.valueOf(MFLauncher.console.clocktotaltextfield.getText())) + " s");
                            } else {
                                running = false;
                                ScreenWindow.colorpanel.setBackground(new Color(255, 150, 0));
                                ScreenWindow.infoactionlabel.setText("本轮计时全部结束");
                                ScreenWindow.infoaction(3);
                                MFLauncher.console.startclockbutton.setEnabled(true);
                                MFLauncher.console.pauseclockbutton.setEnabled(false);
                                MessageWindow.showMessageWindow(0, "本轮计时全部结束");
                            }
                        } else if (MFLauncher.console.singletime != 0) {
                            if (!MFLauncher.console.clocksingletextfield.getText().equals("0")) {
                                MFLauncher.console.clocksingletextfield.setText(String.valueOf(Integer.valueOf(MFLauncher.console.clocksingletextfield.getText()) - 1));
                                MFLauncher.console.clocktotaltextfield.setText(String.valueOf(Integer.valueOf(MFLauncher.console.clocktotaltextfield.getText()) - 1));
                                ScreenWindow.totaltimelabel.setText(String.valueOf(Integer.valueOf(MFLauncher.console.clocktotaltextfield.getText())) + " s");
                                ScreenWindow.singletimelabel.setText(String.valueOf(Integer.valueOf(MFLauncher.console.clocksingletextfield.getText())) + " s");
                            } else if (MFLauncher.console.clocksingletextfield.getText().equals("0")) {
                                if (Integer.valueOf(MFLauncher.console.clocktotaltextfield.getText()) >= MFLauncher.console.singletime) {
                                    running = false;
                                    MFLauncher.console.startclockbutton.setEnabled(true);
                                    MFLauncher.console.pauseclockbutton.setEnabled(false);
                                    ScreenWindow.colorpanel.setBackground(new Color(255, 150, 0));
                                    ScreenWindow.infoactionlabel.setText("本次发言结束");
                                    ScreenWindow.infoaction(3);
                                    MFLauncher.console.clocksingletextfield.setText(String.valueOf(MFLauncher.console.singletime));
                                    ScreenWindow.singletimelabel.setText(String.valueOf(MFLauncher.console.singletime));
                                    MessageWindow.showMessageWindow(0, "本次发言结束");
                                } else {
                                    running = false;
                                    MFLauncher.console.startclockbutton.setEnabled(true);
                                    MFLauncher.console.pauseclockbutton.setEnabled(false);
                                    MFLauncher.console.clocktotaltextfield.setText("0");
                                    ScreenWindow.totaltimelabel.setText("0000s");
                                    ScreenWindow.singletimelabel.setText("0000s");
                                    ScreenWindow.tocolor = new Color(255, 150, 0);
                                    ScreenWindow.infoactionlabel.setText("剩余时间不足，本轮计时全部结束");
                                    ScreenWindow.infoaction(3);
                                    MessageWindow.showMessageWindow(0, "剩余时间不足，本轮计时全部结束");
                                }
                            }
                        }
                    }
                }
                earlier = String.valueOf(LocalTime.now().getSecond());
                try {
                    sleep(100);
                } catch (Exception e) {
                    EventOutput.outputException(e);
                }
            }
        }
    }
}
