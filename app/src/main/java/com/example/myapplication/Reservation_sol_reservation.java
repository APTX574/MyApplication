package com.example.myapplication;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Reservation_sol_reservation extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600180546001600160a01b031916331790556000805561077c806100366000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c8063013cf08b1461005c5780632b43992f146100885780632e4176cf1461009d5780633ffbd47f146100c8578063f4bf93be146100db575b600080fd5b61006f61006a36600461037a565b6100fb565b60405161007f94939291906103e3565b60405180910390f35b61009b6100963660046104b2565b6101ac565b005b6001546100b0906001600160a01b031681565b6040516001600160a01b03909116815260200161007f565b61009b6100d6366004610500565b610270565b6100ee6100e9366004610564565b6102d5565b60405161007f91906105a1565b6002602052600090815260409020805460018201805491929161011d906105bb565b80601f0160208091040260200160405190810160405280929190818152602001828054610149906105bb565b80156101965780601f1061016b57610100808354040283529160200191610196565b820191906000526020600020905b81548152906001019060200180831161017957829003601f168201915b5050505050908060020154908060040154905084565b6001546001600160a01b0316331480156101c557504281115b6102265760405162461bcd60e51b815260206004820152602860248201527f6f6e6c7920636861726d616e2063616e2073656e6420616464206f722074696d60448201526719481a5cc81bdb1960c21b606482015260840160405180910390fd5b600080546002908290818061023a836105f5565b909155508152602081019190915260400160002090506001810161025e858261066a565b50600281019290925560049091015550565b600382604051610280919061072a565b90815260200160405180910390208054610299906105bb565b1590506102a557600080fd5b806003836040516102b6919061072a565b908152602001604051809103902090816102d0919061066a565b505050565b8051602081830181018051600382529282019190930120915280546102f9906105bb565b80601f0160208091040260200160405190810160405280929190818152602001828054610325906105bb565b80156103725780601f1061034757610100808354040283529160200191610372565b820191906000526020600020905b81548152906001019060200180831161035557829003601f168201915b505050505081565b60006020828403121561038c57600080fd5b5035919050565b60005b838110156103ae578181015183820152602001610396565b50506000910152565b600081518084526103cf816020860160208601610393565b601f01601f19169290920160200192915050565b8481526080602082015260006103fc60808301866103b7565b6040830194909452506060015292915050565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261043657600080fd5b813567ffffffffffffffff808211156104515761045161040f565b604051601f8301601f19908116603f011681019082821181831017156104795761047961040f565b8160405283815286602085880101111561049257600080fd5b836020870160208301376000602085830101528094505050505092915050565b6000806000606084860312156104c757600080fd5b833567ffffffffffffffff8111156104de57600080fd5b6104ea86828701610425565b9660208601359650604090950135949350505050565b6000806040838503121561051357600080fd5b823567ffffffffffffffff8082111561052b57600080fd5b61053786838701610425565b9350602085013591508082111561054d57600080fd5b5061055a85828601610425565b9150509250929050565b60006020828403121561057657600080fd5b813567ffffffffffffffff81111561058d57600080fd5b61059984828501610425565b949350505050565b6020815260006105b460208301846103b7565b9392505050565b600181811c908216806105cf57607f821691505b6020821081036105ef57634e487b7160e01b600052602260045260246000fd5b50919050565b60006001820161061557634e487b7160e01b600052601160045260246000fd5b5060010190565b601f8211156102d057600081815260208120601f850160051c810160208610156106435750805b601f850160051c820191505b818110156106625782815560010161064f565b505050505050565b815167ffffffffffffffff8111156106845761068461040f565b6106988161069284546105bb565b8461061c565b602080601f8311600181146106cd57600084156106b55750858301515b600019600386901b1c1916600185901b178555610662565b600085815260208120601f198616915b828110156106fc578886015182559484019460019091019084016106dd565b508582101561071a5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b6000825161073c818460208701610393565b919091019291505056fea26469706673582212209d605c7d6e7aed463e31e2c48e4fa55a39db41ea16b94c4da9c447f262c843a564736f6c63430008110033";

    public static final String FUNC_ADDPROPOSALS = "addProposals";

    public static final String FUNC_CHAIRPERSON = "chairperson";

    public static final String FUNC_PROPOSALS = "proposals";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_USER = "user";

    @Deprecated
    protected Reservation_sol_reservation(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Reservation_sol_reservation(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Reservation_sol_reservation(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Reservation_sol_reservation(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addProposals(String name, BigInteger count, BigInteger endTime) {
        final Function function = new Function(
                FUNC_ADDPROPOSALS, 
                Arrays.<Type>asList(new Utf8String(name),
                new Uint256(count),
                new Uint256(endTime)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> chairperson() {
        final Function function = new Function(FUNC_CHAIRPERSON, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, String, BigInteger, BigInteger>> proposals(BigInteger param0) {
        final Function function = new Function(FUNC_PROPOSALS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, String, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, String, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> register(String id, String name) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Utf8String(id),
                new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> user(String param0) {
        final Function function = new Function(FUNC_USER, 
                Arrays.<Type>asList(new Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static Reservation_sol_reservation load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Reservation_sol_reservation(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Reservation_sol_reservation load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Reservation_sol_reservation(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Reservation_sol_reservation load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Reservation_sol_reservation(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Reservation_sol_reservation load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Reservation_sol_reservation(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Reservation_sol_reservation> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Reservation_sol_reservation.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Reservation_sol_reservation> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Reservation_sol_reservation.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Reservation_sol_reservation> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Reservation_sol_reservation.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Reservation_sol_reservation> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Reservation_sol_reservation.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
