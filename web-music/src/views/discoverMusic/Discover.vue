<template>
  <div class="box">
    <div class="mainBox">
      <div style="width:20%;display:flex;">
        <el-input v-model="input" placeholder="搜索">
        </el-input>
        <el-button icon="el-icon-search"></el-button>
      </div>
      <div>
        <!-- 轮播图 -->
        <h4>热门歌曲</h4>
        <el-carousel :interval="4000" type="card">
          <el-carousel-item v-for="item in hots" :key="item.id" >
            <img :src="prefix+item.cover" :alt="item.musicName" @click="play(item)" />
          </el-carousel-item>
        </el-carousel>
        <!-- 最新音乐 -->
        <h4 class="newsongs">最新音乐</h4>
        <NewSong />
      </div>
      <div>
        
      </div>
    </div>
    
    <div>

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
      input:'',
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