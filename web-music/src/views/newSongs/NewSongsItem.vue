<template>
  <div>
    <!-- 头部部分 -->
    <div class="top">
      <h3>最新音乐</h3>
      <span
        v-for="(item, index) in nations"
        :key="index"
        @click="changeType(item.value)"
        >{{ item.name }}</span
      >
    </div>

    <!-- 列表部分 -->
    <el-table
      :data="newSongsList"
      stripe
      @row-dblclick="open"
      :row-class-name="tableRowClassName"
    >
      <el-table-column type="index"></el-table-column>
      <!-- 播放操作栏 -->
      <el-table-column prop="album" label="操作">
        <template slot-scope="scope">
          <div class="musicPlay">
            <img :src="scope.row.album.blurPicUrl" alt="" />
            <img
              src="../../assets/img/playMusic/rebofang.svg"
              alt=""
              class="bofang"
            />
            <p>{{ scope.row.name }}</p>
          </div>
        </template>
      </el-table-column>
      <!-- 歌手栏 -->
      <el-table-column prop="artists" label="歌手">
        <template slot-scope="scope">
          <div class="artistsName">
            <div
              v-for="(item, index) in scope.row.artists"
              :key="index"
              class="artistsNameItem"
            >
              <span @click="enterArtist(item.id)"> {{ item.name }}</span>
              <p v-if="index - 0 != scope.row.artists.length - 1">/</p>
            </div>
          </div>
        </template>
      </el-table-column>
      <!-- 专辑栏 -->
      <el-table-column prop="album" label="专辑">
        <template slot-scope="scope">
          <div class="albumName">
            {{ scope.row.album.name }}
          </div>
        </template>
      </el-table-column>
      <!-- 时长栏 -->
      <el-table-column prop="duration" label="时长">
        <template slot-scope="scope">
          <div>
            {{ scope.row.duration | dateFormatms }}
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>



<script>
import { getNewSongs } from "../../network/newsongs";
import { getSongsDetail, songDetail } from "../../network/discover";
import { indexMixin } from "../../components/PlayMusic";
export default {
  name: "NewSongsItem",
  data() {
    return {
      nations: [
        { value: "0", name: "全部" },
        { value: "7", name: "华语" },
        { value: "96", name: "欧美" },
        { value: "16", name: "韩国" },
        { value: "8", name: "日本" }
      ],
      newSongsList: [],
      type: 0,
      currentRowIndex: 0
    };
  },
  mixins: [indexMixin],
  created() {
    getNewSongs(this.type).then(res => {
      this.newSongsList = res.data.data;
    });
  },
  methods: {
    changeType(type) {
      this.type = type;
      getNewSongs(this.type).then(res => {
        this.newSongsList = res.data.data;
      });
    },
    // 进入歌手页面
    enterArtist(id) {
      this.$router.push({
        path: "/artist-list",
        query: {
          id: id
        },
        name: "ArtistListDetail"
      });
    },
    tableRowClassName({ row, rowIndex }) {
      row.row_index = rowIndex;
    },
    open(row, event, column) {
      this.currentRowIndex = row.row_index;
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
          this.$bus.$emit("playingIndex", this.currentRowIndex);
          this.playMusic(this.currentRowIndex);
        })
        .catch(() => {});
    },
    async playMusic(index) {
      this.musiclist = [];
      for (let i in this.newSongsList) {
        const { data: res } = await getSongsDetail(this.newSongsList[i].id);
        let song = new songDetail(res.songs);
        this.musiclist.push(song);

        if (i == this.newSongsList.length - 1) {
          this.PlayMusic(index);
        }
      }
    }
  }
};
</script>


<style scoped>
.top {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  border-bottom: 1px rgb(225, 225, 225) solid;
  align-items: flex-end;
}
.top span {
  font-size: 16px;
  color: rgb(136, 136, 136);
  margin-left: 20px;
  margin-bottom: 10px;
  cursor: pointer;
}
img {
  width: 48px;
  height: 48px;
  cursor: pointer;
}
.bofang {
  position: absolute;
  width: 30px;
  height: 30px;
  top: 9px;
  left: 9px;
  cursor: pointer;
}
.musicPlay {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  height: 48px;
}
.musicPlay p {
  font-size: 16px;
  margin: 0;
  display: inline-block;
  height: 100%;
  line-height: 48px;
}
.artistsName {
  display: flex;
  flex-wrap: wrap;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.artistsNameItem {
  display: flex;
}
.artistsNameItem span {
  line-height: 51px;
  cursor: pointer;
  padding: 0 3px;
}
.albumName {
  cursor: pointer;
}
</style>