import Vue from 'vue'
import hljs from 'highlight.js';
import 'highlight.js/styles/googlecode.css' //样式文件
Vue.directive('highlight',function (el) {
    let blocks = el.querySelectorAll('pre code');
    setTimeout(() =>{
        blocks.forEach((block)=>{
            hljs.highlightBlock(block)
        })
    }, 200)
});
/*
addEventListener('load', () => {
    const code = document.querySelector('#code');
    const worker = new Worker('worker.js');
    worker.onmessage = (event) => { code.innerHTML = event.data; }
    worker.postMessage(code.textContent);
});*/
