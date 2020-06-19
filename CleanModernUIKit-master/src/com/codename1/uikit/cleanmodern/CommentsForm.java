/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Commentaire;
import com.mycompany.Entite.Sujet;
import com.mycompany.Entite.User;
import com.mycompany.Service.CommentaireService;
import com.mycompany.Service.SujetService;
import com.mycompany.Service.UserService;
import com.mycompany.utils.Session;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class CommentsForm extends BaseForm {

    private Image imag;
    private EncodedImage enc;
    private CommentaireService ser = new CommentaireService();
    Container ct = new Container();
    Container cc = new Container();
    Container ect = new Container();
    private ArrayList<Commentaire> comments = new ArrayList<Commentaire>();
    private Resources resource;
    private Sujet sujet;
    UserService us = UserService.getInstance();

    public CommentsForm(Resources res, Sujet sujet) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        resource = res;
        this.sujet = sujet;

       
        User u = us.fetch(sujet.getUser_id());
       
        String url = "http://localhost/events/" + u.getAvatar();

        try 
        {
           enc = EncodedImage.create("/tennis_club.png");
        } 
        catch (Exception ex) 
        {
            System.err.println(ex);
        }
        
        imag = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);                  
        
        drawHeader(imag, sujet);
        add(ct);
        
        Button contact = new Button("Envoyer un email");
        contact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                Message mes = new Message("Ecrivez quelque chose");
                Display.getInstance().sendMessage(new String[] {u.getEmail()}, "Enter Subject", mes);
            }
        });
        
        add(contact);
        
        url = "http://localhost/events/" + Session.getInstance().getLoggedInUser().getAvatar();
        imag = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);                  
        drawCommentBox(imag);
        add(cc);
        
        
        
        
        comments = ser.getComments(sujet.getId());
        for(Commentaire c : comments)
        {
            User commenter = us.fetch(c.getUser_id());
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            String urll = "http://localhost/events/" + commenter.getAvatar();
            placeholder = URLImage.createToStorage(encImage, urll, urll);
            drawComments(placeholder, c);
        }
        
        add(ect);
 
    }
    
 
    
    private void drawComments(Image img, Commentaire c) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
      
       Container cnt = BorderLayout.west(image);
    

       SpanLabel contenu = new SpanLabel(c.getContenu());
       SpanLabel date = new SpanLabel(c.getDate());


       Label update = new Label("edit");
       FontImage.setMaterialIcon(update, FontImage.MATERIAL_EDIT);
       update.addPointerPressedListener((evt) -> {
            new CommentForm(resource, sujet, c).show();
       });
       
       Label delete = new Label("delete");
       FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
       delete.addPointerPressedListener((evt) -> {
            ser.delete(c.getId());
            new CommentsForm(resource, sujet).show();
       });

  
    if(c.getUser_id()== Session.getInstance().getLoggedInUser().getId())
       {
           cnt.add(BorderLayout.CENTER, 
           BoxLayout.encloseY(
                contenu,
                date,
                BoxLayout.encloseX(update, delete)
            ));
       }
       else
       {
            cnt.add(BorderLayout.CENTER, 
            BoxLayout.encloseY(
                contenu,
                date 
            ));
       } 
     
    ect.add(cnt);
   }
    
      private void drawHeader(Image img, Sujet s) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);

       SpanLabel d = new SpanLabel(s.getContenu());
       SpanLabel date = new SpanLabel(s.getDate());

       
       ShareButton sb = new ShareButton();
        sb.setText("Partager");
        
        Image screenshot = Image.createImage(getWidth(), getHeight());
        revalidate();
        setVisible(true);
        paintComponent(screenshot.getGraphics(), true);

        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch(IOException err) {
            Log.e(err);
        }
        sb.setImageToShare(imageFile, "image/png");
        
        cnt.add(BorderLayout.CENTER, 
        BoxLayout.encloseY(
            d,
            date,
            sb
        ));
       ct.add(cnt);
   }
      
   private void drawCommentBox(Image img) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);

       TextField contenu = new TextField();
       contenu.setHint("Ecrivez quelque chose");
       contenu.setUIID("TextFieldBlack");

       Label confirm = new Label("commenter");
       FontImage.setMaterialIcon(confirm, FontImage.MATERIAL_ADD);
       confirm.addPointerPressedListener((evt) -> {
          if(!contenu.getText().equals(""))
          {
            ser.add(contenu.getText(), Session.getInstance().getLoggedInUser().getId(), sujet.getId());
            new CommentsForm(resource, sujet).show(); 
          }
          
       });
     
        cnt.add(BorderLayout.CENTER, 
        BoxLayout.encloseY(
           contenu,
           confirm
        ));
       cc.add(cnt);
   }

}