import { request } from './request'


export function globalSearch(query) {
    return request({
        url: '/search/global/music?query='+query,
        method:'get'
    })
}


export function ticketSearch(query) {
    return request({
        url: '/search/ticket?query='+query,
        method:'get'
    })
}