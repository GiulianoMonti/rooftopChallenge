package com.giulian.challenge.controller;

import com.giulian.challenge.exception.ResourceNotFoundException;
import com.giulian.challenge.model.Text;
import com.giulian.challenge.payload.PageDto;
import com.giulian.challenge.payload.TextResponseDTO;
import com.giulian.challenge.payload.TextResponses;
import com.giulian.challenge.repository.TextRepository;
import com.giulian.challenge.service.ITextService;
import com.giulian.challenge.utils.UtilPagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/text")
public class TextController {

    private final ITextService textService;
    private final TextRepository textRepository;
    private UtilPagination utilPagination;

    public TextController(ITextService textService, TextRepository textRepository, UtilPagination utilPagination) {
        this.textService = textService;
        this.textRepository = textRepository;
        this.utilPagination = utilPagination;
    }

    @PostMapping
    public ResponseEntity<TextResponses> addText(@RequestBody Text text) {

        Text texts = textService.createText(text.getText()
                , text.getChars());


        return new ResponseEntity<>(new TextResponses(texts.getId(),
                "/text/" + texts.getId()), HttpStatus.OK);
    }

    @GetMapping
    public List<TextResponseDTO> getAllTexts() {

        return textService.findAllTexts();


    }


//    @GetMapping("text/{chars}")
//    public ResponseEntity<?> getTextByChars(@PathVariable int chars) {
//        // TODO get by hash
//        return new ResponseEntity<>(textService.getTextByChars(chars), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTextById(@PathVariable long id) {

        // TODO get by hash

        try {
            return new ResponseEntity<>(textService.getTextById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTextById(@PathVariable Long id) {
        textService.deleteText(id);
        return new ResponseEntity<>
                ("", HttpStatus.OK);
    }

    @GetMapping("all")
    ResponseEntity<?> getCategoriesPageable(@PageableDefault(sort = "id",
            direction = Sort.Direction.ASC, size = 2) Pageable pageable,
                                            @RequestParam(value = "page", defaultValue = "0")
                                                    int page, HttpServletRequest request) {
        try {

            Page<TextResponseDTO> result = textService.getPageableText(pageable);
            Map<String, String> links = utilPagination.linksPagination(request, result);
            if (page >= result.getTotalPages() | page < 0)
                return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.ACCEPTED);

            PageDto<TextResponseDTO> response = new PageDto<>();
            response.setContent(result.getContent());
            response.setLinks(links);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}








