package com.oracle.mx.openaq.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Source {
    private String id;
    private String url;
    private String name;
}
