(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-9347e25c"],{"190a":function(t,e,n){"use strict";n.r(e);var a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[null==t.user?n("h3",[t._v(" 未登录请先登录 ")]):n("div",[n("el-table",{staticStyle:{width:"100%"},attrs:{data:t.list,stripe:""}},[n("el-table-column",{attrs:{prop:"id",label:"订单号",width:"180"}}),n("el-table-column",{attrs:{prop:"commodity.name",label:"演唱会名称",width:"180"}}),n("el-table-column",{attrs:{prop:"createAt",label:"创建时间"}}),n("el-table-column",{attrs:{fixed:"right",label:"操作",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-button",{attrs:{type:"text",size:"small"},on:{click:function(n){return t.handleClick(e.row)}}},[t._v("查看")]),n("el-button",{attrs:{type:"text",size:"small"}},[t._v("编辑")])]}}])})],1)],1)])},r=[],l=n("9b70"),s={name:"NewSongs",data:function(){return{user:null,message:"未登录请先登录",list:[]}},created:function(){this.load()},methods:{load:function(){var t=this,e=sessionStorage.getItem("user");null!=e&&""!=e?(this.user=JSON.parse(e),Object(l["c"])(this.user.id).then((function(e){console.log(e),t.list=e.data.data.list}))):this.$message({message:"未登录请先登录",type:"warning"})}}},i=s,u=n("2877"),o=Object(u["a"])(i,a,r,!1,null,"6df15c9c",null);e["default"]=o.exports},"9b70":function(t,e,n){"use strict";n.d(e,"a",(function(){return r})),n.d(e,"b",(function(){return l})),n.d(e,"c",(function(){return s}));var a=n("1bab");function r(t){return Object(a["a"])({url:"/share/ticket/buy",method:"post",data:t})}function l(){return Object(a["a"])({url:"/share/ticket/list",method:"get"})}function s(t){return Object(a["a"])({url:"/share/ticket/myorder/"+t,method:"get"})}}}]);
//# sourceMappingURL=chunk-9347e25c.36663612.js.map