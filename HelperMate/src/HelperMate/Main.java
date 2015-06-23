package HelperMate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import org.powerbot.script.rt6.ClientContext;

import HelperMate.tasks.Eat;
import HelperMate.tasks.Drink;
import HelperMate.tasks.MeteorCheck;
import HelperMate.tasks.Task;

@Script.Manifest(name = "Helper Mate", description = "Assists the player buy drinking pots and eating food.", properties = "client=6; topic=0;")
public class Main extends PollingScript<ClientContext> implements PaintListener {

	private static int prayerPotSwigs;
	private static int overloadSwigs;

	private static boolean nearMeteor;

	private List<Task> taskList = new ArrayList<Task>();
	private static String status = "Inactive";

	@Override
	public void start() {
		taskList.addAll(Arrays.asList(new Eat(ctx), new Drink(ctx),
				new MeteorCheck(ctx)));
	}

	@Override
	public void poll() {
		for (Task task : taskList) {
			if (task.activate()) {
				task.execute();
			} else {
				setStatus("Inactive");
			}
		}
	}

	public static void setStatus(String nStatus) {
		status = nStatus;
	}

	public static void setPrayerPotSwigs(int count) {
		prayerPotSwigs = count;
	}

	public static void setOverloadSwigs(int count) {
		overloadSwigs = count;
	}

	@Override
	public void repaint(Graphics g1) {
		Font fontBold = new Font("Sans-serrif", Font.BOLD, 20);
		Font font = new Font("Sans-serrif", Font.BOLD, 12);
		Graphics2D g = (Graphics2D) g1;
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.fillRect(4, 415, 528, 140);
		g.setColor(Color.CYAN);
		g.drawString("Status:", 5, 427);

		switch (status) {
		case "Inactive":
			g.setColor(Color.red);
			break;
		default:
			g.setColor(Color.GREEN);
			break;
		}
		g.drawString(status, 47, 427);

		g.setFont(fontBold);
		g.setColor(Color.RED);
		// g.drawString("Warning Overloads are low!", 268, 430);
		// g.drawString("Warning Prayer pots low!", 290, 430);
		// g.drawString("Warning Low food!", 350, 430);

		if (nearMeteor)
			g.drawString("A meteor has crashed near you!", 5, 546);
	}

	public static void nearMet(boolean b) {
		nearMeteor = b;
	}
}
