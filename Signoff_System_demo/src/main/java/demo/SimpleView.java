package demo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


@SpringView(name = SimpleView.VIEW_NAME)
public class SimpleView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "SimpleView";

	@Override
	public void enter(ViewChangeEvent event) {
		this.removeAllComponents();
		
		final TextField name = new TextField();
		name.setCaption("Type your name here:");

		Button button = new Button("Click Me");
		button.addClickListener(e -> {
			this.addComponent(new Label("Thanks " + name.getValue() + ", it works!"));
		});

		this.addComponents(name, button);
		this.setMargin(true);
		this.setSpacing(true);
	}

}
