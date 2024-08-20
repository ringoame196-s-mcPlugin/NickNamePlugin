package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.Manager.NickNameManager
import com.github.ringoame196_s_mcPlugin.commands.Command
import com.github.ringoame196_s_mcPlugin.commands.TabCompleter
import com.github.ringoame196_s_mcPlugin.events.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val plugin = this
        plugin.saveDefaultConfig() // configファイルを生成
        val nickNameManager = NickNameManager(plugin)
        nickNameManager.makeTable() // table作成
        server.pluginManager.registerEvents(PlayerJoinEvent(plugin), plugin)
        server.pluginManager.registerEvents(Events(), plugin)
        // コマンド登録
        val command = getCommand("nickname")
        command!!.setExecutor(Command(plugin))
        command.tabCompleter = TabCompleter()
    }
}
