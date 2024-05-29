package com.solvd.service.input;

import com.solvd.constant.InputValidationRegexType;
import com.solvd.entities.City;
import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.solvd.common.context.DynamicContext.getAllAvailableCities;

@UtilityClass
public class InputValidatorService {

    public static boolean isInputValid(String input, InputValidationRegexType regexType) {
        Pattern pattern = Pattern.compile(regexType.getRegex(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean isCityInputValid(String input) {
        return getAllAvailableCities().stream().map(City::getName).anyMatch(input::equalsIgnoreCase);
    }
}
