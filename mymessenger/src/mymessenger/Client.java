/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymessenger;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Rawan
 */

public class Client extends JFrame{
    actionperformed action=new actionperformed();
    InputStream in;
    OutputStream out;
    Socket toFromserver = null;
    String clientname;
    JButton button;
    PrintWriter pw;
    Encryption encrypt;
    JLabel label,imagelabel;
    ImageIcon image;
    JTextField textfield=new JTextField("Write message here"); 
    public Client (String Name) throws IOException
    {
        this.clientname=Name;
        setSize(400,500);
        setTitle("Client " + Name);
        label=new JLabel("<html>Messages<br>");
        setLayout(null);
        label.setBounds(20,20, 200,250);
        
        add(label);
        button=new JButton("Send");
        button.setForeground(Color.red);
        button.setBorder( BorderFactory.createRaisedBevelBorder());
        button.setBackground(Color.yellow);
        
     
        button.setBounds(90,325,150,40);
        textfield.setBounds(50,375,250,50);
        add(textfield);
        button.addActionListener(action);
        add(button);
        
                try {
                    toFromserver = new Socket("127.0.0.1", 6000);
                    in=toFromserver.getInputStream();
                    out=toFromserver.getOutputStream();
                    readFromServer read=new readFromServer();
                    read.start();

                } catch (IOException ex) {
                    
                }
                encrypt=new Encryption();
                image=new ImageIcon("userrr.png");
                imagelabel=new JLabel(image);
                imagelabel.setBounds(270, 25, 100, 150);
               add(imagelabel);
    }
     private class actionperformed implements  ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            
                if (e.getSource().equals(button))
                {                    
                               
           pw=new PrintWriter(out,true);
           String text=textfield.getText();
           String encrypted =encrypt.Encrypt(text);
           String enc=encrypt.Encrypt(clientname+":");
           pw.println(enc+encrypted);  
            textfield.setText(null);
//          recivedMsg.setText(recivedMsg.getText()+"<br>"+text);
                            
           
                }
            }
                   
    }
private class readFromServer extends Thread
    {
        public void run()
        {
            while (true)
            {
                
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));
               
                try {
                    String Msg = reader.readLine();
                    if (Msg!=null)
                    {                    
                    label.setText(label.getText()+"<br>"+Msg);
                    
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    
                }
    
         
            }
        }
       }

    }

