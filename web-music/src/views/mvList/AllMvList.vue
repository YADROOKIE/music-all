<template>
  <div>
    <!-- 头部部分 -->
    <div class="top">
      <h3>全部mv</h3>
      <div class="areaItem">
        语种:
        <span
          v-for="(item, index) in nation"
          :key="index"
          @click="changeArea(item, index)"
          :class="{ changeColor: a === index }"
          >{{ item }}</span
        >
      </div>
      <div class="typeItem">
        分类:
        <span
          v-for="(item, index) in kind"
          :key="index"
          @click="changeType(item, index)"
          :class="{ changeColor: b === index }"
          >{{ item }}</span
        >
      </div>
      <div class="orderItem">
        排序:
        <span
          v-for="(item, index) in orders"
          :key="index"
          @click="changeOrder(item, index)"
          :class="{ changeColor: c === index }"
          >{{ item }}</span
        >
      </div>
    </div>

    <!-- mv列表部分 -->
    <div class="mvListsItem">
      <div v-for="(item, index) in mvlists" :key="index" class="mvListsItemBox">
        <img :src="item.cover" alt="" @click="enterMv(item.id)" />
        <div class="name" @click="enterMv(item.id)">{{ item.name }}</div>
        <div class="artistName" @click="enterArtist(item.artistId)">
          {{ item.artistName }}
        </div>
      </div>
    </div>

    <!-- 分页区域 -->
    <el-pagination
      background
      layout="prev, pager, next"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="80"
      :total="total - 0"
    >
    </el-pagination>
  </div>
</template>



<script>
import { getAllMvList } from "../../network/mvlist";
export default {
  name: "AllMvList",
  data() {
    return {
      nation: ["全部", "内地", "港台", "欧美", "韩国", "日本"],
      kind: ["全部", "官方版", "原声", "现场版", "网易出品"],
      orders: ["上升最快", "最热", "最新"],
      area: "全部",
      type: "全部",
      order: "上升最快",
      offset: 0,
      // mv列表
      mvlists: [],
      total: 0,
      currentPage: 1,
      // 控制点击变蓝
      a: 0,
      b: 0,
      c: 0
    };
  },
  created() {
    this.getMvList();
  },
  methods: {
    // 封装获取mv函数
    getMvList() {
      getAllMvList(this.area, this.type, this.order, this.offset).then(res => {
        this.mvlists = res.data.data;
        this.total = res.data.count;
      });
    },
    // 点击语种刷新列表
    changeArea(area, index) {
      this.area = area;
      this.a = index;
      this.offset = 0;
      this.getMvList();
      this.currentPage = 1;
    },
    // 点击分类刷新列表
    changeType(type, index) {
      this.type = type;
      this.b = index;
      this.offset = 0;
      this.getMvList();
      this.currentPage = 1;
    },
    // 点击排序刷新列表
    changeOrder(order, index) {
      this.order = order;
      this.c = index;
      this.offset = 0;
      this.getMvList();
      this.currentPage = 1;
    },
    // 页码点击事件
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.offset = this.currentPage - 1;
      getAllMvList(this.area, this.type, this.order, this.offset).then(res => {
        this.mvlists = res.data.data;
      });
    },
    // 进入mv界面
    enterMv(id) {
      this.$router.push({
        path: "/mv",
        query: {
          id
        },
        name: "MvPlay"
      });
    },
    // 进入歌手界面
    enterArtist(id) {
      this.$router.push({
        path: "/artist-list",
        query: {
          id: id
        },
        name: "ArtistListDetail"
      });
    }
  }
};
</script>


<style scoped>
.top {
  border-bottom: 1px rgb(225, 225, 225) solid;
}
h3 {
  border-bottom: 1px rgb(225, 225, 225) solid;
  margin-bottom: 0;
  padding-bottom: 10px;
}
.areaItem,
.typeItem,
.orderItem {
  margin: 10px 0;
  line-height: 30px;
}
span {
  display: inline-block;
  width: 80px;
  height: 30px;
  text-align: center;
  cursor: pointer;
}
.mvListsItem {
  display: flex;
  flex-wrap: wrap;
}
.mvListsItemBox {
  width: 25%;
  margin-top: 30px;
  margin-bottom: 20px;
}
img {
  width: 300px;
  height: 180px;
  cursor: pointer;
}
.name {
  width: 300px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
  margin-top: 10px;
}
.artistName {
  width: 300px;
  margin-top: 5px;
  font-size: 14px;
  color: rgb(102, 102, 102);
  cursor: pointer;
}
.changeColor {
  color: rgb(45, 140, 240);
}
</style>