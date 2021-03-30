<template>
  <div>
    <!-- 获取推荐mv头部 -->
    <div class="hotMvTop">
      <h3>推荐MV</h3>
    </div>
    <!-- 推荐mv列表 -->
    <div class="hotMv">
      <div v-for="(item, index) in recommendMv" :key="index" class="hotMvItem">
        <img :src="item.picUrl" alt="" @click="videoPlay(item.id)" />
        <div class="hotMvName" @click="videoPlay(item.id)">{{ item.name }}</div>
        <div class="hotMvArtistName" @click="artistIntroduce(item.artistId)">
          {{ item.artistName }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getrecommendMv } from "../../network/mvlist";
export default {
  name: "RecommendMv",
  data() {
    return {
      recommendMv: []
    };
  },
  created() {
    // 获取热播mv
    getrecommendMv().then(res => {
      this.recommendMv = res.data.result;
    });
  },
  methods: {
    // 播放mv
    videoPlay(id) {
      this.$router.push({
        path: "/mv",
        query: {
          id
        },
        name: "MvPlay"
      });
    },
    // 跳往歌手页面
    artistIntroduce(id) {
      this.$router.push({
        path: "/artist-list",
        query: {
          id: id
        },
        name: "ArtistListDetail"
      });
    }
  }
};
</script>

<style scoped>
img {
  width: 270px;
  height: 150px;
}
.hotMvTop {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  border-bottom: 1px rgb(225, 225, 225) solid;
  margin-top: 30px;
}
.hotMvTop h3 {
  margin-bottom: 10px;
}

.hotMv {
  display: flex;
  flex-wrap: wrap;
  cursor: pointer;
}
.hotMvItem {
  width: 25%;
  margin-top: 30px;
}
.hotMvName,
.hotMvArtistName {
  font-size: 14px;
}
.hotMvArtistName {
  color: rgb(102, 102, 102);
}
.hotMvName {
  width: 270px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>