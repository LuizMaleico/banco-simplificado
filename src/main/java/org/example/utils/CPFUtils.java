package org.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPFUtils {
    private static final String REGEX_CPF = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    private static final String REGEX_CNPJ = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}";

    public static boolean isCPF(String cpf) {
        Pattern pattern = Pattern.compile(REGEX_CPF);
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }

    public static boolean isCNPJ(String cnpj) {
        Pattern pattern = Pattern.compile(REGEX_CNPJ);
        Matcher matcher = pattern.matcher(cnpj);
        return matcher.matches();
    }

}
