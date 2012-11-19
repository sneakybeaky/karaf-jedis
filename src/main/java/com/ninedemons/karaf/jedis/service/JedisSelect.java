package com.ninedemons.karaf.jedis.service;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Jon Barber
 */
public class JedisSelect {

    public void setContext(BundleContext context) {
        this.context = context;
    }

    private BundleContext context;


    public ServiceReference[] getDataSources() {
        try {
            ServiceReference[] dsRefs = context.getServiceReferences(JedisPool.class.getName(), null);
            if (dsRefs == null) {
                dsRefs = new ServiceReference[]{};
            }
            return dsRefs;
        } catch (InvalidSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public JedisInfo getDataSourceInfo(ServiceReference reference) {

        String jndiName = getJndiName(reference);
        JedisPool jedisPool = (JedisPool) context.getService(reference);
        Jedis jedis = jedisPool.getResource();
        Client client = jedis.getClient();

        JedisInfo info = new JedisInfo();

        info.setJndiName(jndiName);
        info.setHost(client.getHost());
        info.setPort(client.getPort());
        info.setTimeout(client.getTimeout());

        return info;


    }

    private String getJndiName(ServiceReference ref) {
        return (String)ref.getProperty("osgi.jndi.service.name");
    }

    public static void selectDataSource(String name) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
