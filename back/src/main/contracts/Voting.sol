// SPDX-License-Identifier: MIT
pragma solidity >=0.4.0 <=0.8.20;
pragma experimental ABIEncoderV2;
contract Voting {
    struct Candidate {
        uint id;
        string name;
        uint voteCount;
    }

    mapping(uint => Candidate) public candidates; //获取候选人数量
    mapping(address => bool) public voters; //输入交易地址查看是否已经投票
    uint public candidatesCount;
    address private owner;

    constructor() { // 对合约进行初始化操作
        owner = msg.sender;
        addCandidate("jovi"); // 候选人1
        addCandidate("xiaoAi"); // 候选人2
        addCandidate("siri"); // 候选人3
    }
    //保存投票发起者
    modifier onlyOwner() {
        require(msg.sender == owner, "Only the owner can perform this action.");
        _;
    }
    function addCandidate(string memory _name) public onlyOwner {
        candidates[candidatesCount] = Candidate(candidatesCount, _name, 0);
        candidatesCount++;
    }
    //输入候选人id进行投票
    function vote(uint _candidateId) public {
        require(!voters[msg.sender], "you are already voted");
        require(_candidateId >= 0 && _candidateId < candidatesCount, "candidate does not exist!");

        candidates[_candidateId].voteCount++;
        voters[msg.sender] = true;
    }
    //根据id查询候选人姓名
    function getCandidateName(uint _id) public view returns (string[] memory) {
        require(_id >= 0 && _id < candidatesCount, "candidate does not exist!");

        string[] memory candidateDetails = new string[](2);
        candidateDetails[0] = candidates[_id].name;
        candidateDetails[1] = uintToString(candidates[_id].voteCount);

        return candidateDetails;
    }
    //根据id删除候选人
    function deleteCandidate(uint _id) public onlyOwner  {
        require(_id >= 0 && _id < candidatesCount, "candidate does not exist!");

        delete candidates[_id];
        // 减少候选人计数器
        candidatesCount--;
    }
    //获取所有候选人姓名
    function getAllCandidateName() public view returns (string[] memory) {
        string[] memory candidateNames = new string[](candidatesCount);

        for (uint i = 0; i < candidatesCount; i++) {
            candidateNames[i] = candidates[i].name;
        }

        return candidateNames;
    }
    //删除所有候选人
    function deleteAllCandidateName() public onlyOwner {
        for (uint i=0;i< candidatesCount;i++) {
            delete candidates[i];
        }
        candidatesCount = 0;
    }
    //获取总投票数
    function getTotalVotes() public view returns (uint) {
        uint totalVotes = 0;

        for (uint i = 0; i < candidatesCount; i++) {
            totalVotes += candidates[i].voteCount;
        }

        return totalVotes;
    }
    function getCandidateVotes(uint _id) public view returns (uint) {
        require(_id >= 0 && _id < candidatesCount, "candidate does not exit!");

        return candidates[_id].voteCount;
    }
    // 辅助函数：将数字转换为字符串
    function uintToString(uint num) internal pure returns (string memory) {
        if (num == 0) {
            return "0";
        }

        uint temp = num;
        uint digits;
        
        while (temp != 0) {
            digits++;
            temp /= 10;
        }

        bytes memory buffer = new bytes(digits);

        while (num != 0) {
            digits -= 1;
            buffer[digits] = bytes1(uint8(48 + num % 10));
            num /= 10;
        }

        return string(buffer);
    }

}