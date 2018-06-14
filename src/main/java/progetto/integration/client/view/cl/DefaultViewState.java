package progetto.integration.client.view.cl;

public class DefaultViewState extends AbstractCLViewState {

    public DefaultViewState(CommandLineView view)
    {
        super("Default view state", view);
    }

	@Override
	public void onApply(){

		registerCommand(new NewGameCommand(getView()));
		registerCommand(new ContinueCommand(getView()));
		registerCommand(new SetNameCommand(getView()));

	}

	@Override
	public String getMessage(){

		return "\nBenvenuto in Sagrada!\n";

	}

}
