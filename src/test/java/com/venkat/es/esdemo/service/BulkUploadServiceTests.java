package com.venkat.es.esdemo.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BulkUploadServiceTests {

    @Autowired
    BulkUploadService bulkUploadService;

    @Test
    public void bulkLoad(){
        bulkUploadService.bulkLoad("http://localhost:9200","shakespeare","shakespeare_6.0.json");
        assert(true);
    }
}
