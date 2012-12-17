package com.ninedemons.karaf.jedis.service;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jon Barber
 */
public class JedisSelect {

    public void setContext(BundleContext context) {
        this.context = context;
    }

    public void setJedisAccess(JedisAccess jedisAccess) {
        this.jedisAccess = jedisAccess;
    }

    private BundleContext context;
    private JedisAccess jedisAccess;


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

    public Collection<String> getDataSourceNames() {
        ServiceReference[] dataSources = getDataSources();
        List<String> dsNames = new ArrayList<String>();
        for (ServiceReference ref : dataSources) {
            dsNames.add(getJndiName(ref));
        }
        return dsNames;
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
        info.setSelected(jedisPool == jedisAccess.getJedisPool());

        return info;


    }

    private String getJndiName(ServiceReference ref) {
        return (String)ref.getProperty("osgi.jndi.service.name");
    }

    public void selectDataSource(String name) {
        ServiceReference[] dataSources = getDataSources();
        for (ServiceReference ref : dataSources) {
            String jndiName = getJndiName(ref);
            if (name.equals(jndiName)) {
                JedisPool jedisPool = (JedisPool)context.getService(ref);
                jedisAccess.setPool(jedisPool);
                return;
            }
        }
        throw new RuntimeException("No Jedis Pool with name " + name + " found");
    }
}
