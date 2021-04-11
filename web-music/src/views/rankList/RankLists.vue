<template>
  <div >
    <h4>个人信息</h4>
    <h4 v-if="user==null">未登录请先登录</h4>
    <div  v-else style="width:40%">
      <el-form label-position="left" label-width="80px" >
          <el-form-item label="名称">
            <el-input v-model="user.name"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
              <el-select v-model="user.age" placeholder="请选择">
                <el-option
                  v-for="item in 100"
                  :key="item"
                  :label="item"
                  :value="item">
                </el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="user.gender" label="男">男</el-radio>
            <el-radio v-model="user.gender" label="女">女</el-radio>
          </el-form-item>
            <el-form-item label="自我介绍">
            <el-input v-model="user.myinfo"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="update()">保存</el-button>
    </div>
  </div>
</template>



<script>
import {userUpdate} from '../../network/user.js'

export default {
  name: "RankLists",
  components: {
  },
  data() {
    return {
      user:null ,
    };
  },
  created() {
    var userStr = sessionStorage.getItem('user')
    if(userStr!=null && userStr!=''){
      this.user = JSON.parse(userStr)
    }  
  },
  methods:{
    update(){
      userUpdate(this.user).then(res=>{
        if(res.data.code===20000){
          this.$message({
              message: "更新成功",
              type: "success"
          });
          sessionStorage.setItem('user',JSON.stringify(this.user))
        }
      })
    }
  }
};
</script>



<style scoped>
.rankListsTop {
  display: flex;
  flex-wrap: wrap;
}
</style>