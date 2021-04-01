<template>
  <div>
    <h3 v-if="user==null">
      未登录请先登录
    </h3>
    <div v-else>
          <el-table
            :data="list"
            stripe
            style="width: 100%">
            <el-table-column
              prop="id"
              label="订单号"
              width="180">
            </el-table-column>
            <el-table-column
              prop="commodity.name"
              label="演唱会名称"
              width="180">
            </el-table-column>
            <el-table-column
              prop="createAt"
              label="创建时间">
            </el-table-column>
            <!-- <el-table-column
              prop="number"
              label="歌曲数量">
            </el-table-column> -->
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
    </div>
  </div>
</template>


<script>
import {userTicketOrder} from '../../network/ticket.js'

export default {
  name: "NewSongs",
  data(){
    return{
      user:null,
      message:'未登录请先登录',
      list:[]
    }
  },
  created(){
      this.load()

  },
  methods:{
    load(){
      var userStr = sessionStorage.getItem('user')
      if(userStr==null || userStr==''){
        this.$message({
              message: "未登录请先登录",
              type: "warning"
        });
        return;
      }
      this.user = JSON.parse(userStr)
      userTicketOrder(this.user.id).then(res=>{
        console.log(res)
        this.list = res.data.data.list
      })
    }
  }
};
</script>



<style scoped>
</style>