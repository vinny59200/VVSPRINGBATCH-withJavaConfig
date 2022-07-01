package com.vv.SpringBatchWithJavaConfig.configuration;

import com.vv.SpringBatchWithJavaConfig.listener.SpringBatchJobCompletionListener;
import com.vv.SpringBatchWithJavaConfig.processor.SBProcessor;
import com.vv.SpringBatchWithJavaConfig.reader.SBReader;
import com.vv.SpringBatchWithJavaConfig.writer.SBWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("vvjob")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(orderStep1()).end().build();
    }

    @Bean
    public Step orderStep1() {
        return stepBuilderFactory.get("step1"). < String, String > chunk(1)
                .reader(new SBReader()).processor(new SBProcessor())
                .writer(new SBWriter()).build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new SpringBatchJobCompletionListener();
    }
}
