<template>
  <div style="padding-left:20%;padding-right:20%">
    <el-table
      :data="tableData"
      stripe
      style="width: 100%">
      <el-table-column
        prop="id"
        label="ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="歌单名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="date"
        label="创建时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>





<script>
import { getCatList, getAllPlayList } from "../../network/discover";
import RecommendMusic from "../discoverMusic/RMusicList.vue";
export default {
  name: "CatList",
  components: {
    RecommendMusic
  },
  data() {
    return {
      categories: [],
      catlist: [],
      // 下拉全部歌单分类
      isShowCategories: false,
      recommendResource: [],
      // 当前点击的页码
      currentPage: 1,
      offest: 1,
      total: "2500",
      cat: "全部",
      tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          id:1
        }, {
          date: '2016-05-04',
          name: '王小虎',
          id:2
        }, {
          date: '2016-05-01',
          name: '王小虎',
          id:3
        }, {
          date: '2016-05-03',
          name: '王小虎',
          id:4
        }]
    };
  },
  created() {
    // 获取歌单分类
    getCatList().then(res => {
      this.catlist = res.data.sub;
      this.categories = res.data.categories;
      console.log(this.catlist);
    });
    // 获取全部歌单
    getAllPlayList(0).then(res => {
      this.recommendResource = res.data.playlists;
    });
  },
  methods: {
    handleClick(row) {
        console.log(row);
    },
    // 全部歌单分类下拉
    showCategories() {
      this.isShowCategories = !this.isShowCategories;
    },
    // 分页点击事件
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.offest = this.currentPage - 1;
      getAllPlayList(this.offest, this.cat).then(res => {
        this.recommendResource = res.data.playlists;
        console.log(this.recommendResource);
      });
    },
    // 切换分类歌单列表
    checkPlayLists(cat) {
      this.cat = cat;
      getAllPlayList(0, this.cat).then(res => {
        this.recommendResource = res.data.playlists;
      });
      this.currentPage = 1;
      this.isShowCategories = false;
    }
  }
};
</script>










<style scoped>
.box {
  margin-bottom: 20px;
}
.mainBox {
  position: absolute;
  width: 500px;
  height: 450px;
  border: 1px solid rgb(220, 223, 230);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  overflow: auto;
  margin-top: 20px;
  background-color: white;
  z-index: 999;
}
.top {
  width: 460px;
  color: rgb(81, 90, 110);
  padding: 10px 0px;
  margin-left: 20px;
  border-bottom: 1px solid rgb(220, 223, 230);
}
.allCatBtn {
  display: flex;
  justify-content: center;
}
.allCat {
  width: 470px;
  height: 35px;
  margin-top: 10px;
  border-radius: 12px;
}
.allCategories {
  display: flex;
  flex-wrap: wrap;
}
span {
  font-size: 18px;
  color: #cdcdcd;
}
.rightBtn {
  width: 25%;
  height: 35px;
  border-radius: 0;
  margin-left: 0;
}
.classify {
  margin-top: 15px;
  margin-bottom: 15px;
  height: 100%;
}
.left {
  float: left;
  height: 100%;
  margin-right: 30px;
}
.right {
  float: right;
  width: 380px;
}
</style>