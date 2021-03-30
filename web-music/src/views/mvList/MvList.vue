<template>
  <div>
    <!-- 最新mv -->
    <NewMv />
    <!-- 推荐mv -->
    <RecommendMv />
    <!-- 网易云出品mv -->
    <CloudMv />
    <!-- mv排行榜 -->
    <MvRankList />
  </div>
</template>



<script>
import NewMv from "./NewMv";
import CloudMv from "./CloudMv";
import RecommendMv from "./RecommendMv";
import MvRankList from "./MvRankList";
export default {
  name: "MvList",
  components: {
    NewMv,
    CloudMv,
    RecommendMv,
    MvRankList
  }
};
</script>



<style scoped>
</style>