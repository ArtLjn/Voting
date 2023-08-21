package org.Voting.Voting.service;

import cn.hutool.core.lang.Dict;
import org.Voting.Voting.utils.WebaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static org.Voting.Voting.service.VotingService.ABI;

@Service
public class VotingManager {
    @Autowired
    WebaseUtil webaseUtil;
    @Value("${system.Account.address}")
    String userAddress;
    @Value("${system.contract.votingAddress}")
    String votingAddress;
    public Dict addCandidate(String name) {
        List funcParam = new ArrayList();
        funcParam.add(name);
        String funcName = "addCandidate";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,funcParam,ABI,contractName,votingAddress);
        return result;
    }
    public Dict candidates(int id) {
        if(id<0) {
            return null;
        }
        List funcParam = new ArrayList();
        funcParam.add(id);
        String funcName = "candidates";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,funcParam,ABI,contractName,votingAddress);
        return result;
    }
    public Dict candidatesCount() {
        String funcName = "candidatesCount";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,null,ABI,contractName,votingAddress);
        return result;
    }
    public Dict deleteAllCandidateName() {
        String funcName = "deleteAllCandidateName";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,null,ABI,contractName,votingAddress);
        return result;
    }
    public Dict deleteCandidate(int id) {
        if(id<0) {
            return null;
        }
        List funcParam = new ArrayList();
        funcParam.add(id);
        String funcName = "deleteCandidate";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,funcParam,ABI,contractName,votingAddress);
        return result;
    }
    public Dict getAllCandidateName() {
        String funcName = "getAllCandidateName";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,null,ABI,contractName,votingAddress);
        return result;
    }
    public Dict getCandidateName(int id) {
        if(id<0) {
            return null;
        }
        List funcParam = new ArrayList();
        funcParam.add(id);
        String funcName = "getCandidateName";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,funcParam,ABI,contractName,votingAddress);
        return result;
    }
    public Dict getCandidateVotes(int id) {
        if(id<0) {
            return null;
        }
        List funcParam = new ArrayList();
        funcParam.add(id);
        String funcName = "getCandidateVotes";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,funcParam,ABI,contractName,votingAddress);
        return result;
    }
    public Dict getTotalVotes() {
        String funcName = "getTotalVotes";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,null,ABI,contractName,votingAddress);
        return result;
    }
    public Dict vote(int id) {
        if(id<0) {
            return null;
        }
        List funcParam = new ArrayList();
        funcParam.add(id);
        String funcName = "vote";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,funcParam,ABI,contractName,votingAddress);
        return result;
    }
    public Dict voters(String address) {
        List funcParam = new ArrayList();
        funcParam.add(address);
        String funcName = "vote";
        String contractName =  "Voting";
        Dict result =webaseUtil.request(userAddress,funcName,funcParam,ABI,contractName,votingAddress);
        return result;
    }
}
