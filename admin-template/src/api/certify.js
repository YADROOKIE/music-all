import request from '@/utils/request'

export function list() {
    return request({
      url: '/share/user-certify/list',
      method: 'get'
    })
}

export function pass(rid) {
    return request({
      url: '/share/user-certify/request/pass/'+rid,
      method: 'post'
    })
}

export function unpass(rid,explain) {
    return request({
      url: '/share/user-certify/request/error/'+rid,
      method: 'post',
      params:{
          'explain:':explain
      }
    })
}