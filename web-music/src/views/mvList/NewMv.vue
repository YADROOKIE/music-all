<template>
  <div>
    <!-- 头部部分 -->
    <div class="mvListTop">
      <h3>最新MV</h3>
      <span
        v-for="(item, index) in nations"
        :key="index"
        @click="changeNation(item)"
        >{{ item }}</span
      >
      <div class="right" @click="enterMvDetail">
        <p>更多</p>
        <i class="el-icon-arrow-right"></i>
      </div>
    </div>

    <!-- 最新mv列表 -->
    <div class="newMv">
      <div v-for="(item, index) in mvlists" :key="index" class="newMvItem">
        <img :src="item.cover" alt="" @click="videoPlay(item.id)" />
        <div class="newMvName" @click="videoPlay(item.id)">{{ item.name }}</div>
        <div class="newMvArtistName" @click="artistIntroduce(item.artistId)">
          {{ item.artistName }}
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import { getNewMv } from "../../network/mvlist";
import MvPlay from "./commonMv/MvPlay";
export default {
  name: "NewMv",
  data() {
    return {
      mvlists: [],
      nations: ["内地", "港台", "欧美", "日本", "韩国"]
    };
  },
  created() {
    //   获取最新mv
    getNewMv().then(res => {
      this.mvlists = res.data.data;
    });
  },
  methods: {
    changeNation(area) {
      getNewMv(area).then(res => {
        this.mvlists = res.data.data;
      });
    },
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
    },
    // 跳往全部mv界面
    enterMvDetail() {
      this.$router.push({
        url: "/mv-list",
        name: "AllMvList"
      });
    }
  }
};
</script>

<style scoped>
.mvListTop {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  border-bottom: 1px rgb(225, 225, 225) solid;
  align-items: flex-end;
}
span {
  font-size: 16px;
  color: rgb(136, 136, 136);
  margin-left: 20px;
  margin-bottom: 10px;
  cursor: pointer;
}
.right {
  position: absolute;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  right: 20px;
  cursor: pointer;
}
img {
  width: 270px;
  height: 150px;
  cursor: pointer;
}
.newMv {
  display: flex;
  flex-wrap: wrap;
}
.newMvItem {
  width: 25%;
  margin-top: 30px;
}
.newMvName,
.newMvArtistName {
  font-size: 14px;
  cursor: pointer;
}
.newMvArtistName {
  color: rgb(102, 102, 102);
}
</style>