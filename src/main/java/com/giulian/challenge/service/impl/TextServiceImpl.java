package com.giulian.challenge.service.impl;

import com.giulian.challenge.model.Text;
import com.giulian.challenge.repository.TextRepository;
import com.giulian.challenge.service.ITextService;
import com.giulian.challenge.utils.SyllablesCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TextServiceImpl implements ITextService {

    @Autowired
    TextRepository textRepository;

    @Autowired
    SyllablesCounter syllablesCounter;

    public Text createText(String text, Integer chars) {

        Text newText = new Text();
        try {

            newText.setText(text);
            newText.setHash(syllablesCounter.getHash(text.getBytes()
                    ,"MD5"));
            newText.setChars(chars);
            newText.setMappedText(syllablesCounter.countSyllables(text,chars));
            textRepository.save(newText);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newText;
    }

}
