package com.vv.SpringBatchWithJavaConfig.processor;

import org.springframework.batch.item.ItemProcessor;
public class SBProcessor implements ItemProcessor < String, String > {

    @Override
    public String process(String data) throws Exception {
        return data.toUpperCase();
    }

}
