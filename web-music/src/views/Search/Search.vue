<template>
  <div v-if="routerAlive">
    <div class="searchTop">
      <span
        v-for="(item, index) in form"
        :key="index"
        @click="changeForm(item.style)"
        >{{ item.name }}</span
      >
    </div>

    <SearchSongList v-if="type === 1" />
    <SearchAlbum v-if="type === 10" />
    <SearchArtists v-if="type === 100" />
    <SearchPlayList v-if="type === 1000" />
    <SearchUser v-if="type === 1002" />
    <SearchMv v-if="type === 1004" />
  </div>
</template>


<script>
import { getSearchDetail, searchSongDetail } from "../../network/discover";
import SearchSongList from "./chidrenSearch/SearchSongList";
import SearchAlbum from "./chidrenSearch/SearchAlbum";
import SearchArtists from "./chidrenSearch/SearchArtists";
import SearchPlayList from "./chidrenSearch/SearchPlayList";
import SearchUser from "./chidrenSearch/SearchUser";
import SearchMv from "./chidrenSearch/SearchMv";

export default {
  name: "Search",
  components: {
    SearchSongList,
    SearchAlbum,
    SearchArtists,
    SearchPlayList,
    SearchUser,
    SearchMv
  },
  data() {
    return {
      form: [
        { style: 1, name: "单曲" },
        { style: 10, name: "专辑" },
        { style: 100, name: "歌手" },
        { style: 1000, name: "歌单" },
        { style: 1002, name: "用户" },
        { style: 1004, name: "MV" }
      ],
      type: 1,
      keywords: "",
      total: 0,
      routerAlive: true
    };
  },

  // 向子组件发射routerRefresh
  provide() {
    return {
      routerRefresh: this.routerRefresh
    };
  },
  created() {
    this.keywords = this.$route.query.keywords;
    this.$bus.$on("creatItem", keywords => {
      this.routerRefresh();
    });
  },
  methods: {
    // 组件的摧毁和重铸
    routerRefresh() {
      this.routerAlive = false;
      this.$nextTick(() => {
        this.routerAlive = true;
      });
    },
    changeForm(type) {
      this.type = type;
      this.$router.push({
        url: "/search",
        name: "Search",
        query: {
          keywords: this.keywords,
          type: this.type
        }
      });
    }
  },

  watch: {
    $route: function(oldVal, newVal) {
      this.keywords = this.$route.query.keywords;
    }
  }
};
</script>




<style scoped>
.searchTop {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  border-bottom: 1px rgb(225, 225, 225) solid;
  align-items: flex-end;
}
.searchTop span {
  font-size: 16px;
  color: rgb(136, 136, 136);
  margin-left: 20px;
  margin-bottom: 10px;
  cursor: pointer;
}
</style>