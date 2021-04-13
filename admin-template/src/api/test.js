import request from '@/utils/request'

export function index() {
    return request({
      url: '/share/user/index',
      method: 'get'
    })
}

export function a() {
    return request({
      url: '/share/user/index',
      method: 'get'
    })
}