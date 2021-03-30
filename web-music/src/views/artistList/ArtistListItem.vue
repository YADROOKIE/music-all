<template>
  <div>
    <div class="artistListBox">
      <div
        v-for="(item, index) in artistlists"
        :key="index"
        class="artists"
        @click="toPath(item.id)"
      >
        <img :src="item.img1v1Url" alt="" />
        <div>{{ item.name }}</div>
      </div>
    </div>

    <!-- 分页区域 -->
    <el-pagination
      background
      layout="prev, pager, next"
      @current-change="handleCurrentChange"
      :page-size="50"
      :total="total - 0"
      :current-page="currentPage"
    >
    </el-pagination>
  </div>
</template>


<script>
import { getArtist, getArtistClassify } from "../../network/artistlist";
export default {
  name: "ArtistListItem",
  props: {
    area: {
      type: Number,
      default() {
        return [];
      }
    },
    types: {
      type: Number,
      default() {
        return [];
      }
    },
    initial: {
      type: Number,
      default() {
        return [];
      }
    }
  },
  data() {
    return {
      artistlists: [],
      total: 2500,
      // 当前页码
      currentPage: 1,
      offset: 0
    };
  },
  created() {
    //   获取歌手分类列表
    getArtistClassify(this.area, this.types, this.initial, this.offset).then(
      res => {
        this.artistlists = res.data.artists;
      }
    );
  },
  methods: {
    // 页码点击事件
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      getArtistClassify(
        this.area,
        this.types,
        this.initial,
        this.currentPage
      ).then(res => {
        this.artistlists = res.data.artists;
      });
    },
    // 跳往歌手页面
    toPath(id) {
      this.$router.push({
        path: "/artist-list",
        query: {
          id: id
        },
        name: "ArtistListDetail"
      });
    }
  },
  watch: {
    // 监听分类点击事件
    area(newValue, oldValue) {
      getArtistClassify(this.area, this.types, this.initial, this.offset).then(
        res => {
          this.artistlists = res.data.artists;
        }
      );
      this.currentPage = 1;
    },
    types(newValue, oldValue) {
      getArtistClassify(this.area, this.types, this.initial, this.offset).then(
        res => {
          this.artistlists = res.data.artists;
        }
      );
      this.currentPage = 1;
    },
    initial(newValue, oldValue) {
      getArtistClassify(this.area, this.types, this.initial, this.offset).then(
        res => {
          this.artistlists = res.data.artists;
        }
      );
      this.currentPage = 1;
    }
  }
};
</script>




<style  scoped>
img {
  width: 200px;
  height: 160px;
}
.artistListBox {
  display: flex;
  flex-wrap: wrap;
  padding-bottom: 10px;
}
.artists {
  width: 20%;
  margin: 25px 0px;
  cursor: pointer;
}
</style>