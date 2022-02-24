package com.four7ths.dsa.offer;

/**
 * 将一个字符串中的每个空格替换成【%20】
 */
public class Q02ReplaceSpace {
    public String replaceSpace(StringBuilder str) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toString().toCharArray()) {
            if (ch == ' ') {
                sb.append("%20");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
