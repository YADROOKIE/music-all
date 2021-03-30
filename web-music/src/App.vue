<template>
  <div id="app">
    <tab></tab>
    <el-container class="box">
      <el-aside>
        <Menu></Menu>
      </el-aside>
      <el-main>
        <router-view v-on:play="play"></router-view>
        <PlayList class="playList" />
        <Lyric />
      </el-main>
    </el-container>
    <el-footer>
    <audio controls>
        <source src="http://localhost:8080/b917c6ab-333c-49cc-b19f-2b8df5f430fa.mp3" >
        您的浏览器不支持 audio 元素。
    </audio>
    <button @click="pause">暂停</button>
      <Player />
    </el-footer>
  </div>
</template>

<script>
import Tab from "./components/Header";
import Menu from "./components/Menu";
import Player from "./components/Player";
import PlayList from "./components/PlayList";
import Lyric from "./components/Lyric";

import DicoverMusic from "./views/discoverMusic/Discover";

export default {
  name: "App",
  components: {
    Tab,
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
      console.log(e)
        this.audio = new Audio();
      this.audio.src = e;
      
      var playPromise = this.audio.play();
    },
    pause(){
      this.audio.pause()
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


