<template>
  <div class="box">
    <div v-if="albums.length > 0" class="bigBox">
      <div
        v-for="(item, index) in albums"
        :key="index"
        @click="enterAlbum(item.id)"
        class="mainBox"
      >
        <img :src="item.blurPicUrl" alt="" />
        <div class="albums">{{ item.name }}</div>
        <div class="publishTime">{{ item.publishTime | dateFormat }}</div>
      </div>
    </div>
    <div v-else>
      暂无专辑
    </div>
  </div>
</template>



<script>
import { getArtistAlbum } from "../../../network/artistlist";
export default {
  name: "ArtistListAlbum",
  data() {
    return {
      id: "",
      albums: []
    };
  },
  created() {
    this.id = this.$route.query.id;
    // 获取歌手专辑
    getArtistAlbum(this.id).then(res => {
      this.albums = res.data.hotAlbums;
      console.log(this.albums);
    });
  },
  methods: {
    enterAlbum(id) {
      this.$router.push({
        url: "/album",
        query: {
          id
        },
        name: "Album"
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
.bigBox {
  display: flex;
  flex-wrap: wrap;
}
.mainBox {
  width: 180px;
  margin: 50px 50px 10px 0;
  cursor: pointer;
}
img {
  width: 180px;
  height: 180px;
  border: 1px rgb(216, 216, 216) solid;
}
.albums {
  margin: 5px 0;
}
.publishTime {
  font-size: 14px;
  margin-top: 5px;
}
</style>