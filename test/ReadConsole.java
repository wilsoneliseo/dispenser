
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wegt
 */
public class ReadConsole {
    public static void main(String[] args)
    {
        try{
            int i=System.in.read();//returns ASCII code of 1st character
            System.out.println((char)i);//will print the character
        }catch(IOException e){
        }
    }
}
