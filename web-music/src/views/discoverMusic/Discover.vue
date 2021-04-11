<template>
  <div class="box">
    <div class="mainBox">
      <div style="width:20%;display:flex;">
        <el-input v-model="input" placeholder="搜索">
        </el-input>
        <el-button icon="el-icon-search" @click="search()"></el-button>
        <el-dialog title="所有歌曲" :visible.sync="dialogFormVisible">
          <el-table
            :data="sList"
            stripe
            style="width: 100%">
            <el-table-column
              prop="id"
              label="ID"
              width="180">
            </el-table-column>
            <el-table-column
              prop="musicName"
              label="歌区名称"
              width="180">
            </el-table-column>
            <el-table-column
              prop="singer"
              label="歌手">
            </el-table-column>

            <el-table-column
              fixed="right"
              label="操作"
              width="200">
              <template slot-scope="scope">
                <el-button @click="play(scope.row)"  >播放</el-button>
              </template>
            </el-table-column>
          </el-table>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
          </el-dialog>
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
        <div class="abox">
        <div
          v-for="(item, index) in newsonglist"
          :key="index"
          class="listBox"
          :class="{ shadow: index === count }"
          @mousemove="showShadow(index)"
          @mouseleave="closeShadow(index)"
          
          @click="play(item)"
        >
          <div class="number">{{ index + 1 }}</div>
          <div class="title">
            <img class="aimg" :src="'http://localhost:8080/'+item.cover" alt="" />
          </div>

          <div class="mess">
            <span class="itemName">
              {{ item.musicName }}
            </span>
            
            <div class="bottom">
              <span class="itemName" >
              {{ item.singer }}
            </span>
            </div>
          </div>
        </div>
      </div>
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
import {globalSearch} from '../../network/search.js'

export default {
  name: "DicoverMusic",
  components: {
    RecommendMusic,
    IndividuationMv,
    NewSong
  },
  data() {
    return {
      count: "",
      newsonglist: [],
      recommendResource: [],
      input:'',
      hots:[],
      prefix:'http://localhost:8080/',
      sList:[],
      dialogFormVisible:false
    };
  },
  created() {
    // 获取轮播图
    getHotList().then(res=>{
      console.log(res)
      this.hots = res.data.data.list
    })
    getHotList().then(res => {
      this.newsonglist = res.data.data.list
      console.log(this.newsonglist);
    });
  },
  methods: {
    play(item){
    
      this.$emit("play",item)
    },
    search(){
        globalSearch(this.input).then(res=>{
          console.log(res.data)
          this.sList = res.data.data.list
          this.dialogFormVisible = true
        })
    },
    showShadow(index) {
      this.count = index;
    },
    closeShadow(index) {
      this.count = "";
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



.aimg {
  width: 65px;
  height: 65px;
  cursor: pointer;
}
.abox {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  margin-top: 30px;
}
.number {
  height: 65px;
  width: 40px;
  text-align: center;
  line-height: 65px;
}
.listBox {
  display: flex;
  justify-content: center;
  width: 50%;
  cursor: pointer;
  height: 65px;
  margin-bottom: 25px;
  padding: 1px 0px;
}
.title {
  position: relative;
}
.mess {
  position: relative;
  flex: 1;
  height: 65px;
  font-size: 14px;
  margin-left: 10px;
}
.itemName {
  font-size: 16px;
  cursor: pointer;
}
.bottom {
  position: absolute;
  bottom: 0px;
  color: #999;
}
.artistsName {
  cursor: pointer;
}
.shadow {
  background-color: rgb(234, 234, 234);
}
</style>