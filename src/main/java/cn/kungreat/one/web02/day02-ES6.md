# day01-ES6

### 一、ES6变量声明

#### 1、var的弊端

1.1、var声明的变量有预解析，造成 逻辑混乱，可以先使用，后声明

```js
          console.log(a);//undefined
          var a=10;
```

1.2.可以重复声明  不符合语法规范

```js
         var a=10;//这里是变量进行赋值
         var a=20;//这里是给变量进行值的修改
         console.log(a);//20
```

 1.3.var用在for循环条件中，造成for 循环的污染的问题

```js
        for(var i=0;i<6;i++){
            // console.log(i);//i变量本质上我需要1-5的数据
        }
        console.log(i);//此时i永远变成了6
```

 1.4.var 声明变量没有块级作用域  (作用域:全局作用域 函数作用域 )  函数内部使用var

```js
        {
             var a =20;
         }
         console.log(a);//20  全局作用域
```

#### 2、let关键字  声明变量

1.不可以先使用后声明  没有预解析

```js
          console.log(a);//Cannot access 'a' before initialization: 初始化前无法访问“a”
          let a=10;
```

2.不可以重复声明

```js
        let a=10;
        let a=19;
        console.log(a);//Identifier 'a' has already been declared :标识符“a”已声明
```

3.let用在for循环条件中，不会造成for 循环的污染的问题

```js
         for(let i=0;i<6;i++){
            // console.log(i);//0-5
        }
        console.log(i);//i is not defined:没有定义
```

 4.拥有块级作用域

 {} 就是一个块级作用域

```js
         {
            let a=10;
            console.log(a);//10
         }
         console.log(a);//a is not defined
```

案例--在for循环中使用var 和let

```js
    <button>1</button>
    <button>2</button>
    <button>3</button>
    <button>4</button>
    <button>5</button>
        <script>
        // 需求: 页面内有5个按钮   点击某个按钮输出对应对应的索引 
        // 发生的事件是什么   用到了哪些方法    牵扯到了哪些对象   发生的结果是什么

        var btns=document.getElementsByTagName("button");//数据集合(要使用for 循环进行事件绑定)
    /*     for (var i=0;i<btns.length;i++){
            // ES5如何解决的呢? 存标签属性
            // btns[i].setAttribute("myIndex",i)
            // 存对象属性
            btns[i].myIndex=i
            btns[i].onclick=function(){//事件是异步的
                // console.log(this.getAttribute("myIndex"));//对象.方法名   this 指向该对象
                console.log(this.myIndex);
            }
        } */

        for(let i=0;i<btns.length;i++){
            btn[i].onclick=function(){
                // {i=0 btns[0]  0}
                // {i=1 btns[1]  1}
                // {i=2 btns[2]  2}
                // {i=3 btns[3]  3}
                // {i=4 btns[4]  4}
                console.log(i);
            }
        }
    </script>
```

#### 3、const关键字特点:

1.一旦声明就必须赋值

```js
   //console.log(Data)//  Cannot access 'Data' before initialization:初始化前无法访问“数据”
   const Data=10
   console.log(Data);//10
```

2.不可以重复声明

```js
         const Data=10;
         const Data=20;
         console.log(Data);// Identifier 'Data' has already been declared:标识符“数据”已声明
```

3.不能修改值

```js
        const Data=20;
         Data=23
         console.log(Data);//Assignment to constant variable.:赋值给常数变量。
```

4.拥有块级作用域

```js
         {
             const Data=10;
             console.log(Data)//10
         }
        // console.log(Data);//Data is not defined;: Data未定义

```

5.引用类型的数据 数组和对象 引用数据类型  引用地址不可以改变  但是里面的值是可以修改的

```js
         const arr =[10,20,30]
        // arr =[4,5,6] 常量不可以再次赋值
        arr[0]=4
        arr[1]=5
        arr[2]=6
        console.log(arr);

```

### 二、模板字符串

ES5 解决字符串拼接的是+

```js
let name = '狗蛋',age = 12,gender = '男'
let str = "大家好，我叫" + name + "，今年" + age + "岁了，我是一个" + gender + "孩子"
```

ES6中拼接字符串`固定字符${变量或者表达式}`

```js
let name = '狗蛋',age = 12,gender = '男'
let str = `大家好，我叫${name}，今年${age}岁了，我是一个${gender}孩子`
```

### 三、解构语法

#### 3.1、对象解构的语法

```js
    <script>
        // 从数组或者对象中提取值,对变量进行赋值,这被称为解构
/*       
  以前的写法:
         var obj = {
            myname: "张三",
            age:20,
            city: "广州"
         }
        var myname = obj.myname;
        var age = obj.age;
        var city = obj.city;
        console.log(myname, age, city); 
*/

        let obj = {
            myname: "张三",
            age:20,
            city: "广州",
            fn:function(){
                console.log(11);
            }
        }
        // 条件 变量的名字要和对象的key值相同  这个时候 就要把对象中的key对应的value值赋值给对应的变量
        //完全结构
        // let {myname,age,city}=obj;
        // console.log(myname,age,city);

        // 部分解构  变量的重命名 语法 原来的变量名:要改变的变量名
        // let {myname:user}=obj;
        // console.log(user);

        // let {方法名:自己命名}=对象;语法
        // let {floor:fl}=Math;
        // console.log(fl(-12.5));

        let {fn:f1}=obj
        f1()
    </script>
```

#### 3.2、数组解构的语法

```js
  <script>
        // 思考:对象是否有序的?
        // let obj ={
        //     user:"张三",
        //     age:20,
        //     city:"广州"
        // }
        // console.log(obj);
        // 数组:一系列有序的数据集合
        // let arr =[1,2,3,4]
        // 完全解构
        // let [a,b,c,d]=arr
        // console.log(arr);
        // 部分解构
        // let [a] =arr
        // console.log(a);//此时的a代表索引0除的值
        // 解构数组中间的值   解构第三个值  需要用到,,占位
        // let [,,c]=arr;
        // console.log(c);

        // 复合解构
        // let arr=[1,2,3,[4,5,6],7]//二维数组
        // let [a,b,c,[d,e,f],g]=arr;
        // console.log(a,b,c,d,e,f,g);


    </script>
```

#### 3.3、字符串解构

```

        // 完全解构
        let str="eewrc"
        let [a,b,c,d,e]=str;
        console.log(a,b,c,d,e);
        // 部分解构
         let str="eewrc"
         let [a,b,c]=str;
         console.log(a,b,c);

```

交换两个变量的值

```

        // 交换两个变量的值
        // var a =10;b=20;
        // var c=b;
        // b=a;
        // a=c;
        // console.log(a,b);

        let a=10,b=20;
        [b,a]=[a,b]
        console.log(a,b);
```

### 四、对象的简化写法

```js
   <script>
        // 对象
        // var obj={
        //     user :"张三",
        //     age:20,
        // }

        let user="张三",age=20,city="广州";
        function fn(){
            console.log("ES6真香");
        }
        // var obj={
        //     user:user,
        //     age:age,
        //     city:city
        // }
        // 简化写法 如果对象的key和外面的变量名相同时,会将变量名对应的值给到对应key的value
        let obj={city,age,user,fn}
        // console.log(city,age,user);

        //将变量的值赋值给对象对应key的value
        console.log(obj);
        obj.fn()//语法规则:对象.方法名()
    </script>
```

### 五、函数参数默认值和参数解构

#### 5.1、函数形参的默认值

es5里面如果我们想要实现参数可以省略，我们有一个办法是这样做

```js
         function add(a,b,c){
             // 设置函数参数的默认值
             a=a||0;
             b=b||0;
             c=c||0;
             return a+b+c
         }
         console.log(add(1,2,3));//6
```

ES6

```
funciton 函数名(参数=默认值){ // 注意当 参数 为 undefined 时 参数 赋值为 默认值
  
}
```

```js
        function add(a=0,b=0,c=0){
            return a+b+c
        }
        console.log(add(1,2,3));
```

#### 5.2、函数参数的解构赋值

```js
    <script>
        // 相当于 let arr=[1,2,3]

        // function fn(arr){
        //     console.log(arr);

        // }
        // fn([1,2,3])



        // 相当于let {x,y}={x:5,y:10}

        // 对象的解构赋值
        obj={
            x:5,
            y:10
        }
        let {x,y}=obj;
        console.log(obj);

        // function fn({x,y}){
        //     console.log(x,y);

        // }
        // fn({x:5,y:10})


        // function fn({x:a,y:b}){
        //     console.log(a,b);

        // }
        // fn({x:5,y:10})
```

#### 5.3、解构赋值指定参数的默认值

```js
    <script>
        // $.ajax({
        //     url:"xx.xx.com",
        //     type:"GET",
        //     data:{},
        //     success(res){

        //     }

        // })

        // 对象的解构赋值语法
        // let {a,b,c}=obj;
        //将ajax的固定参数作为参数传递给ajaxFn

        /* 
        思路:
        函数内部接收的实际参数是一个对象,解构的时候需要用到{}语法进行解构,如果有人不传递参数,会报错
        相当于 :let {url,type,data,success}=undefied
        此时给实际参数={}解决报错问题,let {url,type,data,success}={},
        此时会出现undefined问题,给形式参数设置初始值(默认值)来解决
        {url="",type="GET",data={},success=function(){ }}={}


        注意点1:解决报错问题 let={url,type,data,success}={}报错问题的解决
        注意点2:undefined undefined undefined undefined  解决undefined问题给变量赋值(给初始值)
        {url="",type="GET",data={},success=function(){ }}={}解决了undefined 问题

        */
       function ajaxFn({url="",type="GET",data={},success=function(){ }}={}){
        // let {url,type,data,success}={},
           console.log(url,type,data,success);
       }

       ajaxFn({
            url:"xx.xx.com",
            type:"GET",
            data:{
                user:"123",
                pwd:"123"
            },
            success: function(){
                console.log(111);
            }

        })


    </script>
```

### 六、rest 参数和拓展运算符

#### 6.1、rest 参数/剩余参数

arguments 对象：

```js
function fn(){
    console.log(arguments);// 伪数组
}

fn(10, 20, 30, 50, 60);
```

ES6提供了新的方法

```js
       /* 
        1:...rest 参数得到的是一个真数组
        2:重点是...   不是rest   ...是语法  rest是形参(可以改变)
        3:接收剩余参数(返回一个数组)
        4:Rest参数必须是最后一个形式参数
        */


        function add(a,b,...c){// 把剩余的参数都交给rest
            // console.log(arguments);
            // 拥有数组的特征   索引和长度    但不能使用数组的方法
            // arguments.forEach(function(item){
            //     console.log(item);
            // })

                console.log(a,b,c);

        }
        add(1,2,3,4,5,6)

// 报错
function f(a, ...b, c) {
  // ...
}
```

#### 6.2、拓展运算符

```js
<script>
        // let arr=[1,2,3,4]
        // 循环遍历
        // arr.forEach(function(item){
        //     // console.log(item);
        // })
        // console.log(...arr);
        // console.log(Math.max(...arr));

        //展开对象
        // let obj={
        //     name:"张三",
        //     age:20
        // }
        // let obj1={
        //     city:"广州",
        //     email:"dhhdhf@163.com"
        // }
        // let newObj={...obj,...obj1};//对象合并不改变原对象的值
        // newObj.age=60;
        // console.log(newObj);


        //需求:定义两个数组  用两种方法给数组进行合并

        // 合并 对象的另一种写法  assign将一个对象后面的对象的值追加到了第一个对象内
        // 使用Object.assign()去合并对象时  如果不想改变原对象的内容 ,在()里第一个参数设置为{}
         let obj={
            name:"张三",
            age:20
        }
        let obj1={
            city:"广州",
            email:"dhhdhf@163.com"
        }
        let newObj=Object.assign({},obj,obj1)
        newObj.age=50;
        console.log(newObj);
        

    </script>
```

#### 6.3、...在解构赋值中的使用

```js
    <h3>123457</h3>
    <script>
        // ...充当的角色是获取剩余的参数
        //针对数组解构
        // let [a,b,c,...d]=[1,2,3,4,5,6,7,8,9]
        // console.log(a,b,c,d);// a = 1, b = 2, c = 3, arr = [4,5,6,7,8,9]

		//解构字符串
        // var [dom]=document.getElementsByTagName("h3")
        // console.log(dom);

        // let str="qwfrceg";
        // let [a,b,c,...d]=str
        // console.log(a,b,c,d);

        function add(...rest){
            var sum =0;
            rest.forEach(function(item){
                sum+=item
            })
            return sum;
        }
        console.log(add(1,2,3,4,5,6,7,8));


    </script>
```

# day02-ES6

### 一、箭头函数

#### 1.1、基本语法

ES6 允许使用 “箭头”（=>）简化函数的定义。

箭头函数本质也是函数，它出现的目的是为了让我们在使用回调函数的时候更简单

固定语法：

```
(形参)=>{函数体}
```

普通函数定义

```
let fn1 =function(){}
```

箭头函数定义 多用于处理回调函数

```
let fn2 =()=>{};
```

##### **箭头函数的注意点**

```
        1:无参无返回值的箭头函数,特点:{只有一行代码} 可以省略不写 
        2:无参有返回值的箭头函数,特点:{只有一行return代码} 可以省略{}和return 不写
        3:有参无返回值的箭头函数,特点:()只有一个参数可以省略()不写
        4:多参多行代码的箭头函数,不能简写(省略)
        5:创建一个返回值是一个对象的箭头函数{a:x,b:y},不能简写(省略)
        6:箭头函数不可以使用arguments   可以使用...rest参数
```

 1:无参无返回值的箭头函数,特点:{只有一行代码} 可以省略不写 

```js
        // let fn1=function(){
        //     console.log(111);
        // }
        //fn1()

		//简写
        // let fn1=()=>{console.log("fn1");}
        //fn1()
        
        //简写
        let fn1 = () => console.log("fn1");
        fn1()

```

2:无参有返回值的箭头函数,特点:{只有一行return代码} 可以省略{}和return 不写

```js
        //  let fn2=function(){
        //      return "fn2"
        //  }
        // console.log(fn2());
        
        //简写
        // let fn2=()=>{return "fn2";}
        
        //简写
        let fn2 = () => "fn2"
        console.log(fn2());
```

 3:有参无返回值的箭头函数,特点:()只有一个参数可以省略()不写

```js
        //  let fn3=function(a){
        //      console.log(a);
        //  }
        //  fn3("fn3")
        
        //简写
        // let fn3 =(a)=>{console.log(a);}
        
        //简写
        let fn3 = a => console.log(a);
        fn3("fn3")

```

 4:多参多行代码的箭头函数,不能简写(省略)

```js
      let fn4 = (a, b) => {
            let sum = a + b;//求参数的和
            return sum;//将参数的和返回
        }
        console.log(fn4(1, 3));
```

5:创建一个返回值是一个对象的箭头函数{a:x,b:y},不能简写(省略)

原因:对象本身是有{}函数也有{} 此时  函数会认为这个{}是函数的  对象会认为这个{}是对象本身

```js
        let fn5 = (x, y) => {
            return { a: x, b: y };
        }
        console.log(fn5(2, 3));
```

 6:箭头函数不可以使用arguments

```js
        let fn6 = (a,b) => {
            console.log(a, b);
            // console.log(arguments);
        }
        fn6(3, 3)
```

可以使用rest参数,将传的值全部打包处理

```js
  let fn7=(...rest)=>{
            console.log(...rest);
        }
        fn7(3, 3)
```

##### 箭头函数的使用场景

```js
  <script>
        // 1.之前使用的回调函数有哪些:(forEach setTimeout)
        // 在定时器中使用箭头函数
        setTimeout(()=>{
            console.log("这里使用了箭头函数");
        },2000)

        // 使用forEach遍历一个数组, 用箭头函数完成
        // let arr =[1,2,3,4,5,6];
        // arr.forEach((item)=>{
        //     console.log(item);
        // })

        // 下面是简写形式
        arr.forEach(item=>console.log(item))
    </script>
```

#### 1.2.this指向的问题

```
        1:全局打印this   指向window(没人这么用)
        2:全局调用方法打印this   指向window 
        3:对象的方法中打印this    this指向该对象
        4:对象中存一个属性值为this   此时this指向window
        5:定时器中使用的this指向window
        6:此时箭头函数中的this 指向了window (原因:箭头函数没有固定的this指向,指向外层作用域)
```

1:全局打印this  指向window(没人这么用)

```
 console.log(this);
```

2:全局调用方法打印this   指向window 

```
   function fn(){
       console.log(this);
    }
     // 全局调用方法打印this   指向window 
     fn()
```



```js
       // 定义一个对象  
        let user ="456";
        let obj={
            user:"123",
            pwd:"123",
            that:this,
            // 在对象中定义方法
            eat:function(){
                console.log("爱吃饭");
                console.log(this.user);
            },
            // 在对象中定义方法
            sleep:function(){
                console.log("爱睡觉");
                console.log(this);
            }

            
        }

        // 想要打印那两句话
        obj.eat();//这种形式的方法调用this指向该对象 (对象.方法()这种形式this都指向该对象)
        obj.sleep()//箭头函数中this 指向外层作用域,外层作用域刚好是window  所以指向了window
        // 定时器中的this指向  定时器的完整语法   window.setTimeout()  对象.方法()  this就指向该对象
        // console.log(obj.that);    

        // setTimeout(function(){
        //     console.log(this);//此时箭头函数中的this 指向了window 
        // },2000)
```

箭头函数中的this的案例

```js
 // h+c代码:
     <style>
        #box{
            width: 200px;
            height: 200px;
            background-color: #cfc;
        }
    </style>
     <div id="box"></div>
     
     //js代码:
      <script>
        // 需求:页面有一个固定大小的盒子,点击盒子,3秒后  宽度变大
        // 1.找对象
        let boxs=document.getElementById("box")
        // // 2.给找到的对象绑定点击事件
        // boxs.onclick=function(){
        //     // 把this 存起来
        //     let that =this;
        //     // 3秒后(需要用到定时器)定时器里面指向window 
        //     setTimeout(function(){//定时器修改了this指向
        //         that.style.width="300px"
        //     },2000)
        // }

         
        boxs.onclick=function(){
            // 3秒后(需要用到定时器)定时器里面指向window 
            setTimeout(()=>{
                this.style.width="300px"
            },2000)
        }
    </script>
```

### 二、Promise对象

#### 2.1、Promise简介

Promise 是异步编程的一种解决方案，比传统的解决方案——回调函数和事件——更合理和更强大。

**功能：避免了回调地狱，把异步代码改成调用起来像同步代码。**

所谓`Promise`，简单说就是一个容器，里面保存着某个未来才会结束的事件（通常是一个异步操作）的结果。

**一个 Promise 对象 有以下几种状态：**

- pending: 初始状态，既不是成功，也不是失败状态。
- fulfilled: 意味着操作成功完成。
- rejected: 意味着操作失败。

**`Promise`对象有以下两个特点：**

（1）对象的状态不受外界影响。`Promise`对象代表一个异步操作，有三种状态：`pending`（进行中）、`fulfilled`（已成功）和`rejected`（已失败）。只有异步操作的结果，可以决定当前是哪一种状态，任何其他操作都无法改变这个状态。这也是`Promise`这个名字的由来，它的英语意思就是“承诺”，表示其他手段无法改变。

（2）一旦状态改变，就不会再变，任何时候都可以得到这个结果。`Promise`对象的状态改变，只有两种可能：从`pending`变为`fulfilled`和从`pending`变为`rejected`。只要这两种情况发生，状态就凝固了，不会再变了，会一直保持这个结果，这时就称为 resolved（已定型）。如果改变已经发生了，你再对`Promise`对象添加回调函数，也会立即得到这个结果。这与事件（Event）完全不同，事件的特点是，如果你错过了它，再去监听，是得不到结果的。

> 小结：
>
> 1. Promise是一个构造函数，new出来实例对象可以帮我们解决异步操作的一些问题
> 2. 当我们遇到异步操作的时候，都可以使用Promise来加工，便于后期的代码书写

#### 2.2、Promise产生背景

我们如果在进行ajax请求的时候，一个效果需要有多个请求按照一定的顺序完成，如果不使用Promise实现，做起来就容易形成回调地狱

```js
       /* 
        // 需求:发送完第一条ajax收到响应之后发送第二条ajax收到响应之后发送第三条ajax
        回调地狱:弊端 : 不容易维护
        原理:发送ajax 请求时同步发送的,ajax的回调时异步,也就是说 数据有多大,请求时间就有多长
        导致响应时间长 输出不按顺序
        解决: 不使用Promise 可以解决此问题码?  可以的

        */
        //    $.ajax({
        //        url:" http://kumanxuan1.f3322.net:8809/index/banners/travel",
        //        success(res){
        //            console.log(111);
        //        }
        //    })
        //    $.ajax({
        //        url:" http://kumanxuan1.f3322.net:8809/strategies/themes",
        //        success(res){
        //            console.log(222);
        //        }
        //    })
        //    $.ajax({
        //        url:" http://kumanxuan1.f3322.net:8809/travels/query",
        //        success(res){
        //            console.log(333);
        //        }
        //    })




        $.ajax({
            url: " http://kumanxuan1.f3322.net:8809/index/banners/travel",
            success(res) {
                $.ajax({
                    url: " http://kumanxuan1.f3322.net:8809/strategies/themes",
                    success(res) {
                        $.ajax({
                            url: " http://kumanxuan1.f3322.net:8809/travels/query",
                            success(res) {
                                console.log(333);
                            }
                        })
                        console.log(222);
                    }
                })
                console.log(111);
            }
        })


    </script>
```

#### 2.3、Promise语法

 promise 是内置的对象   需要使用new 关键字进行修饰(实例化)

 使用Promise 解决异步代码同步执行

 Promise 接收一个函数的参数 参数有两个 resolve,reject

  resolve() 解决 ( 此方法是为了处理成功的回调)

  reject() 拒绝 ( 此方法是为了处理失败的回调)

  特点:状态一旦发生改变就会凝固,状态不会再受任何影响

```
         let p=new Promise(function(resolve,reject){
            resolve("ok");//解决 ( 此方法是为了处理成功的回调)
             // reject();//拒绝 ( 此方法是为了处理失败的回调)

        })
        console.log(p);
```

 状态1: pending (初始状态/默认状态) 等待 ,resolve();被调用状态有pending 改为fulfilled 表示成功

 状态2: pending (初始状态/默认状态) 等待 ,reject();被调用状态有pending 改为rejected 表示失败

```
           p.then((res)=>{//接受resolve()抛出的数据
              console.log(res);
            }).catch((err)=>{//接受reject()抛出的异常
             console.log(err);
            })
```

```js
      // 定义一个状态表示请求
        let flag = false;
        let p = new Promise(function (resolve, reject) {
            // 判断请求状态
            if (flag) {
                resolve("okokok")
            } else {
                reject("on")
            }

        });
        p.then((a)=>{//接受resolve()抛出的数据
            console.log(a);
        }).catch((e)=>{//接受reject()抛出的异常
            console.log(e);
        })
```

#### 2.4、使用Promise解决回调地狱

**Promise的then链式调用的特点：**
1、第一个then执行完会执行第二个then
2、then里面的函数的返回值，会被下一个then的形参接收
3、如果返回的是一个promise对象，下一个then的形参接收到的不是这个promise对象，而是这个promise对象内部调用resolve时候的实际参数

```js
        // 使用Promise解决回调地狱
        let p1=new Promise((resolve,reject)=>{
            // 发送第一个ajax 
            $.ajax({
                url:"http://kumanxuan1.f3322.net:8809/index/banners/travel",
                type:"GET",
                data:{},
                success(res){
                    // 调用resolve将数据抛出
                    resolve(111)
                },
                error(err){
                    // 调用reject将数据抛出
                    reject(err)
                }
            })
        })
        let p2=new Promise((resolve,reject)=>{
            // 发送第一个ajax 
            $.ajax({
                url:"http://kumanxuan1.f3322.net:8809/strategies/themes",
                type:"GET",
                data:{},
                success(res1){
                    // 调用resolve将数据抛出
                    resolve(222)
                },
                error(err){
                    // 调用reject将数据抛出
                    reject(err)
                }
            })
        })
        let p3=new Promise((resolve,reject)=>{
            // 发送第一个ajax 
            $.ajax({
                url:"http://kumanxuan1.f3322.net:8809/travels/query",
                type:"GET",
                data:{},
                success(res3){
                    // 调用resolve将数据抛出
                    resolve(333)
                },
                error(err){
                    // 调用reject将数据抛出
                    reject(err)
                }
            })
        })

        // 使用链式编程解决问题
        p1.then((res)=>{//处理回调函数在这里进行就可以
            console.log(res);
            return p2
        }).then((res1)=>{
            console.log(res1);
            return p3
        }).then((res2)=>{
            console.log(res2);
           
        })

        // p1.then((res)=>{
        //     console.log(res);//返回成功的回调
        // }).catch((err)=>{
        //     console.log(err);//捕获异常
        // })

        // 无法解决回调地狱的问题
      /*   p1.then((res)=>{
            console.log(res);
            return p2;
        })
        p2.then((res1)=>{
            console.log(res1);
            return p3;
        })
        p3.then((res2)=>{
            console.log(res2);
           
        }) */
    </script>
```

2.4.2、简化多重请求的promise写法

```js
        function getPromiseAjax(url) {
            // console.log(url);
            let p = new Promise((resolve, reject) => {
                // 发送第一个ajax 
                $.ajax({
                    url: url,
                    type: "GET",
                    data:{},
                    success(res) {
                        // 调用resolve将数据抛出
                        resolve(url)
                    }
                    // error(e){
                    //     reject(e)
                    // }
                })
            })
            return p;
        }

        let p1=getPromiseAjax("http://kumanxuan1.f3322.net:8809/index/banners/travel",type="GET")
        let p2=getPromiseAjax("http://kumanxuan1.f3322.net:8809/strategies/themes",type="GET")
        let p3=getPromiseAjax("http://kumanxuan1.f3322.net:8809/travels/query",type="GET")


    // 使用链式编程解决问题
        p1.then((res)=>{//处理回调函数在这里进行就可以
            console.log(res);
            return p2;
        }).then((res1)=>{
            console.log(res1);
            return p3;
        }).then((res2)=>{
            console.log(res2);
        })
```

#### 2.5、Promise的方法使用

Promise 的方法**all** 的使用

all 方法指的是:所有的Promise 都执行resolve 方法 打包返回(数组形式)

假如 有异常抛出,会返回异常 ,假如有多个异常 ,会返回最快的那一个

```js
        let p1 = new Promise(function (resolve, reject) {
            setTimeout(() => {
                reject("111");
            }, 3000)
        })
        let p2 = new Promise(function (resolve, reject) {
            setTimeout(() => {
                reject("222");
            }, 4000)
        })
        let p3 = new Promise(function (resolve, reject) {
            setTimeout(() => {
                resolve("333");
            }, 1000)
        })
        
        let p = Promise.all([p1, p2, p3]).then((res) => {
           console.log(res);
         }).catch((err) => {
             console.log(err);
        })
```

Promise 的方法 **race**的使用

race的方法指的是:无论是resolve还是reject 谁快返回谁

```js
        let p1 = new Promise(function (resolve, reject) {
            setTimeout(() => {
                reject("111");
            }, 3000)
        })
        let p2 = new Promise(function (resolve, reject) {
            setTimeout(() => {
                reject("222");
            }, 4000)
        })
        let p3 = new Promise(function (resolve, reject) {
            setTimeout(() => {
                resolve("333");
            }, 1000)
        })
        
       let p = Promise.race([p1, p2, p3]).then((res) => {
             console.log(res);
         }).catch((err) => {
            console.log(err);
         })
```

2.6、异步代码同步化

**async函数和await关键字**一般成对出现，当函数执行的时候，一旦遇到`await`就会先返回，等到异步操作完成，再接着执行函数体内后面的语句。

```js
        let p1 = new Promise(function (resolve, reject) {
            setTimeout(() => {
                resolve("111");
            }, 3000)
        })
        let p2 = new Promise(function (resolve, reject) {
            setTimeout(() => {
                resolve("222");
            }, 2000)
        })
        let p3 = new Promise(function (resolve, reject) {
            setTimeout(() => {
                resolve("333");
            }, 1000)
        })

        // async修饰的函数都是异步的   await 一旦遇见await后面的代码一律不准执行,等前面的代码执行完毕

        async  function getList() {
            let res1 = await p1;
            console.log(res1);
            let res2 = await p2;
            console.log(res2);
            let res3 = await p3;
            console.log(res3);
        }
        getList();

```

