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

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (!date.equals(entry.date)) return false;
        if (!link.equals(entry.link)) return false;
        if (!time.equals(entry.time)) return false;
        if (!title.equals(entry.title)) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + link.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }
}
