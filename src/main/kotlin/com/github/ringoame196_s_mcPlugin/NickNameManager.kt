package com.github.ringoame196_s_mcPlugin

import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.io.File

class NickNameManager(plugin: Plugin) {
    private val dataBaseManager = DataBaseManager()
    private val dataBaseFilePath = "${plugin.dataFolder.path}\\playerData.db"
    fun setNickName(player: Player, nickName: String) {
        changeName(player, nickName)
        saveNickName(player, nickName) // データベースに保存
    }

    fun changeName(player: Player, nickName: String) {
        val supportedColorNickName = supportedColorCode(nickName)
        player.setDisplayName(supportedColorNickName)
        player.setPlayerListName(supportedColorNickName)
    }

    fun makeTable() {
        if (!File(dataBaseFilePath).exists()) {
            val command = "CREATE TABLE IF NOT EXISTS NickNameTable (uuid TEXT PRIMARY KEY, nickname TEXT)"
            dataBaseManager.runSQLCommand(dataBaseFilePath, command)
        }
    }

    fun acquisitionNickname(player: Player): String? {
        val uuid = player.uniqueId
        val command = "SELECT nickname FROM NickNameTable WHERE uuid = '$uuid';"
        // データベースから結果を取得
        val nickname = dataBaseManager.acquisitionStringValue(dataBaseFilePath, command, "nickname")

        return nickname
    }

    private fun saveNickName(player: Player, nickName: String) {
        val registrationValue = acquisitionNickname(player)
        val uuid = player.uniqueId

        val command = if (registrationValue == null) {
            // 新しいレコードを挿入する
            "INSERT INTO NickNameTable (uuid, nickname) VALUES ('$uuid', '$nickName');"
        } else {
            // 既存のレコードを更新する
            "UPDATE NickNameTable SET nickname = '$nickName' WHERE uuid = '$uuid';"
        }

        dataBaseManager.runSQLCommand(dataBaseFilePath, command) // 保存
    }

    fun supportedColorCode(text: String): String {
        return text.replace("&", "§")
    }
}
