import { request } from './request.js'



// 获取歌手分类列表
export function getArtistClassify(area, type, initial, offset) {
    return request({
        url: '/artist/list',
        params: {
            area,
            type,
            initial,
            offset: offset * 30

        }
    })
}

// 获取歌手单曲
export function getArtistSongs(id) {
    return request({
        url: '/artists',
        params: {
            id: id
        }
    })
}

// 获取歌手热门歌曲
export function getArtistHotSongs(id) {
    return request({
        url: '/artist/top/song',
        params: {
            id: id
        }
    })
}


// 获取歌手mv
export function getArtistMv(id) {
    return request({
        url: '/artist/mv',
        params: {
            id: id
        }
    })
}


// 获取歌手专辑
export function getArtistAlbum(id) {
    return request({
        url: '/artist/album',
        params: {
            id: id
        }
    })
}




// 获取歌手描述
export function getArtistDesc(id) {
    return request({
        url: '/artist/desc',
        params: {
            id: id
        }
    })
}








