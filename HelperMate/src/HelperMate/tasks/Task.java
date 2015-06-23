package HelperMate.tasks;

import org.powerbot.script.ClientContext;
import org.powerbot.script.ClientAccessor;

public abstract class Task<C extends ClientContext> extends ClientAccessor<C>{

	public Task(C ctx) {
		super(ctx);
	}

	public abstract boolean activate();
	
	public abstract void execute();
	
}
