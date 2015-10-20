package zhy2002.ejbtest;

import javax.ejb.Stateless;

/**
 *
 */
@Stateless
public class SampleServiceStatelessBean implements SampleServiceRemote {

    public String getMessage(String clientName) {
        return "Server says hello to" + clientName;
    }


}
