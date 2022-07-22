package com.company.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan({"com.company.*"})
@EnableTransactionManagement
public class ContextConfiguration {

}
