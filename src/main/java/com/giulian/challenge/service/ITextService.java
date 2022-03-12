package com.giulian.challenge.service;

import com.giulian.challenge.model.Text;
import com.giulian.challenge.payload.TextResponseDTO;

public interface ITextService {

    public Text createText(String text, Integer chars) ;
    public TextResponseDTO getTextById(Long textId);

}
