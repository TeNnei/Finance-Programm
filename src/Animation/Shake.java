package Animation;


import javafx.animation.TranslateTransition;
import javafx.scene.Node;

import javafx.util.Duration;

public class Shake {
private TranslateTransition anim;

public Shake(Node node){
    anim = new TranslateTransition(Duration.millis(70), node);
    anim.setFromX(0f);
    anim.setFromY(0f);
    anim.setByX(10);
    anim.setByY(20);
    anim.setCycleCount(3);
    anim.setAutoReverse(true);
}
public void play(){
    anim.playFromStart();
}
}
