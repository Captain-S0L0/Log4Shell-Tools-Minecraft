import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class SystemOut implements ObjectFactory {

    public SystemOut() {
        System.out.println("Log4Shell says hello from init!");
    }

    static {
        System.out.println("Log4Shell says hello from static!");
    }

    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        System.out.println("Log4Shell says hello from getObjectInstance!");
        return "Log4Shell";
    }
}
