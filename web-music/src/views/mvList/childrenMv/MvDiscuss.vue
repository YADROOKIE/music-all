<template>
  <div class="box">
    <div class="discussBox">
      <el-input
        type="textarea"
        placeholder="请输入内容"
        v-model="textarea"
        :rows="3"
        name="text"
      >
      </el-input>
      <el-button type="primary" @click="open">评论</el-button>
    </div>

    <!-- 评论区域 -->
    <el-table
      :data="discussDetail"
      stripe
      :show-header="false"
      v-if="discussDetail.length > 0"
    >
      <el-table-column>
        <template slot-scope="scope">
          <div class="mainBox">
            <el-row>
              <el-col :span="2"
                ><img :src="scope.row.user.avatarUrl" alt=""
              /></el-col>
              <el-col :span="22"
                ><div class="dBox">
                  <span style="color:rgb(12, 115, 194)"
                    >{{ scope.row.user.nickname }}:</span
                  >
                  <span>{{ scope.row.content }}</span>
                </div>
                <div class="timeBox">
                  <span>{{ scope.row.time | dateFormatymdhms }}</span>
                </div></el-col
              >
            </el-row>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <div v-else>
      暂无评论
    </div>

    <!-- 分页区域 -->
    <el-pagination
      background
      layout="prev, pager, next"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :total="total - 0"
      :page-size="20"
    >
    </el-pagination>
  </div>
</template>




<script>
import { getMvDiscuss } from "../../../network/mvlist";
export default {
  name: "MvDiscuss",
  props: {
    id: {
      type: Number,
      default() {
        return [];
      }
    }
  },
  data() {
    return {
      textarea: "",

      // 当前点击的页码
      currentPage: 1,
      offset: 1,
      discussDetail: [],
      pageCount: 7,
      total: ""
    };
  },
  created() {
    getMvDiscuss(this.id, 0).then(res => {
      this.discussDetail = res.data.comments;
      this.total = res.data.total;
    });
  },
  methods: {
    // 页码点击事件
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.offset = this.currentPage - 1;
      getMvDiscuss(this.id, this.offset).then(res => {
        this.discussDetail = res.data.comments;
      });
    },
    open() {
      this.$message.error("需登录才能发表评论");
    }
  }
};
</script>


<style scoped>
.discussBox {
  position: relative;
  margin-top: 30px;
  height: 120px;
}
.el-textarea__inner {
  margin-top: 20px;
  background-color: #f0f0f2;
}
p {
  position: absolute;
  right: 0px;
  font-size: 16px;
}
.mainBox {
  position: relative;
}
img {
  display: table-cell;
  vertical-align: middle;
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

span {
  padding-right: 10px;
}
.el-row {
  margin-top: 30px;
}
.dBox {
  margin-bottom: 8px;
}
.el-pagination {
  padding-top: 50px;
}

.el-button {
  margin-top: 10px;
  float: right;
}
h3 {
  text-align: center;
}
</style>