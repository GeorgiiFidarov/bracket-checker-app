package org.brackets.checker.sberContest.request;

public record CheckBracketsRequest(String text){
    @Override
    public String text() {
        return text;
    }
}
