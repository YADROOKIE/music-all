import { request } from './request'


// 获取专辑信息
export function getAlbumDetail(id) {
    return request({
        url: '/album',
        params: {
            id
        }
    })
}


// 获取专辑动态信息
export function getAlbumDynamic(id) {
    return request({
        url: '/album/detail/dynamic',
        params: {
            id
        }
    })
}

// 获取专辑评论
export function getAlbumDiscuss(id, offset) {
    return request({
        url: '/comment/album',
        params: {
            id,
            offset: 20 * offset
        }
    })
}

