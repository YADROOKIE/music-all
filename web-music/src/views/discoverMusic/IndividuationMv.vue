<template>
  <div>
    <div class="mainBox">
      <div
        v-for="item in mvlist"
        :key="item.id"
        class="smallBox"
        @click="enterMv(item.id)"
      >
        <img :src="item.picUrl" alt="" />
        <div class="copywriter">{{ item.copywriter }}</div>
        <div class="imgBox">
          <img src="../../assets/img/shiping.svg" alt="" />
        </div>
      </div>
    </div>
  </div>
</template>




<script>
import { getIndividuationMv } from "../../network/discover.js";
export default {
  name: "IndividuationMv",
  data() {
    return {
      mvlist: []
    };
  },
  created() {
    getIndividuationMv().then(res => {
      this.mvlist = res.data.result;
    });
  },
  methods: {
    // 点击进入mv
    enterMv(id) {
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



><style  scoped>
img {
  width: 400px;
  height: 150px;
}
.mainBox {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  height: 200px;
  margin-top: 30px;
}
.smallBox {
  position: relative;
  width: 400px;
  padding-right: 100px;
  cursor: pointer;
}
.copywriter {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.imgBox {
  position: absolute;
  width: 31px;
  height: 31px;
  left: 3px;
  top: 0px;
  border-radius: 50%;
  background: rgba(0, 0, 0, 0.2);
}
.imgBox img {
  width: 30px;
  height: 30px;
}
</style>