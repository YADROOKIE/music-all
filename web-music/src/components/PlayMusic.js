import { getMusicUrl, getLyric } from "../network/discover"


class playList {
    constructor(index, song, url = '', id, time, lyric) {
        this.index = index;
        this.title = song.name;
        this.src = url;
        this.pic = song.pic;
        this.id = id;
        this.time = time;
        this.lyric = lyric
    }
}

export const indexMixin = {
    methods: {
        async PlayMusic(index = 0) {
            let path = this.$route.path;
            let musiclist;
            let lyricList = [];
            if (this.musiclist.length >= 200) {
                musiclist = this.musiclist.slice(0, 199);
            }
            else musiclist = this.musiclist;
            let url = null,
                currentLength = 0;
            let playlist = [];
            for (let i = 0; i < musiclist.length; i++) {
                const { data: res } = await getLyric(musiclist[i].id)
                lyricList.push(res.lrc.lyric)
            }
            for (let i = 0, length = musiclist.length; i < length; i++) {
                const { data: res } = await getMusicUrl(musiclist[i].id)
                url = res.data[0].url;
                let song = new playList(i, musiclist[i], url, musiclist[i].id, musiclist[i].time, lyricList[i]);
                playlist.push(song);
                currentLength++;
                if (i == musiclist.length - 1) {
                    this.$bus.$emit("playMusic", playlist, index, path, musiclist);
                }

            }
        },
    }
}












