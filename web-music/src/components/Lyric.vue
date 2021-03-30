<template>
  <div
    class="lyricBox"
    v-if="lyricList != null && lyricList.length > 0"
    v-show="isShowLyric"
  >
    <div>
      <span>{{ txt }}</span>
    </div>
  </div>
</template>



<script>
export default {
  name: "Lyric",
  data() {
    return {
      isShowLyric: false,
      lyricList: [],
      lyricListItem: [],
      lyricIndex: 0,
      sortRule: [],
      playTime: 0,
      index: 0,
      txt: ""
    };
  },
  created() {
    // 歌词显示
    this.$bus.$on("showLyric", isShowLyric => {
      this.isShowLyric = isShowLyric;
    });
    // 接收音乐列表获取歌词
    this.$bus.$on("playMusic", (list, index, path, musicList) => {
      this.lyricList = [];
      for (let i in list) {
        this.formatText(list[i].lyric);
      }
    });
    // 获取当前音乐序号
    this.$bus.$on("playingIndex", index => {
      this.lyricIndex = index;
      console.log(this.lyricIndex);
    });
    // 获取当前音乐时间
    this.$bus.$on("lyricTime", time => {
      this.playTime = time + 1;
      for (
        let i = this.index, len = this.lyricList[this.lyricIndex].length;
        i < len;
        i++
      ) {
        if (
          Math.round(this.playTime) == this.lyricList[this.lyricIndex][i].time
        ) {
          return (this.index = i);
        }
      }
      this.txt = this.lyricList[this.lyricIndex][this.index].text;
    });
  },
  methods: {
    formatText(lyric) {
      this.lyricListItem = [];
      let arr = lyric.split("\n");
      for (let i in arr) {
        let temp_row = arr[i]; //现在每一行格式大概就是这样"[00:04.302][02:10.00]hello world";
        let temp_arr = temp_row.split("]"); //我们可以通过“]”对时间和文本进行分离
        let text = temp_arr.pop(); //把歌词文本从数组中剔除出来，获取到歌词文本了！
        temp_arr.forEach(element => {
          let obj = {};
          let time_arr = element.substr(1, element.length - 1).split(":"); //先把多余的“[”去掉，再分离出分、秒
          let s = parseInt(time_arr[0]) * 60 + Math.ceil(time_arr[1]); //把时间转换成与currentTime相同的类型，方便待会实现滚动效果
          obj.time = s;
          obj.text = text;
          this.lyricListItem.push(obj); //每一行歌词对象存到组件的lyric歌词属性里
        });
      }
      this.lyricList.push(this.lyricListItem);
    }
  }
};
</script>



<style scoped>
.lyricBox {
  position: absolute;
  bottom: 100px;
  left: 760px;
  z-index: 9999;
}
span {
  font-size: 24px;
  color: skyblue;
}
</style>