<template>
  <div class="playlist" v-show="isShow">
    <div class="headBox">
      <h4>播放列表</h4>
    </div>

    <el-table
      :data="lists"
      stripe
      @row-dblclick="playMusic"
      :row-class-name="tableRowClassName"
      highlight-current-row
      :show-header="false"
    >
      <el-table-column type="index"></el-table-column>
      <el-table-column prop="title" width="200px">
        <template slot-scope="scope">
          <img
            src="../assets/img/player/play2.0.svg"
            alt=""
            @click="playMusic1(scope.row)"
          />
          <span>{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="time"></el-table-column>
      <!-- <el-table-column>
        <template>
          <img src="../assets/img/player/delete.svg" alt="" />
        </template>
      </el-table-column> -->
    </el-table>
  </div>
</template>



<script>
export default {
  name: "PlayList",
  data() {
    return {
      lists: [],
      index: 0,
      isShow: false,
      currentRowIndex: 0
    };
  },
  mounted() {
    this.$bus.$on("playListsItem", list => {
      this.lists = [];
      this.lists = list;
    });
    this.$bus.$on("showPlayList", isShow => {
      this.isShow = isShow;
    });
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      row.row_index = rowIndex;
    },
    playMusic(row, event, column) {
      this.currentRowIndex = row.row_index;
      this.$bus.$emit("starSingle", this.currentRowIndex);
      this.$bus.$emit("lyricIndex", this.currentRowIndex);
      this.$bus.$emit("playingIndex", this.currentRowIndex);
    },
    playMusic1(row) {
      this.$bus.$emit("starSingle", row.index);
      this.$bus.$emit("lyricIndex", row.index);
      this.$bus.$emit("playingIndex", row.index);
    }
  }
};
</script>


<style scoped>
.playlist {
  position: absolute;
  top: 60px;
  bottom: 60px;
  right: 0px;
  width: 450px;
  z-index: 999;
  overflow: auto;
}
img {
  width: 15px;
  height: 15px;
  margin-right: 5px;
  cursor: pointer;
}
.el-table {
  height: 100%;
  overflow: auto;
}
h4 {
  display: flex;
  justify-content: center;
  margin-bottom: 0px;
  margin-top: 0px;
  padding-bottom: 20px;
  border-bottom: 1px solid rgb(240, 240, 240);
}
.headBox {
  background-color: white;
}
</style>