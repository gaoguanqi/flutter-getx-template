package com.maple.plugin.utils;
import cn.hutool.core.util.StrUtil;
import static java.lang.Character.isUpperCase;

public class CamelCaseUtils {
    public static final String CONVERSION_PASCAL_CASE = "CamelCase";
    public static final String CONVERSION_LOWER_SNAKE_CASE = "snake_case";


    public static void main(String[] args) {
        String result1 = convert("password",CONVERSION_PASCAL_CASE);
        String result2 = convert("mySet",CONVERSION_PASCAL_CASE);
        String result3 = convert("convertTextIn",CONVERSION_PASCAL_CASE);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        String result4 = convert("password",CONVERSION_LOWER_SNAKE_CASE);
        String result5 = convert("mySet",CONVERSION_LOWER_SNAKE_CASE);
        String result6= convert("convertTextIn",CONVERSION_LOWER_SNAKE_CASE);
        System.out.println(result4);
        System.out.println(result5);
        System.out.println(result6);
    }

    public static String convert(String text, String caseType) {
        if(caseType.equals(CONVERSION_PASCAL_CASE)) {
            return StrUtil.upperFirst(text);
        } else if(caseType.equals(CONVERSION_LOWER_SNAKE_CASE)){
            return toSnakeCase(text);
        }
        return text;
    }


    /**
     * Convert a string (CamelCase) to snake_case
     *
     * @param in CamelCase string
     * @return snake_case String
     */
    private static String toSnakeCase(String in) {
        in = in.replaceAll(" +", "");
        StringBuilder result = new StringBuilder("" + Character.toLowerCase(in.charAt(0)));
        for (int i = 1; i < in.length(); i++) {
            char c = in.charAt(i);
            if (isUpperCase(c)) {
                result.append("_").append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Convert a string (snake_case) to CamelCase
     *
     * @param in snake_case String
     * @return CamelCase string
     */
    private static String toCamelCase(String in) {
        StringBuilder camelCased = new StringBuilder();
        String[] tokens = in.split("_");
        for (String token : tokens) {
            if (token.length() >= 1) {
                camelCased.append(token.substring(0, 1).toUpperCase()).append(token.substring(1));
            } else {
                camelCased.append("_");
            }
        }
        return camelCased.toString();
    }
}
