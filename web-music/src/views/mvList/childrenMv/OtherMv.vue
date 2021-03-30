<template>
  <div class="box">
    <div class="simiMv">
      <h2>相关推荐</h2>
      <div v-for="(item, index) in simimvs" :key="index" class="simiMvItem">
        <img :src="item.cover" alt="" @click="enterMv(item.id)" />
        <div>
          <p class="simiMvItemName" @click="enterMv(item.id)">
            {{ item.name }}
          </p>
          <div class="simiMvItemArtistName" @click="enterAetist(item.artistId)">
            {{ item.artistName }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>




<script>
import { getSimiMv } from "../../../network/mvlist";
export default {
  name: "OtherMv",
  props: {
    id: {
      type: Number,
      default() {
        return [];
      }
    }
  },
  // 接受父组件注入的routerRefresh
  inject: ["routerRefresh"],
  data() {
    return {
      simimvs: []
    };
  },
  mounted() {
    getSimiMv(this.id).then(res => {
      this.simimvs = res.data.mvs;
    });
  },
  methods: {
    enterMv(id) {
      this.$router.push(
        {
          path: "/mv",
          query: {
            id
          },
          name: "MvPlay"
        },
        this.routerRefresh() // 调用函数
      );
    },
    enterAetist(id) {
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


<style>
.simiMvItem img {
  width: 250px;
  height: 150px;
  cursor: pointer;
}
.simiMvItem {
  display: flex;
  margin-bottom: 50px;
}
.simiMvItemName {
  width: 200px;
  font-size: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  cursor: pointer;
}
.simiMvItemArtistName {
  margin-top: 50px;
  color: rgb(225, 225, 225) solid;
  cursor: pointer;
}
</style>