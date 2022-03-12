package com.giulian.challenge.controller;

import com.giulian.challenge.model.Text;
import com.giulian.challenge.service.ITextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TextController {

    private final ITextService textService;

    public TextController(ITextService textService) {
        this.textService = textService;
    }

    @PostMapping
    public ResponseEntity<?> addText( @RequestBody Text text) {

        return new ResponseEntity<>(textService.createText(text.getText()
                ,text.getChars()), HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<?> getText( @RequestBody Text text) {
        //TODO get by hash
//        return new ResponseEntity<>(textService.createText(text.getText()
//                ,text.getChars()), HttpStatus.CREATED);
//    }







}
