## 一、对象的复习

### 1.1、万物皆对象

大家之前都使用过对象，但是有没有想过为什么要用对象？

保存一个值时，可以使用**`变量`**。保存多个值（一组值）时，可以使用**`数组`**。如果要保存一个人的完整信息呢？例如，将张三的个人信息保存到一个数组中：

```javascript
const userInfo = ['张三',20,160] // 你不能确定20,160分别代表什么
```

JS中的对象表达解构更清晰、更强大。张三的个人信息可以用对象来表示。

我们以前学过用字面量创建对象：直接使用字面量方式创建对象比较方便，以键值对的格式来定义数据

1.字面量创建对象：直接使用字面量方式创建对象比较方便，以键值对的格式来定义数据

```javascript
const userInfo = {
  name: '张三',
  age: 20,
  height: 160
}
```

在JavaScript中，对象是花括号{}包裹的一组无序的键值对，是一组无序的相关属性和方法的集合。

对象是由**`属性`**和**`方法`**组成的。

- 事物的**`特征`**，在对象中用**`属性`**来表示
- 事物的**`行为`**，在对象中用**`方法`**来表示

优点：方便直观，可以直接访问里面的属性方法；
缺点：创建大量相同或相似对象时，会出现代码重复，只适合创建单个对象



说完了对象的优点后，我们说说**类**和**对象**的**区别**：

现实生活中，万物皆**对象**，生活中的每一个**具体的事物**都成为对象，例如一个人，一本书，一个电脑。而**类**是泛指**一类事物**。

| 类     | 对象           |
| ------ | -------------- |
| 女朋友 | 迪丽热巴       |
| 班主任 | 我们班的班主任 |
| 游戏   | 英雄联盟       |

### 1.2、对象的成员

书写对象的语法：

```javascript
// 格式：{ 属性: 变量, 方法: 匿名函数 }

{
	key1:value1，
	key2:value2
}
```

完成一个需求，对某人这个对象用代码表示：

```javascript
var person = {
    name : "阳仔",
    age : 10,
    gender : 1,
    eat : function () {
        console.log("吃饭");
    },
    sleep : function () {
        console.log("睡觉");
    }
};
console.log(person.name);//访问对象的属性
person.eat();//访问对象的方法
```

- **对象内有两种成员**：
  - name / age / sex等对象特征描述称之为对象的**属性**，就是我们之前学习的变量
  - eat / sleep 等对象拥有的行为称之为对象的**方法**，也就是我们之前说的函数

### 1.3、对象的操作

- 访问
  - person.name 或 `person["age"]`
  - person.eat() 或 `person["sleep"]()`
- 增加、修改
  - person.name = "xxx"
  - 已存在则修改，不存在则新增
- 删除
  - delete person.name 一般不这么用
  - person.name = null 一般会这么用

### 1.4、数据安全

```js
// 将 全局变量 全局作用域的函数 封装进对象里，这样就只有一个全局变量了，就是我们创建的对象obj
// 创建一个对象，更多的是一种解决方案，一种编程思想
var obj = {
	myName:"阿伟",
  num:1,
  loginStatus:true
}
```

## 二、面向过程和面向对象编程概述（了解）

> 一般来说把面向对象和面向过程放在一起对比讲解
>
> 这是两种编程思想---或者设计思维

- **面向过程**编程就是分析出解决问题的步骤，然后使用函数把这些步骤一步步实现，**重心放在完成的每个过程上**。

- **面向对象**则是以封装的思想，**重点放在解决问题需要的对象身上**，然后通过对对象的操作来完成相应的功能。

  

**比如盖房子**：

![img](images/面向对象和面向过程.png)





**比如把大象放冰箱：**

在面向过程的编程方式中实现“把大象放冰箱”这个问题答案是耳熟能详的，一共分三步：
开门；
把大象装进冰箱；
关门。

面向对象的解决方法：
冰箱.开门（）
冰箱.装进（大象）
冰箱.关门（）



- 两者比较
  - 面向过程性能比面向对象高，适合跟硬件联系很紧密的东西，但是不易维护、不易复用、不易扩展。
  - 面向对象**易维护、易复用、易扩展**，但是更耗资源，性能比面向过程低。
  - 至于以后使用哪一种，这就需要看我们的具体需求，根据不同的需求做不同的选择。

## 三、创建对象

1. 字面量方式创建对象
2. 内置构造函数创建对象
3. 简单工厂创建对象
4. 自定义构造函数创建对象

### 3.1、用字面量方式创建对象（掌握）

前文已述

### 3.2、内置构造函数创建对象

使用new关键字+内置的构造函数创建对象

```javascript
const userInfo1 = new Object()
userInfo1.name = '张三'
userInfo1.age= 100
userInfo1.height = 160

const userInfo2 = new Object()
userInfo2.name = '李四'
userInfo2.age= 20
userInfo2.height = 170

const userInfo3 = new Object()
userInfo3.name = '王五'
userInfo3.age= 30
userInfo3.height = 165
```

如果要创建100个人的信息，怎么办？

缺点：和字面量创建存在一样问题：代码重复

### 3.3、简单工厂创建对象

利用我们学过的函数封装的思想，我们应该可以想到，**当有重复代码的时候，我们可以将这些重复代码抽取到函数中来解决**。

把上面的代码直接拿下来，封装进我们的函数内：

``` js
function createBook() {
	var book = new Object();
	book.name="JS";
	book.price=10;
	book.author="作者";
	book.showInfo=function () {
    	console.log("描述信息");
	}
}

```

这个时候应该想的到，抽取公共部分作为参数，可以传不同值进来；

我们需要得到这个封装好的对象后，需要返回这个对象。

就是：

```javascript
function createBook(name, price, author) {
    var book = new Object();
    book.name=name;
    book.price=price;
    book.author=author;
    book.showInfo=function () {
        console.log(this.name,this.price,this.author);
    }
    return book;
}
var book1 = createBook("bookName1",10,"author1");
var book2 = createBook("bookName2",10,"author2");
console.log(book1);
console.log(book2);
```

我们将创建book对象的代码封装到createBook函数中，当需要创建一个book对象的时候，直接调用该函数，将函数需要的参数传递过去即可。

那么，相同的思想，如果我们需要创建其他的对象，一样可以使用封装函数的方法来解决，这是没问题的。

```javascript
function createPerson(name, age) {
    var p = new Object();
    p.name = name;
    p.age = age;
    return p;
}
var person1 = createPerson("Neld", 10)
console.log(person1)
```

优点：批量创建对象，减少重复代码
**缺点：无法判断对象类型，比如上面两个对象无法判断是Book类型还是Person类型**

```javascript
console.log(typeof book1) // object
console.log(typeof person1) // object
```

### 3.4、自定义构造函数创建对象（必须掌握）

**工厂函数无法判断对象的类型，那么我们需要抽象出一个类，就是我们的构造函数**

**构造函数本质上和普通函数没有区别**

目的：创建对象！

语法：

```javascript
function 函数名（参数列表）{
	this.key1=参数1,
	this.key2=参数2,
}
var obj = new 函数()
```

怎么用自定义构造函数创建对象（对比简单工厂创建对象）

```javascript
// 1.构造函数的首字母大写(语法规范,约定俗成)
function CreatePerson(name, age, sex) {
	  // 2. 不需要创建对象,会自动创建 
    this.name=name;
    this.age=age;
    this.sex=sex;
    // 3. 不需要返回这个对象,会自动返回
}
// 4. **必须搭配new关键字使用**   否则 一方面不安全,再一方面 也不能创建对象
var p = new CreatePerson("Neld", 10, 1);
var p2 = new CreatePerson("Song", 12, 0);
var p3 = CreatePerson("Song", 12, 0); // 试一试，不用new，返回什么？
console.log(p);
console.log(p2);
```

#### 3.4.1、自定义构造函数和工厂函数的区别

1. 构造函数名的**首字母要求大写**
2. 在函数中，不需要手动创建对象进行数据封装，**会自动创建对象并封装数据**
3. 在函数最后，不需要手动返回创建好的对象，**会自动返回**
4. **构造函数一样可以直接调用，此时内部的this执行window，这种方式不太安全，有可能会在函数内部修改当前的全局变量，不建议使用**，而且这样做也不能创建对象，**必须要搭配new关键字一起使用才能创建对象**



## 四、this指针（必须掌握）

在JS编程的过程中发现，我们大量使用到this关键字，用好了this，能让我们的代码更加优雅。

先搞清楚全局作用域和函数作用域（ **在函数中，函数的所属者默认绑定到 this 上。**）

this总是指向一个对象（引用类型），但是具体指向谁，需要根据我们在哪里使用this有关。这里主要分为下面几种情况：

1. 全局使用。

   函数外部使用，作用域即是全局作用域（window），所以，在全局作用域中使用的this指向window

   this在函数内部，全局调用这个函数，也是在全局使用，此时函数内的this也指向window

   ```javascript
   window.a = 1;
   // 所属
   function f() {
     console.log(this.a)
   }
   f()
   setTimeout(function () {
     console.log(this.a)
   }, 1000)
   ```
   
2. 对象方法的调用 例如：obj.fn()

   函数内部的作用域是局部的，属于调用当前函数的对象，所以this执向调用当前函数的对象

   ```javascript
   const obj = {
     name: 'Mike',
     say: function () {
       console.log(this.name)
     }
   }
   
   obj.say() // >Mike
   ```

3. 事件中使用触发事件的时候，this指向 事件源 

   ```html
   <ul id="ul">
     <li>1</li>
     <li>2</li>
     <li>3</li>
     <li>4</li>
     <li>5</li>
   </ul>
   
   <script>
     var ul = document.getElementById('ul')
     var li = ul.getElementsByTagName('li')
     for (var i = 0; i < li.length; i++) {
       li[i].onclick = function () {
         console.log(this.innerText)
       }
     }
   </script>
   ```

4. 箭头函数中使用

   箭头函数没有作用域，箭头函数中的this指向外层作用域

   ```javascript
   window.name = '李四'
   const obj = {
     name: '张三',
     say: () => {
       console.log(this.name)
     }
   }
   obj.say() // >李四
   ```

   

5. 构造函数内部

   **在构造函数中，this指向实例对象**

   ```javascript
   let that = null
   function Person(name) {
     that = this
     this.name = name
     this.say = function () {
       console.log(this) // 方法里面的this，并不是我们说的构造函数里面的this,这里的this会等到方法调用了才会去指向当前对象
     }
   }
   
   const p1 = new Person('张三')
   const p2 = new Person('李四')
   
   console.log(that) // >Person {name: '李四', say: ƒ} 每一次创建实例对象，that，也就是构造函数里面的this，都指向了新的实例对象
   p1.say() // >Person {name: '张三', say: ƒ}
   p2.say() // >Person {name: '李四', say: ƒ}
   ```

   

## 五、构造器属性（必须掌握）

还记不记得，工厂函数没办法区分类型的问题，而我们是构造函数可以区分类型，那，我们在构造函数里，怎么判断该对象的类型呢？

### 5.1、抽象

- ##### **构造器(类)**：
  
  - **泛指一类事物**
  - 把多个对象相同的部分抽象出来，成为一个类，就是一个函数，构造函数，和new一起来创建对象，
- **对象**：
  - **特指某一个具体事物**
  - 使用构造函数创造出来的对象的类型就是构造函数这种类型

### 5.2、怎么分类 

需求：定义Person和Dog类，并各自创建出一个对象，再判断对象是否是Person或Dog类型.

1. **constructor属性**

   定义：使用constructor属性可以获取到创建对象使用的构造器函数（类）。

   语法：对象.constructor——————获取到就是该对象的类

   ```javascript
   function Person(name) {
       this.name = name;
   }
   function Dog(name) {
       this.name = name;
   }
   var p = new Person("p");
   var d = new Dog("d");
   console.log(p.constructor);//打印得到Person函数对象
   console.log(d.constructor);//打印得到Dog函数对象
   if(p.constructor === Person){
       console.log("p是Person对象");
   }
   if(d.constructor === Dog){
       console.log("d是Dog对象");
   }
   ```

2. **instanceof关键字**

   定义：instanceof关键字用来判断对象的类型是否是某个类，如果是返回true，反之返回false。

   语法：var ret = 对象名  instanceof 类名;————————获取到的是一个布尔值

   ```javascript
   function Person(name) {
       this.name = name;
   }
   function Dog(name) {
       this.name = name;
   }
   var p = new Person("p");
   var d = new Dog("d");
   console.log(p instanceof Person);//true
   console.log(d instanceof Person);//false
   
   ```

## 六、原型对象

### 6.1、自定义构造函数存在的问题（了解）

问题：P对象和P2对象的say方法是同一个内存地址吗？

```javascript
function Person(name, age) {
    this.name = name;
    this.age = age;
    this.say = function(){
        console.log("say hello");
    }
}
var p = new Person("zs", 10);
var p2 = new Person("ls", 15);
console.log(p.say===p2.say);
```

首先，p1和p2指向不同的地址、占用不同的空间0x22和0x44。在p1的内部空间0x22内，又开辟了新的内存空间0x33用来存放p1的say方法，同理在p2的内部空间0x44内，开辟了新的内存空间0x55用来存放p2的say方法。

结论：每一次创建实例对象的时候,  say都赋值了一个新的函数-------浪费内存。从内存资源分配考虑，我们无需为每个对象创建并分配一份新的函数对象（完全相同），这种函数大家最好共享同一份。**

**解决办法：那么我们可以把函数单独拿出来，定义全局，函数名做方法对象。使用同一个引用地址，不断地赋值给say：**

```javascript
function Person(name, age) {
    this.name = name;
    this.age = age;
    this.say = say
}
function say(){
	console.log("say hello");
}
var p = new Person("zs", 10);
var p2 = new Person("ls", 15);
console.log(p.say===p2.say); // 使用同一个引用地址
```

**结论：解决方法共享的目的是达到了，但是又产生了新的问题，我们面向对象编程的目的是为了减少全局变量，而这种写法又增加了全局变量，与我们编程思想产生了冲突。**

完美的解决办法：这个时候，我们需要在构造函数上想办法，在构造函数身上开辟一个区域，来存放我们的公共方法——原型对象

### 6.2、原型对象释义（必须掌握）

定义：每一个构造函数都有一个与之相关联的对象，该对象称之为原型对象。

功能：每个实例对象都能共享其原型对象上的属性和方法，减少内存分配。

语法：

1. `构造函数.prototype` 获取原型对象
2. `构造函数.prototype.成员名 = 成员值 ` 在原型对象上添加成员的方法

所以，在上一节中，我们想在每个Person对象中共享同一个say方法，可以这样来实现：

```javascript
function Person(name, age) {
    this.name = name;
    this.age = age;
}
//在原型对象上添加say函数，实例对象共享该函数
Person.prototype.say = function(){
    console.log(this.name + " say hello"); // this指向调用的对象
};
var p = new Person("zs", 10);
p.say();
var p2 = new Person("zs", 10);
p2.say();
```

### 6.3、面向对象中的核心概念（理解）

**构造函数**：Person，和new关键字一起创建对象

**原型对象**：Person.prototype，

**实例对象**：由构造器创建出来的对象称之为实例对象

**实例化：**由构造器创建实例对象的过程称之为实例化

对象的成员：属性+方法

**实例成员：**实例对象上的属性和方法，name,age，只能当前实例对象才能访问

**原型成员：**原型对象上的属性和方法，say()，使用该原型对象对应构造器创建出来的所有实例对象都能访问

**静态成员：**直接添加在构造函数上的属性和 方法，只能使用构造函数才能访问

### 6.4、获取原型对象的方法

#### 6.4.1、 getPrototypeOf方法（了解）

定义：Object构造器上的静态成员方法。

语法：`Object.getPrototypeOf(实例对象)` 获取指定实例对象的原型对象

#### 6.4.2、 __ proto __属性（必须掌握）

定义：在每个实例对象上都有一个__ proto __的属性，也是用来获取该对象的原型对象（该属性是在ES6之后才纳入规范，在这之前，只有部分浏览器实现。）。

语法： `实例对象.__ proto __;`

```javascript
实例对象.__ proto __ === 构造器.prototype；
```

我们之前说过：**每个实例对象都能共享其原型对象上的属性和方法**

那么之前我们用 对象.constructor来获取到就是该对象的类 是什么原理？

![](./images/tsts.png)

结论：实例对象没有constructor属性，其实是共享了其原型对象上的属性（Person.prototype身上才有constructor属性）

`__ proto __`属性，可以当做一个象形文字来看，这个下划线不是无意义的，它相当于一条锁链，把我实例对象身上没有的属性方法，通过这条锁链暴力拉下来，供我使用。



这样，我们就可以通过一个图来表示构造函数、原型对象和实例对象三者的关系：

![img](./images/1551935332196.jpg)





以上提到的三种获取原型对象的方法所得到的结果是一样的。即：

```javascript
Object.getPrototypeOf(p) == Person.prototype == p.__ proto __
//代码不能这么写，要分开比较
```

### 6.5、构造函数创建实例对象补充（必须掌握）

在对原型相关的知识有了一定的认识之后，我们再回过头来看看构造函数创建实例对象中的细节问题。

```javascript
function Person(name) {
    //默认创建一个Object对象 var obj = new Object();
    //将obj对象赋值给this   this = obj;
    this.name = name;//通过this添加属性和方法
    //返回封装好的this对象，return this;
}
```

这是我们前面分析出来的步骤，现在再来看问题就很明显了。

在函数中，默认为我们创建的是一个Object类型的对象，该对象和当前的Person构造器没有任何关系。

那么想让最终创建出来的对象拥有具体的类型的话，应该还有下面一个步骤：

```javascript
//设置obj的__proto__属性指向Person构造函数的原型对象
//obj.__proto__ = Person.prototype;
```

**构造函数创建实例对象的完整过程：**

1. 在函数内部默认会创建一个空对象——var obj = new Object();
2. 设置`obj.__proto__` 属性指向构造器.prototype——`obj.__proto__ = Person.prototype`;
3. 默认把创建好的对象赋值给this——this = obj;
4. 通过this添加属性和方法——this.xx=xx……
5. 默认会把内部创建的对象返回——return this;
