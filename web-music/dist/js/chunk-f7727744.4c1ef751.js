(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f7727744"],{"0d3c":function(t,s,e){},"2c03":function(t,s,e){"use strict";e("66aa")},"40c9":function(t,s,e){"use strict";e("bec6")},"5a1d":function(t,s,e){"use strict";e("9cec")},"66aa":function(t,s,e){},"9cec":function(t,s,e){},b827:function(t,s,e){"use strict";e.r(s);var a=function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",{staticClass:"main"},[e("div",{staticClass:"albumTop"},[e("img",{attrs:{src:t.albums.blurPicUrl,alt:""}}),e("div",{staticClass:"right"},[e("h3",[e("div",{staticClass:"albumBorder"},[t._v("专辑")]),t._v(" "+t._s(t.albums.name)+" ")]),e("div",{staticClass:"operation"},[e("div",{staticClass:"musicPlay",on:{click:function(s){return t.open(t.allindex)}}},[t._v("播放全部")]),e("div",{staticClass:"collect",on:{click:t.collect}},[t._v(" 收藏("+t._s(t.albumDynamic.subCount)+") ")]),e("div",{staticClass:"share",on:{click:t.share}},[t._v(" 分享("+t._s(t.albumDynamic.shareCount)+") ")])]),e("div",{staticClass:"artists"},[e("p",[t._v("歌手:")]),t._l(t.albums.artists,(function(s,a){return e("p",{key:a,staticClass:"artistsName",on:{click:function(e){return t.enterArtists(s.id)}}},[t._v(" "+t._s(s.name)),a!=t.albums.artists.length-1?e("span",[t._v("/")]):t._e()])}))],2),""!=t.albums.company?e("div",{staticClass:"company"},[t._v(" 发行公司: "+t._s(t.albums.company)+" ")]):t._e(),e("div",{staticClass:"time"},[t._v("时间: "+t._s(t._f("dateFormat")(t.albums.publishTime)))])])]),e("el-menu",{attrs:{"default-active":"1",mode:"horizontal"}},[e("el-menu-item",{attrs:{index:"1"},on:{click:t.showMusicLists}},[t._v("歌曲列表")]),e("el-menu-item",{attrs:{index:"2"},on:{click:t.showDiscuss}},[t._v("评论("+t._s(t.albumDynamic.commentCount)+")")]),e("el-menu-item",{attrs:{index:"3"},on:{click:t.showDesc}},[t._v("专辑详情")])],1),e("AlbumMusicLists",{directives:[{name:"show",rawName:"v-show",value:t.isShowMusicList,expression:"isShowMusicList"}],attrs:{songlist:t.songlist,albumName:t.albumName}}),e("AlbumDiscuss",{directives:[{name:"show",rawName:"v-show",value:t.isShowDiscuss,expression:"isShowDiscuss"}],attrs:{id:t.id-0}}),e("AlbumDesc",{directives:[{name:"show",rawName:"v-show",value:t.isShowDesc,expression:"isShowDesc"}],attrs:{albumDesc:t.albumDesc}})],1)},n=[],i=(e("a4d3"),e("e01a"),e("b0c0"),e("d3b7"),e("ddb0"),e("96cf"),e("1da1")),c=e("1bab");function r(t){return Object(c["a"])({url:"/album",params:{id:t}})}function u(t){return Object(c["a"])({url:"/album/detail/dynamic",params:{id:t}})}function l(t,s){return Object(c["a"])({url:"/comment/album",params:{id:t,offset:20*s}})}var o=e("fba2"),m=function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",[e("div",{staticClass:"discussBox"},[e("el-input",{attrs:{type:"textarea",placeholder:"请输入内容",rows:3,name:"text"},model:{value:t.textarea,callback:function(s){t.textarea=s},expression:"textarea"}}),e("el-button",{attrs:{type:"primary"},on:{click:t.open}},[t._v("评论")])],1),t.discussDetail.length>0?e("div",[e("div",{staticClass:"middle"},[t._v("精彩评论")])]):t._e(),t.discussDetail.length>0?e("el-table",{attrs:{data:t.discussDetail,stripe:"","show-header":!1}},[e("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(s){return[e("div",{staticClass:"mainBox"},[e("el-row",[e("el-col",{attrs:{span:1}},[e("img",{attrs:{src:s.row.user.avatarUrl,alt:""}})]),e("el-col",{attrs:{span:23}},[e("div",{staticClass:"dBox"},[e("span",{staticStyle:{color:"rgb(12, 115, 194)"}},[t._v(t._s(s.row.user.nickname)+":")]),e("span",[t._v(t._s(s.row.content))])]),e("div",{staticClass:"timeBox"},[e("span",[t._v(t._s(t._f("dateFormatymdhms")(s.row.time)))])])])],1)],1)]}}],null,!1,1351397388)})],1):e("h3",[t._v("暂无评论")]),e("el-pagination",{attrs:{background:"",layout:"prev, pager, next","current-page":t.currentPage,total:t.total-0,"page-size":20},on:{"current-change":t.handleCurrentChange}})],1)},d=[],h=(e("a9e3"),{name:"AlbumDiscuss",props:{id:{type:Number,default:function(){return[]}}},data:function(){return{textarea:"",currentPage:1,offset:0,discussDetail:[],pageCount:7,total:""}},created:function(){var t=this;l(this.id,0).then((function(s){t.discussDetail=s.data.comments,t.total=s.data.total}))},methods:{handleCurrentChange:function(t){var s=this;this.currentPage=t,this.offset=this.currentPage-1,l(this.id,this.offset).then((function(t){s.discussDetail=t.data.comments}))},open:function(){this.$message.error("需登录才能发表评论")}}}),f=h,b=(e("2c03"),e("2877")),p=Object(b["a"])(f,m,d,!1,null,"5483b6f2",null),v=p.exports,w=function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",[e("h3",[t._v("专辑介绍")]),t._l(t.formatText(t.albumDesc),(function(s,a){return e("div",{key:a,staticClass:"txt"},[t._v(" "+t._s(s)+" ")])}))],2)},_=[],g=(e("ac1f"),e("1276"),{name:"AlbumDesc",props:{albumDesc:{type:String,default:function(){return[]}}},data:function(){return{}},methods:{formatText:function(t){var s=t.split("\n");return s}}}),x=g,y=(e("5a1d"),Object(b["a"])(x,w,_,!1,null,"51f8e92b",null)),D=y.exports,k=function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.songlist,stripe:"","row-class-name":t.tableRowClassName},on:{"row-dblclick":t.dblopen}},[a("el-table-column",{attrs:{type:"index"}}),a("el-table-column",{attrs:{label:"操作",width:"60px"}},[[a("img",{attrs:{src:e("e050"),alt:""},on:{click:t.collect}})]],2),a("el-table-column",{attrs:{prop:"name",label:"音乐标题"}}),a("el-table-column",{attrs:{prop:"ar",label:"歌手"},scopedSlots:t._u([{key:"default",fn:function(s){return t._l(s.row.ar,(function(s,e){return a("span",{key:e},[t._v(t._s(s.name))])}))}}])}),a("el-table-column",{attrs:{label:"专辑"}},[t._v(t._s(t.albumName))]),a("el-table-column",{attrs:{prop:"dt",label:"时长"},scopedSlots:t._u([{key:"default",fn:function(s){return[t._v(" "+t._s(t._f("dateFormatms")(s.row.dt))+" ")]}}])})],1)],1)},C=[],S=e("0608"),M={name:"AlbumMusicLists",props:{songlist:{type:Array,default:function(){return[]}},albumName:{type:String,default:function(){return[]}}},mixins:[S["a"]],data:function(){return{currentRowIndex:0,musiclist:[]}},methods:{tableRowClassName:function(t){var s=t.row,e=t.rowIndex;s.row_index=e},dblopen:function(t,s,e){var a=this;this.currentRowIndex=t.row_index,this.$confirm("“双击播放”会用当前列表的音乐替换播放列表, 是否继续?","替换播放列表",{confirmButtonText:"继续",cancelButtonText:"取消",type:"warning"}).then((function(){a.$message("音乐缓存需要10~20s，请稍后"),a.$bus.$emit("playingIndex",a.currentRowIndex),a.playMusic(a.currentRowIndex)})).catch((function(){}))},playMusic:function(t){var s=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var a,n,i,c;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:s.musiclist=[],e.t0=regeneratorRuntime.keys(s.songlist);case 2:if((e.t1=e.t0()).done){e.next=13;break}return a=e.t1.value,e.next=6,Object(o["l"])(s.songlist[a].id);case 6:n=e.sent,i=n.data,c=new o["p"](i.songs),s.musiclist.push(c),a==s.songlist.length-1&&s.PlayMusic(t),e.next=2;break;case 13:case"end":return e.stop()}}),e)})))()},collect:function(){this.$message.error("需登录才能收藏")}}},$=M,A=(e("e470"),Object(b["a"])($,k,C,!1,null,"c03ae46a",null)),N=A.exports,L={name:"Ablum",components:{AlbumDiscuss:v,AlbumDesc:D,AlbumMusicLists:N},data:function(){return{id:"",albums:[],albumDynamic:[],isShowMusicList:!0,isShowDiscuss:!1,isShowDesc:!1,albumDesc:"",songlist:[],albumName:"",allindex:0}},mixins:[S["a"]],created:function(){var t=this;this.id=this.$route.query.id,r(this.id).then((function(s){t.albums=s.data.album,t.albumDesc=s.data.album.description,t.songlist=s.data.songs,t.albumName=s.data.album.name})),u(this.id).then((function(s){t.albumDynamic=s.data}))},methods:{showMusicLists:function(){this.isShowMusicList=!0,this.isShowDiscuss=!1,this.isShowDesc=!1},showDiscuss:function(){this.isShowMusicList=!1,this.isShowDiscuss=!0,this.isShowDesc=!1},showDesc:function(){this.isShowMusicList=!1,this.isShowDiscuss=!1,this.isShowDesc=!0},enterArtists:function(t){this.$router.push({path:"/artist-list",query:{id:t},name:"ArtistListDetail"})},open:function(t){var s=this;this.$confirm("“播放全部”会替换播放列表, 是否继续?","替换播放列表",{confirmButtonText:"继续",cancelButtonText:"取消",type:"warning"}).then((function(){s.$message("音乐缓存需要10~20s，请稍后"),s.playMusic(t)})).catch((function(){}))},playMusic:function(t){var s=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var a,n,i,c;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:s.musiclist=[],e.t0=regeneratorRuntime.keys(s.songlist);case 2:if((e.t1=e.t0()).done){e.next=13;break}return a=e.t1.value,e.next=6,Object(o["l"])(s.songlist[a].id);case 6:n=e.sent,i=n.data,c=new o["p"](i.songs),s.musiclist.push(c),a==s.songlist.length-1&&s.PlayMusic(t),e.next=2;break;case 13:case"end":return e.stop()}}),e)})))()},collect:function(){this.$message.error("需登录才能收藏")},share:function(){this.$message.error("需登录才能分享")}}},R=L,j=(e("40c9"),Object(b["a"])(R,a,n,!1,null,"00fe67ac",null));s["default"]=j.exports},bec6:function(t,s,e){},e470:function(t,s,e){"use strict";e("0d3c")}}]);
//# sourceMappingURL=chunk-f7727744.4c1ef751.js.map