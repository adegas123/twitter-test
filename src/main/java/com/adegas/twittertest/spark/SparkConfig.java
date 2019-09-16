package com.adegas.twittertest.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
 
    @Value("${spark.app.name}") private String appName;
    
    @Value("${spark.master}") private String masterUri;
 
    @Bean
    public SparkConf conf() {
        return new SparkConf().setAppName(appName).setMaster(masterUri).set("spark.testing.memory", "4718592000");
    }
 
    @Bean
    public JavaSparkContext sc() {
        return new JavaSparkContext(conf());
    }
 
}
