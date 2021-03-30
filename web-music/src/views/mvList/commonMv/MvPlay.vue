<template>
  <!-- 通过routerAlive实现组件的摧毁和重铸 -->
  <div class="mainBox" v-if="routerAlive">
    <div class="leftBox">
      <VideoPlay :id="id - 0" />
    </div>
    <div class="rightBox">
      <OtherMv :id="id - 0" />
    </div>
  </div>
</template>



<script>
import VideoPlay from "../childrenMv/VideoPlay";

import OtherMv from "../childrenMv/OtherMv";
export default {
  name: "MvPlay",
  components: {
    OtherMv,
    VideoPlay
  },

  data() {
    return {
      id: "",
      // 设定值
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
    //获取mv id
    this.id = this.$route.query.id;
  },
  methods: {
    // 组件的摧毁和重铸
    routerRefresh() {
      this.routerAlive = false;
      this.$nextTick(() => {
        this.routerAlive = true;
      });
    }
  },

  watch: {
    // 监听$route的值
    $route: function(oldVal, newVal) {
      this.id = this.$route.query.id;
    }
  }
};
</script>

<style scoped>
.mainBox {
  display: flex;
}
.leftBox {
  width: 55%;
}
.rightBox {
  width: 45%;
}
</style>