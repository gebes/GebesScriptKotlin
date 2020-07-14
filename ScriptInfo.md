# ScriptInfo
This Info was generated with the Version v1.3
 
## About
This file auto generates asynchronously everytime a script gets executed.  
Here you will find a helpful overview of all commands you can use

## Commands
### printer
Modifies how and how fast something gets printed to the console
### print
Prints the parameter and arguments to the console as you parsed them
#### Example 1  

```  
# Prints:
# Hello World
main
 print Hello World
```
#### Example 2  

```  
# Prints:
# Hello World
main
 print
  Hello World
```
#### Example 3  

```  
# Prints:
# Title
# Description
main
 print Title
  Description
```
### println
Prints the parameter and arguments to the console with an extra linebreak at the end
#### Example 1  

```  
# Prints:
# Hello World
#
main
 println Hello World
```
#### Example 2  

```  
# Prints:
# Hello World
#
main
 print
  Hello World
```
#### Example 3  

```  
# Prints:
# Title
# Description
#
main
 print Title
  Description
```
### call
Call multiple functions once or multiple times
#### Example 1  

```  
func
 println
  Func called

# prints "Func called" once
main
 call once
  func
```
#### Example 2  

```  
func
 println
  Func called

# prints "Func called" once
main
 call and repeat once
  func
```
#### Example 3  

```  
func
 println
  Func called

# prints "Func called" forever
main
 call forever
  func
```
#### Example 4  

```  
func
 println
  Func called

# prints "Func called" forever
main
 call and repeat forever
  func
```
#### Example 5  

```  
func
 println
  Func called

# prints "Func called" 3 times
main
 call 3 times
  func
```
### wait
Waits for n seconds
#### Example 1  

```  
main
 wait for enter
 println Waited for user input
```
#### Example 2  

```  
main
 wait 0.5
 println Waited for half a second
```
#### Example 3  

```  
main
 wait 1
 println Waited for a second
```
### clear
Clears the console screen
#### Example 1  

```  
main
 println Message you wont be able to read
 clear
 note This clears the first message instantly
 println Message you will be able to read
```
### var
Set the value of a variable
### scan
Scan input from the console and set it to a specific variable
### if
Compares values and calls methods if true
### note
Side notes which can be printed
