package presenter;


public class BasePresenter<V> {
    private V mV;
    public void attachView(V v){
        this.mV = v;
    }
    public V getMvpView(){
        return mV;
    }
    public void dettachView(){
        mV = null;
    }
}
