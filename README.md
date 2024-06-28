# Webase_Voting

#### 介绍
区块链智能选票springboot接口
### 接口介绍
1.添加候选人，允许post请求，参数name。
``` java
@PostMapping("/addCandidate")
public ResponseEntity<?> addCandidate(@RequestParam("name") String name)
```
![输入图片说明](https://foruda.gitee.com/images/1689575308655574552/5717d200_12252678.png "联想截图_20230717141309.png")
2.candidates 查询id对应的候选人,允许post请求，参数id
```
    @PostMapping("/candidates")
    public ResponseEntity<?> candidates(@RequestParam("id") int id)
```
![输入图片说明](https://foruda.gitee.com/images/1689575387541937847/07d63018_12252678.png "联想截图_20230717141348.png")
3.candidatesCount 获取候选人数量，允许post，get请求，无参数。

```
    @RequestMapping(value = "candidatesCount", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> candidatesCount() throws Exception{
```
![输入图片说明](https://foruda.gitee.com/images/1689575615438004626/cbd7d1ab_12252678.png "联想截图_20230717141419.png")
4.getAllCandidateName 获取所有候选人姓名，允许get，post请求，无参数
```
    @RequestMapping(value = "getAllCandidateName", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getAllCandidateName() throws Exception{
```
![输入图片说明](https://foruda.gitee.com/images/1689575713882757085/184e0e42_12252678.png "联想截图_20230717141440.png")
5.getCandidateName 根据id查询候选人姓名，允许get，post请求，需要id参数。
```
    @RequestMapping(value = "getCandidateName", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getCandidateName(@RequestParam("id") int id) {
```
![输入图片说明](https://foruda.gitee.com/images/1689575808526356348/281dbf55_12252678.png "联想截图_20230717141538.png")
6.vote 投票接口，允许get，post请求，参数id，每人允许投一次
```
    @RequestMapping(value = "vote", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> vote(@RequestParam("id") int id) {
```
![输入图片说明](https://foruda.gitee.com/images/1689575901573461307/5aaf0f9b_12252678.png "联想截图_20230717141746.png")
#### 连续投票，交易失败
![输入图片说明](https://foruda.gitee.com/images/1689576014103738320/4a8dee49_12252678.png "联想截图_20230717141951.png")
7.getCandidateVotes 根据候选人id获取候选人票数,允许get，post请求,参数id。
```
    @RequestMapping(value = "getCandidateVotes", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getCandidateVotes(@RequestParam("id") int id) {
```
![输入图片说明](https://foruda.gitee.com/images/1689576126353973311/9e355f76_12252678.png "联想截图_20230717142109.png")
8.getTotalVotes 获取总投票数,允许get，post请求,无参数
```
    @RequestMapping(value = "getTotalVotes", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getTotalVotes() throws Exception{
```
![输入图片说明](https://foruda.gitee.com/images/1689576192661052137/84aab056_12252678.png "联想截图_20230717142122.png")
9.deleteCandidate 删除指定候选人，允许get，post请求，参数id。
```
    @RequestMapping(value = "deleteCandidate", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> deleteCandidate(@RequestParam("id") int id) {
```
![输入图片说明](https://foruda.gitee.com/images/1689576277572095141/2314f503_12252678.png "联想截图_20230717142156.png")
10.deleteAllCandidateName 删除所有候选人,允许get，post请求，无参数。
```
    @RequestMapping(value = "deleteAllCandidateName", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> deleteAllCandidateName() throws Exception{
```
![输入图片说明](https://foruda.gitee.com/images/1689576351332762977/87215e17_12252678.png "联想截图_20230717142245.png")
