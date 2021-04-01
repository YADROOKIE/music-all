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
      <div style="">
          <div style="width:50%;margin:0 auto;display:flex;">
            <audio  ref="audio" controls style="width:80%" >
                您的浏览器不支持 audio 元素。
            </audio>
            <div>
                <div class="my-font" style="font-size:20px;font-bold">虞兮叹</div>
                <div>闻人</div>
            </div>
            <div>
                <add-to-list v-if="islogin && currItem!=null" :mId="currItem.id"></add-to-list>
            </div>
          </div>
      </div>
      
    </el-footer>
  </div>
</template>

<script>
import Bar from "./components/Header";
import Menu from "./components/Menu";
import AddToList from './components/AddToList'
import DicoverMusic from "./views/discoverMusic/Discover";


export default {
  name: "App",
  components: {
    Bar,
    Menu,
    DicoverMusic,
    AddToList
  },
  data(){
    return{
      audio:null,
      islogin:false,
      currItem:null
    }
  },
  created() {
    var userStr = sessionStorage.getItem('user')
    if(userStr!=null && userStr!=''){
      this.islogin = true
    }
  },

  methods:{
    play(item){
      // console.log('play')
      this.currItem = item
      this.$refs.audio.src = 'http://localhost:8080/'+item.filePath
      this.$refs.audio.play()
    }
  }
  
};
</script>

<style  scoped>
body {
  position: relative;
}
.my-font{
  font-weight: bold;
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
  height: 100px;
  z-index: 9999;
}
</style>>


