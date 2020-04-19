(function(g){var window=this;var w4=function(a,b){var c="ytp-miniplayer-button-bottom-right";var d=g.W?{A:"div",U:["ytp-icon","ytp-icon-expand-watch-page"]}:{A:"svg",K:{height:"18px",version:"1.1",viewBox:"0 0 22 18",width:"22px"},H:[{A:"g",K:{fill:"none","fill-rule":"evenodd",stroke:"none","stroke-width":"1"},H:[{A:"g",K:{transform:"translate(-1.000000, -3.000000)"},H:[{A:"polygon",K:{points:"0 0 24 0 24 24 0 24"}},{A:"path",K:{d:"M19,7 L5,7 L5,17 L19,17 L19,7 Z M23,19 L23,4.98 C23,3.88 22.1,3 21,3 L3,3 C1.9,3 1,3.88 1,4.98 L1,19 C1,20.1 1.9,21 3,21 L21,21 C22.1,21 23,20.1 23,19 Z M21,19.02 L3,19.02 L3,4.97 L21,4.97 L21,19.02 Z",
fill:"#fff","fill-rule":"nonzero"}}]}]}]};var e="\u52d5\u753b\u30da\u30fc\u30b8\u3092\u958b\u304f";g.P(a.L().experiments,"kevlar_miniplayer_expand_top")&&(c="ytp-miniplayer-button-top-left",d=g.W?{A:"div",U:["ytp-icon","ytp-icon-expand-miniplayer"]}:{A:"svg",K:{height:"24px",version:"1.1",viewBox:"0 0 24 24",width:"24px"},H:[{A:"g",K:{fill:"none","fill-rule":"evenodd",stroke:"none","stroke-width":"1"},H:[{A:"g",K:{transform:"translate(12.000000, 12.000000) scale(-1, 1) translate(-12.000000, -12.000000) "},
H:[{A:"path",K:{d:"M19,19 L5,19 L5,5 L12,5 L12,3 L5,3 C3.89,3 3,3.9 3,5 L3,19 C3,20.1 3.89,21 5,21 L19,21 C20.1,21 21,20.1 21,19 L21,12 L19,12 L19,19 Z M14,3 L14,5 L17.59,5 L7.76,14.83 L9.17,16.24 L19,6.41 L19,10 L21,10 L21,3 L14,3 Z",fill:"#fff","fill-rule":"nonzero"}}]}]}]},e="\u62e1\u5927");g.T.call(this,{A:"button",U:["ytp-miniplayer-expand-watch-page-button","ytp-button",c],K:{title:"{{title}}","data-tooltip-target-id":"ytp-miniplayer-expand-watch-page-button"},H:[d]});this.l=a;this.ea("click",
this.o,this);this.updateValue("title",g.XL(a,e,"i"));g.zi(this,g.sM(b.Ta(),this.element))},x4=function(a){g.tu.call(this,{A:"div",
F:"ytp-miniplayer-ui"});this.player=a;this.G=!1;this.D=this.w=this.l=void 0;this.J(a,"minimized",this.AE);this.J(a,"onStateChange",this.sL)},y4=function(a){g.QO.call(this,a);
this.g=new x4(this.player);this.g.hide();g.tK(this.player,this.g.element,4);a.app.D.g&&(this.load(),g.L(a.getRootNode(),"ytp-player-minimized",!0))};
g.r(w4,g.T);w4.prototype.o=function(){this.l.ha("onExpandMiniplayer")};g.r(x4,g.tu);g.h=x4.prototype;
g.h.show=function(){this.l=new g.Ym(this.zE,null,this);this.l.start();if(!this.G){this.B=new g.hQ(this.player,this);g.H(this,this.B);g.tK(this.player,this.B.element,4);this.B.w=.6;this.P=new g.iP(this.player);g.H(this,this.P);this.o=new g.T({A:"div",F:"ytp-miniplayer-scrim"});g.H(this,this.o);this.o.g(this.element);this.J(this.o.element,"click",this.by);var a=new g.T({A:"button",U:["ytp-miniplayer-close-button","ytp-button"],K:{"aria-label":"\u9589\u3058\u308b"},H:[g.nL()]});g.H(this,a);a.g(this.o.element);
this.J(a.element,"click",this.yw);a=new w4(this.player,this);g.H(this,a);a.g(this.o.element);this.u=new g.T({A:"div",F:"ytp-miniplayer-controls"});g.H(this,this.u);this.u.g(this.o.element);this.J(this.u.element,"click",this.by);var b=new g.T({A:"div",F:"ytp-miniplayer-button-container"});g.H(this,b);b.g(this.u.element);a=new g.T({A:"div",F:"ytp-miniplayer-play-button-container"});g.H(this,a);a.g(this.u.element);var c=new g.T({A:"div",F:"ytp-miniplayer-button-container"});g.H(this,c);c.g(this.u.element);
this.M=new g.ZM(this.player,this,!1);g.H(this,this.M);this.M.g(b.element);b=new g.WM(this.player,this);g.H(this,b);b.g(a.element);this.I=new g.ZM(this.player,this,!0);g.H(this,this.I);this.I.g(c.element);this.D=new g.wO(this.player,this);g.H(this,this.D);this.D.g(this.o.element);this.w=new g.hN(this.player,this);g.H(this,this.w);g.tK(this.player,this.w.element,4);this.C=new g.T({A:"div",F:"ytp-miniplayer-buttons"});g.H(this,this.C);g.tK(this.player,this.C.element,4);a=new g.T({A:"button",U:["ytp-miniplayer-close-button",
"ytp-button"],K:{"aria-label":"\u9589\u3058\u308b"},H:[g.nL()]});g.H(this,a);a.g(this.C.element);this.J(a.element,"click",this.yw);a=new g.T({A:"button",U:["ytp-miniplayer-replay-button","ytp-button"],K:{"aria-label":"\u9589\u3058\u308b"},H:[g.AL()]});g.H(this,a);a.g(this.C.element);this.J(a.element,"click",this.mJ);this.J(this.player,"presentingplayerstatechange",this.BE);this.J(this.player,"appresize",this.Na);this.J(this.player,"fullscreentoggled",this.Na);this.Na();this.G=!0}0!=this.player.getPlayerState()&&
g.tu.prototype.show.call(this);this.w.show();this.player.unloadModule("annotations_module")};
g.h.hide=function(){this.l&&(this.l.dispose(),this.l=void 0);g.tu.prototype.hide.call(this);this.player.app.D.g||(this.G&&this.w.hide(),this.player.loadModule("annotations_module"))};
g.h.V=function(){this.l&&(this.l.dispose(),this.l=void 0);g.tu.prototype.V.call(this)};
g.h.yw=function(){this.player.stopVideo();this.player.ha("onCloseMiniplayer")};
g.h.mJ=function(){this.player.playVideo()};
g.h.by=function(a){if(a.target==this.o.element||a.target==this.u.element)g.P(this.player.L().experiments,"kevlar_miniplayer_play_pause_on_scrim")?g.Ct(g.bK(this.player))?this.player.pauseVideo():this.player.playVideo():this.player.ha("onExpandMiniplayer")};
g.h.AE=function(){g.L(this.player.getRootNode(),"ytp-player-minimized",this.player.app.D.g)};
g.h.zE=function(){g.jN(this.w);this.D.l();this.l&&this.l.start()};
g.h.BE=function(a){g.R(a.state,32)&&this.B.hide()};
g.h.Na=function(){var a=this.w,b=g.cK(this.player).getPlayerSize().width;a.Aa=0;a.w=b;a.C=!1;g.lN(a)};
g.h.sL=function(a){this.player.app.D.g&&(0==a?this.hide():this.show())};
g.h.Ta=function(){return this.B};
g.h.Vb=function(){return!1};
g.h.gg=function(){return!1};
g.h.Sh=function(){return!1};
g.h.Hr=function(){};
g.h.Gg=function(){};
g.h.bj=function(){};
g.h.Jm=function(){return null};
g.h.xq=function(){return new g.Uf(0,0,0,0)};
g.h.handleGlobalKeyDown=function(){return!1};
g.h.handleGlobalKeyUp=function(){return!1};
g.h.ki=function(a,b,c,d,e){var f=0,k=d=0,l=g.qg(a);if(b){c=g.hn(b,"ytp-prev-button")||g.hn(b,"ytp-next-button");var m=g.hn(b,"ytp-play-button"),n=g.hn(b,"ytp-miniplayer-expand-watch-page-button");c?f=k=12:m?(b=g.ng(b,this.element),k=b.x,f=b.y-12):n&&(k=g.hn(b,"ytp-miniplayer-button-top-left"),f=g.ng(b,this.element),b=g.qg(b),k?(k=8,f=f.y+40):(k=f.x-l.width+b.width,f=f.y-20))}else k=c-l.width/2,d=25+(e||0);b=g.cK(this.player).getPlayerSize().width;e=f+(e||0);l=g.pd(k,0,b-l.width);e?(a.style.top=e+
"px",a.style.bottom=""):(a.style.top="",a.style.bottom=d+"px");a.style.left=l+"px"};
g.h.showControls=function(){};
g.h.yq=function(){};
g.h.Bh=function(){};g.r(y4,g.QO);y4.prototype.create=function(){};
y4.prototype.Ne=function(){return!1};
y4.prototype.load=function(){this.player.hideControls();this.g.show()};
y4.prototype.unload=function(){this.player.showControls();this.g.hide()};g.dP.miniplayer=y4;})(_yt_player);
