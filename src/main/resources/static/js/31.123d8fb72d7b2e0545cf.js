webpackJsonp([31],{"W8/K":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=s("igLI"),a={data:function(){return{erpai:"",captures:[{model:null}],note:""}},methods:{selectFile:function(t,e){var s=this.$refs.erpai[e].files[0];t.model=s.name,t.file=s},addCapture:function(){this.captures.push({model:null})},submit:function(){Object(i.b)(this.captures)&&this.erpai&&Object(i.d)(this.submitter)&&console.log(this.captures,this.submitter)}}},l={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"admin-form"},[s("p",{staticClass:"card-title"},[t._v("生产可视截图")]),t._v(" "),s("div",{staticClass:"border-main"},[s("div",{staticClass:"form-summary"},[s("el-input",{staticClass:"block",attrs:{size:"small"},model:{value:t.erpai,callback:function(e){t.erpai=e},expression:"erpai"}},[s("template",{slot:"prepend"},[t._v("耳牌号:")])],2),t._v(" "),t._l(t.captures,function(e,i){return s("el-input",{key:i,staticClass:"select-file double-width",attrs:{size:"small",disabled:"",value:e.model},nativeOn:{click:function(e){t.$refs.erpai[i].click()}}},[s("template",{slot:"prepend"},[t._v("上传生产可视截图:"),s("input",{ref:"erpai",refInFor:!0,attrs:{type:"file",hidden:""},on:{change:function(s){t.selectFile(e,i)}}})])],2)}),t._v(" "),s("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(e){t.addCapture()}}},[t._v("增设截图")])],2)]),t._v(" "),s("div",{staticClass:"card"},[s("p",{staticClass:"card-title"},[t._v("备注:")]),t._v(" "),s("el-input",{attrs:{type:"textarea"},model:{value:t.note,callback:function(e){t.note=e},expression:"note"}})],1),t._v(" "),s("div",{staticClass:"admin-send"},[s("el-button",{attrs:{type:"primary"}},[t._v("取消")]),t._v(" "),s("el-button",{attrs:{type:"primary"},on:{click:function(e){t.submit()}}},[t._v("提交/更新")])],1)])},staticRenderFns:[]},n=s("VU/8")(a,l,!1,null,null,null);e.default=n.exports}});
//# sourceMappingURL=31.123d8fb72d7b2e0545cf.js.map