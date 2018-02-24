/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dispenser.ConnSerialComm;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 * Funciones varias para el entorno gráfico 
 */
public final class Miscellaneous { //final previene extension

    private Miscellaneous() {//private evita instanciacion
    }
    
    /**
     * Establecer el modelo en formato hora a una instancia de JSpinner.
     * @param js objeto de JSpinner a ser configurado
     */
    public static void setModelHourFor(JSpinner js){
        Date date=new Date();
        SpinnerDateModel sm=new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        js.setModel(sm);
        JSpinner.DateEditor de=new JSpinner.DateEditor(js, "HH:mm:ss");
        js.setEditor(de);
    }
    
    public static void respConnection(Component c, ConnSerialComm csc){        
        try {        
            int resp = csc.connect("COM3");
            switch (resp) {
                case ConnSerialComm.SUCCESS:
                    javax.swing.JOptionPane.showMessageDialog(c, "Conexion serial fue exitosa.", "Exito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    break;
                case ConnSerialComm.ONLY_SERIAL:
                    javax.swing.JOptionPane.showMessageDialog(c, "No se encontro un puerto serial.", "Sin conexion", javax.swing.JOptionPane.WARNING_MESSAGE);
                    break;
                case ConnSerialComm.PORT_IN_USE:
                    javax.swing.JOptionPane.showMessageDialog(c, "Puerto serial ocupado", "Puerto ocupado", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(c, "Posiblemente cable desconectado.", "Desconexion", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
}
