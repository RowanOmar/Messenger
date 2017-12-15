/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymessenger;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 *
 * @author Rawan
 */
public class User {

  
    public PrintWriter out;
    public BufferedReader in;
    
      public User(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }
}

