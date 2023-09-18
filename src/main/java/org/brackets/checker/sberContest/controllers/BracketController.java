package org.brackets.checker.sberContest.controllers;

import org.brackets.checker.sberContest.controllersInterface.TextReceiver;
import org.brackets.checker.sberContest.request.CheckBracketsRequest;
import org.brackets.checker.sberContest.response.IsCorrectResponse;
import org.brackets.checker.sberContest.service.BracketService;
import org.brackets.checker.sberContest.utilityClasses.EmptyTextChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BracketController implements TextReceiver {

    private final BracketService bracketService;

    @Autowired
    public BracketController(BracketService bracketService) {
        this.bracketService = bracketService;
    }

    @Override
    @PostMapping("/checkBrackets")
    public ResponseEntity<Object> checkBrackets(
            @RequestBody CheckBracketsRequest requestBody){

        String text = requestBody.text();

        if (EmptyTextChecker.Checker(text)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        try {
            boolean isValid = bracketService.isValidBrackets(text);
            return ResponseEntity.status(HttpStatus.OK).body(new IsCorrectResponse(isValid));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
