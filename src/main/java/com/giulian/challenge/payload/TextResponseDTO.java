package com.giulian.challenge.payload;

import com.giulian.challenge.model.Text;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @ElementCollection
    private Map<String, Integer> result;

    public static TextResponseDTO buildResponse(Text text) {

        return TextResponseDTO.builder()
                .id(text.getId())
                .hash(text.getHash())
                .chars(text.getChars())
                .result(text.getMappedText())
                .build();

    }
}
