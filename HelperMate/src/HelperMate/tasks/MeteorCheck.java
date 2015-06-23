package HelperMate.tasks;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import HelperMate.Main;

public class MeteorCheck extends Task<ClientContext> {

	public MeteorCheck(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		final Npc meteor = ctx.npcs.select().id(21086).nearest().peek();
		if (meteor.inViewport()) {
			Main.nearMet(true);
			return true;
		} else {
			Main.nearMet(false);
			return false;
		}
	}

	@Override
	public void execute() {
	}

	public String metType() {
		return null;
	}
}
