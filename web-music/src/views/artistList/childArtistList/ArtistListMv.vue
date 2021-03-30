<template>
  <div>
    <div class="box" v-if="mvs.length != 0">
      <div
        class="mvsBox"
        v-for="(item, index) in mvs"
        :key="index"
        @click="videoPlay(item.id)"
      >
        <img :src="item.imgurl" alt="" />
        <div class="mvsName">{{ item.name }}</div>
      </div>
    </div>
    <h3 v-else>暂无MV</h3>
  </div>
</template>



<script>
import { getArtistMv } from "../../../network/artistlist";
export default {
  name: "ArtistListMv",
  data() {
    return {
      id: "",
      mvs: []
    };
  },
  created() {
    this.id = this.$route.query.id;
    // 获取歌手mv
    getArtistMv(this.id).then(res => {
      this.mvs = res.data.mvs;
      console.log(this.mvs);
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
    }
  }
};
</script>



<style scoped>
.box {
  display: flex;
  flex-wrap: wrap;
}
.mvsBox {
  width: 200px;
  margin: 50px 50px 10px 0;
  cursor: pointer;
}
img {
  width: 200px;
  height: 150px;
}
.mvsName {
  margin-top: 10px;
  font-size: 14px;
}
</style>