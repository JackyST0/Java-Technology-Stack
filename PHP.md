# PHP:

### PHP 语法:
```
基础 PHP 语法,PHP 脚本可放置于文档中的任何位置,PHP 脚本以 <?php 开头，以 ?> 结尾：
<?php
// 此处是 PHP 代码
?>
(注：PHP 语句以分号结尾（;）。PHP 代码块的关闭标签也会自动表明分号（因此在 PHP 代码块的最后一行不必使用分号）。)

PHP 支持三种注释：
<?php
//   这是单行注释

#    这也是单行注释

/*
    这是多行注释块
    它横跨了
    多行
*/
?>

在 PHP 中，所有用户定义的函数、类和关键词（例如 if、else、echo 等等）都对大小写不敏感。不过在 PHP 中，所有变量都对大小写敏感。
```

### PHP 变量：
```
PHP 变量规则：
变量以 $ 符号开头，其后是变量的名称
变量名称必须以字母或下划线开头
变量名称不能以数字开头
变量名称只能包含字母数字字符和下划线（A-z、0-9 以及 _）
变量名称对大小写敏感（$y 与 $Y 是两个不同的变量）
（注：PHP 变量名称对大小写敏感！）

创建 PHP 变量（注：如果您为变量赋的值是文本，请用引号包围该值。）：
PHP 没有创建变量的命令。变量会在首次为其赋值时被创建：
<?php
$txt="Hello world!";
$x=5;
$y=10.5;
?>
PHP 是一门类型松散的语言，在上面的例子中，请注意我们不必告知 PHP 变量的数据类型。PHP 根据它的值，自动把变量转换为正确的数据类型。

Local 和 Global 作用域
函数之外声明的变量拥有 Global 作用域，只能在函数以外进行访问。函数内部声明的变量拥有 LOCAL 作用域，只能在函数内部进行访问。

PHP global 关键词
global 关键词用于在函数内访问全局变量。要做到这一点，请在（函数内部）变量前面使用 global 关键词：
<?php
$x=5;
$y=10;
function myTest() {
  global $x,$y;
  $y=$x+$y;
}
myTest();
echo $y; // 输出 15
?>
PHP 同时在名为 $GLOBALS[index] 的数组中存储了所有的全局变量。下标存有变量名。这个数组在函数内也可以访问，并能够用于直接更新全局变量。上面的例子可以这样重写：
<?php
$x=5;
$y=10;
function myTest() {
  $GLOBALS['y']=$GLOBALS['x']+$GLOBALS['y'];
} 
myTest();
echo $y; // 输出 15
?>

PHP static 关键词
通常，当函数完成/执行后，会删除所有变量。不过，有时我需要不删除某个局部变量。实现这一点需要更进一步的工作。要完成这一点，请在您首次声明变量时使用 static 关键词：
<?php
function myTest() {
  static $x=0;
  echo $x;
  $x++;
}
myTest();
myTest();
myTest();
?>
然后，每当函数被调用时，这个变量所存储的信息都是函数最后一次被调用时所包含的信息。（注：该变量仍然是函数的局部变量。）
```

### PHP 5 echo 和 print 语句：
```
echo 和 print 之间的差异：
echo - 能够输出一个以上的字符串
print - 只能输出一个字符串，并始终返回 1
提示：echo 比 print 稍快，因为它不返回任何值。
```

### PHP 数据类型：
```
字符串、整数、浮点数、逻辑、数组、对象、NULL。
PHP 字符串
字符串是字符序列，比如 "Hello world!"。字符串可以是引号内的任何文本。您可以使用单引号或双引号：
$x = "Hello world!";
$x = 'Hello world!';

PHP 整数
整数是没有小数的数字。整数规则：
整数必须有至少一个数字（0-9）
整数不能包含逗号或空格
整数不能有小数点
整数正负均可
可以用三种格式规定整数：十进制、十六进制（前缀是 0x）或八进制（前缀是 0）
在下面的例子中，我们将测试不同的数字。PHP var_dump() 会返回变量的数据类型和值：
<?php 
$x = 5985;
var_dump($x);
echo "<br>"; 
$x = -345; // 负数
var_dump($x);
echo "<br>"; 
$x = 0x8C; // 十六进制数
var_dump($x);
echo "<br>";
$x = 047; // 八进制数
var_dump($x);
?>

PHP 浮点数
浮点数是有小数点或指数形式的数字。在下面的例子中，我们将测试不同的数字。PHP var_dump() 会返回变量的数据类型和值：
<?php 
$x = 10.365;
var_dump($x);
echo "<br>"; 
$x = 2.4e3;
var_dump($x);
echo "<br>"; 
$x = 8E-5;
var_dump($x);
?>

PHP 逻辑
逻辑是 true 或 false。
$x=true;
$y=false;

PHP 数组
数组在一个变量中存储多个值。在下面的例子中，我们将测试不同的数组。PHP var_dump() 会返回变量的数据类型和值：
<?php 
$cars=array("Volvo","BMW","SAAB");
var_dump($cars);     // 返回 array(3) { [0]=> string(5) "Volvo" [1]=> string(3) "BMW" [2]=> string(4) "SAAB" }
?>

PHP 对象
对象是存储数据和有关如何处理数据的信息的数据类型。在 PHP 中，必须明确地声明对象。首先我们必须声明对象的类。对此，我们使用 class 关键词。类是包含属性和方法的结构。然后我们在对象类中定义数据类型，然后在该类的实例中使用此数据类型：
<?php
class Car
{
  var $color;
  function Car($color="green") {
    $this->color = $color;
  }
  function what_color() {
    return $this->color;
  }
}
?>

PHP NULL 值
特殊的 NULL 值表示变量无值。NULL 是数据类型 NULL 唯一可能的值。NULL 值标示变量是否为空。也用于区分空字符串与空值数据库。可以通过把值设置为 NULL，将变量清空：
<?php
$x="Hello world!";
$x=null;
var_dump($x);           // 返回 NULL
?>
```

### PHP 字符串函数：
```
PHP strlen() 函数
strlen() 函数返回字符串的长度，以字符计。
下例返回字符串 "Hello world!" 的长度：
<?php
echo strlen("Hello world!");                               // 输出 12
?>

对字符串中的单词计数
PHP str_word_count() 函数对字符串中的单词进行计数：
<?php
echo str_word_count("Hello world!");                       // 输出 2
?>

反转字符串
PHP strrev() 函数反转字符串：
<?php
echo strrev("Hello world!");                               // 输出 !dlrow olleH
?>

PHP strpos() 函数
strpos() 函数用于检索字符串内指定的字符或文本。如果找到匹配，则会返回首个匹配的字符位置。如果未找到匹配，则将返回 FALSE。下例检索字符串 "Hello world!" 中的文本 "world"：
<?php
echo strpos("Hello world!","world");                        // 输出 6
?>

替换字符串中的文本
PHP str_replace() 函数用一些字符串替换字符串中的另一些字符。下面的例子用 "Kitty" 替换文本 "world"：
<?php
echo str_replace("world", "Kitty", "Hello world!");         // 输出 Hello Kitty!
?>
```

### PHP 常量：
```
常量是单个值的标识符（名称）。在脚本中无法改变该值。有效的常量名以字符或下划线开头（常量名称前面没有 $ 符号）。（注：与变量不同，常量贯穿整个脚本是自动全局的。）

设置 PHP 常量
如需设置常量，请使用 define() 函数 - 它使用三个参数：
首个参数定义常量的名称
第二个参数定义常量的值
可选的第三个参数规定常量名是否对大小写不敏感。默认是 false。
下例创建了一个对大小写敏感的常量，值为 "Welcome to W3School.com.cn!"：
<?php
define("GREETING", "Welcome to W3School.com.cn!");         // 输出 Welcome to W3School.com.cn!
echo GREETING;
?>
```

### PHP 运算符：
```
PHP 算数运算符
运算符	      名称	    例子	          结果	
+	      加法	  $x + $y	        $x 与 $y 求和	
-	      减法	  $x - $y	        $x 与 $y 的差数	
*	      乘法	  $x * $y	        $x 与 $y 的乘积	
/	      除法	  $x / $y	        $x 与 $y 的商数	
%	      取模	  $x % $y	        $x 除 $y 的余数	
下例展示了使用不同算数运算符的不同结果：
<?php 
$x=17; 
$y=8;
echo ($x + $y); // 输出 25
echo ($x - $y); // 输出 9
echo ($x * $y); // 输出 136
echo ($x / $y); // 输出 2.125
echo ($x % $y); // 输出 1
?>

PHP 赋值运算符
PHP 赋值运算符用于向变量写值。PHP 中基础的赋值运算符是 "="。这意味着右侧赋值表达式会为左侧运算数设置值。
赋值	  等同于	                    描述	
x = y	 x = y	                   右侧表达式为左侧运算数设置值。	
x += y	 x = x + y	           加	
x -= y	 x = x - y	           减	
x *= y	 x = x * y	           乘	
x /= y	 x = x / y	           除	
x %= y	 x = x % y	           模数	
<?php 
$x=17; 
echo $x; // 输出 17
$y=17; 
$y += 8;
echo $y; // 输出 25
$z=17;
$z -= 8;
echo $z; // 输出 9
$i=17;
$i *= 8;
echo $i; // 输出 136
$j=17;
$j /= 8;
echo $j; // 输出 2.125
$k=17;
$k %= 8;
echo $k; // 输出 1
?>

PHP 字符串运算符
运算符	     名称	          例子	                                         结果
.	     串接	      $txt1 = "Hello" $txt2 = $txt1 . " world!"	      现在 $txt2 包含 "Hello world!"	
.=	     串接赋值	      $txt1 = "Hello" $txt1 .= " world!"	      现在 $txt1 包含 "Hello world!"	
<?php
$a = "Hello";
$b = $a . " world!";
echo $b; // 输出 Hello world!
$x="Hello";
$x .= " world!";
echo $x; // 输出 Hello world!
?>

PHP 递增/递减运算符
运算符	    名称	             描述	
++$x	   前递增	          $x 加一递增，然后返回 $x	
$x++	   后递增	          返回 $x，然后 $x 加一递增	
--$x	   前递减	          $x 减一递减，然后返回 $x	
$x--	   后递减	          返回 $x，然后 $x 减一递减	
<?php
$x=17; 
echo ++$x; // 输出 18
$y=17; 
echo $y++; // 输出 17
$z=17;
echo --$z; // 输出 16
$i=17;
echo $i--; // 输出 17
?>

PHP 比较运算符
PHP 比较运算符用于比较两个值（数字或字符串）：
运算符	      名称	                    例子	                     结果	
==	     等于	                  $x == $y	              如果$x 等于 $y，则返回 true。	
===	     全等（完全相同）	          $x === $y	              如果 $x 等于 $y，且它们类型相同，则返回 true。	
!=	     不等于	                  $x != $y	              如果 $x 不等于 $y，则返回 true。	
<>	     不等于	                  $x <> $y	              如果 $x 不等于 $y，则返回 true。	
!==	     不全等（完全不同）	          $x !== $y	              如果 $x 不等于 $y，或它们类型不相同，则返回 true。	
>	     大于	                  $x > $y	              如果 $x 大于 $y，则返回 true。	
<	     小于	                  $x < $y	              如果 $x 小于 $y，则返回 true。	
>=	     大于或等于	                  $x >= $y	              如果 $x 大于或者等于 $y，则返回 true.	
<=	     小于或等于	                  $x <= $y	              如果 $x 小于或者等于 $y，则返回 true。	
<?php
$x=17; 
$y="17";
var_dump($x == $y);                         // 因为值相等，返回 true
echo "<br>";
var_dump($x === $y);                        // 因为类型不相等，返回 false
echo "<br>";
var_dump($x != $y);                         // 因为值相等，返回 false
echo "<br>";
var_dump($x !== $y);                        // 因为值不相等，返回 true
echo "<br>";
$a=17;
$b=8;
var_dump($a > $b);                          // 返回 bool(true)
echo "<br>";
var_dump($a < $b);                          // 返回 bool(false)
?>

PHP 逻辑运算符
运算符	    名称	 例子	                      结果	
and	    与	      $x and $y	            如果 $x 和 $y 都为 true，则返回 true。	
or	    或	      $x or $y	            如果 $x 和 $y 至少有一个为 true，则返回 true。	
xor	    异或      $x xor $y	            如果 $x 和 $y 有且仅有一个为 true，则返回 true。	
&&	    与	      $x && $y	            如果 $x 和 $y 都为 true，则返回 true。	
||	    或	      $x || $y	            如果 $x 和 $y 至少有一个为 true，则返回 true。	
!	    非	      !$x	            如果 $x 不为 true，则返回 true。	

PHP 数组运算符
PHP 数组运算符用于比较数组：
运算符	  名称	            例子	                     结果	
+	   联合	            $x + $y	                $x 和 $y 的联合（但不覆盖重复的键）	
==	   相等	            $x == $y	                如果 $x 和 $y 拥有相同的键/值对，则返回 true。	
===	   全等	            $x === $y	                如果 $x 和 $y 拥有相同的键/值对，且顺序相同类型相同，则返回 true。	
!=	   不相等	    $x != $y	                如果 $x 不等于 $y，则返回 true。	
<>	   不相等	    $x <> $y	                如果 $x 不等于 $y，则返回 true。	
!==	   不全等	    $x !== $y	                如果 $x 与 $y 完全不同，则返回 true。	
<?php
$x = array("a" => "apple", "b" => "banana"); 
$y = array("c" => "orange", "d" => "peach"); 
$z = $x + $y; // $x 与 $y 的联合
var_dump($z);        // 返回 array(4) { ["a"]=> string(5) "apple" ["b"]=> string(6) "banana" ["c"]=> string(6) "orange" ["d"]=> string(5) "peach" }
var_dump($x == $y);  // 返回 bool(false)
var_dump($x === $y); // 返回 bool(false)
var_dump($x != $y);  // 返回 bool(true)
var_dump($x <> $y);  // 返回 bool(true)
var_dump($x !== $y); // 返回 bool(true)
?>
```

### PHP if...else...elseif 语句：
```
PHP 条件语句
在您编写代码时，经常会希望为不同的决定执行不同的动作。您可以在代码中使用条件语句来实现这一点。在 PHP 中，我们可以使用以下条件语句：
if 语句 - 如果指定条件为真，则执行代码
if...else 语句 - 如果条件为 true，则执行代码；如果条件为 false，则执行另一端代码
if...elseif....else 语句 - 根据两个以上的条件执行不同的代码块
switch 语句 - 选择多个代码块之一来执行

PHP - if 语句
if 语句用于在指定条件为 true 时执行代码。
语法
if (条件) {
  当条件为 true 时执行的代码;
}

PHP - if...else 语句
请使用 if....else 语句在条件为 true 时执行代码，在条件为 false 时执行另一段代码。
语法
if (条件) {
  条件为 true 时执行的代码;
} else {
  条件为 false 时执行的代码;
}

PHP - if...elseif....else 语句
请使用 if....elseif...else 语句来根据两个以上的条件执行不同的代码。
语法
if (条件) {
  条件为 true 时执行的代码;
} elseif (condition) {
  条件为 true 时执行的代码;
} else {
  条件为 false 时执行的代码;
}
```

### PHP Switch 语句：
```
Switch 语句
如果您希望有选择地执行若干代码块之一，请使用 Switch 语句。使用 Switch 语句可以避免冗长的 if..elseif..else 代码块。
语法
switch (expression)
{
case label1:
  expression = label1 时执行的代码 ;
  break;  
case label2:
  expression = label2 时执行的代码 ;
  break;
default:
  表达式的值不等于 label1 及 label2 时执行的代码;
}
工作原理：
对表达式（通常是变量）进行一次计算
把表达式的值与结构中 case 的值进行比较
如果存在匹配，则执行与 case 关联的代码
代码执行后，break 语句阻止代码跳入下一个 case 中继续执行
如果没有 case 为真，则使用 default 语句
```

### PHP while 循环：
```
PHP 循环
在您编写代码时，经常需要反复运行同一代码块。我们可以使用循环来执行这样的任务，而不是在脚本中添加若干几乎相等的代码行。在 PHP 中，我们有以下循环语句：
while - 只要指定条件为真，则循环代码块
do...while - 先执行一次代码块，然后只要指定条件为真则重复循环
for - 循环代码块指定次数
foreach - 遍历数组中的每个元素并循环代码块

PHP while 循环
只要指定的条件为真，while 循环就会执行代码块。
语法
while (条件为真) {
  要执行的代码;
}

PHP do...while 循环
do...while 循环首先会执行一次代码块，然后检查条件，如果指定条件为真，则重复循环。
语法
do {
  要执行的代码;
} while (条件为真);
（注：do while 循环只在执行循环内的语句之后才对条件进行测试。这意味着 do while 循环至少会执行一次语句，即使条件测试在第一次就失败了。）
```

### PHP for 循环：
```
PHP for 循环
如果您已经提前确定脚本运行的次数，可以使用 for 循环。
语法
for (init counter; test counter; increment counter) {
  code to be executed;
}
参数：
init counter：初始化循环计数器的值
test counter：: 评估每个循环迭代。如果值为 TRUE，继续循环。如果它的值为 FALSE，循环结束。
increment counter：增加循环计数器的值

PHP foreach 循环
foreach 循环只适用于数组，并用于遍历数组中的每个键/值对。
语法
foreach ($array as $value) {
  code to be executed;
}
每进行一次循环迭代，当前数组元素的值就会被赋值给 $value 变量，并且数组指针会逐一地移动，直到到达最后一个数组元素。
```

### PHP 函数：
```
在 PHP 创建用户定义函数，用户定义的函数声明以单词 "function" 开头：
语法
function functionName() {
  被执行的代码;
}
(提示：函数名能够以字母或下划线开头（而非数字）。函数名对大小写不敏感。)

PHP 默认参数值
下面的例子展示了如何使用默认参数。如果我们调用没有参数的 setHeight() 函数，它的参数会取默认值：
<?php
function setHeight($minheight=50) {
  echo "The height is : $minheight <br>";
}
setHeight(350);
setHeight();                   //（将使用默认值 50） 返回 The height is : 50
setHeight(135);
setHeight(80);
?>

PHP 函数 - 返回值
如需使函数返回值，请使用 return 语句：
<?php
function sum($x,$y) {
  $z=$x+$y;
  return $z;
}
echo "5 + 10 = " . sum(5,10) . "<br>";
echo "7 + 13 = " . sum(7,13) . "<br>";
echo "2 + 4 = " . sum(2,4);
?>
```

### PHP 数组:
```
在 PHP 中创建数组,在 PHP 中， array() 函数用于创建数组：
array();
在 PHP 中，有三种数组类型：
索引数组 - 带有数字索引的数组
关联数组 - 带有指定键的数组
多维数组 - 包含一个或多个数组的数组

PHP 索引数组
有两种创建索引数组的方法：
索引是自动分配的（索引从 0 开始）：
$cars=array("porsche","BMW","Volvo");
或者也可以手动分配索引：
$cars[0]="porsche";
$cars[1]="BMW";
$cars[2]="Volvo";

获得数组的长度 - count() 函数
count() 函数用于返回数组的长度（元素数）：
<?php
$cars=array("porsche","BMW","Volvo");
echo count($cars);            // 输出 3
?>

PHP 关联数组
关联数组是使用您分配给数组的指定键的数组。
有两种创建关联数组的方法：
$age=array("Bill"=>"35","Steve"=>"37","Elon"=>"43");
或者：
$age['Bill']="63";
$age['Steve']="56";
$age['Elon']="47";

遍历关联数组
如需遍历并输出关联数组的所有值，您可以使用 foreach 循环，就像这样：
<?php
$age=array("Bill"=>"63","Steve"=>"56","Elon"=>"47");
foreach($age as $x=>$x_value) {
  echo "Key=" . $x . ", Value=" . $x_value;         // 输出 Key=Bill, Value=63
  echo "<br>";                                              Key=Steve, Value=56
}                                                           Key=Elon, Value=47
?>
```

### PHP 数组排序：
```
PHP - 数组的排序函数
sort() - 以升序对数组排序
rsort() - 以降序对数组排序
asort() - 根据值，以升序对关联数组进行排序
ksort() - 根据键，以升序对关联数组进行排序
arsort() - 根据值，以降序对关联数组进行排序
krsort() - 根据键，以降序对关联数组进行排序

对数组进行升序排序 - sort()
下面的例子按照字母升序对数组 $cars 中的元素进行排序：
<?php
$cars=array("porsche","BMW","Volvo");
sort($cars);                // 数组排序成 "BMW","Volvo","porsche"
?>
对数组进行降序排序 - rsort()
下面的例子按照字母降序对数组 $cars 中的元素进行排序：
<?php
$numbers=array(3,5,1,22,11);
rsort($numbers);            // 数组排序成 22,11,5,3,1
?>

根据值对数组进行升序排序 - asort()
下面的例子根据值对关联数组进行升序排序：
<?php
$age=array("Bill"=>"63","Steve"=>"56","Elon"=>"47");
asort($age);               // 数组排序成 "Elon"=>"47","Steve"=>"56","Bill"=>"63"
?>
根据键对数组进行升序排序 - ksort()
下面的例子根据键对关联数组进行升序排序：
<?php
$age=array("Bill"=>"63","Steve"=>"56","Elon"=>"47");
ksort($age);              // 数组排序成 "Bill"=>"63","Elon"=>"47","Steve"=>"56"
?>

根据值对数组进行降序排序 - arsort()
下面的例子根据值对关联数组进行降序排序：
<?php
$age=array("Bill"=>"63","Steve"=>"56","Elon"=>"47");
arsort($age);                 // 数组排序成 "Bill"=>"63","Steve"=>"56","Elon"=>"47"
?> 
根据键对数组进行降序排序 - krsort()
下面的例子根据键对关联数组进行降序排序：
<?php
$age=array("Bill"=>"63","Steve"=>"56","Elon"=>"47");
krsort($age);                 // 数组排序成 "Steve"=>"56","Elon"=>"47","Bill"=>"63"
?>
```

### PHP 全局变量 - 超全局变量:
```
PHP 中的许多预定义变量都是“超全局的”，这意味着它们在一个脚本的全部作用域中都可用。在函数或方法中无需执行 global $variable; 就可以访问它们。这些超全局变量是：
$GLOBALS
$_SERVER
$_REQUEST
$_POST
$_GET
$_FILES
$_ENV
$_COOKIE
$_SESSION

$GLOBALS — 引用全局作用域中可用的全部变量
$GLOBALS 这种全局变量用于在 PHP 脚本中的任意位置访问全局变量（从函数或方法中均可）。PHP 在名为 $GLOBALS[index] 的数组中存储了所有全局变量。变量的名字就是数组的键。下面的例子展示了如何使用超级全局变量 $GLOBALS：
<?php 
$x = 75; 
$y = 25;
function addition() { 
  $GLOBALS['z'] = $GLOBALS['x'] + $GLOBALS['y']; 
}
addition(); 
echo $z;         // 输出 95
?>
在上面的例子中，由于 z 是 $GLOBALS 数组中的变量，因此在函数之外也可以访问它。

PHP $_SERVER
$_SERVER 这种超全局变量保存关于报头、路径和脚本位置的信息。
下表列出了您能够在 $_SERVER 中访问的最重要的元素：

元素/代码	                             描述
$_SERVER['PHP_SELF']	                  返回当前执行脚本的文件名。
$_SERVER['GATEWAY_INTERFACE']	          返回服务器使用的 CGI 规范的版本。
$_SERVER['SERVER_ADDR']	                  返回当前运行脚本所在的服务器的 IP 地址。
$_SERVER['SERVER_NAME']	                  返回当前运行脚本所在的服务器的主机名（比如 www.w3school.com.cn）。
$_SERVER['SERVER_SOFTWARE']	          返回服务器标识字符串（比如 Apache/2.2.24）。
$_SERVER['SERVER_PROTOCOL']	          返回请求页面时通信协议的名称和版本（例如，“HTTP/1.0”）。
$_SERVER['REQUEST_METHOD']	          返回访问页面使用的请求方法（例如 POST）。
$_SERVER['REQUEST_TIME']	          返回请求开始时的时间戳（例如 1577687494）。
$_SERVER['QUERY_STRING']	          返回查询字符串，如果是通过查询字符串访问此页面。
$_SERVER['HTTP_ACCEPT']	                  返回来自当前请求的请求头。
$_SERVER['HTTP_ACCEPT_CHARSET']	          返回来自当前请求的 Accept_Charset 头（ 例如 utf-8,ISO-8859-1）
$_SERVER['HTTP_HOST']	                  返回来自当前请求的 Host 头。
$_SERVER['HTTP_REFERER']	          返回当前页面的完整 URL（不可靠，因为不是所有用户代理都支持）。
$_SERVER['HTTPS']	                  是否通过安全 HTTP 协议查询脚本。
$_SERVER['REMOTE_ADDR']	                  返回浏览当前页面的用户的 IP 地址。
$_SERVER['REMOTE_HOST']	                  返回浏览当前页面的用户的主机名。
$_SERVER['REMOTE_PORT']	                  返回用户机器上连接到 Web 服务器所使用的端口号。
$_SERVER['SCRIPT_FILENAME']	          返回当前执行脚本的绝对路径。
$_SERVER['SERVER_ADMIN']	          该值指明了 Apache 服务器配置文件中的 SERVER_ADMIN 参数。
$_SERVER['SERVER_PORT']	                  Web 服务器使用的端口。默认值为 “80”。
$_SERVER['SERVER_SIGNATURE']	          返回服务器版本和虚拟主机名。
$_SERVER['PATH_TRANSLATED']	          当前脚本所在文件系统（非文档根目录）的基本路径。
$_SERVER['SCRIPT_NAME']	                  返回当前脚本的路径。
$_SERVER['SCRIPT_URI']	                  返回当前页面的 URI。

PHP $_REQUEST
PHP $_REQUEST 用于收集 HTML 表单提交的数据。
PHP $_POST
PHP $_POST 广泛用于收集提交 method="post" 的 HTML 表单后的表单数据。$_POST 也常用于传递变量。
PHP $_GET
PHP $_GET 可用于收集提交 HTML 表单 (method="get") 之后的表单数据。$_GET 也可以收集 URL 中的发送的数据。
```

### PHP 表单处理：
```
GET vs. POST
GET 和 POST 都创建数组（例如，array( key => value, key2 => value2, key3 => value3, ...)）。此数组包含键/值对，其中的键是表单控件的名称，而值是来自用户的输入数据。
GET 和 POST 被视作 $_GET 和 $_POST。它们是超全局变量，这意味着对它们的访问无需考虑作用域 - 无需任何特殊代码，您能够从任何函数、类或文件访问它们。
$_GET 是通过 URL 参数传递到当前脚本的变量数组。
$_POST 是通过 HTTP POST 传递到当前脚本的变量数组。

何时使用 GET？
通过 GET 方法从表单发送的信息对任何人都是可见的（所有变量名和值都显示在 URL 中）。GET 对所发送信息的数量也有限制。限制在大约 2000 个字符。不过，由于变量显示在 URL 中，把页面添加到书签中也更为方便。
GET 可用于发送非敏感的数据。
（注：绝不能使用 GET 来发送密码或其他敏感信息！）

何时使用 POST？
通过 POST 方法从表单发送的信息对其他人是不可见的（所有名称/值会被嵌入 HTTP 请求的主体中），并且对所发送信息的数量也无限制。
此外 POST 支持高阶功能，比如在向服务器上传文件时进行 multi-part 二进制输入。
不过，由于变量未显示在 URL 中，也就无法将页面添加到书签。
提示：开发者偏爱 POST 来发送表单数据。
```

### PHP 多维数组：
```
两维数组是数组的数组（三维数组是数组的数组的数组）。我们能够在两维数组中存储上表中的数据，就像这样：
$cars = array
  (
  array("Volvo",22,18),
  array("BMW",15,13),
  array("Saab",5,2),
  array("Land Rover",17,15)
  );
现在这个两维数组包含了四个数组，并且它有两个索引（下标）：行和列。
如需访问 $cars 数组中的元素，我们必须使用两个索引（行和列）：
<?php
echo $cars[0][0].": 库存：".$cars[0][1].", 销量：".$cars[0][2].".<br>";
echo $cars[1][0].": 库存：".$cars[1][1].", 销量：".$cars[1][2].".<br>";
echo $cars[2][0].": 库存：".$cars[2][1].", 销量：".$cars[2][2].".<br>";
echo $cars[3][0].": 库存：".$cars[3][1].", 销量：".$cars[3][2].".<br>";
?>
我们也可以在 For 循环中使用另一个 For 循环，来获得 $cars 数组中的元素（我们仍需使用两个索引）：
<?php
for ($row = 0; $row < 4; $row++) {
  echo "<p><b>Row number $row</b></p>";
  echo "<ul>";
  for ($col = 0; $col < 3; $col++) {
    echo "<li>".$cars[$row][$col]."</li>";
  }
  echo "</ul>";
}
?>
```

### PHP 日期和时间:
```
PHP Date() 函数
PHP Date() 函数把时间戳格式化为更易读的日期和时间。
语法
date(format,timestamp)
参数	            描述
format	            必需。规定时间戳的格式。
timestamp	    可选。规定时间戳。默认是当前时间和日期。
(注：时间戳是一种字符序列，它表示具体事件发生的日期和事件。)

获得简单的日期
date() 函数的格式参数是必需的，它们规定如何格式化日期或时间。
下面列出了一些常用于日期的字符：
d - 表示月里的某天（01-31）
m - 表示月（01-12）
Y - 表示年（四位数）
1 - 表示周里的某天
其他字符，比如 "/", "." 或 "-" 也可被插入字符中，以增加其他格式。

获得简单的时间
下面是常用于时间的字符：
h - 带有首位零的 12 小时小时格式
i - 带有首位零的分钟
s - 带有首位零的秒（00 -59）
a - 小写的午前和午后（am 或 pm）
(注：请注意 PHP date() 函数会返回服务器的当前日期/时间！)

获得时区
如果从代码返回的不是正确的时间，有可能是因为您的服务器位于其他国家或者被设置为不同时区。因此，如果您需要基于具体位置的准确时间，您可以设置要用的时区。下面的例子把时区设置为 "Asia/Shanghai"，然后以指定格式输出当前时间：
<?php
date_default_timezone_set("Asia/Shanghai");
echo "当前时间是 " . date("h:i:sa");
?>

通过 PHP mktime() 创建日期
date() 函数中可选的时间戳参数规定时间戳。如果您未规定时间戳，将使用当前日期和时间（正如上例中那样）。
mktime() 函数返回日期的 Unix 时间戳。Unix 时间戳包含 Unix 纪元（1970 年 1 月 1 日 00:00:00 GMT）与指定时间之间的秒数。
语法
mktime(hour,minute,second,month,day,year)
下面的例子使用 mktime() 函数中的一系列参数来创建日期和时间：
<?php
$d=mktime(9, 12, 31, 6, 10, 2015);
echo "创建日期是 " . date("Y-m-d h:i:sa", $d);
?>

通过 PHP strtotime() 用字符串来创建日期
PHP strtotime() 函数用于把人类可读的字符串转换为 Unix 时间。
语法
strtotime(time,now)
下例输出连续六个周六的日期：
<?php
$startdate = strtotime("Saturday");
$enddate = strtotime("+6 weeks",$startdate);
while ($startdate < $enddate) {
  echo date("M d", $startdate),"<br>";            /
  $startdate = strtotime("+1 week", $startdate);
}
?>
```

### PHP Include 文件：
```
PHP include 和 require 语句
通过 include 或 require 语句，可以将 PHP 文件的内容插入另一个 PHP 文件（在服务器执行它之前）。
include 和 require 语句是相同的，除了错误处理方面：
require 会生成致命错误（E_COMPILE_ERROR）并停止脚本
include 只生成警告（E_WARNING），并且脚本会继续
因此，如果您希望继续执行，并向用户输出结果，即使包含文件已丢失，那么请使用 include。否则，在框架、CMS 或者复杂的 PHP 应用程序编程中，请始终使用 require 向执行流引用关键文件。这有助于提高应用程序的安全性和完整性，在某个关键文件意外丢失的情况下。
包含文件省去了大量的工作。这意味着您可以为所有页面创建标准页头、页脚或者菜单文件。然后，在页头需要更新时，您只需更新这个页头包含文件即可。
语法
include 'filename';或require 'filename';

例子
假设我们有一个名为 "vars.php" 的文件，其中定义了一些变量：
<?php
$color='银色的';
$car='奔驰轿车';
?>
然后，如果我们引用这个 "vars.php" 文件，就可以在调用文件中使用这些变量：
<html>
<body>
<h1>欢迎访问我的首页！</h1>
<?php
include 'vars.php';
echo "我有一辆" . $color . $car . "。";    // 输出 我有一辆银色的奔驰轿车。
?>
</body>
</html>
```

### PHP 文件处理:
```
PHP 操作文件
PHP 拥有的多种函数可供创建、读取、上传以及编辑文件。
注意：请谨慎操作文件！
当您操作文件时必须非常小心。如果您操作失误，可能会造成非常严重的破坏。常见的错误是：
编辑错误的文件
被垃圾数据填满硬盘
意外删除文件内容

PHP readfile() 函数
readfile() 函数读取文件，并把它写入输出缓冲。假设我们有一个名为 "webdictionary.txt" 的文本文件，存放在服务器上，就像这样：
AJAX = Asynchronous JavaScript and XML
CSS = Cascading Style Sheets
HTML = Hyper Text Markup Language
PHP = PHP Hypertext Preprocessor
SQL = Structured Query Language
SVG = Scalable Vector Graphics
XML = EXtensible Markup Language
读取此文件并写到输出流的 PHP 代码如下（如读取成功则 readfile() 函数返回字节数）：
<?php
echo readfile("webdictionary.txt");
?>
```

### PHP 文件打开/读取/关闭：
```
PHP Open File - fopen()
打开文件的更好的方法是通过 fopen() 函数。此函数为您提供比 readfile() 函数更多的选项。
在这节我们将使用文本文件 "webdictionary.txt"：
AJAX = Asynchronous JavaScript and XML
CSS = Cascading Style Sheets
HTML = Hyper Text Markup Language
PHP = PHP Hypertext Preprocessor
SQL = Structured Query Language
SVG = Scalable Vector Graphics
XML = EXtensible Markup Language
fopen() 的第一个参数包含被打开的文件名，第二个参数规定打开文件的模式。如果 fopen() 函数未能打开指定的文件，下面的例子会生成一段消息：
<?php
$myfile = fopen("webdictionary.txt", "r") or die("Unable to open file!");
echo fread($myfile,filesize("webdictionary.txt"));
fclose($myfile);
?>
文件会以如下模式之一打开：
模式	              描述
r	             打开文件为只读。文件指针在文件的开头开始。
w	             打开文件为只写。删除文件的内容或创建一个新的文件，如果它不存在。文件指针在文件的开头开始。
a	             打开文件为只写。文件中的现有数据会被保留。文件指针在文件结尾开始。创建新的文件，如果文件不存在。
x	             创建新文件为只写。返回 FALSE 和错误，如果文件已存在。
r+	             打开文件为读/写、文件指针在文件开头开始。
w+	             打开文件为读/写。删除文件内容或创建新文件，如果它不存在。文件指针在文件开头开始。
a+	             打开文件为读/写。文件中已有的数据会被保留。文件指针在文件结尾开始。创建新文件，如果它不存在。
x+	             创建新文件为读/写。返回 FALSE 和错误，如果文件已存在。

PHP 读取文件 - fread()
fread() 函数读取打开的文件。fread() 的第一个参数包含待读取文件的文件名，第二个参数规定待读取的最大字节数。如下 PHP 代码把 "webdictionary.txt" 文件读至结尾：
fread($myfile,filesize("webdictionary.txt"));

PHP 关闭文件 - fclose()
fclose() 函数用于关闭打开的文件。fclose() 需要待关闭文件的名称（或者存有文件名的变量）：
<?php
$myfile = fopen("webdictionary.txt", "r");
// some code to be executed....
fclose($myfile);
?>
（注：用完文件后把它们全部关闭是一个良好的编程习惯。您并不想打开的文件占用您的服务器资源。）

PHP 读取单行文件 - fgets()
fgets() 函数用于从文件读取单行。下例输出 "webdictionary.txt" 文件的首行：
<?php
$myfile = fopen("webdictionary.txt", "r") or die("Unable to open file!");
echo fgets($myfile);
fclose($myfile);
?>
（注：调用 fgets() 函数之后，文件指针会移动到下一行。)

PHP 检查 End-Of-File - feof()
feof() 函数检查是否已到达 "end-of-file" (EOF)。feof() 对于遍历未知长度的数据很有用。下例逐行读取 "webdictionary.txt" 文件，直到 end-of-file：
<?php
$myfile = fopen("webdictionary.txt", "r") or die("Unable to open file!");
// 输出单行直到 end-of-file
while(!feof($myfile)) {
  echo fgets($myfile) . "<br>";
}
fclose($myfile);
?>

PHP 读取单字符 - fgetc()
fgetc() 函数用于从文件中读取单个字符。下例逐字符读取 "webdictionary.txt" 文件，直到 end-of-file：
<?php
$myfile = fopen("webdictionary.txt", "r") or die("Unable to open file!");
// 输出单字符直到 end-of-file
while(!feof($myfile)) {
  echo fgetc($myfile);
}
fclose($myfile);
?>
(注：在调用 fgetc() 函数之后，文件指针会移动到下一个字符。）
```

### PHP 文件创建/写入：
```
PHP 创建文件 - fopen()
fopen() 函数也用于创建文件。也许有点混乱，但是在 PHP 中，创建文件所用的函数与打开文件的相同。
如果您用 fopen() 打开并不存在的文件，此函数会创建文件，假定文件被打开为写入（w）或增加（a）。
下面的例子创建名为 "testfile.txt" 的新文件。此文件将被创建于 PHP 代码所在的相同目录中：
$myfile = fopen("testfile.txt", "w")

PHP 写入文件 - fwrite()
fwrite() 函数用于写入文件。fwrite() 的第一个参数包含要写入的文件的文件名，第二个参数是被写的字符串。下面的例子把姓名写入名为 "newfile.txt" 的新文件中：
<?php
$myfile = fopen("newfile.txt", "w") or die("Unable to open file!");
$txt = "Bill Gates\n";
fwrite($myfile, $txt);
$txt = "Steve Jobs\n";
fwrite($myfile, $txt);
fclose($myfile);
?>
如果我们打开 "newfile.txt" 文件，它应该是这样的：
Bill Gates
Steve Jobs

PHP 覆盖（Overwriting）
如果现在 "newfile.txt" 包含了一些数据，我们可以展示在写入已有文件时发生的的事情。所有已存在的数据会被擦除并以一个新文件开始。在下面的例子中，我们打开一个已存在的文件 "newfile.txt"，并向其中写入了一些新数据：
<?php
$myfile = fopen("newfile.txt", "w") or die("Unable to open file!");
$txt = "Mickey Mouse\n";
fwrite($myfile, $txt);
$txt = "Minnie Mouse\n";
fwrite($myfile, $txt);
fclose($myfile);
?>
如果现在我们打开这个 "newfile.txt" 文件，Bill 和 Steve 都已消失，只剩下我们刚写入的数据：
Mickey Mouse
Minnie Mouse
```

### PHP 文件上传：
```
通过 PHP，可以把文件上传到服务器。

创建一个文件上传表单
允许用户从表单上传文件是非常有用的。请看下面这个供上传文件的 HTML 表单：
<html>
<body>
<form action="upload_file.php" method="post"
enctype="multipart/form-data">
<label for="file">Filename:</label>
<input type="file" name="file" id="file" /> 
<br />
<input type="submit" name="submit" value="Submit" />
</form>
</body>
</html>
请留意如下有关此表单的信息：
<form> 标签的 enctype 属性规定了在提交表单时要使用哪种内容类型。在表单需要二进制数据时，比如文件内容，请使用 "multipart/form-data"。
<input> 标签的 type="file" 属性规定了应该把输入作为文件来处理。举例来说，当在浏览器中预览时，会看到输入框旁边有一个浏览按钮。
（注：允许用户上传文件是一个巨大的安全风险。请仅仅允许可信的用户执行文件上传操作。）

创建上传脚本
"upload_file.php" 文件含有供上传文件的代码：
<?php
if ($_FILES["file"]["error"] > 0)
  {
  echo "Error: " . $_FILES["file"]["error"] . "<br />";
  }
else
  {
  echo "Upload: " . $_FILES["file"]["name"] . "<br />";
  echo "Type: " . $_FILES["file"]["type"] . "<br />";
  echo "Size: " . ($_FILES["file"]["size"] / 1024) . " Kb<br />";
  echo "Stored in: " . $_FILES["file"]["tmp_name"];
  }
?>
通过使用 PHP 的全局数组 $_FILES，你可以从客户计算机向远程服务器上传文件。第一个参数是表单的 input name，第二个下标可以是 "name", "type", "size", "tmp_name" 或 "error"。就像这样：
$_FILES["file"]["name"] - 被上传文件的名称
$_FILES["file"]["type"] - 被上传文件的类型
$_FILES["file"]["size"] - 被上传文件的大小，以字节计
$_FILES["file"]["tmp_name"] - 存储在服务器的文件的临时副本的名称
$_FILES["file"]["error"] - 由文件上传导致的错误代码
这是一种非常简单文件上传方式。基于安全方面的考虑，您应当增加有关什么用户有权上传文件的限制。
```

### PHP Cookies：
```
什么是 Cookie？
cookie 常用于识别用户。cookie 是服务器留在用户计算机中的小文件。每当相同的计算机通过浏览器请求页面时，它同时会发送 cookie。通过 PHP，您能够创建并取回 cookie 的值。

如何创建 cookie？
setcookie() 函数用于设置 cookie。（注：setcookie() 函数必须位于 <html> 标签之前。）
语法
setcookie(name, value, expire, path, domain);
在下面的例子中，我们将创建名为 "user" 的 cookie，把为它赋值 "Alex Porter"。我们也规定了此 cookie 在一小时后过期：
<?php 
setcookie("user", "Alex Porter", time()+3600);
?>
<html>
<body>
</body>
</html>
（注：在发送 cookie 时，cookie 的值会自动进行 URL 编码，在取回时进行自动解码（为防止 URL 编码，可以使用 setrawcookie() 取而代之）。）

如何取回 Cookie 的值？
PHP 的 $_COOKIE 变量用于取回 cookie 的值。在下面的例子中，我们取回了名为 "user" 的 cookie 的值，并把它显示在了页面上：
<?php
// Print a cookie
echo $_COOKIE["user"];
// A way to view all cookies
print_r($_COOKIE);
?>
在下面的例子中，我们使用 isset() 函数来确认是否已设置了 cookie：
<html>
<body>
<?php
if (isset($_COOKIE["user"]))
  echo "Welcome " . $_COOKIE["user"] . "!<br />";
else
  echo "Welcome guest!<br />";
?>
</body>
</html>

如何删除 cookie？
当删除 cookie 时，您应当使过期日期变更为过去的时间点。删除的例子：
<?php 
// set the expiration date to one hour ago
setcookie("user", "", time()-3600);
?>
```

### PHP Sessions：
```
PHP Session 变量
当您运行一个应用程序时，您会打开它，做些更改，然后关闭它。这很像一次会话。计算机清楚你是谁。它知道你何时启动应用程序，并在何时终止。但是在因特网上，存在一个问题：服务器不知道你是谁以及你做什么，这是由于 HTTP 地址不能维持状态。
通过在服务器上存储用户信息以便随后使用，PHP session 解决了这个问题（比如用户名称、购买商品等）。不过，会话信息是临时的，在用户离开网站后将被删除。如果您需要永久储存信息，可以把数据存储在数据库中。
Session 的工作机制是：为每个访问者创建一个唯一的 id (UID)，并基于这个 UID 来存储变量。UID 存储在 cookie 中，亦或通过 URL 进行传导。

开始 PHP Session
在您把用户信息存储到 PHP session 中之前，首先必须启动会话。（注：session_start() 函数必须位于 <html> 标签之前：）
<?php session_start(); ?>
<html>
<body>
</body>
</html>
上面的代码会向服务器注册用户的会话，以便您可以开始保存用户信息，同时会为用户会话分配一个 UID。

存储 Session 变量
存储和取回 session 变量的正确方法是使用 PHP $_SESSION 变量：
<?php
session_start();
$_SESSION['views']=1;
?>
<html>
<body>
<?php
echo "Pageviews=". $_SESSION['views'];            // 输出 Pageviews=1
?>
</body>
</html>

终结 Session
如果您希望删除某些 session 数据，可以使用 unset() 或 session_destroy() 函数。
unset() 函数用于释放指定的 session 变量：
<?php
unset($_SESSION['views']);
?>
您也可以通过 session_destroy() 函数彻底终结 session：
<?php
session_destroy();
?>
（注：session_destroy() 将重置 session，您将失去所有已存储的 session 数据。)
```

### PHP 发送电子邮件:
```
PHP mail() 函数
PHP mail() 函数用于从脚本中发送电子邮件。

语法
mail(to,subject,message,headers,parameters)
参数	             描述
to	        必需。规定 email 接收者。
subject	        必需。规定 email 的主题。注释：该参数不能包含任何新行字符。
message	        必需。定义要发送的消息。应使用 LF (\n) 来分隔各行。
headers	        可选。规定附加的标题，比如 From、Cc 以及 Bcc。
                应当使用 CRLF (\r\n) 分隔附加的标题。
parameters	可选。对邮件发送程序规定额外的参数。
(注：PHP 需要一个已安装且正在运行的邮件系统，以便使邮件函数可用。所用的程序通过在 php.ini 文件中的配置设置进行定义。)

PHP 简易 E-Mail
通过 PHP 发送电子邮件的最简单的方式是发送一封文本 email。在下面的例子中，我们首先声明变量($to, $subject, $message, $from, $headers)，然后我们在 mail() 函数中使用这些变量来发送了一封 e-mail：
<?php
$to = "someone@example.com";
$subject = "Test mail";
$message = "Hello! This is a simple email message.";
$from = "someonelse@example.com";
$headers = "From: $from";
mail($to,$subject,$message,$headers);
echo "Mail Sent.";
?>
```

### PHP 错误处理:
```
基本的错误处理：使用 die() 函数
第一个例子展示了一个打开文本文件的简单脚本：
<?php
$file=fopen("welcome.txt","r");
?>
如果文件不存在，您会获得类似这样的错误：
Warning: fopen(welcome.txt) [function.fopen]: failed to open stream: 
No such file or directory in C:\webfolder\test.php on line 2
为了避免用户获得类似上面的错误消息，我们在访问文件之前检测该文件是否存在：
<?php
if(!file_exists("welcome.txt"))
 {
 die("File not found");
 }
else
 {
 $file=fopen("welcome.txt","r");
 }
?>
现在，假如文件不存在，您会得到类似这样的错误消息：
File not found
比起之前的代码，上面的代码更有效，这是由于它采用了一个简单的错误处理机制在错误之后终止了脚本。不过，简单地终止脚本并不总是恰当的方式。让我们研究一下用于处理错误的备选的 PHP 函数。

创建自定义错误处理器
创建一个自定义的错误处理器非常简单。我们很简单地创建了一个专用函数，可以在 PHP 中发生错误时调用该函数。
该函数必须有能力处理至少两个参数 (error level 和 error message)，但是可以接受最多五个参数（可选的：file, line-number 以及 error context）：
语法
error_function(error_level,error_message,
error_file,error_line,error_context)
参数	                                                              描述
error_level	                                                      必需。为用户定义的错误规定错误报告级别。必须是一个值数。
                                                                      参见下面的表格：错误报告级别。
error_message	                                                      必需。为用户定义的错误规定错误消息。
error_file	                                                      可选。规定错误在其中发生的文件名。
error_line	                                                      可选。规定错误发生的行号。
error_context	                                                      可选。规定一个数组，包含了当错误发生时在用的每个变量以及它们的值。

错误报告级别
这些错误报告级别是错误处理程序旨在处理的错误的不同的类型：
值	          常量	                      描述
2	        E_WARNING	              非致命的 run-time 错误。不暂停脚本执行。
8	        E_NOTICE	              Run-time 通知。
                                              脚本发现可能有错误发生，但也可能在脚本正常运行时发生。
256	        E_USER_ERROR	              致命的用户生成的错误。这类似于程序员使用 PHP 函数 trigger_error() 设置的 E_ERROR。
512	        E_USER_WARNING	              非致命的用户生成的警告。这类似于程序员使用 PHP 函数 trigger_error() 设置的 E_WARNING。
1024	        E_USER_NOTICE	              用户生成的通知。这类似于程序员使用 PHP 函数 trigger_error() 设置的 E_NOTICE。
4096	        E_RECOVERABLE_ERROR	      可捕获的致命错误。类似 E_ERROR，但可被用户定义的处理程序捕获。(参见 set_error_handler())
8191	        E_ALL	                      所有错误和警告，除级别 E_STRICT 以外。
（在 PHP 6.0，E_STRICT 是 E_ALL 的一部分）

现在，让我们创建一个处理错误的函数：
function customError($errno, $errstr)
 { 
 echo "<b>Error:</b> [$errno] $errstr<br />";
 echo "Ending Script";
 die();
 }
上面的代码是一个简单的错误处理函数。当它被触发时，它会取得错误级别和错误消息。然后它会输出错误级别和消息，并终止脚本。
现在，我们已经创建了一个错误处理函数，我们需要确定在何时触发该函数。

Set Error Handler
PHP 的默认错误处理程序是内建的错误处理程序。我们打算把上面的函数改造为脚本运行期间的默认错误处理程序。
可以修改错误处理程序，使其仅应用到某些错误，这样脚本就可以不同的方式来处理不同的错误。不过，在本例中，我们打算针对所有错误来使用我们的自定义错误处理程序：
set_error_handler("customError");
由于我们希望我们的自定义函数来处理所有错误，set_error_handler() 仅需要一个参数，可以添加第二个参数来规定错误级别。通过尝试输出不存在的变量，来测试这个错误处理程序：
<?php
function customError($errno, $errstr)
 { 
 echo "<b>Error:</b> [$errno] $errstr";
 }
set_error_handler("customError");
echo($test);
?>
以上代码的输出应该类似这样：
Error: [8] Undefined variable: test
```

### PHP 异常处理：
```
异常（Exception）用于在指定的错误发生时改变脚本的正常流程。
Try, throw 和 catch
正确的处理程序应当包括：
Try - 使用异常的函数应该位于 "try" 代码块内。如果没有触发异常，则代码将照常继续执行。但是如果异常被触发，会抛出一个异常。
Throw - 这里规定如何触发异常。每一个 "throw" 必须对应至少一个 "catch"
Catch - "catch" 代码块会捕获异常，并创建一个包含异常信息的对象
让我们触发一个异常：
<?php
//创建可抛出一个异常的函数
function checkNum($number)
 {
 if($number>1)
  {
  throw new Exception("Value must be 1 or below");
  }
 return true;
 }
//在 "try" 代码块中触发异常
try
 {
 checkNum(2);
 //If the exception is thrown, this text will not be shown
 echo 'If you see this, the number is 1 or below';
 }
//捕获异常
catch(Exception $e)
 {
 echo 'Message: ' .$e->getMessage();
 }
?>
上面代码将获得类似这样一个错误：
Message: Value must be 1 or below 

创建一个自定义的 Exception 类
创建自定义的异常处理程序非常简单。我们简单地创建了一个专门的类，当 PHP 中发生异常时，可调用其函数。该类必须是 exception 类的一个扩展。
这个自定义的 exception 类继承了 PHP 的 exception 类的所有属性，您可向其添加自定义的函数。我们开始创建 exception 类：
<?php
class customException extends Exception
 {
 public function errorMessage()
  {
  //error message
  $errorMsg = 'Error on line '.$this->getLine().' in '.$this->getFile()
  .': <b>'.$this->getMessage().'</b> is not a valid E-Mail address';
  return $errorMsg;
  }
 }
$email = "someone@example...com";
try
 {
 //check if 
 if(filter_var($email, FILTER_VALIDATE_EMAIL) === FALSE)
  {
  //throw exception if email is not valid
  throw new customException($email);
  }
 }
catch (customException $e)
 {
 //display custom message
 echo $e->errorMessage();
 }
?>
这个新的类是旧的 exception 类的副本，外加 errorMessage() 函数。正因为它是旧类的副本，因此它从旧类继承了属性和方法，我们可以使用 exception 类的方法，比如 getLine() 、 getFile() 以及 getMessage()。

设置顶层异常处理器 （Top Level Exception Handler）
set_exception_handler() 函数可设置处理所有未捕获异常的用户定义函数。
<?php
function myException($exception)
{
echo "<b>Exception:</b> " , $exception->getMessage();
}
set_exception_handler('myException');
throw new Exception('Uncaught Exception occurred');
?>
以上代码的输出应该类似这样：
Exception: Uncaught Exception occurred
在上面的代码中，不存在 "catch" 代码块，而是触发顶层的异常处理程序。应该使用此函数来捕获所有未被捕获的异常。

异常的规则
需要进行异常处理的代码应该放入 try 代码块内，以便捕获潜在的异常。
每个 try 或 throw 代码块必须至少拥有一个对应的 catch 代码块。
使用多个 catch 代码块可以捕获不同种类的异常。
可以在 try 代码块内的 catch 代码块中再次抛出（re-thrown）异常。
简而言之：如果抛出了异常，就必须捕获它。
```

### PHP 过滤器（Filter）：
```
函数和过滤器
如需过滤变量，请使用下面的过滤器函数之一：
filter_var() - 通过一个指定的过滤器来过滤单一的变量
filter_var_array() - 通过相同的或不同的过滤器来过滤多个变量
filter_input - 获取一个输入变量，并对它进行过滤
filter_input_array - 获取多个输入变量，并通过相同的或不同的过滤器对它们进行过滤
在下面的例子中，我们用 filter_var() 函数验证了一个整数：
<?php
$int = 123;
if(!filter_var($int, FILTER_VALIDATE_INT))
 {
 echo("Integer is not valid");
 }
else
 {
 echo("Integer is valid");
 }
?>
上面的代码使用了 "FILTER_VALIDATE_INT" 过滤器来过滤变量。由于这个整数是合法的，因此代码的输出是："Integer is valid"。假如我们尝试使用一个非整数的变量，则输出是："Integer is not valid"。

Validating 和 Sanitizing
有两种过滤器：
Validating 过滤器：
用于验证用户输入
严格的格式规则（比如 URL 或 E-Mail 验证）
如果成功则返回预期的类型，如果失败则返回 FALSE
Sanitizing 过滤器：
用于允许或禁止字符串中指定的字符
无数据格式规则
始终返回字符串

过滤多个输入
表单通常由多个输入字段组成。为了避免对 filter_var 或 filter_input 重复调用，我们可以使用 filter_var_array 或 the filter_input_array 函数。
在本例中，我们使用 filter_input_array() 函数来过滤三个 GET 变量。接收到的 GET 变量是一个名字、一个年龄以及一个邮件地址：
<?php
$filters = array
 (
 "name" => array
  (
  "filter"=>FILTER_SANITIZE_STRING
  ),
 "age" => array
  (
  "filter"=>FILTER_VALIDATE_INT,
  "options"=>array
   (
   "min_range"=>1,
   "max_range"=>120
   )
  ),
 "email"=> FILTER_VALIDATE_EMAIL,
 );
$result = filter_input_array(INPUT_GET, $filters);
if (!$result["age"])
 {
 echo("Age must be a number between 1 and 120.<br />");
 }
elseif(!$result["email"])
 {
 echo("E-Mail is not valid.<br />");
 }
else
 {
 echo("User input is valid");
 }
?>
例子解释：
上面的例子有三个通过 "GET" 方法传送的输入变量 (name, age and email)
设置一个数组，其中包含了输入变量的名称，以及用于指定的输入变量的过滤器
调用 filter_input_array 函数，参数包括 GET 输入变量及刚才设置的数组
检测 $result 变量中的 "age" 和 "email" 变量是否有非法的输入。（如果存在非法输入，）
filter_input_array() 函数的第二个参数可以是数组或单一过滤器的 ID。
如果该参数是单一过滤器的 ID，那么这个指定的过滤器会过滤输入数组中所有的值。
如果该参数是一个数组，那么此数组必须遵循下面的规则：
必须是一个关联数组，其中包含的输入变量是数组的键（比如 "age" 输入变量）
此数组的值必须是过滤器的 ID ，或者是规定了过滤器、标志以及选项的数组
```

### PHP MySQL 连接数据库:
```
连接到一个 MySQL 数据库,在您能够访问并处理数据库中的数据之前，您必须创建到达数据库的连接。
在 PHP 中，这个任务通过 mysql_connect() 函数完成。
语法
mysql_connect(servername,username,password);
参数	                  描述
servername	         可选。规定要连接的服务器。默认是 "localhost:3306"。
username	         可选。规定登录所使用的用户名。默认值是拥有服务器进程的用户的名称。
password	         可选。规定登录所用的密码。默认是 ""。
(注：虽然还存在其他的参数，但上面列出了最重要的参数。)
在下面的例子中，我们在一个变量中 ($con) 存放了在脚本中供稍后使用的连接。如果连接失败，将执行 "die" 部分：
<?php
$con = mysql_connect("localhost","peter","abc123");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }
// some code
?>

关闭连接
脚本一结束，就会关闭连接。如需提前关闭连接，请使用 mysql_close() 函数。
```

### PHP Database ODBC:
```
ODBC 是一种应用程序编程接口（Application Programming Interface，API），使我们有能力连接到某个数据源（比如一个 MS Access 数据库）。
连接到 ODBC
odbc_connect() 函数用于连接到 ODBC 数据源。该函数有四个参数：数据源名、用户名、密码以及可选的指针类型参数。
odbc_exec() 函数用于执行 SQL 语句。
下面的例子创建了到达名为 northwind 的 DSN 的连接，没有用户名和密码。然后创建并执行一条 SQL 语句：
$conn=odbc_connect('northwind','','');
$sql="SELECT * FROM customers"; 
$rs=odbc_exec($conn,$sql);

取回记录
odbc_fetch_row() 函数用于从结果集中返回记录。如果能够返回行，则返回 true，否则返回 false。该函数有两个参数：ODBC 结果标识符和可选的行号：
odbc_fetch_row($rs)

从记录中取回字段
odbc_result() 函数用于从记录中读取字段。该函数有两个参数：ODBC 结果标识符和字段编号或名称。下面的代码行从记录中返回第一个字段的值：
$compname=odbc_result($rs,1); 
The code line below returns the value of a field called "CompanyName":
$compname=odbc_result($rs,"CompanyName");

关闭 ODBC 连接
odbc_close()函数用于关闭 ODBC 连接。
odbc_close($conn);

ODBC 实例
下面的例子展示了如何首先创建一个数据库连接，然后是结果集，然后在 HTML 表格中显示数据。
<html>
<body>
<?php
$conn=odbc_connect('northwind','','');
if (!$conn)
  {exit("Connection Failed: " . $conn);}
$sql="SELECT * FROM customers";
$rs=odbc_exec($conn,$sql);
if (!$rs)
  {exit("Error in SQL");}
echo "<table><tr>";
echo "<th>Companyname</th>";
echo "<th>Contactname</th></tr>";
while (odbc_fetch_row($rs))
{
  $compname=odbc_result($rs,"CompanyName");
  $conname=odbc_result($rs,"ContactName");
  echo "<tr><td>$compname</td>";
  echo "<td>$conname</td></tr>";
}
odbc_close($conn);
echo "</table>";
?>
</body>
</html>
```

### PHP 数据库:
```
其他有关 MySQL 数据库的知识请转到 “MySQL.md” 文档中中查看学习。
```

