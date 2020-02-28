package utils;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class TrayIconDemo1 {


    public void trayAjout() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("./src/icon.png.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray ajout");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray ajout");
        tray.add(trayIcon);

        trayIcon.displayMessage("Admin", "Ajouté avec succes", MessageType.INFO);
    }
    
    
        public void trayRefus() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("./src/icon.png.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray ajout");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray ajout");
        tray.add(trayIcon);

        trayIcon.displayMessage("Admin", "Verifier vos champs", MessageType.INFO);
    }
}