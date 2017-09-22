<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
With the spread of the iPhone, iPad and the superiority in graphics applications with the iPhone SDK, a significant increase in developing programs for this platform overcame in the latest years. 
With the Apple's application called App Store, facilitated the distribution of commercial software developed for the iPhone, either for big and small developers. 
</p>

<p>
New applications increasingly challenging the limits of the hardware. 
In addition, several other ways to develop an application for the iPhone are being used to meet the needs of a better development. 
</p>

<p>
Currently, many applications have been developed so unproductive. 
This is happened because to develop an application, you must use a native Language of iPhone called Objective-C which not provide many features for the developer. 
Moreover, the standard library of the iPhone has many mechanisms that enable the occurrence of failures due to their misuse. 
It because the framework of iPhone is a low level. 
With this, if you call a operational in wrong context, the process can be killed. 
To make a development of a game in Objective-C Language, it is necessary to worry about several features that other Language and game frameworks typically offer.
</p>

<p>
Moreover, the Objetive-C Language is not a easy for programming. 
This because in this Language, the developer need to worry about many thinks, that he should not worry. 
For example, some operation need be execute ate same context. In this case, the Language or the framework need to evite this call with same way. 
With a Language in the back, with the organization of the class, the Language can previne this operation in wrong time.
</p>

<p>
A framework is responsible for implementing a specific functionality. 
Furthermore, the framework should have mechanisms that allow the configuration of its execution. 
Unlike libraries, the framework dictates the flow control application, called Inversion of Control [Martin Fowler, 2004]. 
Inversion of Control is an abstract principle describing an aspect of some software architecture designs in which the flow of control of a system is inverted in comparison to procedural programming. 
In traditional programming the flow is controlled by a central piece of code with callbacks. 
Using Inversion of Control this central control as a design principle is left behind.
</p>

<p>
For example, the framework will ask how to paint the player in the game or what happend if i touch the screen in a part of the game. 
But, the standards frameworks provided by Apple to develop applications for iPhone, are very sensitive to failures, in which the framework will try to resolve conflicts in a better way.
</p>

<p>
The aim of this paper is to establish a environment of development for create game with simply, secure and productive way, minimizing the failures and the complexity of this the iPhone environment. 
For this, we created several abstractions on top of a framework, both to meet the difficulties of standard frameworks, how to address the limitations of the Programming Language Objective-C.
</p>

<p>
The great advantage of the Breder Language is the fact that it had a very efficient Virtual Machine for native operations. 
Thus, the framework will use this feature to be implemented more efficient yours methods. 
In addition, this Virtual Machine is concerned with embedded environment. 
This way, different techniques will be adopted so as not to harm the functioning due to lack of resources. 
</p>

<p>
In the next section will be presented limitations and unproductive in the development of an application for the iPhone and will also be discussed solutions to such problems. 
Session 3 will present the definition of the Breder Language, specifically created for this work. 
In session 4 we  will present the specification of  a framework able to address such described limitations and completelly based on the Breder Language. 
Session 5 describes how to implement a framework using our proposed Language. 
In session 6 we  will present a demo built on our proposed solution. 
Session 7 presents considerations necessary to maintain this environment with performance. 
Finally, the last session presents the conclusions and future issues of our proposal.
</p>