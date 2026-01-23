// 变量
let a=5;
a="a";
a=true;

console.log(a);
//模板字符串
let name="小王";
let age=18;
console.log(`我的名字是${name},年龄是${age}`);

//常量
const pi=3.14159;
console.log(pi);

//函数
function add(a,b){
    return a+b;
}
let multiply=function(a,b){
    return a*b;
}
let sub=(a,b)=>a-b;

let x=3,y=4;

console.log(`${x}+${y}=${add(x,y)}`);
console.log(`${x}*${y}=${multiply(x,y)}`);
console.log(`${x}-${y}=${sub(x,y)}`);

//对象
let person={
    name:"小王",
    age:18,
    sex:"男",
    hobby:["football","basketball"],
    // show:function(){
    //     console.log(`我的名字是${this.name},年龄是${this.age},性别是${this.sex},爱好是${this.hobby}`);
    // }
    show(){
        console.log(`我的名字是${this.name},年龄是${this.age},性别是${this.sex},爱好是${this.hobby}`);
    }
}

person.show();

class Person{
    constructor(name,age,sex){
        this.name=name;
        this.age=age;
        this.sex=sex;
    }
    show(){
        console.log(`我的名字是${this.name},年龄是${this.age},性别是${this.sex}`);
    }
}

let p=new Person("小王",18,"男");
p.show();

let json=JSON.stringify(person);
console.log(json);

let personJson='{"name":"小王","age":20,"sex":"女"}';
let personObj=JSON.parse(personJson);
console.log(personObj);