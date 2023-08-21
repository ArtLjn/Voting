package org.Voting.Voting.utils;

import cn.hutool.core.lang.Dict;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebaseUtil {
    public static Dict request(
            String userAddress,
            String funcName,
            List funcParam,
            String ABI,
            String contractName,
            String contractAddress

    ) {
        JSONArray abiJson = JSONUtil.parseArray(ABI);
        JSONObject data = JSONUtil.createObj();
        data.set("groupId","1");
        data.set("user",userAddress);
        data.set("contractName", contractName);
        data.set("version"," ");
        data.set("funcName" , funcName);
        data.set("funcParam", funcParam);
        data.set("contractAddress", contractAddress);
        data.set("contractAbi",abiJson);
        data.set("useAes",false);
        data.set("useCns", false);
        data.set("cnsName","");
        String dataString = JSONUtil.toJsonStr(data);
        String responseBody = HttpRequest.post("http://222.132.110.157:5002/WeBASE-Front/trans/handle")
                .header(Header.CONTENT_TYPE," application/json;")
                .body(dataString)
                .execute()
                .body();
        Dict retDict = new Dict();
        retDict.set("result",responseBody);
        return retDict;
    }
}
