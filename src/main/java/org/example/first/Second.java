package org.example.first;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Second {

    static int firstValue = 105;
    static int firstCounter = 0;
    static int secondValue = 72;
    static int secondCounter = 0;
    static int thirdValue = 12;
    static int thirdCounter = 0;

    public static void main(String[] args){

        //X*Y=10^n*ac+10^n/2*((a+b)(c+d)−ac−bd)+bd

        String x = "1685287499328328297814655639278583667919355849391453456921116729";
        String y = "7114192848577754587969744626558571536728983167954552999895348492";

        BigInteger finalResult = multiplyKaratsuba(x, y);
        System.out.println(finalResult);

        System.out.println(firstValue + ": " + firstCounter);
        System.out.println(secondValue + ": " + secondCounter);
        System.out.println(thirdValue + ": " + thirdCounter);
    }

    private static BigInteger multiplyKaratsuba(String x, String y) {
        int numbersSize;
        System.out.println("---");
        if (x.length() != y.length()) {
            System.out.println("x!=y " + x + " " + y);
            if (x.length() > y.length()) {
                StringBuilder yBuilder = new StringBuilder(y);
                for(int i = x.length() - yBuilder.length(); i > 0; i--){
                    yBuilder.insert(0, "0");
                }
                y = yBuilder.toString();
            } else {
                StringBuilder xBuilder = new StringBuilder(x);
                for(int i = y.length() - xBuilder.length(); i > 0; i--){
                    xBuilder.insert(0, "0");
                }
                x = xBuilder.toString();
            }
        }
        if (x.length() % 2 != 0) {
            System.out.println("x% 2 != 0 " + x + " " + y);
            x = "0" + x;
            y = "0" + y;
        }
        numbersSize = x.length();

        System.out.println("numbersSize:" + numbersSize);
        System.out.println("x:" + x);
        System.out.println("y:" + y);
        System.out.println("a:" + x.substring(0,numbersSize/2));
        BigInteger a = new BigInteger(x.substring(0,numbersSize/2));
        System.out.println("b:" + x.substring(numbersSize/2));
        BigInteger b = new BigInteger(x.substring(numbersSize/2));
        System.out.println("c:" + y.substring(0,numbersSize/2));
        BigInteger c = new BigInteger(y.substring(0,numbersSize/2));
        System.out.println("d:" + y.substring(numbersSize/2));
        BigInteger d = new BigInteger(y.substring(numbersSize/2));

        BigInteger aAndC;
        if (a.toString().length() > 1 | c.toString().length() > 1) {
            aAndC = multiplyKaratsuba(a.toString(), c.toString());
        } else {
            aAndC = a.multiply(c);
        }

        BigInteger bAndD;
        if (b.toString().length() > 1 | d.toString().length() > 1) {
            bAndD = multiplyKaratsuba(b.toString(), d.toString());
        } else {
            bAndD = b.multiply(d);
        }

        BigInteger aBAndCD;
        if (a.add(b).toString().length() > 1 | c.add(d).toString().length() > 1) {
            aBAndCD = multiplyKaratsuba(a.add(b).toString(), c.add(d).toString());
        } else {
            aBAndCD = (a.add(b)).multiply(c.add(d));
        }

        BigInteger aAndDPlusBAndC = aBAndCD.subtract(aAndC).subtract(bAndD);
        System.out.println("aAndDPlusBAndC:" + aAndDPlusBAndC);

        if (aAndDPlusBAndC.intValue() == firstValue){
            firstCounter++;
        }

        if (aAndDPlusBAndC.intValue() == secondValue){
            secondCounter++;
        }

        if (aAndDPlusBAndC.intValue() == thirdValue){
            thirdCounter++;
        }

        BigInteger firstPart = (BigDecimal.valueOf(Math.pow(10, numbersSize)).toBigInteger()).multiply(aAndC);
        BigInteger secondPart = (BigDecimal.valueOf(Math.pow(10, (double)numbersSize /2)).toBigInteger()).multiply(
                aAndDPlusBAndC
        );
        return firstPart.add(secondPart).add(bAndD);
    }
}
