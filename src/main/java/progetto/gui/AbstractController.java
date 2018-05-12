package progetto.gui;

import javafx.application.Platform;
import progetto.utils.AbstractObservable;
import progetto.utils.IObserver;

public abstract class AbstractController <U, T extends AbstractObservable <U>> {

    private T observable;

    private IObserver<U> iObserver = ogg -> Platform.runLater(this::update);

    public final void setObservable(T newObservable){

        if(observable!=null){

            observable.removeObserver(iObserver);

        }

        observable = newObservable;

        observable.addObserver(iObserver);

        onObserverReplaced();

        Platform.runLater(this::update);

    }



    protected void onObserverReplaced(){

        //Le classi derivate possono estenderla se vogliono

    }

    protected T getObservable(){

        return observable;

    }

    protected abstract void update();

}
