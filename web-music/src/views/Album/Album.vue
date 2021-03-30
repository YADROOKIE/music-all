<template>
  <div class="main">
    <!-- 头部 -->
    <div class="albumTop">
      <img :src="albums.blurPicUrl" alt="" />
      <div class="right">
        <h3>
          <div class="albumBorder">专辑</div>
          {{ albums.name }}
        </h3>
        <div class="operation">
          <div class="musicPlay" @click="open(allindex)">播放全部</div>
          <div class="collect" @click="collect">
            收藏({{ albumDynamic.subCount }})
          </div>
          <div class="share" @click="share">
            分享({{ albumDynamic.shareCount }})
          </div>
        </div>

        <!-- 歌手 -->
        <div class="artists">
          <p>歌手:</p>
          <p
            v-for="(item, index) in albums.artists"
            :key="index"
            class="artistsName"
            @click="enterArtists(item.id)"
          >
            {{ item.name
            }}<span v-if="index != albums.artists.length - 1">/</span>
          </p>
        </div>
        <div class="company" v-if="albums.company != ''">
          发行公司: {{ albums.company }}
        </div>
        <div class="time">时间: {{ albums.publishTime | dateFormat }}</div>
      </div>
    </div>

    <!-- 列表 -->
    <el-menu default-active="1" mode="horizontal">
      <el-menu-item index="1" @click="showMusicLists">歌曲列表</el-menu-item>
      <el-menu-item index="2" @click="showDiscuss"
        >评论({{ albumDynamic.commentCount }})</el-menu-item
      >
      <el-menu-item index="3" @click="showDesc">专辑详情</el-menu-item>
    </el-menu>
    <!-- 歌曲列表 -->
    <AlbumMusicLists
      :songlist="songlist"
      :albumName="albumName"
      v-show="isShowMusicList"
    />
    <AlbumDiscuss :id="id - 0" v-show="isShowDiscuss" />
    <AlbumDesc :albumDesc="albumDesc" v-show="isShowDesc" />
  </div>
</template>



<script>
import { getAlbumDetail, getAlbumDynamic } from "../../network/album";
import { getSongsDetail, songDetail } from "../../network/discover";
import AlbumDiscuss from "./AlbumDiscuss";
import AlbumDesc from "./AlbumDesc";
import AlbumMusicLists from "./AlbumMusicLists";
import { indexMixin } from "../../components/PlayMusic";

export default {
  name: "Ablum",
  components: {
    AlbumDiscuss,
    AlbumDesc,
    AlbumMusicLists
  },
  data() {
    return {
      id: "",
      albums: [],
      albumDynamic: [],
      isShowMusicList: true,
      isShowDiscuss: false,
      isShowDesc: false,
      albumDesc: "",
      songlist: [],
      albumName: "",
      allindex: 0
    };
  },
  mixins: [indexMixin],
  created() {
    this.id = this.$route.query.id;
    // 获取专辑信息
    getAlbumDetail(this.id).then(res => {
      this.albums = res.data.album;
      this.albumDesc = res.data.album.description;
      this.songlist = res.data.songs;
      this.albumName = res.data.album.name;
    });
    // 获取专辑收藏评论数
    getAlbumDynamic(this.id).then(res => {
      this.albumDynamic = res.data;
    });
  },
  methods: {
    showMusicLists() {
      this.isShowMusicList = true;
      this.isShowDiscuss = false;
      this.isShowDesc = false;
    },
    showDiscuss() {
      this.isShowMusicList = false;
      this.isShowDiscuss = true;
      this.isShowDesc = false;
    },
    showDesc() {
      this.isShowMusicList = false;
      this.isShowDiscuss = false;
      this.isShowDesc = true;
    },
    // 进入歌手页码
    enterArtists(id) {
      this.$router.push({
        path: "/artist-list",
        query: {
          id: id
        },
        name: "ArtistListDetail"
      });
    },
    open(index) {
      this.$confirm("“播放全部”会替换播放列表, 是否继续?", "替换播放列表", {
        confirmButtonText: "继续",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message("音乐缓存需要10~20s，请稍后");
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


<style scoped>
.albumTop {
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
.albumBorder {
  text-align: center;
  border: 1px solid red;
  color: red;
  font-weight: bold;
  border-radius: 5px;
  display: inline;
  padding: 5px 15px;
}
.artists {
  display: flex;
  width: 582px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.artistsName {
  color: rgb(12, 115, 194);
  cursor: pointer;
}
p:first-child {
  margin-right: 5px;
}
.operation {
  display: flex;
  margin-top: 20px;
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
.company {
  margin-bottom: 15px;
}
.time {
  margin-top: 20px;
}
</style>