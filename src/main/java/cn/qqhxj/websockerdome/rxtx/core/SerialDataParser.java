package cn.qqhxj.websockerdome.rxtx.core;

/**
 * @author han xinjian
 * @date 2018-12-11 12:07
 **/
@FunctionalInterface
public interface SerialDataParser<T> {
    T parse(byte[] bytes);
}
