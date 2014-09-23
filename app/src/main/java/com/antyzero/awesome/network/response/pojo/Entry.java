package com.antyzero.awesome.network.response.pojo;

import com.antyzero.awesome.domain.Constants;
import com.antyzero.awesome.domain.json.DateTimeFormatting;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Comparator;

/**
 * ...
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry implements Comparator<Entry> {

    // We will use this for better date-time management
    private DateTime dateTime = DateTime.now();

    @JsonProperty(required = true)
    private String title;

    @JsonProperty(required = true)
    private String link;

    @JsonProperty(required = true)
    private String date;

    @JsonProperty(required = true)
    private String time;

    @JsonProperty
    private String description;

    @JsonProperty
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

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Intercept date-time information at de-serialization time to update {@link #dateTime} object
     *
     * @param time required for creator
     */
    @JsonCreator
    @JsonProperty( value = "time" )
    private void deserializerTime( String time ) {
        this.time = time;

        LocalTime localTime = DateTimeFormatting.TIME.parseLocalTime( time );

        dateTime.withTime(
                localTime.getHourOfDay(),
                localTime.getMinuteOfHour(),
                0, 0 );
    }

    /**
     * Intercept date-time information at de-serialization time to update {@link #dateTime} object
     *
     * @param date required for creator
     */
    @JsonCreator
    @JsonProperty( value = "date" )
    private void deserializerDate( String date ) {
        this.date = date;

        // Forcing lower case for month name, joda does not accept capitals
        String dateLowerCase = date.toLowerCase( Constants.LOCALE_NORWAY );

        LocalDate localDate = DateTimeFormatting.DATE.parseLocalDate( dateLowerCase );

        dateTime.withDate(
                localDate.getYear(),
                localDate.getMonthOfYear(),
                localDate.getDayOfMonth() );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare( Entry lhs, Entry rhs ) {
        return (int) Math.signum( rhs.dateTime.getMillis() - lhs.dateTime.getMillis() );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( o == null || ((Object) this).getClass() != o.getClass() ) return false;

        Entry entry = (Entry) o;

        if( !date.equals( entry.date ) ) return false;
        if( !link.equals( entry.link ) ) return false;
        if( !time.equals( entry.time ) ) return false;
        if( !title.equals( entry.title ) ) return false;

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
