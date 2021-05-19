package handsOn5;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import net.sf.clipsrules.jni.*;

public class MyAgent extends Agent {
  private AgentGui gui;
  private Environment clips;

  protected void setup() {
    gui = new AgentGui(this);
    gui.showGui();
  }

  public void load(String nameFile) {
    if(!nameFile.equals("product") && !nameFile.equals("person") && !nameFile.equals("market")){
      System.out.println("Your file " +nameFile+ " dosen't exist");
      return ;
    }
      
    addBehaviour(
      new CyclicBehaviour() {
        public void action() {
          Environment clips;
          System.out.println();
          System.out.println("You have loaded the file: " + nameFile);
          try {

            clips = new Environment();

            clips.eval("(clear)");
            clips.load("/home/nestor/jade/src/handsOn5/clips/"+nameFile+".clp");
            clips.eval("(reset)");
            clips.eval("(facts)");
            clips.run();
          } catch (Exception e) {
            System.out.println(e);
          }

          System.out.println();
          block();
        }
      }
    );
  }

}
