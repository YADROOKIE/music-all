import { request } from './request'


export function adminAddMusic(data) {
    return request({
        url: '/web/base-music/add',
        method:'post',
        data
    })
}


export function adminAddTicket(data) {
    return request({
        url: '/share/commodity/rest/add',
        method:'post',
        data
    })
}