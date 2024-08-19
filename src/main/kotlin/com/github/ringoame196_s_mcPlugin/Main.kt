package com.github.ringoame196_s_mcPlugin

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val plugin = this
        plugin.saveDefaultConfig() // configファイルを生成
        server.pluginManager.registerEvents(Events(), plugin)
        // コマンド登録
        val command = getCommand("nickname")
        command!!.setExecutor(Command(plugin))
        command.tabCompleter = TabCompleter()
    }
}
