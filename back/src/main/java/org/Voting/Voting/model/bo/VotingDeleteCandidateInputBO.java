package org.Voting.Voting.model.bo;

import java.lang.Object;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotingDeleteCandidateInputBO {
  private BigInteger _id;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_id);
    return args;
  }
}
