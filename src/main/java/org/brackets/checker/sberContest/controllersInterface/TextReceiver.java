package org.brackets.checker.sberContest.controllersInterface;


import org.brackets.checker.sberContest.request.CheckBracketsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface TextReceiver {
    @PostMapping("/checkBrackets")
    ResponseEntity<Object> checkBrackets(@RequestBody CheckBracketsRequest requestBody);
}
