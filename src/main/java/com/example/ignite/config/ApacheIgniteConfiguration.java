package com.example.ignite.config;

import com.example.ignite.entity.Employee;
import com.example.ignite.entity.Item;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class ApacheIgniteConfiguration {
    @Bean
    public Ignite igniteInstance(@Autowired IgniteConfiguration igniteConfiguration) {
        igniteConfiguration.setAutoActivationEnabled(true);
        igniteConfiguration.setActiveOnStart(true);
        Ignite ignite = Ignition.getOrStart(igniteConfiguration);
        ignite.active(true);
        return ignite;
    }

    @Bean(name = "igniteConfiguration")
    public IgniteConfiguration igniteConfiguration() {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setIgniteInstanceName("igniteTest");
        igniteConfiguration.setClientMode(false);
        igniteConfiguration.setPeerClassLoadingEnabled(true);
        igniteConfiguration.setLocalHost("127.0.0.1");

        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
        tcpDiscoverySpi.setIpFinder(ipFinder);
        tcpDiscoverySpi.setLocalPort(47500);
        tcpDiscoverySpi.setLocalPortRange(9);
        igniteConfiguration.setDiscoverySpi(tcpDiscoverySpi);

        TcpCommunicationSpi communicationSpi = new TcpCommunicationSpi();
        communicationSpi.setLocalAddress("localhost");
        communicationSpi.setLocalPort(48100);
        communicationSpi.setSlowClientQueueLimit(1000);
        igniteConfiguration.setCommunicationSpi(communicationSpi);


        DataStorageConfiguration storageConfiguration  = new DataStorageConfiguration();
        DataRegionConfiguration regionConfiguration = new DataRegionConfiguration();
        regionConfiguration.setPersistenceEnabled(true);
        regionConfiguration.setName("igniteRegion");

        storageConfiguration.setDefaultDataRegionConfiguration(regionConfiguration);
        igniteConfiguration.setDataStorageConfiguration(storageConfiguration);
        igniteConfiguration.setCacheConfiguration(cacheConfiguration());

        return igniteConfiguration;

    }

    @Bean(name = "cacheConfiguration")
    public CacheConfiguration[] cacheConfiguration() {
        List<CacheConfiguration> cacheConfigurations = new ArrayList<>();
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setAtomicityMode(CacheAtomicityMode.ATOMIC);
        cacheConfiguration.setCacheMode(CacheMode.REPLICATED);
        cacheConfiguration.setName("employee");
        cacheConfiguration.setWriteThrough(false);
        cacheConfiguration.setReadThrough(false);
        cacheConfiguration.setWriteBehindEnabled(false);
        cacheConfiguration.setBackups(1);
        cacheConfiguration.setStatisticsEnabled(true);
        cacheConfiguration.setIndexedTypes(Long.class,Employee.class);

        CacheConfiguration cacheConfiguration1 = new CacheConfiguration();
        cacheConfiguration1.setAtomicityMode(CacheAtomicityMode.ATOMIC);
        cacheConfiguration1.setCacheMode(CacheMode.REPLICATED);
        cacheConfiguration1.setName("itemCache");
        cacheConfiguration1.setWriteThrough(false);
        cacheConfiguration1.setReadThrough(false);
        cacheConfiguration1.setWriteBehindEnabled(false);
        cacheConfiguration1.setBackups(1);
        cacheConfiguration1.setStatisticsEnabled(true);
        cacheConfiguration1.setIndexedTypes(String.class,Item.class);

        cacheConfigurations.add(cacheConfiguration);
        cacheConfigurations.add(cacheConfiguration1);

        return cacheConfigurations.toArray(new CacheConfiguration[cacheConfigurations.size()]);
    }
}
