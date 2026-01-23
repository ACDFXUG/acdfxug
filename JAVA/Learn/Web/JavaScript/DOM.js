import {LOG} from "./utils.js";

// let LOG=(...args)=>{
//     console.log(...args);
// }

let btn=document.querySelector("#btn");
let div=document.querySelector("#msg");
btn.addEventListener("click",()=>{
    div.innerHTML+="hello world!<br>";
    LOG("clicked");
});
btn.addEventListener("mouseenter",()=>{
    div.innerHTML+="mouse enter!<br>";
    LOG("mouse enter");
});

addEventListener("keydown",(keyE)=>{
    div.innerHTML+=`${keyE.key} is pressed!<br>`;
    LOG(`${keyE.key} is pressed`);
})