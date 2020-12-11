| 版本号 | 修订人 | 修订         | 变更内容                             |
| --- | --- | ---------- | -------------------------------- |
| 1.0 | 杨攀  | 2019/09/20 | 1.创建初始版本                         |
| 1.1 | 杨攀  | 2019/10/17 | 1.删除重复，调整结构                      |
| 1.2 | 董剑辉 | 2019/11/20 | 1.增加直接调用合约方法的说明2.修复hvm abi插件的版本号 |



> 轻量级 Java 智能合约运行时，得益当前Java语言的流行以及其强大的生态，能够使用Java语言编写区块链智能合约无疑会使得区块链的开发更加便捷，更加易于推广。而当前HyperJVM由于其自身限制无法满足当前区块链开发多功能，便捷的需求，因此，本文提出HVM，使得智能合约更为简单，强大。

HVM提供了全新的智能合约编写方案，更加简洁安全，下文将会针对其基本特性以及相关的工具进行阐述。

## 智能合约模板

在HVM中，我们建议用户将合约组织为一个单独的项目，在合约编写完成后生成jar包和SDK交互，这样一方面让工程间的结构更加清晰，一方面使得合约能够在非java语言编写的SDK上使用。

为了方便用户编写合约，我们提供了maven工程的archetype以此来快速搭建一个合约工程项目。

### 环境配置

为了方便用户开发自己的智能合约，我们提供了一个智能合约模板，可以在maven中央仓库获取模板项目，也可以在本地配置一下公司内部的私有maven仓库。

**maven中央仓库获取方式**：

我们会不断更新仓库项目，用户只需要从maven仓库获取最版本版本的项目即可 地址：[__https://mvnrepository.com/artifact/cn.hyperchain/java-contract-archetype__](https://mvnrepository.com/artifact/cn.hyperchain/java-contract-archetype)

```javascript

<!-- https://mvnrepository.com/artifact/cn.hyperchain/java-contract-archetype -->

<dependency>

    <groupId>cn.hyperchain</groupId>

    <artifactId>java-contract-archetype</artifactId>

    <version>1.0.2</version>

</dependency>

```

公司内部仓库获取方式：

编辑maven的配置文件`$MAVEN_HOME/conf/settings.xml`，在其`<profiles><profiles/>`标签中添加

```xml

<profile>

  <id>nexus</id>

  <repositories>

    <repository>

        <id>nexus</id>

        <name>hyperchain private nexus</name>

        <url> http://cn0:8081/repository/hyperchain/</url>

    </repository>

  </repositories>

</profile>

<profile>

  <id>nexus-snapshots</id>

  <repositories>

    <repository>

        <id>nexus-snapshots</id>

        <name>hyperchain private nexus snapshots</name>

        <url> http://cn0:8081/repository/maven-snapshots/</url>

    </repository>

  </repositories>

</profile>

```

然后在`<profiles><profiles/>`标签外添加

```xml

 <activeProfiles>

     <activeProfile>nexus</activeProfile>

     <activeProfile>nexus-snapshots</activeProfile>

 </activeProfiles>

```

### 创建工程

下面介绍如果使用常用开发工具`Intellij IDEA`和`Eclipse`创建合约模板工程。

Intellij IDEA

1、打开**File**->**New**->**Project...**->**Maven**



2、输入模板坐标

```properties

GroupId = cn.hyperchain

ArtifactId = java-contract-archetype

Version = 1.0.2

```



![](http://teambitiondoc.hyperchain.cn:8099/storage/011kc21e0f9890d080ec4dd1353dbf6c52f6?Signature=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcHBJRCI6IjVkNGNlYzMwYTU1YzA5MDAwMTcxY2JkNCIsImV4cCI6MTU4Mzg5NDU2OSwiaWF0IjoxNTgzMjg5NzY5LCJyZXNvdXJjZSI6Ii9zdG9yYWdlLzAxMWtjMjFlMGY5ODkwZDA4MGVjNGRkMTM1M2RiZjZjNTJmNiIsInN0b3JhZ2UiOiJkZWZhdWx0In0.Wmbc3_XnaLD_nHZlnrTg4LN-INZeyVQUEgu4T-0BLmw&download=idea_step2.png "")



Eclipse

1、打开**File**->**New**->**Other**->**Maven**->**Maven Project**



![](http://teambitiondoc.hyperchain.cn:8099/storage/011k49d0fded07ad1783c2d8a7e8580181b2?Signature=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcHBJRCI6IjVkNGNlYzMwYTU1YzA5MDAwMTcxY2JkNCIsImV4cCI6MTU4Mzg5NDU2OSwiaWF0IjoxNTgzMjg5NzY5LCJyZXNvdXJjZSI6Ii9zdG9yYWdlLzAxMWs0OWQwZmRlZDA3YWQxNzgzYzJkOGE3ZTg1ODAxODFiMiIsInN0b3JhZ2UiOiJkZWZhdWx0In0.5l4gFHccN-FipfMxpJK6c31X22LK2KnRywryHJwC62c&download=eclipse_step1.png "")



2、输入模板坐标

```properties

GroupId = cn.hyperchain

ArtifactId = java-contract-archetype

Version = 1.0.2

```

![](http://teambitiondoc.hyperchain.cn:8099/storage/011k8fa61b911806e1c2fd2c4ce4e587de19?Signature=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcHBJRCI6IjVkNGNlYzMwYTU1YzA5MDAwMTcxY2JkNCIsImV4cCI6MTU4Mzg5NDU2OSwiaWF0IjoxNTgzMjg5NzY5LCJyZXNvdXJjZSI6Ii9zdG9yYWdlLzAxMWs4ZmE2MWI5MTE4MDZlMWMyZmQyYzRjZTRlNTg3ZGUxOSIsInN0b3JhZ2UiOiJkZWZhdWx0In0.VJ_Dd-4KY4eX1PUKlWSofMNn-0jrmjC2N5h8c8V4KqI&download=eclipse_step2.png "")



### 工程结构

根据用户的输入工程中将会自动创建对应的包结构，并存在一个示例合约SBank以及对应的交付接口。



![](http://teambitiondoc.hyperchain.cn:8099/storage/011k4b6c113d13bab95711edc36feec76ed1?Signature=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcHBJRCI6IjVkNGNlYzMwYTU1YzA5MDAwMTcxY2JkNCIsImV4cCI6MTU4Mzg5NDU2OSwiaWF0IjoxNTgzMjg5NzY5LCJyZXNvdXJjZSI6Ii9zdG9yYWdlLzAxMWs0YjZjMTEzZDEzYmFiOTU3MTFlZGMzNmZlZWM3NmVkMSIsInN0b3JhZ2UiOiJkZWZhdWx0In0.AswoqnPLhaR5nQDkxi8JFMs9UwQ70JOQZsYUw_mZrNI&download=project_structure_01.png "")



下面简单介绍一下样例代码：

- **Sbank.java**: 合约主体类。需要继承**BaseContract**父类。合约主要功能是模拟了一个银行业务，提供了存钱、取钱和转账接口。

- **ISBank.java**: 交付接口类，继承了**BaseContractInterface**。用来交付给应用程序开发者，包含了合约拥有者想要对应用程序1（假设）暴露的接口。

- **ISBank2.java**: 交付接口类，继承了**BaseContractInterface**。用来交付给应用程序开发者，包含了合约拥有者想要对应用程序2（假设）暴露的接口。

- **pom.xml**: 用户需要根据编写的合约主体类修改pom中`<main-class></main-class>`属性，值应该为**合约主体类的全限定类名**。

## HVM合约规范

在HVM中，我们提供了新的合约编写规则。我们推荐在合约中仅保留一些简单的**原子操作**，合约的业务逻辑在`invoke bean`（下面会详细介绍）中编写。

> `invoke bean`：这是HVM中提出的一个新概念，指代实现了**BaseInvoke**接口的类，其实现的**invoke**方法中包含了调用合约方法的业务逻辑。需要注意的是，`invoke bean`需要一个**空参空体**的构造函数。

这种约定有如下几个优点：

1. 能够在一个invoke bean中同时调用合约的多个方法。

1. 能够使得在不更新合约的情况下更新合约业务逻辑（通过更新**invoke**方法内的逻辑，或者实现多个用于不同业务场景的invoke bean），以此适应不断变化的业务需求。

1. 能够保证整个业务逻辑为一个事务，即整个方法要么全部执行要么全部不执行。

1. 能够在编译阶段就进行参数类型检查，而非运行时检查（与HyperJVM相比），提升了安全性。

### 合约主体类

我们约定一个继承了**BaseContract**并实现了一系列**BaseContractInterface**的子接口（合约接口）的类被称为一个合约主体类。该类内的所有实现自合约接口的方法代表合约方法，可以被外界调用。要求该合约主体类中**必须提供无参构造方法，且不建议在构造函数中写合约逻辑**，为了解决可能存在的初始化的需求，我们提供了一个钩子函数`onInit()`来代替原构造函数的作用（见下文）。



### 合约的生命周期

用户可以通过按需重写**BaseContract**中四个钩子方法`onInit()`、`onCreated()`、`onPreCommit()`和`onCommitted()`来在合约生命周期中的不同阶段加入自定代码。

合约的生命周期如下：

- `deploy`：合约部署阶段，该阶段只会在合约部署时出现一次。

- `create`：构建合约对象阶段，无论部署还是调用均有该阶段。

- `execute`：执行invoke bean阶段，该阶段仅仅在调用合约时出现。

- `commit`：持久化阶段会自动扫描合约对象所有的持久化变量并做持久化操作（即数据真正上链），该阶段仅仅在调用合约时出现。

各钩子函数的调用顺序如下：

- `部署合约`: **onInit()** -> **onCreate()** -> **onPreCommit()** -> **onCommit()** 

- `调用合约`: **onCreate()** -> **onPreCommit()** -> **onCommit()** 

> 注：上面提到的四个钩子函数除了`onInit()`外，无论是在合约部署还是合约调用的时候均会被调用。



![合约生命周期](http://git.hyperchain.cn/songyu/selflearnstudy/uploads/06d3c4402cac4f4364f6ff4d30cd6fbe/lifecycle.jpg "")

用户可以在钩子函数中添加自定义的逻辑，比如可以在`onCreate()`中添加权限检查或者非持久化变量的初始化，在`onPreCommit()`中添加执行结果的校验，在`onCommitted()`中添加日志的打印等。

### 合约调用类

调用合约需要编写一个合约调用类，且该调用类需要实现`BaseInvoke`接口，该接口的具体实现如下：

```java

// 泛型T表示返回的类型，V表示合约类

public interface BaseInvoke<T, V extends BaseContractInterface>  {

    T invoke(V obj);

}

```

实现接口的`invoke`来编写执行合约的具体逻辑，`obj`即为合约对象，其中对于该调用类必须有一个空参空体的构造函数。

以调用之前`SBank`为例，编写`BankInvoke`来进行转账操作，调用类具体示例如下：

```java

public class BankInvoke implements BaseInvoke<Boolean, ISBank> {



    /**

    * 调用参数(也可使用单个Bean)

    */

    public String from;

    public String to;

    public int value;



    // 必须提供的空参空体构造函数

    public BankInvoke(){}



    // 方便初始化参数的构造函数

    public BankInvoke(String from, String to, int value){

       this.from = from;

       this.to = to;

       this.value = value;

    }



    // 接口实现

    @Override

    public Boolean invoke(ISBank obj) {

        boolean a = obj.transfer(from, to, value);

        if(a){

            // 转账成功在进行后续操作

            obj.deposit(from,value);

        }

        return a;

    }



    // setter or getter

    public String getFrom() {return from;}

    public String getTo() {return to;}

    public int getValue() {return value;}

}



```

### 合约功能bean

合约中需要用到一些辅助的bean来帮助合约的开发，编写这些bean的时候需要注意必须提供空参构造函数，并且重写`hashCode()`函数和`equals()`函数。

例如SBank中Account账户bean的编写：

```java

public class Account {

    private String name;

    private int amount;



    @Override

    public int hashCode() {

        ...

    }



    @Override

    public boolean equals(Object o) {

        ...

    }

}

```

## HVM数据结构

hvm为使用者提供了三种数据结构，分别是hypermap，hyperlist和hypertable

### 基本类型

HVM支持Java基本的数据结构，如String，int,double,float,byte,char等等

```java

public String key;



public int n = 1;

```

### HyperMap

HyperMap是HVM内部定义的一种类型，与HashMap类似，都实现了Map接口。而与HashMap不同的是在账本的操作上，HashMap一直是以整个map对象为单位修改账本的，而HyperMap可以做到对于单个key，value的修改，只会涉及到单个key，value在账本中的操作，而不会涉及到整个map对象

HyperMap的定义如下：

```java

public class HyperMap<K, V> extends HyperCollection<K> implements Map<K, V> {

    // write cache includes update, delete and insert operations

    private HashMap<K, Node<K, V>> writeCache;



    // read cache include latest data

    private HashMap<K, V> readCache;

    ......

}

```

HyperMap的主要结构就是两个map，一个是写入map，一个是读取缓存map，我们将需要新增或者更改的数据写入到writeCache和readCache中，每次读取的时候先从readCache读取，如果缓存没有命中再从账本中获取。

使用场景推荐

HashMap适合小数据量，且变动不频繁的数据，HyperMap适合会持续增长的大数据量，一般情况下都使用HyperMap

### HyperList

HyperList是HVM内部定义的一种与ArrayList类似的类型，它是一个有序的JAVA合约账本数据集合，实现了JAVA的List接口，数据以懒加载的形式，减少了内存的使用，提高了更新账本的插入效率。

HyperList的定义如下：

```java

public class HyperList<E> extends HyperCollection<Integer> implements List<E>, Persistable {

    // dirty data

    private HashMap<Long, E> writeMap;



    // cache data

    private HashMap<Long, E> cacheMap;

    ......

}

```

他的实现原理和hyperMap类似，也是基于两个cache来实现接口功能。

我们对HashMap和HyperMap在HVM上的测试数据如下：

使用场景推荐

HashList适合小数据量，且变动不频繁的数据，HyperList适合会持续增长的大数据量，一般情况下都使用HyperList

### HyperTable

**设计目标**

HyperTable是HVM提出的第三种数据结构，其主要针对的问题有两点：

- 是为了针对复杂的Map嵌套应用场景

- 解决序列化反序列化造成的性能瓶颈

在结合之前HyperMap和HyperList两种数据结构的开发经验的基础上，HyperTable对许多可能产生歧义的场景进行了限制，这使得HyperTable的维护成本更低，使用更高效。具体的限制会在注意事项一节中提到。

**设计结构**

我们可以认为新增的 `HyperTable` 是一种**多层级表**结构，与HBase相似，具有**表、行、列簇、列**四个概念。他们之间的关系如下：**一个表可以包含多行，一行里有一或多个列簇，每个列簇里包含一或多列**，给定指定的行、列簇、列可以唯一制定一个值。

在我们的设计中，`HyperTable` 就对应表，并设计了数据结构 `Row`、`ColumnFamily` 分别对应行和列簇，由于确定行和列簇后，对每个列都可以指定对应的唯一的值，这类似Map的作用，所以我们没有另外为列设计一个数据结构。

因此，HyperTable的数据结构包含关系也如下

> **HyperTable ==> Row ==> ColumnFamily**，其中Row、ColumnFamily都是HyperTable的内部类。

**提供功能**

`HyperTable` 不仅支持自己插入、更新值，也支持通过其包含的 `Row` 对象、 `ColumnFamily` 对象插入、查询、更新值，为用户操作提供了相应的拓展性，假设一行包含多条数据（多个列簇或列），用户可以在拿到 `Row` 对象后往里插入值，而不需要通过 `HyperTable` 每次提供相同的行名插入。

此外，`HyperTable` 提供了行的迭代器，即可以通过迭代得到每一行的行名信息，迭代器由 `RowsSet` 实现，其实用方式与普通的Java集合迭代器一致，用户可以查看支持接口一章查看如何获取迭代器。

**注意事项**

1. `HyperTable` 对**行名、列簇名、列名、以及值**的插入有限制，不允许插入任何 **null** 或 **""** 字符串。

1. `HyperTable` 不允许**行名、列簇名、列名**包含 **@** 符号。

1. 在使用 `HyperTable` 的行迭代器的时候。不支持添加、删除或更新数据的操作，类似 OpenJDK 的fast-fail机制。

**HyperTable支持接口**

1. 插入值

通过指定行、列簇、列插入值。

```java

public void put(String rowName, String colFamName, String colName, String value);

```

**参数：**

- rowName：行名

- colFamName：列簇名

- colName：列名

- value：值

2. 查询值

通过指定的行、列簇、列查询值，可以查询不存在的值，会返回空。

```java

public String get(String rowName, String colFamName, String colName);

```

**参数：**

- rowName：行名

- colFamName：列簇名

- colName：列名

3. 删除值

通过指定的行、列簇、列删除值，可以删除不存在的值，不会报错。

```java

public void remove(String rowName, String colFamName, String colName);

```

**参数：**

- rowName：行名

- colFamName：列簇名

- colName：列名

4. 删除行

通过指定的行名删除行，该行名对应的行可以不存在，不会报错。

```java

public void removeRow(String rowName);

```

**参数：**

- rowName：行名

5. 查询是否包含某值

通过指定的行、列簇、列查询是否包含某值。

```java

public boolean containsKey(String rowName, String colFamName, String colName);

```

**参数：**

- rowName：行名

- colFamName：列簇名

- colName：列名

6. 查询是否包含某行

通过指定行名查询是否包含某行。

```java

public boolean containsRow(String rowName);

```

**参数：**

- rowName：行名

7. 行的迭代器

遍历行的迭代器，在迭代过程中不能修改数据。

```java

public HyperTable.RowSet rows();

```

8. 获取某行

获取一行的引用，行名参数可以任意指定，不一定是表内已经存在的行。

```java

public HyperTable.Row getRow(String rowName);

```

**参数：**

- rowName：行名

9. 获取该表名

```java

public String getName();

```

**HyperTable.Row支持接口**

> 因为行本身需要依赖于表，所以我们规定只能由 HyperTable 的 getRow() 接口获得 HyperTable.Row 对象，而不是简单地通过new构造方式构造。同样，HyperTable.Row 对象内会保存自己所属的 HyperTable 对象的一份引用。

1. 插入

通过指定列簇、列插入值，与HyperTable接口功能一致。

```java

public void put(String colFamName, String colName, String value);

```

**参数：**

- colFamName：列簇名

- colName：列名

- value：值

2. 查询值

通过指定列簇、列查询值，与HyperTable接口功能一致。

```java

public String get(String colFamName, String colName);

```

**参数：**

- colFamName：列簇名

- colName：列名

3. 删除值

通过指定列簇、列删除值，与HyperTable接口功能一致。

```java

public void remove(String colFamName, String colName);

```

**参数：**

- colFamName：列簇名

- colName：列名

4. 查询是否包含某值

通过指定列簇、列查询是否包含某值，与HyperTable接口功能一致。

```java

public boolean containsKey(String colFamName, String colName);

```

**参数：**

- colFamName：列簇名

- colName：列名

5. 查询是否包含某列簇

通过指定列簇名查询是否包含某列簇。

```java

public boolean containsColFam(String colFamName);

```

**参数：**

- colFamName：列簇名

6. 获取所属的表的引用

```java

public HyperTable getTable();

```

7. 获取某列簇

获取一列簇的引用，列簇名参数可以任意指定，不一定是行内已经存在的列簇。

```java

public HyperTable.ColumnFamily getColFam(String colFamName);

```

**参数：**

- colFamName：列簇名

8. 获取该行名

```java

public String getName();

```

**HyperTable.ColumnFamily支持接口**

>  与行同理，因为列簇本身需要依赖于行，所以我们规定只能由 HyperTable.Row 的 getColFam() 接口获得 HyperTable.ColumnFamily 对象，而不是通过 new 的构造方式构造。同样，HyperTable.ColFam 对象内会保存自己所属的 HyperTable.Row 和 HyperTable 对象的各一份引用。

1. 插入值

通过指定列插入值，与HyperTable接口功能一致。

```java

public void put(String colName, String value);

```

**参数：**

- colName：列名

- value：值

2. 查询值

通过指定列查询值，与HyperTable接口功能一致。

```java

public String get(String colName);

```

**参数：**

- colName：列名

3. 删除值

通过指定列删除值，与HyperTable接口功能一致。

```java

public void remove(String colName);

```

**参数：**

- colName：列名

4. 查询是否包含某值

通过指定列查询是否包含某值，与HyperTable接口功能一致。

```java

public boolean containsKey(String colName);

```

**参数：**

- colName：列名

5. 获取所属的行的引用

```java

public HyperTable.Row getRow();

```

6. 修改所属的行的引用

注意该新行所属的表必须与该列簇所属的表为同一张表，否则会抛出异常。

```java

public void setRow(HyperTable.Row row);

```

**参数：**

- row：新行的引用

7. 获取该列簇名

```java

public String getName();

```



### 特别说明

本版本的限制

- `HyperList`和`HyperMap`目前不支持`clear()`的调用，且两者需要配合`@StoreField`注解使用，请不要作为工具集合或者返回值来使用。

- `HyperList`和`HyperMap`的`toString()`方法并不会输出全量账本内的数据，对于`HyperList`只会最多输出下标1-10的数据，对于`HyperMap`则会输出本次调用涉及的全部KV。

- 集合类型（基于Java的`Map`接口和`Collection`接口实现的类型，包括`HyperList`和`HyperMap`）在使用`@StoreField`注解标识时，必须指定泛型，且不允许出现泛型内嵌套泛型的情况。

- 所有需要被持久化的对象（如StoreFiled注解的对象、HyperMap和HyperList里面使用的对象类型）均应该提供**无参构造方法**。

- 所有合约成员变量都应该通过属性赋值或者在声明时被初始化。

- 慎重考虑在合约执行过程中，将HyperMap和HyperList对象置为NULL，置为NULL即清空所有该变量对应的链上所有数据。

- 合约中引入的`bean`或者`DTO`等作为传输的对象，均需要提供**无参构造方法**，重写`equals()`和`hashCode()`方法，例如上述工程结构中的`Student`对象。

- `HyperTable` 对**行名、列簇名、列名、以及值**的插入有限制，不允许插入任何 **null** 或 **""** 字符串。

- `HyperTable` 不允许**行名、列簇名、列名**包含 **@** 符号。

- 在使用 `HyperTable` 的行迭代器的时候。不支持添加、删除或更新数据的操作，类似 OpenJDK 的fast-fail机制。

常见的错误

1. 如果合约中的`<main-class><main-class/>`属性和合约主类名不一致，那么hvm将会报错`java/lang/NullPointerException`。

1. 如果invoke bean中没有提供无参构造方法，那么hvm将会报错`java/lang/NoClassDefFoundError: java/io/ObjectStreamClass`

## HVM合约功能点

### 合约事件推送

类似于`Solidity`的合约事件推送，`hvm`同样为合约开发者提供了类似的功能，可以将指定的事件消息根据需要的筛选条件通过推送的形式发送给客户端。

合约基础类`BaseContract`提供了一个`event(Object data, String name, String... topics)`方法，可以提供给合约使用

- data: 指定的合约推送数据，最后会转换`json`的数据形式推送给客户端

- name: `event`的名称，必须提供一个非空的字符串来作为标识，客户端订阅的时候即为`topics`订阅数组的第一个变量

- topics: 用户可选的筛选条件，为一个字符串数组，客户端订阅的时候可在`topics`订阅数组中追加

在`hvm`合约中`event`的使用:

```java

public void eventTest() {

    Map<String, String> map = new HashMap<String, String>();

    // 发送一条event, map为数据，"topicA"为事件name，"topicB"为事件可选筛选条件

    event(map, "topicA", "topicB");

}

```

使用`SDK`的`MQ`消息推送订阅来进行精确的事件订阅

```java

MQParam blockInfoParam = new MQParam.Builder().

                addAddress(contractAddress).

                addTopics(new String[]{FunctionEncode.encodeEventTopic("topicA"), FunctionEncode.encodeEventTopic("topicB")}).

                build(TEST_ACCOUNT_JSON2, TEST_ACCPASSWORD);

```

`FunctionEncode.encodeEventTopic(String topic)`可以把字符串的`topic`转换为指定的订阅筛选类型。

具体订阅规则可以参考`SDK`使用手册，具体不在赘述。

### 跨合约调用

HVM跨合约的使用方式为：使用时初始化，在需要使用跨合约调用功能的时候，需要用户显示通过接口获取待跨合约实例。

概念说明

HVM的跨合约调用提供了两种实例，一种称为对**contract实例**的跨合约调用，一种称为对**library实例**的跨合约调用。

contract实例

对这种实例的调用，会影响到该合约账本中的状态变量。即如果在跨合约调用中被调用contract实例对应的合约状态变量被修改了，那么在持久化阶段，被调用合约中的被修改的状态变量也将被持久化。

不跨合约的场景，正常调用或者部署合约时，合约默认为contract实例。

library实例

对这种实例的调用，被调用合约是以工具类的角色存在，对被调用合约的修改最终不会在账本中体现，即所有修改均会被抛弃。

API

获取contract实例

```java

@Contract(address = "0x0")

CrossCall contractCall = new CrossCall();



public final <T extends BaseContractInterface> T getCrossContract(); 

```

- 【所属类】：是**CrossCall**提供的方法，可以在合约主体类中直接调用。

- 【参数】：注解中的address即为合约地址。

- 【返回】：被调用合约的**contract**实例。

- 【说明】：要求该合约地址对应的合约主体类必须实现**BaseContractInterface**接口，可以使用对应合约接口类型变量进行接收。

获取library实例

```java

@Library(address = "0x0")

CrossCall libraryCall = new CrossCall();



public final <T extends BaseContractInterface> T getCrossContract();

```

- 【所属类】：是**CrossCall**提供的方法，可以在合约主体类中直接调用。

- 【参数】：注解中的address即为合约地址。

- 【返回】：被调用合约的**library**实例。

- 【说明】：要求该合约地址对应的合约主体类必须实现**BaseContractInterface**接口，可以使用对应合约接口类型变量进行接收。

> 注意：使用@Contract注解则将会返回一个对修改集尝试影响的合约对象，@Library注解则将返回一个对修改集不产生影响的对象

注意事项

下面是一些在跨合约中需要注意的地方，或者是建议的用法。

同全限定名类

在一个调用中涉及到的所有的合约中不能出现：使用全限定类名相同(即包名和类名都相同)，但是具体实现不同的类。

若出现，将会得到如下异常：

```none

java.lang.RuntimeException: found a duplicate class: cn.hyperchain.Person, please change its package name or class name

```

跨合约调用链

现在限制跨合约只能调用一层，无论跨合约调用的是否是`Contract`或者`Library`，例如A->B，如果B合约中又存在跨合约行为，则会报错。

合约生命周期钩子方法

1. 在每次获取contract实例（library实例）时，均会触发contract实例（library实例）的onCreated钩子函数，即使该实例曾经已经被获取过。

1. 合约可以通过在**onCreated**钩子函数中，通过检查**sender**和**origin**地址，来进行权限控制，若想要终止对自己跨合约调用只需要在**onCreated**钩子函数中抛出**RuntimeException**即可。

> 合约可以通过**getSender()**获取到本次对自己的直接调用者(用户或者合约)的地址，可以通过**getOrigin()**方法获取到调用链的起点(必然是用户)的地址。

1. 因为library实例的变更最终不会被持久化到账本，所以library实例的**onPreCommit**和**onCommited**钩子函数不会被触发。

1. **onCreated**的执行顺序与合约执行顺序相同。**onPreCommit**和**onCommited**钩子方法的调用顺序与合约执行顺序相反。比如：有调用链A->B，那么**onCreated**的调用顺序为A->B，而**onPreCommit**和**onCommited**的调用顺序为B->A。

### 直接调用合约方法

HVM现在支持**不通过invoke bean就直接调用合约方法**，平台会查看交易封装的payload参数，检查前四个字节是不是魔数“fefffbce”，如果是，则表示该交易直接调用合约方法。

直接调用合约方法参数封装形式如下：

| fefffbce | method_name_len(2 bytes) | method_name(method_name_len bytes) | clz_name_len(2 bytes) | param_len(4 bytes) | clz_name(clz_name_len bytes) | param (param_len bytes)...

### 合约成员变量

该节中主要介绍合约的内置变量以及用户自定义成员变量（包含持久化变量和非持久化变量）。

合约内置变量

合约内部提供了让用户获取当前交易的哈希和合约调用者地址的接口。

- `sender`：合约方法调用者的地址（当且仅当存在跨合约调用时值会与orgin不同），通过**getSender()**方法获得。

- `txHash`：当前交易的哈希，通过**getTxHash()**方法获得。

- `origin`：合约调用者的地址，通过**getOrigin()**方法获得。

- `blockTimestamp`：交易所在区块的时间戳，通过**getBlockTimestamp()**方法获得。

- `blockNumber`：交易所在区块，通过**getBlockNumber()**方法获得。

未来还会提供更多的内置变量。

### 合约的内置变量的操作

得到和设置Sender

得到sender：

返回值: 合约的sender

`public final String getSender()`

设置Sender：

- 【sender】: hex字符串表示的sender

`public void setSender(String sender)`

得到和设置Origin

得到origin：

返回值: 合约的orgin

`public final String getOrigin()`

设置origin：

- 【orgin】: hex字符串表示的orgin

`public void setOrigin(String origin)`

得到和设置交易哈希

得到TxHash：

返回值: 合约的TxHash

`public final String getTxHash()`

设置TxHash：

- 【txHash】: hex字符串表示的交易哈希

`public void setTxHash(String txHash)`

得到和设置block的时间戳

得到时间戳：

返回值: 合约的时间戳

`public final long getBlockTimestamp()`

设置时间戳：

- 【blockTimestamp】: long表示的时间戳

`public void setBlockTimestamp(long blockTimestamp)`

得到和设置BlockNumber

得到BlockNumber：

返回值: 合约的BlockNumber

`public final long getBlockNumber()`

设置BlockNumber：

- 【blockNumber】: long表示的BlockNumber

`public void setBlockNumber(long blockNumber)`

得到和设置合约地址

得到合约地址

返回值: 合约的合约地址

`public String getContractAddress()`

设置合约地址

- 【contractAddress】: hex字符串表示的合约地址

`public void setContractAddress(String contractAddress)`

合约持久化变量

合约持久化变量指的是在合约中增加了`@StoreField`注解的成员变量，例如：

```java

@StoreField

public String name = "sbank";



@StoreField

public HyperMap<String, Integer> accounts = new HyperMap<>();

```

持久化变量会在合约执行前从Ledger读取最新数据依赖注入进合约相应变量，同时在执行后自动向Ledger写入修改的脏数据，读取和持久化的过程对用户透明。

**同时，针对区块链场景，我们实现了三个自定义的数据集合：HyperMap和HyperList。顾名思义，即一个Map和List的自定义实现，在合约中需要使用与账本交互的集合时使用HVM提供的两个数据集合，可以做到比普通集合在大数据量时有更好的效率。**



> 注意：HyperMap和HyperList作为持久化成员变量，必须在声明时就初始化，不可以在构造函数或者钩子函数中初始化；集合内用户自定义的泛型类型不可以在带有泛型类型，例如HyperMap<String, Map<String, Integer>>是不允许的；不要把HyperMap和HyperList作为非StoreField来使用（比如局部变量，返回值，函数入参等等），只在需要与账本交互时使用它们。

合约非持久化变量

合约中非`@StoreField`的变量，例如：

```java

public String key;



public int n = 1;

```

该类型变量若不在合约成员变量声明时初始化，则在每次调用前都会修改为改类型数据的零值，同时在合约执行完成后，也不会将数据写入Ledger，不过可以通过hook `onCreated()`来改变非持久化类型的值，提供给在合约调用时使用。

```java

@Override

public void onCreated() {

    this.key = "key";

    this.n = 2;

}

```

### 对合约extra参数的操作

extra字段记录了链上额外的存证信息，是一个json字符串,想要获取存证信息需要调用getValueFromExtraInTx函数

```java

public <T> T getValueFromExtraInTx(Class<T> clazz, String txHash, String path)

```

其中：

- 【clazz】: 需要将extra转成的class

- 【txHash】: 要获取哪个交易哈希里面的extra字段

- 【path】: 对得到的extra字段进行过滤

返回值：从extra字段种得到的class实例

其中path是一系列由`.`分隔的key组成的字符串。

在key中可以包含一些特殊的通配符比如`*`和`?`。如果要访问数组的指定元素，使用下标作为key。要获取数组的长度或者要基于数组添加子path，可以使用`#`。

你可以对一个数组使用条件查询，`#[...]`将返回第一个匹配元素，`#[...]#`将返回所有匹配元素，其中查询条件支持比较运算符：`==`， `!=`， `<`，`<=`， `>`， `>=`，还有一些简单的模式匹配：`%`（like）和`!%`（not like）。

注意：`.`和`?`和`*`可以通过`\`转义成为一般字符。

实例

```none

{"name": {"first": "Tom", "last": "Anderson"},"age":37,"children": ["Sara","Alex","Jack"],"fav.movie": "Deer Hunter","friends": [{"first": "Dale", "last": "Murphy", "age": 44},{"first": "Roger", "last": "Craig", "age": 68},{"first": "Jane", "last": "Murphy", "age": 47}]}

```

| Path                               | Result                  | 说明                                          |
| ---------------------------------- | ----------------------- | ------------------------------------------- |
| “name.last”                        | “Anderson”              | name的last属性                                 |
| “age”                              | 37                      | age属性                                       |
| “children”                         | [“Sara”,“Alex”,“Jack”]  | children属性                                  |
| “children.#”                       | 3                       | children数组的长度                               |
| “children.1”                       | “Alex”                  | children数组的下标为1的元素                          |
| "child*.2"                         | “Jack”                  | 匹配模式为"child*"的元素的下标为2的元素                    |
| “c?ildren.0”                       | “Sara”                  | 匹配模式为“c?ildren”的元素的下标为0的元素                  |
| “fav.movie”                        | “Deer Hunter”           | 转义.，“fav.movie”属性                           |
| “friends.#.first”                  | [“Dale”,“Roger”,“Jane”] | friends数组内每个元素的first属性                      |
| “friends.1.last”                   | “Craig”                 | friends数组的第2个元素的last属性                      |
| “friends.#[last=="Murphy"].first”  | “Dale”                  | friends数组中第一个满足last属性等于“Murphy”的元素的first属性  |
| “friends.#[last=="Murphy"]#.first” | [“Dale”,“Jane”]         | friends数组中所有满足last属性等于“Murphy”的元素的first属性   |
| “friends.#[age>45]#.last”          | [“Craig”,“Murphy”]      | friends数组中所有满足age属性大于45的元素的last属性           |
| “friends.#[first%"D*"].last”       | “Murphy”                | friends数组中第一个满足：first属性符合"D*"模式，的元素的last属性  |
| “friends.#[first!%"D*"].last”      | “Craig”                 | friends数组中第一个不满足：first属性符合"D*"模式，的元素的last属性 |

我们也提供了另外两个重载方法：

```java

 getValueFromExtra(String path)

```

 默认clazz是String类，txHash是current，即当前hash

```java

getValueFromExtra(String txHash, String path)

```

默认clazz是String类

## 合约工具方法

### ByteUtil

byte和hex互转

byte[]转hex

- 【input】: 需要转成hex的byte数组

`public static String bytesToHex(byte[] input)`

函数的返回值为hex String

hex转byte[]

- 【s】: 需要转成byte数组的hex String

`public static byte[] fromHexString(String s)`

函数的返回值为对应的byte数组

byte和int互转

byte[]转int

将用byte[]表示的int转成int类型，注意byte[]的length不能超过4（因为int是4个字节表示的）

- 【bytes】: 需要转成int的byte数组

`public static int bytesToInt(byte[] bytes)`

函数的返回值为对应的int值

int转byte[]

- 【value】: 需要转成byte[]的int

`public static byte[] intToBytes(int value)`

函数的返回值为对应的byte数组

### CryptoUtil

验签

通过公钥、原文和签名内容来验证签名内容。

ECDSA账户验签

- 【addr】:账户地址

- 【origin】:签名原文

- 【signature】:签名串

函数的返回值为boolean类型，表示验签是否成功

`public static native boolean verifySignature(byte[] addr, byte[] origin, byte[] signature);`

sm国密账户验签

- 【pubKey】:公钥

- 【origin】:签名原文

- 【signature】:签名串

函数的返回值为boolean类型，表示验签是否成功

`public static native boolean verifySM2Signature(byte[] pubKey, byte[] origin, byte[] signature);`

对称加密

通过对称密钥对消息进行加密

SM4 加密

- 【key】:对称加密密钥

- 【src】: 需要被加密的内容

返回值： 被加密的密文

`public static byte[] sm4Decrypt(byte[] key, byte[] src)`

注意SM4的key必须是16 bytes

SM4 解密

- 【key】:对称加密密钥

- 【src】: 需要被解密的内容

返回值： 被解密的原文

`public static byte[] sm4Decrypt(byte[] key, byte[] src)`

注意SM4的key必须是16 bytes

AES 加密

- 【key】:对称加密密钥

- 【src】: 需要被加密的内容

返回值： 被加密的密文

`public static byte[] aesEncrypt(byte[] key, byte[] src)`

注意AES的key必须是32 bytes

AES 解密

- 【key】:对称加密密钥

- 【src】: 需要被解密的内容

返回值： 被解密的原文

`public static byte[] aesDecrypt(byte[] key, byte[] src)`

注意AES的key必须是32 bytes

tripleDES 加密

- 【key】:对称加密密钥

- 【src】: 需要被加密的内容

返回值： 被加密的密文

`public static byte[] tripleDESEncrypt(byte[] key, byte[] src)`

注意tripleDES的key必须是24 bytes

tripleDES 解密

- 【key】:对称加密密钥

- 【src】: 需要被解密的内容

返回值： 被解密的原文

`public static byte[] tripleDESDecrypt(byte[] key, byte[] src)`

注意tripleDES的key必须是24 bytes

### ObjectsUtil

判断Object是否相同

- 【x】: Java Object

- 【y】: Java Object

返回值为boolean，表示两个object是否为同一个object

`public static boolean equals(final Object x, final Object y)`

计算多个Object的Hash值

- 【values】: 要计算的多个objests

返回值为多个object的hash值（int）

`public static int hash(final Object... values)`

### Logger方法

Logger类提供了打印对应classlog信息的功能，需要通过其构造器为



`Logger(Class clazz)`

我们需要传入对应的class来初始化logger

打印log信息

hvm提供了6种级别的log记录，分别是critical，error，warning，notice，info，debug

其对应的函数声明分别如下

【message】: 打印log的message

- 【message】: 打印log的message

- public static Logger getLogger(Class clazz)

项目配置

在按照上文配置好私有Maven仓库之后，请在应用项目中添加带有hvm接口的sdk依赖：

```xml

<groupId>cn.hyperchain.javasdk</groupId>

<artifactId>hyperchainsdk</artifactId>

<version>4.2.6</version>

```

即可使用hyperchain Java(HVM) SDK, 我们会不断进行更新维护。

## HVM合约使用

下面以JavaSDK为例来进行Java合约的部署和调用, 使用方式同传统的hyperchain SDK使用方式相同。

### 部署合约

将合约项目打成jar包后，通过使用JavaSDK的`Utils.encodeDeployJar(String jarPath)`将jar包读取为`Transaction`的`payload`后进行合约部署。使用SDK部署的示例如下：

```java

// 读取jar为payload

String payload = Utils.encodeDeployJar("sbank-1.0-SNAPSHOT.jar");

// 包装Transaction

Transaction tx = new Transaction(accountAddr, payload, false, VMType.HVM);

tx.sign(accountJSON, password);

// 部署

ReceiptReturn ret = hyperchainAPI.deployContract(tx);

if (ret.isSuccess()) {

    System.out.println(ret.getContractAddress());

    System.out.println(FunctionDecode.resultDecodeJava(receiptReturn.getRet(), String.class));

    System.out.println("deploy success");

} else {

    System.out.println(ret.getError());

}

```

### 调用合约(InvokeBean形式)

调用合约new一个调用类InvokeBean，将需要的参数赋值后，使用JavaSDK的`Utils.encodeInvokeBeanJava（BaseInvoke bean）`将调用类转换为`Transaction`的`payload`后进行合约调用。使用SDK调用的示例如下：

```java

// 初始化InvokeBean

BankInvoke bankInvoke = new BankInvoke("from", "to", 10);

// 包装Transaction

Transaction transaction = new Transaction(accountAddr, "contractAddress", bankInvoke, false, VMType.HVM);

transaction.sign(accountJSON, password);

// 调用

ReceiptReturn receiptReturn = hyperchainAPI.invokeContract(transaction);

if (receiptReturn.isSuccess()) {

    System.out.println("invoke success");

    System.out.println(FunctionDecode.resultDecodeJava(receiptReturn.getRet(), String.class));

} else {

    System.out.println(receiptReturn.getError());

}

```

### 调用合约（直接调用方式）

直接调用方式是一种拼接函数名称和参数进行调用的形式，对于单个函数的简单调用，省去了编写invokebean的过程，进而加快编码

```none

        //new一个新的ParamBuilder，并把需要调用的函数名称作为结构体的参数传入

        ParamBuilder pb = new ParamBuilder("transfer");

        //然后根据函数参数的类型以及顺序依次通过调用add<typename>方法加入函数调用参数,最后通过build封装成payload

        //这里我们让AAA给BBB转账10

        String func = pb.addString("AAA").addString("BBB").addint(10).build();

        Transaction transaction2 = new Transaction(account.getAddress(), contractAddress, func, false, VMType.HVM);

        transaction2.signWithSM2(json);

        ReceiptReturn receiptReturn2 = hyperchainAPI.invokeContract(transaction2);

        if (receiptReturn2.isSuccess()) {

            System.out.println("[invoke] 调用成功");

            System.out.println("交易Hash: " + receiptReturn2.getTxHash());

            System.out.println("the result is: " + FunctionDecode.resultDecodeJava(receiptReturn2.getRet(), Boolean.class));

        } else {

            System.out.println("[deploy] 调用失败 , 错误原因:");

            System.out.println(receiptReturn2.getError());

            System.exit(0);

        }

​

    }

```

注意事项：直接调用函数参数类型必须与合约接口里面声明的类型一致，否则会出现找到不函数定义的错误。

不支持参数的自动拆箱和装箱， 比如Integer 和int不能混用。

### 升级合约

升级合约就是用新的HVM合约替换掉旧的HVM合约，在这个过程中应当保证调用旧合约的代码仍可以使用， 即要保证合约的后向兼容性。

升级合约代码：

```java



 String path = "/Users/songyu/Desktop/selflearnstudy/javacontract/target/hvm222-1.jar";



        bin = Utils.encodeDeployJar(path);



        Transaction transactionw = new Transaction(account.getAddress(),contractAddress , bin, 1, VMType.HVM);



        transactionw.signWithSM2(accountJson);



        ReceiptReturn invokeReceipt= hyperchain.maintainContract(transactionw);



        if (! invokeReceipt.isSuccess()) {



            log("调用错误");



            log(invokeReceipt.getError());



        }



```

其中path是新合约的路径

1. 在升级合约的时候需要用Utils.encodeDeployJar(path)函数来获得新合约的bin，不能用其他函数。

1. 升级合约的transaction的第二个参数为合约的部署地址contractAddress

1. 调用maintainContract来升级合约

HVM升级合约注意事项

在升级合约的时候，新合约可以增加新的成员变量，但不可把删减旧合约的成员变量，也不能改变旧合约的变量名称。

即新合约必须包含旧合约的类成员变量，且变量名称不能改变。

以下场景是被允许的：

1. 新合约增加了新的成员变量

1. 新合约增加了新的函数

1. 新合约删除和修改了旧合约的函数

1. 如果类是enum，enum可以新增新的常量，但不可以删除已有的常量

1. 类里面引用的其他类可以增加成员变量

1. 数组或者hypermap，hyperlist里面的类增加成员变量

以下场景是不被允许的：

1. 新合约删除了旧合约的成员变量

1. 新合约修改了旧合约成员变量的名字

1. enum减少常量

1. 类里面引用的其他类减少成员变量

1. 数组或者hypermap，hyperlist里面的类减少成员变量

## HVM示例工程

### 安装

为了更快更方便的使用HVM合约，你可以通过 *HVMD* 来对 *HVM* 的功能进行评估, *HVMD* 是 *HVM* 的模拟工具,其行为同 *hyperchain* 执行结果一致。

Docker (推荐)

*HVMD* 的安装是十分简单的,你可以通过 *Docker* 进行安装，我们也推荐您使用 *Docker* 进行安装。

```none

docker pull docker:5000/hyperchain/hvmd:1.0.3-dev

```

二进制安装

如果你想通过二进制安装包 *HVMD* 进行安装，你可以通过下面链接进行下载:

- [Linux x86_64](http://maven.internal.hyperchain.cn/repository/hvm-internal/hvmd-1.0.3-dev-alpha.tar.gz)

- [MacOS darwin x86_64](http://maven.internal.hyperchain.cn/repository/hvm-internal/hvmd-1.0.3-dev-alpha-mac.tar.gz)

### 启动和停止Daemon

如果你已经将 *HVMD* 安装完毕，那么你可以通过如下命令运行:

启动

**Docker:**

```bash

docker run -d --name=hvmd -p 8011:9999 docker:5000/hyperchain/hvmd:{tag}

```

**Binary:**

```bash

cd /path/to/hvmd/

./hvmd

```

停止

如果你需要将 *HVMD* Daemon 停止，请用如下命令:

**Docker:**

```bash

docker stop hvmd

```

**Binary:**

```none

ctrl-c

```

### 智能合约

HVM提供了全新的智能合约编写方案，更加简洁安全，下文将会针对其基本特性以及相关的工具进行阐述。

使用模板

在HVM中，我们建议用户将合约组织为一个单独的项目，在合约编写完成后生成jar包和SDK交互，这样一方面让工程间的结构更加清晰，一方面使得合约能够在非java语言编写的SDK上使用。

为了方便用户编写合约，我们提供了maven工程的archetype以此来快速搭建一个合约工程项目。

环境配置

由于目前内测版本中，合约的模板并没有发布到开放中心仓库，故需要在本地配置一下公司内部的私有maven仓库。

编辑maven的配置文件`$MAVEN_HOME/conf/settings.xml`，在其`<profiles><profiles/>`标签中添加

```xml

<profile>

  <id>nexus</id>

  <repositories>

    <repository>

        <id>nexus</id>

        <name>hyperchain private nexus</name>

        <url> http://cn0:8081/repository/hyperchain/</url>

    </repository>

  </repositories>

</profile>

<profile>

  <id>nexus-snapshots</id>

  <repositories>

    <repository>

        <id>nexus-snapshots</id>

        <name>hyperchain private nexus snapshots</name>

        <url> http://cn0:8081/repository/maven-snapshots/</url>

    </repository>

  </repositories>

</profile>

```

然后在`<profiles><profiles/>`标签外添加

```xml

 <activeProfiles>

     <activeProfile>nexus</activeProfile>

     <activeProfile>nexus-snapshots</activeProfile>

 </activeProfiles>

```

### 工程实例

本节将以一个模板工程为例介绍工程中的使用场景。

创建工程

根据archetype创建工程（过程见智能合约.使用模板.创建工程）

```properties

GroupId = cn.hyperchain

ArtifactId = hvmbasic-archetype

Version = 1.0.3

```

工程结构



![工程结构](http://git.hyperchain.cn/songyu/selflearnstudy/uploads/39cb3a624ef1e7e6cab4bf7a386fe61e/application_structure_01.png "")

下面将讲解和hvm相关的包和类。

> 注：以下将用`${package}`来代表外层用户自定义的包结构，在本例中`${package} = com.test`

- `${package}.app.contract`：里面包含了用户编写的合约代码（包含合约和invoke bean）。

其中的`student/logic`和`student/invoke`内分别放对应合约Student和Student合约的invoke bean。

运行

配置：运行前编译合约



![application setup](http://git.hyperchain.cn/songyu/selflearnstudy/uploads/97e2b87157560c4286def603aa452d1d/application_setup01.png "")



![application setup](http://git.hyperchain.cn/songyu/selflearnstudy/uploads/c95472efdaa1b4262d0961436bea0087/application_setup02.png "")

使用swagger

进入url：[http://localhost:9000/swagger-ui.html](http://localhost:9000/swagger-ui.html)

