/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymessenger;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Rawan
 */
public class Mymessenger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        Server server=new Server();
        server.setVisible(true);
        server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread thread=new Thread(server);
        thread.start();
        
        
        Client client1=new Client("Sara");
        client1.setVisible(true);
        client1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Client client2=new Client("Rowan");
        client2.setVisible(true);
        client2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO code application logic here
    }
    
}
