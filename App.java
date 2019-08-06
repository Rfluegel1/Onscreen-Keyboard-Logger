import org.jnativehook.*;
import org.jnativehook.dispatcher.*;
import org.jnativehook.example.*;
import org.jnativehook.keyboard.*;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import java.util.logging.Logger;
import java.util.logging.Level;

String[] keyStrings;
String[] keyCodes;
ArrayList<Key> keyList = new ArrayList();

void setup(){
  background(0);
  size(532,228);
  keyStrings = loadStrings("qwerty_key_list.txt");
  keyCodes = loadStrings("qwerty_keycode_list.txt");
  loadKeyList();
}

void draw() {
  for( Key k : keyList){
    k.show();
    k.highlight();
  }
}

void keyPressed() {
  for (Key k : keyList){
    if(keyCode==k.KeyCode){
      k.pressed = true;
    }
  }
} 

void keyReleased() {
  for (Key k : keyList){
    if(keyCode==k.KeyCode){
      k.pressed = false;
    }
  }
}

class Key{
  String identification;
  boolean pressed;
  int KeyCode;
  int xPos;
  int yPos;
  int Width;
  int Height;
  
  Key(String id, boolean p, int kc, int x, int y, int w, int h){
    identification = id;
    pressed = p;
    KeyCode = kc;
    xPos = x;
    yPos = y;
    Width = w;
    Height = h;
  }
  
  void show(){
    fill(255);
    rect(xPos,yPos,Width,Height,10);
    fill(0);
    text(identification, xPos+20, yPos+20);
  }
  
  void highlight(){
    if (pressed){
      fill(0, 90);
      rect(xPos,yPos,Width,Height,10);
    } else {
      show();
    }
  }
}

void loadKeyList(){
  int xPos = 0;
  int yPos = 0;
  for(int i = 0; i < keyCodes.length; i++){
    if(i == 14){
      yPos=38;
      xPos = 0;
    }else if(i == 28){
      yPos=38*2;
      xPos = 0;
    }else if(i == 41){
      yPos=38*3;
      xPos = 0;
    }else if(i == 54){
      yPos=38*4;
      xPos = 0;
    }
    if(keyStrings[i].contains("shift") || keyStrings[i].contains("return")){
      
      keyList.add(new Key(keyStrings[i], false, Integer.parseInt(keyCodes[i]), xPos, yPos, 38*2, 38));
      xPos+=38;
    }else{
      keyList.add(new Key(keyStrings[i], false, Integer.parseInt(keyCodes[i]), xPos, yPos, 38, 38));
    }
    xPos += 38;
  } 
}

//public class KeyLogger implements NativeKeyListener{
//  public void main(){
//    try{
//      GlobalScreen.registerNativeHook();
//    }catch(NativeHookException e){
//    }
    
//    Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
//    logger.setLevel(Level.WARNING);
  
//    logger.setUseParentHandlers(false);
//    KeyLogger kl = new 
//    GlobalScreen.addNativeKeyListener(new NativeKeyListener());
//  }
//  public void nativeKeyPressed(NativeKeyEvent e) {
//    System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//  }

//  public void nativeKeyReleased(NativeKeyEvent e) {
//    System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//  }

//  public void nativeKeyTyped(NativeKeyEvent e) {
//    System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//  }
//}
