/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymessenger;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Rawan
 */

public class Server extends JFrame implements Runnable{
    
    private JTextField textfield;
    public  JLabel Messages;
    JButton button=new JButton("Send");
    private InputStream in;
    private OutputStream out;
    private PrintWriter toclient;  
    private BufferedReader fromclient;
    private ArrayList<Socket> connections=new ArrayList<Socket>();
    ArrayList <User>users=new ArrayList<User>();
    Encryption encrypt;
    private ServerSocket serversocket;
    private Socket clientsocket;
   
       
    
    public Server() throws IOException
    {
        setSize(new Dimension (500,500));
        setTitle("Server");
        serversocket=new ServerSocket(6000);
        setLayout(null);
        Messages=new JLabel("<html>All messages");
        textfield=new JTextField("Write message here");
        Messages.setBounds(20, 20, 300, 100);
        textfield.setBounds(150,375,150,50);
        button.setBounds(150,325,150,50);
        button.setForeground(Color.yellow);
        button.setBorder( BorderFactory.createRaisedBevelBorder());
        button.setBackground(Color.red);
        add(Messages);
        add(textfield);
        add(button);
       button.addActionListener(new actionperformed());
        encrypt=new Encryption();
        
    }
    public void run() 
    {
        try
        {
        while (true)
        {
        clientsocket=serversocket.accept();
        connections.add(clientsocket);
        
        in=clientsocket.getInputStream();
        out=clientsocket.getOutputStream();
         
        new Thread(new Runnable() {

            @Override
            public void run() {
                           fromclient=new BufferedReader(new InputStreamReader(in));          
                           toclient=new PrintWriter(out,true);  
                           users.add(new User(toclient,fromclient));
                           while(true)
                           {
                               String New;  
                               try {
                                   New = fromclient.readLine();  
                                   String decrypt=encrypt.decrypt(New);
                                   
                                   broadcast(decrypt);
                                   Messages.setText(Messages.getText() +"<br>"+New);

                               } catch (IOException ex) {
                                   Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                               }

                           }
            }
        }).start();

       Thread.sleep(1000);
       
            System.out.println("Client Connected");
            
        }
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    private class actionperformed implements  ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                if (e.getSource().equals(button))
                {
                ToConnections(textfield.getText());
                Messages.setText(Messages.getText()+"<br>"+textfield.getText());
                textfield.setText(null);
                
                }
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
    }

    
    public void broadcast(String text)
    {
        for(int i=0;i<users.size();i++)
        {
            User user=users.get(i);
            user.out.println(text);
        }
    
    }  
    public void ToConnections(String msg) throws IOException
    {
        for (Socket s: connections)
        { 
              
        OutputStream os=s.getOutputStream();  
        PrintWriter pw=new PrintWriter(os,true);
        pw.println(msg);   
        
        }
    }
}
