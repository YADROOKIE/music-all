<template>
  <div>
    <el-table
      :data="songlist"
      style="width: 100%"
      stripe
      @row-dblclick="dblopen"
      :row-class-name="tableRowClassName"
    >
      <el-table-column type="index"> </el-table-column>
      <el-table-column label="操作" width="60px">
        <template>
          <img src="../../assets/img/collect.svg" alt="" @click="collect" />
        </template>
      </el-table-column>
      <el-table-column prop="name" label="音乐标题"></el-table-column>
      <el-table-column prop="ar" label="歌手">
        <template slot-scope="scope">
          <span v-for="(item, index) in scope.row.ar" :key="index">{{
            item.name
          }}</span>
        </template>
      </el-table-column>
      <el-table-column label="专辑">{{ albumName }}</el-table-column>
      <el-table-column prop="dt" label="时长">
        <template slot-scope="scope">
          {{ scope.row.dt | dateFormatms }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>




<script>
import { indexMixin } from "../../components/PlayMusic";
import { getSongsDetail, songDetail } from "../../network/discover";
export default {
  name: "AlbumMusicLists",
  props: {
    songlist: {
      type: Array,
      default() {
        return [];
      }
    },
    albumName: {
      type: String,
      default() {
        return [];
      }
    }
  },
  mixins: [indexMixin],
  data() {
    return {
      currentRowIndex: 0,
      musiclist: []
    };
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      //把每一行的索引放进row
      row.row_index = rowIndex;
    },
    dblopen(row, event, column) {
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
img {
  padding-top: 5px;
  width: 20px;
  height: 20px;
  cursor: pointer;
}
</style>