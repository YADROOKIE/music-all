<template>
  <div>
    <div class="leftBox">
      <div class="top">
        <h3>
          <div class="mvBorder">MV</div>
          {{ mvdetail.name }}
        </h3>
      </div>

      <video :src="mvUrl" preload controls></video>
      <div class="left">
        <h2>评论</h2>
        <MvDiscuss :id="id - 0" />
      </div>
    </div>
  </div>
</template>


<script>
import { getMvUrl, getMvDetail } from "../../../network/mvlist";
import MvDiscuss from "../childrenMv/MvDiscuss";
export default {
  name: "VideoPlay",
  components: {
    MvDiscuss
  },
  props: {
    id: {
      type: Number,
      default() {
        return [];
      }
    }
  },
  data() {
    return {
      mvUrl: "",
      mvdetail: []
    };
  },
  created() {
    // 获取mv播放地址
    getMvUrl(this.id).then(res => {
      this.mvUrl = res.data.data.url;
    });
    // 获取mv数据
    getMvDetail(this.id).then(res => {
      this.mvdetail = res.data.data;
    });
  }
};
</script>


<style scoped>
.leftBox {
  width: 55%;
}

video {
  width: 800px;
  height: 500px;
}

.mvBorder {
  text-align: center;
  border: 1px solid red;
  color: red;
  font-weight: bold;
  border-radius: 5px;
  display: inline;
  padding: 5px 15px;
}
.left {
  width: 800px;
}
h2 {
  padding-bottom: 15px;
  border-bottom: 1px rgb(225, 225, 225) solid;
}
</style>