package me.gomq.halomobs;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HaloMobs extends JavaPlugin {
    @Override
    public void onEnable() {
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
}
