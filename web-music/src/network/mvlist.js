import { request } from './request.js'

// 获取最新mv
export function getNewMv(area) {
    return request({
        url: '/mv/first',
        params: {
            area,
            limit: 8
        }
    })
}



// 获取推荐mv
export function getrecommendMv() {
    return request({
        url: '/personalized/mv'
    })
}


// 获取网易出品mv
export function getCloudMv() {
    return request({
        url: '/mv/exclusive/rcmd',
        params: {
            limit: 8
        }
    })
}

// 获取mv排行榜
export function getMvRankList(area) {
    return request({
        url: '/top/mv',
        params: {
            area,
            limit: 10
        }
    })
}

// 获取mv播放地址
export function getMvUrl(id) {
    return request({
        url: '/mv/url',
        params: {
            id
        }
    })
}

// 获取mv评论
export function getMvDiscuss(id, offset) {
    return request({
        url: '/comment/mv',
        params: {
            id,
            offset: 20 * offset
        }
    })
}

// 获取mv数据
export function getMvDetail(mvid) {
    return request({
        url: '/mv/detail',
        params: {
            mvid
        }
    })
}

// 获取相似mv
export function getSimiMv(mvid) {
    return request({
        url: '/simi/mv',
        params: {
            mvid
        }
    })
}

// 获取所有mv
export function getAllMvList(area, type, order, offset) {
    return request({
        url: '/mv/all',
        params: {
            area,
            type,
            order,
            offset: 80 * offset,
            limit: 80
        }
    })
}