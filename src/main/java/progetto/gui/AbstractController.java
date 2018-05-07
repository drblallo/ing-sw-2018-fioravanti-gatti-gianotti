package progetto.gui;

import javafx.application.Platform;
import progetto.utils.AbstractObservable;
import progetto.utils.IObserver;

public abstract class AbstractController <U, T extends AbstractObservable <U>> {

    private T observable;

    private IObserver<U> iObserver = new IObserver<U>() {
        @Override
        public void notifyChange(U ogg) {

            Platform.runLater(() -> update());

        }
    };

    public final void setObservable(T observable){

        if(observable!=null){

            observable.removeObserver(iObserver);

        }

        observable.addObserver(iObserver);

        onObserverReplaced();

        Platform.runLater(()->update());

    }



    protected void onObserverReplaced(){

        //Le classi derivate possono estenderla se vogliono

    }

    protected T getObservable(){

        return observable;

    }

    protected abstract void update();

}
