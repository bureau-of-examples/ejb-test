package zhy2002.ejbtest;

import javax.ejb.Remote;

/**
 * Remote interface for SampleService
 */
@Remote
public interface SampleServiceRemote {

    String getMessage(String clientName);

    Integer getIdentityHashcode();
}
