package cn.qqhxj.websockerdome.rxtx;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
/**
 * @author han xinjian
 * @date 2018-11-13 12:53
 **/
public class SerialUtils {

    /**
     * 获取所有可以用的端口号
     * @return  端口列表
     */
    public  static ArrayList<String>  getCommNames() {
        @SuppressWarnings("portIdentifiers")
        Enumeration<CommPortIdentifier> portIdentifiers = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> list = new ArrayList<String>();
        while (portIdentifiers.hasMoreElements()){
            list.add(portIdentifiers.nextElement().getName());
        }
        return  list.isEmpty()?null:list;
    }

    /**
     *   链接到指定串口
     * @param portName  端口号
     * @param baudRgot  比特率
     * @param dataBits  数据位
     * @param stopBits  停止位
     * @param parity    校验位
     * @return          串口
     * @throws Exception  串口连接失败
     */
    public static SerialPort connect ( String portName,int  baudRgot ,int dataBits,int stopBits,int parity ) throws Exception{
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.err.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(SerialUtils.class.getName(),2000);

            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(57600,dataBits,stopBits,parity);

                return (SerialPort) commPort;
            }
            else
            {
                System.err.println("Error: Only serial ports are handled by this example.");
            }
        }
        return null;
    }


    /**
     *  连接到指定端口号的串口  默认数据位 8位 停止位 1   校验位 0
     * @param portName  端口号
     * @param baudRgot  比特率
     * @return           串口
     * @throws Exception  连接串口错误
     */
    public static SerialPort connect ( String portName ,int  baudRgot ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.err.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(SerialUtils.class.getName(),2000);

            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(baudRgot,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

                return (SerialPort) commPort;
            }
            else
            {
                System.err.println("Error: Only serial ports are handled by this example.");
            }
        }
        return null;
    }


    /**
     * 发送数据
     * @param serialPort  端口
     * @param bytes   数据
     * @return  数据是否发送成功
     */
    public static boolean sendData(SerialPort serialPort,byte[] bytes ){
        try {
            serialPort.getOutputStream().write(bytes);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static  byte[] readData(SerialPort serialPort) {
        byte[] rbuff = new byte[1024];
        int hasRead = 0;
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            while((hasRead=serialPort.getInputStream().read(rbuff)) !=-1) {
                stringBuffer.append(new String(rbuff, 0, hasRead));
                break;
            }
            return stringBuffer.toString().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
