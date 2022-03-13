package com.giulian.challenge.payload;

import com.giulian.challenge.model.Text;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TextResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String hash;
    private int chars;
    @Lob
    private LinkedHashMap<String, Integer> result;

    public TextResponseDTO(Text text) {
    }

    public static TextResponseDTO buildResponse(Text text) {

        return TextResponseDTO.builder()
                .id(text.getId())
                .hash(text.getHash())
                .chars(text.getChars())
                .result(text.getResult())
                .build();

    }
}
