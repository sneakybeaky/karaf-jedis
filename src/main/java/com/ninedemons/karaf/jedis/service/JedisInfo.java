package com.ninedemons.karaf.jedis.service;

import java.io.PrintStream;

/**
 * Created by Jon Barber
 */
public class JedisInfo {
    private String host;
    private int port;
    private int timeout;
    private String jndiName;

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getPort() {
        return port;
    }

    public String getJndiName() {
        return jndiName;
    }

    @Override
    public String toString() {
        return "JedisInfo{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", timeout=" + timeout +
                ", jndiName='" + jndiName + '\'' +
                '}';
    }

    public void print(PrintStream out) {
        out.print(jndiName);
        out.print('\t');
        out.print("(Host='");
        out.print(host);
        out.print("', Port=");
        out.print(port);
        out.print(", Timeout=");
        out.print(timeout);
        out.println(" ms)");
    }
}
