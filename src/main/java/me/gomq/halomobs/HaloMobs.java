package me.gomq.halomobs;

import com.fren_gor.ultimateAdvancementAPI.AdvancementMain;
import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;

import me.gomq.halomobs.Events.EntityDeadEvent;
import me.gomq.halomobs.Events.PlayerAchievementDoneEvent;
import me.gomq.halomobs.Events.PlayerInteractionEvent;
import me.gomq.halomobs.Events.PlayerServerEnterEvent;
import me.gomq.halomobs.Recipes.RecipeManager;
import me.gomq.halomobs.Util.Advancements;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

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

        advancementInitialize();
    }

    public void advancementInitialize() {
        mainAdvancement.enableSQLite(new File("database.db"));

        api = UltimateAdvancementAPI.getInstance(this);

        AdvancementTab advancementTab = api.createAdvancementTab("halomobs");
        RootAdvancement rootAdvancement = new RootAdvancement(advancementTab,
                "custom",
                new AdvancementDisplay(Material.WAXED_OXIDIZED_COPPER, "HaloMobs", AdvancementFrameType.TASK, true, true, 0, 0, "The Start of the Halloween"),
                "textures/block/carved_pumpkin.png"
        );

        Advancements.CraftAdv createEgg = new Advancements.CraftAdv("create_egg_item",
                new AdvancementDisplay(Material.TURTLE_EGG, "Egg Enterance", AdvancementFrameType.TASK, true, true, 1.5f, 1.5f, "Craft an Egg Item"),
                Egg, Egg.asQuantity(16),
                rootAdvancement);

        Advancements.KillAdv killGolem = new Advancements.KillAdv("kill_golem",
                new AdvancementDisplay(Material.IRON_BLOCK, "Golem Killer", AdvancementFrameType.TASK, true, true, 3f, 0, "Kill Iron Golem twice"),
                EntityType.IRON_GOLEM, new ItemStack(Material.IRON_BLOCK, 16),
                rootAdvancement, 2);

        Advancements.MineAdv mineNetherite = new Advancements.MineAdv("mine_netherite",
                new AdvancementDisplay(Material.ANCIENT_DEBRIS, "Netherite Master", AdvancementFrameType.TASK, true, true, 1.5f, 0, "Mine Ancient Debris 10 times"),
                Material.ANCIENT_DEBRIS, new ItemStack(Material.NETHERITE_BLOCK, 1),
                killGolem, 10);

        advancementTab.registerAdvancements(rootAdvancement, createEgg, killGolem, mineNetherite);
    }
}
