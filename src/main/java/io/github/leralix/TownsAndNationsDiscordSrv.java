package io.github.leralix;

import github.scarsz.discordsrv.DiscordSRV;
import io.github.leralix.eventListener.NewsBroadcast;
import io.github.leralix.lang.Lang;
import org.bukkit.plugin.java.JavaPlugin;
import org.leralix.lib.utils.config.ConfigUtil;
import org.tan.api.TanAPI;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TownsAndNationsDiscordSrv extends JavaPlugin {

    private static TownsAndNationsDiscordSrv instance;
    private boolean loadedSuccessfully = false;

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "\u001B[33m------------Towns & Nations-Discord SRV---------------\u001B[0m");
        instance = this;

        Logger logger = getLogger();
        DiscordSRV discordSRV = DiscordSRV.getPlugin();

        getLogger().log(Level.INFO, "Loading configuration...");

        ConfigUtil.saveAndUpdateResource(this, "config.yml");

        Constants.init(getConfig());
        Lang.loadTranslations(new File(getDataFolder(),"lang") , Constants.getServerLang());

        getLogger().log(Level.INFO, "Registering broadcast...");
        TanAPI.getInstance().getEventManager().registerEvents(new NewsBroadcast(discordSRV, logger));

        loadedSuccessfully = true;
        getLogger().log(Level.INFO, "\u001B[33m------------Towns & Nations-Discord SRV---------------\u001B[0m");
    }


    @Override
    public void onDisable() {
        if(!loadedSuccessfully){
            getLogger().log(Level.WARNING, "\u001B[33mPlugin was not loaded successfully, disabling...\u001B[0m");
            return;
        }

        getLogger().log(Level.INFO, "\u001B[33mPlugin disabled successfully\u001B[0m");
    }

    public static TownsAndNationsDiscordSrv getInstance() {
        return instance;
    }

}
