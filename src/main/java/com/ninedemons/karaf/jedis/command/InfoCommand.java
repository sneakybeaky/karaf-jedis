package com.ninedemons.karaf.jedis.command;

import com.ninedemons.karaf.jedis.service.JedisAccess;
import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

/**
 * Created by Jon Barber
 */
@Command(scope = "jedis", name = "info", description = "Gets info from the selected Redis server")
public class InfoCommand implements Action {

    private JedisAccess jedisAccess;

    public void setJedisAccess(JedisAccess jedisAccess) {
        this.jedisAccess = jedisAccess;
    }

    @Override
    public Object execute(CommandSession session) throws Exception {
        System.out.println(jedisAccess.info());
        return null;
    }
}
