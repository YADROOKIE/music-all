<template>
  <div>
      <div style="width:20%;display:flex;">
        <el-input v-model="input" placeholder="搜索">
        </el-input>
        <el-button icon="el-icon-search" @click="search()"></el-button>
      </div>
      <el-row :gutter="20">
        <el-col class="" :span="6" v-for="item  in list" :key="item.id">
          <div class="mborder">
            <img style="width: 100%;display: block;" :src="'http://localhost:8080/'+item.cover" class="image">
            <div style="padding: 14px;">
              <span>{{item.name}}</span>
              <div class="bottom clearfix">
                <div style="margin-top:2px;color:grey;">{{item.createAt}}</div>
                <el-button  @click="buy(item.id)">购买</el-button>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    <div v-if="list.length==0">
      没有任何结果
    </div>
  </div>
</template>



<script>
import {listTicket,buyTicket} from '../../network/ticket.js'
import {ticketSearch}  from '../../network/search.js'
export default {

  name: "MvList",
  data(){
    return{
      list:[],
      param:{},
      input:''
    }
  },
  created(){
    listTicket().then(res=>{
      console.log(res)
      this.list = res.data.data.list 
    })
  },
  methods:{
    buy(id){
      var userStr = sessionStorage.getItem('user')
      if(userStr==null || userStr==''){
        this.$message({
              message: "未登录请先登录",
              type: "warning"
        });
        return;
      }
      var user = JSON.parse(userStr)
      this.param.ticketId = id
      this.param.userId =  user.id
      buyTicket(this.param).then(res=>{
        console.log(res)
        if(res.data.code===20000){
          this.$message({
              message: "购买成功",
              type: "success"
          });
        }else{
          this.$message({
              message: "购买失败",
              type: "danger"
          });
        }
      })
    },
    search(){
      if(this.input==''){
        listTicket().then(res=>{
          console.log(res)
          this.list = res.data.data.list 
        })
        return
      }
      ticketSearch(this.input).then(res=>{
         this.list = res.data.data.list 
      })
    }
  }
};
</script>



<style scoped>
.mborder{
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
</style>