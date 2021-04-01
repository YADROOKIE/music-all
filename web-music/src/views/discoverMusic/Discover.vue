<template>
  <div class="box">
    <div class="mainBox">
      <!-- 轮播图 -->
      <h4>热门歌曲</h4>
      <el-carousel :interval="4000" type="card">
        <el-carousel-item v-for="item in hots" :key="item.id" >
          <img :src="prefix+item.cover" :alt="item.musicName" @click="play(item)" />
        </el-carousel-item>
      </el-carousel>

      <!-- 推荐歌单 -->
      <!-- <h4>推荐歌单</h4> -->
      <!-- <RecommendMusic :recommendResource="recommendResource" /> -->

      <!-- 独家放送 -->
      <!-- <h4>独家放送</h4> -->
      <!-- <IndividuationMv /> -->

      <!-- 最新音乐 -->
      <h4 class="newsongs">最新音乐</h4>
      <NewSong />
    </div>
  </div>
</template>


<script>
import { getHotList } from "../../network/discover.js";
import RecommendMusic from "./RMusicList.vue";
import IndividuationMv from "./IndividuationMv.vue";
import NewSong from "./NewSong.vue";
export default {
  name: "DicoverMusic",
  components: {
    RecommendMusic,
    IndividuationMv,
    NewSong
  },
  data() {
    return {
      recommendResource: [],
      banners: [
        {id:1,imageUrl:'http://10.20.57.123:8080/38578335-806d-4917-a4fb-d011963b6598.jpeg'},
        {id:2,imageUrl:'http://10.20.57.123:8080/cea87eab-349b-452d-a5cb-2c3ae115bdbc.jpg'}
      ],
      hots:[],
      prefix:'http://localhost:8080/',
    };
  },
  created() {
    // 获取轮播图
    getHotList().then(res=>{
      console.log(res)
      this.hots = res.data.data.list
    })
  },
  methods: {
    play(item){
    
      this.$emit("play",item)
    }
  }
};
</script>


<style scoped>
.mainBox {
  margin: 0 auto;
}
img {
  width: 500px;
  height: 300px;
}
.newsongs {
  padding-bottom: 30px;
  border-bottom: 1px solid #cdcdcd;
}
h4 {
  margin: 0px;
  padding: 20px 0;
  border-bottom: 1px solid rgb(220, 220, 220);
}
</style>