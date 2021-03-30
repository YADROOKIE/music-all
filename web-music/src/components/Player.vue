<template>
  <transition>
    <div class="player">
      <!-- 播放控制 -->
      <div class="playerControl">
        <img src="../assets/img/player/pre.svg" alt="" @click="preSong" />
        <img
          src="../assets/img/player/play.svg"
          alt=""
          @click="startMusic"
          v-show="isPlay"
        />
        <img
          src="../assets/img/player/stop.svg"
          alt=""
          @click="stopMusic"
          v-show="!isPlay"
        />
        <img src="../assets/img/player/next.svg" alt="" @click="nextSong" />
      </div>
      <!-- 进度条 -->
      <div class="playerProgress">
        <audio
          :src="musicSrc[this.index]"
          ref="audio"
          autoplay
          @ended="musicEnded()"
          @timeupdate="onTimeupdate()"
        ></audio>
        <!-- 开始时间 -->
        <div class="playerStar" v-if="this.playList.length > 0">
          {{ currentTime }}
        </div>
        <div class="playerStar" v-else>00:00</div>
        <!-- 进度条 -->
        <div
          class="vidoProgress"
          @click="setProClick($event)"
          ref="vidoProgress"
        >
          <div
            class="vidoProgressBar"
            :style="{ width: runningTime + 'px' }"
          ></div>
        </div>
        <!-- 歌曲总长 -->
        <div class="playerEnd" v-if="this.playList.length > 0">
          {{ this.playList[this.index].time }}
        </div>
        <div class="playerEnd" v-else>00:00</div>
      </div>
      <!-- 声音控制 -->
      <div class="playerVoice">
        <!-- 声音 -->
        <img
          src="../assets/img/player/voice.svg"
          alt=""
          @click="muting"
          v-show="isShowVoice"
        />
        <!-- 静音按钮 -->
        <img
          src="../assets/img/player/mute.svg"
          alt=""
          @click="openVoice"
          v-show="!isShowVoice"
        />
        <div class="voiceControl" ref="voiceProgress" @click="setVoice($event)">
          <div
            class="voiceControlItem"
            :style="{ width: voiceWidth + 'px' }"
          ></div>
        </div>
      </div>
      <!-- 播放模式 -->
      <div class="playerStyle">
        <img
          src="../assets/img/player/order.svg"
          alt=""
          class="order"
          v-show="this.playMode === 1"
          @click="changeRandom"
        />
        <img
          src="../assets/img/player/random.svg"
          alt=""
          class="order"
          v-show="this.playMode === 2"
          @click="changeCircle"
        />
        <img
          src="../assets/img/player/circle.svg"
          alt=""
          class="order"
          v-show="this.playMode === 3"
          @click="changeOrder"
        />
      </div>
      <!-- 歌词 -->
      <div>
        <img
          src="../assets/img/player/lyric.svg"
          alt=""
          class="lyric"
          @click="showLyric"
          v-show="showLyricBtn"
        />
        <img
          src="../assets/img/player/showLyric.svg"
          alt=""
          class="lyric"
          @click="hiddenLyric"
          v-show="!showLyricBtn"
        />
      </div>

      <!-- 歌单 -->
      <img
        src="../assets/img/player/songlist.svg"
        alt=""
        class="songlist"
        @click="showPlayList((isShow = true))"
      />
    </div>
  </transition>
</template>


<script>
import { getMusicUrl } from "../network/discover";
import { songList } from "./PlayMusic";
import { formatDate } from "../assets/common/tool";

export default {
  name: "Player",
  data() {
    return {
      playList: [], //播放列表
      isPlay: true, //播放按钮控制
      index: null, //播放顺序
      musicList: [],
      musicSrc: [], //音乐播放地址
      path: "",
      currentIndex: 0,
      proLine: 0,
      offsetLeft: 0,
      scale: 0,
      proLoad: 0,
      currentTime: "00:00",
      runningTime: 0, //进度条长度
      voiceWidth: 0, //声音长度
      isShow: true,
      current: 0,
      playMode: 1,
      isShowVoice: true,
      showLyricBtn: true,
      isShowLyric: false,
      src:''
    };
  },
  mounted() {
    this.voiceWidth = this.$refs.voiceProgress.clientWidth;

    // 接受播放全部列表
    this.$bus.$on("playMusic", (list, index, path, musicList) => {
      this.index = index;
      this.musicSrc = [];
      this.playList = [];
      this.path = path;
      this.playList = list;
      this.musicList = musicList;
      for (let i in this.playList) {
        this.musicSrc.push(this.playList[i].src);
      }
      if (this.playList[this.index].src != null) {
        this.$refs.audio.src = this.playList[this.index].src;
        this.isPlay = false;
      } else {
        this.isPlay = false;
        this.$message.error("由于版权问题，该歌曲暂时无法播放");
        for (let i = index; i < this.playList.length; i++) {
          if (this.playList[i].src != null) {
            this.index = i;

            break;
          }
        }
        this.$bus.$emit("playingIndex", this.index);
        this.$refs.audio.src = this.playList[this.index].src;
      }
      // 发射播放列表
      this.$bus.$emit("playListsItem", list, index);
      console.log(this.playList);
      console.log(this.musicSrc);
    });
    // 接受播放单曲
    this.$bus.$on("starSingle", index => {
      this.index = index;
      this.isPlay = false;
      this.$refs.audio.src = this.playList[this.index].src;
    });
  },
  methods: {
    // 下一首
    nextSong() {
      if (this.playList.length > 0) {
        if (this.playMode === 1 || this.playMode === 3) {
          this.index++;
          this.isPlay = false;
          if (this.playList[this.index].src === null) {
            this.$message.error("由于版权问题，该歌曲暂时无法播放");
            for (let i = index; i < this.playList.length; i++) {
              if (this.playList[i].src != null) {
                this.index = i;
                break;
              }
            }
          }
          this.$refs.audio.src = this.playList[this.index].src;
        } else {
          this.index = Math.floor(Math.random() * this.playList.length);
          if (this.playList[this.index].src === null) {
            this.$message.error("由于版权问题，该歌曲暂时无法播放");
            for (let i = index; i < this.playList.length; i++) {
              if (this.playList[i].src != null) {
                this.index = i;
                break;
              }
            }
          }
          this.$refs.audio.src = this.playList[this.index].src;
        }
        this.$bus.$emit("playingIndex", this.index);
      }
    },
    // 上一首
    preSong() {
      if (this.playList.length > 0) {
        if ((this.index != 0 && this.playMode === 1) || this.playMode === 3) {
          this.index--;
          this.isPlay = false;
          if (this.playList[this.index].src === null) {
            this.$message.error("由于版权问题，该歌曲暂时无法播放");
            for (let i = index; i > 0; i--) {
              if (this.playList[i].src != null) {
                this.index = i;
                break;
              }
            }
          }
          this.$refs.audio.src = this.playList[this.index].src;
        } else {
          this.index = Math.floor(Math.random() * this.playList.length);
          if (this.playList[this.index].src === null) {
            this.$message.error("由于版权问题，该歌曲暂时无法播放");
            for (let i = index; i < this.playList.length; i++) {
              if (this.playList[i].src != null) {
                this.index = i;
                break;
              }
            }
          }
          this.$refs.audio.src = this.playList[this.index].src;
        }
        this.$bus.$emit("playingIndex", this.index);
      }
    },

    // 开始音乐
    startMusic() {
      if (this.playList.length > 0) {
        this.isPlay = false;
        this.$refs.audio.play();
      }
    },
    // 暂停音乐
    stopMusic() {
      if (this.playList.length > 0) {
        this.isPlay = true;
        this.$refs.audio.pause();
      }
    },
    // 自动播放下一首
    musicEnded() {
      if (this.playMode === 1) {
        this.index++;
        if (this.playList[this.index].src === null) {
          this.$message.error("由于版权问题，该歌曲暂时无法播放");
          for (let i = index; i < this.playList.length; i++) {
            if (this.playList[i].src != null) {
              this.index = i;
              break;
            }
          }
        }
        this.$refs.audio.src = this.playList[this.index].src;
      } else if (this.playMode === 2) {
        this.index = Math.floor(Math.random() * this.playList.length);
        if (this.playList[this.index].src === null) {
          this.$message.error("由于版权问题，该歌曲暂时无法播放");
          for (let i = index; i < this.playList.length; i++) {
            if (this.playList[i].src != null) {
              this.index = i;
              break;
            }
          }
        }
        this.$refs.audio.src = this.playList[this.index].src;
      } else {
        this.$refs.audio.src = this.playList[this.index].src;
      }
      this.$bus.$emit("playingIndex", this.index);
    },
    // 点击进度条拖拽音乐
    setProClick(e) {
      if (this.musicSrc && this.musicSrc.length > 0) {
        this.runningTime = e.clientX - this.$refs.vidoProgress.offsetLeft;
        this.currentTime =
          (this.runningTime * this.$refs.audio.duration) /
          this.$refs.vidoProgress.clientWidth;
        this.$bus.$emit("lyricTime", this.currentTime);
        this.$refs.audio.currentTime = this.currentTime;

        this.currentTime = formatDate(
          new Date(this.$refs.audio.currentTime * 1000),
          "mm:ss"
        );
      }
    },
    // 声音控制
    setVoice(e) {
      if (this.musicSrc && this.musicSrc.length > 0) {
        this.voiceWidth = e.clientX - this.$refs.voiceProgress.offsetLeft;
        this.$refs.audio.volume =
          this.voiceWidth / this.$refs.voiceProgress.clientWidth;
      }
    },
    // 静音
    muting() {
      this.isShowVoice = false;
      this.$refs.audio.volume = 0;
      this.voiceWidth =
        this.$refs.audio.volume * this.$refs.voiceProgress.clientWidth;
    },
    // 取消静音
    openVoice() {
      this.isShowVoice = true;
      this.voiceWidth = this.$refs.voiceProgress.clientWidth;
      this.$refs.audio.volume =
        this.voiceWidth / this.$refs.voiceProgress.clientWidth;
    },
    onTimeupdate() {
      this.runningTime =
        (this.$refs.vidoProgress.clientWidth * this.$refs.audio.currentTime) /
        this.$refs.audio.duration;
      this.currentTime = formatDate(
        new Date(this.$refs.audio.currentTime * 1000),
        "mm:ss"
      );
      this.$bus.$emit("lyricTime", this.$refs.audio.currentTime);
    },
    // 打开关闭播放列表
    showPlayList(isShow) {
      if (this.current === 0) {
        this.$bus.$emit("showPlayList", isShow);
        this.current++;
      } else {
        isShow = !isShow;
        this.$bus.$emit("showPlayList", isShow);
        this.current--;
      }
    },
    // 改变播放模式
    changeRandom() {
      this.playMode = 2;
    },
    changeCircle() {
      this.playMode = 3;
    },
    changeOrder() {
      this.playMode = 1;
    },
    // 打开歌词
    showLyric() {
      this.showLyricBtn = false;
      this.isShowLyric = !this.isShowLyric;
      this.$bus.$emit("showLyric", this.isShowLyric);
    },
    // 隐藏歌词
    hiddenLyric() {
      this.showLyricBtn = true;
      this.isShowLyric = !this.isShowLyric;
      this.$bus.$emit("showLyric", this.isShowLyric);
    }
  }
};
</script>


<style scoped>
.player {
  display: flex;
  align-items: center;
  height: 100%;
  background-color: rgb(249, 249, 249);
}

.playerControl,
.playerProgress,
.playerVoice {
  margin-right: 50px;
  margin-left: 50px;
}
.playerControl img {
  margin-right: 20px;
}
.playerProgress {
  display: flex;
  align-items: center;
}
.vidoProgress {
  width: 900px;
  height: 4px;
  background-color: #f3f3f3;
  margin-left: 20px;
  margin-right: 20px;
}
img {
  width: 30px;
  height: 30px;
  cursor: pointer;
}
.playerVoice {
  display: flex;
  align-items: center;
}
.playerVoice img {
  width: 30px;
  height: 30px;
  margin-right: 20px;
}
.voiceControl {
  width: 100px;
  height: 4px;
  background-color: #f3f3f3;
}
.order,
.lyric,
.songlist {
  width: 20px;
  height: 20px;
  margin-right: 30px;
}
.vidoProgressBar,
.voiceControlItem {
  width: 0px;
  height: 4px;
  background-color: red;
}
</style>