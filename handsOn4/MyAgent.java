package handsOn4;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import net.sf.clipsrules.jni.*;

public class MyAgent extends Agent {
  
  private AgentGui gui;
  private Environment clips;

  protected void setup() {
    gui = new AgentGui(this);
    gui.showGui();
    addBehaviour(new inferenceEngine(this));
  }

  public void addRuleFact(String statement) {
    addBehaviour(
      new CyclicBehaviour() {
        public void action() {
          try {
            if (statement.contains("defrule")) {
              clips.build(
                statement
              );
            } else {
              clips.assertString(
                statement
              );
            }
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

  private class inferenceEngine extends OneShotBehaviour {

    public inferenceEngine(Agent a) {
      super(a);
    }

    public void action() {
      try {
        clips = new Environment();

        clips.eval("(clear)");
        clips.load("/home/nestor/jade/src/handsOn4/clips/book.clp");
        clips.eval("(reset)");
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }
}
