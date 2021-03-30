<template>
  <div v-if="routerAlive">
    <el-table :data="userList" stripe :show-header="false">
      <el-table-column width="1000px">
        <template slot-scope="scope">
          <div class="name">
            <img :src="scope.row.avatarUrl" alt="" />
            <span>{{ scope.row.nickname }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <span>歌单: </span>{{ scope.row.playlistCount }}
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <span>粉丝: </span>{{ scope.row.followeds }}
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="50"
    >
    </el-pagination>
  </div>
</template>




<script>
import { getSearchDetail } from "../../../network/discover";

export default {
  name: "SearchUser",
  data() {
    return {
      offset: 0,
      total: 0,
      userList: [],
      currentPage: 1,
      routerAlive: true
    };
  },
  // 接受父组件注入的routerRefresh
  inject: ["routerRefresh"],
  created() {
    this.keywords = this.$route.query.keywords;
    this.type = this.$route.query.type;
    this.$bus.$on("creatItem", keywords => {
      this.keywords = keywords;
      this.routerRefresh();
    });
    getSearchDetail(this.keywords, this.type, this.offset).then(res => {
      this.total = res.data.result.userprofileCount;
      this.userList = res.data.result.userprofiles;
    });
  },
  methods: {
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.offset = this.currentPage - 1;
      getSearchDetail(this.keywords, this.type, this.offset).then(res => {
        this.total = res.data.result.userprofileCount;
        this.userList = res.data.result.userprofiles;
      });
    }
  }
};
</script>

<style scoped>
img {
  width: 80px;
  height: 80px;
  margin-right: 10px;
}
.el-pagination {
  margin-top: 25px;
}
.name {
  display: flex;
  align-items: center;
}
span {
  margin-right: 8px;
}
</style>