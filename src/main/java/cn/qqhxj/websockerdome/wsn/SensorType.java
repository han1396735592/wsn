package cn.qqhxj.websockerdome.wsn;

/**
 * @author han xinjian
 * @date 2018-12-08 18:56
 **/
public enum  SensorType {

    A('A',"温湿度传感器"),
    B('B',"火焰传感器");


    private char flag;

    private String name;

    private SensorType(char ch,String name){
        this.flag = ch;
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println((SensorType.A.flag));
    }

}
