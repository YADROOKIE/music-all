<template>
  <div v-if="toplists.length > 0">
    <h4>官方榜</h4>
    <div class="rankListsTop">
      <DailyRankLists
        :title="['飙', '升', '榜']"
        :bg-color="['#56a1e3', '#5a8beb']"
        :rank-id="toplists[0].id"
      />
      <DailyRankLists
        :rank-id="toplists[1].id"
        :title="['新', '歌', '榜']"
        :bg-color="['#34aabe', '#56c2cd']"
      ></DailyRankLists>
      <DailyRankLists
        :rank-id="toplists[2].id"
        :title="['原', '创', '榜']"
        :bg-color="['#d53d6a', '#eb638d']"
      ></DailyRankLists>
      <DailyRankLists
        :rank-id="toplists[3].id"
        :title="['热', '歌', '榜']"
        :bg-color="['#c7523c', '#cc7455']"
      ></DailyRankLists>
      <DailyRankLists
        :rank-id="toplists[4].id"
        :title="['歌', '手', '榜']"
        :bg-color="['#9455ce', '#9946c9']"
      ></DailyRankLists>
    </div>

    <h4>全球榜</h4>
    <GlobalRankLists :globallists="globallists" />
  </div>
</template>



<script>
import { getTopLists } from "../../network/discover";
import DailyRankLists from "./childRankLists/dailyRankLists.vue";
import GlobalRankLists from "./childRankLists/GlobalRankLists.vue";
export default {
  name: "RankLists",
  components: {
    DailyRankLists,
    GlobalRankLists
  },
  data() {
    return {
      toplists: [],
      globallists: []
    };
  },
  created() {
    getTopLists().then(res => {
      this.toplists = res.data.list;
      this.globallists = this.toplists.slice(4);
    });
  }
};
</script>



<style scoped>
.rankListsTop {
  display: flex;
  flex-wrap: wrap;
}
</style>