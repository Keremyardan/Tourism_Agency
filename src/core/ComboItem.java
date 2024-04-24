package core;



// logic of combo item is, this procedure is generally used in Java Swing for a visual interface.
// with a list in combo box, we provide to the client multiple choices.
// combo items are the values those located in combo box. this class, holds keys and values together for combo box.
// so, we won't have to declare variables and key values for each item.
public class ComboItem {
    private int key;
    private String value;

    // constructor with parameters
    public ComboItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    // default constructor
    public ComboItem(){

    }

    // getters and setters
    public int getKey() {
        return key;
    }


    public void setKey(int key) {
        this.key = key;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String toString(){
        return this.value;
    }
}
