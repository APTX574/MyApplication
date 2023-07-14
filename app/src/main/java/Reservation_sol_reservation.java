import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
 * <p>Generated with web3j version 4.5.1.
 */
@SuppressWarnings("rawtypes")
public class Reservation_sol_reservation extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600180546001600160a01b0319163317905560008055610a5c806100366000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c806335b8c6201161005b57806335b8c6201461011c578063ad4723c21461012f578063cf2d31fb14610142578063f10684541461015557600080fd5b8063013cf08b1461008d5780632280f1fa146100bb5780632b43992f146100dc5780632e4176cf146100f1575b600080fd5b6100a061009b36600461065d565b61015e565b6040516100b296959493929190610676565b60405180910390f35b6100ce6100c93660046106f0565b61021f565b6040519081526020016100b2565b6100ef6100ea3660046107b5565b6102ed565b005b600154610104906001600160a01b031681565b6040516001600160a01b0390911681526020016100b2565b6100ef61012a36600461065d565b6103b7565b6100ef61013d3660046106f0565b610503565b6100ef610150366004610803565b610605565b6100ce60005481565b600260205260009081526040902080546001820180549192916101809061084a565b80601f01602080910402602001604051908101604052809291908181526020018280546101ac9061084a565b80156101f95780601f106101ce576101008083540402835291602001916101f9565b820191906000526020600020905b8154815290600101906020018083116101dc57829003601f168201915b505050506002830154600584015460068501546007909501549394919390925060ff1686565b6001546000906001600160a01b03163314801561024d57506000838152600260205260409020600501544210155b6102725760405162461bcd60e51b815260040161026990610884565b60405180910390fd5b60008381526002602090815260408083208584526003018252918290205482518581529182018690528183015290517f4197d0bc8b117c91df3d8dd4fb46e1899ff32c0683fe993a82b9f63a62df09af9181900360600190a15060009182526002602090815260408084209284526003909201905290205490565b6001546001600160a01b03163314801561030657504281115b6103635760405162461bcd60e51b815260206004820152602860248201527f6f6e6c7920636861726d616e2063616e2073656e6420616464206f722074696d60448201526719481a5cc81bdb1960c21b6064820152608401610269565b6000805460029082908180610377836108cf565b909155508152602081019190915260400160002090506001810161039b8582610944565b5060028101929092556005820155600701805460ff1916905550565b6001546001600160a01b0316331480156103e3575060008181526002602052604090206007015460ff16155b801561040057506000818152600260205260409020600501544210155b61041c5760405162461bcd60e51b815260040161026990610884565b6000818152600260205260408120600701805460ff191660011790555b6000828152600260205260409020600601548110156104ff57600082815260026020908152604080832060060154815142818501528251808203850181529083019092528151919092012061048e9190610a04565b60008481526002602090815260408083208484526004810183528184205484526003019091529020549091506001036104f957600083815260026020818152604080842085855260048101835281852054855260030190915290912055816104f5816108cf565b9250505b50610439565b5050565b6001546001600160a01b03163314801561052d575060008281526002602052604090206005015442105b801561053a575081600054115b6105ac5760405162461bcd60e51b815260206004820152603960248201527f6f6e6c7920636861726d616e2063616e2073656e6420616464206f722052657360448201527f20697320656e64206f72207468657265206e6f207265736964000000000000006064820152608401610269565b600082815260026020818152604080842085855260038101835290842060019055858452919052600681018054849360049093019291826105ec836108cf565b9091555081526020810191909152604001600020555050565b6001546001600160a01b0316331480156106375750600082815260036020526040902080546106339061084a565b1590505b61064057600080fd5b60008281526003602052604090206106588282610944565b505050565b60006020828403121561066f57600080fd5b5035919050565b8681526000602060c08184015287518060c085015260005b818110156106aa5789810183015185820160e00152820161068e565b50600060e0828601015260e0601f19601f830116850101925050508560408301528460608301528360808301526106e560a083018415159052565b979650505050505050565b6000806040838503121561070357600080fd5b50508035926020909101359150565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261073957600080fd5b813567ffffffffffffffff8082111561075457610754610712565b604051601f8301601f19908116603f0116810190828211818310171561077c5761077c610712565b8160405283815286602085880101111561079557600080fd5b836020870160208301376000602085830101528094505050505092915050565b6000806000606084860312156107ca57600080fd5b833567ffffffffffffffff8111156107e157600080fd5b6107ed86828701610728565b9660208601359650604090950135949350505050565b6000806040838503121561081657600080fd5b82359150602083013567ffffffffffffffff81111561083457600080fd5b61084085828601610728565b9150509250929050565b600181811c9082168061085e57607f821691505b60208210810361087e57634e487b7160e01b600052602260045260246000fd5b50919050565b6020808252602b908201527f6f6e6c7920636861726d616e2063616e2073656e6420616464206f722052657360408201526a081a5cc81b9bdd08195b9960aa1b606082015260800190565b6000600182016108ef57634e487b7160e01b600052601160045260246000fd5b5060010190565b601f82111561065857600081815260208120601f850160051c8101602086101561091d5750805b601f850160051c820191505b8181101561093c57828155600101610929565b505050505050565b815167ffffffffffffffff81111561095e5761095e610712565b6109728161096c845461084a565b846108f6565b602080601f8311600181146109a7576000841561098f5750858301515b600019600386901b1c1916600185901b17855561093c565b600085815260208120601f198616915b828110156109d6578886015182559484019460019091019084016109b7565b50858210156109f45787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b600082610a2157634e487b7160e01b600052601260045260246000fd5b50069056fea2646970667358221220f6b9052c3e64490330f5c0d8314af535acc9606fe485cfa9f9906c7b8cb2e66664736f6c63430008110033";

    public static final String FUNC_ADDPROPOSALS = "addProposals";

    public static final String FUNC_CHAIRPERSON = "chairperson";

    public static final String FUNC_GETANSWER = "getAnswer";

    public static final String FUNC_JOINRES = "joinRes";

    public static final String FUNC_KAIJIANG = "kaijiang";

    public static final String FUNC_PID = "pid";

    public static final String FUNC_PROPOSALS = "proposals";

    public static final String FUNC_REGISTER = "register";

    public static final Event ANSWER_EVENT = new Event("Answer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

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

    public List<AnswerEventResponse> getAnswerEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ANSWER_EVENT, transactionReceipt);
        ArrayList<AnswerEventResponse> responses = new ArrayList<AnswerEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AnswerEventResponse typedResponse = new AnswerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.resid = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.answer = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AnswerEventResponse> answerEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AnswerEventResponse>() {
            @Override
            public AnswerEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ANSWER_EVENT, log);
                AnswerEventResponse typedResponse = new AnswerEventResponse();
                typedResponse.log = log;
                typedResponse.userId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.resid = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.answer = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AnswerEventResponse> answerEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ANSWER_EVENT));
        return answerEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addProposals(String name, BigInteger count, BigInteger endTime) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDPROPOSALS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new Uint256(count),
                new Uint256(endTime)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> chairperson() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CHAIRPERSON, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getAnswer(BigInteger resid, byte[] pwd) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETANSWER, 
                Arrays.<Type>asList(new Uint256(resid),
                new Bytes32(pwd)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> joinRes(BigInteger resid, byte[] pwd) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_JOINRES, 
                Arrays.<Type>asList(new Uint256(resid),
                new Bytes32(pwd)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> kaijiang(BigInteger resid) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_KAIJIANG, 
                Arrays.<Type>asList(new Uint256(resid)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> pid() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> proposals(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PROPOSALS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> register(byte[] passw, String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Bytes32(passw),
                new org.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public static class AnswerEventResponse extends BaseEventResponse {
        public byte[] userId;

        public BigInteger resid;

        public BigInteger answer;
    }
}
