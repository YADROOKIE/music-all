<template>
  <div>
    <div class="top">
      <div class="language">
        语种：<span
          v-for="(item, index) in language"
          :key="index"
          @click="changeLanguage(item.value, index)"
          :class="{ blue: a === index }"
        >
          {{ item.name }}
        </span>
      </div>
      <div class="classify">
        分类：<span
          v-for="(item, index) in classify"
          :key="index"
          @click="changeClassify(item.value, index)"
          :class="{ blue: b === index }"
        >
          {{ item.name }}
        </span>
      </div>
      <div>
        筛选：
        <span
          v-for="(item, index) in letters"
          :key="index"
          @click="changeLetters(item.value, index)"
          :class="{ blue: c === index }"
          >{{ item.name }}</span
        >
      </div>
    </div>

    <!-- 歌手 -->
    <ArtistListItem :area="area" :types="types" :initial="initial" />
  </div>
</template>


<script>
import ArtistListItem from "./ArtistListItem";
export default {
  name: "artistList",
  components: {
    ArtistListItem
  },
  data() {
    return {
      // 语种
      language: [
        { value: "-1", name: "全部" },
        { value: "7", name: "华语" },
        { value: "96", name: "欧美" },
        { value: "8", name: "日本" },
        { value: "16", name: "韩国" },
        { value: "0", name: "其他" }
      ],
      // 分类
      classify: [
        { value: "-1", name: "全部" },
        { value: "1", name: "男歌手" },
        { value: "2", name: "女歌手" },
        { value: "3", name: "乐队" }
      ],
      letters: [
        { value: "-1", name: "热门" },
        { value: "65", name: "A" },
        { value: "66", name: "B" },
        { value: "67", name: "C" },
        { value: "68", name: "D" },
        { value: "69", name: "E" },
        { value: "70", name: "F" },
        { value: "71", name: "G" },
        { value: "72", name: "H" },
        { value: "73", name: "I" },
        { value: "74", name: "J" },
        { value: "75", name: "K" },
        { value: "76", name: "L" },
        { value: "77", name: "M" },
        { value: "78", name: "N" },
        { value: "79", name: "O" },
        { value: "80", name: "P" },
        { value: "81", name: "Q" },
        { value: "82", name: "R" },
        { value: "83", name: "S" },
        { value: "84", name: "T" },
        { value: "85", name: "U" },
        { value: "86", name: "V" },
        { value: "87", name: "W" },
        { value: "88", name: "X" },
        { value: "89", name: "Y" },
        { value: "90", name: "Z" },
        { value: "0", name: "其他" }
      ],
      // 语种默认值
      area: -1,
      // 分类默认值
      types: -1,
      initial: -1,
      // 控制字体点击变蓝事件
      a: 0,
      b: 0,
      c: 0
    };
  },
  methods: {
    // 改变传递的语种
    changeLanguage(value, index) {
      this.area = value - 0;
      this.a = index;
    },
    // 改变传递的分类
    changeClassify(value, index) {
      this.types = value - 0;
      this.b = index;
    },
    // 改变传递的首字母
    changeLetters(value, index) {
      this.initial = value - 0;
      this.c = index;
    }
  }
};
</script>



<style scoped>
span {
  padding-right: 30px;
  cursor: pointer;
}
.top {
  padding-bottom: 15px;
  font-size: 18px;
}
.language,
.classify {
  margin-bottom: 10px;
}
.blue {
  color: rgb(45, 140, 240);
}
</style>