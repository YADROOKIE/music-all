<template>
  <div>
    <el-table
      :data="songlists"
      stripe
      :show-header="false"
      @row-dblclick="dblopen"
      :row-class-name="tableRowClassName"
    >
      <el-table-column type="index"></el-table-column>
      <el-table-column prop="name"></el-table-column>
      <el-table-column prop="dt">
        <template slot-scope="scope">{{
          scope.row.dt | dateFormatms
        }}</template>
      </el-table-column>
    </el-table>
  </div>
</template>



<script>
import { getArtistHotSongs } from "../../../network/artistlist";
import { getSongsDetail, songDetail } from "../../../network/discover";
import { indexMixin } from "../../../components/PlayMusic";
export default {
  name: "HotSongs",
  props: {
    songlists: {
      type: Array,
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
      for (let i in this.songlists) {
        const { data: res } = await getSongsDetail(this.songlists[i].id);
        let song = new songDetail(res.songs);
        this.musiclist.push(song);
        if (i == this.songlists.length - 1) {
          this.PlayMusic(index);
        }
      }
    }
  }
};
</script>

<style scoped>
</style>