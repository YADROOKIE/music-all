<template>
  <div>
    <div class="mainBox">
      <div
        v-for="(item, index) in globallists"
        :key="index"
        class="ranlists"
        @mouseover="showPlay(item.id)"
        @mouseleave="hiddenPlay(item.id)"
        @click="toPath(item.id)"
      >
        <img :src="item.coverImgUrl" alt="" />
        <div>{{ item.name }}</div>

        <!-- 鼠标经过事件 -->
        <div v-show="isShow(item.id)">
          <div class="showPlayCount">
            <span class="el-icon-headset"></span>
            <div>{{ item.playCount }}</div>
          </div>
          <img
            src="../../../assets/img/playMusic/bofang.svg"
            alt=""
            class="showVidoPlay"
          />
        </div>
      </div>
    </div>
  </div>
</template>




<script>
export default {
  name: "GlobalRankLists",
  props: {
    globallists: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  data() {
    return {
      showNumber: ""
    };
  },
  methods: {
    showPlay(id) {
      this.showNumber = id;
    },
    hiddenPlay() {
      this.showNumber = "";
    },
    isShow(id) {
      return id === this.showNumber ? true : false;
    },
    // 点击路由跳转
    toPath(id) {
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


<style scoped>
img {
  width: 240px;
  height: 240px;
}
.mainBox {
  display: flex;
  flex-wrap: wrap;
}
.ranlists {
  position: relative;
  margin-right: 30px;
  margin-bottom: 10px;
  cursor: pointer;
  font-size: 18px;
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
  top: 200px;
  left: 14px;
  width: 30px;
  height: 30px;
  z-index: 99;
}
</style>