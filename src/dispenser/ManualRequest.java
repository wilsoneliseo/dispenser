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
public class ManualRequest extends Request{

    public ManualRequest(int quantity) {
        super(quantity);
    }

    @Override
    void perform(OutputStream out) throws IOException {
        String s="0";
        out.write(s.getBytes());       
    }
    
}
