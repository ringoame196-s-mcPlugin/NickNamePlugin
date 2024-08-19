package com.github.ringoame196_s_mcPlugin

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val plugin = this
        server.pluginManager.registerEvents(Events(), plugin)
        val command = getCommand("nickname")
        command!!.setExecutor(Command())
        command.tabCompleter = TabCompleter()
    }
}
