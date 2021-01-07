package com.cobra.es.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Getter
@Setter
@Document(indexName = "simple")
public class SimpleEntity {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date date;
}
