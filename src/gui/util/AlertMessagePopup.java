package gui.util;

import gui.menu.LoadingState;
import gui.util.managers.ResourceManager;
import mdes.slick.sui.Button;
import mdes.slick.sui.Display;
import mdes.slick.sui.Label;
import mdes.slick.sui.Popup;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

import org.newdawn.slick.Color;

public class AlertMessagePopup extends Popup {

	public AlertMessagePopup(Display display) {
		setSize(300, 100);
		setLocationRelativeTo(display);
		setBackground(new Color(0, 0, 0, 0.6f));
		setOpaque(true);
		setVisible(false);
		
		final Button ok = new Button("OK");
		ok.setName("okButton");
		ok.setSize(200, 30);
		ok.setFont(LoadingState.font);
		ok.setForeground(Color.white);
		ok.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		ok.setLocationRelativeTo(this);
		ok.setLocation(ok.getX(), 60);
		this.add(ok);
		
		ActionListener okAction = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				ok.getParent().setVisible(false);
			}
		};		
		ok.addActionListener(okAction);
		
		Label invalidLoginLabel = new Label();
		invalidLoginLabel.setFont(LoadingState.font);
		invalidLoginLabel.setBounds(0, 15, 300, 30);
		invalidLoginLabel.setForeground(Color.white);
		invalidLoginLabel.setName("invalidLoginLabel");
		this.add(invalidLoginLabel);
		
		display.add(this);
	}
}
