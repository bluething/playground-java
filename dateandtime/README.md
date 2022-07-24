### Date

The class Date represents a specific instant in time, with millisecond precision since the 1st of January 1970 00:00:00 GMT (the epoch time).  
This class are deprecated now.

The problems with Date class:  
- Thread safety, because this class is mutable.  
- It doesn't handle all dates very well. Technically, it should reflect coordinated universal time (UTC). However, that depends on an operating system of the host environment.  
- Don't have rich function like get duration of period, add time, etc.  
- Many deprecated constructors and functions.

### Calendar

As a helper for Date class. We can manipulate date and time with Calendar class (similar method in Date class deprecated).  
This class don't hava public constructor.