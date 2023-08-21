package org.Voting.Voting.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Voting.Voting.model.bo.VotingAddCandidateInputBO;
import org.Voting.Voting.model.bo.VotingCandidatesInputBO;
import org.Voting.Voting.model.bo.VotingDeleteCandidateInputBO;
import org.Voting.Voting.model.bo.VotingGetCandidateNameInputBO;
import org.Voting.Voting.model.bo.VotingGetCandidateVotesInputBO;
import org.Voting.Voting.model.bo.VotingVoteInputBO;
import org.Voting.Voting.model.bo.VotingVotersInputBO;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class VotingService {
  public static final String ABI = org.Voting.Voting.utils.IOUtil.readResourceAsString("abi/Voting.abi");

  public static final String BINARY = org.Voting.Voting.utils.IOUtil.readResourceAsString("bin/ecc/Voting.bin");

//  public static final String SM_BINARY = org.Voting.Voting.utils.IOUtil.readResourceAsString("bin/sm/Voting.bin");

  @Value("${system.contract.votingAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse vote(VotingVoteInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "vote", input.toArgs());
  }

  public CallResponse candidates(VotingCandidatesInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "candidates", input.toArgs());
  }

  public CallResponse getCandidateName(VotingGetCandidateNameInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getCandidateName", input.toArgs());
  }

  public CallResponse candidatesCount() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "candidatesCount", Arrays.asList());
  }

  public TransactionResponse deleteAllCandidateName() throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "deleteAllCandidateName", Arrays.asList());
  }

  public TransactionResponse addCandidate(VotingAddCandidateInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "addCandidate", input.toArgs());
  }

  public CallResponse getTotalVotes() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getTotalVotes", Arrays.asList());
  }

  public TransactionResponse deleteCandidate(VotingDeleteCandidateInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "deleteCandidate", input.toArgs());
  }

  public CallResponse voters(VotingVotersInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "voters", input.toArgs());
  }

  public CallResponse getCandidateVotes(VotingGetCandidateVotesInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getCandidateVotes", input.toArgs());
  }

  public CallResponse getAllCandidateName() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getAllCandidateName", Arrays.asList());
  }
}
