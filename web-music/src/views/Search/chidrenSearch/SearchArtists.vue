<template>
  <div v-if="routerAlive">
    <!-- 列表部分 -->
    <div class="artistsListsItem">
      <div
        v-for="(item, index) in artistList"
        :key="index"
        class="artistsListsItemBox"
      >
        <img :src="item.picUrl" alt="" @click="enterArtists(item.id)" />
        <div class="name" @click="enterArtists(item.id)">{{ item.name }}</div>
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
  name: "SearchArtists",
  data() {
    return {
      offset: 0,
      total: null,
      artistList: [],
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
      this.total = res.data.result.artistCount;
      this.artistList = res.data.result.artists;
    });
  },
  methods: {
    // 进入歌手页面
    enterArtists(id) {
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
        this.total = res.data.result.artistCount;
        this.artistList = res.data.result.artists;
      });
    }
  }
};
</script>


<style scoped>
.artistsListsItem {
  display: flex;
  flex-wrap: wrap;
}
.artistsListsItemBox {
  width: 20%;
  margin-top: 30px;
  margin-bottom: 20px;
}
img {
  width: 250px;
  height: 180px;
  cursor: pointer;
}
.name {
  width: 250px;
  font-size: 14px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
  margin-top: 10px;
}
</style>