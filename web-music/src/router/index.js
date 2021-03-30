import Vue from 'vue'
import VueRouter from 'vue-router'


const Tab = () => import('../views/discoverMusic/Discover.vue')
const Musiclistdetail = () => import('../views/discoverMusic/MusicListDetail.vue')
const CatList = () => import('../views/allMusicList/CatList.vue')
const RankLists = () => import('../views/rankList/RankLists.vue')
const ArtistList = () => import('../views/artistList/ArtistList.vue')
const ArtistListDetail = () => import('../views/artistList/ArtistListDetail.vue')
const ArtistListAlbum = () => import('../views/artistList/childArtistList/ArtistListAlbum.vue')
const ArtistListMv = () => import('../views/artistList/childArtistList/ArtistListMv.vue')
const ArtistListDesc = () => import('../views/artistList/childArtistList/ArtistListDesc.vue')
const MvList = () => import('../views/mvList/MvList.vue')
const MvPlay = () => import('../views/mvList/commonMv/MvPlay.vue')
const AllMvList = () => import('../views/mvList/AllMvList.vue')
const NewSongs = () => import('../views/newSongs/NewSongs.vue')
const Album = () => import('../views/Album/Album.vue')
const Search = () => import('../views/Search/Search.vue')






Vue.use(VueRouter)

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  { path: '/', redirect: '/individuation' },
  { path: '/individuation', component: Tab },
  { path: '/musiclistdetail', name: 'detail', component: Musiclistdetail },
  { path: '/allmusiclist', component: CatList },
  { path: '/ranklist', component: RankLists },
  { path: '/artist-list', component: ArtistList },
  {
    path: '/artist-list', name: "ArtistListDetail", component: ArtistListDetail,
    children: [
      { path: '/artist-list/album', component: ArtistListAlbum, name: "ArtistListAlbum" },
      { path: '/artist-list/mv', component: ArtistListMv, name: "ArtistListMv" },
      { path: '/artist-list/desc', component: ArtistListDesc, name: "ArtistListDesc" }
    ]
  },
  { path: '/mv', component: MvList },
  { path: '/mv', component: MvPlay, name: 'MvPlay' },
  { path: '/mv-list', component: AllMvList, name: 'AllMvList' },
  { path: '/new-songs', component: NewSongs, name: 'NewSongs' },
  { path: '/album', component: Album, name: 'Album' },
  { path: '/search', component: Search, name: 'Search' }


]

const router = new VueRouter({
  routes
})

export default router
