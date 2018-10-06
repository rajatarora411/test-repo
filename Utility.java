package com.tester.test.utility;


import com.google.common.base.Charsets;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



public class Utility {



    /**
     * This method is used to remove some unwanted string characters
     *
     * @param xmlData
     * @return
     */
    public static String cleanXML(String xmlData) {
        if (xmlData.isEmpty()) return null;

        String xmlTag = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
        return xmlData.replace(xmlTag, "");
    }

 
    /**
     * @param price
     * @return true if the value is positive.
     */
    public static boolean isPositiveValue(BigDecimal price) {

        return (price != null && BigDecimal.ZERO.compareTo(price) < 0);
    }

    public static boolean isPositiveValue(Integer price) {
        if (price == null || price <= 0) {
            return false;
        }
        return true;
    }

    public static BigDecimal convertAmount(BigDecimal amount, BigDecimal conversionRate) {
        return amount.multiply(conversionRate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static String getRateCode(String tbrateCodes, String brand) {
        // tbratecodes expected values
        String rateCode = "";
        if (!StringUtils.isEmpty(tbrateCodes)) {
            String defaultValue = "default";
            String[] rateCodeValues = tbrateCodes.split("},\\{");
            for (String rateCodeValue : rateCodeValues) {
                String[] getRateCodeValue = rateCodeValue.split(",");
                if (StringUtils.isNotBlank(brand) && brand.equals(getRateCodeValue[1])) {
                    rateCode = getRateCodeValue[2].replace("\"", "");
                    break;
                }
                if (defaultValue.equals(getRateCodeValue[1])) {
                    rateCode = getRateCodeValue[2].replace("\"", "");
                }
            }
        }
        return rateCode;
    }


    public static boolean indexExist(String[] array, int index) {
        boolean indexExist = false;
        if (array != null && array.length > index && !StringUtils.isEmpty(array[index])) {
            indexExist = true;
        }
        return indexExist;
    }

    public static String convertToCents(BigDecimal value) {

        return String.valueOf(value.movePointRight(2).intValueExact());

    }

  

    public static String removeAccentCharacter(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

}
