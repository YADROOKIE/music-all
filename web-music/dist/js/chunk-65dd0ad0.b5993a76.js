(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-65dd0ad0"],{"2b84":function(t,e,r){"use strict";r("fd5a")},"3ff6":function(t,e,r){"use strict";r("dc92")},"689c":function(t,e,r){},"69f5":function(t,e,r){},"747b":function(t,e,r){},7490:function(t,e,r){"use strict";r("d4a4")},"933a":function(t,e,r){"use strict";r("69f5")},a970:function(t,e,r){"use strict";r("747b")},bea7:function(t,e,r){"use strict";r("ca17")},ca17:function(t,e,r){},cb80:function(t,e,r){"use strict";r.r(e);var s=function(){var t=this,e=t.$createElement,r=t._self._c||e;return t.routerAlive?r("div",[r("div",{staticClass:"searchTop"},t._l(t.form,(function(e,s){return r("span",{key:s,on:{click:function(r){return t.changeForm(e.style)}}},[t._v(t._s(e.name))])})),0),1===t.type?r("SearchSongList"):t._e(),10===t.type?r("SearchAlbum"):t._e(),100===t.type?r("SearchArtists"):t._e(),1e3===t.type?r("SearchPlayList"):t._e(),1002===t.type?r("SearchUser"):t._e(),1004===t.type?r("SearchMv"):t._e()],1):t._e()},n=[],a=r("fba2"),i=function(){var t=this,e=t.$createElement,s=t._self._c||e;return t.routerAlive?s("div",[s("el-table",{attrs:{data:t.list,stripe:"","row-class-name":t.tableRowClassName},on:{"row-dblclick":t.open}},[s("el-table-column",{attrs:{type:"index"}}),s("el-table-column",{attrs:{label:"操作",width:"50px"}},[[s("img",{attrs:{src:r("e050"),alt:""},on:{click:t.collect}})]],2),s("el-table-column",{attrs:{label:"音乐标题",prop:"name"}}),s("el-table-column",{attrs:{label:"歌手"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("div",{staticClass:"artistsName"},t._l(e.row.artists,(function(r,n){return s("div",{key:n,staticClass:"artistsNameItem"},[s("span",{on:{click:function(e){return t.enterArtists(r.id)}}},[t._v(" "+t._s(r.name)+" ")]),n-0!=e.row.artists.length-1?s("p",[t._v("/")]):t._e()])})),0)]}}],null,!1,1443314526)}),s("el-table-column",{attrs:{label:"专辑"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("span",{on:{click:function(r){return t.enterAlbum(e.row.albumId)}}},[t._v(" "+t._s(e.row.album)+" ")])]}}],null,!1,3408933841)}),s("el-table-column",{attrs:{label:"时长",prop:"time"}})],1),s("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:t.total,"current-page":t.currentPage,"page-size":50},on:{"current-change":t.handleCurrentChange}})],1):t._e()},u=[],o=(r("d3b7"),r("ddb0"),r("96cf"),r("1da1")),l=r("0608"),c={name:"SearchSongList",data:function(){return{currentPage:1,list:[],total:null,offset:0,musiclist:[],currentRowIndex:0,routerAlive:!0}},inject:["routerRefresh"],mixins:[l["a"]],created:function(){var t=this;this.keywords=this.$route.query.keywords,this.type=this.$route.query.type,this.$bus.$on("creatItem",(function(e){t.keywords=e,t.routerRefresh()})),Object(a["g"])(this.keywords,this.type,this.offset).then((function(e){t.total=e.data.result.songCount;for(var r=0,s=e.data.result.songs.length;r<s;r++){var n=new a["j"](e.data.result.songs[r]);t.list.push(n)}}))},methods:{handleCurrentChange:function(t){var e=this;this.currentPage=t,this.offset=this.currentPage-1,Object(a["g"])(this.keywords,this.type,this.offset).then((function(t){e.list=[],e.total=t.data.result.songCount;for(var r=0,s=t.data.result.songs.length;r<s;r++){var n=new a["j"](t.data.result.songs[r]);e.list.push(n)}}))},enterArtists:function(t){this.$router.push({path:"/artist-list",query:{id:t},name:"ArtistListDetail"})},enterAlbum:function(t){this.$router.push({url:"/album",query:{id:t},name:"Album"})},tableRowClassName:function(t){var e=t.row,r=t.rowIndex;e.row_index=r},open:function(t,e,r){var s=this;this.currentRowIndex=t.row_index,this.$confirm("“双击播放”会用当前列表的音乐替换播放列表, 是否继续?","替换播放列表",{confirmButtonText:"继续",cancelButtonText:"取消",type:"warning"}).then((function(){s.$message("音乐缓存需要10~20s，请稍后"),s.$bus.$emit("playingIndex",s.currentRowIndex),s.playMusic(s.currentRowIndex)})).catch((function(){}))},playMusic:function(t){var e=this;return Object(o["a"])(regeneratorRuntime.mark((function r(){var s,n,i,u;return regeneratorRuntime.wrap((function(r){while(1)switch(r.prev=r.next){case 0:e.musiclist=[],r.t0=regeneratorRuntime.keys(e.list);case 2:if((r.t1=r.t0()).done){r.next=13;break}return s=r.t1.value,r.next=6,Object(a["h"])(e.list[s].id);case 6:n=r.sent,i=n.data,u=new a["k"](i.songs),e.musiclist.push(u),s==e.list.length-1&&e.PlayMusic(t),r.next=2;break;case 13:case"end":return r.stop()}}),r)})))()},collect:function(){this.$message.error("需登录才能收藏")}}},h=c,f=(r("2b84"),r("2877")),d=Object(f["a"])(h,i,u,!1,null,"59bfa531",null),p=d.exports,m=function(){var t=this,e=t.$createElement,r=t._self._c||e;return t.routerAlive?r("div",[r("div",{staticClass:"albumListsItem"},t._l(t.albumList,(function(e,s){return r("div",{key:s,staticClass:"albumListsItemBox"},[r("img",{attrs:{src:e.picUrl,alt:""},on:{click:function(r){return t.enterAlbum(e.id)}}}),r("div",{staticClass:"name",on:{click:function(r){return t.enterAlbum(e.id)}}},[t._v(t._s(e.name))]),r("div",{staticClass:"artistName"},t._l(e.artists,(function(s,n){return r("div",{key:n,staticClass:"artistNameItem"},[r("span",{on:{click:function(e){return t.enterArtists(s.id)}}},[t._v(" "+t._s(s.name)+" ")]),n-0!==e.artists.length-1?r("p",[t._v("/")]):t._e()])})),0)])})),0),r("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:t.total,"current-page":t.currentPage,"page-size":50},on:{"current-change":t.handleCurrentChange}})],1):t._e()},y=[],v={name:"SearchAlbum",data:function(){return{offset:0,total:null,albumList:[],currentPage:1,routerAlive:!0}},inject:["routerRefresh"],created:function(){var t=this;this.keywords=this.$route.query.keywords,this.type=this.$route.query.type,this.$bus.$on("creatItem",(function(e){t.keywords=e,t.routerRefresh()})),Object(a["g"])(this.keywords,this.type,this.offset).then((function(e){t.total=e.data.result.albumCount,t.albumList=e.data.result.albums}))},methods:{enterArtists:function(t){this.$router.push({path:"/artist-list",query:{id:t},name:"ArtistListDetail"})},enterAlbum:function(t){this.$router.push({url:"/album",query:{id:t},name:"Album"})},handleCurrentChange:function(t){var e=this;this.currentPage=t,this.offset=this.currentPage-1,Object(a["g"])(this.keywords,this.type,this.offset).then((function(t){e.total=t.data.result.albumCount,e.albumList=t.data.result.albums}))}}},g=v,b=(r("a970"),Object(f["a"])(g,m,y,!1,null,"1a5390d5",null)),k=b.exports,w=function(){var t=this,e=t.$createElement,r=t._self._c||e;return t.routerAlive?r("div",[r("div",{staticClass:"artistsListsItem"},t._l(t.artistList,(function(e,s){return r("div",{key:s,staticClass:"artistsListsItemBox"},[r("img",{attrs:{src:e.picUrl,alt:""},on:{click:function(r){return t.enterArtists(e.id)}}}),r("div",{staticClass:"name",on:{click:function(r){return t.enterArtists(e.id)}}},[t._v(t._s(e.name))])])})),0),r("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:t.total,"current-page":t.currentPage,"page-size":50},on:{"current-change":t.handleCurrentChange}})],1):t._e()},_=[],C={name:"SearchArtists",data:function(){return{offset:0,total:null,artistList:[],currentPage:1,routerAlive:!0}},inject:["routerRefresh"],created:function(){var t=this;this.keywords=this.$route.query.keywords,this.type=this.$route.query.type,this.$bus.$on("creatItem",(function(e){t.keywords=e,t.routerRefresh()})),Object(a["g"])(this.keywords,this.type,this.offset).then((function(e){t.total=e.data.result.artistCount,t.artistList=e.data.result.artists}))},methods:{enterArtists:function(t){this.$router.push({path:"/artist-list",query:{id:t},name:"ArtistListDetail"})},handleCurrentChange:function(t){var e=this;this.currentPage=t,this.offset=this.currentPage-1,Object(a["g"])(this.keywords,this.type,this.offset).then((function(t){e.total=t.data.result.artistCount,e.artistList=t.data.result.artists}))}}},$=C,A=(r("bea7"),Object(f["a"])($,w,_,!1,null,"184f16c8",null)),x=A.exports,L=function(){var t=this,e=t.$createElement,r=t._self._c||e;return t.routerAlive?r("div",[r("el-table",{attrs:{data:t.playList,stripe:"","show-header":!1},on:{"row-click":t.open}},[r("el-table-column",{attrs:{width:"1000px"},scopedSlots:t._u([{key:"default",fn:function(e){return[r("div",{staticClass:"name"},[r("img",{attrs:{src:e.row.coverImgUrl,alt:""}}),r("span",[t._v(t._s(e.row.name))])])]}}],null,!1,731184282)}),r("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.trackCount)+"首 ")]}}],null,!1,3893429799)}),r("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(e){return[r("span",{staticClass:"by"},[t._v("by")]),t._v(" "+t._s(e.row.creator.nickname))]}}],null,!1,4183780277)})],1),r("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:t.total,"current-page":t.currentPage,"page-size":50},on:{"current-change":t.handleCurrentChange}})],1):t._e()},S=[],j={name:"SearchPlayList",data:function(){return{offset:0,total:0,playList:[],currentPage:1,routerAlive:!0}},inject:["routerRefresh"],created:function(){var t=this;this.keywords=this.$route.query.keywords,this.type=this.$route.query.type,this.$bus.$on("creatItem",(function(e){t.keywords=e,t.routerRefresh()})),Object(a["g"])(this.keywords,this.type,this.offset).then((function(e){t.total=e.data.result.playlistCount,t.playList=e.data.result.playlists}))},methods:{handleCurrentChange:function(t){var e=this;this.currentPage=t,this.offset=this.currentPage-1,Object(a["g"])(this.keywords,this.type,this.offset).then((function(t){e.total=t.data.result.playlistCount,e.playList=t.data.result.playlists}))},open:function(t,e,r){this.$router.push({url:"/musiclistdetail",name:"detail",query:{id:t.id}}),console.log(t.id)}}},P=j,R=(r("f338"),Object(f["a"])(P,L,S,!1,null,"4ca7aeed",null)),I=R.exports,q=function(){var t=this,e=t.$createElement,r=t._self._c||e;return t.routerAlive?r("div",[r("el-table",{attrs:{data:t.userList,stripe:"","show-header":!1}},[r("el-table-column",{attrs:{width:"1000px"},scopedSlots:t._u([{key:"default",fn:function(e){return[r("div",{staticClass:"name"},[r("img",{attrs:{src:e.row.avatarUrl,alt:""}}),r("span",[t._v(t._s(e.row.nickname))])])]}}],null,!1,2883890762)}),r("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(e){return[r("span",[t._v("歌单: ")]),t._v(t._s(e.row.playlistCount)+" ")]}}],null,!1,4064766095)}),r("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(e){return[r("span",[t._v("粉丝: ")]),t._v(t._s(e.row.followeds)+" ")]}}],null,!1,513005156)})],1),r("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:t.total,"current-page":t.currentPage,"page-size":50},on:{"current-change":t.handleCurrentChange}})],1):t._e()},O=[],M={name:"SearchUser",data:function(){return{offset:0,total:0,userList:[],currentPage:1,routerAlive:!0}},inject:["routerRefresh"],created:function(){var t=this;this.keywords=this.$route.query.keywords,this.type=this.$route.query.type,this.$bus.$on("creatItem",(function(e){t.keywords=e,t.routerRefresh()})),Object(a["g"])(this.keywords,this.type,this.offset).then((function(e){t.total=e.data.result.userprofileCount,t.userList=e.data.result.userprofiles}))},methods:{handleCurrentChange:function(t){var e=this;this.currentPage=t,this.offset=this.currentPage-1,Object(a["g"])(this.keywords,this.type,this.offset).then((function(t){e.total=t.data.result.userprofileCount,e.userList=t.data.result.userprofiles}))}}},N=M,E=(r("7490"),Object(f["a"])(N,q,O,!1,null,"83593a30",null)),U=E.exports,z=function(){var t=this,e=t.$createElement,r=t._self._c||e;return t.routerAlive?r("div",[r("div",{staticClass:"mvListsItem"},t._l(t.mvList,(function(e,s){return r("div",{key:s,staticClass:"mvListsItemBox"},[r("img",{attrs:{src:e.cover,alt:""},on:{click:function(r){return t.enterMv(e.id)}}}),r("div",{staticClass:"name",on:{click:function(r){return t.enterMv(e.id)}}},[t._v(t._s(e.name))]),r("div",{staticClass:"artistName",on:{click:function(r){return t.enterArtist(e.artistId)}}},[t._v(" "+t._s(e.artistName)+" ")])])})),0),r("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:t.total,"current-page":t.currentPage,"page-size":50},on:{"current-change":t.handleCurrentChange}})],1):t._e()},B=[],D={name:"SearchMv",data:function(){return{offset:0,total:0,mvList:[],currentPage:1,routerAlive:!0}},inject:["routerRefresh"],created:function(){var t=this;this.keywords=this.$route.query.keywords,this.type=this.$route.query.type,this.$bus.$on("creatItem",(function(e){t.keywords=e,t.routerRefresh()})),Object(a["g"])(this.keywords,this.type,this.offset).then((function(e){t.total=e.data.result.mvCount,t.mvList=e.data.result.mvs}))},methods:{enterMv:function(t){this.$router.push({path:"/mv",query:{id:t},name:"MvPlay"})},enterArtist:function(t){this.$router.push({path:"/artist-list",query:{id:t},name:"ArtistListDetail"})},handleCurrentChange:function(t){var e=this;this.currentPage=t,this.offset=this.currentPage-1,Object(a["g"])(this.keywords,this.type,this.offset).then((function(t){e.total=t.data.result.mvCount,e.mvList=t.data.result.mvs}))}}},T=D,F=(r("933a"),Object(f["a"])(T,z,B,!1,null,"63d316ac",null)),J=F.exports,V={name:"Search",components:{SearchSongList:p,SearchAlbum:k,SearchArtists:x,SearchPlayList:I,SearchUser:U,SearchMv:J},data:function(){return{form:[{style:1,name:"单曲"},{style:10,name:"专辑"},{style:100,name:"歌手"},{style:1e3,name:"歌单"},{style:1002,name:"用户"},{style:1004,name:"MV"}],type:1,keywords:"",total:0,routerAlive:!0}},provide:function(){return{routerRefresh:this.routerRefresh}},created:function(){var t=this;this.keywords=this.$route.query.keywords,this.$bus.$on("creatItem",(function(e){t.routerRefresh()}))},methods:{routerRefresh:function(){var t=this;this.routerAlive=!1,this.$nextTick((function(){t.routerAlive=!0}))},changeForm:function(t){this.type=t,this.$router.push({url:"/search",name:"Search",query:{keywords:this.keywords,type:this.type}})}},watch:{$route:function(t,e){this.keywords=this.$route.query.keywords}}},G=V,H=(r("3ff6"),Object(f["a"])(G,s,n,!1,null,"e07a9ed2",null));e["default"]=H.exports},d4a4:function(t,e,r){},dc92:function(t,e,r){},f338:function(t,e,r){"use strict";r("689c")},fd5a:function(t,e,r){}}]);
//# sourceMappingURL=chunk-65dd0ad0.b5993a76.js.map