package HelperMate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;

import HelperMate.tasks.Drink;
import HelperMate.tasks.Eat;
import HelperMate.tasks.Task;

@Script.Manifest(name = "Helper Mate", description = "Assists the player buy drinking pots and eating food.", properties = "client=6; topic=0;")
public class Main extends PollingScript<ClientContext> implements PaintListener {

	private static int prayerPotLeft;
	private static int overloadLeft;
	private static int foodLeft;


	private List<Task> taskList = new ArrayList<Task>();
	private static String status = "Inactive";

	private Rectangle box = new Rectangle(20, 20, 140, 80);

	@Override
	public void start() {

		taskList.addAll(Arrays.asList(new Eat(ctx), new Drink(ctx)));

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
		prayerPotLeft = count;
	}

	public static void setOverloadSwigs(int count) {
		overloadLeft = count;
	}

	@Override
	public void repaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;

		Font fontBold = new Font("Sans-serrif", Font.BOLD, 20);
		Font font = new Font("Sans-serrif", Font.BOLD, 12);
		Color border = new Color(56, 27, 35);
		Color background = new Color(33, 27, 56, 70);

		g.setFont(font);

		g.setColor(border);
		g.draw(box);
		g.setColor(background);
		g.fill(box);

		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Status:", 25, 35);
		g.drawString("Overloads:", 25, 55);
		g.drawString("Prayer Pots:", 25, 75);
		g.drawString("Monkfish:", 25, 95);

		g.drawString(getStatus(), 70, 35);
		g.drawString("" + overloadLeft, 90, 55);
		g.drawString("" + prayerPotLeft, 100, 75);
		g.drawString("" + foodLeft, 85, 95);
	}

	public static String getStatus() {
		return status;
	}

}
