package com.cobra.es.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
public class ChildVo {
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Keyword)
    private String childName;

    @Override
    public String toString() {
        return "ChildVo{" +
                "id='" + id + '\'' +
                ", childName='" + childName + '\'' +
                '}';
    }
}
