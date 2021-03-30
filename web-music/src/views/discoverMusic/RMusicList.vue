<template>
  <div>
    <div class="musicList">
      <div
        v-for="item in recommendResource"
        :key="item.id"
        @mouseover="showCopyWriter(item.id)"
        @mouseleave="showPlayCount(item.id)"
        @click="toPath(item.id, item.trackNumberUpdateTime)"
        class="arae"
      >
        <div class="recommendResourceBox">
          <!-- 歌单图片 -->
          <img :src="item.picUrl || item.coverImgUrl" alt="" />
          <transition name="el-fade-in-linear">
            <div class="transition-box" v-show="isShow(item.id)">
              <!-- 编辑推荐 -->
              <div class="copyWriter">
                {{ item.copywriter }}
              </div>
              <img
                src="../../assets/img/playMusic/bofang.svg"
                alt=""
                class="showVidoPlay"
              />
            </div>
          </transition>
          <!-- 歌单描述 -->
          <span>{{ item.name }}</span>
          <transition name="el-fade-in">
            <!-- 播放次数 -->
            <div
              class="showPlayCount transition-box"
              v-show="isPlayCountShow(item.id)"
            >
              <span class="el-icon-headset"></span>
              <div>{{ item.playCount }}</div>
            </div>
          </transition>
        </div>
      </div>
    </div>
  </div>
</template>



<script>
import { getMusicListDetail } from "../../network/discover.js";
import Musiclistdetail from "./MusicListDetail.vue";
export default {
  name: "RecommendMusic",
  components: {
    Musiclistdetail
  },
  props: {
    recommendResource: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  data() {
    return {
      // 获取图片trackCount
      showNumber: ""
    };
  },
  methods: {
    // 鼠标经过事件
    showCopyWriter(id) {
      this.showNumber = id;
    },
    // 鼠标离开事件
    showPlayCount(id) {
      this.showNumber = "";
    },
    // 判断数字是否与图片trackCount相同
    isShow(id) {
      return id === this.showNumber ? true : false;
    },
    isPlayCountShow(id) {
      return id === this.showNumber ? false : true;
    },
    // 点击路由跳转
    toPath(id, time) {
      // 发起网络请求
      this.$router.push({
        path: "/musiclistdetail",
        name: "detail",
        query: {
          id
        }
      });
    }
  }
};
</script>




<style  scoped>
img {
  width: 250px;
  height: 250px;
  z-index: 9;
}
.recommendResourceBox {
  width: 250px;
  flex-direction: column;
  margin: 25px 0;
  margin-right: 50px;
  position: relative;
}
.musicList {
  display: flex;
  flex-wrap: wrap;
}
.copyWriter {
  position: absolute;
  top: 0px;
  z-index: 99;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  color: #fff;
}
.showPlayCount {
  position: absolute;
  display: flex;
  align-items: center;
  top: 0px;
  right: 0px;
  z-index: 99;
  color: #fff;
  background-color: rgba(0, 0, 0, 0.4);
}
.showVidoPlay {
  position: absolute;
  top: 215px;
  left: 6px;
  width: 30px;
  height: 30px;
  z-index: 99;
}
.arae {
  cursor: pointer;
}
.el-icon-headset {
  margin-right: 3px;
}
</style>