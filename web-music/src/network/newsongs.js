import { request } from './request'



// 获取最新音乐
export function getNewSongs(type) {
    return request({
        url: '/top/song',
        params: {
            type
        }
    })
}