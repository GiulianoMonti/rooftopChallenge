package com.giulian.challenge.service.impl;

import com.giulian.challenge.exception.ResourceNotFoundException;
import com.giulian.challenge.model.Text;
import com.giulian.challenge.payload.TextResponseDTO;
import com.giulian.challenge.repository.TextRepository;
import com.giulian.challenge.service.ITextService;
import com.giulian.challenge.utils.HashData;
import com.giulian.challenge.utils.SyllablesCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TextServiceImpl implements ITextService {

    @Autowired
    TextRepository textRepository;

    @Autowired
    MessageSource messageSource;

    @Autowired
    SyllablesCounter syllablesCounter;

    @Autowired
    HashData hashData;

    // TODO error messages en error.properties (?)
    @Value("error.category.id.not.found")
    private String idNotFoundMessage;

    public Text createText(String text, Integer chars) {

        if (text.length() <= chars || chars < 2) {
            chars = 2;
        }

        Text newText = new Text();
        try {
            newText = textRepository.findByHash(hashData.getHash(text.getBytes(), chars.byteValue()
                    , "MD5")).orElseThrow(() -> new Exception("REPETIDO"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {


            newText.setText(text);
//            log.info(" HASH HASH HASH HASH HASH "+ textRepository.findByHash(newText.getHash()));

            newText.setHash(hashData.getHash(text.getBytes(), chars.byteValue()
                    , "MD5"));

            newText.setChars(chars);
            newText.setMappedText(syllablesCounter.countSyllables(text, chars));
            textRepository.save(newText);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newText;
    }

    @Override
    public List<Text> findAllTexts() {
        return textRepository.findAll();
    }


    @Override
    public List<Text> getTextByChars(int chars) {

        List<Text> texts = textRepository.findByChars(chars);
        return new ArrayList<>(texts);

    }

    @Override
    public TextResponseDTO getTextById(Long textId) {

        Text text = textRepository.findById(textId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(textId));

        return TextResponseDTO.buildResponse(text);

    }

    @Override
    public void deleteText(Long id) {
        textRepository.delete(textRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException(1)));

    }


}
