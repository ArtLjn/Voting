package org.Voting.Voting.controller;

import org.Voting.Voting.service.VotingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VotingController {
    private static VotingManager votingManager;
    @Autowired
    public VotingController(VotingManager votingManager) {
        this.votingManager = votingManager;
    }
    @PostMapping("/addCandidate")
    public ResponseEntity<?> addCandidate(@RequestParam("name") String name) {
        try{
            votingManager.addCandidate(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("ok");
    }
    @PostMapping("/candidates")
    public ResponseEntity<?> candidates(@RequestParam("id") int id) throws Exception{
        return ResponseEntity.ok(votingManager.candidates(id));
    }
    @RequestMapping(value = "candidatesCount", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> candidatesCount() throws Exception{
        return ResponseEntity.ok(votingManager.candidatesCount());
    }
    @RequestMapping(value = "deleteAllCandidateName", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> deleteAllCandidateName() throws Exception{
        return ResponseEntity.ok(votingManager.deleteAllCandidateName());
    }
    @RequestMapping(value = "deleteCandidate", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> deleteCandidate(@RequestParam("id") int id) {
        try{
            votingManager.deleteCandidate(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("ok");
    }
    @RequestMapping(value = "getAllCandidateName", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getAllCandidateName() throws Exception{
        return ResponseEntity.ok(votingManager.getAllCandidateName());
    }
    @RequestMapping(value = "getCandidateName", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getCandidateName(@RequestParam("id") int id) {
        return ResponseEntity.ok(votingManager.getCandidateName(id));
    }
    @RequestMapping(value = "getCandidateVotes", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getCandidateVotes(@RequestParam("id") int id) {
        return ResponseEntity.ok(votingManager.getCandidateVotes(id));
    }
    @RequestMapping(value = "getTotalVotes", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getTotalVotes() throws Exception{
        return ResponseEntity.ok(votingManager.getTotalVotes());
    }
    @RequestMapping(value = "vote", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> vote(@RequestParam("id") int id) {
        return ResponseEntity.ok(votingManager.vote(id));
    }
    @RequestMapping(value = "voters", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> voters(@RequestParam("address") String address) {
        return ResponseEntity.ok(votingManager.voters(address));
    }
}
