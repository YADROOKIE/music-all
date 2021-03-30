<template>
  <div>
    <el-table
      :data="songlist"
      style="width: 100%"
      stripe
      @row-dblclick="open"
      :row-class-name="tableRowClassName"
    >
      <el-table-column type="index"> </el-table-column>
      <el-table-column label="操作" width="60px">
        <template>
          <img src="../../../assets/img/collect.svg" alt="" @click="collect" />
        </template>
      </el-table-column>
      <el-table-column prop="name" label="音乐标题"></el-table-column>
      <el-table-column label="歌手">
        <template slot-scope="scope">
          <span
            class="aristsName"
            @click="enterArtists(item.id)"
            v-for="(item, index) in scope.row.artists"
            :key="index"
            >{{ item.name }}
            <span v-if="index - 0 != scope.row.artists.length - 1">/</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="专辑">
        <template slot-scope="scope">
          <span class="albums" @click="enterAlbum(scope.row.albumId)">{{
            scope.row.album
          }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="time" label="时长"></el-table-column>
    </el-table>
  </div>
</template>




<script>
import { indexMixin } from "../../../components/PlayMusic";
import { getSongsDetail, songDetail } from "../../../network/discover";
export default {
  name: "MusicLists",
  props: {
    songlist: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  data() {
    return {
      currentRowIndex: 0,
      musiclist: []
    };
  },
  mixins: [indexMixin],
  methods: {
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
    tableRowClassName({ row, rowIndex }) {
      //把每一行的索引放进row
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
    }
  }
};
</script>



<style  scoped>
.aristsName,
.albums {
  cursor: pointer;
}
img {
  padding-top: 5px;
  width: 20px;
  height: 20px;
  cursor: pointer;
}
</style>