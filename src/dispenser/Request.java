/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispenser;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author wegt
 */
public abstract class Request {
    int quantity;

    public Request(int quantity) {
        this.quantity = quantity;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    abstract void perform(OutputStream out) throws IOException;
}
