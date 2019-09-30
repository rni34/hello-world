function log(){}function maxAlert(e){maxAlertCount>alertCount?alert(alertCount+": "+e):alertCount==maxAlertCount&&alert("MAX ALERTS REACHED: "+e),alertCount++}function iAmInGreaseMonkey(){try{return!(window instanceof Window)}catch(e){return!1}}function hasToggleSpanishAccentScriptElement(){for(var e=document.getElementsByTagName("script"),t=0;t<e.length;t++){var n=e[t]
if(n.src&&n.src.match(/[\/]toggle-spanish-accents.user.js$/))return!0}return!1}function makeToggleLookup(e){for(var t=[],n=0;n<e.length;n++){cycle=e[n]
for(var o=0;o<cycle.length;o++){var l=cycle[o],a=cycle[o<cycle.length-1?o+1:0]
t[l]=a}}return t}function nodeInserted(e){var t=e.target;("textarea"==t.nodeName||"input"==t.nodeName&&"text"==t.type)&&setupControl(t)}function setupDocument(e){try{e.onkeydown=spanishToggleKeyDownHandler}catch(t){e.addEventListener("keydown",spanishToggleKeyDownHandler,!1)}}function blurHandler(){elementWithFocus=null}function focusHandler(e){e||(e=window.event),e.target?elementWithFocus=e.target:e.srcElement&&(elementWithFocus=e.srcElement)}function setupControl(e){try{e.onfocus=focusHandler,e.onblur=blurHandler}catch(t){e.removeEventListener("focus",focusHandler,!1),e.removeEventListener("blur",focusHandler,!1),e.addEventListener("focus",focusHandler,!1),e.addEventListener("blur",blurHandler,!1)}}function spanishToggleKeyDownHandler(e,t){return e||(e=window.event),e.keyCode!=F2_code||e.altKey||e.ctrlKey?e.keyCode==F2_code&&e.altKey&&!e.ctrlKey?(setupInputFields(document),alert("Text input fields were reset for accent toggling"),!1):!0:(toggleSpanishAccent(this,t),!1)}function toggleAccentOnChar(e){var t=e.charCodeAt(0),n=toggleLookup[t]
return n?String.fromCharCode(n):e}function toggleSpanishAccent(e,t){var n=!1,o=!1,l=window
if(void 0!==t?(o=void 0!==t.editor,l=o?t.get("host"):t.getWin()):!elementWithFocus&&e.activeElement&&(elementWithFocus=e.activeElement),elementWithFocus)if(elementWithFocus.selectionStart&&elementWithFocus.selectionEnd){if(elementWithFocus.selectionStart==elementWithFocus.selectionEnd&&elementWithFocus.selectionStart>0){var a=elementWithFocus.selectionStart-1,c=elementWithFocus.value[a],i=toggleAccentOnChar(c)
i!=c&&(elementWithFocus.value=elementWithFocus.value.substring(0,a)+i+elementWithFocus.value.substring(a+1),elementWithFocus.selectionStart=a+1,elementWithFocus.selectionEnd=a+1)}}else n=!0
else if("function"==typeof l.getSelection&&l.getSelection()){var r=l.getSelection()
if(o){if(sel=r[0],sel.collapsed){var s=sel.commonAncestorContainer,a=sel.startOffset,u=s.nodeValue
if(a>0){s.nodeValue=u.substring(0,a-1)+toggleAccentOnChar(u[a-1])+u.substring(a)
var d=rangy.createRange()
d.collapseToPoint(s,a),rsel=rangy.getSelection(),rsel.removeAllRanges(),rsel.addRange(d)}}}else if(r.isCollapsed){var g=r.anchorNode,a=r.anchorOffset,m=g.nodeValue
if(a>0){g.nodeValue=m.substring(0,a-1)+toggleAccentOnChar(m[a-1])+m.substring(a)
var d=l.document.createRange()
d.setStart(g,a),d.setEnd(g,a),r.removeAllRanges(),r.addRange(d)}else log("at start of text")}}else n=!0
if(n&&l.document.selection){var d=l.document.selection.createRange()
if(d&&""==d.text&&(d.moveStart("character",-1),1==d.text.length)){var c=d.text,i=toggleAccentOnChar(c)
d.text=i,d.moveStart("character",1)}}}function setupSpanishTogglingOnAllTextFields(){var e=[[65,193],[97,225,170],[69,201],[101,233],[73,205],[105,237],[78,209],[110,241],[79,211],[111,243,186],[85,218,220],[117,250,252],[63,191],[33,161],[36,8364],[34,171,187],[39,8249,8250]]
toggleLookup=makeToggleLookup(e),setupDocument(document),setupInputFields(document)}function setupInputFields(e){for(var t=e.getElementsByTagName("textarea"),n=0;n<t.length;n++)setupControl(t[n])
for(var o=e.getElementsByTagName("input"),n=0;n<o.length;n++)"text"==o[n].type&&setupControl(o[n])
window.setTimeout("setupTinyMCEFields();",250)}function setupTinyMCEFields(){"undefined"!=typeof tinymce&&"undefined"!=typeof tinyMCE&&("function"==typeof tinymce.each&&tinymce.each(tinymce.EditorManager.editors,attachToTinyMCEField),tinyMCE.onAddEditor.add(attachToNewTinyMCEField))}function attachToNewTinyMCEField(e,t){attachToTinyMCEField(t)}function attachToTinyMCEField(e){try{e.onKeyDown.remove(tinyMCEHandleAccentToggle)}catch(t){}e.onKeyDown.add(tinyMCEHandleAccentToggle)}function tinyMCEHandleAccentToggle(e,t){spanishToggleKeyDownHandler(t,e)}var disableIfLoadedExplicitly=!1
logging=!1
var F2_code=113,toggleLookup,maxAlertCount=20,alertCount=1,elementWithFocus=null
disableIfLoadedExplicitly&&hasToggleSpanishAccentScriptElement()||setupSpanishTogglingOnAllTextFields()