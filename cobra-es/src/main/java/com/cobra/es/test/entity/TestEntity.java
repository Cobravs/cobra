package com.cobra.es.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Document(indexName = "#{ T(com.cobra.es.config.EsConfig).getTestIndexName() }", createIndex = false)
public class TestEntity {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Keyword)
    private String value;
    @Field(type = FieldType.Keyword)
    private String callId;
    @Field(type = FieldType.Object)
    private ChildVo childVo;
    @Field(type = FieldType.Object)
    private List<Vo> voList;
    @Field(type = FieldType.Keyword)
    private String newKey;
    @Field(type = FieldType.Keyword)
    private String field2;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public TestEntity() {
    }

    public TestEntity(String id, String name, String value, String callId, ChildVo childVo, List<Vo> voList, String newKey,
                      String field2, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.callId = callId;
        this.childVo = childVo;
        this.voList = voList;
        this.newKey = newKey;
        this.field2 = field2;
        this.date = date;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", callId='" + callId + '\'' +
                ", childVo=" + childVo +
                ", voList=" + voList +
                ", newKey='" + newKey + '\'' +
                ", field2='" + field2 + '\'' +
                ", date=" + date +
                '}';
    }
}
