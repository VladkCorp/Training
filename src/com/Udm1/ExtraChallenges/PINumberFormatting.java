package com.Udm1.ExtraChallenges;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PINumberFormatting {

    public static void main(String[] args) {

        BigDecimal m1 = BigDecimal.valueOf(Math.PI).setScale(1,RoundingMode.HALF_UP);
        BigDecimal m2 = BigDecimal.valueOf(Math.PI).setScale(2,RoundingMode.HALF_UP);
        BigDecimal m3 = BigDecimal.valueOf(Math.PI).setScale(3,RoundingMode.HALF_UP);
        BigDecimal m4 = BigDecimal.valueOf(Math.PI).setScale(4,RoundingMode.HALF_UP);
        BigDecimal m5 = BigDecimal.valueOf(Math.PI).setScale(5,RoundingMode.HALF_UP);

        System.out.println(m1.toString() + " " +
                m2.toString() + " " + m3.toString() + " " +
                m4.toString() + " " + m5.toString());
    }
}
