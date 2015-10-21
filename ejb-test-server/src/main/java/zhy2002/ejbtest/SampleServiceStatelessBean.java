package zhy2002.ejbtest;

import javax.ejb.Stateless;

/**
 *
 */
@Stateless(name = "SampleServiceStatelessBean")
public class SampleServiceStatelessBean implements SampleServiceRemote {

    public String getMessage(String clientName) {
        return "Server says hello to" + clientName;
    }

    public Integer getIdentityHashcode(){
        try {
            Thread.sleep(1000);
            return hashCode();
        }catch (InterruptedException ex){
            return 0;
        }
    }


}
