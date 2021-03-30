<template>
  <div>
    <div v-if="artistDetail != null">
      <h3>{{ artistDetail.name }}简介</h3>
      <p class="briefDesc">{{ artistDetail.briefDesc }}</p>
      <div v-for="(item, index) in artistdesc" :key="index">
        <h4 class="bescTop">{{ item.ti }}</h4>
        <p
          class="txt"
          v-for="(items, index) in formatText(item.txt)"
          :key="index"
        >
          {{ items }}
        </p>
      </div>
    </div>
    <div v-else>
      该歌手暂无简介
    </div>
  </div>
</template>



<script>
import { getArtistDesc, getArtistSongs } from "../../../network/artistlist";
export default {
  name: "ArtistListDesc",
  data() {
    return {
      id: "",
      artistdesc: [],
      artistDetail: []
    };
  },
  created() {
    this.id = this.$route.query.id;
    getArtistSongs(this.id).then(res => {
      this.artistDetail = res.data.artist;
    });
    getArtistDesc(this.id).then(res => {
      this.artistdesc = res.data.introduction;
      console.log(this.artistdesc);
    });
  },
  methods: {
    formatText(txt) {
      let newText = txt.split("\n");
      return newText;
    }
  }
};
</script>



<style scoped>
.briefDesc,
.txt {
  text-indent: 2em;
  font-size: 16px;
  color: #666;
}
h3 {
  color: #333;
}
</style>