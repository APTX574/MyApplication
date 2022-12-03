package com.example.myapplication.util;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * @author aptx
 * @date 2022/12/03 19:56
 */
public class Web3Util {
    static String url = "https://sepolia.infura.io/v3/42e1865458574ae9b258fc5ac9ba2371";
    static Web3j client;

    public static Web3j getConcent() {
        return Web3j.build(new HttpService(url));
    }

    static {
        client = getConcent();
    }

    public static BigInteger getGas() {
        try {
            EthGasPrice ethGasPriceRequest = client.ethGasPrice().send();
            return ethGasPriceRequest.getGasPrice();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BigInteger getBalance(String address) {
        try {
            EthGetBalance ethGetBalance = client.ethGetBalance(
                    address,
                    DefaultBlockParameterName.fromString(DefaultBlockParameterName.LATEST.name())
            ).sendAsync().get();
            return ethGetBalance.getBalance();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
