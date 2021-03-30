<template>
  <div v-if="routerAlive">
    <!-- 列表 -->
    <el-table
      :data="list"
      stripe
      @row-dblclick="open"
      :row-class-name="tableRowClassName"
    >
      <el-table-column type="index"></el-table-column>
      <el-table-column label="操作" width="50px">
        <template>
          <img src="../../../assets/img/collect.svg" alt="" @click="collect" />
        </template>
      </el-table-column>
      <el-table-column label="音乐标题" prop="name"></el-table-column>
      <el-table-column label="歌手">
        <template slot-scope="scope">
          <div class="artistsName">
            <div
              v-for="(item, index) in scope.row.artists"
              :key="index"
              class="artistsNameItem"
            >
              <span @click="enterArtists(item.id)"> {{ item.name }} </span>
              <p v-if="index - 0 != scope.row.artists.length - 1">/</p>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="专辑">
        <template slot-scope="scope">
          <span @click="enterAlbum(scope.row.albumId)">
            {{ scope.row.album }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="时长" prop="time"></el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="50"
    >
    </el-pagination>
  </div>
</template>




<script>
import { indexMixin } from "../../../components/PlayMusic";
import {
  getSearchDetail,
  searchSongDetail,
  songDetail,
  getSongsDetail
} from "../../../network/discover";
export default {
  name: "SearchSongList",
  data() {
    return {
      currentPage: 1,
      list: [],
      total: null,
      offset: 0,
      musiclist: [],
      currentRowIndex: 0,

      routerAlive: true
    };
  },
  // 接受父组件注入的routerRefresh
  inject: ["routerRefresh"],
  mixins: [indexMixin],
  created() {
    this.keywords = this.$route.query.keywords;
    this.type = this.$route.query.type;

    this.$bus.$on("creatItem", keywords => {
      this.keywords = keywords;
      this.routerRefresh();
    });
    getSearchDetail(this.keywords, this.type, this.offset).then(res => {
      this.total = res.data.result.songCount;
      for (let i = 0, len = res.data.result.songs.length; i < len; i++) {
        let song = new searchSongDetail(res.data.result.songs[i]);
        this.list.push(song);
      }
    });
  },
  methods: {
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.offset = this.currentPage - 1;
      getSearchDetail(this.keywords, this.type, this.offset).then(res => {
        this.list = [];
        this.total = res.data.result.songCount;
        for (let i = 0, len = res.data.result.songs.length; i < len; i++) {
          let song = new searchSongDetail(res.data.result.songs[i]);
          this.list.push(song);
        }
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
      for (let i in this.list) {
        const { data: res } = await getSongsDetail(this.list[i].id);
        let song = new songDetail(res.songs);
        this.musiclist.push(song);

        if (i == this.list.length - 1) {
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

<style scoped>
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
p {
  margin: 0px;
  padding: 0 5px;
}
.el-pagination {
  margin-top: 25px;
}
span {
  cursor: pointer;
}
img {
  padding-top: 5px;
  width: 20px;
  height: 20px;
  cursor: pointer;
}
</style>