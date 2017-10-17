# ThoughtWorks 2018校园招聘作业题目——出租车


### 作业情况简介：

> Java 版本：JDK 1.8  
> IDE: IntelliJ IDEA  
> 操作系统： Mac OS Sierra  
> 依赖管理：Maven  
> 版本控制：Git
> 测试：JUnit 4.12 

### How to Run

> **注意** 
> 本项目为方便输入  
> 使用了System.setIn重定向输入流到文件中  
> 测试数据写在testCase1.txt或 testCase2.txt中  
> 如需从控制台手动输入，需要取消该重定向.该代码在：  
> **com.thoughtworks.taxi.util.FormatInput.readInput(fileName)** 19行

> 1. 使用IDEA或Eclipse导入项目
> 2. 根据提示导入maven依赖，或者使用命令行进入项目根目录，执行： mvn install
> 3. build
> 4. 选择 package com.thoughtworks.taxi.main.hanleIO.main(String args[]) -> run 
> 5. 测试：一共25个测试用例，每个Java类对应的测试用例在相应的包中，测试Suite在 package com.thoughtworks.taxi.main.TestSuit中
> 6. 25个测试全部通过，表示环境正常，运行通过
> 7. 可能的Exception：  
>   FileNotFoundException: 请根据提示的文件名（如testCase1.txt）在项目根目录下确认该文件是否存在

### 核心算法介绍

#### 1.计算报废车辆信息：

##### 算法相关代码：

> com.thoughtworks.taxi.core -- Calculate.class -- isWriteOff(Car car)  
> test：com.thoughtworks.taxi.core -- CalculateTest.class  
		
		testWriteOff(); testWriteOff2();testWriteOff2()

##### 算法步骤：
  
> 1. 计算计算车辆是否经过大修，从而调整报废年限 —— writeOffYears
> 2. 计算车辆购买日期至提交日期的天数——boughtDays
> 3. 计算车辆报废日期 —— writeOffDate
> 3. 如果boughtDays >= writeOffYears * 365， 则车辆已报废，不作提醒
> 4. 设置常量：两个月能达到的最大天数（62） —— maxMonthDays;开始提醒保修的提前时间（1个月）—— nearToFixMonthNum  
> 5. 计算提醒报废的条件：
    
    boughtDays >= writeOffDays - maxMonthDays && monthSplit <= nearToFixMonthNum && monthSplit >=0
    
>    即 同时满足 **报废日期距离提交日期还剩一个月内** 和 **月份的数字相差不超过 1**，极限情况为 8月31日和 7月1日，此时相差 62天，但依然该提醒
> 6. 不满足上述条件的车辆标记为New,进入按里程数提醒保修的计算

#### 2. 计算需要提醒每一万公里保养的车辆

##### 算法相关代码：

> com.thoughtworks.taxi.core -- Calculate.class -- isDistanceRelated(Car car）   
> test：com.thoughtworks.taxi.core -- CalculateTest.class
 
    testIsDistanceRelated()；testIsDistanceRelated2()  
    
##### 算法步骤：

> 在算法1中被标记为New的车辆，进入此次计算
  
> 1. 设置常量：里程数(10000) —— distanceKilos， 开始提醒的里程数(500) ——(distanceRemindBegin)

> 2. 计算提醒条件：

	(car.getKilos() % distanceKilos) == 0 || distanceKilos - (car.getKilos() % distanceKilos) <= distanceRemindBegin
	
> 即满足汽车里程数为 10000的整数倍或者对10000取余后，余数在 9500 - 10000之间
> 不满足条件的汽车返回 false,进入按时间提醒保修的计算

#### 3. 计算需要定期保养的车辆

##### 算法相关代码：

> com.thoughtworks.taxi.core -- Calculate.class -- isTimeRelated(Car car)  
> test: com.thoughtworks.taxi.core -- CalculateTest.class

		testIsTimeRelated();testIsTimeRelated2();

##### 算法步骤：

> 1. 计算汽车购买年限，根据年限调整保修周期——fixSplit

> 2. 忽略掉具体日期之后计算两个日期间的月份差距 —— monthSplit

> 3. 计算公式：

	(monthSplit % fixSplit) <= 1
   
> 即 monthSplit 对 fixSplit取余，余数表示距离下一个维修周期还剩的月数

