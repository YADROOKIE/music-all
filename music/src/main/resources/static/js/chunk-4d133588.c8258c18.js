(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4d133588"],{2575:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("div",{staticClass:"top"},[n("h3",[t._v("全部mv")]),n("div",{staticClass:"areaItem"},[t._v(" 语种: "),t._l(t.nation,(function(e,r){return n("span",{key:r,class:{changeColor:t.a===r},on:{click:function(n){return t.changeArea(e,r)}}},[t._v(t._s(e))])}))],2),n("div",{staticClass:"typeItem"},[t._v(" 分类: "),t._l(t.kind,(function(e,r){return n("span",{key:r,class:{changeColor:t.b===r},on:{click:function(n){return t.changeType(e,r)}}},[t._v(t._s(e))])}))],2),n("div",{staticClass:"orderItem"},[t._v(" 排序: "),t._l(t.orders,(function(e,r){return n("span",{key:r,class:{changeColor:t.c===r},on:{click:function(n){return t.changeOrder(e,r)}}},[t._v(t._s(e))])}))],2)]),n("div",{staticClass:"mvListsItem"},t._l(t.mvlists,(function(e,r){return n("div",{key:r,staticClass:"mvListsItemBox"},[n("img",{attrs:{src:e.cover,alt:""},on:{click:function(n){return t.enterMv(e.id)}}}),n("div",{staticClass:"name",on:{click:function(n){return t.enterMv(e.id)}}},[t._v(t._s(e.name))]),n("div",{staticClass:"artistName",on:{click:function(n){return t.enterArtist(e.artistId)}}},[t._v(" "+t._s(e.artistName)+" ")])])})),0),n("el-pagination",{attrs:{background:"",layout:"prev, pager, next","current-page":t.currentPage,"page-size":80,total:t.total-0},on:{"current-change":t.handleCurrentChange}})],1)},i=[],a=n("db77"),s={name:"AllMvList",data:function(){return{nation:["全部","内地","港台","欧美","韩国","日本"],kind:["全部","官方版","原声","现场版","网易出品"],orders:["上升最快","最热","最新"],area:"全部",type:"全部",order:"上升最快",offset:0,mvlists:[],total:0,currentPage:1,a:0,b:0,c:0}},created:function(){this.getMvList()},methods:{getMvList:function(){var t=this;Object(a["a"])(this.area,this.type,this.order,this.offset).then((function(e){t.mvlists=e.data.data,t.total=e.data.count}))},changeArea:function(t,e){this.area=t,this.a=e,this.offset=0,this.getMvList(),this.currentPage=1},changeType:function(t,e){this.type=t,this.b=e,this.offset=0,this.getMvList(),this.currentPage=1},changeOrder:function(t,e){this.order=t,this.c=e,this.offset=0,this.getMvList(),this.currentPage=1},handleCurrentChange:function(t){var e=this;this.currentPage=t,this.offset=this.currentPage-1,Object(a["a"])(this.area,this.type,this.order,this.offset).then((function(t){e.mvlists=t.data.data}))},enterMv:function(t){this.$router.push({path:"/mv",query:{id:t},name:"MvPlay"})},enterArtist:function(t){this.$router.push({path:"/artist-list",query:{id:t},name:"ArtistListDetail"})}}},c=s,u=(n("34a3"),n("2877")),o=Object(u["a"])(c,r,i,!1,null,"4d8131b1",null);e["default"]=o.exports},"34a3":function(t,e,n){"use strict";n("4cd7")},"4cd7":function(t,e,n){},db77:function(t,e,n){"use strict";n.d(e,"d",(function(){return i})),n.d(e,"c",(function(){return a})),n.d(e,"b",(function(){return s})),n.d(e,"e",(function(){return c})),n.d(e,"a",(function(){return u}));var r=n("1bab");function i(t){return Object(r["a"])({url:"/mv/url",params:{id:t}})}function a(t,e){return Object(r["a"])({url:"/comment/mv",params:{id:t,offset:20*e}})}function s(t){return Object(r["a"])({url:"/mv/detail",params:{mvid:t}})}function c(t){return Object(r["a"])({url:"/simi/mv",params:{mvid:t}})}function u(t,e,n,i){return Object(r["a"])({url:"/mv/all",params:{area:t,type:e,order:n,offset:80*i,limit:80}})}}}]);
//# sourceMappingURL=chunk-4d133588.c8258c18.js.map