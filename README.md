# karaf-jedis

Jedis commands for the Karaf shell. Heavily influenced by the great [blog post](http://www.liquid-reality.de/x/LYBk) by Christian Schneider

# Installing the Jedis bundle

From the Karaf shell run

```
> install -s mvn:org.apache.commons/com.springsource.org.apache.commons.pool/1.5.3
> install -s mvn:redis.clients/com.ninedemons.jedis/2.1.0-SNAPSHOT
```

And then install the datasource Blueprint file from the datasource folder by copying to the Karaf deploy folder.

Then install the Karaf shell commands :

```
> install -s mvn:com.ninedemons.karaf.jedis/jedis-command/1.0.0-SNAPSHOT
```

You should now see new commands under the 'jedis' scope, e.g.

```
> help jedis
COMMANDS
        jedis:info    Gets info from the selected Redis server
        jedis:ping    Pings the selected Redis server
        jedis:jedisSelect  Selects a Jedis pool to use
```