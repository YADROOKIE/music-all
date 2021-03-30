<template>
  <div class="main">
    <div class="artistTop">
      <img :src="artistDetail.img1v1Url" alt="" />
      <div class="right">
        <h3>
          <div class="artistBorder">歌手</div>
          {{ artistDetail.name }}
        </h3>
        <p><span>单曲数: </span>{{ artistDetail.musicSize }}</p>
        <p><span>专辑数: </span>{{ artistDetail.albumSize }}</p>
        <p><span>MV数: </span>{{ artistDetail.mvSize }}</p>
        <p class="artistBriefDesc" v-if="artistDetail.briefDesc != null">
          <span>简介: </span>{{ artistDetail.briefDesc }}
        </p>
      </div>
    </div>

    <!-- 跳转栏 -->
    <div class="nav">
      <span
        v-for="(item, index) in artist"
        :key="index"
        class="spanBox"
        @click="toPath(item.value, item.addtion, index)"
        :class="{ checkColor: active == index }"
        >{{ item.name }}</span
      >
    </div>
    <HotSongs :songlists="songlists" v-show="isActive" />
    <keep-alive>
      <router-view> </router-view>
    </keep-alive>
  </div>
</template>


<script>
import {
  getArtistSongs,
  getArtistHotSongs,
  songDetail
} from "../../network/artistlist";
import HotSongs from "./childArtistList/HotSongs.vue";
export default {
  name: "ArtistListDetail",
  components: {
    HotSongs
  },
  data() {
    return {
      id: "",
      artistDetail: [],
      artist: [
        {
          value: "/artist-list",
          name: "热门作品",
          addtion: "ArtistListDetail"
        },
        {
          value: "/artist-list/album",
          name: "所有专辑",
          addtion: "ArtistListAlbum"
        },
        { value: "/artist-list/mv", name: "相关MV", addtion: "ArtistListMv" },
        {
          value: "/artist-list/desc",
          name: "歌手详情",
          addtion: "ArtistListDesc"
        }
      ],
      isActive: true,
      active: "",
      songlists: []
    };
  },
  created() {
    this.id = this.$route.query.id;
    // 获取歌手单曲
    getArtistSongs(this.id).then(res => {
      this.artistDetail = res.data.artist;
    });
    // 获取歌手热门50首
    getArtistHotSongs(this.id).then(res => {
      this.songlists = res.data.songs;
    });
  },
  methods: {
    toPath(path, additon, index, artistname) {
      this.artistname = artistname;
      this.$router.push({
        path: path,
        query: {
          id: this.id
        },
        name: additon
      });
      this.active = index;
      if (index !== 0) {
        this.isActive = false;
      } else {
        this.isActive = true;
      }
    }
  }
};
</script>


<style scoped>
.main {
  width: 100%;
}
.artistTop {
  display: flex;
  width: 100%;
  height: 250px;
}
img {
  width: 260px;
  height: 230px;
}
h3 {
  margin-top: 10px;
}
.right {
  float: right;
  margin-left: 45px;
  height: 100%;
}
.artistBriefDesc {
  overflow: hidden;
  text-overflow: ellipsis;
}
.artistBorder {
  text-align: center;
  border: 1px solid red;
  color: red;
  font-weight: bold;
  border-radius: 5px;
  display: inline;
  padding: 5px 15px;
}
p {
  width: 1200px;
  margin: 0;
  padding-top: 16px;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
p span {
  color: rgb(12, 115, 206);
}
.nav {
  display: flex;
  flex-wrap: wrap;
}
.spanBox {
  text-align: center;
  line-height: 80px;
  font-size: 20px;
  width: 140px;
  height: 80px;
  cursor: pointer;
}
.checkColor {
  color: rgb(45, 140, 240);
  border-bottom: 2px rgb(45, 140, 240) solid;
}
</style>