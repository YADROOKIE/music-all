<template>
  <div v-if="routerAlive">
    <!-- mv列表部分 -->
    <div class="mvListsItem">
      <div v-for="(item, index) in mvList" :key="index" class="mvListsItemBox">
        <img :src="item.cover" alt="" @click="enterMv(item.id)" />
        <div class="name" @click="enterMv(item.id)">{{ item.name }}</div>
        <div class="artistName" @click="enterArtist(item.artistId)">
          {{ item.artistName }}
        </div>
      </div>
    </div>

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
  name: "SearchMv",
  data() {
    return {
      offset: 0,
      total: 0,
      mvList: [],
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
      this.total = res.data.result.mvCount;
      this.mvList = res.data.result.mvs;
    });
  },
  methods: {
    // 进入mv界面
    enterMv(id) {
      this.$router.push({
        path: "/mv",
        query: {
          id
        },
        name: "MvPlay"
      });
    },
    // 进入歌手界面
    enterArtist(id) {
      this.$router.push({
        path: "/artist-list",
        query: {
          id: id
        },
        name: "ArtistListDetail"
      });
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.offset = this.currentPage - 1;
      getSearchDetail(this.keywords, this.type, this.offset).then(res => {
        this.total = res.data.result.mvCount;
        this.mvList = res.data.result.mvs;
      });
    }
  }
};
</script>

<style scoped>
.mvListsItem {
  display: flex;
  flex-wrap: wrap;
}
.mvListsItemBox {
  width: 20%;
  margin-top: 30px;
  margin-bottom: 20px;
}
img {
  width: 250px;
  height: 150px;
  cursor: pointer;
}
.name {
  width: 250px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
  margin-top: 10px;
}
.artistName {
  width: 300px;
  margin-top: 5px;
  font-size: 14px;
  color: rgb(102, 102, 102);
  cursor: pointer;
}
</style>