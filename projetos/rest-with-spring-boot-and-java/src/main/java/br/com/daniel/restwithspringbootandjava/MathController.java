package br.com.daniel.restwithspringbootandjava;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.daniel.restwithspringbootandjava.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {

    @GetMapping(path = "sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @GetMapping(path = "subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @GetMapping(path = "multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @GetMapping(path = "division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        if(convertToDouble(numberTwo).equals(0D)) 
            throw new UnsupportedMathOperationException("Please, the second number not be zero (0)");
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @GetMapping(path = "mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    @GetMapping(path = "squareRoot/{numberOne}")
    public Double squareRoot(
            @PathVariable(value = "numberOne") String numberOne) {
        if (!isNumeric(numberOne)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return Math.sqrt(convertToDouble(numberOne));
    }

    private Double convertToDouble(String strNumber) {
        if (strNumber == null)
            return 0D;
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number))
            return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null)
            return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
