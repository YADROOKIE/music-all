(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7221a216"],{2022:function(t,e,i){},"21f7":function(t,e,i){},2420:function(t,e,i){"use strict";i("2022")},"361d":function(t,e,i){},"36bb":function(t,e,i){"use strict";i("21f7")},"6a25":function(t,e,i){},7944:function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return t.routerAlive?i("div",{staticClass:"mainBox"},[i("div",{staticClass:"leftBox"},[i("VideoPlay",{attrs:{id:t.id-0}})],1),i("div",{staticClass:"rightBox"},[i("OtherMv",{attrs:{id:t.id-0}})],1)]):t._e()},r=[],s=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("div",{staticClass:"leftBox"},[i("div",{staticClass:"top"},[i("h3",[i("div",{staticClass:"mvBorder"},[t._v("MV")]),t._v(" "+t._s(t.mvdetail.name)+" ")])]),i("video",{attrs:{src:t.mvUrl,preload:"",controls:""}}),i("div",{staticClass:"left"},[i("h2",[t._v("评论")]),i("MvDiscuss",{attrs:{id:t.id-0}})],1)])])},a=[],c=(i("a9e3"),i("db77")),u=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"box"},[i("div",{staticClass:"discussBox"},[i("el-input",{attrs:{type:"textarea",placeholder:"请输入内容",rows:3,name:"text"},model:{value:t.textarea,callback:function(e){t.textarea=e},expression:"textarea"}}),i("el-button",{attrs:{type:"primary"},on:{click:t.open}},[t._v("评论")])],1),t.discussDetail.length>0?i("el-table",{attrs:{data:t.discussDetail,stripe:"","show-header":!1}},[i("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(e){return[i("div",{staticClass:"mainBox"},[i("el-row",[i("el-col",{attrs:{span:2}},[i("img",{attrs:{src:e.row.user.avatarUrl,alt:""}})]),i("el-col",{attrs:{span:22}},[i("div",{staticClass:"dBox"},[i("span",{staticStyle:{color:"rgb(12, 115, 194)"}},[t._v(t._s(e.row.user.nickname)+":")]),i("span",[t._v(t._s(e.row.content))])]),i("div",{staticClass:"timeBox"},[i("span",[t._v(t._s(t._f("dateFormatymdhms")(e.row.time)))])])])],1)],1)]}}],null,!1,1918255214)})],1):i("div",[t._v(" 暂无评论 ")]),i("el-pagination",{attrs:{background:"",layout:"prev, pager, next","current-page":t.currentPage,total:t.total-0,"page-size":20},on:{"current-change":t.handleCurrentChange}})],1)},o=[],l={name:"MvDiscuss",props:{id:{type:Number,default:function(){return[]}}},data:function(){return{textarea:"",currentPage:1,offset:1,discussDetail:[],pageCount:7,total:""}},created:function(){var t=this;Object(c["c"])(this.id,0).then((function(e){t.discussDetail=e.data.comments,t.total=e.data.total}))},methods:{handleCurrentChange:function(t){var e=this;this.currentPage=t,this.offset=this.currentPage-1,Object(c["c"])(this.id,this.offset).then((function(t){e.discussDetail=t.data.comments}))},open:function(){this.$message.error("需登录才能发表评论")}}},d=l,f=(i("2420"),i("2877")),v=Object(f["a"])(d,u,o,!1,null,"066dcb17",null),m=v.exports,h={name:"VideoPlay",components:{MvDiscuss:m},props:{id:{type:Number,default:function(){return[]}}},data:function(){return{mvUrl:"",mvdetail:[]}},created:function(){var t=this;Object(c["d"])(this.id).then((function(e){t.mvUrl=e.data.data.url})),Object(c["b"])(this.id).then((function(e){t.mvdetail=e.data.data}))}},p=h,b=(i("d86a"),Object(f["a"])(p,s,a,!1,null,"5c6c804c",null)),_=b.exports,x=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"box"},[i("div",{staticClass:"simiMv"},[i("h2",[t._v("相关推荐")]),t._l(t.simimvs,(function(e,n){return i("div",{key:n,staticClass:"simiMvItem"},[i("img",{attrs:{src:e.cover,alt:""},on:{click:function(i){return t.enterMv(e.id)}}}),i("div",[i("p",{staticClass:"simiMvItemName",on:{click:function(i){return t.enterMv(e.id)}}},[t._v(" "+t._s(e.name)+" ")]),i("div",{staticClass:"simiMvItemArtistName",on:{click:function(i){return t.enterAetist(e.artistId)}}},[t._v(" "+t._s(e.artistName)+" ")])])])}))],2)])},C=[],y={name:"OtherMv",props:{id:{type:Number,default:function(){return[]}}},inject:["routerRefresh"],data:function(){return{simimvs:[]}},mounted:function(){var t=this;Object(c["e"])(this.id).then((function(e){t.simimvs=e.data.mvs}))},methods:{enterMv:function(t){this.$router.push({path:"/mv",query:{id:t},name:"MvPlay"},this.routerRefresh())},enterAetist:function(t){this.$router.push({path:"/artist-list",query:{id:t},name:"ArtistListDetail"})}}},g=y,O=(i("36bb"),Object(f["a"])(g,x,C,!1,null,null,null)),M=O.exports,j={name:"MvPlay",components:{OtherMv:M,VideoPlay:_},data:function(){return{id:"",routerAlive:!0}},provide:function(){return{routerRefresh:this.routerRefresh}},created:function(){this.id=this.$route.query.id},methods:{routerRefresh:function(){var t=this;this.routerAlive=!1,this.$nextTick((function(){t.routerAlive=!0}))}},watch:{$route:function(t,e){this.id=this.$route.query.id}}},w=j,k=(i("c3bd"),Object(f["a"])(w,n,r,!1,null,"1261883d",null));e["default"]=k.exports},c3bd:function(t,e,i){"use strict";i("361d")},d86a:function(t,e,i){"use strict";i("6a25")},db77:function(t,e,i){"use strict";i.d(e,"d",(function(){return r})),i.d(e,"c",(function(){return s})),i.d(e,"b",(function(){return a})),i.d(e,"e",(function(){return c})),i.d(e,"a",(function(){return u}));var n=i("1bab");function r(t){return Object(n["a"])({url:"/mv/url",params:{id:t}})}function s(t,e){return Object(n["a"])({url:"/comment/mv",params:{id:t,offset:20*e}})}function a(t){return Object(n["a"])({url:"/mv/detail",params:{mvid:t}})}function c(t){return Object(n["a"])({url:"/simi/mv",params:{mvid:t}})}function u(t,e,i,r){return Object(n["a"])({url:"/mv/all",params:{area:t,type:e,order:i,offset:80*r,limit:80}})}}}]);
//# sourceMappingURL=chunk-7221a216.de696535.js.map