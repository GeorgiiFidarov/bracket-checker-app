package org.brackets.checker.sberContest.utilityClasses;

public class EmptyTextChecker {
    public static boolean Checker(String text) {
        return text==null||text.isEmpty()||text.isBlank();
    }
}
