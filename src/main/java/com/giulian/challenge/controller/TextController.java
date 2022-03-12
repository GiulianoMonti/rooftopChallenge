package com.giulian.challenge.controller;

import com.giulian.challenge.model.Text;
import com.giulian.challenge.payload.TextResponses;
import com.giulian.challenge.repository.TextRepository;
import com.giulian.challenge.service.ITextService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TextController {

    private final ITextService textService;
    private final TextRepository textRepository;

    public TextController(ITextService textService, TextRepository textRepository) {
        this.textService = textService;
        this.textRepository = textRepository;
    }

    @PostMapping("/text")
    public ResponseEntity<TextResponses> addText(@RequestBody Text text) {

        Text texts = textService.createText(text.getText()
                , text.getChars());


        return new ResponseEntity<>(new TextResponses(texts.getId(),
                "/text/"+texts.getId()), HttpStatus.OK);
    }

//    @GetMapping("text/{chars}")
//    public ResponseEntity<?> getTextByChars(@PathVariable int chars) {
//        // TODO get by hash
//        return new ResponseEntity<>(textService.getTextByChars(chars), HttpStatus.OK);
//    }

    @GetMapping("text/{id}")
    public ResponseEntity<?> getTextById(@PathVariable long id) {

        // TODO get by hash

        return new ResponseEntity<>(textService.getTextById(id), HttpStatus.OK);
    }
}








