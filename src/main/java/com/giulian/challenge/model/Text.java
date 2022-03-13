package com.giulian.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE text SET deleted = true WHERE id=?")
@Where(clause = " deleted = false ")
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String hash;
    private String text;
    private int chars;
    private Boolean deleted = Boolean.FALSE;

    @Lob
    private LinkedHashMap<String,Integer> mappedText ;

}
