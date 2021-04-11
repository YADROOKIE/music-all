<template>
  <div>
    <div class="box">
      <div
        v-for="(item, index) in newsonglist"
        :key="index"
        class="listBox"
        :class="{ shadow: index === count }"
        @mousemove="showShadow(index)"
        @mouseleave="closeShadow(index)"
        @dblclick="open(index)"
        @click="play(item)"
      >
        <div class="number">{{ index + 1 }}</div>
        <div class="title">
          <img :src="'http://localhost:8080/'+item.cover" alt="" />
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
</template>


<script>
import { indexMixin } from "../../components/PlayMusic";
import {
  getNewSong,
  getHotList,
  getMusicUrl,
  songDetail,
  getSongsDetail
} from "../../network/discover.js";
export default {
  name: "NewSong",
  data() {
    return {
      newsonglist: [],
      count: "",
      musiclist: []
    };
  },
  mixins: [indexMixin],
  created() {
    getHotList().then(res => {
      this.newsonglist = res.data.data.list
      console.log(this.newsonglist);
    });
  },
  methods: {
    play(item){
      this.$emit("play",item)
    },
    // 进入专辑页面
    enterAlbum(id) {
      this.$router.push({
        url: "/album",
        query: {
          id
        },
        name: "Album"
      });
    },
    // 进入歌手页面
    enterArtists(id) {
      this.$router.push({
        path: "/artist-list",
        query: {
          id: id
        },
        name: "ArtistListDetail"
      });
    },
    showShadow(index) {
      this.count = index;
    },
    closeShadow(index) {
      this.count = "";
    },
    open(index) {
      this.$confirm(
        "“双击播放”会用当前列表的音乐替换播放列表, 是否继续?",
        "替换播放列表",
        {
          confirmButtonText: "继续",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.$message("音乐缓存需要10~20s，请稍后");
          this.$bus.$emit("playingIndex", index);
          this.playMusic(index);
        })
        .catch(() => {});
    },
    async playMusic(index) {
      this.musiclist = [];
      for (let i in this.newsonglist) {
        const { data: res } = await getSongsDetail(this.newsonglist[i].id);
        let song = new songDetail(res.songs);
        this.musiclist.push(song);
        if (i == this.newsonglist.length - 1) {
          this.PlayMusic(index);
        }
      }
    }
  }
};
</script>



<style scoped>
img {
  width: 65px;
  height: 65px;
  cursor: pointer;
}
.box {
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