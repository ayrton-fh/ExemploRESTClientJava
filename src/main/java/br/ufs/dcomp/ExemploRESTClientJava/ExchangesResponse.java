package br.ufs.dcomp.ExemploRESTClientJava;

public class ExchangesResponse {
    String source;
    String vhost;
    String destination;
    String destination_type;
    String routing_key;
    Object arguments;
    String properties_key;
    
    public ExchangesResponse() {
        this.source = "";
        this.vhost = "%2f";
        this.destination = "";
        this.destination_type = "";
        this.routing_key = "";
        this.arguments = new Object();
        this.properties_key = "";
    }
}