webpackJsonp([50],{b7Mr:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var l=t("3QKP"),r=t("JAV3"),o=t("RO48"),s={components:{dataCur:l.a},watch:{"models.sex":{handler:function(e){this.models.tradeMarkEartag=e?"M":"G"},immediate:!0}},mounted:function(){this.models.eartagOfFather="G",this.models.eartagOfMother="M",this.models.eartagOfFathersFather="G",this.models.eartagOfFathersMother="M",this.models.eartagOfMothersFather="G",this.models.eartagOfMothersMother="M"},data:function(){return{getGeneaRec:o._3,postGeneaRec:o._34,updateGeneaRec:o._47,items:[{label:"性别",model:"sex",type:"radio"},{label:"原耳牌",model:"nativeEartag"},{label:"免疫耳牌",model:"immuneEartag",mr:1},{label:"商标耳牌",model:"tradeMarkEartag",trade:!0},{label:"种羊基地",model:"breedingSheepBase"},{label:"初登时间",model:"birthTime",type:"time",mr:1},{label:"初登体重(kg)",model:"birthWeight"},{label:"颜色",model:"color"},{label:"父号",model:"eartagOfFather",mr:1},{label:"母号",model:"eartagOfMother"},{label:"父父号",model:"eartagOfFathersFather"},{label:"父母号",model:"eartagOfFathersMother",mr:1},{label:"母父号",model:"eartagOfMothersFather"},{label:"母母号",model:"eartagOfMothersMother"},{label:"品种名",model:"typeName",type:"select",fetchSuggestions:r.g,mr:1}],models:{immuneEartag:null,nativeEartag:null,tradeMarkEartag:null,breedingSheepBase:null,birthTime:null,birthWeight:null,color:null,eartagOfFather:null,eartagOfMother:null,eartagOfFathersFather:null,eartagOfFathersMother:null,eartagOfMothersFather:null,eartagOfMothersMother:null,sex:0,remark:"",typeName:null}}}},d={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{staticClass:"admin-form"},[t("data-cur",{attrs:{title:"系谱档案",modpath:"genealogic","radio-index":0,models:e.models,items:e.items,"get-data":e.getGeneaRec,"post-data":e.postGeneaRec,"update-data":e.updateGeneaRec},on:{"update:models":function(a){e.models=a}}})],1)},staticRenderFns:[]},n=t("VU/8")(s,d,!1,null,null,null);a.default=n.exports}});
//# sourceMappingURL=50.d1af2e9d46d16708af1c.js.map