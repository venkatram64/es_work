package com.venkat.es.esdemo.model;

public class HostInfo {

    private String host;
    private String protocal;
    private int port;

    public HostInfo(String host, String protocal, int port) {
        this.host = host;
        this.protocal = protocal;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public String getProtocal() {
        return protocal;
    }

    public int getPort() {
        return port;
    }
}
