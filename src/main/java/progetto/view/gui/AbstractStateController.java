package progetto.view.gui;

public abstract class AbstractStateController {

    private ViewStateMachine viewStateMachine;

    public void setViewStateMachine(ViewStateMachine viewStateMachine){

        this.viewStateMachine = viewStateMachine;

    }

    public ViewStateMachine getViewStateMachine(){

        return viewStateMachine;

    }

    public void onPreShow(){

        //

    }

    public void setup(){

        //

    }

}
