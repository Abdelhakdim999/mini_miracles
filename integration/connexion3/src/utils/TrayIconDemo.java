/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

/**
 *
 * @author Dimassi Abdelhak
 */
public class TrayIconDemo {
    public void trayAjout() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("/images/mini_miracles.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray ajout");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray ajout");
        tray.add(trayIcon);

        trayIcon.displayMessage("Admin", "Activité ajoutée avec succee", TrayIcon.MessageType.INFO);
    }
    
    public void trayModif() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("/images/mini_miracles.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray modif");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray ajout");
        tray.add(trayIcon);

        trayIcon.displayMessage("Admin", "Activité modifiée avec succee", TrayIcon.MessageType.INFO);
    }
    
    public void traySupp() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("/images/mini_miracles.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray modif");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray ajout");
        tray.add(trayIcon);

        trayIcon.displayMessage("Admin", "Activité supprimée avec succee", TrayIcon.MessageType.INFO);
    }
    
    public void trayEnfAjout() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("/images/mini_miracles.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray modif");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray ajout");
        tray.add(trayIcon);

        trayIcon.displayMessage("Parent", "Enfant ajouté avec succee", TrayIcon.MessageType.INFO);
    }
    
    public void trayEnfModif() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("/images/mini_miracles.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray modif");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray ajout");
        tray.add(trayIcon);

        trayIcon.displayMessage("Parent", "Enfant modifié avec succee", TrayIcon.MessageType.INFO);
    }
    
    public void trayEnfSupp() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("/images/mini_miracles.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray modif");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray ajout");
        tray.add(trayIcon);

        trayIcon.displayMessage("Parent", "Enfant supprimé avec succee", TrayIcon.MessageType.INFO);
    }
    
}
