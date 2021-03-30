<template>
  <div v-if="routerAlive">
    <el-table :data="playList" stripe @row-click="open" :show-header="false">
      <el-table-column width="1000px">
        <template slot-scope="scope">
          <div class="name">
            <img :src="scope.row.coverImgUrl" alt="" />
            <span>{{ scope.row.name }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope"> {{ scope.row.trackCount }}首 </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <span class="by">by</span> {{ scope.row.creator.nickname }}</template
        >
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
  name: "SearchPlayList",
  data() {
    return {
      offset: 0,
      total: 0,
      playList: [],
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
      this.total = res.data.result.playlistCount;
      this.playList = res.data.result.playlists;
    });
  },
  methods: {
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.offset = this.currentPage - 1;
      getSearchDetail(this.keywords, this.type, this.offset).then(res => {
        this.total = res.data.result.playlistCount;
        this.playList = res.data.result.playlists;
      });
    },
    open(row, event, column) {
      this.$router.push({
        url: "/musiclistdetail",
        name: "detail",
        query: {
          id: row.id
        }
      });
      console.log(row.id);
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
.name {
  display: flex;
  align-items: center;
}
.by {
  padding-right: 5px;
  color: rgb(195, 195, 195);
}
.el-pagination {
  margin-top: 25px;
}
.el-table {
  margin-top: 30px;
  cursor: pointer;
}
</style>