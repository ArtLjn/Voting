<template>
    <div class="main">
        <el-card class="card1">
            <span>总票数 : {{count}}</span>
            <span v-for="(item,index) in CandidateList" :key="index" class="div">
                <span class="candidate">候选人: {{item}}</span>
            </span>
        </el-card>
        <el-card class="card">
            <el-form>
                <el-form-item label="投票给候选人">
                    <el-input v-model="id3" class="input"></el-input>
                    <el-button @click="vote" type="primary" plain>确认</el-button>
                </el-form-item>
                <el-form-item label="添加候选人">
                    <el-input v-model="account" class="input"></el-input>
                    <el-button @click="addCandidate" type="success" plain>添加</el-button>
                </el-form-item>
                <el-form-item label="查询候选人">
                    <el-input v-model="id" class="input"></el-input>
                    <el-button @click="candidates" type="success" plain>查询</el-button>
                </el-form-item>
                <el-form-item label="删除候选人">
                    <el-input v-model="id2" class="input"></el-input>
                    <el-button @click="deleteCandidate" type="danger" plain>删除</el-button>
                </el-form-item>
                <el-form-item label="查询候选人票数">
                    <el-input v-model="id4" class="input"></el-input>
                    <el-button @click="getCandidateVotes" type="success" plain>查询</el-button>
                </el-form-item>
                <el-button @click="deleteAllCandidateName" type="danger" plain>删除所有候选人</el-button>
            </el-form>
        </el-card>
    </div>
</template>
<script>
import axios from 'axios'
export default{
    name:"Voting",
    data() {
        return{
            candidate:'',
            CandidateList:[],
            account:'',
            id:'',
            id2:'',
            id3:'',
            id4:'',
            count:''
        }
    },
    methods:{
        getAllCandidateName() {
            axios
                .get("/api/getAllCandidateName")
                .then((response) => {
                    const Json_string = JSON.stringify(response.data)
                    console.log(Json_string)
                    const data = JSON.parse(Json_string)
                    const result = JSON.parse(data["result"])
                    const formatted_result = JSON.parse(result[0]);
                    console.log(formatted_result);
                    this.CandidateList = formatted_result;
                    console.log(this.CandidateList)
                })
                .catch((error) => {
                    console.log(error)
                    this.$message({
                        type: 'error',
                        message: "error"
                    })
                })
        },
        addCandidate() {
            axios
                .post(`/api/addCandidate?name=${this.account}`)
                .then(() => {
                    this.$message({type:"success",message:"ok"})
                    this.getAllCandidateName();
                })
        },
        candidates() {
            axios
                .post(`/api/candidates?id=${this.id}`)
                .then((response) => {
                    const Json_string = JSON.stringify(response.data)
                    console.log(Json_string)
                    const data = JSON.parse(Json_string)
                    const result = JSON.parse(data["result"])
                    console.log(result)
                    this.$message({
                        type:'success',
                        message:result
                    })
                })
        },
        deleteCandidate() {
            axios
                .get(`/api/deleteCandidate?id=${this.id2}`)
                .then(() => {
                    this.$message({
                        type:'success',
                        message:"ok"
                    })
                    this.getAllCandidateName();
                })
        },
        vote() {
            axios
                .get(`/api/vote?id=${this.id3}`)
                .then(() => {
                    this.$message({
                        type:'success',
                        message:'ok',
                    })
                }).catch((error) => {
                    this.$message({
                        type:"error",
                        message:"你已经投过票了"
                    })
                }) 
        },
        getCandidateVotes() {
            axios
                .get(`/api/getCandidateVotes?id=${this.id4}`)
                .then((response) => {
                    this.$message({
                        type:"success",
                        message:response.data
                    })
                })
        },
        getTotalVotes() {
            axios
                .get("/api/getTotalVotes")
                .then((response) => {
                    const String_count = JSON.stringify(response.data)
                    const data = JSON.parse(String_count)
                    const result = JSON.parse(data["result"])
                    this.count = parseInt(result[0]);
                })
        },
        deleteAllCandidateName() {
            axios
                .get("/api/deleteAllCandidateName")
                .then(() => {
                    this.$message({
                        type:"success",
                        message:"删除成功"
                    })
                    this.getAllCandidateName();
                })
        }
    },
    mounted() {
        this.getAllCandidateName();
        this.getTotalVotes();
    }
    
}
</script>
<style scoped>
.main{
    width: 100%;
    min-height: 100vh;
    height: auto;
    background: linear-gradient(to top right,rgb(225, 194, 199),rgb(102, 193, 194));
    justify-content: center;
    align-items: center;
    display: flex;
    flex-direction: column;
}
.card{
    width: 50%;
    margin: auto;
    min-height: 500px;
    height: auto;
}
.card1{
    height: 100px;
    margin: auto;
}
@media screen and (max-width: 600px) {
    .card{
        width: 90%;
    }
}
.candidate{
    margin: 50px;
}
.input{
    width:90%; margin-right:10px;
}
</style>
