package com.giulian.challenge.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextResponses {

    private long id;
    private String url;

    public TextResponses(long id, String url) {
        this.id = id;
        this.url = url;
    }
}
