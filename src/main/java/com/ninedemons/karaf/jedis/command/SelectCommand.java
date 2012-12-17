package com.ninedemons.karaf.jedis.command;

import com.ninedemons.karaf.jedis.service.JedisInfo;
import com.ninedemons.karaf.jedis.service.JedisSelect;
import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;
import org.osgi.framework.ServiceReference;

import java.util.*;

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
            List<JedisInfo> jedisInfoList = getDataSourcesInfo();

            for (JedisInfo info : jedisInfoList) {
                info.print(System.out);
            }

        } else {
            jedisSelect.selectDataSource(name);
        }
        return null;
    }

    public List<JedisInfo> getDataSourcesInfo() {

        ServiceReference[] dataSources = jedisSelect.getDataSources();

        if (dataSources.length == 0) {
            return Collections.emptyList();
        }

        List<JedisInfo> jedisDataSources = new ArrayList<JedisInfo>(dataSources.length);

        for (ServiceReference dataSource : dataSources) {
            JedisInfo jedisInfo = jedisSelect.getDataSourceInfo(dataSource);
            jedisDataSources.add(jedisInfo);
        }

        return jedisDataSources;
    }



}
