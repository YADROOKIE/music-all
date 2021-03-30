<template>
  <div class="box" v-if="flag" @click="toPath(rankId)">
    <div
      class="rankListsTop"
      :style="{
        background:
          'linear-gradient(to right,' + bgColor[0] + ',' + bgColor[1] + ')'
      }"
    >
      <div class="left">
        <div class="firstIcon">
          <i>{{ title[0] }}</i>
        </div>
        <div class="secondIcon">
          <i>{{ title[1] }}</i>
        </div>
        <div class="thirdIcon">
          {{ title[2] }}
        </div>
        <div class="time" v-if="musicListDetail.playlist != null">
          {{ musicListDetail.playlist.updateTime | dateFormatmd }}
        </div>
      </div>
      <div class="right">
        <img src="../../../assets/img/playMusic/bofang.svg" alt="" />
      </div>
    </div>

    <!-- 表格区域 -->
    <div class="center">
      <el-table :data="musiclist" :show-header="false" stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="name"></el-table-column>
        <el-table-column prop="song"></el-table-column>
      </el-table>
    </div>
  </div>
</template>




<script>
import {
  getMusicListDetail,
  getSongsDetail,
  songDetail
} from "../../../network/discover";
export default {
  name: "DailyRankLists",
  props: {
    title: {
      type: Array,
      default() {
        return 0;
      }
    },
    bgColor: {
      type: Array,
      defaule() {
        return [];
      }
    },
    rankId: {
      type: Number,
      default() {
        return 0;
      }
    }
  },
  data() {
    return {
      musicListDetail: [],
      musiclist: [],
      flag: false
    };
  },
  created() {
    if (this.rankId != null) {
      getMusicListDetail(this.rankId).then(res => {
        this.musicListDetail = res.data;
        /**遍历查询歌单所有歌曲详情 */
        for (let i of this.musicListDetail.playlist.trackIds.slice(0, 8)) {
          getSongsDetail(i.id).then(res => {
            let song = new songDetail(res.data.songs);
            this.musiclist.push(song);
          });
        }
      });
    }

    this.flag = true;
  },
  methods: {
    toPath(id) {
      // 发起网络请求
      this.$router.push({
        path: "/musiclistdetail",
        name: "detail",
        query: {
          id
        }
      });
    }
  }
};
</script>




<style scoped>
.box {
  width: 30%;
  margin-right: 50px;
  margin-bottom: 50px;
  cursor: pointer;
}
.rankListsTop {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  height: 130px;
  align-items: center;
}
.left {
  display: flex;
  flex-wrap: wrap;

  position: absolute;
  left: 20px;
  color: white;
}
.right {
  position: absolute;
  right: 20px;
}
.firstIcon {
  font-size: 60px;
}
.secondIcon {
  margin-left: 20px;
}

.secondIcon,
.thirdIcon {
  font-size: 36px;
  font-style: italic;
}
.time {
  position: absolute;
  top: 43px;
  left: 77px;
  font-size: 16px;
}
</style>