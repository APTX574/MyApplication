package com.example.myapplication.util;

import junit.framework.TestCase;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Web3UtzilTest extends TestCase {
    public void test() {

        Web3Util.getCon("0x36dE876488B16E542bb6e2bA872A6AaBBcdb780c");
////        Web3Util.registerUser("01234567890123456789012345678991", "wqs");
//        Web3Util.addRes("wqsde2", BigInteger.valueOf(12),Util.timeUtil("2022-12-13 01:35"));
//        Web3Util.join(BigInteger.valueOf(1),"01234567890123456789012345678991");
//        List<Map<String, Object>> res = Web3Util.getRes();
//        System.out.println(res);
        boolean kj = Web3Util.kj(BigInteger.valueOf(1));
        System.out.println(kj);
        boolean answer = Web3Util.getAnswer(BigInteger.valueOf(1), "01234567890123456789012345678991");
        System.out.println(answer);
    }

}