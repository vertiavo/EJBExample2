package wipb.jee.clientdemo.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

/**
 * Created by vertiavo on 14.04.17.
 */
public class Interceptor {
    @AroundInvoke
    Object logTime(InvocationContext ic) throws Exception {
        long entryTime = System.currentTimeMillis();
        Object res = null;

        try {
            res = ic.proceed();
        } finally {
            Logger.getLogger(ic.getTarget()
                    .getClass()
                    .getName())
                    .fine(String.valueOf(System.currentTimeMillis()-entryTime));
        }
        return res;
    }
}
