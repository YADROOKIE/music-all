(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-242f5766"],{"5aac":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-row",{attrs:{gutter:20}},t._l(t.list,(function(e){return a("el-col",{key:e.id,attrs:{span:6}},[a("div",{staticClass:"mborder"},[a("img",{staticClass:"image",staticStyle:{width:"100%",display:"block"},attrs:{src:"http://localhost:8080/"+e.cover}}),a("div",{staticStyle:{padding:"14px"}},[a("span",[t._v(t._s(e.name))]),a("div",{staticClass:"bottom clearfix"},[a("div",{staticStyle:{"margin-top":"2px",color:"grey"}},[t._v(t._s(e.createAt))]),a("el-button",{on:{click:function(a){return t.buy(e.id)}}},[t._v("购买")])],1)])])])})),1)],1)},n=[],i=a("9b70"),r={name:"MvList",data:function(){return{list:[],param:{}}},created:function(){var t=this;Object(i["b"])().then((function(e){console.log(e),t.list=e.data.data.list}))},methods:{buy:function(t){var e=this,a=sessionStorage.getItem("user");if(null!=a&&""!=a){var s=JSON.parse(a);this.param.ticketId=t,this.param.userId=s.id,Object(i["a"])(this.param).then((function(t){console.log(t),2e4===t.data.code?e.$message({message:"购买成功",type:"success"}):e.$message({message:"购买失败",type:"danger"})}))}else this.$message({message:"未登录请先登录",type:"warning"})}}},c=r,o=(a("7731"),a("2877")),u=Object(o["a"])(c,s,n,!1,null,"4b35a908",null);e["default"]=u.exports},7731:function(t,e,a){"use strict";a("f9b1")},"9b70":function(t,e,a){"use strict";a.d(e,"a",(function(){return n})),a.d(e,"b",(function(){return i})),a.d(e,"c",(function(){return r}));var s=a("1bab");function n(t){return Object(s["a"])({url:"/share/ticket/buy",method:"post",data:t})}function i(){return Object(s["a"])({url:"/share/ticket/list",method:"get"})}function r(t){return Object(s["a"])({url:"/share/ticket/myorder/"+t,method:"get"})}},f9b1:function(t,e,a){}}]);
//# sourceMappingURL=chunk-242f5766.760da811.js.map