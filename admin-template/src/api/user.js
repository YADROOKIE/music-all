import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/web/user/login',
    method: 'post',
    data
  })
}
export function listUser() {
  return request({
    url: '/share/base-user/rest/list',
    method: 'get'
  })
}
export function delUser(id) {
  return request({
    url: '/share/base-user/rest/del/'+id,
    method: 'post'
  })
}

export function getInfo(token) {
  return request({
    url: '/share/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/share/user/logout',
    method: 'post'
  })
}
