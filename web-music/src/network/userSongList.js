import { request } from './request'


export function addUserSongList(data) {
    return request({
        url: '/web/user-song-list/add',
        method:'post',
        data
    })
}
export function deleteUserSongList(id) {
    return request({
        url: '/web/user-song-list/delete/'+id,
        method:'post'
    })
}
export function cancelSongList(id) {
    return request({
        url: '/web/song-list-music/delete/'+id,
        method:'post'
    })
}

export function getUserSongList(id) {
    return request({
        url: '/web/user-song-list/list/'+id,
        method:'get'
    })
}

export function addSongToList(data){
    return request({
        url: '/web/song-list-music/addtolist',
        method:'post',
        data
    })
}

export function getListAllMusic(id){
    return request({
        url: '/web/song-list-music/list/'+id,
        method:'get'
    })
}