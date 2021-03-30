import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
    playing: false,                    //当前是否正在播放
    playlist: [],                         //为实现下一首，上一首功能，保存当前播放列表
    sequenceList: [],                //当前按正常顺序的列表，列表还有一种随机列表
    currentIndex: -1              //当前歌曲的index
}
export default new Vuex.Store({
    state
})