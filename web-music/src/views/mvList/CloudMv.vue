<template>
  <div>
    <!-- 网易出品mv头部 -->
    <div class="cloudMvTop">
      <h3>网易出品</h3>
      <!-- <div class="hotMvright">
        <p>更多</p>
        <i class="el-icon-arrow-right"></i>
      </div> -->
    </div>
    <!-- 网易出品mv列表 -->
    <div class="cloudMv">
      <div
        v-for="(item, index) in cloudMv"
        :key="index"
        class="cloudMvItem"
        @click="videoPlay(item.id)"
      >
        <img :src="item.cover" alt="" />
        <div class="cloudMvName">{{ item.name }}</div>
      </div>
    </div>
  </div>
</template>


<script>
import { getCloudMv } from "../../network/mvlist";
export default {
  name: "CloudMv",
  data() {
    return {
      cloudMv: []
    };
  },
  created() {
    getCloudMv().then(res => {
      this.cloudMv = res.data.data;
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
img {
  width: 270px;
  height: 150px;
}
.cloudMvTop {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  border-bottom: 1px rgb(225, 225, 225) solid;
  margin-top: 30px;
}
.cloudMvTop h3 {
  margin-bottom: 10px;
}
.cloudMv {
  display: flex;
  flex-wrap: wrap;
  cursor: pointer;
}
.cloudMvItem {
  width: 25%;
  margin-top: 30px;
}
.cloudMvName {
  font-size: 14px;
  width: 270px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>