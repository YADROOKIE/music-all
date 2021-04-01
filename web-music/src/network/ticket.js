import { request } from './request'


export function buyTicket(data) {
    return request({
        url: '/share/ticket/buy',
        method:'post',
        data
    })
}

export function listTicket() {
    return request({
        url: '/share/ticket/list',
        method:'get'
    })
}


export function userTicketOrder(id) {
    return request({
        url: '/share/ticket/myorder/'+id,
        method:'get'
    })
}