<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
As mentioned previously, most problems related to programming for the iPhone can be solved with a specification of an appropriate framework that protects most of the possible failures that may occur. 
That should set up a high-level structure in order to isolate the internal complexities  from a basic library of the iPhone SDK.
</p>

<p>
The first basic structure would be the high-level abstract representation of a window. With this, all models of iPhone will always have a single window with different sizes depending on the device, according to its resolution. 
For example, the window for the iPhone 4 have a dimension of 960x640 for the iPhone 3G would have a dimension of 480x320 and to iPad have a dimension of 1024x768.
</p>

<p>
Another important basic structure for a game are Components. 
A Component represent a object in the device, visible or not, upgradable or not and suitable for events. 
This component represent the most basic abstraction of a feature in the window. 
Thus, in a game application, a window would have at least one component to be displayed, even though this is just a static image without any events. 
Therefore, as the component is a structure with the possibility to be graphics, it must have a dimension of size and other typical graphics properties. 
The component must have a event handler, which can be modeled as a callback.  
</p>

<p>
This object may change of state in the context of the application. 
Thus, all components registered in a window will always have a status update event at each screen update. 
After all the components change states, they will suffer the event painting in the entire hierarchy of components of the window.
</p>

<p>
At the same time we define a component as the most basic representation structure of an object. 
For this specification the framework defines a structure that represents a group of component. 
With this, we can define a hierarchy of components with parent-child relationship, in which all components may or may not have a father and the whole structure of group can have several components sons. 
This group structure will be called Container, which will be the father of several children components. 
The same occurs with the component. 
The container will also have a dimension in which a reference for some operations will be defined. 
In the specific case of the container, when any event is released, it will always be propagated to the children, passing on their information. 
Therefore, every event that occurs at the Container will always be handled by some component.
</p>

<p>
In the case of a container, the state update corresponds to updating the states of all components sons. 
The same is true when calling its painting callback, which will be delegated to all components in the order that was registered, where the first registered will be the first to be painted.
</p>

<p>
As the Component and Container has some features in common, a class hierarchy can be defined to take advantage of features of size, event, updating and painting. 
So in the class diagram, a Container will inherit from a component.
</p>

<p>
As mentioned previously, a Container can have multiple children. 
Therefore, we must define that a Component is always a parent, unless it is the root of the hierarchy.
</p>

<p>
As shown, all components will always suffer first the action of the state update and then will be painted, in case of graphical items, at each screen update. 
With this, the framework creates an entity that abstracts the process of painting and the updater.
</p>

<p>
Therefore, independent of the framework of iPhone available, a new framework that abstracts typical difficulties will be created while maintaining the simplicity in its usage. 
For a game, basically, we defined a structure that works with primitive operations with 2D images, also called sprites.
</p>

<p>
A class organization that best represents the proposed architecture will be presented below.
</p>

<p>
Figure 1 shows a class defined for the window representation.
</p>

<pre>
class Frame {
  int width;
  int height;
  Component root;
}
</pre>

<p>
Class that represents a component  in the figure 2, where contains the fields coordinated relative size, kinship and sensitive to the touch event. 
In addition, the component will have functions to retrieve absolute coordinate, change of state, painting and event handling of touch, since the act of pressing, drag and release your finger from the screen.
</p>

<pre>
class Component {
  int x;
  int y;
  int width;
  int height;
  Container parent;
  boolean selectable;
  int absoluteX();
  int absoluteY();
  void update();
  void paint(Graphic g);
  void touchPress(int x, int y);
  void touchDrag(int x, int y);
  void touchRelease(int x, int y);
}
</pre>

<p>
The class that represents a container is illustrated in figure 3. This container may handle  the fields of Its children.
</p>

<pre>
class Container extend Component {
  Component[] children;
}
</pre>

<p>
The class that represents the painting, which contains primitive functions to paint, rotate and move an image is Illustrated in the figure 4.
</p>

<pre>
class Graphic {
  void drawimage(int x, int y, Image i);
  void translate(int x, int y);
  void ratate(float angle, int x, int y);
}
</pre>

<p>
The class that represents an image from a file defined in the class constructor is shown in the figure 5.
</p>

<pre>
class Image {
  void Image(string filename);
}
</pre>

<p>
Figure 6 illustrates the class hierarchy involved between the Component and Container.
</p>

<pre>
class Container extend Component ;
</pre>

<p>
Since in Breder Language is possible to detect, at compile time, if a parameter or a return of a method may be a possibly null value or not[blind for revision], the implementation of the framework will use this feature to tie all the parameters of the methods, telling what parameters of the methods will be null or not, causing them to become more productive and reliable to use.
</p>

<p>
The native methods are operations implemented in a Compiled Language. In this work  these methods are implemented in ANSI C. 
This is an important strategy in order to guarantee high performance and for , decreasing the overhead in the Breder Virtual Machine processing. 
For the sake of performance, most of the methods of the game framework will be implemented natively.
</p>