<template>
  <div id="app">
    <bar></bar>
    <el-container class="box">
      <el-aside>
        <Menu></Menu>
      </el-aside>
      <el-main>
        <router-view v-on:play="play"></router-view>
      </el-main>
    </el-container>
    <el-footer>
      <audio ref="audio" controls style="width:75%">
          您的浏览器不支持 audio 元素。
      </audio>
      <Player />
    </el-footer>
  </div>
</template>

<script>
import Bar from "./components/Header";
import Menu from "./components/Menu";
import Player from "./components/Player";
import PlayList from "./components/PlayList";
import Lyric from "./components/Lyric";

import DicoverMusic from "./views/discoverMusic/Discover";

export default {
  name: "App",
  components: {
    Bar,
    Menu,
    DicoverMusic,
    Player,
    PlayList,
    Lyric

  },
  data(){
    return{
      audio:null
    }
  },
  created() {
    this.$message("没有备案，网速较慢，请耐心等待");
  },

  methods:{
    play(e){
      // console.log('play')
      this.$refs.audio.src = e
      this.$refs.audio.play()
    }
  }
  
};
</script>

<style  scoped>
body {
  position: relative;
}
#app {
  display: flex;
  flex-direction: column;
  flex: 1;
  height: 100%;
}
.el-footer {
  position: sticky;
  bottom: 0px;
  width: 100%;
  padding: 0px;
  height: 60px;
  z-index: 9999;
}
</style>>


