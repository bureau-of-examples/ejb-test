package zhy2002.ejbtest;

import javax.ejb.Remote;

/**
 * Remote interface for session service.
 */
@Remote
public interface SessionServiceRemote {

    Integer getIdentityHashcode() throws InterruptedException;

    void storeValue(String key, Object object);

    Object getValue(String key);
}
