package org.brackets.checker.sberContest.service;

import org.brackets.checker.sberContest.serviceInterface.IsValidBrackets;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class BracketService implements IsValidBrackets {

    @Override
    public boolean isValidBrackets(String text) {
        Stack<Character> stack = new Stack<>();

        for (char c : text.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
