package com.example.myapplication.util;

import android.content.Context;
import com.example.myapplication.Reservation_sol_reservation;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author aptx
 * @date 2022/12/03 19:56
 */
public class Web3Util {
    static String url = "https://sepolia.infura.io/v3/42e1865458574ae9b258fc5ac9ba2371";
    static Web3j client;
    static Reservation_sol_reservation con;
    static Credentials credentials;

    public static Web3j getConcent() {
        return Web3j.build(new HttpService(url));
    }

    static {
        client = getConcent();
        credentials =
                Credentials.create("7f2a69adeb7deaa37faa76229cfc750958eaa84f6f7e480ba300e4b05e4ad10d");
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


    public static BigInteger addUser(Context context, String id, String name) {
        try {
            TransactionReceipt receipt = con.register(id.getBytes(Charset.forName("ascii")), name).send();
            BigInteger blockNumber = receipt.getBlockNumber();
            return blockNumber;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void getCon(String address) {
        con = Reservation_sol_reservation.load(
                address, client,
                credentials,
                Convert.toWei("10", Convert.Unit.GWEI).toBigInteger(),
                BigInteger.valueOf(1000000));

    }

    public static BigInteger registerUser(String pwd, String name) {
        try {
            TransactionReceipt send = con.register(pwd.getBytes(StandardCharsets.US_ASCII), name).send();
            return send.getBlockNumber();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BigInteger addRes(String name, BigInteger count, BigInteger endTime) {
        try {
            TransactionReceipt send = con.addProposals(name, count, endTime).send();
            return send.getBlockNumber();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean getAnswer(BigInteger resid, String pwd) {
        try {
            TransactionReceipt send = con.getAnswer(resid, pwd.getBytes(StandardCharsets.US_ASCII)).send();
            List<Reservation_sol_reservation.AnswerEventResponse> answerEvents = con.getAnswerEvents(send);
            Reservation_sol_reservation.AnswerEventResponse answerEventResponse = answerEvents.get(0);
            BigInteger answer = answerEventResponse.answer;
            return Objects.equals(answer, BigInteger.valueOf(2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean kj(BigInteger resid) {
        try {
            TransactionReceipt send = con.kaijiang(resid).send();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String, Object>> getRes() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            BigInteger len = con.pid().send();
            for (int i = 0; i < len.intValue(); i++) {
                Map map = new HashMap();
                Tuple6<BigInteger, String, BigInteger, BigInteger, BigInteger, Boolean> send = con.proposals(BigInteger.valueOf(i)).send();
                BigInteger id = send.component1();
                String name = send.component2();
                BigInteger count = send.component3();
                BigInteger endTime = send.component4();
                BigInteger countJoin = send.component5();
                Boolean kj = send.component6();
                map.put("id", id.intValue());
                map.put("name", name);
                map.put("count", count.intValue());
                map.put("countJoin", countJoin.intValue());
                map.put("endTime", endTime.intValue());
                map.put("kj", kj);
                list.add(map);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void join(BigInteger redId, String pwd) {
        try {
            con.joinRes(redId, pwd.getBytes("ascii")).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }


}

/**
 5719862, 5719337, 5720556, 5720671, 5720139, 5720279, 5720650, 5720092
 5720154,5719362,5719125,5718960,5667101,3699597,255020
 * **/