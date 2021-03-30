<template>
  <div>
    <div class="artistTop">
      <img :src="musiclistDetail.coverImgUrl" alt="" />
      <div class="right">
        <h3>
          <div class="artistBorder">歌单</div>
          {{ musiclistDetail.name }}
        </h3>
        <div class="creator">
          <img :src="creator.avatarUrl" alt="" />
          <p>{{ creator.nickname }}</p>
          <span>创建时间: </span>{{ musiclistDetail.createTime | dateFormat }}
        </div>
        <div class="operation">
          <div class="musicPlay" @click="open(allindex)">播放全部</div>
          <div class="collect" @click="collect">
            收藏
          </div>
          <div class="share" @click="share">
            分享
          </div>
        </div>
        <div
          class="tags"
          v-if="musiclistDetail.tags != null && musiclistDetail.tags.length > 0"
        >
          <p>标签:</p>
          <p
            v-for="(item, index) in musiclistDetail.tags"
            :key="index"
            class="tagsName"
          >
            {{ item
            }}<span v-if="index - 0 != musiclistDetail.tags.length - 1">/</span>
          </p>
        </div>
        <p class="artistBriefDesc">
          <span>简介: </span>{{ musiclistDetail.description }}
        </p>
      </div>
    </div>

    <el-menu default-active="1" mode="horizontal">
      <el-menu-item index="1" @click="showMusicLists">歌曲列表</el-menu-item>
      <el-menu-item index="2" @click="showDiscuss"
        >评论({{ musiclistDetail.commentCount }})</el-menu-item
      >
      <el-menu-item index="3" @click="showCollecter">收藏者</el-menu-item>
    </el-menu>

    <!-- 歌曲列表 -->
    <MusicLists :songlist="songlist" v-show="isShowMusicList" />
    <!-- 歌单评论 -->
    <Discuss :songlist="songlist" v-show="isShowDiscuss" :id="id - 0" />
    <!-- 收藏者 -->
    <Collecter :sub="sub" v-show="isShowCollecter" />
  </div>
</template>



<script>
import {
  getSongsDetail,
  songDetail,
  getDiscussDetail,
  getMusicListDetail,
  getSub
} from "../../network/discover.js";
import { indexMixin } from "../../components/PlayMusic";
import MusicLists from "./childMusicListDetail/MusicList.vue";
import Discuss from "./childMusicListDetail/Discuss.vue";
import Collecter from "./childMusicListDetail/Collecter.vue";
export default {
  name: "Musiclistdetail",
  components: {
    MusicLists,
    Discuss,
    Collecter
  },
  data() {
    return {
      id: "",
      time: "",
      musiclistDetail: {}, //歌单详情
      songlist: [], //音乐列表
      isShowMusicList: true,
      isShowDiscuss: false,
      isShowCollecter: false,
      sub: [], //收藏者信息,
      offset: 0,
      creator: {},
      allindex: 0,
      musiclist: []
    };
  },
  mixins: [indexMixin],
  created() {
    this.id = this.$route.query.id;
    getMusicListDetail(this.id).then(res => {
      this.musiclistDetail = res.data.playlist;
      this.creator = res.data.playlist.creator;
      // 循环获取歌曲信息
      for (let i of this.musiclistDetail.trackIds) {
        getSongsDetail(i.id).then(res => {
          let song = new songDetail(res.data.songs);
          this.songlist.push(song);
        });
      }
    });
    //获取收藏者信息
    getSub(this.id).then(res => {
      this.sub = res.data.subscribers;
    });
  },
  methods: {
    showMusicLists() {
      this.isShowMusicList = true;
      this.isShowDiscuss = false;
      this.isShowCollecter = false;
    },
    showDiscuss() {
      this.isShowMusicList = false;
      this.isShowDiscuss = true;
      this.isShowCollecter = false;
    },
    showCollecter() {
      this.isShowMusicList = false;
      this.isShowDiscuss = false;
      this.isShowCollecter = true;
    },
    // 弹出播放替换框
    open(index) {
      this.$confirm("“播放全部”会替换播放列表, 是否继续?", "替换播放列表", {
        confirmButtonText: "继续",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message("音乐缓存需要10~20s，请稍后");
          this.$bus.$emit("playingIndex", index);
          this.playMusic(index);
        })
        .catch(() => {});
    },
    async playMusic(index) {
      this.musiclist = [];
      for (let i in this.songlist) {
        const { data: res } = await getSongsDetail(this.songlist[i].id);
        let song = new songDetail(res.songs);
        this.musiclist.push(song);
        if (i == this.songlist.length - 1) {
          this.PlayMusic(index);
        }
      }
    },
    collect() {
      this.$message.error("需登录才能收藏");
    },
    share() {
      this.$message.error("需登录才能分享");
    }
  }
};
</script>



<style  scoped>
.main {
  width: 100%;
}
.artistTop {
  display: flex;
  width: 100%;
  height: 250px;
}
img {
  width: 260px;
  height: 230px;
}
h3 {
  margin-top: 10px;
}
.right {
  float: right;
  margin-left: 45px;
  height: 100%;
}
.artistBorder {
  text-align: center;
  border: 1px solid red;
  color: red;
  font-weight: bold;
  border-radius: 5px;
  display: inline;
  padding: 5px 15px;
}
.el-menu {
  padding-top: 50px;
  font-size: 18px;
}
.tags {
  display: flex;
  flex-wrap: wrap;
}
.tagsName {
  color: rgb(12, 115, 194);
}
.tagsName span {
  padding: 0px 2px;
}
p:first-child {
  margin-right: 5px;
}
.creator {
  display: flex;
  align-items: center;
}
.creator img {
  width: 30px;
  height: 30px;
  border-radius: 50%;
}
.creator p,
.creator span {
  margin: 0 10px;
  font-size: 18px;
}
.artistBriefDesc {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  margin: 0;
  margin-top: 20px;
}
.operation {
  display: flex;
  margin-top: 15px;
}
.musicPlay {
  border: 1px solid red;
  background-color: red;
  color: white;
  width: 150px;
  height: 35px;
  border-radius: 35px;
  text-align: center;
  line-height: 35px;
  margin-right: 50px;
  cursor: pointer;
}
.collect {
  border: 1px solid rgb(229, 229, 229);
  width: 150px;
  height: 35px;
  border-radius: 35px;
  text-align: center;
  line-height: 35px;
  margin-right: 50px;
  cursor: pointer;
}
.share {
  border: 1px solid rgb(229, 229, 229);
  width: 150px;
  height: 35px;
  border-radius: 35px;
  text-align: center;
  line-height: 35px;
  cursor: pointer;
}
</style>