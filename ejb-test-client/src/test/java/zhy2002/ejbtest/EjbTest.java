package zhy2002.ejbtest;


import org.junit.Before;
import org.junit.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Note that these test may depend on timing and server implementation details.
 * So if they fail it does not necessarily mean the test statement is false.
 */
public class EjbTest {

    private static Logger logger = Logger.getLogger("EjbTest");

    private InitialContext initialContext;

    @Before
    public void beforeMethod(){
        try {
            initialContext = new InitialContext();
        }catch (NamingException ex){

            logger.log(Level.SEVERE, "Failed to create InitialContext, all remaining tests are aborted. Please ensure the EJB server project is started.");
            System.exit(1);
        }
    }

    private static final String INTERFACE_SAMPLE_SERVICE_REMOTE = "ejb_test_server_EJB_exploded/SampleServiceStatelessBean!zhy2002.ejbtest.SampleServiceRemote";
    @Test
    public void ifAStatelessBeanIsBeingUsedAnotherOneWillBeCreated() throws NamingException, InterruptedException, ExecutionException {
        Callable<Integer[]> printHashCode = () -> {
            try {
                SampleServiceRemote remoteBean = (SampleServiceRemote) initialContext.lookup(INTERFACE_SAMPLE_SERVICE_REMOTE);

                Integer hashCode1 = remoteBean.getIdentityHashcode();
                System.out.println(Thread.currentThread().getId() + ":" + hashCode1 + " | " + new Date());
                Integer hashCode2 = remoteBean.getIdentityHashcode();
                System.out.println(Thread.currentThread().getId() + ":" + hashCode2 + " | " + new Date());
                return new Integer[]{hashCode1, hashCode2};

            }catch (NamingException ex){
                throw new RuntimeException(ex);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletionService<Integer[]> completionService = new ExecutorCompletionService<>(executor);
        completionService.submit(printHashCode);
        completionService.submit(printHashCode);

        Future<Integer[]> result1 = completionService.take();
        Future<Integer[]> result2 = completionService.take();

        assertThat(result1.get()[0], not(equalTo(result2.get()[0])));

    }


    @Test
    public void methodCallsFromTheSameProxyInstanceMayBeHandledByDifferentStatelessInstances() throws NamingException, InterruptedException {
        SampleServiceRemote remoteBean = (SampleServiceRemote) initialContext.lookup(INTERFACE_SAMPLE_SERVICE_REMOTE);
        Integer code1 = remoteBean.getIdentityHashcode();
        System.out.println("Code 1 = " + code1);
        Thread thread = new Thread(()->{
            try {
                SampleServiceRemote bean = (SampleServiceRemote) initialContext.lookup(INTERFACE_SAMPLE_SERVICE_REMOTE);
                Integer code = bean.getIdentityHashcode(); //grab the released server instance
                System.out.println("Other thread: " + code);
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        });
        thread.start();
        Integer code2 = remoteBean.getIdentityHashcode(); //call on the other thread is not finished yet
        System.out.println("Code 2 = " + code2);
        thread.join();
        assertThat(code1, not(equalTo(code2)));

    }
}
