/*
*  Structural Design Pattern
*  This pattern decouples an abstractionfromits implementation (as in usage)
*   so that the two can vary independently. ⟣*
*  |—————————————————————|                     |——————————————————————————————|
*  |  Abstraction (Abs)  |                     |  Implementation (Interface)  |
*  |—————————————————————|◇———————————————————|——————————————————————————————|
*  | + operation()       |                     | + operationImpl()            |
*  |—————————————————————|                     |——————————————————————————————|
*             △                                              △
*             |                                              |*
*  |——————————————————————|                    |——————————————————————————————|
*  |  RefinedAbstraction  |                    |  ConcreteImplementations     |
*  |——————————————————————|                    |——————————————————————————————|
*/

interface Color {
    public void fillWithColor(int borderSize);
}

class Red implements Color {
    public void fillWithColor(int borderSize) {
        System.out.println("Red border of " + borderSize + " pixels.");
    }
}

class Green implements Color {
    public void fillWithColor(int borderSize) {
        System.out.println("Green border of " + borderSize + " pixels.");
    }
}

abstract class Shape {
    protected Color myColor;
    protected int borderSize;

    protected Shape (Color color) {
        this.myColor = color;
    }

    // Implementor Specific method
    abstract public void drawShapes();
    
    // Abstraction Specific method
    public void modifyBorders(int borderSize) {
        this.borderSize = borderSize;
    }
}

class Rectangle extends Shape {
    public Rectangle(Color color) {
        super(color);
    }

    public void drawShapes() {
        System.out.print("Rectangle with ");
        this.myColor.fillWithColor(this.borderSize);
    }
}

class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }

    public void drawShapes() {
        System.out.print("Triangle with ");
        this.myColor.fillWithColor(this.borderSize);
    }
}

public class Bridge {
    public static void main (String[] Args) {
        System.out.println("*****   Bridge Design Pattern   *****\n");

        Shape shape1 = new Rectangle(new Red());
        shape1.modifyBorders(10);
        shape1.drawShapes();

        
        Shape shape2 = new Triangle(new Green());
        shape2.modifyBorders(15);
        shape2.drawShapes();
    }
}