package com.smirnov.formula1.validator;

public class ValidatorImpl implements Validator {

    @Override
    public void validate(String abbreviations, String startTime, String endTime) {

        if (abbreviations == null || startTime == null || endTime == null) {
            throw new IllegalArgumentException(checkForNull(abbreviations, startTime, endTime) + " path is null");
        }

        if (abbreviations.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new IllegalArgumentException(checkForEmptyString(abbreviations, startTime, endTime) + " path is empty");
        }

    }

    private String checkForNull(String filename1, String filename2, String filename3) {
        String result = null;

        if (filename1 == null) {
            result = "abbreviations";
        } else if (filename2 == null) {
            result = "start";
        } else if (filename3 == null) {
            result = "end";
        }

        return result;
    }

    private String checkForEmptyString(String filename1, String filename2, String filename3) {
        String result = null;

        if (filename1.isEmpty()) {
            result = "abbreviations";
        } else if (filename2.isEmpty()) {
            result = "start";
        } else if (filename3.isEmpty()) {
            result = "end";
        }

        return result;
    }

}
