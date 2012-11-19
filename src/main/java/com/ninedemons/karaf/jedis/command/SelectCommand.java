package com.ninedemons.karaf.jedis.command;

import com.ninedemons.karaf.jedis.service.JedisInfo;
import com.ninedemons.karaf.jedis.service.JedisSelect;
import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;
import org.osgi.framework.ServiceReference;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jon Barber
 */

@Command(scope = "jedis", name = "select", description = "Selects a Jedis pool")
public class SelectCommand implements Action {
    @Argument(index = 0, name = "name", required = false, description = "Jedis pool JNDI name", multiValued = false)
    String name;

    public void setJedisSelect(JedisSelect jedisSelect) {
        this.jedisSelect = jedisSelect;
    }

    private JedisSelect jedisSelect;


    @Override
    public Object execute(CommandSession session) throws Exception {
        if (name == null) {
            Map<String,JedisInfo> table = getDataSourcesInfo();
            System.out.println(table);
        } else {
            jedisSelect.selectDataSource(name);
        }
        return null;
    }

    public Map<String,JedisInfo> getDataSourcesInfo() {


        ServiceReference[] dataSources = jedisSelect.getDataSources();

        if (dataSources.length == 0) {
            return Collections.emptyMap();
        }

        Map<String,JedisInfo> jedisDataSources = new HashMap<String, JedisInfo>(dataSources.length);

        for (ServiceReference dataSource : dataSources) {
            JedisInfo jedisInfo = jedisSelect.getDataSourceInfo(dataSource);
            jedisDataSources.put(jedisInfo.getJndiName(),jedisInfo);
        }

        return jedisDataSources;
    }



}
