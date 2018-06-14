package progetto.integration.client.view.cl;

public class StateStub extends AbstractCLViewState {

    private CommandLineView commandLineView;
    private String name;

    public StateStub(String name, CommandLineView cl) {
        super(name, cl);
    }

    @Override
    public void addObservers() {

    }

    @Override
    public void onApply() {
    }

    @Override
    public String getMessage() {
        return "StateStub";
    }
}
