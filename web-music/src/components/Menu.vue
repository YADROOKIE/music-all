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
        { link: "/mv", name: "购票中心" },
        {
          link: "/allmusiclist",
          name: "我的歌单"
        },
        {
          link: "/ranklist",
          name: "个人信息"
        },
        {
          link: "/new-songs",
          name: "我的订单"
        },
        {
          link: "/artist-list",
          name: "浏览记录"
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