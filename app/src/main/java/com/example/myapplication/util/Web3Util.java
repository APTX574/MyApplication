package com.example.myapplication.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.example.myapplication.Reservation_sol_reservation;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.logging.ConsoleHandler;

/**
 * @author aptx
 * @date 2022/12/03 19:56
 */
public class Web3Util {
    static String url = "https://sepolia.infura.io/v3/42e1865458574ae9b258fc5ac9ba2371";
    static Web3j client;
    static Reservation_sol_reservation con;

    public static Web3j getConcent() {
        return Web3j.build(new HttpService(url));
    }

    static {
        client = getConcent();
        Credentials credentials =
                Credentials.create("7f2a69adeb7deaa37faa76229cfc750958eaa84f6f7e480ba300e4b05e4ad10d");

        con = Reservation_sol_reservation.load(
                "0x3CffAF277d905E6414b4264cbeAbC1F0965B3abf", client,
                credentials,
                Convert.toWei("10", Convert.Unit.GWEI).toBigInteger(),
                BigInteger.valueOf(100000));

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

    public static String getUser(Context context,String id) {


        try {
            String send = con.user(id).send();
            System.out.println(send);
            Util.toast(send, context);
            return send;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static BigInteger addUser(Context context, String id,String name) {


        try {
            TransactionReceipt receipt = con.register(id, name).send();
            BigInteger blockNumber = receipt.getBlockNumber();
            return blockNumber;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
