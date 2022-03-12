package com.giulian.challenge.service;

import com.giulian.challenge.model.Text;
import com.giulian.challenge.payload.TextResponseDTO;

import java.util.List;

public interface ITextService {

    public Text createText(String text, Integer chars) ;
    List<Text> getTextByChars(int chars);
    public TextResponseDTO getTextById(Long textId);

}
