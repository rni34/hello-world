(function(g){var window=this;var e3=function(a,b,c){var d=b.ra();g.L(a.element,"ytp-suggestion-set",!!d.videoId);var e=b.getPlaylistId();c=b.fc(c?c:"mqdefault.jpg");var f=b instanceof g.DB&&b.lengthSeconds?g.aL(b.lengthSeconds):null,k=!!e;e=k&&"RD"==(new g.tD(e.substr(0,2),e.substr(2))).type;var l=b instanceof g.DB?b.na:null;d={title:b.title,author:b.author,author_and_views:d.shortViewCount?b.author+" \u2022 "+d.shortViewCount:b.author,aria_label:b.Oi||g.LL("$TITLE \u3092\u518d\u751f",{TITLE:b.title}),duration:f,url:b.Hh(),is_live:l,
is_list:k,is_mix:e,background:c?"background-image: url("+c+")":""};b instanceof g.vD&&(d.playlist_length=b.getLength());a.update(d)},f3=function(a,b){g.tu.call(this,{A:"div",
U:["html5-endscreen","ytp-player-content",b||"base-endscreen"]});this.player=a;this.created=!1},g3=function(a){g.T.call(this,{A:"div",
U:["ytp-upnext","ytp-player-content"],K:{"aria-label":"{{aria_label}}"},H:[{A:"div",F:"ytp-cued-thumbnail-overlay-image",K:{style:"{{background}}"}},{A:"span",F:"ytp-upnext-top",H:[{A:"span",F:"ytp-upnext-header",T:"\u6b21\u306e\u52d5\u753b"},{A:"span",F:"ytp-upnext-title",T:"{{title}}"},{A:"span",F:"ytp-upnext-author",T:"{{author}}"}]},{A:"a",F:"ytp-upnext-autoplay-icon",K:{role:"button",href:"{{url}}","aria-label":"\u6b21\u306e\u52d5\u753b\u3092\u518d\u751f"},H:[{A:"svg",K:{height:"100%",version:"1.1",
viewBox:"0 0 72 72",width:"100%"},H:[{A:"circle",F:"ytp-svg-autoplay-circle",K:{cx:"36",cy:"36",fill:"#fff","fill-opacity":"0.3",r:"31.5"}},{A:"circle",F:"ytp-svg-autoplay-ring",K:{cx:"-36",cy:"36","fill-opacity":"0",r:"33.5",stroke:"#FFFFFF","stroke-dasharray":"211","stroke-dashoffset":"-211","stroke-width":"4",transform:"rotate(-90)"}},{A:"path",F:"ytp-svg-fill",K:{d:"M 24,48 41,36 24,24 V 48 z M 44,24 v 24 h 4 V 24 h -4 z"}}]}]},{A:"span",F:"ytp-upnext-bottom",H:[{A:"span",F:"ytp-upnext-cancel"},
{A:"span",F:"ytp-upnext-paused",T:"\u81ea\u52d5\u518d\u751f\u306f\u4e00\u6642\u505c\u6b62\u3055\u308c\u3066\u3044\u307e\u3059"}]}]});this.w=null;var b=this.i["ytp-upnext-cancel"];this.w=new g.T({A:"button",U:["ytp-upnext-cancel-button","ytp-button"],K:{tabindex:"0","aria-label":"\u81ea\u52d5\u518d\u751f\u3092\u30ad\u30e3\u30f3\u30bb\u30eb\u3059\u308b"},T:"\u30ad\u30e3\u30f3\u30bb\u30eb"});g.H(this,this.w);this.w.ea("click",this.aH,this);this.w.g(b);this.l=a;this.I=this.i["ytp-svg-autoplay-ring"];
this.C=this.B=this.o=this.u=null;this.D=new g.I(this.ul,5E3,this);g.H(this,this.D);this.G=0;this.J(this.i["ytp-upnext-autoplay-icon"],"click",this.MI);this.zx();this.J(a,"autonavvisibility",this.zx);this.J(a,"mdxnowautoplaying",this.eI);this.J(a,"mdxautoplaycanceled",this.fI);this.J(a,"mdxautoplayupnext",this.Oz);3==this.l.getPresentingPlayerType()&&(a=(a=g.dK(g.XJ(this.l)))?a.RB():null)&&this.Oz(a)},h3=function(a,b){if(!a.o){g.Lo("a11y-announce","\u6b21\u306e\u52d5\u753b "+a.u.title);
a.G=(0,g.Kq)();a.o=new g.I((0,g.x)(a.wo,a,b),25);a.wo(b);var c=b||g.Q(a.l.L().experiments,"autoplay_time")||1E4;a.l.ha("onAutonavCoundownStarted",c)}g.kn(a.element,"ytp-upnext-autoplay-paused")},j3=function(a){i3(a);
a.G=(0,g.Kq)();a.wo();g.K(a.element,"ytp-upnext-autoplay-paused")},i3=function(a){a.o&&(a.o.dispose(),a.o=null)},k3=function(a,b){b=void 0===b?!1:b;
if(g.P(a.l.L().experiments,"autonav_notifications")&&b&&window.Notification&&document.hasFocus){var c=Notification.permission;"default"==c?Notification.requestPermission():"granted"!=c||document.hasFocus()||(c=a.u.ra(),a.ul(),a.B=new Notification("\u6b21\u306e\u52d5\u753b",{body:c.title,icon:c.fc()}),a.C=a.J(a.B,"click",a.CI),a.D.start())}i3(a);a.l.nextVideo(!1,b)},l3=function(a){f3.call(this,a,"subscribecard-endscreen");
var b=a.getVideoData();this.l=new g.T({A:"div",F:"ytp-subscribe-card",H:[{A:"img",F:"ytp-author-image",K:{src:b.Od}},{A:"div",F:"ytp-subscribe-card-right",H:[{A:"div",F:"ytp-author-name",T:b.author},{A:"div",F:"html5-subscribe-button-container"}]}]});g.H(this,this.l);this.l.g(this.element);this.o=new g.$P("\u767b\u9332",null,"\u767b\u9332\u89e3\u9664",null,!0,!1,b.Ef,b.subscribed,"trailer-endscreen",null,null,a);g.H(this,this.o);this.o.g(this.l.i["html5-subscribe-button-container"]);this.hide()},
m3=function(a){var b=a.L(),c=g.Bs||g.$f?{style:"will-change: opacity"}:void 0,d=b.i,e=["ytp-videowall-still"];
b.g&&e.push("ytp-videowall-show-text");g.T.call(this,{A:"a",U:e,K:{href:"{{url}}",target:d?b.u:"","aria-label":"{{aria_label}}","data-is-live":"{{is_live}}","data-is-list":"{{is_list}}","data-is-mix":"{{is_mix}}"},H:[{A:"div",F:"ytp-videowall-still-image",K:{style:"{{background}}"}},{A:"span",F:"ytp-videowall-still-info",H:[{A:"span",F:"ytp-videowall-still-info-bg",H:[{A:"span",F:"ytp-videowall-still-info-content",K:c,H:[{A:"span",F:"ytp-videowall-still-info-title",T:"{{title}}"},{A:"span",F:"ytp-videowall-still-info-author",
T:"{{author_and_views}}"},{A:"span",F:"ytp-videowall-still-info-live",T:"\u30e9\u30a4\u30d6"},{A:"span",F:"ytp-videowall-still-info-duration",T:"{{duration}}"}]}]}]},{A:"span",U:["ytp-videowall-still-listlabel-regular","ytp-videowall-still-listlabel"],H:[{A:"span",F:"ytp-videowall-still-listlabel-icon"},"\u518d\u751f\u30ea\u30b9\u30c8",{A:"span",F:"ytp-videowall-still-listlabel-length",H:[" (",{A:"span",T:"{{playlist_length}}"},")"]}]},{A:"span",U:["ytp-videowall-still-listlabel-mix","ytp-videowall-still-listlabel"],
H:[{A:"span",F:"ytp-videowall-still-listlabel-mix-icon"},"\u30df\u30c3\u30af\u30b9\u30ea\u30b9\u30c8",{A:"span",F:"ytp-videowall-still-listlabel-length",T:" (50+)"}]}]});this.w=d;this.l=a;this.o=null;this.u=new g.Is(this);g.H(this,this.u);this.ea("click",this.C);this.ea("keypress",this.D);this.u.J(a,"videodatachange",this.B);g.wK(a,this.element,this);this.B()},n3=function(a){g.yK(a.l,a.element);
var b=a.o.ra().videoId,c=a.o.getPlaylistId();g.oS(a.l.app,b,a.o.Db,c,void 0,void 0)},o3=function(a){f3.call(this,a,"videowall-endscreen");
this.B=a;this.w=0;this.o=[];this.C=this.O=this.u=null;this.D=this.P=!1;this.M=null;this.ba=new g.Is(this);g.H(this,this.ba);this.G=new g.I(g.Ra(g.K,this.element,"ytp-show-tiles"),0);g.H(this,this.G);var b=new g.T({A:"button",U:["ytp-button","ytp-endscreen-previous"],K:{"aria-label":"\u524d\u3078"},H:[g.iL()]});g.H(this,b);b.g(this.element);b.ea("click",this.sE,this);this.I=new g.mu({A:"div",F:"ytp-endscreen-content"});g.H(this,this.I);this.I.g(this.element);b=new g.T({A:"button",U:["ytp-button","ytp-endscreen-next"],
K:{"aria-label":"\u6b21\u3078"},H:[g.jL()]});g.H(this,b);b.g(this.element);b.ea("click",this.rE,this);this.l=new g3(a);g.H(this,this.l);g.tK(this.player,this.l.element,4);this.hide()},p3=function(a){return g.uK(a.player)&&a.Ct()&&!a.C},q3=function(a,b){return(0,g.ee)(b.suggestions,function(c){c=g.ML(a.B.L(),c);
g.H(a,c);return c})},r3=function(a){var b=a.tq();
b!=a.P&&(a.P=b,a.player.N("autonavvisibility"))},t3=function(a){g.QO.call(this,a);
this.g=null;this.i=new g.Is(this);g.H(this,this.i);this.l=a.L();s3(a)?this.g=new o3(this.player):this.l.Pa?this.g=new l3(this.player):this.g=new f3(this.player);g.H(this,this.g);g.tK(this.player,this.g.element,4);this.Ty();this.i.J(a,"videodatachange",this.Ty,this);this.i.J(a,g.sD("endscreen"),this.AD,this);this.i.J(a,"crx_endscreen",this.BD,this)},s3=function(a){a=a.L();
return a.La&&!a.Pa};
g.r(f3,g.tu);f3.prototype.create=function(){this.created=!0};
f3.prototype.destroy=function(){this.created=!1};
f3.prototype.Ct=function(){return!1};
f3.prototype.tq=function(){return!1};g.r(g3,g.T);g.h=g3.prototype;g.h.ul=function(){this.B&&(this.D.stop(),this.Sa(this.C),this.C=null,this.B.close(),this.B=null)};
g.h.zx=function(){g.ru(this,g.YJ(this.l))};
g.h.CI=function(){window.focus();this.ul()};
g.h.hide=function(){g.T.prototype.hide.call(this)};
g.h.wo=function(a){a=a||g.Q(this.l.L().experiments,"autoplay_time")||1E4;var b=Math.min((0,g.Kq)()-this.G,a);a=Math.min(b/a,1);this.I.setAttribute("stroke-dashoffset",-211*(a+1));1<=a&&3!=this.l.getPresentingPlayerType()?k3(this,!0):this.o&&this.o.start()};
g.h.MI=function(a){!g.Yd(this.w.element,g.$q(a))&&g.TL(a,this.l)&&k3(this)};
g.h.aH=function(){g.$J(this.l,!0)};
g.h.eI=function(a){this.l.getPresentingPlayerType();this.show();h3(this,a)};
g.h.Oz=function(a){this.l.getPresentingPlayerType();this.u&&this.u.ra().videoId==a.ra().videoId||(this.u=a,e3(this,a,"hqdefault.jpg"))};
g.h.fI=function(){this.l.getPresentingPlayerType();i3(this);this.hide()};
g.h.V=function(){i3(this);this.ul();g.T.prototype.V.call(this)};g.r(l3,f3);g.r(m3,g.T);m3.prototype.C=function(a){g.TL(a,this.l,this.w,this.o.Db||void 0)&&n3(this)};
m3.prototype.D=function(a){switch(a.keyCode){case 13:case 32:g.er(a)||(n3(this),g.dr(a))}};
m3.prototype.B=function(){var a=this.l.getVideoData(),b=this.l.L();this.w=a.Wb?!1:b.i};g.r(o3,f3);g.h=o3.prototype;g.h.create=function(){f3.prototype.create.call(this);var a=this.player.getVideoData();a&&(this.u=q3(this,a),this.O=a);this.Cf();this.ba.J(this.player,"appresize",this.Cf);this.ba.J(this.player,"onVideoAreaChange",this.Cf);this.ba.J(this.player,"videodatachange",this.tE);this.ba.J(this.player,"autonavchange",this.uq);this.ba.J(this.player,"autonavcancel",this.qE);a=this.O.autonavState;a!=this.M&&this.uq(a);this.ba.J(this.element,"transitionend",this.dK)};
g.h.destroy=function(){g.Ks(this.ba);g.Bi(this.o);this.o=[];this.u=null;f3.prototype.destroy.call(this);g.kn(this.element,"ytp-show-tiles");this.G.stop();this.M=this.O.autonavState};
g.h.Ct=function(){return 1!=this.O.autonavState};
g.h.show=function(){f3.prototype.show.call(this);g.kn(this.element,"ytp-show-tiles");this.player.L().g?g.bn(this.G):this.G.start();(this.D||this.C&&this.C!=this.O.clientPlaybackNonce)&&g.$J(this.player,!1);p3(this)?(r3(this),2==this.O.autonavState?g.P(this.player.L().experiments,"fast_autonav_in_background")&&3==this.player.getVisibilityState()?k3(this.l,!0):h3(this.l):3==this.O.autonavState&&j3(this.l)):(g.$J(this.player,!0),r3(this))};
g.h.hide=function(){f3.prototype.hide.call(this);j3(this.l);r3(this)};
g.h.dK=function(a){g.$q(a)==this.element&&this.Cf()};
g.h.Cf=function(){if(this.u&&this.u.length){g.K(this.element,"ytp-endscreen-paginate");var a=g.kK(this.B,!0,this.B.isFullscreen()),b=g.JC(this.B);b&&(b=b.Vb()?48:32,a.width-=2*b);var c=a.width/a.height,d=96/54,e=b=2,f=Math.max(a.width/96,2),k=Math.max(a.height/54,2),l=this.u.length,m=Math.pow(2,2);var n=l*m+(Math.pow(2,2)-m);n+=Math.pow(2,2)-m;for(n-=m;0<n&&(b<f||e<k);){var q=b/2,t=e/2,v=b<=f-2&&n>=t*m,y=e<=k-2&&n>=q*m;if((q+1)/t*d/c>c/(q/(t+1)*d)&&y)n-=q*m,e+=2;else if(v)n-=t*m,b+=2;else if(y)n-=
q*m,e+=2;else break}d=!1;n>=3*m&&6>=l*m-n&&(4<=e||4<=b)&&(d=!0);m=96*b;n=54*e;c=m/n<c?a.height/n:a.width/m;c=Math.min(c,2);m*=c;n*=c;m*=g.pd(a.width/m||1,1,1.21);n*=g.pd(a.height/n||1,1,1.21);m=Math.floor(Math.min(a.width,m));n=Math.floor(Math.min(a.height,n));a=this.I.element;g.pg(a,m,n);g.Xf(a,{marginLeft:m/-2+"px",marginTop:n/-2+"px"});c=this.l;f=this.u[0];c.u=f;e3(c,f,"hqdefault.jpg");g.L(this.element,"ytp-endscreen-takeover",p3(this));r3(this);m+=4;n+=4;for(f=c=0;f<b;f++)for(k=0;k<e;k++)if(q=
c,t=0,d&&f>=b-2&&k>=e-2?t=1:0==k%2&&0==f%2&&(2>k&&2>f?0==k&&0==f&&(t=2):t=2),q=g.qd(q+this.w,l),0!=t){v=this.o[c];v||(v=new m3(this.player),this.o[c]=v,a.appendChild(v.element));y=Math.floor(n*k/e);var B=Math.floor(m*f/b),C=Math.floor(n*(k+t)/e)-y-4,G=Math.floor(m*(f+t)/b)-B-4;g.eg(v.element,B,y);g.pg(v.element,G,C);g.Xf(v.element,"transitionDelay",(k+f)/20+"s");g.L(v.element,"ytp-videowall-still-mini",1==t);g.L(v.element,"ytp-videowall-still-large",2<t);t=v;q=this.u[q];t.o!=q&&(t.o=q,e3(t,q,g.hn(t.element,
"ytp-videowall-still-large")?"hqdefault.jpg":"mqdefault.jpg"),(q=(q=q.Db)&&q.itct)&&g.xK(t.l,t.element,q));c++}g.L(this.element,"ytp-endscreen-paginate",c<l);for(b=this.o.length-1;b>=c;b--)e=this.o[b],g.Ud(e.element),g.Ai(e);this.o.length=c}};
g.h.tE=function(){var a=this.player.getVideoData();this.O!=a&&(this.w=0,this.u=q3(this,a),this.O=a,this.Cf())};
g.h.rE=function(){this.w+=this.o.length;this.Cf()};
g.h.sE=function(){this.w-=this.o.length;this.Cf()};
g.h.TC=function(){return!!this.l.o};
g.h.uq=function(a){1==a?(this.D=!1,this.C=this.O.clientPlaybackNonce,i3(this.l),this.Da()&&this.Cf()):(this.D=!0,this.Da()&&p3(this)&&(2==a?h3(this.l):3==a&&j3(this.l)))};
g.h.qE=function(a){if(a){for(a=0;a<this.o.length;a++)g.zK(this.B,this.o[a].element,!0);this.uq(1)}else this.C=null,this.D=!1;this.Cf()};
g.h.tq=function(){return this.Da()&&p3(this)};g.r(t3,g.QO);g.h=t3.prototype;g.h.lw=function(){var a=this.player.getVideoData(),b=!!(a&&a.suggestions&&a.suggestions.length);b=!s3(this.player)||b;a=a.rh||g.Sy(a.Ma);var c=g.KR(this.player.app);return b&&!a&&!c};
g.h.kw=function(){return this.g.tq()};
g.h.MC=function(){return this.kw()?this.g.TC():!1};
g.h.V=function(){g.UP(this.player.app,"endscreen",void 0);g.QO.prototype.V.call(this)};
g.h.load=function(){g.QO.prototype.load.call(this);this.g.show()};
g.h.unload=function(){g.QO.prototype.unload.call(this);this.g.hide();this.g.destroy()};
g.h.AD=function(a){this.lw()&&(this.g.created||this.g.create(),"load"==a.getId()&&this.load())};
g.h.BD=function(a){"load"==a.getId()&&this.loaded&&this.unload()};
g.h.Ty=function(){g.UP(this.player.app,"endscreen",void 0);var a=this.player.getVideoData();a=new g.pD(Math.max(1E3*(a.lengthSeconds-10),0),0x8000000000000,{id:"preload",namespace:"endscreen"});g.H(this,a);var b=new g.pD(0x8000000000000,0x8000000000000,{id:"load",priority:6,namespace:"endscreen"});g.H(this,b);g.pK(this.player,[a,b])};g.dP.endscreen=t3;})(_yt_player);
