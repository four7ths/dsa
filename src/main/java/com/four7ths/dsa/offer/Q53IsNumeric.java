package com.four7ths.dsa.offer;

/**
 * 表示数值的字符串
 * 判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是
 */
public class Q53IsNumeric {
    public boolean isNumeric(char[] str) {
        return new String(str).matches("[+-]?((\\d*\\.)?\\d+)((E|e)[+-]?\\d+)?");
    }
}
