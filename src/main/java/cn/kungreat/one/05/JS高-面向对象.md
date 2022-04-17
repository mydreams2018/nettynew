## 面向对象三大特性（了解）

### 1、封装

定义：使用对象封装一些变量和函数

作用：复用和信息隐藏

详解：封装，也就是把客观事物封装成抽象的类，并且类可以把自己的数据和方法只让可信的类或者对象操作，对不可信的进行信息隐藏。

我们把"属性"（property）和"方法"（method），封装成一个对象，甚至从原型对象生成一个实例对象，这个过程就是封装。

------拓展: 封装一个案例------tab栏------功能封装

封装tab栏的代码：

```html
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Tab栏</title>
  <style type="text/css">
    div,
    span,
    ul,
    li,
    h2,
    body {
      padding: 0;
      margin: 0;
      list-style: none;
    }

    .all {
      width: 440px;
      border: 1px solid #000;
      padding: 10px;
      margin: 80px auto;
    }

    .all h2 {
      height: 30px;
      text-align: center;
      display: flex;
      justify-content: space-between;
    }

    .all h2 span {
      width: 100px;
      height: 30px;
      background: #CCC;
      text-align: center;
      cursor: pointer;
    }

    .all h2 span.current {
      background: #FF3366;
    }

    .all ul li {
      display: none;
      background: #FF3366;
      height: 200px;
    }

    .all ul li.current {
      display: block
    }
  </style>
  </head>

  <body>
    <div id="tab1" class="all">
      <h2>
        <span class="current">新闻</span>
        <span>娱乐</span>
        <span>游戏</span>
        <span>财经</span>
      </h2>
      <ul>
        <li class="current">新闻</li>
        <li>娱乐</li>
        <li>游戏</li>
        <li>财经</li>
      </ul>
    </div>


    <div id="tab2" class="all">
      <h2>
        <span class="current">欧阳三</span>
        <span>南宫四</span>
        <span>西门五</span>
        <span>令狐六</span>
      </h2>
      <ul>
        <li class="current">欧阳三</li>
        <li>南宫四</li>
        <li>西门五</li>
        <li>令狐六</li>
      </ul>
    </div>
    
    <script>
      // 需求: 面向对象的 tab栏 功能封装 
      /*
            思路: 只需要一个Tab类 下次遇到一样的结构  直接创建一个 tab的实例就可以实现tab栏功能
        */
    </script>
</body>

</html>
```

```js
// 原生js
var box = document.getElementById("tab1");
var btns = box.getElementsByTagName("span");
var pages = box.getElementsByTagName("li");

for (var i = 0; i < btns.length; i++) {
  btns[i].index = i
  btns[i].onclick = function () {
    for (var j = 0; j < btns.length; j++) {
      btns[j].className = ''
      pages[j].className = ''
    }
    this.className = 'current'
    pages[this.index].className = 'current'
  }
}
```

```js
// 第一次封装
var instance = null
function Tab(id) {
  this.box = document.getElementById(id);
  this.btns = this.box.getElementsByTagName("span");
  this.pages = this.box.getElementsByTagName("li");
  instance = this
}
Tab.prototype.clearFn = function () {
  for (var j = 0; j < this.btns.length; j++) {
    this.btns[j].className = ''
    this.pages[j].className = ''
  }
}
Tab.prototype.clickFn = function () {
  instance.clearFn()
  this.className = 'current'
  instance.pages[this.index].className = 'current'
}
Tab.prototype.initFn = function () {
  for (var i = 0; i < this.btns.length; i++) {
    this.btns[i].index = i
    this.btns[i].onclick = this.clickFn
  }
}

var t1 = new Tab('tab1')
t1.initFn()
```

```js
// 最终封装
// var instance = null 删除
function Tab(id) {
  this.box = document.getElementById(id);
  this.btns = this.box.getElementsByTagName("span");
  this.pages = this.box.getElementsByTagName("li");
  // instance = this 删除
}
Tab.prototype.clearFn = function () {
  for (var j = 0; j < this.btns.length; j++) {
    this.btns[j].className = ''
    this.pages[j].className = ''
  }
}
Tab.prototype.clickFn = function () {
  // console.log(instance)
  this.instance.clearFn() // 修改
  this.className = 'current'
  this.instance.pages[this.index].className = 'current' // 修改
}
Tab.prototype.initFn = function () {
  for (var i = 0; i < this.btns.length; i++) {
    this.btns[i].index = i
    this.btns[i].instance = this // 添加
    this.btns[i].onclick = this.clickFn
  }
}

var t1 = new Tab('tab1')
var t2 = new Tab('tab2')
t1.initFn()
t2.initFn()
```



### 2、继承

定义：一个类获取另外一个类属性和方法的一种方式
作用：代码复用
详解：继承，就是可以使用已创建好的类的所有功能，并在无需重新编写原来的类的情况下对这些功能进行扩展。

- 通过继承创建的新类称为“**子类**”或“派生类”。
- 被继承的类称为“基类”、“**父类**”或“超类”。
- 继承的过程，就是从一般到特殊的过程。

### 3、多态

定义：同一个操作，作用于不用的对象，会有不同的行为
作用：具有可拓展性
详解：多态首先是建立在继承的基础上的，先有继承才能有多态。多态是指不同的子类在继承父类后分别都重写覆盖了父类的方法，即父类同一个方法，在继承的子类中表现出不同的形式。js天生就具备多态的特性(弱类型语言)

1. 弱类型    没有严格的类型区分      var a = 10;  a="10";
2. 强类型   Java    TypeScript(JavaScript的超集)    类型转换有严格的规定, 不能随意改变类型

## 二、继承     

ES5是没有继承语法的，只能通过方法模拟继承语法，所以有多种方式达到继承效果。
继承目的：通过相应的代码将父类中的成员复制到子类对象中

- [x] 混入式继承
- [x] 原型式继承
- [x] 原型链继承
- [x] 借用构造函数继承
- [x] 组合继承

### 4.1、混入式继承（了解）

实现原理：**将父类成员拷贝到子对象中（浅拷贝）。**

实现方法：`for…in…循环遍历父类，子类[key]=父类[key]`

缺点：共享数据安全问题，修改子类，会影响父类，引用数据类型浅拷贝，会修改引用地址

```js
//混入式继承（拷贝继承）
//obj2继承到obj1中的成员，可以直接将obj1中的成员拷贝到obj2中即可
var obj1 = {name:"zs",age:10};
var obj2 = {};
// 将obj1中的成员拷贝到obj2中
for (var key in obj1) {
    obj2[key] = obj1[key];
}
console.log(obj1);
console.log(obj2);
```

共享数据安全的问题：

```js
var obj1 = {name:"zs",age:10,car:{name:"mini"}};
var obj2 = {};
// 将obj1中的成员拷贝到obj2中
for (var key in obj1) {
    obj2[key] = obj1[key];
}
//修改obj1对象中的car属性
obj1.car.name = "bus";

console.log(obj1);//{name:"zs",age:10,car:{name:"bus"}}
console.log(obj2);//{name:"zs",age:10,car:{name:"bus"}}
```

当我们需要修改其中某一个对象中的引用类型属性时，会造成其他相关的对象也被修改，原因在于大家引用的是同一个内存区域中的数据。

### 4.2、原型式继承（了解）

实现原理：**子类的原型对象指向父类的原型对象**

实现方式：`子类.prototype = 父类.prototype`

缺点：造成了原型结构的混乱，没有严格的父子类关系，并且只能继承原型成员，不能继承实例成员。

```js
// 父类
function Person(name) {
  this.name = name
}
Person.prototype.say = function () {
  console.log(this.name)
}

// 子类
function Student(name) {
  this.name = name
}
Student.prototype = Person.prototype // 子类的原型对象指向父类的原型对象
var s = new Student('Mike')
```

画图分析：

![](images/原型式继承.png)

### 4.3、原型链继承（掌握）

实现原理：**将子类的原型对象指向父类的实例对象。**

实现方法：`子类.prototype = new 父类()`

```javascript
// 实现原理: 子类的原型对象指向父类实例对象
// 弊端: 不能继承实例成员,只能继承原型成员

// 父类 
function Person(name, age) {
  // 实例成员
  this.name = name;
  this.age = age;
}
Person.prototype.eat = function () {
  console.log(this.name + "爱吃木桶饭");
}

// 子类 
function Student(name, age, score) {
  // 没有继承实例成员,只是重写了一次
  this.name = name;
  this.age = age;
  this.score = score;
}

// 原型链继承 
Student.prototype = new Person();
Student.prototype.constructor = Student // 需要将Student.prototype.constructor手动指向Student，不然指向的是Person
Student.prototype.getScore = function () {
  console.log(this.name + "的成绩是:" + this.score);
}

// 生成一个子类的实例
var s = new Student("欧阳三", 20, 120);

s.eat();
s.getScore();


console.log(s.constructor);
```



![](images/原型链继承.png)

### 4.4、借用构造函数继承（掌握）

#### 4.4.1、call方法和apply方法的基本使用（十分重要）

定义：将方法**借给**某个对象的方法。call和apply作用相同，写法不同。

使用call方法的语法：

```javascript
被借用对象.方法.call(借用对象)
```

使用apply方法的语法：

```javascript
被借用对象.方法.apply(借用对象)
```

 **特点：可以设置方法中this的指向——方法中的this指向借用对象**

```js
var obj1 = {
    name:"Neld",
    age:10,
    showInfo:function () {
        console.log("name:"+this.name,"age:"+this.age);
    }
}
var obj2 = {
    name:"Lily",
    age:12
}
obj1.showInfo();//name:Neld age:10
obj2.showInfo();//obj2.showInfo is not a function

obj1.showInfo.call(obj2);//name:Lily age:12
// obj1.showInfo.apply(obj2);//name:Lily age:12
```

**call和apply的区别（传参方式不同）**： 

```javascript
// call传参，跟在借用对象后面，用逗号隔开
被借用对象.方法.call(借用对象,参数1,参数2……)
// apply传参，不能直接写在后面，要将参数封装在数组中跟在借用对象后面，用逗号隔开
被借用对象.方法.apply(借用对象,[ 参数1,参数2…… ])
```

需求：给obj1添加add方法，需要传入两个参数，完成加法运算。然后将这个方法借给obj2对象，通过obj2传入参数，完成加法运算

```js
var obj1 = {
    name:"Neld",
    age:10,
    add : function (a, b) {
        return a + b;
    }
}
var obj2 = {
    name:"Lily",
    age:12
}
//使用cal方法
obj1.add.call(obj2, 100, 200);
//使用apply方法
//console.log(obj1.add.apply(obj2, 1, 2));//CreateListFromArrayLike called on non-object
obj1.add.apply(obj2, [100, 200]);
```

#### 4.4.2、借用构造函数继承说明

实现原理：在子构造函数中调用父构造函数，达到继承并向父构造函数传参的目的。

实现方法：

1. 将父对象的构造函数设置为子对象的成员
2. 调用这个方法，类似于将父构造函数中的代码复制到子构造函数中来执行
3. 用完之后删掉

```js
借用构造函数继承--------继承实例成员

// 之前的原型链继承 几乎完美了, 但是不能继承实例成员 

// 实现原理: 父类构造函数在子类中运行
// 弊端: 无法继承原型成员

// 父类 
function Person(name, age) {
  this.name = name;
  this.age = age;
}

Person.prototype.eat = function () {
  console.log("吃吃吃");
}

function Student(name, age, score) {
  /* this.name = name;
            this.age = age; */

  /* // 父类构造函数在子类中运行, 达到了代码复用的目的
            Person(name, age);// 此时, 父类中的this指向了window */

  /* // 1. 将父类 赋值给 子类的实例成员
            this.myFn = Person;
            // 2. 调用这个实例方法;--------将父类内部的this指向子类的实例
            this.myFn(name, age);
            // 3. 用完之后 删除
            delete this.myFn;
            */

  // 上述代码 将 Person 借给了 this
  // Person.call(this, name, age);
  Person.apply(this, [name, age]);

  this.score = score;
}


var s = new Student("欧阳三", 20, 120);

console.log(s);

// s.eat(); // 报错就对了
```

**高级实现方法：凡是要借用方法，首先想到使用call或apply**

```js
function Student(name,age,score) {
    //Person.call(this,name,age);
    Person.apply(this,[name,age]);
    this.score = score;
}
```

这种继承方式都存在下面两个问题：

1. 如果父子构造函数存在相同的成员，那么子构造函数会覆盖父构造函数中的成员
2. 不能继承原型链中的成员

### 4.5、组合继承（必须掌握）

实现原理：基原型链继承+借用构造函数继承

```js
function Student(name,age,score) {
   //Person.call(this,name,age);
   Person.apply(this,[name,age]);//继承构造函数中的成员
   this.score = score;
}

Student.prototype = new Person();//继承原型链上的成员
Student.prototype.constructor = Student
```

缺点：子类的原型对象上有无用属性

### 4.6、寄生组合继承（拓展）

实现原理：组合继承的基础上，改造原本的原型链继承。子类和父类中间创建一个空类，过滤掉无用的父类实例属性。

```js
// 寄生式组合继承(拓展)
// 实现原理：使用寄生式继承优化原型链继承 + 借用构造函数继承
// 父类
function Person(name, age) {
  this.name = name;
  this.age = age;
}

Person.prototype.eat = function () {
  console.log(this.name + "吃饭");
}

// 子类 
function Student(name, age, score) {
  // 借用构造函数继承
  Person.call(this, name, age);
  this.score = score;
}

/* // 原型链继承-------脏数据来源
        Student.prototype = new Person();
        Student.prototype.constructor = Student; */

// 寄生式继承
// 思路： 在父类和子类中创建一个没有实例成员的空类，使用空类的实例 赋值给子类的原型------ 可以过滤脏数据

// 5. 用完之后销毁掉 ----- 立即执行函数
// 函数的局部变量,在函数执行完毕之后,就被销毁了 
// 想要再次拿到,只能再次调用,意味着重新创建了一次,又销毁了------------生命周期(创建到销毁的过程)
(function () {
  // 1. 创建一个空类
  var Super = function () { };
  // 2. 空类的原型指向父类的原型
  Super.prototype = Person.prototype;
  // 3. 空类的实例 赋值给子类的原型
  Student.prototype = new Super();
  // 4. 将constructor属性指回去
  Student.prototype.constructor = Student;
})()


Student.prototype.getScore = function () {
  console.log(this.name + "的分数是:" + this.score);
}


var s = new Student("欧阳三", 20, 120);

console.log(s);
s.eat();
```



![](images/寄生式组合继承_.png)



总结：  ES5实现继承的方式不止一种。这是因为ES5 中的继承机制并不是明确规定的，而是通过模仿实现的。这意味着所有的继承细节并非完全由解释程序处理。作为开发者，你有权决定最适用的继承方式。

## 五、绘制完整的原型链结构图（掌握）

这一节重点探讨函数对象的原型链结构。我们依赖一段代码来画：

```

```

完整的结构图如下：

![img](.\images\15464464848.png)

