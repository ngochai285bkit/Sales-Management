package App;

import View.LoginView;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.util.SystemInfo;

import javax.swing.*;

public class Launch {
    static boolean screenshotsMode = Boolean.parseBoolean(System.getProperty("flatlaf.demo.screenshotsMode"));

    public static void main(String[] args) {
        // macOS  (see https://www.formdev.com/flatlaf/macos/)
        if (SystemInfo.isMacOS) {
            // enable screen menu bar
            // (moves menu bar from JFrame window to top of screen)
            System.setProperty("apple.laf.useScreenMenuBar", "true");

            // application name used in screen menu bar
            // (in first menu after the "apple" menu)
            System.setProperty("apple.awt.application.name", "FlatLaf Demo");

            // appearance of window title bars
            // possible values:
            //   - "system": use current macOS appearance (light or dark)
            //   - "NSAppearanceNameAqua": use light appearance
            //   - "NSAppearanceNameDarkAqua": use dark appearance
            // (needs to be set on main thread; setting it on AWT thread does not work)
            System.setProperty("apple.awt.application.appearance", "system");
        }

        // Linux
        if (SystemInfo.isLinux) {
            // enable custom window decorations
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        }

        if (Launch.screenshotsMode && !SystemInfo.isJava_9_orLater && System.getProperty("flatlaf.uiScale") == null)
            System.setProperty("flatlaf.uiScale", "2x");


        SwingUtilities.invokeLater(() -> {
            FlatIntelliJLaf.setup();
            new LoginView();
        });
    }
}
