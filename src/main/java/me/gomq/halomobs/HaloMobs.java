package me.gomq.halomobs;

import com.fren_gor.ultimateAdvancementAPI.AdvancementMain;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;

import me.gomq.halomobs.Events.EntityDeadEvent;
import me.gomq.halomobs.Events.PlayerAchievementDoneEvent;
import me.gomq.halomobs.Events.PlayerInteractionEvent;
import me.gomq.halomobs.Events.PlayerServerEnterEvent;
import me.gomq.halomobs.Recipes.RecipeManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import static me.gomq.halomobs.AddAdvancements.initializeAdvancements;
import static me.gomq.halomobs.Recipes.EggRecipe.Egg;

public class HaloMobs extends JavaPlugin {
    private AdvancementMain mainAdvancement;
    public static UltimateAdvancementAPI api;

    @Override
    public void onLoad() {
        mainAdvancement = new AdvancementMain(this);
        mainAdvancement.load();
    }

    @Override
    public void onEnable() {
        initialize();
        InputStream propertyStream = getClass().getClassLoader().getResourceAsStream("version.properties");
        Properties propertyReader = new Properties();

        try {
            propertyReader.load(propertyStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String version = propertyReader.getProperty("version");

        getLogger().info(ChatColor.GREEN + "Initializing HaloMobs Plugin v" + version);
        getLogger().info(ChatColor.GRAY + """

                 ('-. .-.   ('-.                           _   .-')               .-. .-')    .-')   \s
                ( OO )  /  ( OO ).-.                      ( '.( OO )_             \\  ( OO )  ( OO ). \s
                ,--. ,--.  / . --. / ,--.      .-'),-----. ,--.   ,--.).-'),-----. ;-----.\\ (_)---\\_)\s
                |  | |  |  | \\-.  \\  |  |.-') ( OO'  .-.  '|   `.'   |( OO'  .-.  '| .-.  | /    _ | \s
                |   .|  |.-'-'  |  | |  | OO )/   |  | |  ||         |/   |  | |  || '-' /_)\\  :` `. \s
                |       | \\| |_.'  | |  |`-' |\\_) |  |\\|  ||  |'.'|  |\\_) |  |\\|  || .-. `.  '..`''.)\s
                |  .-.  |  |  .-.  |(|  '---.'  \\ |  | |  ||  |   |  |  \\ |  | |  || |  \\  |.-._)   \\\s
                |  | |  |  |  | |  | |      |    `'  '-'  '|  |   |  |   `'  '-'  '| '--'  /\\       /\s
                `--' `--'  `--' `--' `------'      `-----' `--'   `--'     `-----' `------'  `-----'\s""");
        getLogger().info(ChatColor.GREEN + "Developed by GomQ (https://github.com/igomq)");
    }

    @Override
    public void onDisable() { getLogger().info(ChatColor.RED + "Disabling HaloMobs Plugin."); }

    public void initialize() {
        ArrayList<ShapelessRecipe> recipes = RecipeManager.getRecipes();
        for (ShapelessRecipe r : recipes) {
            Bukkit.addRecipe(r);
        }

        getServer().getPluginManager().registerEvents(new PlayerInteractionEvent(), this);
        getServer().getPluginManager().registerEvents(new EntityDeadEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerAchievementDoneEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerServerEnterEvent(), this);

        mainAdvancement.enableSQLite(new File("database.db"));
        api = UltimateAdvancementAPI.getInstance(this);
        initializeAdvancements();
    }
}
