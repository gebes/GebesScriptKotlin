# GebesScript
 Simple Script with Kotlin as Interpreter
 
 ## HelloWorld Example
 There a tons of examples in the ./scripts/ folder too
 ```
 main
  println
   Hello World!
 ```

 ## Execute a script
 Simply execute the jar and it will look up for .gebes files in a scripts folder (which will be automatically created).
 It's important that you execute the .jar in the console/terminal
 
 ### Launch arguments
 If the argument is the path, then it will execute this script in chronological order.
 If you don't want the "Executing script..." message than add `--no-prefix`.  
 
 Example:
 ```
    java -jar GebesScript-1.x.jar ./scripts/hello.gebes --no-prefix
 ``` 

 If there are no arguments, it will then redirect you to the selection screen.