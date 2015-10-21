package zhy2002.ejbtest;

import javax.ejb.Stateful;
import java.util.HashMap;

/**
 *
 */
@Stateful(name = "SessionServiceStatefulBean")
public class SessionServiceStatefulBean implements SessionServiceRemote{

    private HashMap<String, Object> storage = new HashMap<>();

    @Override
    public Integer getIdentityHashcode() throws InterruptedException {
        Thread.sleep(1000);
        return hashCode();
    }

    @Override
    public void storeValue(String key, Object object) {
        storage.put(key, object);
    }

    @Override
    public Object getValue(String key) {
        return storage.get(key);
    }
}
