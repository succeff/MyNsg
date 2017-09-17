package util;

public interface NetDataCallBack<T> {
    void success(T t);
    void error(String error);
}
