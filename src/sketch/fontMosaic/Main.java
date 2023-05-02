package sketch.fontMosaic;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;

public class Main extends PApplet {
    private PImage img;
    private final ArrayList<ArrayList<Float>> randomValues = new ArrayList<>();
    private final int baseRect = 20;
    private int numH = width / baseRect;
    private int numV = width / baseRect;
    private final ArrayList<ArrayList<Float>> forceList = new ArrayList<>();
    //メイン関数
    public static void main(String[] args) {
        PApplet.main(new String[]{Main.class.getName()});
    }

    @Override
    public void settings() {
        size(1000, 1000);
    }

    @Override
    public void setup() {

        background(255);
        numH = width / baseRect;
        numV = height / baseRect;

        img = loadImage("image.png");

        noStroke();
        createRandomValues();
    }

    @Override
    public void draw() {
        drawRect();


        for (int h = 0; h < numH; h++) {
            for (int v = 0; v < numV; v++) {
                float f = forceList.get(h).get(v);
                float mappedDecay = random(0.7f,0.99f);
                forceList.get(h).set(v, f * mappedDecay);
            }
        }
    }

    void createRandomValues() {
        float seed = 30;

        for (int h = 0; h < numH; h++) {
            randomValues.add(new ArrayList<>());
            forceList.add(new ArrayList<>());
            for (int v = 0; v < numV; v++) {
                randomValues.get(h).add(random(0, seed) - seed / 2);
                forceList.get(h).add(0.0f);
            }
        }
    }

    @Override
    public void mouseClicked() {
        Collections.shuffle(randomValues);

        for (int h = 0; h < numH; h++) {
            for (int v = 0; v < numV; v++) {
                forceList.get(h).set(v,random(4.5f,8.8f));
            }
        }
    }

    void drawRect() {
        //float seed = map(mouseX, 0, 1000, 0, 50);

        fill(0);
        background(255);


        for (int h = 0; h < numH; h++) {
            for (int v = 0; v < numV; v++) {
                float force = forceList.get(h).get(v);
                float random = randomValues.get(h).get(v) * force;
                float randomSize = randomValues.get(h).get(v) * force;

                float rectSize = baseRect + randomSize;
                int x = h * baseRect;
                int y = v * baseRect;

                translate(x + baseRect / 2.0f, y + baseRect / 2.0f);
                int c = img.get(x, y);

                if (red(c) != 255) {
                    //rect(random, random,rectSize, rectSize);
                    rotate(radians(random));
                    rect(0, 0, rectSize, rectSize);
                    //rect(0, 0,baseRect, baseRect);
                    rotate(radians(-random));
                    //rotate(10);
                } else {
                    rotate(radians(random * 0.1f));
                    //rect(0, 0,baseRect+randomSize*0.2, baseRect+randomSize*0.2);
                    rect(0, 0, baseRect, baseRect);
                    rotate(radians(-random * 0.1f));
                }
                translate(-(x + baseRect / 2.0f), -(y + baseRect / 2.0f));

            }
        }
    }
}
