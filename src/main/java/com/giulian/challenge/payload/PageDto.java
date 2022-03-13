package com.giulian.challenge.payload;

import com.giulian.challenge.model.Text;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T>{
    private List<TextResponseDTO> content;
    private Map<String,String> links = new HashMap<>();
}

