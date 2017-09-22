<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
In order to solve problems related to the Language Objective-C, such as the lack of resources, we propose and implement in this paper a definition of a new high level language. 
This Language is focused on structuring and organizing the source code in order to increase productivity and safety in the implementation of an application. 
Also, this Language have a syntax similar to the Java Language in order to make its learning more practical and follows completely the Object Oriented paradigm.
</p>

<p>
The objective of the proposed language is providing tools for developers  to obtain a clear code, simple and with a high level standard. 
Breder Language is strongly typed, allowing various syntactic and semantic errors detections at compile time, facilitating the development. 
Although the language is very useful for iPhone developers,  it can also be used in many other pourposes.
</p>

<p>
Breder Language was developed for Windows, MacOS and Linux, allowing the use of the same source code in other environments. 
Therefore, we developed a Breder Virtual Machine that abstracts all the features of specific hardware and Operating System, thus maintaining homogeneity in the application execution and capable to be used in diverse hardware as embedded devices. 
In addition, several embedded devices like iPhone and Android were tested in order to calculate the degree of cost generated by Breder Language.
</p>

<p>
Breder Language has interesting features that make game development more productive and reliable. 
One of  the main characteristics is the high Object Oriented paradigm and high level of Garbage Collection, with automatic object management in the memory.
</p>

<p>
The Breder Language has the feature of a class with type Interface [blind for revision]. 
Thus, this class called Interface will not implement the methods declared. 
This because your goal is to keep the specification of the class that implement it. 
Thus, new implementation of that concept, should follow the specification of the interface, making it easier to use the class implemented, because the user already know the signatures of the methods described in the interface.
</p>

<p>
Moreover, the Breder Language has a better encapsulation of classes, protecting their fields and methods outside of classes, thus making the code more secure. 
Also, this Language has multiple hierarchy [blind for revision], in which a class can extend multiple classes and interfaces. 
Thus, an organization and reuse of code will be better assigned, thereby facilitating the organization of classes and interfaces.
</p>

<p>
An important requirement of Breder Language is to serve projects that require intensive processing, such as games. 
For this, we created several internal aspects that ensure high performance in communication between Breder Language and other Compiled Languages.
</p>

<p>
The Breder Language allows the creation of native methods that were used to implement operations in a Compiled Language. 
We must therefore develop methods to implement such operations that need to access specific features of the iPhone SDK.
</p>

<p>
Finally, the great advantage of the Breder Language is the fact that it had a very efficient Breder Virtual Machine for native operations [blind for revision]. 
Thus, the framework will use this feature to be implemented more efficiently. In addition, the Breder Virtual Machine is concerned with embedded environment. 
This way, different techniques will be adopted so as not to harm the functioning due to lack of resources. 
For example, the Garbage Collector of Breder Virtual Machine, in embedded devices, will work lightly, not hurting application performance.
</p>