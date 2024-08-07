import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.DynamicArray;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Voting extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040523480156200001157600080fd5b5033600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620000a26040805190810160405280600481526020017f6a6f76690000000000000000000000000000000000000000000000000000000081525062000146640100000000026401000000009004565b620000f16040805190810160405280600681526020017f7869616f4169000000000000000000000000000000000000000000000000000081525062000146640100000000026401000000009004565b620001406040805190810160405280600481526020017f736972690000000000000000000000000000000000000000000000000000000081525062000146640100000000026401000000009004565b62000387565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515620001db576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401620001d29062000365565b60405180910390fd5b6060604051908101604052806002548152602001828152602001600081525060008060025481526020019081526020016000206000820151816000015560208201518160010190805190602001906200023692919062000259565b506040820151816002015590505060026000815480929190600101919050555050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200029c57805160ff1916838001178555620002cd565b82800160010185558215620002cd579182015b82811115620002cc578251825591602001919060010190620002af565b5b509050620002dc9190620002e0565b5090565b6200030591905b8082111562000301576000816000905550600101620002e7565b5090565b90565b6000602782527f4f6e6c7920746865206f776e65722063616e20706572666f726d20746869732060208301527f616374696f6e2e000000000000000000000000000000000000000000000000006040830152606082019050919050565b60006020820190508181036000830152620003808162000308565b9050919050565b61132d80620003976000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630121b93f146100b45780631e620cd4146100dd5780631f62b4e5146101065780632d35a8a2146101315780633477ee2e1461015c57806340c719c71461019b578063462e91ec146101b2578063866163c0146101db5780639a0e7d6614610218578063a3ec138d14610243578063e13804a714610280575b600080fd5b3480156100c057600080fd5b506100db60048036036100d69190810190610ed4565b6102bd565b005b3480156100e957600080fd5b5061010460048036036100ff9190810190610ed4565b61041e565b005b34801561011257600080fd5b5061011b61054e565b60405161012891906110c1565b60405180910390f35b34801561013d57600080fd5b50610146610679565b604051610153919061117e565b60405180910390f35b34801561016857600080fd5b50610183600480360361017e9190810190610ed4565b61067f565b60405161019293929190611199565b60405180910390f35b3480156101a757600080fd5b506101b0610741565b005b3480156101be57600080fd5b506101d960048036036101d49190810190610e93565b610831565b005b3480156101e757600080fd5b5061020260048036036101fd9190810190610ed4565b61093f565b60405161020f919061117e565b60405180910390f35b34801561022457600080fd5b5061022d6109b0565b60405161023a919061117e565b60405180910390f35b34801561024f57600080fd5b5061026a60048036036102659190810190610e6a565b6109f6565b60405161027791906110e3565b60405180910390f35b34801561028c57600080fd5b506102a760048036036102a29190810190610ed4565b610a16565b6040516102b491906110c1565b60405180910390f35b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff1615151561034c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103439061113e565b60405180910390fd5b6000811015801561035e575060025481105b151561039f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610396906110fe565b60405180910390fd5b6000808281526020019081526020016000206002016000815480929190600101919050555060018060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156104b0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104a79061111e565b60405180910390fd5b600081101580156104c2575060025481105b1515610503576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104fa906110fe565b60405180910390fd5b60008082815260200190815260200160002060008082016000905560018201600061052e9190610cff565b600282016000905550506002600081548092919060019003919050555050565b606080600060025460405190808252806020026020018201604052801561058957816020015b60608152602001906001900390816105745790505b509150600090505b600254811015610671576000808281526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106445780601f1061061957610100808354040283529160200191610644565b820191906000526020600020905b81548152906001019060200180831161062757829003601f168201915b5050505050828281518110151561065757fe5b906020019060200201819052508080600101915050610591565b819250505090565b60025481565b6000602052806000526040600020600091509050806000015490806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107315780601f1061070657610100808354040283529160200191610731565b820191906000526020600020905b81548152906001019060200180831161071457829003601f168201915b5050505050908060020154905083565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156107d5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107cc9061111e565b60405180910390fd5b600090505b6002548110156108265760008082815260200190815260200160002060008082016000905560018201600061080f9190610cff565b6002820160009055505080806001019150506107da565b600060028190555050565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156108c3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108ba9061111e565b60405180910390fd5b60606040519081016040528060025481526020018281526020016000815250600080600254815260200190815260200160002060008201518160000155602082015181600101908051906020019061091c929190610d47565b506040820151816002015590505060026000815480929190600101919050555050565b6000808210158015610952575060025482105b1515610993576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161098a9061115e565b60405180910390fd5b600080838152602001908152602001600020600201549050919050565b6000806000809150600090505b6002548110156109ee57600080828152602001908152602001600020600201548201915080806001019150506109bd565b819250505090565b60016020528060005260406000206000915054906101000a900460ff1681565b60608060008310158015610a2b575060025483105b1515610a6c576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a63906110fe565b60405180910390fd5b6002604051908082528060200260200182016040528015610aa157816020015b6060815260200190600190039081610a8c5790505b5090506000808481526020019081526020016000206001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b4d5780601f10610b2257610100808354040283529160200191610b4d565b820191906000526020600020905b815481529060010190602001808311610b3057829003601f168201915b5050505050816000815181101515610b6157fe5b90602001906020020181905250610b8c60008085815260200190815260200160002060020154610bb1565b816001815181101515610b9b57fe5b9060200190602002018190525080915050919050565b606060008060606000851415610bfe576040805190810160405280600181526020017f30000000000000000000000000000000000000000000000000000000000000008152509350610cf7565b8492505b600083141515610c28578180600101925050600a83811515610c2057fe5b049250610c02565b816040519080825280601f01601f191660200182016040528015610c5b5781602001602082028038833980820191505090505b5090505b600085141515610cf3","57600182039150600a85811515610c7b57fe5b066030017f0100000000000000000000000000000000000000000000000000000000000000028183815181101515610caf57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600a85811515610ceb57fe5b049450610c5f565b8093505b505050919050565b50805460018160011615610100020316600290046000825580601f10610d255750610d44565b601f016020900490600052602060002090810190610d439190610dc7565b5b50565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610d8857805160ff1916838001178555610db6565b82800160010185558215610db6579182015b82811115610db5578251825591602001919060010190610d9a565b5b509050610dc39190610dc7565b5090565b610de991905b80821115610de5576000816000905550600101610dcd565b5090565b90565b6000610df88235611276565b905092915050565b600082601f8301121515610e1357600080fd5b8135610e26610e2182611204565b6111d7565b91508082526020830160208301858383011115610e4257600080fd5b610e4d8382846112a0565b50505092915050565b6000610e628235611296565b905092915050565b600060208284031215610e7c57600080fd5b6000610e8a84828501610dec565b91505092915050565b600060208284031215610ea557600080fd5b600082013567ffffffffffffffff811115610ebf57600080fd5b610ecb84828501610e00565b91505092915050565b600060208284031215610ee657600080fd5b6000610ef484828501610e56565b91505092915050565b6000610f088261123d565b80845260208401935083602082028501610f2185611230565b60005b84811015610f5a578383038852610f3c838351610f7a565b9250610f4782611253565b9150602088019750600181019050610f24565b508196508694505050505092915050565b610f7481611260565b82525050565b6000610f8582611248565b808452610f998160208601602086016112af565b610fa2816112e2565b602085010191505092915050565b6000601982527f63616e64696461746520646f6573206e6f7420657869737421000000000000006020830152604082019050919050565b6000602782527f4f6e6c7920746865206f776e65722063616e20706572666f726d20746869732060208301527f616374696f6e2e000000000000000000000000000000000000000000000000006040830152606082019050919050565b6000601582527f796f752061726520616c726561647920766f74656400000000000000000000006020830152604082019050919050565b6000601882527f63616e64696461746520646f6573206e6f7420657869742100000000000000006020830152604082019050919050565b6110bb8161126c565b82525050565b600060208201905081810360008301526110db8184610efd565b905092915050565b60006020820190506110f86000830184610f6b565b92915050565b6000602082019050818103600083015261111781610fb0565b9050919050565b6000602082019050818103600083015261113781610fe7565b9050919050565b6000602082019050818103600083015261115781611044565b9050919050565b600060208201905081810360008301526111778161107b565b9050919050565b600060208201905061119360008301846110b2565b92915050565b60006060820190506111ae60008301866110b2565b81810360208301526111c08185610f7a565b90506111cf60408301846110b2565b949350505050565b6000604051905081810181811067ffffffffffffffff821117156111fa57600080fd5b8060405250919050565b600067ffffffffffffffff82111561121b57600080fd5b601f19601f8301169050602081019050919050565b6000602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b60008115159050919050565b6000819050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b82818337600083830152505050565b60005b838110156112cd5780820151818401526020810190506112b2565b838111156112dc576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a723058206ff426ccfc067d5439038df4f1136d35cc88532f21488beb64335c35c78c8eae6c6578706572696d656e74616cf50037"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"_candidateId\",\"type\":\"uint256\"}],\"name\":\"vote\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"deleteCandidate\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getAllCandidateName\",\"outputs\":[{\"name\":\"\",\"type\":\"string[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"candidatesCount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"candidates\",\"outputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"voteCount\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"deleteAllCandidateName\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_name\",\"type\":\"string\"}],\"name\":\"addCandidate\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"getCandidateVotes\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getTotalVotes\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"voters\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"}],\"name\":\"getCandidateName\",\"outputs\":[{\"name\":\"\",\"type\":\"string[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_DELETECANDIDATE = "deleteCandidate";

    public static final String FUNC_GETALLCANDIDATENAME = "getAllCandidateName";

    public static final String FUNC_CANDIDATESCOUNT = "candidatesCount";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_DELETEALLCANDIDATENAME = "deleteAllCandidateName";

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_GETCANDIDATEVOTES = "getCandidateVotes";

    public static final String FUNC_GETTOTALVOTES = "getTotalVotes";

    public static final String FUNC_VOTERS = "voters";

    public static final String FUNC_GETCANDIDATENAME = "getCandidateName";

    protected Voting(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt vote(BigInteger _candidateId) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_candidateId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void vote(BigInteger _candidateId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_candidateId)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForVote(BigInteger _candidateId) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_candidateId)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getVoteInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_VOTE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt deleteCandidate(BigInteger _id) {
        final Function function = new Function(
                FUNC_DELETECANDIDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void deleteCandidate(BigInteger _id, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_DELETECANDIDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForDeleteCandidate(BigInteger _id) {
        final Function function = new Function(
                FUNC_DELETECANDIDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getDeleteCandidateInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DELETECANDIDATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public List getAllCandidateName() throws ContractException {
        final Function function = new Function(FUNC_GETALLCANDIDATENAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        List<Type> result = (List<Type>) executeCallWithSingleValueReturn(function, List.class);
        return convertToNative(result);
    }

    public BigInteger candidatesCount() throws ContractException {
        final Function function = new Function(FUNC_CANDIDATESCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple3<BigInteger, String, BigInteger> candidates(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_CANDIDATES, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple3<BigInteger, String, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue());
    }

    public TransactionReceipt deleteAllCandidateName() {
        final Function function = new Function(
                FUNC_DELETEALLCANDIDATENAME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void deleteAllCandidateName(TransactionCallback callback) {
        final Function function = new Function(
                FUNC_DELETEALLCANDIDATENAME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForDeleteAllCandidateName() {
        final Function function = new Function(
                FUNC_DELETEALLCANDIDATENAME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public TransactionReceipt addCandidate(String _name) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void addCandidate(String _name, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAddCandidate(String _name) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getAddCandidateInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public BigInteger getCandidateVotes(BigInteger _id) throws ContractException {
        final Function function = new Function(FUNC_GETCANDIDATEVOTES, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger getTotalVotes() throws ContractException {
        final Function function = new Function(FUNC_GETTOTALVOTES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Boolean voters(String param0) throws ContractException {
        final Function function = new Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public List getCandidateName(BigInteger _id) throws ContractException {
        final Function function = new Function(FUNC_GETCANDIDATENAME, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        List<Type> result = (List<Type>) executeCallWithSingleValueReturn(function, List.class);
        return convertToNative(result);
    }

    public static Voting load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Voting(contractAddress, client, credential);
    }

    public static Voting deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Voting.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}
