import request from '@/utils/request'

export function listMusic() {
  return request({
    url: '/web/base-music/list',
    method: 'get'
  })
}

export function delMusic(id) {
    return request({
      url: '/web/base-music/del/'+id,
      method: 'post'
    })
  }