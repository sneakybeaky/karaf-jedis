# Installing DataSources for Jedis

This folder contains a sample Blueprint config to install a Jedis datasource that connects to a Redis server running
 on localhost port 6379

Copy to the Karaf deploy folder to install

# Test

> list

The table should show your Jedis "bundles" as "Active" and "Created". If not check the log with:

> log:display


After the datasource is installed you can use the jedis-commands to check if it works.
