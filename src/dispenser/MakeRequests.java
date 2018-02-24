/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispenser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wegt
 */
public class MakeRequests implements Runnable{
    private final ArrayList<Request> list;
    private final OutputStream out;
    
    public MakeRequests(ArrayList<Request> list, OutputStream out) {
        this.list = list;
        this.out=out;
    }
        
    @Override
    public void run() {
        while(!list.isEmpty()){
            try {
                Request r=list.remove(0);
                for(int i=0;i<r.getQuantity();i++){
                    r.perform(out);                    
                    Thread.sleep(11000);
                }
            } catch (IOException ex) {
                Logger.getLogger(MakeRequests.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MakeRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
