package com.lin.garbagesorting.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 伟浩 on 2014/10/2.
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {
    public StringUtil() {
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim()) || "null".equals(str.trim()) || "undefined".equals(str.trim());
    }

    public static boolean isNull(Object obj) {
        return null == obj;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static int safeInteger(Integer num) {
        return num == null ? 0 : num;
    }

    public static String empty(String str) {
        return null == str ? "" : str;
    }

    public static String emptyIsZero(String str) {
        return null != str && !"".equals(str) ? str : "0";
    }

    public static String firstChar2Upper(String str) {
        return null != str && str.trim().length() != 0 ? str.substring(0, 1).toUpperCase() + str.substring(1) : "";
    }


   /* public static String toJsonStr(Object o) {
        if (isNotNull(o))
            return GsonUtil.GsonString(o);
        return "";
    }*/

    public static String buildUUID() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    public static String escapeHtmlTag(String content, int length) {
        if (isNotEmpty(content)) {
            content = content.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?[^><]*>", "");
            if (length == 0) {
                return content;
            } else {
                if (content.length() > length) {
                    content = content.substring(0, length) + "..";
                }

                return content;
            }
        } else {
            return "";
        }
    }

    public static String chooseTwo(boolean result, String str1, String str2) {
        return result ? str1 : str2;
    }


    /**
     * 截取过长字符串并添加省略号
     *
     * @param str
     * @param len
     * @return
     */
    public static String subStrEllipsis(String str, int len) {
        if (isNotEmpty(str)) {
            if (str.length() > len)
                return str.substring(0, len) + "..";
            else
                return str;
        }
        return "";
    }

    /**
     * 替换特定字符串
     *
     * @param target
     * @param chars
     * @return
     */
    public static String replaceStr(String target, String chars, int len) {
        if (len != 0) {
            target = subStrEllipsis(target, len);
        }
        if (isNotEmpty(target)) {
            return target.replaceAll(chars, "<font color='red'>" + chars + "</font>");
        }
        return "";
    }

    /**
     * 获取字符串中的图片链接（src=""）
     *
     * @param s
     * @return
     */
    public static List<String> getImg(String s) {
        String regex;
        List<String> list = new ArrayList<String>();
        regex = "src=\"(.*?)\"";
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(s);
        while (ma.find()) {
            list.add(ma.group());
        }
        return list;
    }

    /**
     * 获取html字符中的图片链接（htpp://img.jpg）
     *
     * @param htmlStr
     * @return
     */
    public static List getImagePath(String htmlStr) {
        String img = "";
        Pattern p_image;
        Matcher m_image;
        List pics = new ArrayList<String>();

        String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            img = m_image.group();
            Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }


    private static final String[] BROWSER_TYPE = new String[]{"MSIE 6.0", "MSIE 7.0", "MSIE 8.0", "MSIE 9.0", "MSIE 10.0", "Maxthon", "QQBrowser", "GreenBrowser", "360SE", "Firefox", "Opera", "Chrome", "Safari"};

    public static String getBrowseType(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT");
        String[] arr$ = BROWSER_TYPE;
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            String type = arr$[i$];
            if (userAgent.indexOf(type) != -1) {
                return type;
            }
        }

        if (userAgent.indexOf("Gecko") > 0 && userAgent.indexOf("rv:11") > 0) {
            return "MSIE 11.0";
        }

        return userAgent;
    }

    /**
     * 获取6位随机数
     *
     * @return
     */
    public static String createRandom6code() {
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }

    public static String buildLikeStr(String param) {
        if (!isNotBlank(param))
            return null;
        StringBuffer sb = new StringBuffer("%");
        sb.append(param);
        sb.append("%");
        return sb.toString();
    }

    /**
     * 移除字符串
     *
     * @param str
     * @param remove
     * @return
     */
    public static String removeLast(String str, String remove) {
        if (isNotEmpty(str) && isNotEmpty(remove)) {
            int index = str.lastIndexOf(remove);
            if (index == -1) {
                return str;
            } else {
                return str.substring(0, index) + str.substring(index + remove.length());
            }
        } else {
            return str;
        }
    }

    public static String toUpperCamelCase(String value) {
        if (value == null || "".equals(value.trim())) {
            return "";
        }
        int len = value.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = value.charAt(i);
            if (i == 0 && c >= 97 && c <= 122) {
                sb.append(Character.toUpperCase(c));
                continue;
            }
            if (c == 95) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(value.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
