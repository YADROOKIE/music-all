<template>
  <div>
    <!-- 头部部分 -->
    <div class="mvRankListTop">
      <h3>MV排行榜</h3>
      <span
        v-for="(item, index) in nations"
        :key="index"
        @click="changeNation(item)"
        >{{ item }}</span
      >
      <div class="right">
        <p>更多</p>
        <i class="el-icon-arrow-right"></i>
      </div>
    </div>

    <!-- mv排行榜主体 -->
    <div class="mvRankList">
      <div
        v-for="(item, index) in mvranklist"
        :key="index"
        class="mvRankListItem"
      >
        <span class="rankListIndex" v-if="index + 1 >= 10">{{
          index + 1
        }}</span>
        <span class="rankListIndex" v-else>0{{ index + 1 }}</span>

        <img :src="item.cover" alt="" @click="videoPlay(item.id)" />
        <div class="box">
          <p class="mvRankListName" @click="videoPlay(item.id)">
            {{ item.name }}
          </p>
          <div
            class="mvRankListArtistName"
            @click="artistIntroduce(item.artistId)"
          >
            {{ item.artistName }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import { getMvRankList } from "../../network/mvlist";
export default {
  name: "MvRankListv",
  data() {
    return {
      mvranklist: [],
      nations: ["内地", "港台", "欧美", "日本", "韩国"]
    };
  },
  created() {
    // 获取mv排行榜
    getMvRankList().then(res => {
      this.mvranklist = res.data.data;
    });
  },
  methods: {
    // 切换不同地区的mv排行榜
    changeNation(area) {
      getMvRankList(area).then(res => {
        this.mvranklist = res.data.data;
      });
    },
    // 播放mv
    videoPlay(id) {
      this.$router.push({
        path: "/mv",
        query: {
          id
        },
        name: "MvPlay"
      });
      console.log(this.mvranklist);
    },
    // 跳往歌手页面
    artistIntroduce(id) {
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
.mvRankListTop {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  border-bottom: 1px rgb(225, 225, 225) solid;
  align-items: flex-end;
}
span {
  font-size: 16px;
  color: rgb(136, 136, 136);
  margin-left: 20px;
  margin-bottom: 10px;
  cursor: pointer;
}
.rankListIndex {
  font-size: 20px;
  margin-bottom: 0;
  line-height: 150px;
  margin-right: 10px;
}
.right {
  position: absolute;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  right: 20px;
  cursor: pointer;
}
img {
  width: 270px;
  height: 150px;
  cursor: pointer;
}
.mvRankList {
  display: flex;
  flex-wrap: wrap;
}
.mvRankListItem {
  width: 49%;
  display: flex;
  padding-bottom: 20px;
  margin: 20px 0 50px 0;
}
.mvRankListName {
  margin-top: 5px;
  cursor: pointer;
}
.mvRankListArtistName {
  font-size: 14px;
  color: rgb(136, 136, 136);
  cursor: pointer;
}
.box {
  margin-left: 10px;
}
</style>