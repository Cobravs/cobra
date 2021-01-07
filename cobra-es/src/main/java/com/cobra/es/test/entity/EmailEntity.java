package com.cobra.es.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

//@Document(indexName = "mail_#{ T(com.cobra.es.config.EsConfig).getSuffix() }", createIndex = false)
@Getter
@Setter
public class EmailEntity {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date date;
}
