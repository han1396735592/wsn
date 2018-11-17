package cn.qqhxj.websockerdome.rxtx;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main implements SerialPortEventListener {

    // 输入/输出流
    private InputStream inputStream;
    private OutputStream outputStream;

    @Override
    public void serialEvent(SerialPortEvent event) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        /*
         *  SerialPortEvent.BI:/*Break interrupt,通讯中断
         *  SerialPortEvent.OE:/*Overrun error，溢位错误
         *  SerialPortEvent.FE:/*Framing error，传帧错误
         *  SerialPortEvent.PE:/*Parity error，校验错误
         *  SerialPortEvent.CD:/*Carrier detect，载波检测
         *  SerialPortEvent.CTS:/*Clear to send，清除发送
         *  SerialPortEvent.DSR:/*Data set ready，数据设备就绪
         *  SerialPortEvent.RI:/*Ring indicator，响铃指示
         *  SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*Output buffer is empty，输出缓冲区清空
         *  SerialPortEvent.DATA_AVAILABLE:
         */
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                System.out.println("err");
                break;
            case SerialPortEvent.DATA_AVAILABLE: // 
                readData();
                break;

            default:
                break;
        }

    }


    public void readData() {
        byte[] rbuff = new byte[1024];
        int hasRead = 0;

        try {
            while((hasRead=inputStream.read(rbuff)) !=-1) {
                System.out.println(hasRead);
                String data = new String(rbuff, 0, hasRead);
                System.out.println("接受了数据 data=("+data+")");
                break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {


        Main main = new Main();


        try {
            SerialPort serialPort = SerialUtils.connect("COM4", 4800);
            main.outputStream = serialPort.getOutputStream();
            main.inputStream = serialPort.getInputStream();
            serialPort.addEventListener(main);
            // 设置可监听

            serialPort.notifyOnDataAvailable(true);

            while (true){
                boolean b = SerialUtils.sendData(serialPort, "aa".getBytes());
                System.out.println(b);
                System.out.println("send");
                Thread.sleep(1000);
            }




        } catch (Exception e) {
            e.printStackTrace();
        }



    }


}
