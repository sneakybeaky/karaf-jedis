package com.ninedemons.karaf.jedis.command;

import com.ninedemons.karaf.jedis.service.JedisSelect;
import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

import java.util.List;

/**
 * Created by Jon Barber
 */
public class JedisNameCompleter implements Completer {

    private JedisSelect jedisSelect;

    public void setJedisSelect(JedisSelect jedisSelect) {
        this.jedisSelect = jedisSelect;
    }

    @Override
    public int complete(String buffer, int cursor, List<String> candidates) {
        StringsCompleter delegate = new StringsCompleter(jedisSelect.getDataSourceNames());
        return delegate.complete(buffer, cursor, candidates);
    }
}
