import { request } from './request.js'
import { formatDate } from '../assets/common/tool.js'


export function getHotList() {
    return request({
        url: '/web/base-music/hots'
    })
}

// 获取轮播图
export function banner() {
    return request({
        url: '/banner',
    })
}
// 获取推荐歌单
export function getPersonalized() {
    return request({
        url: '/personalized',
        params: {
            limit: 10
        }

    })
}

// 获取歌单信息
export function getMusicListDetail(id) {
    return request({
        url: '/playlist/detail',
        params: {
            id: id
        }
    })
}

// 获取歌曲列表信息
export function getSongsDetail(id) {
    return request({
        url: '/song/detail',
        params: {
            ids: id
        }
    })
}



// 获取歌单评论
export function getDiscussDetail(id, offset) {
    return request({
        url: '/comment/playlist',
        params: {
            id: id,
            offset: 20 * offset
        }
    })
}

// 获取收藏者信息
export function getSub(id, limit) {
    return request({
        url: '/playlist/subscribers',
        params: {
            id: id,
            limit
        }
    })
}

// 获取独家放送列表
export function getIndividuationMv() {
    return request({
        url: '/personalized/privatecontent'
    })
}


// 推荐最新音乐
export function getNewSong() {
    return request({
        url: '/personalized/newsong'
    })
}


// 获取歌单分类
export function getCatList() {
    return request({
        url: '/playlist/catlist'
    })
}


// 获取全部歌单
export function getAllPlayList(offset, cat) {
    return request({
        url: '/top/playlist',
        params: {
            offset: 50 * offset,
            cat
        }
    })
}

// 获取排行榜信息
export function getTopLists() {
    return request({
        url: '/toplist/detail'
    })
}

// 获取音乐播放地址
export function getMusicUrl(id) {
    return request({
        url: '/song/url',
        params: {
            id
        }
    })
}


// 获取歌词
export function getLyric(id) {
    return request({
        url: '/lyric',
        params: {
            id: id
        }
    })
}

// 搜索
export function getSearchDetail(keywords, type, offset) {
    return request({
        url: '/cloudsearch',
        params: {
            keywords,
            type,
            offset: 50 * offset,
            limit: 50

        }
    })
}


export class songDetail {
    constructor(songs) {
        this.id = songs[0].id;
        this.name = songs[0].name;
        this.album = songs[0].al.name;
        this.artists = songs[0].ar;
        this.pic = songs[0].al.picUrl;
        this.albumId = songs[0].al.id
        this.time = formatDate(new Date(songs[0].dt), 'mm:ss')
    }
}


export class searchSongDetail {
    constructor(songs) {
        this.id = songs.id;
        this.name = songs.name;
        this.album = songs.al.name;
        this.artists = songs.ar;
        this.pic = songs.al.picUrl;
        this.albumId = songs.al.id
        this.time = formatDate(new Date(songs.dt), 'mm:ss')
    }
}




