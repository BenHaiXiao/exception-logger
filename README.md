# Error Log的输出控制器
----

## ExceptionLogger用法

	// 声明slf4j
	private static Logger logger = LoggerFactory.getLogger(XxxClass.class);

	// 声明控制策略器
	Strategy strategy = xxxx;
	// 声明日志输出控制器
    ExceptionLogger exceptionLogger = new ExceptionLogger(strategy);

	public void method() {
		// 通常需要计算方法的总调用次数，用于计算出错的比例等等
		exceptionLogger.addUpTotal();

		// do something
		
		try {
			// do something there will throw some exceptions
		} catch (Exception e) {
			// 采用slf4j的参数格式，只是需把slf4j的实例传进去
			exceptionLogger.error(logger, "msg format", arg);
		}
	}

## 控制策略器

### FrequencyStrategy

频率策略。一定时间内（单位：s）超过一定的数量就触发。

### RatioStrategy

比例策略。一定时间内（单位：s）超过一定的比例就触发。 

**注意：** 这个比例数值依赖于调用`addUpTotal`方法。

### PeriodStrategy

周期数量策略。一定调用周期（次数）之内，超过一定error数量时触发。

**注意：** 这个周期数量数值依赖于调用`addUpTotal`方法。

### DayIncrementStrategy

按天增量策略。按天统计达到一定增量触发。

### AndStrategy

逻辑且策略。用于组合两个基础策略。

### OrStrategy

逻辑或策略。用于组合两个基础策略。

### NotStrategy

逻辑非策略。用于反转一个基础策略。

### NoneStrategy

无行为的策略。NullObject设计模式。overflow永远返回`true`。

## 工具类

为ExceptionLogger和Strategy提供一个桥梁，提供一个key到ExceptionLogger的映射。

### FrequencyLoggerFactory

ExceptionLogger + FrequencyStrategy。

### RatioLoggerFactory

ExceptionLogger + RatioStrategy。

### PeriodLoggerFactory

ExceptionLogger + PeriodStrategy。

### DayIncrementLoggerFactory

ExceptionLogger + DayIncrementStrategy。

### FrequencyRatioLoggerFactory

ExceptionLogger + （FrequencyStrategy and/or RatioStrategy）。

### FrequencyPeriodLoggerFactory

ExceptionLogger + （FrequencyStrategy and/or PeriodStrategy）。

