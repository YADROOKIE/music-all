(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-587de957"],{"31ad":function(t,n,r){"use strict";r.d(n,"b",(function(){return a})),r.d(n,"f",(function(){return e})),r.d(n,"d",(function(){return u})),r.d(n,"e",(function(){return s})),r.d(n,"a",(function(){return c})),r.d(n,"c",(function(){return o}));var i=r("1bab");function a(t,n,r,a){return Object(i["a"])({url:"/artist/list",params:{area:t,type:n,initial:r,offset:30*a}})}function e(t){return Object(i["a"])({url:"/artists",params:{id:t}})}function u(t){return Object(i["a"])({url:"/artist/top/song",params:{id:t}})}function s(t){return Object(i["a"])({url:"/artist/mv",params:{id:t}})}function c(t){return Object(i["a"])({url:"/artist/album",params:{id:t}})}function o(t){return Object(i["a"])({url:"/artist/desc",params:{id:t}})}},"36be":function(t,n,r){},4775:function(t,n,r){"use strict";r("36be")},"61ce":function(t,n,r){"use strict";r.r(n);var i=function(){var t=this,n=t.$createElement,r=t._self._c||n;return r("div",[0!=t.mvs.length?r("div",{staticClass:"box"},t._l(t.mvs,(function(n,i){return r("div",{key:i,staticClass:"mvsBox",on:{click:function(r){return t.videoPlay(n.id)}}},[r("img",{attrs:{src:n.imgurl,alt:""}}),r("div",{staticClass:"mvsName"},[t._v(t._s(n.name))])])})),0):r("h3",[t._v("暂无MV")])])},a=[],e=r("31ad"),u={name:"ArtistListMv",data:function(){return{id:"",mvs:[]}},created:function(){var t=this;this.id=this.$route.query.id,Object(e["e"])(this.id).then((function(n){t.mvs=n.data.mvs,console.log(t.mvs)}))},methods:{videoPlay:function(t){this.$router.push({path:"/mv",query:{id:t},name:"MvPlay"})}}},s=u,c=(r("4775"),r("2877")),o=Object(c["a"])(s,i,a,!1,null,"67a312d8",null);n["default"]=o.exports}}]);
//# sourceMappingURL=chunk-587de957.3760e5f7.js.map