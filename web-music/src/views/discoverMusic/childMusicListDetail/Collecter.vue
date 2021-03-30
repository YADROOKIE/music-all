<template>
  <div class="live" v-if="sub != null">
    <div class="content">
      <div class="item" v-for="(item, index) in sub" :key="index">
        <img :src="item.avatarUrl" alt="" />
        <div>{{ item.nickname }}</div>
      </div>
    </div>
  </div>
</template>




<script>
export default {
  name: "Collecter",
  props: {
    sub: {
      type: Array,
      default() {
        return [];
      }
    }
  }
};
</script>




<style  scoped>
.live {
  width: 100%;
}
.live .content {
  display: flex;
  flex-wrap: wrap;
}
.content .item {
  padding: 30px;
  text-align: center;
  font-size: 12px;
}
.item img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
}
</style>