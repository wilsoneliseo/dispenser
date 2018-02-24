/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispenser;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author wegt
 */
public class ConnSerialComm {    
    private static final int TIMEOUT=2000; /**< constante de uso interno. Tiempo muerto en milisegundos. */
    private static final int DATA_RATE=9600; /**< constante de uso interno. Velocidad de datos. */
    private OutputStream out;

    public OutputStream getOut() {
        return out;
    }
    
    public static final int PORT_IN_USE=1; /**< constante de uso externo. Significa error que ocurre cuando el puerto actual esta en uso. */
    public static final int SUCCESS=2; /**< constante de uso externo. Significa la conexion fue exitosa. */
    public static final int ONLY_SERIAL=3; /**< constante de uso externo. Significa error que ocurre cuando el puerto actual no es serial. */

    public ConnSerialComm() {
        super();
        this.out = null;
    }
    
    /**
     * Realiza la conexion serial.
     * @param portName es el nombre del puerto. Por ejemplo "COM3".
     * @return el entero de acuerdo al significado de las constantes publicas de esta clase.
     * @throws Exception 
     */
    public int connect(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            return PORT_IN_USE;
        } else {
            CommPort commPort = portIdentifier.open(this.getClass().getName(), TIMEOUT);

            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                this.out=serialPort.getOutputStream();

//                InputStream in = serialPort.getInputStream();
//                OutputStream out = serialPort.getOutputStream();

//                (new Thread(new SerialReader(in))).start();
//                (new Thread(new SerialWriter(out))).start();
                return SUCCESS;
            } else {
                return ONLY_SERIAL;
            }
        }
    }

    /**
     *      */
    public static class SerialReader implements Runnable {

        InputStream in;

        public SerialReader(InputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int len = -1;
            try {
                while ((len = this.in.read(buffer)) > -1) {
                    System.out.print(new String(buffer, 0, len));
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     *      */
    public static class SerialWriter implements Runnable {

        OutputStream out;

        public SerialWriter(OutputStream out) {
            this.out = out;
        }

        @Override
        public void run() {
            try {
                int c = 0;
                while ((c = System.in.read()) > -1) {
                    this.out.write(c);
                }
            } catch (IOException e) {
            }
        }
    }
}
