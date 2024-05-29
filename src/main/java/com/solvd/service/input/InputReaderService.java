package com.solvd.service.input;

import com.solvd.constant.InputValidationRegexType;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

import static com.solvd.constant.Message.INPUT_ERROR_MESSAGE;
import static com.solvd.service.input.InputValidatorService.isInputValid;

@UtilityClass
@Log4j2
public class InputReaderService {
    private static final Scanner scanner = new Scanner(System.in);

    public static String scanAndValidateUserInput(InputValidationRegexType regexType) {
        String input = scanner.nextLine();
        while (!isInputValid(input, regexType)) {
            log.error(INPUT_ERROR_MESSAGE);
            input = scanner.nextLine();
        }
        return input;
    }
}
