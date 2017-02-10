package com.vzwcorp.pricinglab.config;

//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;
//import org.springframework.cloud.aws.jdbc.config.annotation.RdsInstanceConfigurer;
//import org.springframework.cloud.aws.jdbc.datasource.DataSourceFactory;
//import org.springframework.cloud.aws.jdbc.datasource.TomcatJdbcDataSourceFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Verizon Wireless - Pricing lab Team. on 8/19/2016.
 */
//@Component
//@EnableTransactionManagement
//@EnableRdsInstance(databaseName = "plab", dbInstanceIdentifier = "plab", password = "Prior#12s", readReplicaSupport = true)
public class AWS_RDS_DBConfig_east_1 {

//    private static Logger logger = Logger.getLogger(AWS_RDS_DBConfig_east_1.class);
//
//    @Value("${cloud.aws.datasource.1.name}")
//    private String awsRdsDatasourceName;
//
//    @Value("${cloud.aws.datasource.1.initial-size}")
//    private Integer awsRdsDatasourceInitialSize;
//
//    @Value("${cloud.aws.datasource.1.validation-query}")
//    private String awsRdsDatasourceValidationQuery;
//
//    @Bean
//    public RdsInstanceConfigurer instanceConfigurer() {
//        return new RdsInstanceConfigurer() {
//            @Override
//            public DataSourceFactory getDataSourceFactory() {
//                TomcatJdbcDataSourceFactory dataSourceFactory = new TomcatJdbcDataSourceFactory();
//                dataSourceFactory.setInitialSize(awsRdsDatasourceInitialSize);
//                dataSourceFactory.setName(awsRdsDatasourceName);
//                dataSourceFactory.setValidationQuery(awsRdsDatasourceValidationQuery);
//                return dataSourceFactory;
//            }
//        };
//    }
}
