package presenter;


public interface IMainView<T> {
    void success(T t, int code);
    void error(String message);
}
