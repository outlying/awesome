package com.antyzero.awesome.network.response.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ...
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class Entry {

    @JsonProperty( required = true )
    private String title;

    @JsonProperty( required = true )
    private String link;

    @JsonProperty( required = true )
    private String date;

    @JsonProperty( required = true )
    private String time;

    private String description;

    private String category;
}
