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
        <div class="title-middle">
          <div class="el-icon-arrow-left" @click="$router.back(-1)"></div>
          <div class="el-icon-arrow-right" @click="forward"></div>
        </div>
      </el-col>

      <el-col :span="5" class="searchBox">
        <el-input
          placeholder="请输入内容"
          v-model="input"
          @keyup.enter.native="goSearch(input)"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="goSearch(input)"
          ></el-button>
        </el-input>
      </el-col>

      <el-col :span="12" class="loginBox">
        <div @click="open">
          <el-avatar icon="el-icon-user-solid"></el-avatar>
        </div>

        <span class="login" @click="open">登录</span>
        <span class="logon" @click="open">注册</span>
      </el-col>
    </el-row>
  </div>
</template>




<script>
import { getSearchDetail } from "../network/discover";
export default {
  name: "Tab",
  data() {
    return {
      input: "",
      type: 1
    };
  },
  methods: {
    open() {
      this.$message({
        message: "登录注册功能正在开发中，敬请期待",
        type: "warning"
      });
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


