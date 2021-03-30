<template>
  <div v-if="routerAlive">
    <!-- 列表部分 -->
    <div class="albumListsItem">
      <div
        v-for="(item, index) in albumList"
        :key="index"
        class="albumListsItemBox"
      >
        <img :src="item.picUrl" alt="" @click="enterAlbum(item.id)" />
        <div class="name" @click="enterAlbum(item.id)">{{ item.name }}</div>
        <div class="artistName">
          <div
            v-for="(items, indexs) in item.artists"
            :key="indexs"
            class="artistNameItem"
          >
            <span @click="enterArtists(items.id)">
              {{ items.name }}
            </span>
            <p v-if="indexs - 0 !== item.artists.length - 1">/</p>
          </div>
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
  name: "SearchAlbum",
  data() {
    return {
      offset: 0,
      total: null,
      albumList: [],
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
      this.total = res.data.result.albumCount;
      this.albumList = res.data.result.albums;
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
    // 进入专辑页面
    enterAlbum(id) {
      this.$router.push({
        url: "/album",
        query: {
          id
        },
        name: "Album"
      });
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.offset = this.currentPage - 1;
      getSearchDetail(this.keywords, this.type, this.offset).then(res => {
        this.total = res.data.result.albumCount;
        this.albumList = res.data.result.albums;
      });
    }
  }
};
</script>

<style scoped>
.albumListsItem {
  display: flex;
  flex-wrap: wrap;
}
.albumListsItemBox {
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
  font-size: 18px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
  margin-top: 10px;
}
.artistName {
  display: flex;
  margin-top: 5px;
  font-size: 14px;
  color: rgb(102, 102, 102);
  cursor: pointer;
}
p {
  margin: 0px;
  padding: 0 5px;
}
.artistNameItem {
  display: flex;
}
</style>