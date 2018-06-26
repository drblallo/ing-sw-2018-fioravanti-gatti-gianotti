package progetto.view.gui;

import progetto.model.IModel;
import progetto.model.ObservableModel;

public abstract class AbstractStateController {

    private ViewStateMachine viewStateMachine;

    public void setViewStateMachine(ViewStateMachine viewStateMachine){
        this.viewStateMachine = viewStateMachine;
    }

    public ViewStateMachine getViewStateMachine(){
        return viewStateMachine;
    }

    public IModel getModel()
    {
        return viewStateMachine.getModel();
    }

    public ObservableModel getObsModel()
    {
        return viewStateMachine.getObsModel();
    }

    public void onPreShow(){
        //
    }

    public void setup(){
        //
    }

}
