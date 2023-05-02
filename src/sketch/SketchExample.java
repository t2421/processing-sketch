package sketch;
import processing.core.PApplet;

public class SketchExample extends  PApplet {
    //メイン関数
    public static void main(String[] args) {
        PApplet.main(new String[]{SketchExample.class.getName()});
    }

    @Override
    public void settings() {
        size(300, 300);
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
        background(200);
    }
}
