import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Converter converter = new Converter();

        Scanner in = new Scanner(System.in);
        String inputString = in.nextLine();

        String operation = "";

        int a = 0;
        int b = 0;

        String aCh = "";
        String bCh = "";

        boolean operationIsEmpty = true;
        boolean isRomanGlobal = false;

        if (!checkForThreeOperands(inputString)) {
            for(char ch : inputString.toCharArray()) {
                if (Character.isDigit(ch)) {
                    if (operation.isEmpty()) {
                        aCh += ch;
                    } else {
                        bCh += ch;
                    }
                } else if (ch == '+' && operationIsEmpty) {
                    operation = String.valueOf(ch);
                    operationIsEmpty = false;
                } else if (ch == '-' && operationIsEmpty) {
                    operation = String.valueOf(ch);
                    operationIsEmpty = false;
                } else if (ch == '/' && operationIsEmpty) {
                    operation = String.valueOf(ch);
                    operationIsEmpty = false;
                } else if (ch == '*' && operationIsEmpty) {
                    operation = String.valueOf(ch);
                    operationIsEmpty = false;
                } else {
                    boolean isRoman = converter.isRoman(String.valueOf(ch));

                    if (isRoman && operation.isEmpty()) {
                        aCh += ch;
                        isRomanGlobal = true;
                    } else if (isRoman)  {
                        bCh += ch;
                        isRomanGlobal = true;
                    }
                }
            }
        } else {
            throw new Exception("Извините, программа не поддерживает более двух операндов!");
        }

        if (isRomanGlobal) {
            a = converter.romanToInt(String.valueOf(aCh));
            b = converter.romanToInt(String.valueOf(bCh));
            int result = calc(a,b,operation);
            System.out.println(converter.intToRoman(result));
        } else {
            a = convertToInt(aCh);
            b = convertToInt(bCh);
            int result = calc(a,b,operation);
            System.out.println(result);
        }
    }

    public static int calc(Integer a, Integer b, String operation) {
        int result = 0;
        if (Objects.equals(operation, "+")) {
            result = a + b;
        } else if (Objects.equals(operation, "-")) {
            result = a - b;
        } else if (Objects.equals(operation, "/")) {
            result = a / b;
        } else if (Objects.equals(operation, "*")) {
            result = a * b;
        }

        return  result;
    }

    public static Integer convertToInt(String str) throws Exception {
        int number = 0;
        try {
            number = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            throw new Exception("Не могу преобразовать операнд");
        }

        return number;
    }

    public static boolean checkForThreeOperands(String str) {
        List<String> operation = new ArrayList<String>();

        for (char ch : str.toCharArray()) {
            if (ch == '+') {
                operation.add(String.valueOf(ch));
            }
        }

        return operation.size() > 1;
    }
}