import { request } from './request'


export function userLogin(data) {
    return request({
        url: '/share/base-user/rest/login',
        method:'post',
        data
    })
}

export function userRegister(data) {
    return request({
        url: '/share/base-user/rest/register',
        method:'post',
        data
    })
}