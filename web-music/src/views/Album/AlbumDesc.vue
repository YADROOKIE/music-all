<template>
  <div>
    <h3>专辑介绍</h3>
    <div
      v-for="(item, index) in formatText(albumDesc)"
      :key="index"
      class="txt"
    >
      {{ item }}
    </div>
  </div>
</template>


<script>
export default {
  name: "AlbumDesc",
  props: {
    albumDesc: {
      type: String,
      default() {
        return [];
      }
    }
  },

  data() {
    return {};
  },
  methods: {
    formatText(albumDesc) {
      let newText = albumDesc.split("\n");
      return newText;
    }
  }
};
</script>



<style scoped>
.txt {
  font-size: 16px;
  text-indent: 2em;
  line-height: 1.5;
  color: rgb(103, 103, 103);
}
</style>