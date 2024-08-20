package com.github.ringoame196_s_mcPlugin

import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.io.File

class NickNameManager(plugin: Plugin) {
    private val dataBaseManager = DataBaseManager()
    private val dataBaseFilePath = "${plugin.dataFolder.path}\\playerData.db"
    fun setNickName(player: Player, nickName: String) {
        player.setDisplayName(nickName)
        player.setPlayerListName(nickName)
    }

    fun makeTable() {
        if (!File(dataBaseFilePath).exists()) {
            val command = "CREATE TABLE IF NOT EXISTS NickNameTable (uuid TEXT PRIMARY KEY, nickname TEXT)"
            dataBaseManager.runSQLCommand(dataBaseFilePath, command)
        }
    }

    fun acquisitionNickname(player: Player): String {
        val command = ""
        return ""
    }
}
