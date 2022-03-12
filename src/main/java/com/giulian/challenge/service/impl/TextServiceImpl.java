package com.giulian.challenge.service.impl;

import com.giulian.challenge.model.Text;
import com.giulian.challenge.payload.TextResponseDTO;
import com.giulian.challenge.repository.TextRepository;
import com.giulian.challenge.service.ITextService;
import com.giulian.challenge.utils.HashData;
import com.giulian.challenge.utils.SyllablesCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TextServiceImpl implements ITextService {

    @Autowired
    TextRepository textRepository;

    @Autowired
    SyllablesCounter syllablesCounter;
    @Autowired
    HashData hashData;

    public Text createText(String text, Integer chars) {

        Text newText = new Text();
        try {
            newText = textRepository.findByHash(hashData.getHash(text.getBytes(),chars.byteValue()
                    , "MD5")).orElseThrow(() -> new Exception("REPETIDO"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        String checkHash ="";
        try {



            newText.setText(text);
//            log.info(" cdalkfjao "+ textRepository.findByHash(newText.getHash()));

            newText.setHash(hashData.getHash(text.getBytes(),chars.byteValue()
                    , "MD5"));

//            if(textRepository.findByHash(newText.getHash()).equals(hashData.getHash(text.getBytes(),chars.byteValue()
//                    , "MD5"))){
//                System.out.println("HOLAAAAAA");
//            }

            newText.setChars(chars);
            newText.setMappedText(syllablesCounter.countSyllables(text, chars));
            textRepository.save(newText);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newText;
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
                        new ExpressionException("not"));

        return TextResponseDTO.buildResponse(text);

    }


}
