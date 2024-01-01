package com.example.demo.contract;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.Bool;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Volunteer extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50600380546001600160a01b03191633179055610b0c806100326000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c806349d8678b1161007157806349d8678b14610147578063e620f9e31461015f578063e627989814610181578063f2c298be146101a3578063f3620dfd146101b6578063f851a440146101be57600080fd5b8063163f7522146100ae5780631bc63267146100e65780631ea1ec37146101085780633b355fdc146101125780634456033814610125575b600080fd5b6100d16100bc36600461088b565b60026020526000908152604090205460ff1681565b60405190151581526020015b60405180910390f35b6100f96100f43660046108bb565b6101e9565b6040516100dd93929190610921565b610110610296565b005b6101106101203660046108bb565b610447565b336000908152602081905260409020600201545b6040519081526020016100dd565b33600090815260208190526040902060010154610139565b61017261016d3660046108bb565b610500565b6040516100dd9392919061094c565b61019461018f36600461088b565b61060d565b6040516100dd93929190610976565b6101106101b13660046109b1565b6106b7565b6100d16107ab565b6003546101d1906001600160a01b031681565b6040516001600160a01b0390911681526020016100dd565b60016020819052600091825260409091208054918101805461020a90610a62565b80601f016020809104026020016040519081016040528092919081815260200182805461023690610a62565b80156102835780601f1061025857610100808354040283529160200191610283565b820191906000526020600020905b81548152906001019060200180831161026657829003601f168201915b5050506002909301549192505060ff1683565b33600090815260208190526040902060010154600a116104455760006102fc6040516bffffffffffffffffffffffff193360601b166020820152426034820152600090819060540160408051601f19818403018152919052805160209091012092915050565b33600081815260208181526040808320600281018690558151606081019092528582529383528282528354949550919391929083019161033b90610a62565b80601f016020809104026020016040519081016040528092919081815260200182805461036790610a62565b80156103b45780601f10610389576101008083540402835291602001916103b4565b820191906000526020600020905b81548152906001019060200180831161039757829003601f168201915b50505091835250506001602091820181905260008581528183526040902083518155838301518051949550859491936103f2938501929101906107f2565b50604091820151600291909101805460ff19169115159190911790555182815233907f7b668c1d802020ad1f5d31cd10b7ba94c44ed61f34fe12736de49cb3815e62f5906020015b60405180910390a250505b565b3360009081526002602052604090205460ff166104a15760405162461bcd60e51b8152602060048201526013602482015272155cd95c881b9bdd081c9959da5cdd195c9959606a1b60448201526064015b60405180910390fd5b33600090815260208190526040812060010180548392906104c3908490610a9d565b909155505060405181815233907f9dee2777caab0589c7cce89c64e4fb5dbca27623569d48f5edb8e3d8cd780dc09060200160405180910390a250565b6000818152600160205260408120805482916060916105615760405162461bcd60e51b815260206004820152601a60248201527f436572746966696361746520646f6573206e6f742065786973740000000000006044820152606401610498565b8054600282015460018301805460ff90921691819061057f90610a62565b80601f01602080910402602001604051908101604052809291908181526020018280546105ab90610a62565b80156105f85780601f106105cd576101008083540402835291602001916105f8565b820191906000526020600020905b8154815290600101906020018083116105db57829003601f168201915b50505050509050935093509350509193909250565b60006020819052908152604090208054819061062890610a62565b80601f016020809104026020016040519081016040528092919081815260200182805461065490610a62565b80156106a15780601f10610676576101008083540402835291602001916106a1565b820191906000526020600020905b81548152906001019060200180831161068457829003601f168201915b5050505050908060010154908060020154905083565b3360009081526002602052604090205460ff16156107175760405162461bcd60e51b815260206004820152601760248201527f5573657220616c726561647920726567697374657265640000000000000000006044820152606401610498565b604080516060810182528281526000602080830182905282840182905233825260028152838220805460ff19166001179055818152929020815180519293849361076492849201906107f2565b50602082015160018201556040918201516002909101555133907f65e14a3b08845268772acc470a74a6d8b79b9169ac5285d592ffcb6f541278749061043a908590610ac3565b33600090815260208190526040812060020154158015906107ed57503360009081526020818152604080832060029081015484526001909252909120015460ff165b905090565b8280546107fe90610a62565b90600052602060002090601f0160209004810192826108205760008555610866565b82601f1061083957805160ff1916838001178555610866565b82800160010185558215610866579182015b8281111561086657825182559160200191906001019061084b565b50610872929150610876565b5090565b5b808211156108725760008155600101610877565b60006020828403121561089d57600080fd5b81356001600160a01b03811681146108b457600080fd5b9392505050565b6000602082840312156108cd57600080fd5b5035919050565b6000815180845260005b818110156108fa576020818501810151868301820152016108de565b8181111561090c576000602083870101525b50601f01601f19169290920160200192915050565b83815260606020820152600061093a60608301856108d4565b90508215156040830152949350505050565b838152821515602082015260606040820152600061096d60608301846108d4565b95945050505050565b60608152600061098960608301866108d4565b60208301949094525060400152919050565b634e487b7160e01b600052604160045260246000fd5b6000602082840312156109c357600080fd5b813567ffffffffffffffff808211156109db57600080fd5b818401915084601f8301126109ef57600080fd5b813581811115610a0157610a0161099b565b604051601f8201601f19908116603f01168101908382118183101715610a2957610a2961099b565b81604052828152876020848701011115610a4257600080fd5b826020860160208301376000928101602001929092525095945050505050565b600181811c90821680610a7657607f821691505b60208210811415610a9757634e487b7160e01b600052602260045260246000fd5b50919050565b60008219821115610abe57634e487b7160e01b600052601160045260246000fd5b500190565b6020815260006108b460208301846108d456fea26469706673582212203f451749e3460bf69fa02b79421a25c251fd536d39918ccbe8eed596bcf6de9764736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50600380546001600160a01b03191633179055610b18806100326000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c80639a2ab256116100715780639a2ab25614610156578063a2e874e314610178578063beabef6c14610180578063dbfcebb714610198578063f1522800146101a0578063fbb7bcd8146101cb57600080fd5b806324f68902146100ae57806354bbf850146100d9578063660b6cd11461010c57806392426dc114610121578063932365e414610143575b600080fd5b6100c16100bc366004610897565b6101ed565b6040516100d093929190610914565b60405180910390f35b6100fc6100e7366004610897565b60026020526000908152604090205460ff1681565b60405190151581526020016100d0565b61011f61011a36600461094f565b610297565b005b336000908152602081905260409020600201545b6040519081526020016100d0565b61011f610151366004610a00565b61039d565b610169610164366004610a00565b610452565b6040516100d093929190610a19565b6100fc610560565b33600090815260208190526040902060010154610135565b61011f6105a7565b6003546101b3906001600160a01b031681565b6040516001600160a01b0390911681526020016100d0565b6101de6101d9366004610a00565b610751565b6040516100d093929190610a43565b60006020819052908152604090208054819061020890610a6e565b80601f016020809104026020016040519081016040528092919081815260200182805461023490610a6e565b80156102815780601f1061025657610100808354040283529160200191610281565b820191906000526020600020905b81548152906001019060200180831161026457829003601f168201915b5050505050908060010154908060020154905083565b3360009081526002602052604090205460ff16156102fd57604051636381e58960e11b815260206004820152601760248201527f5573657220616c7265616479207265676973746572656400000000000000000060448201526064015b60405180910390fd5b604080516060810182528281526000602080830182905282840182905233825260028152838220805460ff19166001179055818152929020815180519293849361034a92849201906107fe565b50602082015160018201556040918201516002909101555133907f7cbf98b47d31095b76609f26097c26ad8bfabbb9a25dcf0181c8aea7b2e6c9b190610391908590610aa9565b60405180910390a25050565b3360009081526002602052604090205460ff166103f357604051636381e58960e11b8152602060048201526013602482015272155cd95c881b9bdd081c9959da5cdd195c9959606a1b60448201526064016102f4565b3360009081526020819052604081206001018054839290610415908490610abc565b909155505060405181815233907f8f954fcc3c7b6da6bce002b54e8bb4c2f1801c4cc8044c5829582a97b3bbdf2d9060200160405180910390a250565b6000818152600160205260408120805482916060916104b457604051636381e58960e11b815260206004820152601a60248201527f436572746966696361746520646f6573206e6f7420657869737400000000000060448201526064016102f4565b8054600282015460018301805460ff9092169181906104d290610a6e565b80601f01602080910402602001604051908101604052809291908181526020018280546104fe90610a6e565b801561054b5780601f106105205761010080835404028352916020019161054b565b820191906000526020600020905b81548152906001019060200180831161052e57829003601f168201915b50505050509050935093509350509193909250565b33600090815260208190526040812060020154158015906105a257503360009081526020818152604080832060029081015484526001909252909120015460ff165b905090565b33600090815260208190526040902060010154600a1161074f57600061060d6040516bffffffffffffffffffffffff193360601b166020820152426034820152600090819060540160408051601f19818403018152919052805160209091012092915050565b33600081815260208181526040808320600281018690558151606081019092528582529383528282528354949550919391929083019161064c90610a6e565b80601f016020809104026020016040519081016040528092919081815260200182805461067890610a6e565b80156106c55780601f1061069a576101008083540402835291602001916106c5565b820191906000526020600020905b8154815290600101906020018083116106a857829003601f168201915b5050509183525050600160209182018190526000858152818352604090208351815583830151805194955085949193610703938501929101906107fe565b50604091820151600291909101805460ff19169115159190911790555182815233907fa45e416c1f38ded93a574f315ab04ed7340bcbb85c5dbc4fc719c7a5d486542890602001610391565b565b60016020819052600091825260409091208054918101805461077290610a6e565b80601f016020809104026020016040519081016040528092919081815260200182805461079e90610a6e565b80156107eb5780601f106107c0576101008083540402835291602001916107eb565b820191906000526020600020905b8154815290600101906020018083116107ce57829003601f168201915b5050506002909301549192505060ff1683565b82805461080a90610a6e565b90600052602060002090601f01602090048101928261082c5760008555610872565b82601f1061084557805160ff1916838001178555610872565b82800160010185558215610872579182015b82811115610872578251825591602001919060010190610857565b5061087e929150610882565b5090565b5b8082111561087e5760008155600101610883565b6000602082840312156108a957600080fd5b81356001600160a01b03811681146108c057600080fd5b9392505050565b6000815180845260005b818110156108ed576020818501810151868301820152016108d1565b818111156108ff576000602083870101525b50601f01601f19169290920160200192915050565b60608152600061092760608301866108c7565b60208301949094525060400152919050565b63b95aa35560e01b600052604160045260246000fd5b60006020828403121561096157600080fd5b813567ffffffffffffffff8082111561097957600080fd5b818401915084601f83011261098d57600080fd5b81358181111561099f5761099f610939565b604051601f8201601f19908116603f011681019083821181831017156109c7576109c7610939565b816040528281528760208487010111156109e057600080fd5b826020860160208301376000928101602001929092525095945050505050565b600060208284031215610a1257600080fd5b5035919050565b8381528215156020820152606060408201526000610a3a60608301846108c7565b95945050505050565b838152606060208201526000610a5c60608301856108c7565b90508215156040830152949350505050565b600181811c90821680610a8257607f821691505b60208210811415610aa35763b95aa35560e01b600052602260045260246000fd5b50919050565b6020815260006108c060208301846108c7565b60008219821115610add5763b95aa35560e01b600052601160045260246000fd5b50019056fea2646970667358221220541f22a28aa8e0bde4d301732ec67ba36c98636f82c112e444dfc1a254b73c4164736f6c634300080b0033"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"volunteerAddress\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"certificateId\",\"type\":\"uint256\"}],\"name\":\"CertificateApplied\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"volunteerAddress\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"hoursLogged\",\"type\":\"uint256\"}],\"name\":\"ServiceHoursLogged\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"volunteerAddress\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"name\",\"type\":\"string\"}],\"name\":\"VolunteerRegistered\",\"type\":\"event\"},{\"conflictFields\":[{\"kind\":4,\"value\":[3]}],\"inputs\":[],\"name\":\"admin\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"selector\":[4166100032,4048693248],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":2,\"slot\":0,\"value\":[0]}],\"inputs\":[],\"name\":\"applyCertificate\",\"outputs\":[],\"selector\":[513928247,3690785719],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":2,\"slot\":0,\"value\":[0]}],\"inputs\":[],\"name\":\"getCertificateIdByAddress\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"selector\":[1146487608,2453827009],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":3,\"slot\":1,\"value\":[0]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_certificateId\",\"type\":\"uint256\"}],\"name\":\"getCertificateInfo\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"internalType\":\"bool\",\"name\":\"isValid\",\"type\":\"bool\"},{\"internalType\":\"string\",\"name\":\"owner\",\"type\":\"string\"}],\"selector\":[3860920803,2586489430],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":2,\"slot\":0,\"value\":[0]}],\"inputs\":[],\"name\":\"getVolunteerServiceHours\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"selector\":[1238919051,3198938988],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":2,\"slot\":0,\"value\":[0]}],\"inputs\":[],\"name\":\"hasVolunteerCertificate\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"selector\":[4083289597,2733143267],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":3,\"slot\":2,\"value\":[0]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"isUserRegistered\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"selector\":[373257506,1421604944],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":2,\"slot\":0,\"value\":[0]},{\"kind\":2,\"slot\":2,\"value\":[0]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_hours\",\"type\":\"uint256\"}],\"name\":\"logServiceHours\",\"outputs\":[],\"selector\":[993353692,2468570596],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":2,\"slot\":0,\"value\":[0]},{\"kind\":2,\"slot\":2,\"value\":[0]}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"_userName\",\"type\":\"string\"}],\"name\":\"register\",\"outputs\":[],\"selector\":[4072839358,1712024785],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":3,\"slot\":1,\"value\":[0]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"volunteerCertificates\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"owner\",\"type\":\"string\"},{\"internalType\":\"bool\",\"name\":\"isValid\",\"type\":\"bool\"}],\"selector\":[465973863,4223122648],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":3,\"slot\":0,\"value\":[0]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"volunteers\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"name\",\"type\":\"string\"},{\"internalType\":\"uint256\",\"name\":\"serviceHours\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"certificateId\",\"type\":\"uint256\"}],\"selector\":[3861354648,620136706],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADMIN = "admin";

    public static final String FUNC_APPLYCERTIFICATE = "applyCertificate";

    public static final String FUNC_GETCERTIFICATEIDBYADDRESS = "getCertificateIdByAddress";

    public static final String FUNC_GETCERTIFICATEINFO = "getCertificateInfo";

    public static final String FUNC_GETVOLUNTEERSERVICEHOURS = "getVolunteerServiceHours";

    public static final String FUNC_HASVOLUNTEERCERTIFICATE = "hasVolunteerCertificate";

    public static final String FUNC_ISUSERREGISTERED = "isUserRegistered";

    public static final String FUNC_LOGSERVICEHOURS = "logServiceHours";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_VOLUNTEERCERTIFICATES = "volunteerCertificates";

    public static final String FUNC_VOLUNTEERS = "volunteers";

    public static final Event CERTIFICATEAPPLIED_EVENT = new Event("CertificateApplied",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SERVICEHOURSLOGGED_EVENT = new Event("ServiceHoursLogged",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VOLUNTEERREGISTERED_EVENT = new Event("VolunteerRegistered",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    protected Volunteer(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<CertificateAppliedEventResponse> getCertificateAppliedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CERTIFICATEAPPLIED_EVENT, transactionReceipt);
        ArrayList<CertificateAppliedEventResponse> responses = new ArrayList<CertificateAppliedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CertificateAppliedEventResponse typedResponse = new CertificateAppliedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.volunteerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.certificateId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<ServiceHoursLoggedEventResponse> getServiceHoursLoggedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SERVICEHOURSLOGGED_EVENT, transactionReceipt);
        ArrayList<ServiceHoursLoggedEventResponse> responses = new ArrayList<ServiceHoursLoggedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ServiceHoursLoggedEventResponse typedResponse = new ServiceHoursLoggedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.volunteerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.hoursLogged = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<VolunteerRegisteredEventResponse> getVolunteerRegisteredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOLUNTEERREGISTERED_EVENT, transactionReceipt);
        ArrayList<VolunteerRegisteredEventResponse> responses = new ArrayList<VolunteerRegisteredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VolunteerRegisteredEventResponse typedResponse = new VolunteerRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.volunteerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public String admin() throws ContractException {
        final Function function = new Function(FUNC_ADMIN,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public TransactionReceipt applyCertificate() {
        final Function function = new Function(
                FUNC_APPLYCERTIFICATE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForApplyCertificate() {
        final Function function = new Function(
                FUNC_APPLYCERTIFICATE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String applyCertificate(TransactionCallback callback) {
        final Function function = new Function(
                FUNC_APPLYCERTIFICATE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public BigInteger getCertificateIdByAddress() throws ContractException {
        final Function function = new Function(FUNC_GETCERTIFICATEIDBYADDRESS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple3<BigInteger, Boolean, String> getCertificateInfo(BigInteger _certificateId) throws
            ContractException {
        final Function function = new Function(FUNC_GETCERTIFICATEINFO,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(_certificateId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple3<BigInteger, Boolean, String>(
                (BigInteger) results.get(0).getValue(),
                (Boolean) results.get(1).getValue(),
                (String) results.get(2).getValue());
    }

    public BigInteger getVolunteerServiceHours() throws ContractException {
        final Function function = new Function(FUNC_GETVOLUNTEERSERVICEHOURS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Boolean hasVolunteerCertificate() throws ContractException {
        final Function function = new Function(FUNC_HASVOLUNTEERCERTIFICATE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public Boolean isUserRegistered(String param0) throws ContractException {
        final Function function = new Function(FUNC_ISUSERREGISTERED,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public TransactionReceipt logServiceHours(BigInteger _hours) {
        final Function function = new Function(
                FUNC_LOGSERVICEHOURS,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(_hours)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForLogServiceHours(BigInteger _hours) {
        final Function function = new Function(
                FUNC_LOGSERVICEHOURS,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(_hours)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String logServiceHours(BigInteger _hours, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_LOGSERVICEHOURS,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(_hours)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple1<BigInteger> getLogServiceHoursInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_LOGSERVICEHOURS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public TransactionReceipt register(String _userName) {
        final Function function = new Function(
                FUNC_REGISTER,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_userName)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForRegister(String _userName) {
        final Function function = new Function(
                FUNC_REGISTER,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_userName)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String register(String _userName, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_userName)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple1<String> getRegisterInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REGISTER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
        );
    }

    public Tuple3<BigInteger, String, Boolean> volunteerCertificates(BigInteger param0) throws
            ContractException {
        final Function function = new Function(FUNC_VOLUNTEERCERTIFICATES,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple3<BigInteger, String, Boolean>(
                (BigInteger) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (Boolean) results.get(2).getValue());
    }

    public Tuple3<String, BigInteger, BigInteger> volunteers(String param0) throws
            ContractException {
        final Function function = new Function(FUNC_VOLUNTEERS,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple3<String, BigInteger, BigInteger>(
                (String) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue());
    }

    public static Volunteer load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Volunteer(contractAddress, client, credential);
    }

    public static Volunteer deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(Volunteer.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class CertificateAppliedEventResponse {
        public TransactionReceipt.Logs log;

        public String volunteerAddress;

        public BigInteger certificateId;
    }

    public static class ServiceHoursLoggedEventResponse {
        public TransactionReceipt.Logs log;

        public String volunteerAddress;

        public BigInteger hoursLogged;
    }

    public static class VolunteerRegisteredEventResponse {
        public TransactionReceipt.Logs log;

        public String volunteerAddress;

        public String name;
    }
}
