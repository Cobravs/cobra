package com.cobra.es.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
public class Vo {
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Keyword)
    private String voName;

    @Override
    public String toString() {
        return "Vo{" +
                "id='" + id + '\'' +
                ", voName='" + voName + '\'' +
                '}';
    }
}
