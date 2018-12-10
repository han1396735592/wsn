package cn.qqhxj.websockerdome.wsn;

/**
 * @author han xinjian
 * @date 2018-12-08 18:56
 **/
public enum SensorType {

    A('A', "雨滴"),
    B('B', "三轴加速度"),
    C('C', "光照"),
    D('D', "颜色"),
    E('E', "温湿度"),
    F('F', "气压海拔"),
    G('G', "RFID"),
    I('I', "遥控器"),
    J('J', "可燃气体"),
    L('L', "光敏"),
    N('N', "火焰"),
    Q('Q', "电子称"),
    U('U', "18B20"),
    T('T', "条形码"),
    _03H(((char) 0x03), "315M传感器"),
    _04H(((char) 0x04), "震动"),
    _07H(((char) 0x07), "超高频"),
    _08H(((char) 0x08), "14443A-ISO15693"),
    _0AH(((char) 0x0A), "智能货架-ISO15693"),
    _0BH(((char) 0x0B), "125K低频"),
    _0CH(((char) 0x0C), "红外对射"),
    _0DH(((char) 0x0D), "高频15693"),
    _0FH(((char) 0x0F), "水位测试"),
    _11H(((char) 0x11), "氧气传感器"),
    _20H(((char) 0x20), "噪声测量"),
    _21H(((char) 0x21), "光线TPS852"),
    _22H(((char) 0x22), "二氧化碳"),
    _13H(((char) 0x13), "485电表"),
    _14H(((char) 0x14), "PH值测量"),
    S('S', "调光模块"),
    O('O', "凌阳语音控制"),
    H('H', "无线窗帘控制"),
    P('P', "红外学习遥控"),
    K('K', "无线控制"),
    _02H(((char) 0x02), "直流电机调速"),
    _05H(((char) 0x05), "雨棚控制"),
    _06H(((char) 0x06), "智能小车"),
    _09H(((char) 0x09), "多功能演示板"),
    _12H(((char) 0x12), "电磁锁"),
    _15H(((char) 0x15), "语音播放"),
    _23H(((char) 0x23), "指纹识别");

    public char flag;

    public String name;

    private SensorType(char ch, String name) {
        this.flag = ch;
        this.name = name;
    }
}
