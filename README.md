# karaf-jedis

Jedis commands for the Karaf shell

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
