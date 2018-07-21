package com.venkat.es.esdemo.service;

import com.venkat.es.esdemo.model.HostInfo;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//https://www.programcreek.com/java-api-examples/?api=org.elasticsearch.action.bulk.BulkRequestBuilder
//https://www.elastic.co/guide/en/kibana/current/tutorial-load-dataset.html
//https://amsterdam.luminis.eu/2016/07/07/the-new-elasticsearch-java-rest-client/

@Service
public class BulkUploadService {

    public HostInfo getHostInfo(String esPath){

        URL url = null;

        try {
            url = new URL(esPath);
        } catch (MalformedURLException me) {
            System.err.println("URL not present or malformed");
            return null;
        }

        return new HostInfo(url.getHost(),url.getProtocol(),url.getPort());
    }

    public void bulkLoad(String esURL,String indexName,String fileName){
        HostInfo info = this.getHostInfo(esURL);
        if(info == null){
            System.out.println("unable to load the data...");
            return;
        }

        HttpHost httpHost = new HttpHost(info.getHost(), info.getPort(), info.getProtocal());
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);

        try {

            Response response = restHighLevelClient.getLowLevelClient()
                    .performRequest("HEAD", "/" + indexName);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 404) {
                CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
                CreateIndexResponse  createIndexResponse = restHighLevelClient.indices().create(createIndexRequest);
            }

            BulkRequest bulkRequest = new BulkRequest();
            int count = 0;
            int batch = 500;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;

            while((line = bufferedReader.readLine())!=null) {
                bulkRequest.add(new IndexRequest(indexName,"current").source(line, XContentType.JSON));
                count++;
                if(count%batch == 0) {
                    BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest);
                    if (bulkResponse.hasFailures()) {
                        for (BulkItemResponse bulkItemResponse : bulkResponse) {
                            if (bulkItemResponse.isFailed()) {
                                BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                                System.out.println("Error " + failure.toString());
                            }
                        }
                    }
                    bulkRequest = new BulkRequest();
                }
            }

            if (bulkRequest.numberOfActions() > 0) {
                BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest);
                if (bulkResponse.hasFailures()) {
                    for (BulkItemResponse bulkItemResponse : bulkResponse) {
                        if (bulkItemResponse.isFailed()) {
                            BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                            System.out.println("Error " + failure.toString());
                        }
                    }
                }
            }

            System.out.println("Total uploaded: " + count);
            restHighLevelClient.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
