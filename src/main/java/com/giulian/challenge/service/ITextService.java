package com.giulian.challenge.service;

import com.giulian.challenge.model.Text;
import com.giulian.challenge.payload.TextResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITextService {

    Text createText(String text, Integer chars) ;
    List<Text> getTextByChars(int chars);
    TextResponseDTO getTextById(Long textId);
    List<TextResponseDTO> findAllTexts();

    void deleteText(Long id);

    Page<Text> getPageableText(Pageable pageable);
}
