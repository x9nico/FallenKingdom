package fr.devsylone.fallenkingdom.commands.scoreboard.scoreboardcommands;

import org.bukkit.entity.Player;

import fr.devsylone.fallenkingdom.Fk;
import fr.devsylone.fallenkingdom.commands.scoreboard.FkScoreboardCommand;
import fr.devsylone.fallenkingdom.exception.FkLightException;
import fr.devsylone.fallenkingdom.players.FkPlayer;
import fr.devsylone.fallenkingdom.players.FkPlayer.PlayerState;

public class SetName extends FkScoreboardCommand
{
	public SetName()
	{
		super("SetName", "<text>", 1, "Modifie le nom du scoreboard.");
	}

	public void execute(Player sender, FkPlayer fkp, String[] args) throws Exception
	{
		if(fkp.getState() != PlayerState.EDITING_SCOREBOARD)
		{
			Fk.getInstance().getCommandManager().executeCommand(new String[] {"scoreboard", "edit"}, sender);
			if(!fkp.hasAlreadyLearntHowToEditTheBeautifulScoreboard())
				return;
		}
		
		String line = String.join(" ", args);
		
		if(line.length() >= 32)
			throw new FkLightException("Le titre ne peut faire plus de 32 caractères");
		
		Fk.getInstance().getScoreboardManager().setName(line.replace("&", "§"));
	}
}
