<template>
  <div class="menus">
    <el-menu router :default-active="$route.path">
      <el-menu-item v-for="item in list" :key="item.id" :index="item.link">
        <span slot="title">{{ item.name }}</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>


<script>
export default {
  name: "Menu",
  data() {
    return {
      list: [
        {
          link: "/individuation",
          name: "热门推荐"
        },
        {
          link: "/allmusiclist",
          name: "歌单"
        },
        {
          link: "/ranklist",
          name: "排行榜"
        },
        {
          link: "/artist-list",
          name: "歌手"
        },
        { link: "/mv", name: "MV" },
        {
          link: "/new-songs",
          name: "最新音乐"
        }
      ]
    };
  }
};
</script>



<style scoped>
.menus {
  height: 100%;
  background-color: rgb(249, 249, 249);
}
.el-menu {
  background-color: rgb(249, 249, 249);
  border-right: 0px;
}
.el-menu-item {
  font-size: 18px;
}
img {
  width: 18px;
  height: 18px;
}
</style>