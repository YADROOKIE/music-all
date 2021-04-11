<template>
  <div>
    <el-row>
      <el-col :span="5">
        <div class="title-left">
          <ul>
            <li></li>
            <li></li>
            <li></li>
          </ul>
        </div>
      </el-col>
      <el-col :span="3">
        <div class="title-middle" >
          <div class="el-icon-arrow-left" @click="$router.back(-1)"></div>
          <div class="el-icon-arrow-right" @click="forward"></div>
        </div>

      </el-col>

      <el-col :span="5" class="searchBox">
        哈哈哈哈哈音乐网站
      </el-col>

      <el-col :span="12" class="loginBox">
        <div >
          
          <el-avatar icon="el-icon-user-solid" v-if="user==null" ></el-avatar>
          <el-avatar v-else :src="user.avatar"></el-avatar>
        </div>

        <div  v-if="user==null">
          <span class="login" @click="openlogin" >登录</span>
          <span class="logon" @click="open">注册</span>
        </div>
        <div v-else> 
            <button v-if="user!=null && user.stuNo=='admin'" @click="admin()">管理</button>
            <span  >{{user.name}}</span>
            <span class="login" style="color:grey"  @click="logout">退出</span>
        </div>
      </el-col>

    </el-row>
    <el-dialog title="登录" :visible.sync="dialogFormVisible" width="30%" center >
      <el-form :model="form">
        <el-form-item label="账号" >
          <el-input v-model="form.stuNo" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" >
          <el-input v-model="form.password" autocomplete="off" type="password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="login">登 录</el-button>
      </div>
    </el-dialog>
    <el-dialog title="注册" :visible.sync="dialogRFormVisible" width="30%" center >
      <el-form :model="rForm">
        <el-form-item label="账号" >
          <el-input v-model="rForm.stuNo" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" >
          <el-input v-model="rForm.password" autocomplete="off" type="password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" >
          <el-input v-model="rForm.cpassword" autocomplete="off" type="password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogRFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="register">注 册</el-button>
      </div>
    </el-dialog>
  </div>
</template>




<script>
import { getSearchDetail } from "../network/discover";
import {userLogin, userRegister  }  from '../network/user';
export default {
  created(){
      var userStr = sessionStorage.getItem('user')
      if(userStr!=null && userStr!=''){
        this.user = JSON.parse(userStr)
      }
  },
  data() {
    return {
      user : null,
      input: "",
      type: 1,
      dialogFormVisible:false,
      dialogRFormVisible:false,
      form:{name:'',region:''},
      rForm:{},
      formLabelWidth: '120px'
    };
  },
  methods: {
    logout(){
      sessionStorage.removeItem('user')
      this.user = null
      this.$message({
            message: "退出登录",
            type: "info"
      });
    },
    login(){
      userLogin(this.form).then(res=>{
        console.log(res)
        if(res.data.code===20000){
          this.$message({
            message: "登录成功",
            type: "success"
          });
          this.user = res.data.data.user
          sessionStorage.setItem('user',JSON.stringify(res.data.data.user))
          this.dialogRFormVisible = false
        }else{
          this.$message({
            message: "登录失败，账号或密码错误",
            type: "danger"
          });
        }
      })
      this.dialogFormVisible = false
    },
    openlogin(){
      console.log('login')
      this.dialogFormVisible = true
    },
    register(){
      userRegister(this.rForm).then(res=>{
        console.log(res)
        if(res.data.code===20000){
          this.$message({
            message: "注册成功",
            type: "success"
          });
          this.dialogRFormVisible = false
        }else{
          this.$message({
            message: "注册失败，账号已存在",
            type: "danger"
          });
        }
      })
    },
    open() {
      this.dialogRFormVisible = true

    },
    goSearch(keywords) {
      this.$router.push(
        {
          url: "/search",
          name: "Search",
          query: {
            keywords,
            type: this.type
          }
        },
        this.$bus.$emit("creatItem", keywords)
      );
    },
    forward() {
      this.$router.go(1);
    },
    admin(){
      this.$router.push('Admin')
    }
  },

  watch: {
    $route: function(oldVal, newVal) {
      this.keywords = this.$route.query.keywords;
    },
    $route: function(oldVal, newVal) {
      this.type = this.$route.query.type;
    }
  }
};
</script>


<style scoped>
.el-row {
  height: 60px;
  display: flex;
  background-color: rgb(249, 249, 249);
}

ul li {
  float: left;
  width: 24px;
  height: 24px;
  margin-left: 20px;
  border-radius: 50%;
}
li:nth-child(1) {
  margin-left: 0px;
  background-color: #ed655a;
}
li:nth-child(2) {
  background-color: #e0c04c;
}
li:nth-child(3) {
  background-color: #72be47;
}

.title-middle {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}
.title-middle div {
  width: 21px;
  height: 21px;
}
.searchBox {
  display: flex;
  align-items: center;
  justify-content: center;
}
.loginBox {
  display: flex;
  align-items: center;
  justify-content: center;
}
.login,
.logon {
  font-size: 14px;
  padding-left: 10px;
  cursor: pointer;
}
.el-avatar {
  cursor: pointer;
}
.el-icon-arrow-left {
  margin-right: 10px;
}
.el-icon-arrow-left,
.el-icon-arrow-right {
  font-size: 20px;
  cursor: pointer;
}
</style>


